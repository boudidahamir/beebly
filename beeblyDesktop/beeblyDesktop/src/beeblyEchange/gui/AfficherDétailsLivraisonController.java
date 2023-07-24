package beeblyEchange.gui;

import beeblyEchange.gui.AjouterClotureAchatController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import beebly.entity.DétailsLivraison;

import java.io.IOException;

public class AfficherDétailsLivraisonController  {
    @FXML
    private Label date;

    @FXML
    private Label description;

    @FXML
    private Label edition;

    @FXML
    private Label adresse;

    @FXML
    private Pane pane;

    @FXML
    private Label point;

    @FXML
    private Label titre;

    @FXML
    private Label etat;


    DétailsLivraison détailsLivraison;


    public void pass(DétailsLivraison détailsLivraison) {
        System.out.println(détailsLivraison);

        this.détailsLivraison = détailsLivraison;
        date.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getDateProposition().toString());
        description.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getDescriptionEtat());
        edition.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getEditon());
        titre.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getTitreLivre());
        point.setText(Integer.toString(détailsLivraison.getEstimationOffreLivre().getPointEstime()));
        adresse.setText(détailsLivraison.getAdresseLivraison());
        etat.setText(détailsLivraison.getEtatLivrasion().toString());

    }

    public void supprimerProposition(ActionEvent e) {
        Pane newLoadedPane =null;
        //EstimationOffreLivreService estimationOffreLivreService =new EstimationOffreLivreService();
        // estimationOffreLivreService.delete(estimationOffreLivre.getIdEstimationOffreLivre());
        // propositionLivreService.delete(propositionLivre.getIdProposition());
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/supprimerAvecSucces.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }

    @FXML
    public void modifier(ActionEvent e) {

        Pane newLoadedPane = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/ModifierDetailsLivraison.fxml"));
            newLoadedPane = loader.load();
            ModifierDetailsLivraisonController c = loader.getController();
            c.pass(détailsLivraison);

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }

    @FXML
    public void cloturer(ActionEvent e) {

        Pane newLoadedPane = null;
        try {

            System.out.println(getClass().getResource("/beeblyEchange/gui/AjouterClotureAchat.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/AjouterClotureAchat.fxml"));
            newLoadedPane = loader.load();
            AjouterClotureAchatController c = loader.getController();
            c.pass(détailsLivraison);

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }
}
