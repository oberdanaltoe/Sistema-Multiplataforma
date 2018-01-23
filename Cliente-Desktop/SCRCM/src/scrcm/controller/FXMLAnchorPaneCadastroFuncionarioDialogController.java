/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import scrcm.domain.Especialidade;
import scrcm.domain.Funcao;
import scrcm.domain.Funcionario;
import scrcm.service.EspecialidadeService;
import scrcm.service.FuncaoService;
/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroFuncionarioDialogController implements Initializable {

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Funcionario funcionario;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField textFieldNomeFuncionario;
    @FXML
    private TextField textFieldLoginFuncionario;
    @FXML
    private TextField textFieldCPFFuncionario;
    @FXML
    private TextField textFieldSenhaFuncionario;    
    @FXML
    private ComboBox<Funcao> comboBoxFuncao;
    @FXML
    private ComboBox<Especialidade> comboBoxEspecialidade;
    
    private List<Funcao> listFuncao;
    private FuncaoService funcaoService = new FuncaoService();
    private ObservableList<Funcao> observableListaFuncao;
    
    private List<Especialidade> listEspecialidade;
    private EspecialidadeService especialidadeService = new EspecialidadeService();
    private ObservableList<Especialidade> observableListaEspecialidade;
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            carregarComboBoxFuncaoEspecialidade();
            comboBoxEspecialidade.getSelectionModel().getSelectedItem();
            comboBoxFuncao.getSelectionModel().getSelectedItem();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroFuncionarioDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void carregarComboBoxFuncaoEspecialidade() throws IOException {        
        listEspecialidade = especialidadeService.listar();
        observableListaEspecialidade = FXCollections.observableArrayList(listEspecialidade);
        comboBoxEspecialidade.setItems(observableListaEspecialidade);        
        listFuncao = funcaoService.listar();
        observableListaFuncao = FXCollections.observableArrayList(listFuncao);
        comboBoxFuncao.setItems(observableListaFuncao);
        
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        this.textFieldNomeFuncionario.setText(funcionario.getNome());  
        this.textFieldCPFFuncionario.setText(funcionario.getCpf());  
        this.textFieldLoginFuncionario.setText(funcionario.getLogin());  
        this.textFieldSenhaFuncionario.setText(funcionario.getSenha());  
        this.comboBoxEspecialidade.setValue(funcionario.getEspecialidade());
        this.comboBoxFuncao.setValue(funcionario.getFuncao());
    }
    
    @FXML
    public void handleButtonConfirmar() {
        funcionario.setNome(textFieldNomeFuncionario.getText());
        funcionario.setCpf(textFieldCPFFuncionario.getText());
        funcionario.setLogin(textFieldLoginFuncionario.getText());
        funcionario.setSenha(textFieldSenhaFuncionario.getText());
        funcionario.setFuncao(comboBoxFuncao.getSelectionModel().getSelectedItem());
        funcionario.setEspecialidade(comboBoxEspecialidade.getSelectionModel().getSelectedItem());        
        btnConfirmarClicked = true;
        dialogStage.close();
    }
    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }
    
    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
    
}
