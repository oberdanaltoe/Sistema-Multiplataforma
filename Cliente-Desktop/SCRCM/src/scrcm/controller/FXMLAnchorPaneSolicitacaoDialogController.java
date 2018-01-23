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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import scrcm.domain.SolicitacaoVisita;
import scrcm.domain.TipoAbrigo;
import scrcm.domain.TipoAbrigoSolicitacao;
import scrcm.domain.UsuarioComum;
import scrcm.service.TipoAbrigoService;
import scrcm.service.UsuarioComumService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneSolicitacaoDialogController implements Initializable {

    private SolicitacaoVisita solicitacaoVisita = new SolicitacaoVisita();
    @FXML
    private Button btnEnviar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAddAbrigo;
    @FXML
    private Button btnFoto;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnRemoveAbrigo;
    @FXML
    private ComboBox<String> comboxPossuiProdiedade;
    @FXML
    private MaskTextField textFieldMediaAnimaisPropriedade;
    @FXML
    private TextArea textAreaObservacoes;
    @FXML
    private MaskTextField textFieldAnimaisMordidos;
    @FXML
    private ComboBox<String> comboxCasosMorteRegiao;
    @FXML
    private ComboBox<String> comboxPropriedadeLocaisProximos;
    @FXML
    private ComboBox<String> comboxTempoOcorridoMorte;
    @FXML
    private ComboBox<String> comboxConheceAbrigo;
    @FXML
    private ComboBox<String> comboxSolicitarRecolhimentoCer;
    @FXML
    private ComboBox<TipoAbrigo> comboxTipoAbrigo;
    @FXML
    private ComboBox<UsuarioComum> comboxUsuarioComum;
    @FXML
    private ListView<TipoAbrigo> listViewAbrigos;
    @FXML
    private Label pergunta01;
    @FXML
    private Label pergunta02;
    @FXML
    private Label pergunta03;
    @FXML
    private Label pergunta04;
    @FXML
    private Label pergunta05;
    @FXML
    private Label pergunta06;
    @FXML
    private Label pergunta07;
    @FXML
    private Label pergunta08;
    @FXML
    private Label pergunta09;
    @FXML
    private Label pergunta10;
    @FXML
    private Label pergunta11;
    @FXML
    private Label pergunta12;

    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;

    private List<String> listSimNao;
    private ObservableList<String> observableListSimNao;
    private ObservableList<String> observableListProLoc;
    private ObservableList<String> observableLisTempoMorte;
    private ObservableList<TipoAbrigo> observableLisTipoAbrigoCombobox;
    private ObservableList<TipoAbrigo> observableLisTipoAbrigoListView;
    private List<TipoAbrigo> listTipoAbrigoCB;
    private List<TipoAbrigo> listTipoAbrigoList = new ArrayList<>();
    private TipoAbrigoService tas = new TipoAbrigoService();
    UsuarioComumService ucs = new UsuarioComumService();
    List<TipoAbrigoSolicitacao> ltas = new ArrayList<>();
    private List<UsuarioComum> listUsuarioComumCB;
    private ObservableList<UsuarioComum> observableLisUsuarioComumCombobox;
    private ArrayList<TipoAbrigoSolicitacao> tiposAbrigos;

    private Date dataSolcitacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date data = new Date(System.currentTimeMillis());
        dataSolcitacao = data;
        textFieldAnimaisMordidos.setMask("NNNNN");
        textFieldMediaAnimaisPropriedade.setMask("NNNNN");
        try {
            carregarComboBox();
            carregarLabelPerguntas();
            carregarComboBoxTipoAbrigo();
            carregarComboBoxUsuario();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneSolicitacaoDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    public void conheceAbrigo() {
        if (comboxConheceAbrigo.getSelectionModel().getSelectedIndex() != -1) {
            if (comboxConheceAbrigo.getSelectionModel().getSelectedItem().equals("Sim")) {
                comboxTipoAbrigo.setVisible(true);
                listViewAbrigos.setVisible(true);
                btnAddAbrigo.setVisible(true);
                btnRemoveAbrigo.setVisible(true);
            } else if (comboxConheceAbrigo.getSelectionModel().getSelectedItem().equals("Não")) {
                comboxTipoAbrigo.setVisible(false);
                listViewAbrigos.setVisible(false);
                btnAddAbrigo.setVisible(false);
                btnRemoveAbrigo.setVisible(false);
            }
        }
    }

    public void casosMorte() {
        if (comboxCasosMorteRegiao.getSelectionModel().getSelectedIndex() != -1) {
            if (comboxCasosMorteRegiao.getSelectionModel().getSelectedItem().equals("Sim")) {
                comboxPropriedadeLocaisProximos.setVisible(true);
                comboxTempoOcorridoMorte.setVisible(true);
                comboxSolicitarRecolhimentoCer.setVisible(true);
                pergunta05.setVisible(true);
                pergunta06.setVisible(true);
                pergunta09.setVisible(true);
            } else if (comboxCasosMorteRegiao.getSelectionModel().getSelectedItem().equals("Não")) {
                comboxPropriedadeLocaisProximos.setVisible(false);
                comboxTempoOcorridoMorte.setVisible(false);
                comboxSolicitarRecolhimentoCer.setVisible(false);
                pergunta05.setVisible(false);
                pergunta06.setVisible(false);
                pergunta09.setVisible(false);
            }
        }
    }

    public void carregarComboBox() throws IOException {
        observableListSimNao = FXCollections.observableArrayList("Sim", "Não");
        observableListProLoc = FXCollections.observableArrayList("Propriedade", "Regiões Proximas");
        observableLisTempoMorte = FXCollections.observableArrayList("Menos de uma Semana", "Duas Semanas", "Mais de um mês");
        comboxPossuiProdiedade.setItems(observableListSimNao);
        comboxCasosMorteRegiao.setItems(observableListSimNao);
        comboxConheceAbrigo.setItems(observableListSimNao);
        comboxSolicitarRecolhimentoCer.setItems(observableListSimNao);
        comboxPropriedadeLocaisProximos.setItems(observableListProLoc);
        comboxTempoOcorridoMorte.setItems(observableLisTempoMorte);
    }

    public void carregarComboBoxTipoAbrigo() throws IOException {
        listTipoAbrigoCB = tas.listar();
        observableLisTipoAbrigoCombobox = FXCollections.observableArrayList(listTipoAbrigoCB);
        comboxTipoAbrigo.setItems(observableLisTipoAbrigoCombobox);

    }

    public void carregarComboBoxUsuario() throws IOException {
        listUsuarioComumCB = ucs.listar();
        observableLisUsuarioComumCombobox = FXCollections.observableArrayList(listUsuarioComumCB);
        comboxUsuarioComum.setItems(observableLisUsuarioComumCombobox);

    }

    public void carregarLabelPerguntas() throws IOException {
        pergunta01.setText("Possui Propriedade?");
        pergunta02.setText("Quantidade media de animais da propriedade?");
        pergunta03.setText("Quantos animais foram mordidos por morcegos?");
        pergunta04.setText("Houve casos de morte de animais por raiva na região?");
        pergunta05.setText("Na propriedade ou locais próximos?");
        pergunta06.setText("Quanto tempo do ocorrido da morte aproximadamente?");
        pergunta07.setText("Existe o conhecimento sobre abrigo de morcegos na propriedade ou em locais próximos?");
        pergunta08.setText("Tipos de abrigo:");
        pergunta09.setText("Deseja solicitar recolhimento de cérebro para exames para comprovação da raiva?");
        pergunta10.setText("Observações:");
        pergunta11.setText("Produto Rural:");
        pergunta12.setText("Enviar Fotos:");

    }

    public void handlleAdicionar() throws IOException {
        TipoAbrigo tipoAbrigo = new TipoAbrigo();
        tipoAbrigo = comboxTipoAbrigo.getValue();
        if (tipoAbrigo != null) {
            listTipoAbrigoCB.remove(tipoAbrigo);
            listTipoAbrigoList.add(tipoAbrigo);
            observableLisTipoAbrigoCombobox.remove(tipoAbrigo);
            observableLisTipoAbrigoListView = FXCollections.observableArrayList(listTipoAbrigoList);
            listViewAbrigos.setItems(observableLisTipoAbrigoListView);
            TipoAbrigoSolicitacao tipoAbrigoSolicitacao = new TipoAbrigoSolicitacao();
            tipoAbrigoSolicitacao.settAbrigo(tipoAbrigo);
            ltas.add(tipoAbrigoSolicitacao);
            solicitacaoVisita.setListTipoAbrigoSolicitacao(ltas);
        }
    }

    public void handlleRemover() throws IOException {
        TipoAbrigo tipoAbrigo = new TipoAbrigo();
        tipoAbrigo = listViewAbrigos.getSelectionModel().getSelectedItem();
        if (tipoAbrigo != null) {
            listTipoAbrigoList.remove(tipoAbrigo);
            listTipoAbrigoCB.add(tipoAbrigo);
            observableLisTipoAbrigoCombobox.add(tipoAbrigo);
            observableLisTipoAbrigoListView.remove(tipoAbrigo);
            observableLisTipoAbrigoListView = FXCollections.observableArrayList(listTipoAbrigoList);
            observableLisTipoAbrigoCombobox = FXCollections.observableArrayList(listTipoAbrigoCB);
            comboxTipoAbrigo.setItems(observableLisTipoAbrigoCombobox);
            listViewAbrigos.setItems(observableLisTipoAbrigoListView);
            ltas.clear();
            for (TipoAbrigo ta : listTipoAbrigoList) {
                TipoAbrigoSolicitacao tipoAbrigoSolicitacao = new TipoAbrigoSolicitacao();
                tipoAbrigoSolicitacao.settAbrigo(ta);
                ltas.add(tipoAbrigoSolicitacao);
            }
            solicitacaoVisita.setListTipoAbrigoSolicitacao(ltas);
        }

    }

    public void handleLimparTela() throws IOException {
        observableLisTempoMorte.clear();
        observableLisTipoAbrigoCombobox.clear();
        observableLisTipoAbrigoListView.clear();
        listTipoAbrigoList.clear();
        observableListProLoc.clear();
        observableListSimNao.clear();
        listViewAbrigos.getItems().clear();
        conheceAbrigo();
        carregarComboBoxTipoAbrigo();
        carregarComboBoxUsuario();
        carregarComboBox();
        casosMorte();
        textAreaObservacoes.setText("");
        textFieldAnimaisMordidos.setText("");
        textFieldMediaAnimaisPropriedade.setText("");
        comboxPropriedadeLocaisProximos.setVisible(false);
        comboxTempoOcorridoMorte.setVisible(false);
        comboxSolicitarRecolhimentoCer.setVisible(false);
        pergunta05.setVisible(false);
        pergunta06.setVisible(false);
        pergunta09.setVisible(false);
        comboxTipoAbrigo.setVisible(false);
        listViewAbrigos.setVisible(false);
        btnAddAbrigo.setVisible(false);
        btnRemoveAbrigo.setVisible(false);
    }

    public SolicitacaoVisita getSolicitacaoVisita() {
        return solicitacaoVisita;
    }

    public void setSolicitacaoVisita(SolicitacaoVisita solicitacaoVisita) {
        this.solicitacaoVisita = solicitacaoVisita;
    }

    @FXML
    public void handleButtonConfirmar() throws IOException {
        boolean teste;
        teste = VerificaCombobox();
        if (teste) {
            solicitacaoVisita.setPossuiPropriedade(comboxPossuiProdiedade.getSelectionModel().getSelectedItem());
            solicitacaoVisita.setStatus(1);
            solicitacaoVisita.setQtdMediaAnimais(Integer.parseInt(textFieldMediaAnimaisPropriedade.getText()));
            solicitacaoVisita.setQtdAnimaisMordidos(Integer.parseInt(textFieldAnimaisMordidos.getText()));
            solicitacaoVisita.setCasosMorteRegiao(comboxCasosMorteRegiao.getSelectionModel().getSelectedItem());
            solicitacaoVisita.setProprieLocaisProximos(comboxPropriedadeLocaisProximos.getSelectionModel().getSelectedItem());
            solicitacaoVisita.setTempoOcorridoMorte(comboxTempoOcorridoMorte.getSelectionModel().getSelectedItem());
            solicitacaoVisita.setConheceAbrigo(comboxConheceAbrigo.getSelectionModel().getSelectedItem());
            solicitacaoVisita.setSolicitarRecolhimento(comboxSolicitarRecolhimentoCer.getSelectionModel().getSelectedItem());
            solicitacaoVisita.setObservacoes(textAreaObservacoes.getText());
            solicitacaoVisita.setUsuarioComum(comboxUsuarioComum.getSelectionModel().getSelectedItem());
            solicitacaoVisita.setDataSolcitacao(dataSolcitacao);
            solicitacaoVisita.setFoto(" Em produção ");
            solicitacaoVisita.setListTipoAbrigoSolicitacao(ltas);

            btnConfirmarClicked = true;
            dialogStage.close();
        }
    }

    public boolean VerificaCombobox() {
        boolean conferido = false;
        if (comboxPossuiProdiedade.getSelectionModel().getSelectedIndex() == -1) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else if (comboxUsuarioComum.getSelectionModel().getSelectedIndex() == -1) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else if (textFieldAnimaisMordidos.getText().equals("")) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else if (textFieldMediaAnimaisPropriedade.getText().equals("")) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else if (textAreaObservacoes.getText().equals("")) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else if (comboxConheceAbrigo.getSelectionModel().getSelectedIndex() == -1) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else if (comboxConheceAbrigo.getSelectionModel().getSelectedItem().equals("Sim")) {
            if (listTipoAbrigoList.isEmpty()) {
                ImprimiAlertaVerificacao();
                conferido = false;
            } else {
                conferido = true;
            }
        } else if (comboxCasosMorteRegiao.getSelectionModel().getSelectedIndex() == -1) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else if (comboxCasosMorteRegiao.getSelectionModel().getSelectedItem().equals("Sim")) {
            if (comboxPropriedadeLocaisProximos.getSelectionModel().getSelectedIndex() == -1) {
                ImprimiAlertaVerificacao();
                conferido = false;
            } else if (comboxTempoOcorridoMorte.getSelectionModel().getSelectedIndex() == -1) {
                ImprimiAlertaVerificacao();
                conferido = false;
            } else if (comboxSolicitarRecolhimentoCer.getSelectionModel().getSelectedIndex() == -1) {
                ImprimiAlertaVerificacao();
                conferido = false;
            } else {
                conferido = true;
            }
        } else {
            conferido = true;
        }
        if (conferido != true) {
            String mensagem = "";
            mensagem = "Conferido == false!!!";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        }
        return conferido;
    }

    public void ImprimiAlertaVerificacao() {
        String mensagem = "";
        mensagem = "Existem campos NÃO preenchidos ou selecionados!!!";
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensagem);
        alerta.show();
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
}
