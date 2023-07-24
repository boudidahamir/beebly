/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package beeblyEvent.gui;

import beebly.entity.Evenement;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import beebly.services.ServiceEvenementIMP;

/**
 * FXML Controller class
 *
 * @author khaled
 */
public class EvenementController implements Initializable {
    
    @FXML
    private TableView<Evenement> table_reclamation;
    @FXML
    private TableColumn<Evenement, Integer> id;
    @FXML
    private TableColumn<Evenement, String> libelle;
    @FXML
    private TableColumn<Evenement, String> image;
    @FXML
    private TableColumn<Evenement, String> date;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> emplacement;
    @FXML
    private TableColumn<Evenement, Integer> nb_place;
    @FXML
    private TableColumn<Evenement, Integer> duree;
    @FXML
    private TableColumn<Evenement, Integer> id_user;
    private DatePicker date_picker_field;
    
    @FXML
    private TextField tf_libelle;
    @FXML
    private TextField tf_description;
    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_image;
    @FXML
    private TextField tf_emplacement;
    @FXML
    private TextField tf_duree;
    @FXML
    private TextField tf_id_user;
    @FXML
    private TextField tf_nb_place;
    @FXML
    private DatePicker tf_date;
    /**
     * Initializes the controller class.
     */
    ServiceEvenementIMP se = new ServiceEvenementIMP();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ObservableList<Evenement> obsreservationlist=FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> ChoiceBox;
    @FXML
    private TextField setPromptText;
    @FXML
    private ImageView image_tableView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficheTableView();
         filteredSearch();
        ChoiceBox.getItems().addAll("libelle", "description", "emplacement");
        ChoiceBox.setValue("libelle");
        setPromptText.setPromptText("Rechercher ");
    }
    
    private void search_date(ActionEvent event) {
          System.out.println("date :"+date_picker_field.getValue());
        get_evenemnts_by_date(date_picker_field.getValue()); 
        afficheTableView();
    }
    
    
    public void afficheTableView() {
        List event = se.afficherEvenements();
        ObservableList list = FXCollections.observableArrayList(event);
        
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        nb_place.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        table_reclamation.setItems(list);
        
    }
    
    public void clear() {
        tf_id.setText("");
        tf_libelle.setText("");
        tf_image.setText("");
        tf_emplacement.setText("");
        tf_duree.setText("");
        tf_description.setText("");
        tf_id_user.setText("");
        
        tf_nb_place.setText("");
        
        tf_id.setText("");
        
    }
    
    @FXML
    private void btn_update(ActionEvent event) {
        Evenement evenement = new Evenement();
        evenement.setId(Integer.parseInt(tf_id.getText()));
        evenement.setLibelle(tf_libelle.getText());
        evenement.setImage(tf_image.getText());
        evenement.setDescription(tf_description.getText());
        
        evenement.setEmplacement(tf_emplacement.getText());
        evenement.setDuree(Integer.parseInt(tf_duree.getText()));
        evenement.setId_user(Integer.parseInt(tf_id_user.getText()));
        evenement.setNb_place(Integer.parseInt(tf_nb_place.getText()));
        evenement.setDate("" + tf_date.getValue());
        se.modifierEvenement(evenement, Integer.parseInt(tf_id.getText()));
        afficheTableView();
        clear();
        
    }
    
    @FXML
    private void clickeMouse(MouseEvent event) {
        
        tf_id.setText("" + table_reclamation.getSelectionModel().getSelectedItem().getId());
        tf_libelle.setText("" + "" + table_reclamation.getSelectionModel().getSelectedItem().getLibelle());
        tf_image.setText("" + table_reclamation.getSelectionModel().getSelectedItem().getImage());
        tf_emplacement.setText(table_reclamation.getSelectionModel().getSelectedItem().getEmplacement());
        tf_duree.setText("" + table_reclamation.getSelectionModel().getSelectedItem().getDuree());
        
        tf_id_user.setText("" + table_reclamation.getSelectionModel().getSelectedItem().getId_user());
        
        tf_nb_place.setText("" + table_reclamation.getSelectionModel().getSelectedItem().getNb_place());
        tf_date.setValue(LocalDate.parse(table_reclamation.getSelectionModel().getSelectedItem().getDate(), formatter));
        tf_description.setText("" + table_reclamation.getSelectionModel().getSelectedItem().getDescription());
        
    }

    @FXML
    private void btn_redirect(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/ajouterEvenement.fxml"));
            Parent root = loader.load();
            tf_id.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    @FXML
    private void btn_retoure(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/HomePage.fxml"));
            Parent root = loader.load();
            tf_libelle.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    
     public void get_evenemnts_by_date(LocalDate date){ 
       
        table_reclamation.getItems().clear();
        se.get_evenemnts_by_date( date).forEach((event)->{
            obsreservationlist.add(event);
        });
    }
     
      public void filteredSearch() {

        List<Evenement> List_event = se.afficherEvenements();
        ObservableList<Evenement> list = FXCollections.observableArrayList(List_event);
        FilteredList<Evenement> flProduit = new FilteredList(list, p -> true);
        setPromptText.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (ChoiceBox.getValue()) {
                case "libelle":
                    flProduit.setPredicate(p -> p.getLibelle().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "description":
                    flProduit.setPredicate(p -> p.getDescription().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                case "emplacement":
                    flProduit.setPredicate(p -> p.getEmplacement().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
                
                   
            }

        });
        table_reclamation.setItems(flProduit);
        ChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            if (newVal != null) {
                setPromptText.setText("");
            }
        });
     
     
      }
    
    
}
