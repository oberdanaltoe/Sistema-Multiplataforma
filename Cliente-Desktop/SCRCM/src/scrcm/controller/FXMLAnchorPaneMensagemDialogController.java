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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scrcm.domain.Mensagens;

/**
 * FXML Controller class
 *
 * @author Oberdan
 */
public class FXMLAnchorPaneMensagemDialogController implements Initializable {

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Mensagens mensagens;
    
    @FXML
    private Button btnCadastrarAlterar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField textFieldTitulo;
    @FXML
    private TextArea textAreaMensagem;
    @FXML
    private DatePicker dataPickerExpira;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public Mensagens getMensagens() {
        return mensagens;
    }

    public void setMensagens(Mensagens mensagens) {
        this.mensagens = mensagens;
    }
    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
    
    public void handleButtonConfirmar() {        
        mensagens.setTitulo(textFieldTitulo.getText());
        mensagens.setMensagem(textAreaMensagem.getText());   
        mensagens.setDataExpira(java.sql.Date.valueOf(dataPickerExpira.getValue()));
        btnConfirmarClicked = true;
        dialogStage.close();
    }
}
