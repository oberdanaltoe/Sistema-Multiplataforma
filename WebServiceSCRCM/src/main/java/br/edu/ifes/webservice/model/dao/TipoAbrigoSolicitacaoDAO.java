/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.SolicitacaoVisita;
import br.edu.ifes.webservice.model.TipoAbrigo;
import br.edu.ifes.webservice.model.TipoAbrigoSolicitacao;
import java.sql.Connection;
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
public class TipoAbrigoSolicitacaoDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public boolean inserir(TipoAbrigoSolicitacao tas) {
        String sql = "INSERT INTO tipo_abrigo_solicitacao(cod_solicitacao_visita, "
                + "cod_tipo_abrigo) VALUES (?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, tas.getSolicitacaoVisita().getCdSolicitacao());
            stmt.setInt(2, tas.gettAbrigo().getCdAbrigo());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoSolicitacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public TipoAbrigoSolicitacao buscar(TipoAbrigoSolicitacao tas) {
        String sql = "SELECT * FROM tipo_abrigo_solicitacao WHERE cod_solicitacao_visita = ? "
                + "AND cod_tipo_abrigo = ?;";
        TipoAbrigoSolicitacao retorno = new TipoAbrigoSolicitacao();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, tas.getSolicitacaoVisita().getCdSolicitacao());
            stmt.setInt(2, tas.gettAbrigo().getCdAbrigo());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
                TipoAbrigo ta = new TipoAbrigo();
                TipoAbrigoDAO tadao = new TipoAbrigoDAO();
                
                SolicitacaoVisitaDAO svdao = new SolicitacaoVisitaDAO();
                SolicitacaoVisita sv = new SolicitacaoVisita();
                
                sv.setCdSolicitacao(resultado.getInt("cod_solicitacao_visita"));
                sv = svdao.buscar(sv);
                ta.setCdAbrigo(resultado.getInt("cod_tipo_abrigo"));
                ta = tadao.buscar(ta);

                tas.setSolicitacaoVisita(sv);
                tas.settAbrigo(ta);

                retorno = tas;

            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoSolicitacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public boolean excluir(TipoAbrigoSolicitacao tas) {
        String sql = "DELETE FROM tipo_abrigo_solicitacao WHERE cod_solicitacao_visita = ? ";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, tas.getSolicitacaoVisita().getCdSolicitacao());
            System.err.println("tas.getSolicitacaoVisita().getCdSolicitacao()); == " + tas.getSolicitacaoVisita().getCdSolicitacao());
            
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoSolicitacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }

    public boolean alterar(TipoAbrigoSolicitacao tas) {
        String sql = "UPDATE tipo_abrigo_solicitacao SET cod_solicitacao_visita=?, cod_tipo_abrigo=?"
                + " WHERE cod_solicitacao_visita=? AND cod_tipo_abrigo=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, tas.getSolicitacaoVisita().getCdSolicitacao());
            stmt.setInt(2, tas.gettAbrigo().getCdAbrigo());
            stmt.setInt(3, tas.getSolicitacaoVisita().getCdSolicitacao());
            stmt.setInt(4, tas.gettAbrigo().getCdAbrigo());
            System.out.println("Chegou no DAO");

            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoSolicitacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }
    public List<TipoAbrigoSolicitacao> listar() {
        String sql = "SELECT * FROM tipo_abrigo_solicitacao;";

        List<TipoAbrigoSolicitacao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                TipoAbrigoSolicitacao tAbrigo = new TipoAbrigoSolicitacao();
                TipoAbrigo ta = new TipoAbrigo();
                TipoAbrigoDAO tadao = new TipoAbrigoDAO();
                
                SolicitacaoVisitaDAO svdao = new SolicitacaoVisitaDAO();
                SolicitacaoVisita sv = new SolicitacaoVisita();
                
                sv.setCdSolicitacao(resultado.getInt("cod_solicitacao_visita"));
                sv = svdao.buscar(sv);
                ta.setCdAbrigo(resultado.getInt("cod_tipo_abrigo"));
                ta = tadao.buscar(ta);

                tAbrigo.setSolicitacaoVisita(sv);
                tAbrigo.settAbrigo(ta);

                retorno.add(tAbrigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoSolicitacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<TipoAbrigoSolicitacao> listarPorSolicitacao(SolicitacaoVisita solicitacaoVisita) {
        String sql = "SELECT * FROM tipo_abrigo_solicitacao WHERE cod_solicitacao_visita = ?;";

        List<TipoAbrigoSolicitacao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);            
            stmt.setInt(1, solicitacaoVisita.getCdSolicitacao());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                TipoAbrigoSolicitacao tas = new TipoAbrigoSolicitacao();
                TipoAbrigo tipoAbrigo = new TipoAbrigo();
                TipoAbrigoDAO tadao = new TipoAbrigoDAO();
                
                SolicitacaoVisitaDAO svdao = new SolicitacaoVisitaDAO();
                
                tipoAbrigo.setCdAbrigo(resultado.getInt("cod_tipo_abrigo"));
                tipoAbrigo = tadao.buscar(tipoAbrigo);
                
                solicitacaoVisita.setCdSolicitacao(resultado.getInt("cod_solicitacao_visita"));
                solicitacaoVisita = svdao.buscar(solicitacaoVisita);
                
                tas.setSolicitacaoVisita(solicitacaoVisita);
                tas.settAbrigo(tipoAbrigo);

                retorno.add(tas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoSolicitacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
