/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.UF;

/**
 *
 * @author Oberdan Debona Altoé
 */
public class UFDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();
    
    public UF buscar(UF uf) throws SQLException {
        String sql = "SELECT * FROM uf WHERE sigla_uf=?;";
        UF retorno = new UF();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, uf.getSigla());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                uf.setNomeUF(resultado.getString("nome_UF"));
                retorno = uf;

            }
        } catch (SQLException ex) {
            Logger.getLogger(UFDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }

    public List<UF> listar() throws SQLException {
        String sql = "SELECT * FROM uf;";

        List<UF> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                UF uf = new UF();
                uf.setSigla(resultado.getString("sigla_UF"));
                uf.setNomeUF(resultado.getString("nome_UF"));

                retorno.add(uf);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UFDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }

    public boolean inserir(UF uf) {
        String sql = "INSERT INTO uf(sigla_uf, nome_uf) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, uf.getSigla());
            stmt.setString(2, uf.getNomeUF());
            stmt.execute();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UFDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean excluir(UF uf) throws SQLException {
        String sql = "DELETE FROM uf where sigla_uf=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, uf.getSigla());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UFDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }
    public boolean alterar(UF uf) throws SQLException {
        String sql = "UPDATE uf set nome_UF=? where sigla_UF=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, uf.getNomeUF());
            stmt.setString(2, uf.getSigla());
            
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UFDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }
}
