/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.GUI;

import beebly.entity.Reclamation;
import beebly.entity.Reponse;
import beebly.services.ServiceReponseIMP;
import beebly.services.UserService;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AjouterReponseController implements Initializable {

    @FXML
    private TextArea contenu_field;
    @FXML
    private Button send_button;
    @FXML
    private Label date_label;
    
    private Reclamation reclamation; 
    private ReclamationsController reclamations_controller;
    private boolean contenu=false; 
    private BooleanProperty form_valid= new SimpleBooleanProperty(true);
    java.util.Date date = new java.util.Date();
    java.sql.Date todaysDate = new Date(date.getTime());
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contenu_field.setWrapText(true);
        date_label.setText(todaysDate.toString());
        contenu_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 10) {
                contenu_field.setStyle("-fx-border-color: red"); 
                contenu=false;
            } else {
                contenu_field.setStyle(null); // reset border color if length is greater than or equal to minLength
                contenu=true;
            }
            if (newValue.length() > 0 && newValue.charAt(0) == ' ') {
                contenu_field.setText(newValue.trim());
            }
            form_valid.set(!check_form());
        });
        send_button.disableProperty().bind(form_valid);
    }    
    
    @FXML
    private void send_response(ActionEvent event) {
        Reponse reponse =new Reponse();
        reponse.setDate(todaysDate);
        reponse.setContenu(contenu_field.getText());
        ServiceReponseIMP service_reponse=new ServiceReponseIMP(); 
        service_reponse.ajouter_reponse(reponse, reclamation, UserService.currentuser);
        reclamations_controller.get_reclamations(); 
        reclamations_controller.loadData();
        Notifications.create()
                    .title("Réponse envoyée")
                    .text("Votre réponse a été enovyé au client : "+reclamation.getUser().getNom() +" "+ reclamation.getUser().getPrenom()+ " avec  succés")
                    .showInformation();
        Stage stage = (Stage) send_button.getScene().getWindow();
        stage.close();
        
    }
    
    private boolean check_form(){ 
        if (contenu==true )
            return true;
        return false;
     }
    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }
    public void setReclamations_controller(ReclamationsController reclamations_controller) {
        this.reclamations_controller = reclamations_controller;
    }
}
