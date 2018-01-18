/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.CidadeService;
import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.Bairro;
import br.edu.ifes.webservice.model.Cidade;
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
public class BairroDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public boolean inserir(Bairro bairro) {
        String sql = "INSERT INTO bairro(cod_bairro, nome_bairro, cod_cidade) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bairro.getCdBairro());
            stmt.setString(2, bairro.getNomeBairro());
            stmt.setInt(3, bairro.getCidade().getCdCidade());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Bairro bairro) {
        String sql = "UPDATE bairro set nome_bairro=?, cod_cidade=? where cod_bairro=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, bairro.getNomeBairro());
            stmt.setInt(2, bairro.getCidade().getCdCidade());
            stmt.setInt(3, bairro.getCdBairro());

            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }

    public Bairro buscar(Bairro bairro) {
        String sql = "SELECT * FROM bairro WHERE cod_bairro=?;";
        Bairro retorno = new Bairro();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bairro.getCdBairro());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {

                bairro.setCdBairro(resultado.getInt("cod_bairro"));
                bairro.setNomeBairro(resultado.getString("nome_bairro"));
                Cidade cidade = new Cidade();
                CidadeService cs = new CidadeService();
                cidade = cs.buscar(resultado.getInt("cod_cidade"));
                bairro.setCidade(cidade);
                bairro.getCidade().getCdCidade();
                retorno = bairro;

            }
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Bairro> buscarBairroPorCidade(Bairro bairro) {
        String sql = "SELECT * FROM bairro WHERE cod_cidade=?;";
        List<Bairro> retorno = new ArrayList<>();
        Bairro b;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bairro.getCidade().getCdCidade());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                b = new Bairro();
                b.setCdBairro(resultado.getInt("cod_bairro"));
                b.setNomeBairro(resultado.getString("nome_bairro"));

                retorno.add(b);

            }

        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<Bairro> listar() {
        String sql = "SELECT * FROM bairro;";

        List<Bairro> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cidade cidade = new Cidade();
                CidadeDAO cdao = new CidadeDAO();
                Bairro bairro = new Bairro();

                bairro.setCdBairro(resultado.getInt("cod_bairro"));
                bairro.setNomeBairro(resultado.getString("nome_bairro"));
                cidade.setCdCidade(resultado.getInt("cod_cidade"));

                cidade = cdao.buscar(cidade);
                bairro.setCidade(cidade);

                retorno.add(bairro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean excluir(Bairro bairro) {
        String sql = "DELETE FROM bairro where cod_bairro=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bairro.getCdBairro());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BairroDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }
}
