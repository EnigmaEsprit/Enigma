/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Decouverte;

import GUI.Utilisateur.ClientInterfaceController;
import GUI.Utilisateur.InscriptionController;
import GUI.Utilisateur.LoginController;
import GUI.Utilisateur.VendeurInterfaceController;
import Util.Util;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MapsController implements Initializable,MapComponentInitializedListener {

    @FXML
    private Pane menu;
  //  private WebView map;
    @FXML
    private GoogleMapView gmap;
    private GoogleMap map;
    private GeocodingService G;
    private boolean ready;
    @FXML
    private ComboBox<String> Gouvernement;
    ObservableList<String> Gouvenements = FXCollections.observableArrayList("Grand Tunis","Beja","Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kassrine","Kebili","Le Kef","Mahdia","Médnine","Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse","Tataouine","Touzeur","Zaghouan");
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
    private Pane menu3;
    @FXML
    private Button recherche;
    @FXML
    private Button Maps;
    @FXML
    private Button Contacts;
    @FXML
    private ImageView profil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
      
    
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
     if(Util.connectedUser== null)
        {
            
        }
     else if(Util.connectedUser.getImg()==null)
                {
                    
                profil.setImage(new javafx.scene.image.Image("http://localhost/uimg/user.jpg"));
                }
                else
                {
                    profil.setImage(new javafx.scene.image.Image("http://localhost/uimg/"+Util.connectedUser.getImg()));
               
             
                }
    menu2.setVisible(false);
    menu3.setVisible(false);
       
       Gouvernement.setItems(Gouvenements);
       gmap.addMapInializedListener(this);
    /*   gmap =map.getEngine();
        gmap.load(getClass().getResource("map3.html").toExternalForm());
        ready = false;
        gmap.getLoadWorker().stateProperty().addListener(new  ChangeListener<Worker.State>()
                {
                    public void changed(final ObservableValue<? extends Worker.State> observableValue,
                                final Worker.State oldState,
                                final Worker.State newState)
            {
                if (newState == Worker.State.SUCCEEDED)
                {
                    ready = true;
                }
            }
                });
         */
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
              
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Utlisateur/ClientInterface.fxml"));
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
    public void createMap(){
          map = new GoogleMap();
        G = new GeocodingService();
        MapOptions mapOptions = new  MapOptions();
          mapOptions.center(new LatLong(33.8869, 9.5375))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(6);

        map = gmap.createMap(mapOptions);
    }
    @Override
    public void mapInitialized() {
       
        String gouv = Gouvernement.getValue();
        System.out.println(gouv);
         createMap();
        if(gouv == "Grand Tunis")
        {
            G.geocode("Rue jamaa Ezzitouna, Tunis", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });
              G.geocode("Rue Hedi Zarrouk, Carthage", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });  
        }
        if(gouv == "Nabeul")
        {
         G.geocode("Avenue Farhat Hached, Nabeul‎", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });
        G.geocode("La Medina Yasmine Hammamet, Yasmine Hammamet", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });
        G.geocode("Médina de Hammamet, Ruelle Ibrahim Chérif, Hammamet", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });
           
        }
       if(gouv == "Sidi Bouzid")
       {
           G.geocode("السوق الاسبوعية بسيدي بوزيد", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });

       }
           if(gouv == "Monastir")
           {
               G.geocode("Independence, Monastir", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });
           }
           if(gouv == "Kairouan" )
           {
             G.geocode("bab djalladine, Kairouan", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
           }
          if(gouv == "Bizerte" )
           {
             G.geocode("Rue de la Regence,Bizerte", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
              G.geocode("Rue Cheikh Idriss Chéerif,Bizerte", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
           }
             if(gouv == "Médnine" )
           {
             G.geocode("Houmt Souk", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);
                        }

            map.setCenter(latLong);
       });  
                G.geocode("Zarzis", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
               

       }
            if(gouv == "Kebili" )
           {
             G.geocode("Douz", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       }
         if(gouv == "Touzeur" )
           {
             G.geocode("Touzer", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       }
          if(gouv == "Gabes" )
           {
             G.geocode("Matmata", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
         G.geocode("Gabes", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });      

       }
           if(gouv == "Tataouine" )
           {
             G.geocode("souk el jomla,Tataouine", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       }
            if(gouv == "Gafsa" )
           {
             G.geocode("Gafsa", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       }
             if(gouv == "Beja" )
           {
             G.geocode("Testour,Beja,Tunisie", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
             G.geocode("Beja,Tunisie", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   

       }
              if(gouv == "Sousse" )
           {
             G.geocode("avenue Mohamed Ali,Sousse", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       }
                 if(gouv == "Mahdia" )
           {
             G.geocode("El Djem,Mahdia", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
          G.geocode("Bain Maure Hammam el souk,Mahdia", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });      

       }
        
              if(gouv == "Le Kef" )
           {
             G.geocode("Rue Hassan Harbouch,Le Kef", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       }   
        
              if(gouv == "Kassrine" )
           {
             G.geocode("Kassrine", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       }     
            if(gouv == "Zaghouan" )
           {
             G.geocode("Zaghouan", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       }   
           if(gouv == "Siliana" )
           {
             G.geocode("Siliana", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       } 
               if(gouv == "Jendouba" )
           {
             G.geocode("Jendouba", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       }  
          if(gouv == "Sfax" )
           {
             G.geocode("sidi dawed ,sfax", (GeocodingResult[] results, GeocoderStatus status) -> {

            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if (results.length > 1) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                System.out.println("result LG "+latLong.getLongitude()+ "   " +latLong.getLatitude());
                markerOptions.position(latLong)
                        .visible(Boolean.TRUE)
                        .title("My Marker");

                Marker marker = new Marker(markerOptions);
                map.addMarker(marker);

            }

            map.setCenter(latLong);
       });   
            

       }
    }

    @FXML
    private void btnChercherSouk(ActionEvent event) {
        mapInitialized();
        
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
    private void btnEventAction(ActionEvent event) {
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

    @FXML
    private void btnRechercheAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/RechercheContactInterface.fxml", event);
        
    }

    @FXML
    private void btnMapsAction(ActionEvent event) {
        LoadWindowParent("/GUI/Decouverte/Maps.fxml", event);
    }

    @FXML
    private void PanierFenetre(ActionEvent event) {
        LoadWindowParent("/GUI/Panier/FXMLPanierInterface.fxml",event);
    }
    
    private void LoadWindowParent(String loc,ActionEvent event){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(loc));
            
            Parent root = (Parent) loader.load();
           
             
            Stage window;
            window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(new Scene(root));
            window.setResizable(false);
            window.show();

           
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void btncataloguesAction(ActionEvent event) {
       LoadWindowParent("/GUI/Produits/AfficheListProduits.fxml", event);
    }

    @FXML
    private void btnBoutiquesAction(ActionEvent event) {
         LoadWindowParent("/GUI/Magasins/AfficheMagasins.fxml", event);
    }

    
}
