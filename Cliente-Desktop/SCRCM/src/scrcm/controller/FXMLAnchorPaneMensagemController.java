/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scrcm.domain.Mensagens;
import scrcm.service.MensagemService;

/**
 * FXML Controller class
 *
 * @author Oberdan
 */
public class FXMLAnchorPaneMensagemController implements Initializable {

    @FXML
    private Label labelTitulo;
    @FXML
    private Label labelDataExpira;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TextArea textAreaMensagem;
    @FXML
    private TableView<Mensagens> tableViewMensagens;
    @FXML
    private TableColumn<Mensagens, String> tablesColumnMensagens;
    private ObservableList<Mensagens> observableListMensagens;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            carregarTableViewMensagens();
            selecionarItemTableViewMensagem(null);
            tableViewMensagens.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> selecionarItemTableViewMensagem(newValue));
        } catch (Exception ex) {
            Logger.getLogger(FXMLAnchorPaneMensagemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTableViewMensagens() throws Exception {
        MensagemService ms = new MensagemService();
        ArrayList<Mensagens> listM = new ArrayList<Mensagens>();
        String mensagem = "";
        listM = ms.listar();

        if (listM == null || listM.isEmpty()) {
            mensagem = "Não existem Mensagens cadastrados";
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText(mensagem);
            alerta.show();
        } else {
            for (Mensagens m : listM) {
                tablesColumnMensagens.setCellValueFactory(new PropertyValueFactory<>("titulo"));
                observableListMensagens = FXCollections.observableArrayList(listM);
                tableViewMensagens.setItems(observableListMensagens);
            }
        }
    }

    Calendar hoje = Calendar.getInstance();

    public void selecionarItemTableViewMensagem(Mensagens mensagens) {
        if (mensagens != null) {
            labelTitulo.setText(mensagens.getTitulo());
            String data = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(mensagens.getDataExpira());
            Calendar c = Calendar.getInstance();
            c.setTime(mensagens.getDataExpira());
            c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
            String dataConvertida = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
            labelDataExpira.setText(dataConvertida);
            textAreaMensagem.setText(mensagens.getMensagem());
        } else {
            labelTitulo.setText("");
            labelDataExpira.setText("");
            textAreaMensagem.setText("");
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException, Exception {
        Mensagens ms = new Mensagens();
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroMensagemDialog(ms);
        if (buttonConfirmarClicked) {
            MensagemService mensagemService = new MensagemService();
            mensagemService.inserir(ms);
            carregarTableViewMensagens();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro Efetuado com Sucesso!!");
            alert.setContentText("Usuário : " + ms.getTitulo() + " Cadastrado com Sucesso!");
            alert.show();
        }
    }

    public boolean showFXMLAnchorPaneCadastroMensagemDialog(Mensagens ms) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneMensagemDialogController.class.getResource("/scrcm/view/FXMLAnchorPaneMensagemDialog.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Mensagens");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        FXMLAnchorPaneMensagemDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMensagens(ms);
        dialogStage.showAndWait();

        return controller.isBtnConfirmarClicked();

    }

}
