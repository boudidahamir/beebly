/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeblyUser.gui;

import beebly.services.AdminServices;
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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author amirb
 */
public class ProfileadminController implements Initializable {

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
    private TextField pcin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AdminServices cs = new AdminServices();
        UserService us = new UserService();
        String cin = cs.getCin(us.currentuser.getId());
        Pnom.setText(us.currentuser.getNom());
        pprenom.setText(us.currentuser.getPrenom());
        pemail.setText(us.currentuser.getAdrmail());
        padresse.setText(us.currentuser.getAdresse());
        ptel.setText(us.currentuser.getTel());
        pcin.setText(cin);

    }

    @FXML
    private void goUpdateCurrentuser(ActionEvent event) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("../../beeblyUser/gui/updateprofileadmin.fxml"));
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
            loader = new FXMLLoader(getClass().getResource("../../beeblyUser/gui/LogIn.fxml"));
            Parent root = loader.load();
            pcin.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("../../Home/gui/bar.fxml"));
            Parent root = loader.load();
            Pnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteownacc(ActionEvent event) {
        AdminServices cs = new AdminServices();
        UserService us = new UserService();
        cs.supprimerAdmin(cs.finduser(us.currentuser.getId()));
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("../../beeblyUser/gui/LogIn.fxml"));
            Parent root = loader.load();
            Pnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
