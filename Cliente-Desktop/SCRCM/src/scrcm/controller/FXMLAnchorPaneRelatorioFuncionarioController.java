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
import scrcm.domain.Funcionario;
import scrcm.service.FuncionarioService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneRelatorioFuncionarioController implements Initializable {
    @FXML
    private TableView<Funcionario> tableViewFuncionario;
    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncionario;
    @FXML
    private TableColumn<Funcionario, Integer> tableColumnEspecialidade;
    @FXML
    private TableColumn<Funcionario, Integer> tableColumnFuncao;
    
    private ObservableList<Funcionario> observableListFuncionario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ImprimirFuncionario();
        } catch (JRException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatorioFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneRelatorioFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void ImprimirFuncionario() throws JRException, IOException{
        
        FuncionarioService fs = new FuncionarioService();
        ArrayList<Funcionario> listFunc = new ArrayList<Funcionario>();
        String mensagem = "";               
        listFunc = fs.listarComNomes();
                
        if (listFunc == null || listFunc.isEmpty()) {
            mensagem = "Não existem Funcionários cadastradas";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (Funcionario funcionario : listFunc) {
                tableColumnFuncionario.setCellValueFactory(new PropertyValueFactory<>("nome"));
                tableColumnEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));    
                tableColumnFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao")); 
                observableListFuncionario = FXCollections.observableArrayList(listFunc);
                tableViewFuncionario.setItems(observableListFuncionario);
            }
        }
    }
    
    
}
