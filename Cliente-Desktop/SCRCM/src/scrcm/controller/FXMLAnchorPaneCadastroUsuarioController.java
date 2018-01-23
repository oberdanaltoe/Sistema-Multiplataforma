/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import scrcm.domain.UsuarioComum;
import scrcm.service.UsuarioComumService;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLAnchorPaneCadastroUsuarioController implements Initializable {

    @FXML
    private Label labelNomeUsuario;
    @FXML
    private Label labelPontoReferencia;
    @FXML
    private Label labelCidade;
    @FXML
    private Label labelCPF;
    @FXML
    private Label labelTelefone;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelDataCadastro;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelSenha;
    @FXML
    private Label labelBairro;
    @FXML
    private Label labelNumero;
    @FXML
    private Label labelRua;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TableView<UsuarioComum> tableViewusuarios;
    @FXML
    private TableColumn<UsuarioComum, String> tableColumnUsuarios;
    private ObservableList<UsuarioComum> observableListUsuarioComum;
    private Date data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = new Date(System.currentTimeMillis());
        try {
            carregarTableViewUsuarios();
            selecionarItemTableViewUsuario(null);
            tableViewusuarios.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewUsuario(newValue));
        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneCadastroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewUsuarios() throws Exception {
        UsuarioComumService ucs = new UsuarioComumService();
        ArrayList<UsuarioComum> listUC = new ArrayList<UsuarioComum>();
        String mensagem = "";
        listUC = ucs.listar();

        if (listUC == null || listUC.isEmpty()) {
            mensagem = "Não existem Usuários cadastrados";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();

        } else {
            for (UsuarioComum uc : listUC) {
                tableColumnUsuarios.setCellValueFactory(new PropertyValueFactory<>("nome"));
                observableListUsuarioComum = FXCollections.observableArrayList(listUC);
                tableViewusuarios.setItems(observableListUsuarioComum);
            }
        }
    }

    public void selecionarItemTableViewUsuario(UsuarioComum uc) {
        if (uc != null) {
            labelNomeUsuario.setText(String.valueOf(uc.getNome()));
            labelCPF.setText(String.valueOf(uc.getCpf()));
            labelLogin.setText(String.valueOf(uc.getLogin()));
            labelSenha.setText(String.valueOf(uc.getSenha()));
            labelRua.setText(String.valueOf(uc.getRua()));
            labelNumero.setText(String.valueOf(uc.getNumero()));
            labelPontoReferencia.setText(String.valueOf(uc.getPontoReferencia()));
            labelBairro.setText(String.valueOf(uc.getBairro().getNomeBairro()));
            labelCidade.setText(String.valueOf(uc.getBairro().getCidade().getNomeCidade()));
            labelTelefone.setText(String.valueOf(uc.getTelefone()));
            labelEmail.setText(String.valueOf(uc.getEmail()));
            if (uc.getDataCadastro() != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(uc.getDataCadastro());
                c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
                String dataConvertida = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                labelDataCadastro.setText(dataConvertida);
            } else {
                Calendar c = Calendar.getInstance();
                c.setTime(data);
                c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
                String dataData = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                labelDataCadastro.setText(dataData);
            }

        } else {
            labelNomeUsuario.setText("");
            labelCPF.setText("");
            labelLogin.setText("");
            labelSenha.setText("");
            labelRua.setText("");
            labelNumero.setText("");
            labelPontoReferencia.setText("");
            labelBairro.setText("");
            labelCidade.setText("");
            labelTelefone.setText("");
            labelEmail.setText("");
            labelDataCadastro.setText("");
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException, Exception {
        UsuarioComum uc = new UsuarioComum();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroUsuarioDialog(uc);
        if (buttonConfirmarClicked) {
            UsuarioComumService ucs = new UsuarioComumService();
            ucs.inserir(uc);
            carregarTableViewUsuarios();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("Usuário : " + uc.getNome() + " Cadastrado com Sucesso!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException, Exception {
        UsuarioComum usuarioComum = tableViewusuarios.getSelectionModel().getSelectedItem();
        if (usuarioComum != null) {
            boolean butttonConfirmarClicked = showFXMLAnchorPaneCadastroUsuarioDialog(usuarioComum);
            if (butttonConfirmarClicked) {
                UsuarioComumService ucs = new UsuarioComumService();
                ucs.alterar(usuarioComum);
                carregarTableViewUsuarios();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
                alert.setContentText("Usuário : " + usuarioComum.getNome() + " Alterado com Sucesso!");
                alert.show();

            }
        }
    }

    public boolean showFXMLAnchorPaneCadastroUsuarioDialog(UsuarioComum uc) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroUsuarioDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneCadastroUsuarioDialog.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de usuário");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLAnchorPaneCadastroUsuarioDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setUsuarioComum(uc);
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }

    @FXML
    public void handleButtonExcluir() throws IOException, Exception {
        String errorMessage = "";
        UsuarioComum uc = tableViewusuarios.getSelectionModel().getSelectedItem();
        UsuarioComumService ucs = new UsuarioComumService();
        ucs.excluir(uc);
        this.observableListUsuarioComum.remove(uc);
        carregarTableViewUsuarios();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Usuário Removido");
        alert.setContentText("Usuário : " + uc.getNome() + " Removido com Sucesso!");
        alert.show();

    }

}
