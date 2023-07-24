package Home.gui;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class BarControlleur {

    @FXML
    private Pane mainPane;

    @FXML
    void statistiqueTrade(ActionEvent event) {
        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/Courbe.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(newLoadedPane);

    }

    @FXML
    void gestionBlog(ActionEvent event) {

        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/blog/GUI/gestiontopicback.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void gestionCloture(ActionEvent event) {
        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/AfficherListeClotureAchat.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(newLoadedPane);

    }

    @FXML
    void gestionDétails(ActionEvent event) {
        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/AfficherListeDetailsLivaisonLivre.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(newLoadedPane);

    }

    @FXML
    void gestionEstimation(ActionEvent event) {
        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/AfficherListeEstimationOffreLivre.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(newLoadedPane);

    }

    @FXML
    void gestionEvenement(ActionEvent event) {
        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/HomePage.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(newLoadedPane);

    }

    @FXML
    void gestionLivres(ActionEvent event) {

        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beebly/gui/AjouterLivre.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(newLoadedPane);

    }
    @FXML
    private Button button;

    @FXML
    void gestionProposition(ActionEvent event) {
        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/afficherListeProposition.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void gestionReclamation(ActionEvent event) {

        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamation/GUI/ReclamationsUser.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(newLoadedPane);

    }

    @FXML
    void gestionUtilisateur(ActionEvent event) {

        Pane newLoadedPane =null;
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyUser/gui/afficheruserback.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        mainPane.getChildren().clear();
        mainPane.getChildren().add(newLoadedPane);

    }
}

