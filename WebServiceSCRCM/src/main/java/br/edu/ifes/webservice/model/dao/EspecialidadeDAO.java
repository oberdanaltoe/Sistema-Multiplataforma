/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.Especialidade;
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
public class EspecialidadeDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public List<Especialidade> listar() throws SQLException {
        String sql = "SELECT * FROM especialidade_funcionario;";

        List<Especialidade> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Especialidade especialidade = new Especialidade();

                especialidade.setCdEspecialidade(resultado.getInt("cod_especialidade"));
                especialidade.setNomeEspecialidade(resultado.getString("desc_especialidade"));

                retorno.add(especialidade);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }

    public boolean inserir(Especialidade especialidade) {
        String sql = "INSERT INTO especialidade_funcionario(cod_especialidade, desc_especialidade) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, especialidade.getCdEspecialidade());
            stmt.setString(2, especialidade.getNomeEspecialidade());
            stmt.execute();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Especialidade buscar(Especialidade especialidade) throws SQLException {
        String sql = "SELECT * FROM especialidade_funcionario WHERE cod_especialidade=?;";
        Especialidade retorno = new Especialidade();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, especialidade.getCdEspecialidade());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                especialidade.setNomeEspecialidade(resultado.getString("desc_especialidade"));
                retorno = especialidade;

            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;

    }
    public boolean alterar(Especialidade especialidade) throws SQLException {
        String sql = "UPDATE especialidade_funcionario set desc_especialidade=? where cod_especialidade=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, especialidade.getNomeEspecialidade());
            stmt.setInt(2, especialidade.getCdEspecialidade());
            
            System.out.println("stmt:  " + stmt);
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }

    public boolean excluir(Especialidade especialidade) throws SQLException {
        String sql = "DELETE FROM especialidade_funcionario where cod_especialidade=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, especialidade.getCdEspecialidade());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspecialidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }
}
