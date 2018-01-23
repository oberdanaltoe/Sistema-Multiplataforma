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
import javafx.mascara.util.MaskTextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import scrcm.domain.Bairro;
import scrcm.domain.Cidade;
import scrcm.service.CidadeService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroBairroDialogController implements Initializable {

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;
    private Bairro bairro;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCancelar;
    @FXML
    private MaskTextField textFieldNomeBairro;    
    @FXML
    private ComboBox<Cidade> comboBoxCidade;
    
    private List<Cidade> listCidade;
    private CidadeService cidadeService = new CidadeService();
    private ObservableList<Cidade> observableListaCidade;
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldNomeBairro.setMask("L!*!");
        try {
            // TODO
            carregarComboBoxCidade();
            comboBoxCidade.getSelectionModel().getSelectedItem();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroBairroDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void carregarComboBoxCidade() throws IOException {        
        listCidade = cidadeService.listar();
        observableListaCidade = FXCollections.observableArrayList(listCidade);
        comboBoxCidade.setItems(observableListaCidade);
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
        this.textFieldNomeBairro.setText(bairro.getNomeBairro());  
        this.comboBoxCidade.setValue(bairro.getCidade());
    }
    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    @FXML
    public void handleButtonConfirmar() {
        bairro.setNomeBairro(textFieldNomeBairro.getText());
        bairro.setCidade(comboBoxCidade.getSelectionModel().getSelectedItem());        
        btnConfirmarClicked = true;
        dialogStage.close();
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
}
