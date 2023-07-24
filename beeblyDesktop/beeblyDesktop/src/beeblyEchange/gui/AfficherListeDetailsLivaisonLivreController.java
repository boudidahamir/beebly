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
import beebly.entity.DétailsLivraison;
import beebly.services.DétailsLivraisonServiceInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AfficherListeDetailsLivaisonLivreController implements Initializable {
    DétailsLivraisonServiceInterface détailsLivraisonService = new DétailsLivraisonServiceInterface();
    @FXML
    private TableColumn<DétailsLivraison, Date> date;

    @FXML
    private Pane detail;

    @FXML
    private TableColumn<DétailsLivraison, String> edition;

    @FXML
    private TableColumn<DétailsLivraison, String> nomClient;

    @FXML
    private TableColumn<DétailsLivraison, Integer> point;

    @FXML
    private TableView<DétailsLivraison> table;

    @FXML
    private TableColumn<DétailsLivraison,String> titre;

    @FXML
    private TableColumn<DétailsLivraison,String> etatLivraison;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }


    public void refresh(){
        ObservableList<DétailsLivraison> détailsLivraisonObservableList =
                FXCollections.observableArrayList(détailsLivraisonService.getall());

        titre.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEstimationOffreLivre().getProposition().getTitreLivre()));

        edition.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEstimationOffreLivre().getProposition().getEditon()));

        date.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Date>(cellData.getValue().getEstimationOffreLivre().getProposition().getDateProposition()));

        nomClient.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEstimationOffreLivre().getProposition().getClient().getNom()));

        point.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(Integer.valueOf(cellData.getValue().getEstimationOffreLivre().getPointEstime())));

        etatLivraison.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEtatLivrasion().toString()));



        table.setItems(détailsLivraisonObservableList);


    }
    @FXML
    public void afficher(MouseEvent e) {

        Pane newLoadedPane = null;
        try {

            System.out.println(getClass().getResource("/beeblyEchange/gui/AfficherDétailsLivraisons.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/AfficherDétailsLivraisons.fxml"));
            newLoadedPane = loader.load();
            AfficherDétailsLivraisonController c = loader.getController();
            c.pass(table.getSelectionModel().getSelectedItem());

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);
    }

}
