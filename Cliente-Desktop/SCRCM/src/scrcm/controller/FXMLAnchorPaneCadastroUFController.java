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
import scrcm.domain.UF;
import scrcm.service.UFService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroUFController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<UF> tableViewUF;
    @FXML
    private TableColumn<UF, String> tableColumnUF;
    @FXML
    private Label labelUFNome;
    @FXML
    private Label labelSiglaUF;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;

    private ObservableList<UF> observableListUF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarTableViewUF();
            selecionarItemTableViewUF(null);
            tableViewUF.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewUF(newValue));

        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroUFController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewUF() throws Exception {
        UFService ufService = new UFService();
        ArrayList<UF> ltUFs = new ArrayList<UF>();
        String mensagem = "";
        ltUFs = ufService.listar();
        
        if (ltUFs == null || ltUFs.isEmpty()) {
            mensagem = "Não existem UFs cadastradas";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (UF ufs : ltUFs) {
                tableColumnUF.setCellValueFactory(new PropertyValueFactory<>("nomeUF"));
                observableListUF = FXCollections.observableArrayList(ltUFs);
                tableViewUF.setItems(observableListUF);
            }
        }
    }

    public void selecionarItemTableViewUF(UF uf) {
        if (uf != null) {
            labelUFNome.setText(String.valueOf(uf.getNomeUF()));
            labelSiglaUF.setText(String.valueOf(uf.getSigla()));
        } else {
            labelUFNome.setText("");
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException, Exception {
        UF uf = new UF();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroUFDialog(uf);
        if (buttonConfirmarClicked) {
            UFService ufService = new UFService();
            ufService.inserir(uf);
            carregarTableViewUF();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("UF : " + uf.getNomeUF() + " Cadastrada com Sucesso!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException, Exception {
        UF uf = tableViewUF.getSelectionModel().getSelectedItem();
        if (uf != null) {
            boolean butttonConfirmarClicked = showFXMLAnchorPaneCadastroUFDialog(uf);
            if (butttonConfirmarClicked) {
                UFService ufService = new UFService();
                ufService.alterar(uf);
                carregarTableViewUF();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Alteração efetuada com sucesso!");
                alert.setContentText("UF : " + uf.getNomeUF() + " Foi Alterada");
                alert.show();
            }
        }
    }

    @FXML
    public void handleButtonExcluir() throws IOException, Exception {
        String errorMessage = "";
        UF uf = tableViewUF.getSelectionModel().getSelectedItem();
        UFService ufService = new UFService();
        ufService.excluir(uf);
        this.observableListUF.remove(uf);
        carregarTableViewUF();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("UF Removida");
        alert.setContentText("UF : " + uf.getNomeUF()
                + " Removida com Sucesso!");
        alert.show();

    }

    public boolean showFXMLAnchorPaneCadastroUFDialog(UF uf) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroUFDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneCadastroUFDialog.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de UFs");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLAnchorPaneCadastroUFDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setUf(uf);
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }

}
