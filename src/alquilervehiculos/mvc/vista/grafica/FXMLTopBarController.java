/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class FXMLTopBarController implements Initializable
{

    @FXML
    private ImageView img_clients, img_vehicles, img_rents, img_door;

    @FXML
    private AnchorPane v_clients, v_vehicles, v_rents;

    @FXML
    private Button btn_c_new, btn_c_search, btn_v_new, btn_v_search, btn_r_new, btn_r_search;

    @FXML
    private void handleBarButtonAction(MouseEvent event)
    {
        if (event.getTarget() == img_clients)
        {
            v_clients.setVisible(true);
            v_vehicles.setVisible(false);
            v_rents.setVisible(false);
        } else if (event.getTarget() == img_vehicles)
        {
            v_clients.setVisible(false);
            v_vehicles.setVisible(true);
            v_rents.setVisible(false);
        } else if (event.getTarget() == img_rents)
        {
            v_clients.setVisible(false);
            v_vehicles.setVisible(false);
            v_rents.setVisible(true);
        } else if (event.getTarget() == img_door)
        {
            v_clients.setVisible(false);
            v_vehicles.setVisible(false);
            v_rents.setVisible(false);
        }
    }

    @FXML
    private void handleScreenButtonsAction(ActionEvent event) throws IOException
    {
        if (event.getSource() == btn_c_new)
        {
            Parent parentNewClientsView = FXMLLoader.load(getClass().getResource("clients/FXMLClientsNew.fxml"));
            Scene newClientsScene = new Scene(parentNewClientsView);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(newClientsScene);
            stage.show();
        } else if (event.getSource() == btn_c_search)
        {
            Parent parentSearchClientsView = FXMLLoader.load(getClass().getResource("clients/FXMLClientSearch.fxml"));
            Scene searchClientsScene = new Scene(parentSearchClientsView);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(searchClientsScene);
            stage.show();
        } else if (event.getSource() == btn_v_new)
        {
            Parent parentSearchClientsView = FXMLLoader.load(getClass().getResource("vehicles/FXMLVehiclesNew.fxml"));
            Scene searchClientsScene = new Scene(parentSearchClientsView);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(searchClientsScene);
            stage.show();
        } else if (event.getSource() == btn_v_search)
        {
            Parent parentSearchClientsView = FXMLLoader.load(getClass().getResource("vehicles/FXMLVehiclesSearch.fxml"));
            Scene searchClientsScene = new Scene(parentSearchClientsView);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(searchClientsScene);
            stage.show();            
        } else if (event.getSource() == btn_r_new)
        {
            Parent parentSearchClientsView = FXMLLoader.load(getClass().getResource("rents/FXMLRentsNew.fxml"));
            Scene searchClientsScene = new Scene(parentSearchClientsView);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(searchClientsScene);
            stage.show();            
        } else if (event.getSource() == btn_r_search)
        {
            Parent parentSearchClientsView = FXMLLoader.load(getClass().getResource("rents/FXMLRentsSearch.fxml"));
            Scene searchClientsScene = new Scene(parentSearchClientsView);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(searchClientsScene);
            stage.show();            
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
