/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.HabitoAlimentar;
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
public class HabitoAlimentarDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public boolean inserir(HabitoAlimentar habito) {
        String sql = "INSERT INTO habito_alimentar_morcego(cod_habito_alimentar, desc_habito_alimentar) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, habito.getCdHabito());
            stmt.setString(2, habito.getNomeHabito());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(HabitoAlimentarDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public HabitoAlimentar buscar(HabitoAlimentar habito) {
        String sql = "SELECT * FROM habito_alimentar_morcego WHERE cod_habito_alimentar=?;";
        HabitoAlimentar retorno = new HabitoAlimentar();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, habito.getCdHabito());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                habito.setNomeHabito(resultado.getString("desc_habito_alimentar"));
                retorno = habito;

            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitoAlimentarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public List<HabitoAlimentar> listar() {
        String sql = "SELECT * FROM habito_alimentar_morcego;";

        List<HabitoAlimentar> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                HabitoAlimentar habito = new HabitoAlimentar();

                habito.setCdHabito(resultado.getInt("cod_habito_alimentar"));
                habito.setNomeHabito(resultado.getString("desc_habito_alimentar"));

                retorno.add(habito);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitoAlimentarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean alterar(HabitoAlimentar habito) {
        String sql = "UPDATE habito_alimentar_morcego set desc_habito_alimentar=? where cod_habito_alimentar=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, habito.getNomeHabito());
            stmt.setInt(2, habito.getCdHabito());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitoAlimentarDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }

    public boolean excluir(HabitoAlimentar habito) {
        String sql = "DELETE FROM habito_alimentar_morcego where cod_habito_alimentar=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, habito.getCdHabito());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitoAlimentarDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }
}
