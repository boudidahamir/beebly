/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.gui;

import beebly.entity.Livre;
import beebly.services.LivreService;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author amona
 */
public class AfficherListeLivreController implements Initializable {

    @FXML
    private TableView<Livre> table;
    @FXML
    private TableColumn<Livre, String> titre;
    @FXML
    private TableColumn<Livre, Date> date;
    @FXML
    private Pane detail;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<Livre, String> categorie;
    @FXML
    private TableColumn<Livre, String> description;
    private ObservableList<Livre> livresList;

    List<Livre> Livv;
    LivreService lvs = new LivreService();
    @FXML
    private Button pdf;
    @FXML
    private PieChart piechart;
    @FXML
    private Button stat;

    private ObservableList<Livre> LivresList;
    @FXML
    private Button suppBtn;
    @FXML
    private Button RedAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LivresList = getLivreList();
        afficherLivres();

        FilteredList<Livre> filteredList = new FilteredList<>(LivresList, p -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filterLivres(filteredList, newValue);
            table.setItems(filteredList);

        });

    }
    public void actualiser() {
        Livv = lvs.Afficher();
        table.getItems().setAll(Livv);
    }

    @FXML
    private void afficher(MouseEvent event) {
        Pane newLoadedPane = null;
        try {

            System.out.println(getClass().getResource("/beebly/gui/AfficherLivre.fxml"));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/beebly/gui/AfficherLivre.fxml"));
            newLoadedPane = loader.load();
            AfficherLivreController L = loader.getController();
            L.pass(table.getSelectionModel().getSelectedItem());

        } catch (IOException e1) {

            e1.printStackTrace();
        }
        detail.getChildren().clear();
        detail.getChildren().add(newLoadedPane);
    }

    @FXML
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
        detail.getChildren().clear();

        ObservableList<Livre> list = getLivreList();

        titre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        categorie.setCellValueFactory(new PropertyValueFactory<Livre, String>("categorie"));
        date.setCellValueFactory(new PropertyValueFactory<Livre, Date>("datePublication"));
        description.setCellValueFactory(new PropertyValueFactory<Livre, String>("description"));

        table.setItems(list);*/

    }

    private void filterLivres(FilteredList<Livre> filteredList, String searchValue) {
        filteredList.setPredicate(liv -> {
            if (searchValue == null || searchValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchValue.toLowerCase();
            if (liv.getDatePublication().toString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        });
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teletubbies", "root", "");
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

    @FXML
    private void exporterPDF1(ActionEvent event) {
        /*try {
            // create a new PDF document
            PDDocument document = new PDDocument();
            
            // create a new page
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            
            // create a new content stream for the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            
            // set the font and font size for the text
           PDType1Font font = new PDType1Font(PDType1Font.HELVETICA_BOLD.getName(), PDType1Font.HELVETICA_BOLD.getEncoding());
            float fontSize = 12;
            contentStream.setFont(font, fontSize);
            
            // write the Livre data to the PDF
            Livre livre = new Livre(); // create a new Livre object for demonstration purposes
            livre.setIdLivre(1);
            livre.setTitre("JavaFX pour les nuls");
            livre.setCategorie("Informatique");
            livre.setDatePublication(new Date());
            livre.setDescription("Ce livre est destiné aux débutants en JavaFX.");
            
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("ID: " + livre.getIdLivre());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Titre: " + livre.getTitre());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Categorie: " + livre.getCategorie());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Date de publication: " + livre.getDatePublication());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Description: " + livre.getDescription());
            contentStream.endText();
            
            // add an image to the PDF
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner une image");
            File imageFile = fileChooser.showOpenDialog(null);
            if (imageFile != null) {
                PDImageXObject image = PDImageXObject.createFromFile(imageFile.getAbsolutePath(), document);
                contentStream.drawImage(image, 50, 450);
            }
            
            // close the content stream
            contentStream.close();
            
            // save the document to a file
            FileChooser fileChooser2 = new FileChooser();
            fileChooser2.setTitle("Enregistrer le fichier");
            fileChooser2.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
            File file = fileChooser2.showSaveDialog(null);
            if (file != null) {
                document.save(file);
                document.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }*/
        try {
            // Obtenir les informations sélectionné
            Livre L = table.getSelectionModel().getSelectedItem();
            // String produitS = produitSBox.getItems().get(p.getProduit_s() - 1);
            // String produitR = produitRBox.getItems().get(p.getProduit_r() - 1);
            Date date = L.getDatePublication();
            String titre = L.getTitre();
            String catego = L.getCategorie();
            String desc = L.getDescription();

            // Ouvrir un dialogue pour choisir le dossier de destination et le nom de fichier
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exporter en PDF");
            fileChooser.setInitialFileName("livre.pdf");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PDF Files", "*.pdf"));
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                // Créer un document PDF avec les informations et enregistrer dans le fichier choisi
                Document document = new Document() {
                };
                PdfWriter.getInstance(document, new FileOutputStream(file));
                document.open();

                // Ajouter un en-tête
                Paragraph header = new Paragraph("Livre", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.UNDERLINE, BaseColor.DARK_GRAY));
                //header.setAlignment(Element.ALIGN_CENTER);
                document.add(header);

                // Ajouter les informations 
                Paragraph descPara = new Paragraph("Description: " + desc.toString(), FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK));
                Paragraph datePara = new Paragraph("Date: " + date.toString(), FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK));
                Paragraph catPara = new Paragraph("Categorie: " + catego.toString(), FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK));
                Paragraph titrePara = new Paragraph("Titre: " + titre.toString(), FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK));

                datePara.setIndentationLeft(30);
                descPara.setIndentationLeft(30);
                titrePara.setIndentationLeft(30);
                catPara.setIndentationLeft(30);

                document.add(datePara);
                document.add(descPara);
                document.add(catPara);
                document.add(titrePara);

                // Fermer le document PDF
                document.close();
                System.out.println("Exporté en PDF: " + file.getAbsolutePath());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void Redstat(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../beebly/gui/Statistic.fxml"));
            Parent root = loader.load();
            stat.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public void afficherLivres() {
        ObservableList<Livre> list = getLivreList();

        titre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        categorie.setCellValueFactory(new PropertyValueFactory<Livre, String>("categorie"));
        date.setCellValueFactory(new PropertyValueFactory<Livre, Date>("datePublication"));
        description.setCellValueFactory(new PropertyValueFactory<Livre, String>("description"));

        table.setItems(list);
    }

 

    @FXML
    private void SupprimerLivre(ActionEvent event) {
        LivreService lss = new LivreService();
        Livre L;   
        L= table.getSelectionModel().getSelectedItem();
        lss.Supprimer(L);
        actualiser();  
    }

    @FXML
    private void RedAjt(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterLivre.fxml"));
            Parent root = loader.load();
            RedAjout.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    

}
