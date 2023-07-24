package beeblyEchange.gui;

import javafx.beans.property.SimpleObjectProperty;
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
import beebly.entity.EstimationOffreLivre;
import beebly.entity.enums.Etat;
import beebly.services.EstimationOffreLivreServiceInterface;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AfficherListeEstimationOffreLivreController implements Initializable {

    EstimationOffreLivreServiceInterface estimationOffreLivreService=new EstimationOffreLivreServiceInterface();
    @FXML
    private TableColumn<EstimationOffreLivre, Date> date;

    @FXML
    private Pane detail;

    @FXML
    private TableColumn<EstimationOffreLivre, String> edition;

    @FXML
    private TableColumn<EstimationOffreLivre, Etat> etat;

    @FXML
    private TableColumn<EstimationOffreLivre, Integer> point;

    @FXML
    private TableView<EstimationOffreLivre> table;

    @FXML
    private TableColumn<EstimationOffreLivre,String> titre;

    @FXML
    private RadioButton toute;

    @FXML
    private RadioButton refuse;

    @FXML
    private RadioButton accepte;

    @FXML
    private TextField recherche;


    @FXML
    private RadioButton enAttente;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();

    }



    List<EstimationOffreLivre> l;

    public void refresh(){

        ObservableList<EstimationOffreLivre> estimationOffreLivres=FXCollections.observableArrayList() ;

        //check box 1 selectionné
        if(accepte.isSelected())

            l=estimationOffreLivreService.getall().stream()
                    .filter(t->t.getEtat().equals(Etat.Acceptée))
                    .collect(Collectors.toList());


        //check box 2 selectionné
       else if(refuse.isSelected())

            l=estimationOffreLivreService.getall().stream()
                    .filter(t->t.getEtat().equals(Etat.Refusé))
                    .collect(Collectors.toList());

       //check box 3 selectionné
        else if(enAttente.isSelected())

            l=estimationOffreLivreService.getall().stream()
                    .filter(t->t.getEtat().equals(Etat.En_attente))
                    .collect(Collectors.toList());

        //check box 4 selectionné
        else if(toute.isSelected())
                    l=estimationOffreLivreService.getall();




        estimationOffreLivres = FXCollections.observableArrayList(
                        l.stream()
                        .filter(t->t.getProposition().getTitreLivre().contains(recherche.getText())||
                                        t.getProposition().getEditon().contains(recherche.getText())
                                        ||(t.getProposition().getDescriptionEtat() != null &&
                                        t.getProposition().getDescriptionEtat().contains(recherche.getText()))
                                        ||Integer.toString(t.getPointEstime()).contains(recherche.getText()))
                        .collect(Collectors.toList()));



        titre.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getProposition().getTitreLivre()));

        edition.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getProposition().getEditon()));

        date.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Date>(cellData.getValue().getProposition().getDateProposition()));

        etat.setCellValueFactory(new PropertyValueFactory<EstimationOffreLivre, Etat>("etat"));
        point.setCellValueFactory(new PropertyValueFactory<EstimationOffreLivre, Integer>("pointEstime"));


        table.setItems(estimationOffreLivres);

        detail.getChildren().clear();





    }


    @FXML
    public void afficher(MouseEvent e) {

        Pane newLoadedPane = null;
        try {

            System.out.println(getClass().getResource("/beeblyEchange/gui/vue/estimationProposition/afficherDetailsEstimationProposition.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEchange/gui/afficherDetailsEstimationProposition.fxml"));
            newLoadedPane = loader.load();
            AfficherDetailsEstimationController c = loader.getController();
            c.pass(table.getSelectionModel().getSelectedItem());

        } catch (IOException e1) {
            // TODO Auto-generated catc1h block
            e1.printStackTrace();
        }
        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);
    }
}

