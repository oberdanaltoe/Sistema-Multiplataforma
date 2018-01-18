/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.Funcao;
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
public class FuncaoDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public List<Funcao> listar() throws SQLException {
        String sql = "SELECT * FROM funcao_funcionario;";

        List<Funcao> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcao funcao = new Funcao();

                funcao.setCdFuncao(resultado.getInt("cod_Funcao"));
                funcao.setNomeFuncao(resultado.getString("desc_funcao"));

                retorno.add(funcao);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }

    public boolean inserir(Funcao funcao) {
        String sql = "INSERT INTO funcao_funcionario(cod_funcao, desc_funcao) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcao.getCdFuncao());
            stmt.setString(2, funcao.getNomeFuncao());
            stmt.execute();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Funcao buscar(Funcao funcao) throws SQLException {
        String sql = "SELECT * FROM funcao_funcionario WHERE cod_funcao=?;";
        Funcao retorno = new Funcao();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcao.getCdFuncao());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                funcao.setNomeFuncao(resultado.getString("desc_funcao"));
                retorno = funcao;
                System.out.println("funcao buscar: " + funcao.getNomeFuncao());

            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;

    }

    public boolean alterar(Funcao funcao) throws SQLException {
        String sql = "UPDATE funcao_funcionario set desc_funcao=? where cod_funcao=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcao.getNomeFuncao());
            stmt.setInt(2, funcao.getCdFuncao());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }

    public boolean excluir(Funcao funcao) throws SQLException {
        String sql = "DELETE FROM funcao_funcionario where cod_funcao=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcao.getCdFuncao());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }
}
