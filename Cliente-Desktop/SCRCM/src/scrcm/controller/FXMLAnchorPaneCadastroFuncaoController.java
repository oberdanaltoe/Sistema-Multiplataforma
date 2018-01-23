/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scrcm.domain.Funcao;
import scrcm.service.FuncaoService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroFuncaoController implements Initializable {

    @FXML
    private TableView<Funcao> tableViewFuncao;
    @FXML
    private TableColumn<Funcao, String> tableColumnFuncao;
    @FXML
    private Label labelFuncaoNome;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;

    private ObservableList<Funcao> observableListFuncao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarTableViewFuncao();
            selecionarItemTableViewFuncao(null);
            tableViewFuncao.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewFuncao(newValue));

        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroFuncaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewFuncao() throws Exception {
        FuncaoService funcaoService = new FuncaoService();
        ArrayList<Funcao> listFunc = new ArrayList<Funcao>();
        String mensagem = "";
        listFunc = funcaoService.listar();

        if (listFunc == null || listFunc.isEmpty()) {
            mensagem = "Não existem Funções cadastradas";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (Funcao funcao : listFunc) {
                tableColumnFuncao.setCellValueFactory(new PropertyValueFactory<>("nomeFuncao"));
                observableListFuncao = FXCollections.observableArrayList(listFunc);
                tableViewFuncao.setItems(observableListFuncao);
            }
        }
    }

    public void selecionarItemTableViewFuncao(Funcao funcao) {
        if (funcao != null) {
            labelFuncaoNome.setText(String.valueOf(funcao.getNomeFuncao()));
        } else {
            labelFuncaoNome.setText("");
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException, Exception {
        Funcao funcao = new Funcao();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroFuncaoDialog(funcao);
        if (buttonConfirmarClicked) {
            FuncaoService funcaoService = new FuncaoService();
            funcaoService.inserir(funcao);
            carregarTableViewFuncao();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("Funcao : " + funcao.getNomeFuncao() + " Cadastrada com Sucesso!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException, Exception {
        Funcao funcao = tableViewFuncao.getSelectionModel().getSelectedItem();
        if (funcao != null) {
            boolean butttonConfirmarClicked = showFXMLAnchorPaneCadastroFuncaoDialog(funcao);
            if (butttonConfirmarClicked) {
                FuncaoService funcaoService = new FuncaoService();
                funcaoService.alterar(funcao);
                carregarTableViewFuncao();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Alteração efetuada com sucesso!");
                alert.setContentText("Função : " + funcao.getNomeFuncao() + " Alterada com sucesso!");
                alert.show();
            }
        }
    }

    @FXML
    public void handleButtonExcluir() throws IOException, Exception {
        String errorMessage = "";
        Funcao funcao = tableViewFuncao.getSelectionModel().getSelectedItem();
        FuncaoService funcaoService = new FuncaoService();
        funcaoService.excluir(funcao);
        this.observableListFuncao.remove(funcao);
        carregarTableViewFuncao();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Função Removida");
        alert.setContentText("Função : " + funcao.getNomeFuncao() + " Removida com Sucesso!");
        alert.show();

    }

    public boolean showFXMLAnchorPaneCadastroFuncaoDialog(Funcao funcao) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroFuncaoDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneCadastroFuncaoDialog.fxml"));

        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Funções");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLAnchorPaneCadastroFuncaoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncao(funcao);
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }
}
