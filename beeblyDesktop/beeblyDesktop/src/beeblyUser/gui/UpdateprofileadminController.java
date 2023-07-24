/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeblyUser.gui;

import beebly.entity.Admin;
import beebly.services.AdminServices;
import beebly.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amirb
 */
public class UpdateprofileadminController implements Initializable {

     @FXML
private TextField Pnom;
@FXML
private TextField pprenom;
@FXML
private TextField pemail;
@FXML
private TextField padresse;
@FXML
private TextField ptel;
@FXML
private TextField pmdp;
@FXML
private TextField pmdpc;
@FXML
private TextField pcin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AdminServices cs=new AdminServices();
          UserService us=new UserService();
          Pnom.setText(us.currentuser.getNom());
          pprenom.setText(us.currentuser.getPrenom());
          pemail.setText(us.currentuser.getAdrmail());
          padresse.setText(us.currentuser.getAdresse());
          ptel.setText(us.currentuser.getTel());
          pmdp.setText(us.currentuser.getMdp());
          pmdpc.setText(us.currentuser.getMdp());
          pcin.setText(cs.getCin(us.currentuser.getId()));
    }   
    @FXML
    private void goToProfile(ActionEvent event) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("../../beeblyUser/gui/profileadmin.fxml"));
            Parent root = loader.load(); 
            Pnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void UpdateProfile(ActionEvent event) {
         
         try {
             AdminServices cs=new AdminServices();
         UserService us=new UserService();
         String cin = cs.getCin(us.currentuser.getId());
            boolean test=true;
            
            String nom = Pnom.getText();
            Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(nom);
            boolean b = m.find();
            if(b || nom==null)
            {
                Pnom.setStyle("-fx-text-box-border:red;");
                System.out.println("nom ghalet");
                test=false;
            }else{
                Pnom.setStyle("-fx-text-box-border:transparent;");
            }
            
            String prenom = pprenom.getText();
            m = p.matcher(prenom);
            b = m.find();
            if(b || prenom==null)
            {
                pprenom.setStyle("-fx-text-box-border:red;");
                System.out.println("prenom ghalet");
                test=false;
            }else{
                pprenom.setStyle("-fx-text-box-border:transparent;");
            }
           
            String adresse = padresse.getText();
            if(adresse==null)
            {
                padresse.setStyle("-fx-text-box-border:red;");
                test=false;
            }else{
                padresse.setStyle("-fx-text-box-border:transparent;");
            }

            String tel = ptel.getText();
            p = Pattern.compile("[^0-9]", Pattern.CASE_INSENSITIVE);
            m = p.matcher(tel);
            b = m.find();
            if(tel.length()!=8 || b)
            {
                ptel.setStyle("-fx-text-box-border:red;");
                System.out.println("tel ghalet");
                test=false;
            }else{
                ptel.setStyle("-fx-text-box-border:transparent;");
            }
            
            String ppcin = pcin.getText();
            p = Pattern.compile("[^0-9]", Pattern.CASE_INSENSITIVE);
            m = p.matcher(ppcin);
            b = m.find();
            if(b || "".equals(ppcin) || ppcin.length()!=8)
            {
                pcin.setStyle("-fx-text-box-border:red;");
                System.out.println("cin ghalet");
                test=false;
            }else{
                pcin.setStyle("-fx-text-box-border:transparent;");
            }
            
            String mdp = pmdp.getText();

            String mdpconfirm = pmdpc.getText();
            if(!mdp.equals(mdpconfirm))
            {
                pmdpc.setStyle("-fx-text-box-border:red;");
                pmdp.setStyle("-fx-text-box-border:red;");
                System.out.println("mdp ghalet ghalet");
                test=false;
            }else{
                pmdp.setStyle("-fx-text-box-border:transparent;");
                pmdpc.setStyle("-fx-text-box-border:transparent;");
            }
            
            String mail = pemail.getText();
            if(mail.matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$"))
            {
            int first =mail.indexOf("@");
            int second = mail.indexOf("@", first+1);
            
            if (second !=-1)
            {
                pemail.setStyle("-fx-text-box-border:red;");
            System.out.println("mail ghalet ghalet");
            test=false;
            }
            pemail.setStyle("-fx-text-box-border:transparent;");
            }
            else
            {
                pemail.setStyle("-fx-text-box-border:red;");
            System.out.println("mail ghalet ghalet");
            test=false;
            }
            
            if(test==true)
            {
                Admin c=new Admin(Integer.valueOf(ppcin), us.currentuser.getId(), Pnom.getText(), pprenom.getText(), pemail.getText(), pmdp.getText(), padresse.getText(), ptel.getText(), us.currentuser.getType());
                cs.modifierAdmin(c);
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("../../Home/gui/bar.fxml"));
                Parent root = loader.load();
                Pnom.getScene().setRoot(root);
                us.currentuser=c;
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }  
    
}
