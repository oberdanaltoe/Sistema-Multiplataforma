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
import scrcm.domain.Cidade;
import scrcm.service.CidadeService;
/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneRelatoriosController implements Initializable {
    
    @FXML
    private TableView<Cidade> tableViewCidade;
    @FXML
    private TableColumn<Cidade, String> tableColumnCidade;
    @FXML
    private TableColumn<Cidade, Integer> tableColumnCodCidade;
    
    private ObservableList<Cidade> observableListCidade;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            handleImprimirCidades();
        } catch (JRException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatoriosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void handleImprimirCidades() throws JRException, IOException{
        CidadeService cidadeService = new CidadeService();
        ArrayList<Cidade> listCid = new ArrayList<Cidade>();
        String mensagem = "";
        listCid = cidadeService.listar();

        if (listCid == null || listCid.isEmpty()) {
            mensagem = "Não existem Cidades cadastradas";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (Cidade cidade : listCid) {
                tableColumnCodCidade.setCellValueFactory(new PropertyValueFactory<>("cdCidade"));
                tableColumnCidade.setCellValueFactory(new PropertyValueFactory<>("nomeCidade"));                             
                observableListCidade = FXCollections.observableArrayList(listCid);
                tableViewCidade.setItems(observableListCidade);
            }
        }
    }
    
    
    
    
}
