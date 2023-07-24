/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.GUI;

import beebly.entity.Reclamation;
import beebly.services.ServiceImageIMP;
import beebly.services.ServiceReclamationIMP;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private Label date_label;
    @FXML
    private TextField sujet_field;
    @FXML
    private TextArea description_field;
    @FXML
    private TextField image_field;
    @FXML
    private Button modify_button;
    @FXML
    private Button browse_button;
    @FXML
    private ChoiceBox<String> type_choice_field;
    
    private Reclamation reclamation; 
    private ReclamationsUserController reclamations_user_controller;
    private boolean sujet,description,type=false; 
    private BooleanProperty form_valid= new SimpleBooleanProperty(true);
    FileChooser fileChooser = new FileChooser();
    File selectedFile=null;
    String check_image="";
    java.util.Date date = new java.util.Date();
    java.sql.Date todaysDate = new Date(date.getTime()); 
    ObservableList<String> typeList = FXCollections.observableArrayList("livre", "livraison", "autre");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        description_field.setWrapText(true);
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
        
        modify_button.disableProperty().bind(form_valid);
    }    

     @FXML
    private void modify_reclamation(ActionEvent event) {
        Reclamation new_reclamation=new Reclamation(); 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modification");
        alert.setHeaderText("Modification d'une reclamation");
        alert.setContentText("Vous voulez vraiment modifier les données de la reclamation ? ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (selectedFile != null) {
                try {
                    // Convert the image file to a byte array
                    InputStream inputStream = new FileInputStream(selectedFile);
                    byte[] imageBytes = new byte[inputStream.available()];
                    inputStream.read(imageBytes);
                    inputStream.close();
                    new_reclamation.setPhoto(imageBytes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                if (check_image=="reclamation_image"){  
                    System.out.println("reclmation_image");
                    new_reclamation.setPhoto(reclamation.getPhoto());
                }else{ 
                    new_reclamation.setPhoto(new byte[0]);
                }
            }
            new_reclamation.setId(reclamation.getId());
            new_reclamation.setType(type_choice_field.getValue()); 
            new_reclamation.setSujet(sujet_field.getText());
            new_reclamation.setDescription(description_field.getText());
            ServiceReclamationIMP service_reclamation=new ServiceReclamationIMP();
            service_reclamation.update_reclamation(new_reclamation);
            reclamations_user_controller.get_reclamations(); 
            reclamations_user_controller.loadData();
            Notifications.create()
                    .title("Réclamation modifiée")
                    .text("Votre réclamation a été modfiée avec succés merci.")
                    .showInformation();
            Stage stage = (Stage) modify_button.getScene().getWindow();
            stage.close();
        }
        
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
            check_image="";
        }
       
    }

    
    private boolean check_form(){ 
        if (sujet==true && description==true && type==true)
            return true;
        return false;
     }
    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }
     public void initialize_modify_Reclamation_form() {
        date_label.setText(reclamation.getDate().toString());
        sujet_field.setText(reclamation.getSujet()); 
        description_field.setText(reclamation.getDescription());
        type_choice_field.setValue(reclamation.getType());
        if(new ServiceImageIMP().isImage(reclamation.getPhoto()) ){
              image_field.setText("photo");
              check_image="reclamation_image"; 
        }else{
            image_field.setText(null);
            check_image="";
        }
    }
    public void setReclamations_user_controller(ReclamationsUserController reclamations_user_controller) {
        this.reclamations_user_controller = reclamations_user_controller;
    }

   
    
    
    
    
}
