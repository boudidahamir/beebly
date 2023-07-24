/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.gui;

import beebly.entity.Livre;
import beebly.services.LivreService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 *
 */
public class AjouterLivreController implements Initializable {
    
            @FXML
    private Button home;
    @FXML
    private TextField titre_field;
    @FXML
    private TextArea description_field;
    @FXML
    private TextField image_field;
    @FXML
    private Button add_button;
    @FXML
    private Button browse_button;
    @FXML
    private TextField categorie_field;

    private boolean description, sujet = false;
    private BooleanProperty form_valid = new SimpleBooleanProperty(true);
    FileChooser fileChooser = new FileChooser();
    File selectedFile = null;
    @FXML
    private Button update_button;
    @FXML
    private TableView<Livre> table;
    @FXML
    private TableColumn<Livre, String> titre;
    @FXML
    private TableColumn<Livre, String> categorie;
    @FXML
    private TableColumn<Livre, Date> date;
    @FXML
    private TableColumn<Livre, String> dess;

    List<Livre> LL;
    LivreService lss = new LivreService();
    @FXML
    private Button RedBtnn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        description_field.setWrapText(true);
        image_field.setDisable(true);
        /*
        titre_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 5) {
                titre_field.setStyle("-fx-border-color: red");
                sujet = false;
            } else {
                titre_field.setStyle(null);
                sujet = true;
            }
            if (newValue.length() > 0 && newValue.charAt(0) == ' ') {
                titre_field.setText(newValue.trim());
            }
            if (newValue.matches(".*\\d+.*")) {
                titre_field.setText(newValue.replaceAll("\\d", ""));
            }

            form_valid.set(!check_form());
        });
        description_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 10) {
                description_field.setStyle("-fx-border-color: red");
                description = false;
            } else {
                description_field.setStyle(null);
                description = true;
            }
            if (newValue.length() > 0 && newValue.charAt(0) == ' ') {
                description_field.setText(newValue.trim());
            }
            form_valid.set(!check_form());
        }); */

        afficherLivres();

    }

    @FXML
    private void browse_images(ActionEvent event) {

        fileChooser.setTitle("Select File");

        File initialDirectory = new File(System.getProperty("user.home"));
        fileChooser.setInitialDirectory(initialDirectory);

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        Stage stage = (Stage) browse_button.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            image_field.setText(selectedFile.getAbsolutePath());
        } else {
            image_field.setText(null);

        }
    }

    @FXML
    private void add_livre(ActionEvent event) {

        Livre livre = new Livre();
        if (selectedFile != null) {
            try {

                InputStream inputStream = new FileInputStream(selectedFile);
                byte[] imageBytes = new byte[inputStream.available()];
                inputStream.read(imageBytes);
                inputStream.close();
                livre.setPhoto(imageBytes);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            livre.setPhoto(new byte[0]);
        }

        livre.setTitre(titre_field.getText());
        livre.setCategorie(categorie_field.getText());
        livre.setDescription(description_field.getText());

        LivreService LS = new LivreService();
        LS.Ajouter(livre);

        /*   Notifications.create()
                .title("Réclamation envoyée")
                .text("Votre réclamation a été recu avec succés merci.")
                .showInformation(); */
        titre_field.clear();
        description_field.clear();
        image_field.clear();
        categorie_field.clear();
        selectedFile = null;
    }

    /* private boolean check_form() {
        if (sujet == true && description == true) {
            return true;
        }
        return false;
    }*/
    @FXML
    private void update_livre(ActionEvent event) {
        LivreService ls = new LivreService();

        Livre L;
        L = table.getSelectionModel().getSelectedItem();
        L.setTitre(titre.getText());
        L.setCategorie(categorie.getText());
        L.setDescription(dess.getText());
        L.setDatePublication((Date) date.getCellObservableValue(L));

        ls.Modifier(L, titre.getText(), categorie.getText(), dess.getText(), (Date) date.getCellObservableValue(L));

        actualiser();
        reset1();

    }

    @FXML
    private void afficher(MouseEvent event) {

    }

    public void actualiser() {
        LL = lss.Afficher();
        table.getItems().setAll(LL);
    }

    private void reset1() {
        titre_field.clear();

        description_field.clear();

        categorie_field.clear();
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

    public ObservableList<Livre> getLivreList() {
        ObservableList<Livre> livresList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM livre";
        Statement st;
        ResultSet rs;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Livre liv;
            while (rs.next()) {
                liv = new Livre(rs.getString("titre"), rs.getString("categorie"), rs.getDate("date_publication"), rs.getString("description_etat_livre"), rs.getBytes("image"));
                livresList.add(liv);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return livresList;
    }

    public void afficherLivres() {
        ObservableList<Livre> list = getLivreList();

        titre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        categorie.setCellValueFactory(new PropertyValueFactory<Livre, String>("categorie"));
        date.setCellValueFactory(new PropertyValueFactory<Livre, Date>("datePublication"));
        dess.setCellValueFactory(new PropertyValueFactory<Livre, String>("description"));

        table.setItems(list);
    }

    @FXML
    private void RListe(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beebly/gui/AfficherListeLivre.fxml"));
            Parent root = loader.load();
            RedBtnn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }
    
            @FXML
    private void goSatMenu(ActionEvent event) {
           try {
               
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home/gui/bar.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
    }

}
