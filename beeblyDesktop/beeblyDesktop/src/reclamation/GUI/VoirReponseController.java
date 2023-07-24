/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.GUI;

import beebly.entity.Reclamation;
import beebly.entity.Reponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class VoirReponseController implements Initializable {

    @FXML
    private TextArea contenu_field;
    @FXML
    private Button modify_button;
    @FXML
    private Label date_label;
    
    private Reclamation reclamation;
    private Reponse reponse;
    private ReclamationsController reclamations_controller;
    private ReclamationsUserController reclamationsUser_controller;
    private boolean contenu_new=false; 
    private BooleanProperty form_valid= new SimpleBooleanProperty(true);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contenu_field.setWrapText(true);
        contenu_field.setDisable(true);
        
    }    

    

   
    public void initialize_modify_Response_form() {
        date_label.setText(reponse.getDate().toString());
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

    public void setReclamationsUser_controller(ReclamationsUserController reclamationsUser_controller) {
        this.reclamationsUser_controller = reclamationsUser_controller;
    }
    
}
