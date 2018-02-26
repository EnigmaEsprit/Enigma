/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entites.Panier.Commande;
import entites.Panier.FonctionPanier;
import entites.Produit.Produit;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;



import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.controlsfx.control.Notifications;
import service.Panier.CommandesServices;

/**
 * FXML Controller class
 *
 * @author jean
 */
public class FXMLCommandesInterfaceController implements Initializable {

    @FXML
    private TableColumn<Commande, String> prixTotalColumn;
    @FXML
    private TableColumn<Commande, String> userColumn;
    @FXML
    private TableColumn<Commande, String> etatColumn;
    @FXML
    private TableColumn<Commande, String> dateCommandeColumn;
    @FXML
    private TableColumn<Commande, String> idTransactionColumn;
    @FXML
    private TableColumn<?, ?> actionColumn;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Commande> data = FXCollections.observableArrayList();
    CommandesServices cs  = new CommandesServices();
    @FXML
    private TableView<Commande> listeCommandes;
    @FXML
    private Pane PaneView;
    private AnchorPane AnchorPaneView;
    @FXML
    private Button btnGenererPdf;
    @FXML
    private HBox HBox;
    @FXML
    private Pane paneView;
    @FXML
    private AnchorPane paneViewEntete;
    @FXML
    private Pane menu;
    private Pane menu3;
    @FXML
    private Pane menu2;
    @FXML
    private Button espClient1;
    @FXML
    private Button espVendeur1;
    @FXML
    private Button espAdmin1;
    @FXML
    private Button Log;
    @FXML
    private Button Event;
    @FXML
    private Button Contacts;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       /* listeCommandes.setMaxHeight(900);
      listeCommandes.prefHeightProperty().bind(AnchorPaneView.widthProperty());
        listeCommandes.prefWidthProperty().bind(AnchorPaneView.widthProperty());
         PaneView.prefHeightProperty().bind(AnchorPaneView.widthProperty());
        PaneView.prefWidthProperty().bind(AnchorPaneView.widthProperty());
        HBox.prefHeightProperty().bind(AnchorPaneView.heightProperty());
        HBox.prefWidthProperty().bind(AnchorPaneView.widthProperty());*/
        
