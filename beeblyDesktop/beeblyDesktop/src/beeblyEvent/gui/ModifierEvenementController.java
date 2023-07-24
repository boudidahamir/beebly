/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package beeblyEvent.gui;

import beebly.entity.Evenement;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import beebly.services.ServiceEvenementIMP;


/**
 * FXML Controller class
 *
 * @author khaled
 */
public class ModifierEvenementController implements Initializable {

    @FXML
    private Rectangle tf_libelle;
    @FXML
    private TextField tf_libel;
    @FXML
    private TextField image_field;
    @FXML
    private Button browse_button;
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_emplacement;
    @FXML
    private TextField tf_nb_place;
    @FXML
    private TextField tf_duree;
    @FXML
    private Button btn_add;
    @FXML
    private DatePicker tf_date;

    /**
     * Initializes the controller class.
     */
    ServiceEvenementIMP se = new ServiceEvenementIMP();

    // Utilisateur userConn = Utilisateur.user_connecter;
    // String mewImagePath = userConn.getImage();
    private BooleanProperty form_valid = new SimpleBooleanProperty(true);
    private boolean label, description, emplacement, nb_place, duree = false;

    java.util.Date dadte = new java.util.Date();
    java.sql.Date todaysDate = new Date(dadte.getTime());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // controle de saisie
        // labelle
        tf_libel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 5) {
                tf_libel.setStyle("-fx-border-color: red");
                label = false;
            } else {
                tf_libel.setStyle(null); // reset border color if length is greater than or equal to minLength
                label = true;
                tf_libel.setStyle("-fx-border-color: green");

            }
            form_valid.set(!check_form());
        });
        // description
        tf_description.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 5) {
                tf_description.setStyle("-fx-border-color: red");
                description = false;
            } else {
                tf_description.setStyle(null); // reset border color if length is greater than or equal to minLength
                description = true;
                tf_description.setStyle("-fx-border-color: green");

            }
            form_valid.set(!check_form());
        });
        // emplacement 
        tf_emplacement.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                tf_emplacement.setStyle("-fx-border-color: red");
                emplacement = false;
            } else {
                tf_emplacement.setStyle(null); // reset border color if length is greater than or equal to minLength
                emplacement = true;
                tf_emplacement.setStyle("-fx-border-color: green");

            }
            form_valid.set(!check_form());
        });

        // nb_place 
        tf_nb_place.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                tf_nb_place.setStyle("-fx-border-color: red");
                nb_place = false;
            } else {
                tf_emplacement.setStyle(null); // reset border color if length is greater than or equal to minLength
                nb_place = true;
            }
            form_valid.set(!check_form());
        });
        tf_nb_place.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                tf_nb_place.setStyle("-fx-border-color: green");

                if (!newValue.matches("\\d*")) {
                    tf_nb_place.setText(newValue.replaceAll("[^\\d]", ""));
                    tf_nb_place.setStyle("-fx-border-color: red");

                }

            }
        });
        // duree 
        tf_duree.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                tf_duree.setStyle("-fx-border-color: red");
                duree = false;
            } else {
                tf_duree.setStyle(null); // reset border color if length is greater than or equal to minLength
                duree = true;
            }
            form_valid.set(!check_form());
        });

        tf_duree.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                tf_duree.setStyle("-fx-border-color: green");

                if (!newValue.matches("\\d*")) {
                    tf_duree.setText(newValue.replaceAll("[^\\d]", ""));
                    tf_duree.setStyle("-fx-border-color: red");

                }
            }
        });
        // date 
    }

    @FXML
    private void browse_images(ActionEvent event) {
    }

    @FXML
    private void modifier_event(ActionEvent event) {
         // instanciation dun nouveau evenemnt 
        Evenement evenement = new Evenement();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification");
        alert.setHeaderText("Modification d'une reclamation");
        alert.setContentText("Vous voulez vraiment modifier les données de la reclamation ? ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            
            
         evenement.setLibelle(tf_libel.getText());
        evenement.setDate(tf_date.getValue().toString());
        evenement.setDescription(tf_description.getText());
        evenement.setEmplacement(tf_emplacement.getText());
        evenement.setNb_place(Integer.parseInt(tf_nb_place.getText()));
        evenement.setDuree(Integer.parseInt(tf_duree.getText()));
        evenement.setImage(image_field.getText());
        evenement.setId_user(1);
        
        se.modifierEvenement(evenement, evenement.getId());
        
        Notifications.create()
                    .title("Réclamation modifiée")
                    .text("Votre réclamation a été modfiée avec succés merci.")
                    .showInformation();
            Stage stage = (Stage) btn_add.getScene().getWindow();
            stage.close();
        
        }
        
        
        
        
    }

    private boolean check_form() {
        if (label == true && description == true && emplacement == true && nb_place == true && duree == true) {
            return true;
        }
        return false;
    }

}
