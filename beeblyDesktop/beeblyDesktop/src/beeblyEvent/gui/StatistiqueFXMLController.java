/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package beeblyEvent.gui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import beebly.services.ServiceEvenementIMP;

/**
 * FXML Controller class
 *
 * @author khaled
 */
public class StatistiqueFXMLController implements Initializable {

    @FXML
    private PieChart pieChart;
        ServiceEvenementIMP su = new ServiceEvenementIMP();
    @FXML
    private AnchorPane barchart;
    @FXML
    private Button btn_retour;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // TODO
        Map<String, Integer> countMap = new HashMap<>();
        countMap.putAll(su.countTicketByEmp());
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        countMap.forEach((role, count) -> {
            pieChartData.add(new PieChart.Data(role, count));
        });
        pieChart.setTitle("Statistiques les emplacement des vente des tickets");
        pieChart.setData(pieChartData);
        FadeTransition transition = new FadeTransition(Duration.seconds(3), pieChart);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }    

    @FXML
    private void redirectAction(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/HomePage.fxml"));
            Parent root = loader.load();
            btn_retour.getScene().setRoot(root);
        }    catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
        
    }
    
}
