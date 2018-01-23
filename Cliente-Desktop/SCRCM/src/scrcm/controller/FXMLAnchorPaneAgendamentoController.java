/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scrcm.domain.AgendamentoVisita;
import scrcm.domain.Bairro;
import scrcm.domain.FuncionarioAgenda;
import scrcm.service.AgendamentoVisitaService;
import scrcm.service.FuncionarioAgendaService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneAgendamentoController implements Initializable {

    @FXML
    private TableView<AgendamentoVisita> tableViewAgendamento;
    @FXML
    private TableColumn<AgendamentoVisita, Date> tableColumnData;
    @FXML
    private TableColumn<AgendamentoVisita, Bairro> tableColumnBairro;
    @FXML
    private ListView<FuncionarioAgenda> listViewFuncAgendamento;
    @FXML
    private Label labelAgendamentoCidade;
    @FXML
    private Label labelAgendamentoData;
    @FXML
    private Label labelAgendamentoRua;
    @FXML
    private Label labelAgendamentoNumero;
    @FXML
    private Label labelAgendamentoBairro;
    @FXML
    private Label labelAgendamentoTipoVisita;

    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    private ObservableList<AgendamentoVisita> observableListAgenda;
    private ObservableList<FuncionarioAgenda> observableListViewFuncionarioAgenda;
    private List<FuncionarioAgenda> listFuncioAgenda = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnAlterar.setVisible(false);
        try {
            // TODO
            carregarTableViewAgendamento();

            selecionarItemTableViewAgendamento(null);
            tableViewAgendamento.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        try {
                            selecionarItemTableViewAgendamento(newValue);
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLAnchorPaneAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneAgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewAgendamento() throws Exception {
        AgendamentoVisitaService avs = new AgendamentoVisitaService();

        ArrayList<AgendamentoVisita> listAgend = new ArrayList<AgendamentoVisita>();
        System.out.println("\n\n\n\n");
        System.out.println("listAgend" + listAgend);
        System.out.println("\n\n\n\n");
        String mensagem = "";

        listAgend = avs.listar();

        if (listAgend == null || listAgend.isEmpty()) {
            mensagem = "Não existem Agendamentos Cadastrados";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {

            tableColumnBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
            tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));

            observableListAgenda = FXCollections.observableArrayList(listAgend);
            tableViewAgendamento.setItems(observableListAgenda);

        }

    }

    public void selecionarItemTableViewAgendamento(AgendamentoVisita agendamentoVisita) throws IOException {
        if (agendamentoVisita != null) {
            String mensagem = "";

            labelAgendamentoBairro.setText(agendamentoVisita.getBairro().getNomeBairro());
            labelAgendamentoCidade.setText(agendamentoVisita.getBairro().getCidade().getNomeCidade());
            labelAgendamentoRua.setText((agendamentoVisita.getRua()));
            labelAgendamentoNumero.setText(String.valueOf(agendamentoVisita.getNumero()));
            labelAgendamentoTipoVisita.setText((agendamentoVisita.getTipoVisita()));

            Calendar c = Calendar.getInstance();
            c.setTime(agendamentoVisita.getData());
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
            String dataConvertida = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
            labelAgendamentoData.setText(dataConvertida);

            FuncionarioAgenda funcionarioAgenda = new FuncionarioAgenda();
            funcionarioAgenda.setAgendamentoVisita(agendamentoVisita);

            FuncionarioAgendaService fas = new FuncionarioAgendaService();

            listFuncioAgenda = fas.listarPorAgendamento(agendamentoVisita);

            observableListViewFuncionarioAgenda = FXCollections.observableArrayList(listFuncioAgenda);
            listViewFuncAgendamento.setItems(observableListViewFuncionarioAgenda);

            System.out.println("listFuncioAgenda.add(fas.buscar(funcionarioAgenda)); " + listFuncioAgenda);

        } else {
            labelAgendamentoBairro.setText("");
            labelAgendamentoCidade.setText("");
            labelAgendamentoRua.setText("");
            labelAgendamentoNumero.setText("");
            labelAgendamentoTipoVisita.setText("");
            labelAgendamentoData.setText("");
            listFuncioAgenda.clear();
        }

    }

    @FXML
    public void handleButtonInserir() throws IOException, Exception {
        AgendamentoVisita agendamentoVisita = new AgendamentoVisita();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneAgendamentoDialog(agendamentoVisita);
        if (buttonConfirmarClicked) {
            AgendamentoVisitaService visitaService = new AgendamentoVisitaService();
            boolean confirma;
            confirma = visitaService.inserir(agendamentoVisita);

            if (agendamentoVisita.getListfa() != null) {
                for (FuncionarioAgenda fa1 : agendamentoVisita.getListfa()) {
                    AgendamentoVisita visita = new AgendamentoVisita();
                    AgendamentoVisitaService avs = new AgendamentoVisitaService();
                    FuncionarioAgendaService fas = new FuncionarioAgendaService();

                    visita.setCdAgendamentoVisita(avs.buscarUltimo());
                    fa1.setAgendamentoVisita(visita);
                    fas.inserir(fa1);
                }
            }
            carregarTableViewAgendamento();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("Agendamento cadastrado com Sucesso!");
            alert.show();

            if (confirma) {
                agendamentoVisita.getSolicitacaoVisita().setStatus(2);
                AgendamentoVisitaService avsAlterar = new AgendamentoVisitaService();
                avsAlterar.alterarStatus(agendamentoVisita);
            }

        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException, Exception {
        AgendamentoVisita av = tableViewAgendamento.getSelectionModel().getSelectedItem();
        if (av != null) {
            boolean butttonConfirmarClicked = showFXMLAnchorPaneAgendamentoDialog(av);
            if (butttonConfirmarClicked) {

                AgendamentoVisitaService avs = new AgendamentoVisitaService();
                avs.alterar(av);

                carregarTableViewAgendamento();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
                alert.setContentText("Agendamento : " + av.getCdAgendamentoVisita() + " Alterado com Sucesso!");
                alert.show();
            }
        }
    }

    public boolean showFXMLAnchorPaneAgendamentoDialog(AgendamentoVisita agendamentoVisita) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneAgendamentoDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneAgendamentoDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Agendamento");

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLAnchorPaneAgendamentoDialogController controller = loader.getController();

        controller.setDialogStage(dialogStage);
        controller.setAgendamentoVisita(agendamentoVisita);

        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }

    @FXML
    public void handleButtonExcluir() throws IOException, Exception {
        boolean confere;
        String errorMessage = "";
        AgendamentoVisita av = tableViewAgendamento.getSelectionModel().getSelectedItem();
        AgendamentoVisitaService avs = new AgendamentoVisitaService();

        FuncionarioAgenda fa = new FuncionarioAgenda();
        fa.setAgendamentoVisita(av);
        FuncionarioAgendaService fas = new FuncionarioAgendaService();

        confere = fas.excluir(fa);
        System.err.println("confere boolean: " + confere);

        avs.excluir(av);
        this.observableListAgenda.remove(av);

        carregarTableViewAgendamento();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Funcionário Removido");
        alert.setContentText("Agendamento : " + av.getCdAgendamentoVisita() + " Removido com Sucesso!");
        alert.show();

    }
}
