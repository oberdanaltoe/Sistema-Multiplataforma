/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.Familia;
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
public class FamiliaDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public boolean inserir(Familia familia) {
        String sql = "INSERT INTO familia_morcego(cod_familia, nome_familia) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, familia.getCdFamilia());
            stmt.setString(2, familia.getNomeFamilia());
          
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Familia> listar() {
        String sql = "SELECT * FROM familia_morcego;";

        List<Familia> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {

                Familia familia = new Familia();
                FamiliaDAO familiaDAO = new FamiliaDAO();
               
                familia.setCdFamilia(resultado.getInt("cod_familia"));
                familia.setNomeFamilia(resultado.getString("nome_familia"));                           
                
                retorno.add(familia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
     public Familia buscar(Familia familia) {
        String sql = "SELECT * FROM familia_morcego WHERE cod_familia=?;";
        Familia retorno = new Familia();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, familia.getCdFamilia());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
                familia.setNomeFamilia(resultado.getString("nome_familia"));
                retorno = familia;

            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }
     
     public boolean alterar(Familia familia) {
        String sql = "UPDATE familia_morcego set nome_familia=? where cod_familia=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, familia.getNomeFamilia());
            stmt.setInt(2, familia.getCdFamilia());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }

    public boolean excluir(Familia familia) {
        String sql = "DELETE FROM familia_morcego where cod_familia=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, familia.getCdFamilia());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FamiliaDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }
}
