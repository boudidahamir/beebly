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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author amirb
 */
public class AfficheruserbackController implements Initializable {

    @FXML
    TableColumn id;
    @FXML
    TableColumn nom;
    @FXML
    TableColumn prenom;
    @FXML
    TableColumn email;
    @FXML
    TableColumn mdp;
    @FXML
    TableColumn adresse;
    @FXML
    TableColumn tel;
    @FXML
    TableColumn type;
    @FXML
    TableColumn cin;
    @FXML
    private TableView<Admin> tabuser;
    @FXML
    TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AdminServices ad = new AdminServices();
        ObservableList<Admin> row = FXCollections.observableArrayList();
        List<Admin> Admins = new ArrayList<>();
        Admins = ad.getAll();
        for (Admin c : Admins) {
            if (c.getId() != UserService.currentuser.getId()) {
                row.add(c);
            }

        }
        id.setCellValueFactory(new PropertyValueFactory<Admin, String>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Admin, Double>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Admin, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<Admin, Double>("adrmail"));
        mdp.setCellValueFactory(new PropertyValueFactory<Admin, String>("mdp"));
        adresse.setCellValueFactory(new PropertyValueFactory<Admin, Double>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<Admin, String>("tel"));
        type.setCellValueFactory(new PropertyValueFactory<Admin, Double>("type"));
        cin.setCellValueFactory(new PropertyValueFactory<Admin, String>("cin"));
        tabuser.setItems(row);
    }

    @FXML
    private void supprimeruser(ActionEvent event) {
        Admin ad = tabuser.getSelectionModel().getSelectedItem();
        AdminServices ads = new AdminServices();
        ads.supprimerAdmin(ad);
        ObservableList<Admin> row = FXCollections.observableArrayList();
        List<Admin> Admins = new ArrayList<>();
        Admins = ads.getAll();
        for (Admin c : Admins) {
            if (c.getId() != UserService.currentuser.getId()) {
                row.add(c);
            }

        }
        tabuser.setItems(row);

    }

    @FXML
    private void goToProfile(ActionEvent event) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("../../beeblyUser/gui/profileadmin.fxml"));
            Parent root = loader.load();
            tabuser.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouteradmin(ActionEvent event) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("../../beeblyUser/gui/ajouteradmin.fxml"));
            Parent root = loader.load();
            tabuser.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void generateexcel(ActionEvent event) {
        UserService us = new UserService();
        us.generateExcel();
    }

    @FXML
    private void recherche(KeyEvent event) {
        AdminServices ad = new AdminServices();
        ObservableList<Admin> row = FXCollections.observableArrayList();
        List<Admin> Admins = new ArrayList<>();
        Admins = ad.search(recherche.getText());
        for (Admin c : Admins) {
            if (c.getId() != UserService.currentuser.getId()) {
                row.add(c);
            }

        }
        id.setCellValueFactory(new PropertyValueFactory<Admin, String>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Admin, Double>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Admin, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<Admin, Double>("adrmail"));
        mdp.setCellValueFactory(new PropertyValueFactory<Admin, String>("mdp"));
        adresse.setCellValueFactory(new PropertyValueFactory<Admin, Double>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<Admin, String>("tel"));
        type.setCellValueFactory(new PropertyValueFactory<Admin, Double>("type"));
        cin.setCellValueFactory(new PropertyValueFactory<Admin, String>("cin"));
        tabuser.setItems(row);
    }

}
