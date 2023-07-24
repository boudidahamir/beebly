package beeblyEchange.gui;

import beeblyEchange.gui.ajouterUneEstimationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import beebly.entity.PropositionLivre;
import beebly.services.PropositionLivreServiceInterface;

import java.io.IOException;

public class afficherDetailsPropositionController {
    @FXML
    private Label date;

    @FXML
    private Label description;

    @FXML
    private Label edition;

    @FXML
    private Label titre;

    @FXML
    private Pane pane;

    @FXML
    private Button ajouter;

    PropositionLivre propositionLivre;
    PropositionLivreServiceInterface propositionLivreService =new PropositionLivreServiceInterface();

    public void pass(PropositionLivre propositionLivre) {

        if(
        propositionLivreService.getTraite().stream()
                .filter(t->t.getIdProposition()==propositionLivre.getIdProposition())
                .count() >0)
            ajouter.setVisible(false);




        this.propositionLivre = propositionLivre;
        date.setText(propositionLivre.getDateProposition().toString());
        description.setText(propositionLivre.getDescriptionEtat());
        edition.setText(propositionLivre.getEditon());
        titre.setText(propositionLivre.getTitreLivre());
    }

    public void supprimerProposition(ActionEvent e) {
        Pane newLoadedPane = null;
        PropositionLivreServiceInterface propositionLivreService=new PropositionLivreServiceInterface();
        propositionLivreService.delete(propositionLivre.getIdProposition());
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../beeblyEchange/gui/supprimerAvecSucces.fxml"));
            newLoadedPane = loader.load();


        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }

    public void ajouterEstimation(ActionEvent e) {
        Pane newLoadedPane = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/ajouterUneEstimation.fxml"));
            newLoadedPane = loader.load();
            ajouterUneEstimationController c = loader.getController();
            c.pass(propositionLivre);

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);


    }


}