         menu.setTranslateX(-190);
    TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(500), menu);

    menuTranslation.setFromX(-190);
    menuTranslation.setToX(0);

    menu.setOnMouseEntered(evt -> {
        menuTranslation.setRate(1);
        menuTranslation.play();
    });
    menu.setOnMouseExited(evt -> {
        menuTranslation.setRate(-1);
        menuTranslation.play();
    });
    menu2.setVisible(false);
  
    
        btnGenererPdf.setOnMouseClicked((MouseEvent e)->{
            GenererPdf();
            sendEmail();
        });
        setColumn();
        loadList();
    }    


    @FXML
    private void optionModifierQuantiteProduit(ActionEvent event) {
    }

    @FXML
    private void optionActualiser(ActionEvent event) {
    }

    @FXML
    private void statitisques(ActionEvent event) {
        LoadWindow("FXMLStatistiques.fxml", "Statistiques");
    }

    
    
     private void loadList()
    {
    
        
         for (Commande c : cs.selectCommandes()) {
        Button btBloquer = new Button("Bloquer");
             System.out.println(c);
           
            
            data.add(new Commande(c.getIdCommande(),c.getPrixTotal(),c.getNom(),c.getEtat(),c.getDateDeCommande(),c.getIdTransaction(),btBloquer));
            //System.out.println(c);
           
            
            btBloquer.setOnAction((event) -> {
                
               
              Alert alert = new Alert(AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Commande  a ete bloquer ");
              alert.showAndWait();
            });
        }
        
        listeCommandes.setItems(data);

      
    
    }
        
     private void setColumn(){
    
       
        prixTotalColumn.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
        userColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        dateCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("dateDeCommande"));
        idTransactionColumn.setCellValueFactory(new PropertyValueFactory<>("idTransaction"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("buttonBloquer"));
    
    }

    @FXML
    private void GenererPdfMethode(ActionEvent event) {
    }
    
     private void GenererPdf() {
         Document document =new Document();
        try{
           
           PdfWriter writer= PdfWriter.getInstance(document, new FileOutputStream("pdf\\commande.pdf"));
            document.open();
            
            Image image = Image.getInstance("MILK_Logo_WEB_MEDIUM-01_495x.png");
            document.add(new Paragraph("Exemple",FontFactory.getFont(FontFactory.TIMES_BOLD,18,Font.BOLD,BaseColor.RED)));
           document.add(new Paragraph("------------------------------------------------------------------------"));
           document.add(new Paragraph(new Date().toString()));
            
            document.add(image);
           
            
            PdfPTable table = new PdfPTable(5);
            
            table.setWidthPercentage(105);
            table.setSpacingBefore(11f);
            table.setSpacingAfter(11f);
            
            float[] colWidth={2f,2f,2f,2f,2f};
            table.setWidths(colWidth);
            PdfPCell titre = new PdfPCell(new Paragraph("Listes des commandes"));
            titre.setColspan(5);
            titre.setRowspan(2);
            titre.setHorizontalAlignment(Element.ALIGN_CENTER);
            titre.setBackgroundColor(BaseColor.GREEN);
             PdfPCell c2=new PdfPCell(new Paragraph("Prix Total"));
              PdfPCell c3=new PdfPCell(new Paragraph("Nom user"));
              PdfPCell c4=new PdfPCell(new Paragraph("etat"));
              PdfPCell c5=new PdfPCell(new Paragraph("date Commande"));
              PdfPCell c6=new PdfPCell(new Paragraph("Id Transaction"));
              
            /* c1.setHorizontalAlignment(Element.ALIGN_CENTER);
     
              c1.setBackgroundColor(BaseColor.DARK_GRAY);*/
              
             table.addCell(titre);
              table.addCell(c2);
              table.addCell(c3);
              table.addCell(c4);
              table.addCell(c5); table.addCell(c6);
              
              
              for(Commande c: cs.selectCommandes()){
              
              table.addCell(new Paragraph(""+c.getPrixTotal()));
              table.addCell(new Paragraph(""+c.getNom()));
              table.addCell(new Paragraph(""+c.getEtat()));
              table.addCell(new Paragraph(""+c.getDateDeCommande()));
              table.addCell(new Paragraph(""+c.getIdTransaction()));
              
              }
           
              
              document.add(table);
              
              List orderList = new List(List.ORDERED);
              orderList.add(new ListItem("Fun"));
              orderList.add(new ListItem("Technology"));
              orderList.add(new ListItem("Tic"));
              orderList.add(new ListItem("Java"));
              orderList.add(new ListItem("mation"));
              
              document.add(orderList);
              
              
              
            document.close();
            writer.close();
            
            AlertMaker.showSimpleAlert("Success", "Generation du panier.pdf \n Location:G:\\3ieme_annee\\PIDEV\\Atelier\\ApplicationPanier");
            
            notifications();
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("itext PDF program executed");
    }
     
     
     public void notifications(){
         try{
             Thread.sleep(1000);
         }catch(InterruptedException ex){
             Logger.getLogger(FXMLCommandesInterfaceController.class.getName()).log(Level.SEVERE,null,ex);
             
     }
         
        
         
         Notifications notif = Notifications.create()
                 .title("Download Complete")
                 .text("saved to: ")
                 .graphic(new ImageView("Image/check.png"))
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.TOP_LEFT)
                 .onAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 System.out.println("Cliked on Notifications");
             }
         });
         notif.darkStyle();
         
         Platform.runLater(new Runnable() {
             @Override
             public void run() {
             notif.show();}
         });
}
     


