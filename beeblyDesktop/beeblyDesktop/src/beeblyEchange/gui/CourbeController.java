package beeblyEchange.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import beebly.services.PropositionLivreServiceInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class CourbeController implements Initializable {

    @FXML
    private LineChart<?, ?> chart;


    @FXML
    private ChoiceBox<Integer> annee;

    @FXML
    private ChoiceBox<Integer> mois;

    PropositionLivreServiceInterface propositionLivreService = new PropositionLivreServiceInterface();


    @FXML
    private Label stat1;

    @FXML
    private Label stat2;

    @FXML
    private Label stat3;

    @FXML
    private Label stat4;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        stat1.setText(String.valueOf(propositionLivreService.getNonTraite().size()));
        annee.getItems().addAll(2023,2022,2021,2020,2019);
        mois.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        annee.setValue(2023);
        mois.setValue(3);
        chart.getData().add(propositionLivreService.getStat(mois.getValue(), annee.getValue()));



}


    public void refresh(ActionEvent e){

        chart.getData().add(propositionLivreService.getStat(mois.getValue(), annee.getValue()));


}
}