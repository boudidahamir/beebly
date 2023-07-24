/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.GUI;

import beebly.entity.Reclamation;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class VoirPhotoController implements Initializable {

    @FXML
    private ImageView image_view; 
    Reclamation reclamation;
    Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void setImage_view() { 
        System.out.println("photo data"+reclamation.getPhoto());
        Image image = new Image(new ByteArrayInputStream(reclamation.getPhoto()));
        image_view.setPreserveRatio(false);
        image_view.setFitWidth(stage.getWidth());
        image_view.setFitHeight(stage.getHeight());
        this.image_view.setImage(image);
        stage.setResizable(false);
        
    }
    
    

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    } 

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
}
