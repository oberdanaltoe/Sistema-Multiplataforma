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
import scrcm.domain.Bairro;
import scrcm.service.BairroService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroBairroController implements Initializable {

    @FXML
    private TableView<Bairro> tableViewBairro;
    @FXML
    private TableColumn<Bairro, String> tableColumnBairro;    
    @FXML
    private Label labelBairroNome;
    @FXML
    private Label labelBairroCidade;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;

    private ObservableList<Bairro> observableListBairro;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            carregarTableViewBairro();
            selecionarItemTableViewBairro(null);
            tableViewBairro.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewBairro(newValue));
        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroBairroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
    public void carregarTableViewBairro() throws Exception {
        BairroService bairroService = new BairroService();
        ArrayList<Bairro> listBairro = new ArrayList<Bairro>();
        String mensagem = "";
        listBairro = bairroService.listar();

        if (listBairro == null || listBairro.isEmpty()) {
            mensagem = "Não existem Bairros cadastradas";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (Bairro bairro : listBairro) {                              
                tableColumnBairro.setCellValueFactory(new PropertyValueFactory<>("nomeBairro"));
                observableListBairro = FXCollections.observableArrayList(listBairro);
                tableViewBairro.setItems(observableListBairro);
            }
        }
    }
    
    public void selecionarItemTableViewBairro(Bairro bairro) {
        if (bairro!= null) {            
            labelBairroNome.setText(String.valueOf(bairro.getNomeBairro()));
            labelBairroCidade.setText(String.valueOf(bairro.getCidade().getNomeCidade()));
        } else {
            labelBairroNome.setText("");
            labelBairroCidade.setText("");
        }
    }
    
    @FXML
    public void handleButtonInserirr() throws IOException, Exception {
        Bairro b = new Bairro();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroBairroDialog(b);
        if (buttonConfirmarClicked) {
            BairroService BService = new BairroService();
            BService.inserir(b);
            carregarTableViewBairro();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("Bairro : " + b.getNomeBairro() + " Cadastrada com Sucesso!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonAlterar() throws IOException, Exception {
        Bairro bairro = tableViewBairro.getSelectionModel().getSelectedItem();
        if (bairro != null) {
            boolean butttonConfirmarClicked = showFXMLAnchorPaneCadastroBairroDialog(bairro);
            if (butttonConfirmarClicked) {                
                BairroService bairroService = new BairroService();
                bairroService.alterar(bairro);                
                carregarTableViewBairro();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Alteração efetuada com sucesso!");
                alert.setContentText("Bairro : " + bairro.getNomeBairro() + " Alterada com sucesso!");
                alert.show();
            }
        }
    }
    
    public boolean showFXMLAnchorPaneCadastroBairroDialog(Bairro bairro) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroBairroDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneCadastroBairroDialog.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Bairro");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        FXMLAnchorPaneCadastroBairroDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setBairro(bairro);
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }
    
    @FXML
    public void handleButtonExcluir() throws IOException, Exception {
        String errorMessage = "";
        Bairro bairro = tableViewBairro.getSelectionModel().getSelectedItem();
        BairroService bairroService = new BairroService();
        bairroService.excluir(bairro);
        this.observableListBairro.remove(bairro);
        carregarTableViewBairro();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Bairro Removido");
        alert.setContentText("Bairro : " + bairro.getNomeBairro() + " Removida com Sucesso!");
        alert.show();
    }
    
}
