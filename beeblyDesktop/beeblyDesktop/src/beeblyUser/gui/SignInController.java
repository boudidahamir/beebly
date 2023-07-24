/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeblyUser.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import beebly.entity.Client;
import beebly.services.ClientServices; 
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FXML Controller class
 *
 * @author amirb
 */
public class SignInController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtadresse;
    @FXML
    private TextField txtadressemail;
    @FXML
    private TextField txtmdp;
    @FXML
    private TextField txtmdpconf;
    @FXML
    private TextField txttel;
    
    @FXML
    private Button signin;
    @FXML
    private Button cancel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private void addUser(ActionEvent event) {
        
        try {
            boolean test=true;
            ClientServices ps = new ClientServices();
            
            String nom = txtnom.getText();
            Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(nom);
            boolean b = m.find();
            if(b || nom==null)
            {
                txtnom.setStyle("-fx-text-box-border:red;");
                System.out.println("nom ghalet");
                test=false;
            }else{
                txtnom.setStyle("-fx-text-box-border:transparent;");
            }
            
            String prenom = txtprenom.getText();
            m = p.matcher(prenom);
            b = m.find();
            if(b || prenom==null)
            {
                txtprenom.setStyle("-fx-text-box-border:red;");
                System.out.println("prenom ghalet");
                test=false;
            }else{
                txtprenom.setStyle("-fx-text-box-border:transparent;");
            }
           
            String adresse = txtadresse.getText();
            if(adresse==null)
            {
                txtadresse.setStyle("-fx-text-box-border:red;");
                test=false;
            }else{
                txtadresse.setStyle("-fx-text-box-border:transparent;");
            }

            String tel = txttel.getText();
            p = Pattern.compile("[^0-9]", Pattern.CASE_INSENSITIVE);
            m = p.matcher(tel);
            b = m.find();
            if(tel.length()!=8 || b)
            {
                txttel.setStyle("-fx-text-box-border:red;");
                System.out.println("tel ghalet");
                test=false;
            }else{
                txttel.setStyle("-fx-text-box-border:transparent;");
            }
            
            String mdp = txtmdp.getText();

            String mdpconfirm = txtmdpconf.getText();
            if(!mdp.equals(mdpconfirm))
            {
                txtmdpconf.setStyle("-fx-text-box-border:red;");
                txtmdp.setStyle("-fx-text-box-border:red;");
                System.out.println("mdp ghalet ghalet");
                test=false;
            }else{
                txtmdp.setStyle("-fx-text-box-border:transparent;");
                txtmdpconf.setStyle("-fx-text-box-border:transparent;");
            }
            
            String mail = txtadressemail.getText();
            if(mail.matches("^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$") && ps.checkmail(mail)==false)
            {
            int first =mail.indexOf("@");
            int second = mail.indexOf("@", first+1);
            
            if (second !=-1)
            {
                txtadressemail.setStyle("-fx-text-box-border:red;");
            System.out.println("mail ghalet ghalet");
            test=false;
            }
            txtadressemail.setStyle("-fx-text-box-border:transparent;");
            }
            else
            {
                txtadressemail.setStyle("-fx-text-box-border:red;");
            System.out.println("mail ghalet ghalet");
            test=false;
            }
            
            if(test==true)
            {
                Client c = new Client(0, nom, prenom, mail, mdp, adresse, tel, "client");
                ps.ajouter(c);
                FXMLLoader loader;
                loader = new FXMLLoader(getClass().getResource("/beeblyUser/gui/logIn.fxml"));
                Parent root = loader.load();
                txtnom.getScene().setRoot(root);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
                @FXML
    private void cancel(ActionEvent event) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/beeblyUser/gui/LogIn.fxml"));
            Parent root = loader.load(); 
            txtadresse.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
