/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamation.GUI;

import beebly.entity.Reclamation;
import beebly.services.ServiceImageIMP;
import beebly.services.ServiceReclamationIMP;
import beebly.services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class ReclamationsUserController implements Initializable {
    
                @FXML
    private Button home;
    @FXML
    private TableView<Reclamation> table_reclamation;
    @FXML
    private TableColumn<Reclamation, Date> date;
    @FXML
    private TableColumn<Reclamation, String> type;
    @FXML
    private TableColumn<Reclamation, String> sujet;
    @FXML
    private TableColumn<Reclamation, String> status;
    @FXML
    private TableColumn<Reclamation, String> photo;
    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private TableColumn<Reclamation, String> editcol;
    @FXML
    private DatePicker date_picker_field;
    @FXML
    private Button type_search_button;
    @FXML
    private Button date_search_button;
    @FXML
    private Button etat_search_button;
    @FXML
    private ChoiceBox<String> type_choice_field;
    @FXML
    private ChoiceBox<String> etat_choice_field;
    @FXML
    private Button add_button;
    
    
    
    ServiceReclamationIMP service_reclamation=new ServiceReclamationIMP();
    ObservableList<Reclamation> obsreservationlist=FXCollections.observableArrayList();
    ObservableList<String> typeList = FXCollections.observableArrayList("Aucun","livre", "livraison", "autre");
    ObservableList<String> etatList = FXCollections.observableArrayList( "Aucun","en cours", "traitée");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Todo 
        get_reclamations();
        loadData();
        type_choice_field.setValue("Aucun");
        type_choice_field.setItems(typeList);
        etat_choice_field.setValue("Aucun");
        etat_choice_field.setItems(etatList);
        type_search_button.setDisable(true);
        etat_search_button.setDisable(true);
        type_choice_field.setOnAction((event) -> {
           if(type_choice_field.getValue()=="Aucun"){
                type_search_button.setDisable(true);
           }else{
                type_search_button.setDisable(false);
           }
        });
        etat_choice_field.setOnAction((event) -> {
           if(etat_choice_field.getValue()=="Aucun"){
                etat_search_button.setDisable(true);
           }else{
                etat_search_button.setDisable(false);
           }
        });
    }
    
    @FXML
    private void clear_search_fields(ActionEvent event) {
        type_choice_field.setValue("Aucun");
        etat_choice_field.setValue("Aucun");
        date_picker_field.setValue(null);
        get_reclamations();
        loadData();
    }

    @FXML
    private void serach_type(ActionEvent event) {
        get_reclamations_by_type(type_choice_field.getValue()); 
        loadData();
    }

    @FXML
    private void search_etat(ActionEvent event) {
        get_reclamations_by_status(etat_choice_field.getValue()); 
        loadData();
    }
    
    @FXML
    private void search_date(ActionEvent event) { 
        System.out.println("date :"+date_picker_field.getValue());
        get_reclamations_by_date(date_picker_field.getValue()); 
        loadData();
    }
    
     @FXML
    private void add_reclamation(ActionEvent event) {
        try {
            /*FXMLLoader loader = new FXMLLoader ();
            loader.setLocation(getClass().getResource("/Vues/AjouterReclamation.fxml"));
            loader.load();*/
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamation/GUI/AjouterReclamation.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root); 
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/Assets/logo.png"));
                stage.setTitle("Beebly");
                stage.setScene(scene); 
                AjouterReclamationController ajouter_reclamation_controller=loader.getController();
                ajouter_reclamation_controller.setReclamations_user_controller(this);
                stage.show();
        }catch (IOException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void get_reclamations_by_type(String type){ 
        obsreservationlist.clear();
        table_reclamation.getItems().clear();
        service_reclamation.get_reclamations_by_user_id_by_type(UserService.currentuser.getId(), type).forEach((reclamation)->{
            System.out.println("reclamtion ala "+reclamation);
            obsreservationlist.add(reclamation);
        });
    }
    public void get_reclamations_by_status(String status){ 
        obsreservationlist.clear();
        table_reclamation.getItems().clear();
        service_reclamation.get_reclamations_by_user_id_by_status(UserService.currentuser.getId(), status).forEach((reclamation)->{ 
            obsreservationlist.add(reclamation);
        });
    }
    public void get_reclamations_by_date(LocalDate date){ 
        obsreservationlist.clear();
        table_reclamation.getItems().clear();
        service_reclamation.get_reclamations_by_user_id_by_date(UserService.currentuser.getId(), date).forEach((reclamation)->{
            obsreservationlist.add(reclamation);
        });
    }
    public void get_reclamations(){ 
        obsreservationlist.clear();
        table_reclamation.getItems().clear();
        service_reclamation.get_reclamations_by_user_id(UserService.currentuser.getId()).forEach((reclamation)->{
            obsreservationlist.add(reclamation);
        });
    }
    public void loadData(){
        table_reclamation.setItems(obsreservationlist);
        System.out.println("table items "+table_reclamation.getItems()); 
        //clear_search_fields();
        date.setCellValueFactory(data->{
             Date date= data.getValue().getDate();
             ObservableValue<Date> obs=new SimpleObjectProperty<>(date);
             return obs;
         });
        type.setCellValueFactory(data->{
             String  type= data.getValue().getType();
             ObservableValue<String> obs=new SimpleObjectProperty<>(type);
             return obs;
         });
        sujet.setCellValueFactory(data->{
             String  sujet= data.getValue().getSujet();
             ObservableValue<String> obs=new SimpleObjectProperty<>(sujet);
             return obs;
         });
        Description.setCellValueFactory(data->{
             String  Description= data.getValue().getDescription();
             ObservableValue<String> obs=new SimpleObjectProperty<>(Description);
             return obs;
         });
        
        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctoryPhoto;
        cellFoctoryPhoto = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        Reclamation reclamation=(Reclamation) this.getTableRow().getItem();
                        Button button = new Button();
                        //System.out.println("reclamation photo"+reclamation.getPhoto());
                            if(reclamation!=null){ 
                                boolean check_image_exists=new ServiceImageIMP().isImage(reclamation.getPhoto());
                                if(!check_image_exists){
                                    setText("Aucune");//photo
                                }else{  
                                    Label label =new Label(); 
                                    label.setText("Voir photo");
                                    button.setId("details_button");
                                    button.setText("Voir photo");
                                    //Button fucntions 
                                    button.setOnMouseClicked((event) -> {
                                        try {
                                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamation/GUI/VoirPhoto.fxml"));
                                                Parent root = loader.load();
                                                Scene scene = new Scene(root); 
                                                Stage stage = new Stage();
                                                stage.getIcons().add(new Image("/Assets/logo.png"));
                                                stage.setTitle("Beebly");
                                                stage.setScene(scene);
                                                VoirPhotoController voir_photo_controller=loader.getController(); 
                                                voir_photo_controller.setReclamation(reclamation);
                                                voir_photo_controller.setStage(stage);
                                                voir_photo_controller.setImage_view();
                                                stage.show();
                                        } catch (IOException ex) {
                                               // Logger.getLogger(AjouterRéservationChambreController.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    });
                                    HBox managebtn = new HBox(button);
                                    managebtn.setStyle("-fx-alignment:center");
                                    HBox.setMargin(button,new Insets(2, 2, 0, 0));
                                    setGraphic(managebtn);
                                }
                            }
                        
                    }
                }

            };
           
            return cell;
        };
        
        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctoryEtat;
        cellFoctoryEtat = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        Reclamation reclamation=(Reclamation) this.getTableRow().getItem();
                        Button button = new Button(); 
                        if(reclamation!=null){ 
                            if(reclamation.getStatus().equals("en cours")){
                                button.setId("annuler_etat");
                                button.setText(reclamation.getStatus());
                            }else{ 
                                button.setId("confirmer_button");
                                button.setText(reclamation.getStatus());
                            }
                            HBox managebtn = new HBox(button);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(button,new Insets(2, 2, 0, 0));
                            setGraphic(managebtn);
                            setText(null);
                        }
                        
                    }
                }

            };
           
            return cell;
        };
        
        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctoryAction; 
        ReclamationsUserController reclamations_user_controller=this;
        cellFoctoryAction = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        
                        Reclamation reclamation=(Reclamation) this.getTableRow().getItem();
                        HBox managebtn = new HBox();
                        Button button = new Button(); 
                            if(reclamation!=null){ 
                                if(reclamation.getStatus().equals("en cours")){
                                    FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                                    deleteIcon.setStyle(
                                        " -fx-cursor: hand ;"
                                                + "-glyph-size:24px;"
                                                + "-fx-fill:#ff1744;"
                                                + "-fx-border-insets: 5px;"
                                                + "-fx-padding: 10px;"
                                     );
                                    button.setId("details_button");
                                    button.setText("Modifier");
                                    managebtn.getChildren().addAll(button,deleteIcon);

                                    //Button fucntions 
                                    button.setOnMouseClicked((event) -> {
                                        try {
                                            /*FXMLLoader loader = new FXMLLoader ();
                                            loader.setLocation(getClass().getResource("/Vues/AjouterReclamation.fxml"));
                                            loader.load();*/
                                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamation/GUI/ModifierReclamation.fxml"));
                                                Parent root = loader.load();
                                                Scene scene = new Scene(root); 
                                                Stage stage = new Stage();
                                                stage.getIcons().add(new Image("/Assets/logo.png"));
                                                stage.setTitle("Beebly");
                                                stage.setScene(scene);

                                                ModifierReclamationController modifier_reclamation_controller=loader.getController(); 
                                                modifier_reclamation_controller.setReclamations_user_controller(reclamations_user_controller);
                                                modifier_reclamation_controller.setReclamation(reclamation);
                                                modifier_reclamation_controller.initialize_modify_Reclamation_form();
                                                stage.show();
                                        }catch (IOException ex) {
                                            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    });

                                    //Button fucntions  
                                    deleteIcon.setOnMouseClicked((event) -> {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Supprimer une réclamtion");
                                        alert.setHeaderText("Vous voulez vraiment effectuer la supression ?");
                                        Optional<ButtonType> option = alert.showAndWait();
                                        if (option.get() == ButtonType.OK) { 
                                            service_reclamation.delete_reclamation(reclamation.getId());
                                            get_reclamations();
                                            loadData();
                                        }
                                    });
                                }else{ 
                                    button.setId("details_button");
                                    button.setText("Voir réponse");
                                    managebtn.getChildren().addAll(button);

                                    //Button fucntions 
                                    button.setOnMouseClicked((event) -> {
                                        try {
                                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamation/GUI/VoirReponse.fxml"));
                                                Parent root = loader.load();
                                                Scene scene = new Scene(root); 
                                                Stage stage = new Stage();
                                                stage.getIcons().add(new Image("/Assets/logo.png"));
                                                stage.setTitle("Beebly");
                                                stage.setScene(scene);

                                                VoirReponseController voir_reponse_controller=loader.getController(); 
                                                voir_reponse_controller.setReclamationsUser_controller(reclamations_user_controller);
                                                voir_reponse_controller.setReclamation(reclamation);
                                                voir_reponse_controller.setReponse(reclamation.getReponse());
                                                voir_reponse_controller.initialize_modify_Response_form();
                                                stage.show();
                                        } catch (IOException ex) {
                                               // Logger.getLogger(AjouterRéservationChambreController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
                                }
                                managebtn.setStyle("-fx-alignment:center");
                                HBox.setMargin(button,new Insets(4, 2, 4, 4));
                                setGraphic(managebtn);
                                setText(null);
                            }                           
                       
                    }
                }

            };
           
            return cell;
        };
        photo.setCellFactory(cellFoctoryPhoto);
        status.setCellFactory(cellFoctoryEtat);
        editcol.setCellFactory(cellFoctoryAction);
        table_reclamation.setItems(obsreservationlist); 
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
