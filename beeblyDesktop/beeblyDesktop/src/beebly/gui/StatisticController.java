/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.gui;

import beebly.entity.Livre;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author amona
 */
public class StatisticController implements Initializable {

    @FXML
    private VBox box;
    @FXML
    private PieChart pieChart;

    private List<Livre> livres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO // Retrieve the list of Livre objects from your data source
        livres = retrieveLivres();

        // Calculate the statistics
        Map<String, Integer> categories = new HashMap<>();
        for (Livre livre : livres) {
            String category = livre.getCategorie();
            categories.put(category, categories.getOrDefault(category, 0) + 1);
        }

        // Add the data series to the pie chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : categories.entrySet()) {
            String category = entry.getKey();
            int count = entry.getValue();
            pieChartData.add(new PieChart.Data(category, count));
        }
        pieChart.setData(pieChartData);
    }

    private List<Livre> retrieveLivres() {
        List<Livre> livres = new ArrayList<>();
    String url = "jdbc:mysql://localhost:3306/web";
    String username = "root";
    String password = "";
    try (Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement()) {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM livre");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String titre = resultSet.getString("titre");
            String categorie = resultSet.getString("categorie");
            Date datePublication = resultSet.getDate("date_publication");
            String description = resultSet.getString("description_etat_livre");
            Livre livre = new Livre(id, titre, categorie, datePublication, description);
            livres.add(livre);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return livres;
    }

}
