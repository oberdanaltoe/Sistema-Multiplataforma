/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scrcm.domain.Funcao;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroFuncaoDialogController implements Initializable {

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;    
    Funcao funcao;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCancelar;    
    @FXML
    private TextField textFieldFuncaoNome;
    
    public Stage getDialogStage() {
        return dialogStage;
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }
    
    public Funcao getFuncao() {
        return funcao;
    }
    
    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
        this.textFieldFuncaoNome.setText(funcao.getNomeFuncao());
    }
    
    @FXML
    public void handleButtonConfirmar() {
        funcao.setNomeFuncao(textFieldFuncaoNome.getText());
        btnConfirmarClicked = true;
        dialogStage.close();
    }
    
    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
    
}
