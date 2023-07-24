/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package beeblyEvent.gui;

import beebly.entity.Evenement;
import beebly.entity.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import beebly.services.ServiceTicketIMP;
import beebly.tools.MyDB;

/**
 * FXML Controller class
 *
 * @author khaled
 */
public class AjouterTicketController implements Initializable {

    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_prix;
    @FXML
    private Button add_button;

    /**
     * Initializes the controller class.
     */
    ServiceTicketIMP st = new ServiceTicketIMP();

    private BooleanProperty form_valid = new SimpleBooleanProperty(true);
    private boolean type, prix = false;
    @FXML
    private ComboBox<Integer> names;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        loadNames();

        tf_type.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 2) {
                tf_type.setStyle("-fx-border-color: red");
                type = false;
            } else if (newValue.contains("vip") || newValue.contains("standard")) {
                tf_type.setStyle(null); // reset border color if length is greater than or equal to minLength
                type = true;
                tf_type.setStyle("-fx-border-color: green");

            }
            form_valid.set(!check_form());
        });

        tf_prix.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                tf_prix.setStyle("-fx-border-color: green");

                if (!newValue.matches("\\d*")) {
                    tf_prix.setText(newValue.replaceAll("[^\\d]", ""));
                    tf_prix.setStyle("-fx-border-color: red");

                }
            }
        });

    }

    @FXML
    private void ajouter_ticket(ActionEvent event) {
        Ticket ticket = new Ticket();

        ticket.setPrix(Integer.parseInt(tf_prix.getText()));
        ticket.setType(tf_type.getText());
        ticket.setId_evenement(names.getSelectionModel().getSelectedItem());

        st.ajoutTicket(ticket);
        goToAllList();

    }

    private void goToAllList() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/ticket.fxml"));
            Parent root = loader.load();
            tf_prix.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean check_form() {
        if (type == true && prix == true) {
            return true;
        }
        return false;
    }

    public void loadNames() {

        try {
            Connection cnx;

            cnx = MyDB.getInstance().getConnection();

            ResultSet rs = cnx.createStatement().executeQuery("select id from evenement");
            ObservableList data = FXCollections.observableArrayList();
            while (rs.next()) {
                data.add(rs.getInt("id"));
            }

            names.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(AjouterTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void retoure_action(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/HomePage.fxml"));
            Parent root = loader.load();
            names.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
