/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import scrcm.domain.UsuarioComum;
import scrcm.service.UsuarioComumService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneRelatorioUsuariosController implements Initializable {

    @FXML
    private TableView<UsuarioComum> tableViewUsuario;
    @FXML
    private TableColumn<UsuarioComum, String> tabelColumnNome;
    @FXML
    private TableColumn<UsuarioComum, String> tabelColumnRua;
    @FXML
    private TableColumn<UsuarioComum, String> tableColumnReferencia;
    @FXML
    private TableColumn<UsuarioComum, String> tablecolumnBairro;
    @FXML
    private TableColumn<UsuarioComum, Integer> tabelColumnNumero;
    
    private ObservableList<UsuarioComum> observableListUsuarioComum;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ImprimirUsuario();
        } catch (JRException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatorioUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatorioUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void ImprimirUsuario() throws JRException, IOException{
        UsuarioComumService ucs = new UsuarioComumService();
        ArrayList<UsuarioComum> listUsers = new ArrayList<UsuarioComum>();
        String mensagem = "";
        listUsers = ucs.listarUsuarios();

        if (listUsers == null || listUsers.isEmpty()) {
            mensagem = "Não existem Usuarios cadastrados";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (UsuarioComum comum : listUsers) {
                tableColumnReferencia.setCellValueFactory(new PropertyValueFactory<>("pontoReferencia"));
                tablecolumnBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));                             
                tabelColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome")); 
                tabelColumnRua.setCellValueFactory(new PropertyValueFactory<>("rua")); 
                tabelColumnNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));                 
                observableListUsuarioComum = FXCollections.observableArrayList(listUsers);
                tableViewUsuario.setItems(observableListUsuarioComum);
            }
        }
    }
    
}
