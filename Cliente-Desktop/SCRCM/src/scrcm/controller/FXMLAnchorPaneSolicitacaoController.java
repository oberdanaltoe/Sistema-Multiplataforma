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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scrcm.domain.SolicitacaoVisita;
import scrcm.domain.TipoAbrigoSolicitacao;
import scrcm.service.SolicitacaoVisitaService;
import scrcm.service.TipoAbrigoSolicitacaoService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneSolicitacaoController implements Initializable {

    @FXML
    private TableView<SolicitacaoVisita> tableViewSolicitacao;
    @FXML
    private TableColumn<SolicitacaoVisita, String> tableColumnSolicitacao;
    @FXML
    private Label labelSolicitacaoUsuario;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Label labelPossuiProdiedade;
    @FXML
    private Label labelMediaAnimaisPropriedade;
    @FXML
    private TextArea textAreaObservacoes;
    @FXML
    private Label labelAnimaisMordidos;
    @FXML
    private Label labelCasosMorteRegiao;
    @FXML
    private Label labelPropriedadeLocaisProximos;
    @FXML
    private Label labelTempoOcorridoMorte;
    @FXML
    private Label labelConheceAbrigo;
    @FXML
    private Label labelSolicitarRecolhimentoCer;
    @FXML
    private ListView<TipoAbrigoSolicitacao> listViewAbrigos;
    @FXML
    private List<TipoAbrigoSolicitacao> listTipoAbrigoSolicitacao = new ArrayList<>();

    private ObservableList<SolicitacaoVisita> observableListSolicitacao;
    private ObservableList<TipoAbrigoSolicitacao> observableListTipoAbrigoSolicitacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarTableViewSolicitacao();
            selecionarItemTableViewSolicitacao(null);
            tableViewSolicitacao.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> {
                        try {
                            selecionarItemTableViewSolicitacao(newValue);
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLAnchorPaneSolicitacaoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneSolicitacaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewSolicitacao() throws Exception {

        SolicitacaoVisitaService svs = new SolicitacaoVisitaService();
        ArrayList<SolicitacaoVisita> listSolicitacaoVisita = new ArrayList<SolicitacaoVisita>();
        String mensagem = "";
        listSolicitacaoVisita = svs.listar();

        if (listSolicitacaoVisita == null || listSolicitacaoVisita.isEmpty()) {
            mensagem = "Não existem Funcionários cadastrados";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (SolicitacaoVisita sv : listSolicitacaoVisita) {
                tableColumnSolicitacao.setCellValueFactory(new PropertyValueFactory<>("cdSolicitacao"));
                observableListSolicitacao = FXCollections.observableArrayList(listSolicitacaoVisita);
                tableViewSolicitacao.setItems(observableListSolicitacao);
            }
        }
    }

    public void selecionarItemTableViewSolicitacao(SolicitacaoVisita sv) throws IOException {
        if (sv != null) {
            labelSolicitacaoUsuario.setText(String.valueOf(sv.getUsuarioComum().getNome()));
            labelPossuiProdiedade.setText(String.valueOf(sv.getPossuiPropriedade()));
            labelMediaAnimaisPropriedade.setText(String.valueOf(sv.getQtdMediaAnimais()));
            labelAnimaisMordidos.setText(String.valueOf(sv.getQtdAnimaisMordidos()));
            labelCasosMorteRegiao.setText(String.valueOf(sv.getCasosMorteRegiao()));
            labelPropriedadeLocaisProximos.setText(String.valueOf(sv.getProprieLocaisProximos()));
            labelConheceAbrigo.setText(String.valueOf(sv.getConheceAbrigo()));
            labelSolicitarRecolhimentoCer.setText(String.valueOf(sv.getSolicitarRecolhimento()));
            labelTempoOcorridoMorte.setText(String.valueOf(sv.getTempoOcorridoMorte()));
            textAreaObservacoes.setText(String.valueOf(sv.getObservacoes()));
            TipoAbrigoSolicitacao tas = new TipoAbrigoSolicitacao();
            tas.setSolicitacaoVisita(sv);
            TipoAbrigoSolicitacaoService tass = new TipoAbrigoSolicitacaoService();
            listTipoAbrigoSolicitacao = tass.listarPorSolicitacao(sv);
            if (listTipoAbrigoSolicitacao != null) {
                observableListTipoAbrigoSolicitacao = FXCollections.observableArrayList(listTipoAbrigoSolicitacao);
                listViewAbrigos.setItems(observableListTipoAbrigoSolicitacao);
            }
        } else {
            labelSolicitacaoUsuario.setText("");
            labelPossuiProdiedade.setText("");
            labelMediaAnimaisPropriedade.setText("");
            labelAnimaisMordidos.setText("");
            labelCasosMorteRegiao.setText("");
            labelPropriedadeLocaisProximos.setText("");
            labelConheceAbrigo.setText("");
            labelSolicitarRecolhimentoCer.setText("");
            listTipoAbrigoSolicitacao.clear();
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException, Exception {
        SolicitacaoVisita sv = new SolicitacaoVisita();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneSolicitacaoDialog(sv);
        if (buttonConfirmarClicked) {
            SolicitacaoVisitaService svs = new SolicitacaoVisitaService();
            svs.inserir(sv);
            if (sv.getListTipoAbrigoSolicitacao() != null) {
                for (TipoAbrigoSolicitacao tas : sv.getListTipoAbrigoSolicitacao()) {
                    SolicitacaoVisita sv1 = new SolicitacaoVisita();
                    SolicitacaoVisitaService svs1 = new SolicitacaoVisitaService();
                    TipoAbrigoSolicitacaoService tass = new TipoAbrigoSolicitacaoService();
                    sv1.setCdSolicitacao(svs1.buscarUltimo());
                    tas.setSolicitacaoVisita(sv1);
                    tass.inserir(tas);
                }
            }
            carregarTableViewSolicitacao();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("Solicitação cadastrado com Sucesso!");
            alert.show();
        }
    }

    public boolean showFXMLAnchorPaneSolicitacaoDialog(SolicitacaoVisita solicitacaoVisita) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneSolicitacaoDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneSolicitacaoDialog.fxml"));
       
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Solicitação");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLAnchorPaneSolicitacaoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setSolicitacaoVisita(solicitacaoVisita);
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }

    @FXML
    public void handleButtonExcluir() throws IOException, Exception {
        boolean confere;

        SolicitacaoVisita sv = tableViewSolicitacao.getSelectionModel().getSelectedItem();
        SolicitacaoVisitaService svs = new SolicitacaoVisitaService();
        TipoAbrigoSolicitacao tas = new TipoAbrigoSolicitacao();
        TipoAbrigoSolicitacaoService tass = new TipoAbrigoSolicitacaoService();       
        tas.setSolicitacaoVisita(sv);
        confere = tass.excluir(tas);
        svs.excluir(sv);
        this.observableListSolicitacao.remove(sv);
        carregarTableViewSolicitacao();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Solicitação Removida");
        alert.setContentText("Solicitação : " + sv.getCdSolicitacao() + " Removido com Sucesso!");
        alert.show();

    }

}
