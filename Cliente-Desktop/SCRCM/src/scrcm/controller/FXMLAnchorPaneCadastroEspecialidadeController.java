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
import scrcm.domain.Especialidade;
import scrcm.service.EspecialidadeService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroEspecialidadeController implements Initializable {

    @FXML
    private TableView<Especialidade> tableViewEspecialidade;
    @FXML
    private TableColumn<Especialidade, String> tableColumnEspecialidade;
    @FXML
    private Label labelEspecialidadeNome;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;

    private ObservableList<Especialidade> observableListEspecialidade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            carregarTableViewEspecialidade();
            selecionarItemTableViewEspecialidade(null);
            tableViewEspecialidade.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewEspecialidade(newValue));

        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroEspecialidadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewEspecialidade() throws Exception {
        EspecialidadeService especialidadeService = new EspecialidadeService();
        ArrayList<Especialidade> listEspec = new ArrayList<Especialidade>();
        String mensagem = "";
        listEspec = especialidadeService.listar();

        if (listEspec == null || listEspec.isEmpty()) {
            mensagem = "Não existem Especialidades cadastradas";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (Especialidade especialidade : listEspec) {
                tableColumnEspecialidade.setCellValueFactory(new PropertyValueFactory<>("nomeEspecialidade"));
                observableListEspecialidade = FXCollections.observableArrayList(listEspec);
                tableViewEspecialidade.setItems(observableListEspecialidade);
            }
        }
    }

    public void selecionarItemTableViewEspecialidade(Especialidade especialidade) {
        if (especialidade != null) {
            labelEspecialidadeNome.setText(String.valueOf(especialidade.getNomeEspecialidade()));
        } else {
            labelEspecialidadeNome.setText("");
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException, Exception {
        Especialidade especialidade = new Especialidade();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroEspecialidadeDialog(especialidade);
        if (buttonConfirmarClicked) {
            EspecialidadeService especialidadeService = new EspecialidadeService();
            especialidadeService.inserir(especialidade);
            carregarTableViewEspecialidade();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("Especialidade : " + especialidade.getNomeEspecialidade() + " Cadastrada com Sucesso!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException, Exception {
        Especialidade especialidade = tableViewEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade != null) {
            boolean butttonConfirmarClicked = showFXMLAnchorPaneCadastroEspecialidadeDialog(especialidade);
            if (butttonConfirmarClicked) {
                EspecialidadeService especialidadeService = new EspecialidadeService();
                especialidadeService.alterar(especialidade);
                carregarTableViewEspecialidade();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Alteração efetuada com sucesso!");
                alert.setContentText("Especialidade : " + especialidade.getNomeEspecialidade() + " Alterada com sucesso!");
                alert.show();
            }
        }
    }

    @FXML
    public void handleButtonExcluir() throws IOException, Exception {
        String errorMessage = "";
        Especialidade especialidade = tableViewEspecialidade.getSelectionModel().getSelectedItem();
        EspecialidadeService especialidadeService = new EspecialidadeService();
        especialidadeService.excluir(especialidade);
        this.observableListEspecialidade.remove(especialidade);
        carregarTableViewEspecialidade();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Especialidade Removida");
        alert.setContentText("Especialidade : " + especialidade.getNomeEspecialidade() + " Removida com Sucesso!");
        alert.show();
    }

    public boolean showFXMLAnchorPaneCadastroEspecialidadeDialog(Especialidade especialidade) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroEspecialidadeDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneCadastroEspecialidadeDialog.fxml"));

        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Especilidades");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLAnchorPaneCadastroEspecialidadeDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEspecialidade(especialidade);
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }
}
