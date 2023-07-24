/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.GUI;

import beebly.entity.Reclamation;
import beebly.services.ServiceReclamationIMP;
import beebly.services.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TextField sujet_field;
    @FXML
    private TextArea description_field;
    @FXML
    private TextField image_field;
    @FXML
    private Button add_button;
    @FXML
    private Button browse_button;
    @FXML
    private ChoiceBox<String> type_choice_field;
    
    private ReclamationsUserController reclamations_user_controller;
    private boolean sujet,description,type=false; 
    private BooleanProperty form_valid= new SimpleBooleanProperty(true);
    FileChooser fileChooser = new FileChooser();
    File selectedFile=null;
    java.util.Date date = new java.util.Date();
    java.sql.Date todaysDate = new Date(date.getTime()); 
    
    
    ObservableList<String> typeList = FXCollections.observableArrayList("Aucun","livre", "livraison", "autre");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        description_field.setWrapText(true);

        type_choice_field.setValue("Aucun");
        type_choice_field.setItems(typeList);
        image_field.setDisable(true);       
        sujet_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 5) {
                sujet_field.setStyle("-fx-border-color: red");
                sujet=false; 
            } else {
                sujet_field.setStyle(null); // reset border color if length is greater than or equal to minLength
                sujet=true;
            }
            if (newValue.length() > 0 && newValue.charAt(0) == ' ') {
                sujet_field.setText(newValue.trim());
            }
            if (newValue.matches(".*\\d+.*")) {
                sujet_field.setText(newValue.replaceAll("\\d", ""));
            }

            form_valid.set(!check_form());
        });
        description_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 10) {
                description_field.setStyle("-fx-border-color: red"); 
                description=false;
            } else {
                description_field.setStyle(null); // reset border color if length is greater than or equal to minLength
                description=true;
            }
            if (newValue.length() > 0 && newValue.charAt(0) == ' ') {
                description_field.setText(newValue.trim());
            }
            form_valid.set(!check_form());
        });
        type_choice_field.setOnAction((event) -> {
           if(type_choice_field.getValue()=="Aucun"){
                type=false;
           }else{
                type=true;
           }
           form_valid.set(!check_form());
        });
        
        add_button.disableProperty().bind(form_valid);
        
    } 

    @FXML
    private void add_reclamation(ActionEvent event) { 
        Reclamation reclamation=new Reclamation();
        if (selectedFile != null) {
            try {
                // Convert the image file to a byte array
                InputStream inputStream = new FileInputStream(selectedFile);
                byte[] imageBytes = new byte[inputStream.available()];
                inputStream.read(imageBytes);
                inputStream.close();
                reclamation.setPhoto(imageBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else reclamation.setPhoto(new byte[0]);
        reclamation.setType(type_choice_field.getValue()); 
        reclamation.setDate(todaysDate);
        reclamation.setSujet(sujet_field.getText());
        reclamation.setDescription(description_field.getText());
                
        reclamation.setStatus("en cours");
        reclamation.setUser(UserService.currentuser); 
        ServiceReclamationIMP service_reclamation=new ServiceReclamationIMP(); 
        service_reclamation.ajouter_reclamtion(reclamation);
        
        
                
       /* Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Réclamation envoyée");
        alert.setHeaderText(null);
        alert.setContentText("Votre réclamation a été recu avec succés merci.");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                sujet_field.clear();
                description_field.clear();
                type_choice_field.setValue("Aucun");
                image_field.clear();
                selectedFile=null;
                alert.close();
                       
            }
        });*/
        reclamations_user_controller.get_reclamations(); 
        reclamations_user_controller.loadData();
        Notifications.create()
                    .title("Réclamation envoyée")
                    .text("Votre réclamation a été recu avec succés merci.")
                    .showInformation();
        sujet_field.clear();
        description_field.clear();
        type_choice_field.setValue("Aucun");
        image_field.clear();
        selectedFile=null;
        
        
    }
    
    @FXML
    private void browse_images(ActionEvent event) { 
        fileChooser.setTitle("Select File");
        
        // Set initial directory (optional)
        File initialDirectory = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(initialDirectory);

        // Add file filters (optional)
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        Stage stage = (Stage) browse_button.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
                image_field.setText(selectedFile.getAbsolutePath());
        }
        else{ 
            image_field.setText(null);
            
        }
       
    }
   
 
    private boolean check_form(){ 
        if (sujet==true && description==true && type==true)
            return true;
        return false;
     }

    public void setReclamations_user_controller(ReclamationsUserController reclamations_user_controller) {
        this.reclamations_user_controller = reclamations_user_controller;
    }
    
    
}
