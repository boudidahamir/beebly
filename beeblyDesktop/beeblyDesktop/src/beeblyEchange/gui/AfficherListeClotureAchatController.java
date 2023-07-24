package beeblyEchange.gui;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import beebly.entity.ClotureAchat;
import beebly.entity.enums.EtatCloture;
import beebly.services.ClotureAchatServiceInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherListeClotureAchatController implements Initializable {

    ClotureAchatServiceInterface clotureAchatService= new ClotureAchatServiceInterface();
    @FXML
    private TableColumn<ClotureAchat, String> etatLivre;

    @FXML
    private Pane detail;

    @FXML
    private TableColumn<ClotureAchat, String> edition;

    @FXML
    private TableColumn<ClotureAchat, EtatCloture> etatCloture;

    @FXML
    private TableColumn<ClotureAchat, Integer> point;

    @FXML
    private TableView<ClotureAchat> table;

    @FXML
    private TableColumn<ClotureAchat,String> titre;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<ClotureAchat> clotureAchatObservableList =
                FXCollections.observableArrayList(clotureAchatService.getall());

        titre.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDetailsLivraison().getEstimationOffreLivre()
                        .getProposition().getTitreLivre()));

        edition.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDetailsLivraison().getEstimationOffreLivre()
                        .getProposition().getEditon()));

        etatCloture.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getEtatCloture()));

        etatLivre.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEtatLivre()));

        point.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(Integer.valueOf(cellData.getValue().getPointAchatFinale())));




        table.setItems(clotureAchatObservableList);
    }
    @FXML
    public void afficher(MouseEvent e) {

        Pane newLoadedPane = null;
        try {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/AfficherDetailCloture.fxml"));
            newLoadedPane = loader.load();
            AfficherDetailsClotureAchatController c = loader.getController();
            c.pass(table.getSelectionModel().getSelectedItem());

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);
    }
}
