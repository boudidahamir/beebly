package Home.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GlobalControlleur {

        @FXML
    private Button button;
                @FXML
    private Button button2;
                        @FXML
    private Button button3;
    
    @FXML
    void gestionBlog(ActionEvent event) {

        Pane pane= (Pane) button.getScene().getRoot();
        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/blog/GUI/gestiontopic.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }

    @FXML
    void gestionReclamation(ActionEvent event) {
        Pane pane= (Pane) button.getScene().getRoot();
        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamation/GUI/Reclamations.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);

    }

    @FXML
    void gestionUtilisateur(ActionEvent event) {
        Pane pane= (Pane) button.getScene().getRoot();
        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyUser/gui/Profile.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);

    }
}

