package com.example.examen2tdi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;
import utils.MYSQLConnection;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private RadioButton rb_mujer;
    @FXML
    private RadioButton rb_hombre;
    @FXML
    private TextField tf_kilos;
    @FXML
    private ComboBox<String> cb_actividad;
    @FXML
    private TextField tf_edad;
    @FXML
    private TextField tf_talla;
    @FXML
    private TextArea ta_observaciones;
    @FXML
    private TextField tf_nombre;
    @FXML
    private Button btn_guardar;
    @FXML
    private Label lbl_info;
    @FXML
    private Button btn_descargar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ObservableList<String> elementos = FXCollections.observableArrayList();
        elementos.addAll("Sedentario", "Moderado", "Activo", "Muy activo");
        cb_actividad.setItems(elementos);

        cb_actividad.getSelectionModel().selectFirst();



        ToggleGroup tb = new ToggleGroup();

        this.rb_mujer.setToggleGroup(tb);
        this.rb_hombre.setToggleGroup(tb);



    }

    @FXML
    public void guardar(ActionEvent actionEvent) {


        if(!tf_nombre.getText().isEmpty() &&
           !tf_kilos.getText().isEmpty() &&
           !tf_edad.getText().isEmpty() &&
           !tf_talla.getText().isEmpty() &&
           !ta_observaciones.getText().isEmpty()) {

            Double ger = 0.0;
            Double get = 0.0;

            String nombre = this.tf_nombre.getText();
            String sexo = " ";
            if (this.rb_mujer.isSelected()) {

                sexo = "Mujer";

            } else {

                sexo = "Hombre";

            }
            Integer peso = Integer.valueOf(this.tf_kilos.getText());
            Integer edad = Integer.valueOf(this.tf_edad.getText());
            Integer talla = Integer.valueOf(this.tf_talla.getText());

            String actividad = this.cb_actividad.getValue();
            String observaciones = this.ta_observaciones.getText();


            if (sexo.equals("Mujer")) {

                ger = (double) Math.round(655.0955 + 9.463 * peso + 1.8496 * talla - 4.6756 * edad);

            } else {

                ger = (double) Math.round(66.473 + 13.751 * peso + 5.0033 * talla - 6.755 * edad);

            }

            if (actividad.equals("Sedentario")) {

                get = ger * 1.3;


            } else if (actividad.equals("Moderado")) {

                if (sexo.equals("Mujer")) {

                    get = ger * 1.5;

                } else {

                    get = ger * 1.6;

                }

            } else if (actividad.equals("Activo")) {

                if (sexo.equals("Mujer")) {

                    get = ger * 1.6;

                } else {

                    get = ger * 1.7;

                }

            } else if (actividad.equals("Muy activo")) {

                if (sexo.equals("Mujer")) {

                    get = ger * 1.9;

                } else {

                    get = ger * 2.1;

                }
            }


            this.lbl_info.setText("El cliente " + nombre + " tiene un  GER de " + ger +
                    " y un GET de " + get);

        }else{

            this.lbl_info.setText("TODOS LOS CAMPOS DEBEN ESTAR LLENOS O SELECCIONADOS.");

        }


    }


    @FXML
    public void descargar(ActionEvent actionEvent) {

        Connection conexion= MYSQLConnection.getConexion();
        try {
            JasperPrint jasper= JasperFillManager.fillReport("lista.jasper", new HashMap<>(),conexion);
            JRViewer visor=new JRViewer(jasper);
            JFrame ventana=new JFrame("Listado de clientes");
            ventana.getContentPane().add(visor);
            ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ventana.pack();
            ventana.setVisible(true);
            JRPdfExporter exp=new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasper));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("lista.pdf"));
            exp.setConfiguration(new SimplePdfExporterConfiguration());
            exp.exportReport();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

    }
}