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
import scrcm.domain.TipoAbrigo;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroTipoAbrigoDialogController implements Initializable {

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private TipoAbrigo tipoAbrigo;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField textFieldTipoAbrigoNome;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public TipoAbrigo getTipoAbrigo() {
        return tipoAbrigo;
    }

    public void setTipoAbrigo(TipoAbrigo tipoAbrigo) {
        this.tipoAbrigo = tipoAbrigo;
        this.textFieldTipoAbrigoNome.setText(tipoAbrigo.getNomeAbrigo());
    }

    @FXML
    public void handleButtonConfirmar() {
        tipoAbrigo.setNomeAbrigo(textFieldTipoAbrigoNome.getText());
        btnConfirmarClicked = true;
        dialogStage.close();
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }

}
