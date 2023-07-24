/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeblyUser.gui;

import beebly.services.ClientServices;
import beebly.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amirb
 */
public class ProfileController implements Initializable {
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
private TextField ppoint;
        @FXML
    private Button home;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
          ClientServices cs=new ClientServices();
          UserService us=new UserService();
          String soldepoints = cs.getsoldepoint(us.currentuser.getId());
          Pnom.setText(us.currentuser.getNom());
          pprenom.setText(us.currentuser.getPrenom());
          pemail.setText(us.currentuser.getAdrmail());
          padresse.setText(us.currentuser.getAdresse());
          ptel.setText(us.currentuser.getTel());
          ppoint.setText(soldepoints);


    }    
    
        @FXML
    private void goUpdateCurrentuser(ActionEvent event) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/beeblyUser/gui/updateprofile.fxml"));
            Parent root = loader.load(); 
            Pnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
            @FXML
    private void afficheruser(ActionEvent event) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/beeblyUser/gui/afficherUser.fxml"));
            Parent root = loader.load(); 
            Pnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @FXML
    private void deleteownacc(ActionEvent event) {
        ClientServices cs = new ClientServices();
        UserService us = new UserService();
        cs.supprimerClient(cs.finduser(us.currentuser.getId()));
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/beeblyUser/gui/LogIn.fxml"));
            Parent root = loader.load();
            Pnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
                    @FXML
    private void deconnecter(ActionEvent event) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/beeblyUser/gui/LogIn.fxml"));
            Parent root = loader.load(); 
            Pnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
            @FXML
    private void goSatMenu(ActionEvent event) {
           try {
               
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home/gui/globale.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
    }
    

    
}
