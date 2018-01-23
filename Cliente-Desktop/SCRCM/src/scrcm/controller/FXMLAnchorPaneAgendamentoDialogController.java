/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import scrcm.domain.AgendamentoVisita;
import scrcm.domain.Bairro;
import scrcm.domain.Cidade;
import scrcm.domain.Endereco;
import scrcm.domain.Funcionario;
import scrcm.domain.FuncionarioAgenda;
import scrcm.domain.SolicitacaoVisita;
import scrcm.domain.UsuarioComum;
import scrcm.service.AgendamentoVisitaService;
import scrcm.service.BairroService;
import scrcm.service.CidadeService;
import scrcm.service.FuncionarioAgendaService;
import scrcm.service.FuncionarioService;
import scrcm.service.SolicitacaoVisitaService;
import scrcm.service.UsuarioComumService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneAgendamentoDialogController implements Initializable {

    @FXML
    private TextField textFieldNumero;
    @FXML
    private TextField textFieldRua;
    @FXML
    private TextField textFieldBairro;
    @FXML
    private TextField textFieldCidade;
    @FXML
    private TextField textFieldProdutor;
    @FXML
    private DatePicker datePickerAgendamento;
    @FXML
    private ComboBox<String> comboxTipoVisita;
    @FXML
    private ComboBox<Cidade> comboxCidade;
    @FXML
    private ComboBox<Bairro> comboxBairro;
    @FXML
    private ComboBox<Funcionario> comboxFuncionarios;
    @FXML
    private ComboBox<SolicitacaoVisita> comboxSolicitacao;
    @FXML
    private Label labelRua;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnRemoveFunconario;
    @FXML
    private Button btnCancelar;
    @FXML
    private ListView<SolicitacaoVisita> listViewSolicitacaoVisita;
    @FXML
    private ListView<Funcionario> listViewFuncionarios;
    @FXML
    private TableView<SolicitacaoVisita> tableViewSolicitacao;
    @FXML
    private TableColumn<SolicitacaoVisita, String> tableColumnSolicitacao;
    @FXML
    private Button btnAddFunconario;

    private ObservableList<String> observableListTipoVisitas;
    private Stage dialogStage;
    private boolean btnConfirmarClicked = false;

    private AgendamentoVisita agendamentoVisita;
    private ObservableList<Cidade> observableLisCidade;
    private List<Cidade> listCidadeCB;
    private ObservableList<Bairro> observableLisBairro;
    private List<Bairro> listBairroCB;

    private ObservableList<SolicitacaoVisita> observableLisSolicitacaoVisita;
    private List<SolicitacaoVisita> listSolicitacaoVisitaCB;

    private ObservableList<Funcionario> observableLisFuncionarioList;
    private ObservableList<Funcionario> observableLisFuncionarioCB;
    private List<Funcionario> listFuncionarioCB;
    private List<Funcionario> listFuncionarioList = new ArrayList<>();
    private List<FuncionarioAgenda> listfa = new ArrayList<>();

    AgendamentoVisitaService avs = new AgendamentoVisitaService();
    CidadeService cs = new CidadeService();
    BairroService bs = new BairroService();
    FuncionarioService fs = new FuncionarioService();

    private List<UsuarioComum> listUsuarioComumCB;
    private ObservableList<UsuarioComum> observableLisUsuarioComumCombobox;
    UsuarioComumService ucs = new UsuarioComumService();

    private ObservableList<SolicitacaoVisita> observableLisSolicitacaoCombobox;
    private List<SolicitacaoVisita> listSolicitacaoCB;
    SolicitacaoVisitaService svs = new SolicitacaoVisitaService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            carregarComboBoxTipoAbrigo();
            carregarComboBoxCidadeBairro();
            observableLisFuncionarioList = FXCollections.observableArrayList(listFuncionarioList);
            listViewFuncionarios.setItems(observableLisFuncionarioList);
            carregarComboBoxSolicitacao();
        } catch (IOException ex) {
            Logger.getLogger(FXMLAnchorPaneAgendamentoDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void carregarComboBoxTipoAbrigo() throws IOException {
        observableListTipoVisitas = FXCollections.observableArrayList("Visita em Abrigo", "Visita de Recolhimento de Cerebro", "Visita em Curral");
        comboxTipoVisita.setItems(observableListTipoVisitas);
    }

    public AgendamentoVisita getAgendamentoVisita() {
        return agendamentoVisita;
    }

    public void setAgendamentoVisita(AgendamentoVisita agendamentoVisita) {
        this.agendamentoVisita = agendamentoVisita;
    }

    public boolean isBtnConfirmarClicked() {
        return btnConfirmarClicked;
    }

    public void carregarComboBoxCidadeBairro() throws IOException {

        listCidadeCB = cs.listar();
        listBairroCB = bs.listar();
        listFuncionarioCB = fs.listar();
        observableLisCidade = FXCollections.observableArrayList(listCidadeCB);
        observableLisBairro = FXCollections.observableArrayList(listBairroCB);
        observableLisFuncionarioCB = FXCollections.observableArrayList(listFuncionarioCB);
        comboxFuncionarios.setItems(observableLisFuncionarioCB);
    }

    public void selecionarComboBoxViewSolicitacao() throws IOException {
        SolicitacaoVisita sv = new SolicitacaoVisita();
        sv = comboxSolicitacao.getSelectionModel().getSelectedItem();
        if (sv != null) {
            String mensagem = "";
            textFieldProdutor.setText(sv.getUsuarioComum().getNome());
            textFieldProdutor.setDisable(true);
            textFieldBairro.setText(sv.getUsuarioComum().getBairro().getNomeBairro());
            textFieldBairro.setDisable(true);
            textFieldCidade.setText(sv.getUsuarioComum().getBairro().getCidade().getNomeCidade());
            textFieldCidade.setDisable(true);
            textFieldNumero.setText(String.valueOf(sv.getUsuarioComum().getNumero()));
            textFieldNumero.setDisable(true);
            textFieldRua.setText(sv.getUsuarioComum().getRua());
            textFieldRua.setDisable(true);

        } else {
            textFieldProdutor.setText("");
            textFieldBairro.setText("");
            textFieldCidade.setText("");
            textFieldNumero.setText("");
            textFieldRua.setText("");
            carregarComboBoxTipoAbrigo();
            observableLisFuncionarioList.clear();
        }

    }

    public void carregarComboBoxSolicitacao() throws IOException {
        listSolicitacaoCB = svs.listarStatus();
        observableLisSolicitacaoCombobox = FXCollections.observableArrayList(listSolicitacaoCB);
        comboxSolicitacao.setItems(observableLisSolicitacaoCombobox);
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }

    public void handlleAdicionarFuncionario() throws IOException {
        Funcionario funcionario = new Funcionario();
        funcionario = comboxFuncionarios.getValue();
        if (funcionario != null) {
            listFuncionarioCB.remove(funcionario);
            listFuncionarioList.add(funcionario);
            observableLisFuncionarioCB.remove(funcionario);
            observableLisFuncionarioList.add(funcionario);

            FuncionarioAgenda fa = new FuncionarioAgenda();
            fa.setFuncionario(funcionario);
            listfa.add(fa);
            agendamentoVisita.setListfa(listfa);
        }

    }

    public void handlleRemover() throws IOException {
        Funcionario funcionario = new Funcionario();
        funcionario = listViewFuncionarios.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
            listFuncionarioCB.add(funcionario);
            listFuncionarioList.remove(funcionario);
            observableLisFuncionarioCB.add(funcionario);
            observableLisFuncionarioList.remove(funcionario);
            FuncionarioAgenda fa = new FuncionarioAgenda();
            fa.setFuncionario(funcionario);
            listfa.clear();

            for (Funcionario fa1 : listFuncionarioList) {
                FuncionarioAgenda agenda = new FuncionarioAgenda();
                agenda.setFuncionario(fa1);
                listfa.add(agenda);
            }
            agendamentoVisita.setListfa(listfa);
        }

    }

    public void ImprimiAlertaVerificacao() {
        String mensagem = "";
        mensagem = "Existem campos NÃO preenchidos ou selecionados!!!";
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensagem);
        alerta.show();
    }

    public boolean VerificaCombobox() {
        boolean conferido;
        if (comboxTipoVisita.getSelectionModel().getSelectedIndex() == -1) {
            ImprimiAlertaVerificacao();
            conferido = false;
        }  else if (listfa.isEmpty()) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else if (textFieldRua.getText().equals("")) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else if (textFieldNumero.getText().equals("")) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else if (datePickerAgendamento.getValue() == null) {
            ImprimiAlertaVerificacao();
            conferido = false;
        } else {
            conferido = true;
        }
        return conferido;
    }

    @FXML
    public void handleButtonConfirmar() throws IOException {
        boolean teste;
        teste = VerificaCombobox();
        if (teste) {
            UsuarioComum usuarioComum = new UsuarioComum();
            Funcionario f = new Funcionario();
            Bairro bairro = new Bairro();
            SolicitacaoVisita sv = new SolicitacaoVisita();
            sv = comboxSolicitacao.getSelectionModel().getSelectedItem();
            String mensagem = "";
            mensagem = "Solicitacao: " + sv.getCdSolicitacao();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
            usuarioComum = sv.getUsuarioComum();
            bairro.setCdBairro(sv.getUsuarioComum().getBairro().getCdBairro());
            agendamentoVisita.setSolicitacaoVisita(sv);
            agendamentoVisita.setBairro(bairro);
            agendamentoVisita.setTipoVisita(comboxTipoVisita.getSelectionModel().getSelectedItem());
            agendamentoVisita.setData(java.sql.Date.valueOf(datePickerAgendamento.getValue()));
            agendamentoVisita.setStatus(1);
            agendamentoVisita.setRua(textFieldRua.getText());
            agendamentoVisita.setNumero(Integer.valueOf(textFieldNumero.getText()));
            agendamentoVisita.setUsuarioComum(usuarioComum);
            agendamentoVisita.setFuncionario(f);
            agendamentoVisita.setListfa(listfa);
            btnConfirmarClicked = true;
            dialogStage.close();
        }
    }
}
