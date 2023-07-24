package beeblyEchange.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import beebly.entity.DétailsLivraison;
import beebly.entity.enums.EtatLivrasion;
import beebly.services.DétailsLivraisonServiceInterface;

import java.io.IOException;

public class ModifierDetailsLivraisonController {

    DétailsLivraisonServiceInterface détailsLivraisonService =new DétailsLivraisonServiceInterface();

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
    private ChoiceBox<EtatLivrasion> etatLivraison;


    DétailsLivraison détailsLivraison;

    public void pass(DétailsLivraison détailsLivraison) {
        System.out.println(détailsLivraison);

        etatLivraison.getItems().addAll(EtatLivrasion.Livré,EtatLivrasion.EnAttente,
                EtatLivrasion.EnRoute,EtatLivrasion.NonDisponible);

        etatLivraison.setValue(détailsLivraison.getEtatLivrasion());
        this.détailsLivraison = détailsLivraison;
        date.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getDateProposition().toString());
        description.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getDescriptionEtat());
        edition.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getEditon());
        titre.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getTitreLivre());
        point.setText(Integer.toString(détailsLivraison.getEstimationOffreLivre().getPointEstime()));
        adresse.setText(détailsLivraison.getAdresseLivraison());

    }




    public void modifier(ActionEvent e){
        détailsLivraison.setEtatLivrasion(etatLivraison.getValue());
        détailsLivraisonService.modify(détailsLivraison);


        Pane newLoadedPane = null;
        try {

            System.out.println(getClass().getResource("/beeblyEchange/gui/AfficherDétailsLivraisons.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/AfficherDétailsLivraisons.fxml"));
            newLoadedPane = loader.load();
            AfficherDétailsLivraisonController c = loader.getController();
            c.pass(détailsLivraison);

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);




    }



}
