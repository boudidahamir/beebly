/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.GUI;

import beebly.entity.Reclamation;
import beebly.entity.Reponse;
import beebly.services.ServiceImageIMP;
import beebly.services.ServiceReclamationIMP;
import beebly.services.ServiceReponseIMP;
import beebly.services.UserService;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ModifierReponseController implements Initializable {

    @FXML
    private TextArea contenu_field;
    @FXML
    private Button modify_button;
    @FXML
    private Label date_label;
    @FXML
    private TextArea contenu_new_field;
    
    
    private Reclamation reclamation;
    private Reponse reponse;
    private ReclamationsController reclamations_controller;
    private boolean contenu_new=false; 
    private BooleanProperty form_valid= new SimpleBooleanProperty(true);
    java.util.Date date = new java.util.Date();
    java.sql.Date todaysDate = new Date(date.getTime()); 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contenu_field.setWrapText(true);
        contenu_new_field.setWrapText(true);
        contenu_field.setDisable(true);
        contenu_new_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 10) {
                contenu_new_field.setStyle("-fx-border-color: red"); 
                contenu_new=false;
            } else {
                contenu_new_field.setStyle(null); // reset border color if length is greater than or equal to minLength
                contenu_new=true;
            }
            if (newValue.length() > 0 && newValue.charAt(0) == ' ') {
                contenu_new_field.setText(newValue.trim());
            }
            form_valid.set(!check_form());
        });
        
        modify_button.disableProperty().bind(form_valid);
    }    

    

    @FXML
    private void modify_response(ActionEvent event) {
        Reponse new_reponse =new Reponse();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification");
        alert.setHeaderText("Modification d'une réponse d'une reclamation ");
        alert.setContentText("Vous voulez vraiment modifier votre réponse à la réclamation ? ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            new_reponse.setId(reponse.getId());
            new_reponse.setDate(todaysDate); 
            new_reponse.setContenu(contenu_new_field.getText()); 
            ServiceReponseIMP service_reponse=new ServiceReponseIMP();
            service_reponse.update_reponse(new_reponse, reclamation, UserService.currentuser);
            reclamations_controller.get_reclamations(); 
            reclamations_controller.loadData();
            Notifications.create()
                    .title("Réponse modifiée")
                    .text("Votre réponse a été modfiée avec succés ")
                    .showInformation();
            Stage stage = (Stage) modify_button.getScene().getWindow();
            stage.close();
        }
        
    }
    private boolean check_form(){ 
        if (contenu_new==true && contenu_new_field.getText()!=contenu_field.getText())
            return true;
        return false;
     }
    public void initialize_modify_Response_form() {
        date_label.setText(todaysDate.toString());
        contenu_field.setText(reponse.getContenu());
        
    }
     public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }
     
    public void setReclamations_controller(ReclamationsController reclamations_controller) {
        this.reclamations_controller = reclamations_controller;
    }
    
}
