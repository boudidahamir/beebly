package beeblyEchange.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import beebly.entity.PropositionLivre;
import beebly.services.PropositionLivreServiceInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AfficherListePropositionController implements Initializable {


    @FXML
    private TextField recherche;

    @FXML
    private TableColumn<PropositionLivre, Date> date;

    @FXML
    private TableColumn<PropositionLivre, String> edition;

    @FXML
    private TableView<PropositionLivre> table;

    @FXML
    private TableColumn<PropositionLivre, String> titre;

    @FXML
    private TableColumn<PropositionLivre, String> nomClient;

    @FXML
    private RadioButton nontraite;

    @FXML
    private RadioButton tous;

    @FXML
    private RadioButton traite;

    @FXML
    private Pane detail;

    PropositionLivreServiceInterface s = new PropositionLivreServiceInterface();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }



    public void refresh(){
        ObservableList<PropositionLivre> propositions=FXCollections.observableArrayList() ;
        //check box 1 selectionné
        if(tous.isSelected())
            propositions = FXCollections.observableArrayList(
                    s.getall().stream()
                    .filter(t->t.getTitreLivre().contains(recherche.getText())||t.getEditon().contains(recherche.getText())
                            ||(t.getDescriptionEtat() != null && t.getDescriptionEtat().contains(recherche.getText()))
                            ||t.getClient().getNom().contains(recherche.getText()))
                    .collect(Collectors.toList()));
        //check box 2 selectionné
        else if(traite.isSelected())
            propositions = FXCollections.observableArrayList(
                    s.getTraite().stream()
                    .filter(t->t.getTitreLivre().contains(recherche.getText())||t.getEditon().contains(recherche.getText())
                            ||(t.getDescriptionEtat() != null && t.getDescriptionEtat().contains(recherche.getText()))||t.getClient().getNom().contains(recherche.getText()))
                    .collect(Collectors.toList()));
        //check box 3 selectionné
        else if(nontraite.isSelected())
            propositions = FXCollections.observableArrayList(
                    s.getNonTraite().stream()
                    .filter(t->t.getTitreLivre().contains(recherche.getText())||t.getEditon().contains(recherche.getText())
                            ||(t.getDescriptionEtat() != null && t.getDescriptionEtat().contains(recherche.getText()))||t.getClient().getNom().contains(recherche.getText()))
                    .collect(Collectors.toList()));


        titre.setCellValueFactory(new PropertyValueFactory<PropositionLivre, String>("titreLivre"));
        edition.setCellValueFactory(new PropertyValueFactory<PropositionLivre, String>("editon"));
        date.setCellValueFactory(new PropertyValueFactory<PropositionLivre, Date>("dateProposition"));
        nomClient.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClient().getNom()));
        System.out.println(propositions);
        table.setItems(propositions);
        detail.getChildren().clear();
    }

    public void afficher(MouseEvent e) {



        Pane newLoadedPane = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/afficherDetailsProposition.fxml"));
            newLoadedPane = loader.load();
            afficherDetailsPropositionController c = loader.getController();
            c.pass(table.getSelectionModel().getSelectedItem());

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);
    }


}


