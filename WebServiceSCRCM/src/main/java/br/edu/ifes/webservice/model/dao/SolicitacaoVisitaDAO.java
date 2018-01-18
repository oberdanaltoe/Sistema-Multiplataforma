package br.edu.ifes.webservice.model.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.edu.ifes.webservice.UsuarioComumService;
import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.SolicitacaoVisita;
import br.edu.ifes.webservice.model.UsuarioComum;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oberdan Debona Altoé
 */
public class SolicitacaoVisitaDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public boolean inserir(SolicitacaoVisita solicitacaoVisita) {
        String sql = "INSERT INTO solicitacao_visita( status,"
                + "possui_propriedade, solicitar_recolhimento_cerebro, "
                + "qtd_media_animais, qtd_animais_mordidos, casos_morte_regiao, "
                + "proprie_locais_proximos, tempo_ocorrido_morte, existe_abrigo_morcego, "
                + "observacoes, foto, cod_usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
           // stmt.setInt(1, solicitacaoVisita.getCdSolicitacao());
            stmt.setInt(1, solicitacaoVisita.getStatus());
            stmt.setString(2, solicitacaoVisita.getPossuiPropriedade());
            stmt.setString(3, solicitacaoVisita.getSolicitarRecolhimento());
            stmt.setInt(4, solicitacaoVisita.getQtdMediaAnimais());
            stmt.setInt(5, solicitacaoVisita.getQtdAnimaisMordidos());
            stmt.setString(6, solicitacaoVisita.getCasosMorteRegiao());
            stmt.setString(7, solicitacaoVisita.getProprieLocaisProximos());
            stmt.setString(8, solicitacaoVisita.getTempoOcorridoMorte());
            stmt.setString(9, solicitacaoVisita.getConheceAbrigo());
            stmt.setString(10, solicitacaoVisita.getObservacoes());
            stmt.setString(11, solicitacaoVisita.getFoto());
            stmt.setInt(12, solicitacaoVisita.getUsuarioComum().getCdUsuarioComum());
            System.out.println("Imprimir stmr   :   " + stmt);
            stmt.execute();
            System.out.println("stmt.execute();  :   " + stmt);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(SolicitacaoVisita solicitacaoVisita) {
        String sql = "UPDATE solicitacao_visita SET status=?,possui_propriedade=?"
                + ",solicitar_recolhimento_cerebro=?,"
                + "qtd_media_animais=?,qtd_animais_mordidos=?,"
                + "casos_morte_regiao=?,proprie_locais_proximos=?,"
                + "tempo_ocorrido_morte=?,existe_abrigo_morcego=?,"
                + "observacoes=?,foto=? WHERE cod_solicitacao_visita = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, solicitacaoVisita.getStatus());
            stmt.setString(2, solicitacaoVisita.getPossuiPropriedade());
            stmt.setString(3, solicitacaoVisita.getSolicitarRecolhimento());
            stmt.setInt(4, solicitacaoVisita.getQtdMediaAnimais());
            stmt.setInt(5, solicitacaoVisita.getQtdAnimaisMordidos());
            stmt.setString(6, solicitacaoVisita.getCasosMorteRegiao());
            stmt.setString(7, solicitacaoVisita.getProprieLocaisProximos());
            stmt.setString(8, solicitacaoVisita.getTempoOcorridoMorte());
            stmt.setString(9, solicitacaoVisita.getConheceAbrigo());
            stmt.setString(10, solicitacaoVisita.getObservacoes());
            stmt.setString(11, solicitacaoVisita.getFoto());
            //stmt.setInt(12, solicitacaoVisita.getUsuarioComum().getCdUsuarioComum());
            stmt.setInt(12, solicitacaoVisita.getCdSolicitacao());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public SolicitacaoVisita buscar(SolicitacaoVisita solicitacaoVisita) {
        String sql = "SELECT * FROM solicitacao_visita WHERE cod_solicitacao_visita =?";
        SolicitacaoVisita retorno = new SolicitacaoVisita();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, solicitacaoVisita.getCdSolicitacao());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                UsuarioComum usuarioComum = new UsuarioComum();
                UsuarioComumService ucs = new UsuarioComumService();

                solicitacaoVisita.setCdSolicitacao(resultado.getInt("cod_solicitacao_visita"));
                solicitacaoVisita.setStatus(resultado.getInt("status"));
                solicitacaoVisita.setSolicitarRecolhimento(resultado.getString("solicitar_recolhimento_cerebro"));
                solicitacaoVisita.setQtdMediaAnimais(resultado.getInt("qtd_media_animais"));
                solicitacaoVisita.setQtdAnimaisMordidos(resultado.getInt("qtd_animais_mordidos"));
                solicitacaoVisita.setCasosMorteRegiao(resultado.getString("casos_morte_regiao"));
                solicitacaoVisita.setProprieLocaisProximos(resultado.getString("proprie_locais_proximos"));
                solicitacaoVisita.setTempoOcorridoMorte(resultado.getString("tempo_ocorrido_morte"));
                solicitacaoVisita.setConheceAbrigo(resultado.getString("existe_abrigo_morcego"));
                solicitacaoVisita.setObservacoes(resultado.getString("observacoes"));
                solicitacaoVisita.setFoto(resultado.getString("foto"));
                usuarioComum.setCdUsuarioComum(resultado.getInt("cod_usuario"));
                System.out.println("Usuario comum codigo: " + usuarioComum.getCdUsuarioComum());
                usuarioComum = ucs.buscar(usuarioComum.getCdUsuarioComum());

                solicitacaoVisita.setUsuarioComum(usuarioComum);

                retorno = solicitacaoVisita;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean excluir(SolicitacaoVisita solicitacaoVisita) {
        String sql = "DELETE FROM solicitacao_visita where cod_solicitacao_visita=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, solicitacaoVisita.getCdSolicitacao());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }

    public List<SolicitacaoVisita> listar() {
        String sql = "SELECT * FROM solicitacao_visita";

        List<SolicitacaoVisita> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                SolicitacaoVisita solicitacaoVisita = new SolicitacaoVisita();
                UsuarioComum usuarioComum = new UsuarioComum();
                UsuarioComumDAO ucdao = new UsuarioComumDAO();
                solicitacaoVisita.setCdSolicitacao(resultado.getInt("cod_solicitacao_visita"));
                solicitacaoVisita.setPossuiPropriedade(resultado.getString("possui_propriedade"));
                solicitacaoVisita.setStatus(resultado.getInt("status"));
                solicitacaoVisita.setSolicitarRecolhimento(resultado.getString("solicitar_recolhimento_cerebro"));
                solicitacaoVisita.setQtdMediaAnimais(resultado.getInt("qtd_media_animais"));
                solicitacaoVisita.setQtdAnimaisMordidos(resultado.getInt("qtd_animais_mordidos"));
                solicitacaoVisita.setCasosMorteRegiao(resultado.getString("casos_morte_regiao"));
                solicitacaoVisita.setProprieLocaisProximos(resultado.getString("proprie_locais_proximos"));
                solicitacaoVisita.setTempoOcorridoMorte(resultado.getString("tempo_ocorrido_morte"));
                solicitacaoVisita.setConheceAbrigo(resultado.getString("existe_abrigo_morcego"));
                solicitacaoVisita.setObservacoes(resultado.getString("observacoes"));
                solicitacaoVisita.setFoto(resultado.getString("foto"));
                usuarioComum.setCdUsuarioComum(resultado.getInt("cod_usuario"));
                usuarioComum = ucdao.buscar(usuarioComum);
                solicitacaoVisita.setUsuarioComum(usuarioComum);

                retorno.add(solicitacaoVisita);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    public int buscarUltimo() {
        String sql = "SELECT MAX(cod_solicitacao_visita) AS codigo FROM solicitacao_visita";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                SolicitacaoVisita solicitacaoVisita = new SolicitacaoVisita();
                     int x = resultado.getInt("codigo");           
                solicitacaoVisita.setCdSolicitacao(x);
                System.out.println("\n\n\n\n");
                System.out.println("solicitacaoVisita.getCdSolicitacao()" + solicitacaoVisita.getCdSolicitacao());
                System.out.println("\n\n\n\n");
                return solicitacaoVisita.getCdSolicitacao();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitacaoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
}
