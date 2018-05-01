/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica.clients;

import alquilervehiculos.mvc.modelo.dominio.Cliente;
import alquilervehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilervehiculos.mvc.vista.grafica.JavaFXMainStage;
import alquilervehiculos.mvc.vista.grafica.Mensajes;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLClientSearchController implements Initializable
{

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField tFId, tFNombre, tFDni, tFCalle, tFLocalidad, tFCodigoP, tFBuscar;

    @FXML
    private ListView<String> lista;

    @FXML
    private Button btn_accept, btn_cancel, btn_delete;

    @FXML
    private void handleScreenButtonsAction(ActionEvent event) throws IOException
    {
        if (event.getSource() == btn_accept)
        {
            if (Cliente.formatoDniCorrecto(tFBuscar.getText()))
            {
                Cliente cliente = JavaFXMainStage.controlador.buscarCliente(tFBuscar.getText());
                tFId.setText("Cliente -" + String.valueOf(cliente.getIdentificador() + "-"));
                tFNombre.setText(cliente.getNombre());
                tFDni.setText(cliente.getDni());
                tFCalle.setText(cliente.getDireccionPostal().getCalle());
                tFLocalidad.setText(cliente.getDireccionPostal().getLocalidad());
                tFCodigoP.setText(cliente.getDireccionPostal().getCodigoPostal());
            } else
            {
                Mensajes.mostrarError("Clientes", "DNI incorrecto.");
            }
        } else if (event.getSource() == btn_delete)
        {
            String dniString = lista.getSelectionModel().getSelectedItem().toString();
            JavaFXMainStage.controlador.borrarCliente(dniString);
            Mensajes.mostrarInfo("Clientes", "Eliminado.");
            listarClientes();
        } else if (event.getSource() == btn_cancel)
        {
            Parent root1 = FXMLLoader.load(getClass().getResource("../FXMLTopBar.fxml"));
            Scene scene1 = new Scene(root1);
            scene1.setFill(javafx.scene.paint.Color.TRANSPARENT);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root1.setOnMousePressed(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            }
            );

            root1.setOnMouseDragged(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent event)
                {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            }
            );
            stage.setScene(scene1);
            stage.show();
        }
    }

    @FXML
    private void listClicked(MouseEvent event)
    {
        String dniString = lista.getSelectionModel().getSelectedItem().toString();
        try
        {
            Cliente cliente = JavaFXMainStage.controlador.buscarCliente(dniString);
            tFId.setText("Cliente -" + String.valueOf(cliente.getIdentificador() + "-"));
            tFNombre.setText(cliente.getNombre());
            tFDni.setText(cliente.getDni());
            tFCalle.setText(cliente.getDireccionPostal().getCalle());
            tFLocalidad.setText(cliente.getDireccionPostal().getLocalidad());
            tFCodigoP.setText(cliente.getDireccionPostal().getCodigoPostal());
        } catch (ExcepcionAlquilerVehiculos e)
        {
            Mensajes.mostrarError("Clientes", e.getMessage());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        listarClientes();
    }

    private void listarClientes()
    {
        ObservableList<String> clientes = FXCollections.observableArrayList();
        for (Cliente cliente : JavaFXMainStage.controlador.obtenerClientes())
        {
            clientes.add(cliente.getDni());
        }
        lista.setItems(clientes);
    }

}
