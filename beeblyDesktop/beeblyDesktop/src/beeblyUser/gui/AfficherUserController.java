/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beeblyUser.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import beebly.entity.Client;
import beebly.services.ClientServices;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author amirb
 */
public class AfficherUserController implements Initializable {

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
    TableColumn soldepoint;
    @FXML
    private TableView<Client> tabuser;
    @FXML
    TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClientServices cs = new ClientServices();
        ObservableList<Client> row = FXCollections.observableArrayList();
        List<Client> Clients = new ArrayList<>();
        Clients = cs.getAll();
        for (Client c : Clients) {
            row.add(c);
        }
        id.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Client, Double>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<Client, Double>("adrmail"));
        mdp.setCellValueFactory(new PropertyValueFactory<Client, String>("mdp"));
        adresse.setCellValueFactory(new PropertyValueFactory<Client, Double>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<Client, String>("tel"));
        type.setCellValueFactory(new PropertyValueFactory<Client, Double>("type"));
        soldepoint.setCellValueFactory(new PropertyValueFactory<Client, String>("soldepoint"));
        tabuser.setItems(row);
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("../../beeblyUser/gui/Profile.fxml"));
            Parent root = loader.load();
            tabuser.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void recherche(KeyEvent event) {
        ClientServices cs = new ClientServices();
        ObservableList<Client> row = FXCollections.observableArrayList();
        List<Client> Clients = new ArrayList<>();
        Clients = cs.search(recherche.getText());
        for (Client c : Clients) {
            row.add(c);
        }
        id.setCellValueFactory(new PropertyValueFactory<Client, String>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Client, Double>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<Client, Double>("adrmail"));
        mdp.setCellValueFactory(new PropertyValueFactory<Client, String>("mdp"));
        adresse.setCellValueFactory(new PropertyValueFactory<Client, Double>("adresse"));
        tel.setCellValueFactory(new PropertyValueFactory<Client, String>("tel"));
        type.setCellValueFactory(new PropertyValueFactory<Client, Double>("type"));
        soldepoint.setCellValueFactory(new PropertyValueFactory<Client, String>("soldepoint"));
        tabuser.setItems(row);
    }

}
