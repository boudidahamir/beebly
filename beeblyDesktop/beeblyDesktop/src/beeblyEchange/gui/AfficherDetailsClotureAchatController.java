package beeblyEchange.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import beebly.entity.ClotureAchat;
import beebly.entity.enums.EtatLivrasion;
import beebly.services.ClotureAchatServiceInterface;

import java.io.IOException;

public class AfficherDetailsClotureAchatController {
    @FXML
    private Label date;

    @FXML
    private Label edition;

    @FXML
    private Label etatCloture;

    @FXML
    private Label etatLivre;

    @FXML
    private Label nomClient;

    @FXML
    private Pane pane;

    @FXML
    private Label point;

    @FXML
    private Label titre;


    @FXML
    private Button cloture;


    ClotureAchat clotureAchat;

    public void pass(ClotureAchat clotureAchat) {
        System.out.println(clotureAchat);
        if(!(clotureAchat.getDetailsLivraison().getEtatLivrasion()== EtatLivrasion.Livré ||
                clotureAchat.getDetailsLivraison().getEtatLivrasion()== EtatLivrasion.NonDisponible))
            cloture.setVisible(false);



        this.clotureAchat = clotureAchat;
        etatCloture.setText(clotureAchat.getEtatCloture().toString());
        date.setText(clotureAchat.getDetailsLivraison().getEstimationOffreLivre().getProposition().getDateProposition().toString());
        etatLivre.setText(clotureAchat.getEtatLivre());
        edition.setText(clotureAchat.getDetailsLivraison().getEstimationOffreLivre().getProposition().getEditon());
        titre.setText(clotureAchat.getDetailsLivraison().getEstimationOffreLivre().getProposition().getTitreLivre());
        point.setText(Integer.toString(clotureAchat.getDetailsLivraison().getEstimationOffreLivre().getPointEstime()));
        nomClient.setText(clotureAchat.getDetailsLivraison().getEstimationOffreLivre().getProposition().getClient().getNom());


    }

    public void supprimerProposition(ActionEvent e) {
        Pane newLoadedPane =null;
        ClotureAchatServiceInterface clotureAchatService=new ClotureAchatServiceInterface();
        clotureAchatService.delete(clotureAchat.getIdCloture());

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
