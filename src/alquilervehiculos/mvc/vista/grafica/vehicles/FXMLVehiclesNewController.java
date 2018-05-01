/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.mvc.vista.grafica.vehicles;

import alquilervehiculos.mvc.modelo.dominio.Cliente;
import alquilervehiculos.mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import alquilervehiculos.mvc.modelo.dominio.vehiculo.TipoVehiculo;
import alquilervehiculos.mvc.modelo.dominio.vehiculo.Vehiculo;
import alquilervehiculos.mvc.vista.grafica.JavaFXMainStage;
import alquilervehiculos.mvc.vista.grafica.Mensajes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class FXMLVehiclesNewController implements Initializable
{

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField tFTipo, tFMarca, tFModelo, tFMatricula, tFCilindrada, tFNumeroP, tFPma, tFBuscar;

    @FXML
    private Button btn_accept, btn_cancel;

    @FXML
    private void handleScreenButtonsAction(ActionEvent event) throws IOException
    {
        if (event.getSource() == btn_accept)
        {
            try
            {
                Vehiculo vehiculo = null;
                JavaFXMainStage.controlador.anadirVehiculo(vehiculo = TipoVehiculo.getTipoVehiculoSegunOrdinal(Integer.parseInt(tFTipo.getText())).getInstancia(tFMatricula.getText(), tFMarca.getText(), tFModelo.getText(), Integer.parseInt(tFCilindrada.getText()), Integer.parseInt(tFNumeroP.getText()), Integer.parseInt(tFPma.getText())));
                Mensajes.mostrarInfo("Vehiculos", "Vehiuclo añadido");
                JavaFXMainStage.controlador.modelo.escribirVehiculos();
            } catch (ExcepcionAlquilerVehiculos e)
            {
                Mensajes.mostrarError("Vehiculos", e.getMessage());
            }
        } else if (event.getSource()
                == btn_cancel)
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
