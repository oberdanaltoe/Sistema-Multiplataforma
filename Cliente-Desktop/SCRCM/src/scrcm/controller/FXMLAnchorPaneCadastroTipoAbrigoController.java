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
import scrcm.domain.TipoAbrigo;
import scrcm.service.TipoAbrigoService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroTipoAbrigoController implements Initializable {

    @FXML
    private TableView<TipoAbrigo> tableViewTipoAbrigo;
    @FXML
    private TableColumn<TipoAbrigo, String> tableColumnTipoAbrigo;
    @FXML
    private Label labelTipoAbrigoNome;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;

    private ObservableList<TipoAbrigo> observableListTipoAbrigo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            carregarTableViewTipoAbrigo();
            selecionarItemTableViewTipoAbrigo(null);
            tableViewTipoAbrigo.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewTipoAbrigo(newValue));

        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroTipoAbrigoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewTipoAbrigo() throws Exception {
        TipoAbrigoService tipoAbrigoService = new TipoAbrigoService();
        ArrayList<TipoAbrigo> ltipoAbrigos = new ArrayList<TipoAbrigo>();
        String mensagem = "";
        ltipoAbrigos = tipoAbrigoService.listar();
        
        if (ltipoAbrigos == null || ltipoAbrigos.isEmpty()) {
            mensagem = "Não existem Tipo de Abrigos cadastrados";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (TipoAbrigo tipoAbrigo : ltipoAbrigos) {
                tableColumnTipoAbrigo.setCellValueFactory(new PropertyValueFactory<>("nomeAbrigo"));
                observableListTipoAbrigo = FXCollections.observableArrayList(ltipoAbrigos);
                tableViewTipoAbrigo.setItems(observableListTipoAbrigo);
            }
        }
    }

    public void selecionarItemTableViewTipoAbrigo(TipoAbrigo tipoAbrigo) {
        if (tipoAbrigo != null) {
            labelTipoAbrigoNome.setText(String.valueOf(tipoAbrigo.getNomeAbrigo()));
        } else {
            labelTipoAbrigoNome.setText("");
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException, Exception {
        TipoAbrigo tipoAbrigo = new TipoAbrigo();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroTipoAbrigoDialog(tipoAbrigo);
        if (buttonConfirmarClicked) {
            TipoAbrigoService tipoAbrigoService = new TipoAbrigoService();
            tipoAbrigoService.inserir(tipoAbrigo);
            carregarTableViewTipoAbrigo();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("Tipo Abrigo : " + tipoAbrigo.getNomeAbrigo() + " Cadastrado com Sucesso!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException, Exception {
        TipoAbrigo tipoAbrigo = tableViewTipoAbrigo.getSelectionModel().getSelectedItem();
        if (tipoAbrigo != null) {
            boolean butttonConfirmarClicked = showFXMLAnchorPaneCadastroTipoAbrigoDialog(tipoAbrigo);
            if (butttonConfirmarClicked) {
                TipoAbrigoService tipoAbrigoService = new TipoAbrigoService();
                tipoAbrigoService.alterar(tipoAbrigo);
                carregarTableViewTipoAbrigo();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Alteração efetuada com sucesso!");
                alert.setContentText("Tipo Abrigo : " + tipoAbrigo.getNomeAbrigo() + " Alterado com sucesso!");
                alert.show();
            }
        }
    }
    
     @FXML
    public void handleButtonExcluir() throws IOException, Exception {
        String errorMessage = "";
        TipoAbrigo tipoAbrigo = tableViewTipoAbrigo.getSelectionModel().getSelectedItem();
        TipoAbrigoService tipoAbrigoService = new TipoAbrigoService();
        tipoAbrigoService.excluir(tipoAbrigo);
        this.observableListTipoAbrigo.remove(tipoAbrigo);
        carregarTableViewTipoAbrigo();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Tipo Abrigo Removido");
        alert.setContentText("Tipo Abrigo : " + tipoAbrigo.getNomeAbrigo() + " Removido com Sucesso!");
        alert.show();

    }

    public boolean showFXMLAnchorPaneCadastroTipoAbrigoDialog(TipoAbrigo tipoAbrigo) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroTipoAbrigoDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneCadastroTipoAbrigoDialog.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Tipo Abrigo");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLAnchorPaneCadastroTipoAbrigoDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setTipoAbrigo(tipoAbrigo);
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }
}
