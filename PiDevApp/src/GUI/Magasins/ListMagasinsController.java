/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Magasins;

import entites.Utilisateur.Utilisateur;
import entites.Magasins.magasins;
import service.Magasins.magasinsServices;
import service.Produits.produitServices;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entites.Utilisateur.Vendeur;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import service.Utilisateur.AdminService;
import service.Utilisateur.VendeurService;

public class ListMagasinsController implements Initializable {

    @FXML
    private JFXTextField tfSearch;
    @FXML
    private TableView<magasins> tableM;
    @FXML
    private TableColumn<magasins, String> nommag;
    @FXML
    private TableColumn<magasins, String> desciptionmag;
    @FXML
    private TableColumn<magasins, Date> datemag;
    @FXML
    private TableColumn<magasins, String> contactmag;
    @FXML
    private TableColumn<magasins, String> adressemag;
    @FXML
    private TableColumn<magasins, String> nummag;
    //@FXML
   // private TableColumn<magasins, Integer> iduser;
    @FXML
    private Button btnmodifier;
      
    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfdescription;


    
    @FXML
    private TextField tfcontact;

    @FXML
    private TextField tfnum;

    @FXML
    private TextField tfadresse;

    VendeurService us;
   Utilisateur u;
    magasinsServices ms=new magasinsServices();
    List<magasins> magasins;

    private ObservableList<magasins> data = FXCollections.observableArrayList();
    
    FilteredList<magasins> filteredData = new FilteredList<>(data);
    magasins mag;
    magasins magasin;
    @FXML
    private JFXComboBox<String> listvendeur;
  public ListMagasinsController()
  {
        magasins=ms.afficherMagasinc();
        data.addAll(magasins);}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load();
         
         
         
   }    
   
     private void load() {
      
        nommag.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomMagasin()));
        desciptionmag.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescriptionMagasin()));
        datemag.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getDateCreationMagasin()));
        
        contactmag.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContactMagasin()));
     
        adressemag.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresseMagasin()));
        
        nummag.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumeroMagasin()));
    
        //iduser.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getIdUser()));

               tableM.setItems(data); 
      }

    @FXML
    private void serachMagasin(KeyEvent event) {
         tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                    filteredData.setPredicate((magasins mag)-> {
                        
                            if (newValue == null || newValue.isEmpty()) {
                                return true;
                            }

                            // compare avec le filtre
                            String lowerCaseFilter = newValue.toLowerCase();
                              //bech theb tlawej
                            if (mag.getDescriptionMagasin().toLowerCase()
                                    .indexOf(lowerCaseFilter) != -1) {
                                return true; 
                            }
                          
                            return false; // Does not match.
                        });
                });
    
       SortedList<magasins> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableM.comparatorProperty());
        tableM.setItems(filteredData);
         
        
    }

    @FXML
    private void supprimermagasin(ActionEvent event) {
         magasins mm=ms.rechercherMagasinsById(tableM.getSelectionModel().getSelectedItem().getIdMagasin());
          int iduser=mm.getIdUser();
          u= us.rechercherVendeurById(iduser);
 //  ---------------------------- //u.setActif(0);
          ms.supprimerMagasins(tableM.getSelectionModel().getSelectedItem().getIdMagasin());

          tableM.getItems().clear();
          tableM.getItems().addAll(ms.afficherMagasinc());
          tableM.refresh();
           tfnom.setText("");
            tfdescription.setText("");
            tfcontact.setText("");
            tfnum.setText("");
            tfadresse.setText("");
    }

    @FXML
    private void modifiermagasin(ActionEvent event) {
        if(!tfnom.getText().isEmpty()|!tfdescription.getText().isEmpty()|!tfadresse.getText().isEmpty()|!tfnum.getText().isEmpty()|!tfcontact.getText().isEmpty())
        {
           mag =   ms.rechercherMagasinsById(tableM.getSelectionModel().getSelectedItem().getIdMagasin());

            mag.setNomMagasin(tfnom.getText());
            mag.setDescriptionMagasin(tfdescription.getText());
            mag.setAdresseMagasin(tfadresse.getText());
            mag.setNumeroMagasin(tfnum.getText());
            mag.setContactMagasin(tfcontact.getText());
              Utilisateur u=new Utilisateur();
        u=us.rechercherVendeurByName(listvendeur.getSelectionModel().getSelectedItem());
        mag.setIdUser(u.getId());
             ms.modifierMagasin(mag);
             
        }
         tableM.getItems().clear();
          tableM.getItems().addAll(ms.afficherMagasinc());
            tableM.refresh();
             tfnom.setText("");
            tfdescription.setText("");
            tfcontact.setText("");
            tfnum.setText("");
            tfadresse.setText("");
            
            }
    @FXML   
    void affichermagasin(MouseEvent event) {
 
        //us=new VendeurService();
         AdminService us= new AdminService();
        ObservableList<String> data = FXCollections.observableArrayList();
        ObservableList<Vendeur> lsm=us.SelectVendeur();
        for (int i=0; i<lsm.size();i++)
        {
        data.add(lsm.get(i).getNom());
        
                }
  listvendeur.setItems(data);
   
 mag =   ms.rechercherMagasinsById(tableM.getSelectionModel().getSelectedItem().getIdMagasin());
     tfnom.setText(mag.getNomMagasin());
   tfdescription.setText(mag.getDescriptionMagasin());
   tfcontact.setText(mag.getContactMagasin());
   tfnum.setText(mag.getNumeroMagasin());
  // tfdate.setText(mag.get);
   tfadresse.setText(mag.getAdresseMagasin());
        listvendeur.getSelectionModel().select(tableM.getSelectionModel().getSelectedIndex());

    }
}
