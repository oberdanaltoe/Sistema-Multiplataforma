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
import scrcm.domain.Funcionario;
import scrcm.service.FuncionarioService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroFuncionarioController implements Initializable {

    @FXML
    private TableView<Funcionario> tableViewFuncionario;
    @FXML
    private TableColumn<Funcionario, String> tableColumnFuncionario;
    @FXML
    private Label labelFuncionarioNome;
    @FXML
    private Label labelFuncionarioFuncao;
    @FXML
    private Label labelFuncionarioEspecialidade;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;

    private ObservableList<Funcionario> observableListFuncionario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            carregarTableViewFuncionario();
            selecionarItemTableViewFuncionario(null);
            tableViewFuncionario.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewFuncionario(newValue));
            
        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewFuncionario() throws Exception {
        FuncionarioService funcService = new FuncionarioService();
        ArrayList<Funcionario> listFuncionario = new ArrayList<Funcionario>();
        String mensagem = "";
        listFuncionario = funcService.listar();

        if (listFuncionario == null || listFuncionario.isEmpty()) {
            mensagem = "Não existem Funcionários cadastrados";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (Funcionario func : listFuncionario) {
                tableColumnFuncionario.setCellValueFactory(new PropertyValueFactory<>("nome"));
                observableListFuncionario = FXCollections.observableArrayList(listFuncionario);
                tableViewFuncionario.setItems(observableListFuncionario);
            }
        }
    }

    public void selecionarItemTableViewFuncionario(Funcionario funcionario) {
        if (funcionario != null) {
            labelFuncionarioNome.setText(String.valueOf(funcionario.getNome()));
            labelFuncionarioFuncao.setText(String.valueOf(funcionario.getFuncao().getNomeFuncao()));
            labelFuncionarioEspecialidade.setText(String.valueOf(funcionario.getEspecialidade().getNomeEspecialidade()));
        } else {
            labelFuncionarioNome.setText("");
            labelFuncionarioEspecialidade.setText("");
            labelFuncionarioFuncao.setText("");
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException, Exception {
        Funcionario funcionario = new Funcionario();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroFuncionarioDialog(funcionario);
        if (buttonConfirmarClicked) {
            FuncionarioService funcionarioService = new FuncionarioService();
            funcionarioService.inserir(funcionario);
            carregarTableViewFuncionario();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("Funcionario : " + funcionario.getNome() + " Cadastrado com Sucesso!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException, Exception {
        Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
            boolean butttonConfirmarClicked = showFXMLAnchorPaneCadastroFuncionarioDialog(funcionario);
            if (butttonConfirmarClicked) {
                FuncionarioService funcionarioService = new FuncionarioService();
                funcionarioService.alterar(funcionario);
                carregarTableViewFuncionario();
               
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
                alert.setContentText("Funcionario : " + funcionario.getNome() + " Alterado com Sucesso!");
                alert.show();

            }
        }
    }

    public boolean showFXMLAnchorPaneCadastroFuncionarioDialog(Funcionario funcionario) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroFuncionarioDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneCadastroFuncionarioDialog.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Funcionário");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLAnchorPaneCadastroFuncionarioDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario);
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }

    @FXML
    public void handleButtonExcluir() throws IOException, Exception {
        Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
        FuncionarioService funcionarioService = new FuncionarioService();
        funcionarioService.excluir(funcionario);
        this.observableListFuncionario.remove(funcionario);
        carregarTableViewFuncionario();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Funcionário Removido");
        alert.setContentText("Funcionário : " + funcionario.getNome() + " Removido com Sucesso!");
        alert.show();

    }
}
