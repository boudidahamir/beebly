/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package beeblyEvent.gui;

import beebly.entity.Ticket;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import beebly.services.ServiceTicketIMP;

/**
 * FXML Controller class
 *
 * @author khaled
 */
public class TicketController implements Initializable {

    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_prix;
    @FXML
    private TextField tf_id_evenement;
    @FXML
    private TableView<Ticket> tv_ticket;
    @FXML
    private TableColumn<Ticket, Integer> id;
    @FXML
    private TableColumn<Ticket, String> type;
    @FXML
    private TableColumn<Ticket, Integer> prix;
    @FXML
    private TableColumn<Ticket, Integer> id_evenement;
    @FXML
    private Button btn_update;

    /**
     * Initializes the controller class.
     */
    ServiceTicketIMP st = new ServiceTicketIMP();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficheTableView();
    }

    @FXML
    private void updateTicket(ActionEvent event) {
        Ticket ticket = new Ticket();
        ticket.setId(Integer.parseInt(tf_id.getText()));
        ticket.setPrix(Integer.parseInt(tf_prix.getText()));
        ticket.setType(tf_type.getText());
        ticket.setId_evenement(Integer.parseInt(tf_id_evenement.getText()));

        st.modifierTicket(ticket, Integer.parseInt(tf_id.getText()));
        afficheTableView();

    }
    
    
    


    public void afficheTableView() {
        List ticket = st.afficherTicket();
        ObservableList list = FXCollections.observableArrayList(ticket);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        id_evenement.setCellValueFactory(new PropertyValueFactory<>("id_evenement"));
        tv_ticket.setItems(list);
        clear();

    }
    public void clear()
    {
        tf_id.setText("");
        tf_type.setText("");
        tf_prix.setText("");
        tf_id_evenement.setText("");
    }

    @FXML
    private void MouseClicked(MouseEvent event) {
        tf_id.setText("" + tv_ticket.getSelectionModel().getSelectedItem().getId());
        tf_id_evenement.setText("" + "" + tv_ticket.getSelectionModel().getSelectedItem().getId_evenement());
        tf_prix.setText("" + tv_ticket.getSelectionModel().getSelectedItem().getPrix());
        tf_type.setText(tv_ticket.getSelectionModel().getSelectedItem().getType());
    }

    @FXML
    private void retoure(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/HomePage.fxml"));
            Parent root = loader.load();
            tf_type.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void aller_Add(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/ajouterTicket.fxml"));
            Parent root = loader.load();
            tf_type.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
