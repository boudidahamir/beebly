/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.gui;

import beebly.entity.Livre;
import beebly.services.LivreService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author amona
 */
public class AfficherLivreController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Label titre;
    @FXML
    private Label categorie;
    @FXML
    private Label date;
    @FXML
    private Label description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    Livre liv = new Livre();
    public void pass(Livre liv) {
        
        this.liv = liv;
        date.setText(liv.getDatePublication().toString());
        description.setText(liv.getDescription());
        categorie.setText(liv.getCategorie());
        titre.setText(liv.getTitre());
    }

    

   

    @FXML
    private void supprimerLivre(ActionEvent event) {
        
        Pane newLoadedPane = null;
        LivreService LivService=new LivreService();
        System.out.println(liv);
        LivService.Supprimer(liv);
        
        pane.getChildren().clear();
        pane.getChildren().add(newLoadedPane);
    }
    
}
