package beeblyEchange.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import beebly.entity.EstimationOffreLivre;
import beebly.services.EstimationOffreLivreServiceInterface;

import java.io.IOException;

public class AfficherDetailsEstimationController {


    @FXML
    private Label date;

    @FXML
    private Label description;

    @FXML
    private Label edition;

    @FXML
    private Label etat;

    @FXML
    private Pane pane;

    @FXML
    private Label point;

    @FXML
    private Label titre;


    EstimationOffreLivre estimationOffreLivre;

    public void pass(EstimationOffreLivre estimationOffreLivre) {
        System.out.println(estimationOffreLivre);


        this.estimationOffreLivre = estimationOffreLivre;
        date.setText(estimationOffreLivre.getProposition().getDateProposition().toString());
        description.setText(estimationOffreLivre.getProposition().getDescriptionEtat());
        edition.setText(estimationOffreLivre.getProposition().getEditon());
        titre.setText(estimationOffreLivre.getProposition().getTitreLivre());
        point.setText(Integer.toString(estimationOffreLivre.getPointEstime()));
        etat.setText(estimationOffreLivre.getEtat().toString());
    }

    public void supprimerProposition(ActionEvent e) {
        Pane newLoadedPane =null;
        EstimationOffreLivreServiceInterface estimationOffreLivreService =new EstimationOffreLivreServiceInterface();
         estimationOffreLivreService.delete(estimationOffreLivre.getIdEstimationOffreLivre());
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
}
