/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package beeblyEvent.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author khaled
 */
public class HomePageController implements Initializable {

    @FXML
    private Button f;
        @FXML
    private Button home;
    @FXML
    private Button goSat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        // TODO
    }    

    @FXML
    private void changeEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/Evenement.fxml"));
            Parent root = loader.load();
            f.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
    }

    @FXML
    private void changeTicket(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/ticket.fxml"));
            Parent root = loader.load();
            f.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
    }

    @FXML
    private void goSatRedirction(ActionEvent event) {
           try {
               
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beeblyEvent/gui/StatistiqueFXML.fxml"));
            Parent root = loader.load();
            f.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
    }
    
        @FXML
    private void goSatMenu(ActionEvent event) {
           try {
               
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home/gui/bar.fxml"));
            Parent root = loader.load();
            f.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());   
        }
    }
    
}