public void sendEmail(){
     try{
            String host ="smtp.gmail.com" ;
            String user = "jeandavidbikie@gmail.com";
            String pass = "GeneralIngenieurBikie";
            String to = "jean.bikiembida@esprit.tn  ";
            String from = "jeandavidbikie@gmail.com";
            String subject = "Souk El Medina";
            String messageText = "Your Is Test Email :";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);
                
            
            MimeBodyPart messageBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();
        
         String file = "G:\\3ieme_annee\\PIDEV\\Atelier\\ApplicationPanier\\pdf\\commande.pdf";
        String fileName = "commande.pdf";
    messageBodyPart = new MimeBodyPart();   
    DataSource source = new FileDataSource(file);      
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(fileName);
    multipart.addBodyPart(messageBodyPart);
        
     msg.setContent(multipart);

       
        System.out.println("sending");
        
        System.out.println("Done");
        
        
           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
}

 private void LoadWindow(String loc, String title) {
        try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(homePageParent));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @FXML
    private void btnespClientAction(ActionEvent event) {
              System.out.println(Util.connectedUser);
        if (Util.connectedUser==null)
        {
            LoadWindowParent("/GUI/Utilisateur/Login.fxml", event);
        }
        else
        {
           try {
              
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/ClientInterface.fxml"));
                Parent root = (Parent) loader.load();
                ClientInterfaceController ClientIn = loader.getController();
                ClientIn.myFunction();
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        }

       
    
    @FXML
    private void btnespVendeurAction(ActionEvent event) {
        if(Util.connectedUserVendeur==null){
            LoadWindowParent("/GUI/Utilisateur/LoginVendeur.fxml", event);
        }
        else 
        {
              try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utilisateur/VendeurInterface.fxml"));
                Parent root = (Parent) loader.load();
                VendeurInterfaceController ClientIn = loader.getController();
                ClientIn.myFunction();
                Stage window;
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();

            } catch (IOException ex) {
                System.out.println("catch : " + ex.getMessage());
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
    }
 @FXML
    private void btnespAdminAction(ActionEvent event) {
            
             LoadWindowParent("/GUI/Utilisateur/LoginAdmin.fxml", event);
    }

    @FXML
    private void afficherSuite(MouseEvent event) {
        menu.setVisible(true);
        menu2.setVisible(true);
 
    }

    @FXML
    private void exite(MouseEvent event) {
         menu2.setOnMouseEntered(evt -> {menu2.setVisible(true);});
         menu2.setOnMouseExited(evt -> {menu2.setVisible(false);});
       
        menu2.setVisible(false);
    }


    @FXML
    private void exitemenu3(MouseEvent event) {
        menu3.setOnMouseEntered(evt -> {menu3.setVisible(true);});
         menu3.setOnMouseExited(evt -> {menu3.setVisible(false);});
       
        menu3.setVisible(false);
    }

    @FXML
    private void afficherSuitemenu3(MouseEvent event) {
         menu.setVisible(true);
        menu3.setVisible(true);
 
    }

    private void btnRechercheAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/RechercheContactInterface.fxml", event);
    }

    private void btnMapsAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/Maps.fxml", event);
    }

    private void PanierFenetre(ActionEvent event) {
        
       LoadWindowParent("/GUI/Panier/FXMLPanierInterface.fxml", event);
    }

    private void LoadWindow(String loc){
         try {
            Parent homePageParent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            //stage.setTitle(name);
            stage.setScene(new Scene(homePageParent));
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPanierInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void LoadWindowParent(String loc,ActionEvent event){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(loc));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));

            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(ClientInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void btnEventAction(ActionEvent event) {
    }

      
   

 

    @FXML
    private void statswindow(ActionEvent event) {
          LoadWindowParent("/GUI/Panier/FXMLStatistiques.fxml", event);
    }

    @FXML
    private void CommandesFenetre(ActionEvent event) {
         LoadWindowParent("/GUI/Panier/FXMLCommandesInterface.fxml", event);
    }

}