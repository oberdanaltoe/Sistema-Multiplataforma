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
import scrcm.domain.Cidade;
import scrcm.service.CidadeService;
/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroCidadeController implements Initializable {

    @FXML
    private TableView<Cidade> tableViewCidade;
    @FXML
    private TableColumn<Cidade, String> tableColumnCidade;    
    @FXML
    private Label labelCidadeNome;
    @FXML
    private Label labelCidadeUF;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;

    private ObservableList<Cidade> observableListCidade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
             carregarTableViewCidade();
            selecionarItemTableViewCidade(null);
            tableViewCidade.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewCidade(newValue));

        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroCidadeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewCidade() throws Exception {
        CidadeService cidadeService = new CidadeService();
        ArrayList<Cidade> listCid = new ArrayList<Cidade>();
        String mensagem = "";
        listCid = cidadeService.listar();

        if (listCid == null || listCid.isEmpty()) {
            mensagem = "Não existem Cidades cadastradas";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (Cidade cidade : listCid) {                               
                tableColumnCidade.setCellValueFactory(new PropertyValueFactory<>("nomeCidade"));
                observableListCidade = FXCollections.observableArrayList(listCid);
                tableViewCidade.setItems(observableListCidade);
            }
        }
    }

    public void selecionarItemTableViewCidade(Cidade cidade) {
        if (cidade != null) {            
            labelCidadeNome.setText(String.valueOf(cidade.getNomeCidade()));
            labelCidadeUF.setText(String.valueOf(cidade.getuF().getSigla()));
        } else {
            labelCidadeNome.setText("");
            labelCidadeUF.setText("");
        }
    }
    @FXML
    public void handleButtonInserir() throws IOException, Exception {
        Cidade cidade = new Cidade();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroCidadeDialog(cidade);
        if (buttonConfirmarClicked) {
            CidadeService cidadeService = new CidadeService();
            cidadeService.inserir(cidade);
            carregarTableViewCidade();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("Cidade : " + cidade.getNomeCidade() + " Cadastrada com Sucesso!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException, Exception {
        Cidade cidade = tableViewCidade.getSelectionModel().getSelectedItem();
        if (cidade != null) {
            boolean butttonConfirmarClicked = showFXMLAnchorPaneCadastroCidadeDialog(cidade);
            if (butttonConfirmarClicked) {
                CidadeService cidadeService = new CidadeService();
                cidadeService.alterar(cidade);
                carregarTableViewCidade();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Alteração efetuada com sucesso!");
                alert.setContentText("Cidade : " + cidade.getNomeCidade() + " Alterada com sucesso!");
                alert.show();

            }
        }
    }

    @FXML
    public void handleButtonExcluir() throws IOException, Exception {
        Cidade cidade = tableViewCidade.getSelectionModel().getSelectedItem();
        CidadeService cidadeService = new CidadeService();
        cidadeService.excluir(cidade);
        this.observableListCidade.remove(cidade);
        carregarTableViewCidade();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Cidade Removida");
        alert.setContentText("Cidade : " + cidade.getNomeCidade() + " Removida com Sucesso!");
        alert.show();

    }

    public boolean showFXMLAnchorPaneCadastroCidadeDialog(Cidade cidade) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroCidadeDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneCadastroCidadeDialog.fxml"));
       
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Cidade");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        FXMLAnchorPaneCadastroCidadeDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCidade(cidade);
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }
}
