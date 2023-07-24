/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.gui;

import beebly.entity.Client;
import beebly.entity.Commande;
import beebly.entity.EtatCommande;
import beebly.entity.Livre;
import beebly.services.CommandeService;
import beebly.services.LivreService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;

/**
 * FXML Controller class
 *
 * @author amona
 */
public class AfficherListeCommandeController implements Initializable {

    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, Integer> idClient;
    @FXML
    private TableColumn<Commande, String> Adresse;
    @FXML
    private TableColumn<Commande, Float> prix;
    @FXML
    private Pane detail;
    @FXML
    private TextField recherche;

    List<Commande> Cmdd;
    CommandeService cms = new CommandeService();
    private ObservableList<Commande> commandesList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commandesList = getCommandeList();
        refresh();
        // actualiser();
        FilteredList<Commande> filteredList = new FilteredList<>(commandesList, p -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filterCommandes(filteredList, newValue);
            table.setItems(filteredList);
        });

    }

    @FXML
    private void afficher(MouseEvent event) {
        /* Pane newLoadedPane = null;
        try {

            System.out.println(getClass().getResource("../../beebly/gui/AfficherCommande.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../beebly/gui/AfficherCommande.fxml"));
            newLoadedPane = loader.load();
            AfficherCommandeController C = loader.getController();
            C.pass(table.getSelectionModel().getSelectedItem());

        } catch (IOException e1) {
            
            e1.printStackTrace();
        }
        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);*/
    }

    @FXML
    private void refresh(KeyEvent event) {
    }

    public void actualiser() {
//        Cmdd =cms.Afficher();
        table.getItems().setAll(Cmdd);
    }

    private void refresh() {
        /* ObservableList<Livre> livs = FXCollections.observableArrayList();

        LivreService ls = new LivreService();
        livs = FXCollections.observableArrayList();
        System.out.println();
        ObservableList<Livre> LivreObservableList
                = FXCollections.observableArrayList(ls.Afficher());

        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        date.setCellValueFactory(new PropertyValueFactory<Livre, Date>("datePublication"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        System.out.println(livs);
        table.setItems(LivreObservableList);
        detail.getChildren().clear();*/

        ObservableList<Commande> list = getCommandeList();

        idClient.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("idClient"));
        Adresse.setCellValueFactory(new PropertyValueFactory<Commande, String>("adresseLivraison"));
       // etat.setCellValueFactory(new PropertyValueFactory<Commande, EtatCommande>("etatcommande"));
        prix.setCellValueFactory(new PropertyValueFactory<Commande, Float>("prix"));

        table.setItems(list);

    }

    private void filterCommandes(FilteredList<Commande> filteredList, String searchValue) {
        filteredList.setPredicate(cm -> {
            if (searchValue == null || searchValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchValue.toLowerCase();
            if (cm.getAdresseLivraison().toString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        });
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<Commande> getCommandeList() {
        ObservableList<Commande> CommandesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM livre";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Commande cmm;
            //String etatCommandeStr = rs.getString("etatcommande");
           // EtatCommande etatCommande = EtatCommande.valueOf(etatCommandeStr);
            int idClient = rs.getInt("idClient");
            Client client = new Client(idClient);
            while (rs.next()) {
                cmm = new Commande(rs.getInt("idClient"), rs.getString("adresselivraison"), rs.getFloat("prix"));
                CommandesList.add(cmm);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return CommandesList;
    }
}
