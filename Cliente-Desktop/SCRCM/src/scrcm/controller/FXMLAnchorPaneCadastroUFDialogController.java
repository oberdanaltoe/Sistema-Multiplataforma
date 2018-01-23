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
import scrcm.domain.UF;


/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroUFDialogController implements Initializable {

    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField textFieldUFNome;
    @FXML
    private TextField textFieldUFSigla;
    
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    UF uf ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
        this.textFieldUFNome.setText(uf.getNomeUF());
        this.textFieldUFSigla.setText(uf.getSigla());
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }
    
    @FXML
    public void handleButtonConfirmar() {
        uf.setNomeUF(textFieldUFNome.getText());
        uf.setSigla(textFieldUFSigla.getText());
        btnConfirmarClicked = true;
        dialogStage.close();
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
    
}
