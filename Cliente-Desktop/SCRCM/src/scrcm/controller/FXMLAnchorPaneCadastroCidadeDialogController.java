/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scrcm.domain.Cidade;
import scrcm.domain.UF;
import scrcm.service.UFService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroCidadeDialogController implements Initializable {

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;

    Cidade cidade;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField textFieldFuncaoNome;    
    @FXML
    private ComboBox<UF> comboBoxUF;
    
    private List<UF> listUF;
    private UFService ufService = new UFService();
    private ObservableList<UF> observableListaUF;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            carregarComboBoxUF();            
            comboBoxUF.getSelectionModel().getSelectedItem();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroCidadeDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carregarComboBoxUF() throws IOException {        
        listUF = ufService.listar();
        observableListaUF = FXCollections.observableArrayList(listUF);
        comboBoxUF.setItems(observableListaUF);
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
        this.textFieldFuncaoNome.setText(cidade.getNomeCidade());  
        this.comboBoxUF.setValue(cidade.getuF());        
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    @FXML
    public void handleButtonConfirmar() {
        cidade.setNomeCidade(textFieldFuncaoNome.getText());
        cidade.setuF(comboBoxUF.getSelectionModel().getSelectedItem());
        btnConfirmarClicked = true;
        dialogStage.close();
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }

}
