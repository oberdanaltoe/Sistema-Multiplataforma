/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Oberdan Debona Altoé
 */
public class FXMLMainController implements Initializable {
    @FXML
    private MenuItem menuCadastroUF;
    @FXML
    private MenuItem menuCadastroEspecialidade;
    @FXML
    private MenuItem menuCadastroTipoAbrigo;
    @FXML
    private MenuItem menuCadastroFamilia;
    @FXML
    private MenuItem menuCadastroFuncao;
    @FXML
    private MenuItem menuCadastroHabitoAlimenta;
    @FXML
    private MenuItem menuCadastroCidade;
    @FXML
    private MenuItem menuAgendamentoVisita;
    @FXML
    private MenuItem menuRegistrarVisitaAbrigo;
    @FXML
    private MenuItem menuRegistrarVisitaCurral;
    @FXML
    private MenuItem menuRecolhimentoCerebro;
    @FXML
    private MenuItem menuSolicitacaoVisita;
    @FXML
    private MenuItem menuRelatorios;
    @FXML
    private MenuItem menuRelatorioFuncionario;
    @FXML
    private MenuItem menuRelatorioUsuario;   
    @FXML
    private MenuItem menuCadastroUsuario;
    @FXML
    private MenuItem menuCadastroBairro;
    @FXML
    private MenuItem menuCadastroFuncionario;
    @FXML
    private MenuItem menuSolicitacaoVisitaTeste;
    @FXML
    private MenuItem menuCadastroMensagem;
            
    @FXML
    private AnchorPane anchorPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
    @FXML
    public void handleMenuItemCadastroUF() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneCadastroUF.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    public void handleMenuItemCadastroFuncao() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneCadastroFuncao.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    public void handleMenuItemCadastroEspecialidade() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneCadastroEspecialidade.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    public void handleMenuItemCadastroCidade() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneCadastroCidade.fxml"));
        anchorPane.getChildren().setAll(a);
    }
        
    public void handleMenuItemCadastroFamilia() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneCadastroFamilia.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    public void handleMenuItemCadastroHabitoAlimentar() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneCadastroHabitoAlimentar.fxml"));
        anchorPane.getChildren().setAll(a);
    }
        
    public void handleMenuItemCadastroTipoAbrigo() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneCadastroTipoAbrigo.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    public void handleMenuItemAgendamentoVisita() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneAgendamento.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleMenuItemRelatorioCidades() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneRelatorios.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleMenuItemRelatorioFuncionario() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneRelatorioFuncionario.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    public void handleMenuItemRelatorioUsuario() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneRelatorioUsuarios.fxml"));
        anchorPane.getChildren().setAll(a);
    }   
    
    public void handleMenuItemCadastroBairro() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneCadastroBairro.fxml"));
        anchorPane.getChildren().setAll(a);
    } 
    public void handleMenuItemCadastroFuncionario() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneCadastroFuncionario.fxml"));
        anchorPane.getChildren().setAll(a);
    } 
    public void handleMenuItemSolicitacaoVisita() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneSolicitacao.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    public void handleMenuItemCadastroUsuario() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneCadastroUsuario.fxml"));
        anchorPane.getChildren().setAll(a);
    } 
    public void handleMenuCadastroMensagem() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/scrcm/view/FXMLAnchorPaneMensagem.fxml"));
        anchorPane.getChildren().setAll(a);
    }
}
