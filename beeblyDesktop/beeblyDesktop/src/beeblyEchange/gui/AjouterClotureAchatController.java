package beeblyEchange.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import beebly.entity.ClotureAchat;
import beebly.entity.DétailsLivraison;
import beebly.entity.enums.EtatCloture;
import beebly.services.ClotureAchatServiceInterface;

import java.io.IOException;
import java.util.regex.Pattern;

public class AjouterClotureAchatController {

    @FXML
    private Label date;

    @FXML
    private TextArea descriptionEtat;

    @FXML
    private Label edition;

    @FXML
    private ComboBox<EtatCloture> etatCloture;

    @FXML
    private TextField inputPointFinale;

    @FXML
    private Label nomClient;

    @FXML
    private Label erreur;


    @FXML
    private Label pointErreur;
    @FXML
    private Label descriptionErreur;

    @FXML
    private Pane pane;

    @FXML
    private Label point;

    @FXML
    private Label titre;



    DétailsLivraison détailsLivraison;
    ClotureAchatServiceInterface clotureAchatService =new ClotureAchatServiceInterface();


    public void pass(DétailsLivraison détailsLivraison) {
        System.out.println(détailsLivraison);

        etatCloture.getItems().addAll(EtatCloture.Acceptée,EtatCloture.Rejeté);

        this.détailsLivraison = détailsLivraison;
        date.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getDateProposition().toString());
        edition.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getEditon());
        titre.setText(détailsLivraison.getEstimationOffreLivre().getProposition().getTitreLivre());
        point.setText(Integer.toString(détailsLivraison.getEstimationOffreLivre().getPointEstime()));

    }

    public void supprimerProposition(ActionEvent e) {

        String regex = "^[1-9][0-9]*$";
        Pattern pattern = Pattern.compile(regex);

        String regex2 = "^[a-zA-Z0-9\\s\\-\\.\\,\\!\\?\\(\\)\\'\\\"]+$";
        Pattern pattern2 = Pattern.compile(regex2);

        System.out.println(pattern.matcher(inputPointFinale.getText()).matches());


        if (pattern.matcher(inputPointFinale.getText()).matches() &&
                pattern2.matcher(descriptionEtat.getText()).matches()){

            ClotureAchat clotureAchat=new ClotureAchat(détailsLivraison,
                    Integer.valueOf(inputPointFinale.getText()),etatCloture.getValue(),descriptionEtat.getText());

            System.out.println(clotureAchat);

            clotureAchatService.add(clotureAchat);
            Pane newLoadedPane = null;
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/ajoutAvecSucces.fxml"));
                newLoadedPane = loader.load();


            } catch (IOException e1) {
                // TODO Auto-generated catc1h block
                e1.printStackTrace();
            }
            pane.getChildren().clear();
            pane.getChildren().add(newLoadedPane);
        } else {  if (!(pattern.matcher(inputPointFinale.getText()).matches()))
            pointErreur.setText("point invalide !!");
            else
                descriptionErreur.setText("description invalide");

        }
        }


    public void supprimerAlert(MouseEvent e) {
        pointErreur.setText("");
        descriptionErreur.setText("");

    }


}
