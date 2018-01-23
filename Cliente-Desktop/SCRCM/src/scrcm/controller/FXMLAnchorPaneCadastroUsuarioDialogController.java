/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scrcm.domain.Bairro;
import scrcm.domain.Cidade;
import scrcm.domain.UsuarioComum;
import scrcm.service.BairroService;
import scrcm.service.CidadeService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroUsuarioDialogController implements Initializable {

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;

    private UsuarioComum usuarioComum;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private MaskTextField textFieldCPF;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldSenha;
    @FXML
    private TextField textFieldRua;
    @FXML
    private MaskTextField textFieldNumero;
    @FXML
    private TextField textFieldPontoReferencia;
    @FXML
    private MaskTextField textFieldTelefone;
    @FXML
    private ComboBox<Cidade> comboBoxCidade;
    @FXML
    private ComboBox<Bairro> comboBoxBairro;

    CidadeService cs = new CidadeService();
    BairroService bs = new BairroService();
    private ObservableList<Cidade> observableLisCidade;
    private List<Cidade> listCidadeCB = new ArrayList<>();
    private ObservableList<Bairro> observableLisBairro;
    private List<Bairro> listBairroCB;
    
    private Date dataCadastro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldNumero.setMask("NNNNN");
        textFieldCPF.setMask("NNNNNNNNNNN");
        textFieldTelefone.setMask("NNNNNNNNNNNNNN");
        Date data = new Date(System.currentTimeMillis());
        dataCadastro = data;
        try {
            carregarComboBoxCidade();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroUsuarioDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarComboBoxCidade() throws IOException {
        listCidadeCB = cs.listar();
        observableLisCidade = FXCollections.observableArrayList(listCidadeCB);
        comboBoxCidade.setItems(observableLisCidade);

    }

    public void selecionarComboboxCidade() throws IOException {
        Cidade cidade = comboBoxCidade.getValue();
        Bairro bairro = new Bairro();
        bairro.setCidade(cidade);
        listBairroCB = (List<Bairro>) bs.buscarPorCidade(bairro);
        observableLisBairro = FXCollections.observableArrayList(listBairroCB);
        comboBoxBairro.setItems(observableLisBairro);

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

    public UsuarioComum getUsuarioComum() {
        return usuarioComum;
    }

    public void setUsuarioComum(UsuarioComum uc) {
        this.usuarioComum = uc;
        if (uc.getCdUsuarioComum() != 0) {
            this.textFieldNome.setText(uc.getNome());
            this.textFieldLogin.setText(uc.getLogin());
            this.textFieldSenha.setText(uc.getSenha());
            this.textFieldCPF.setText(uc.getCpf());
            this.textFieldNumero.setText(String.valueOf(uc.getNumero()));
            this.textFieldRua.setText(uc.getRua());
            this.textFieldPontoReferencia.setText(uc.getPontoReferencia());
            this.comboBoxBairro.setValue(uc.getBairro());
            this.textFieldEmail.setText(uc.getEmail());
            this.textFieldTelefone.setText(uc.getTelefone());
            uc.setCidade(uc.getBairro().getCidade());
            this.comboBoxCidade.setValue(uc.getCidade());
        }
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }

    public void handleButtonConfirmar() {
        usuarioComum.setNome(textFieldNome.getText());
        usuarioComum.setCpf(textFieldCPF.getText());
        usuarioComum.setLogin(textFieldLogin.getText());
        usuarioComum.setSenha(textFieldSenha.getText());
        usuarioComum.setRua(textFieldRua.getText());
        usuarioComum.setNumero(Integer.parseInt(textFieldNumero.getText()));
        usuarioComum.setPontoReferencia(textFieldPontoReferencia.getText());
        usuarioComum.setBairro(comboBoxBairro.getSelectionModel().getSelectedItem());
        usuarioComum.setDataCadastro(dataCadastro);
        usuarioComum.setTelefone(textFieldTelefone.getText());   
        usuarioComum.setEmail(textFieldEmail.getText());
        btnConfirmarClicked = true;
        dialogStage.close();
    }

}
