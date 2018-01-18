/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.TipoAbrigo;
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
public class TipoAbrigoDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public TipoAbrigo buscar(TipoAbrigo tipoAbrigo) throws SQLException {
        String sql = "SELECT * FROM tipo_abrigo WHERE cod_tipo_abrigo=?;";
        TipoAbrigo retorno = new TipoAbrigo();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, tipoAbrigo.getCdAbrigo());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {

                tipoAbrigo.setNomeAbrigo(resultado.getString("desc_tipo_abrigo"));
                retorno = tipoAbrigo;

            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }

    public List<TipoAbrigo> listar() throws SQLException {
        String sql = "SELECT * FROM tipo_abrigo;";

        List<TipoAbrigo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                TipoAbrigo tAbrigo = new TipoAbrigo();
                tAbrigo.setCdAbrigo(resultado.getInt("cod_tipo_abrigo"));
                tAbrigo.setNomeAbrigo(resultado.getString("desc_tipo_abrigo"));

                retorno.add(tAbrigo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }

    public boolean inserir(TipoAbrigo tipoAbrigo) {
        String sql = "INSERT INTO tipo_abrigo(cod_tipo_abrigo, desc_tipo_abrigo) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, tipoAbrigo.getCdAbrigo());
            stmt.setString(2, tipoAbrigo.getNomeAbrigo());
            stmt.execute();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     public boolean alterar(TipoAbrigo tipoAbrigo) throws SQLException {
        String sql = "UPDATE tipo_abrigo SET desc_tipo_abrigo=? WHERE cod_tipo_abrigo=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, tipoAbrigo.getNomeAbrigo());
            stmt.setInt(2, tipoAbrigo.getCdAbrigo());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }

    public boolean excluir(TipoAbrigo tipoAbrigo) throws SQLException {
        String sql = "DELETE FROM tipo_abrigo where cod_tipo_abrigo=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, tipoAbrigo.getCdAbrigo());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoAbrigoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }
}
