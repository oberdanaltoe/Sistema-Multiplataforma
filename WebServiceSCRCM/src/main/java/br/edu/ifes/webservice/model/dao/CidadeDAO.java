/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.Cidade;
import br.edu.ifes.webservice.model.UF;
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
public class CidadeDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();
    

    public List<Cidade> listar() throws SQLException  {
        String sql = "SELECT * FROM cidade;";

        List<Cidade> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cidade cidade = new Cidade();
                UF uf = new UF();
                UFDAO ufDAO = new UFDAO();

                cidade.setCdCidade(resultado.getInt("cod_cidade"));
                cidade.setNomeCidade(resultado.getString("nome_cidade"));
                uf.setSigla(resultado.getString("UF"));
                
                uf = ufDAO.buscar(uf);
                cidade.setuF(uf);

                retorno.add(cidade);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UFDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }

    public boolean inserir(Cidade cidade) {
        String sql = "INSERT INTO cidade(cod_cidade, nome_cidade, uf) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cidade.getCdCidade());
            stmt.setString(2, cidade.getNomeCidade());
            stmt.setString(3, cidade.getuF().getSigla());
            stmt.execute();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Cidade buscar(Cidade cidade) throws SQLException {
        String sql = "SELECT * FROM cidade WHERE cod_cidade=?;";
        Cidade retorno = new Cidade();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cidade.getCdCidade());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                cidade.setCdCidade(resultado.getInt("cod_cidade"));
                cidade.setNomeCidade(resultado.getString("nome_cidade"));
                
                retorno = cidade;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;

}
    public boolean excluir(Cidade cidade) throws SQLException {
        String sql = "DELETE FROM cidade where cod_cidade=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cidade.getCdCidade());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }
    public boolean alterar(Cidade cidade) throws SQLException {
        String sql = "UPDATE cidade set nome_cidade=?, UF=? where cod_cidade=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cidade.getNomeCidade());
            stmt.setString(2, cidade.getuF().getSigla());
            stmt.setInt(3, cidade.getCdCidade());
            
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }

}
