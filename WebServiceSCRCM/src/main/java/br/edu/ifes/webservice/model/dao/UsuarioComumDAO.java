/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.BairroService;
import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.Bairro;
import br.edu.ifes.webservice.model.Endereco;

import br.edu.ifes.webservice.model.UsuarioComum;
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
public class UsuarioComumDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public boolean inserir(UsuarioComum uc) {
        String sql = "INSERT INTO usuario_comum(cod_usuario, nome_usuario, cpf, login, "
                + "senha, rua, numero, ponto_referencia, cod_bairro, longitude, latitude) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, uc.getCdUsuarioComum());
            stmt.setString(2, uc.getNome());
            stmt.setString(3, uc.getCpf());
            stmt.setString(4, uc.getLogin());            
            stmt.setString(5, uc.getSenha());
            stmt.setString(6, uc.getRua());
            stmt.setInt(7, uc.getNumero());
            stmt.setString(8, uc.getPontoReferencia());
            stmt.setInt(9, uc.getBairro().getCdBairro());
            stmt.setDouble(10, uc.getLongitude());
            stmt.setDouble(11, uc.getLatitude());
            
            stmt.execute();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<UsuarioComum> listar() throws SQLException {
        String sql = "SELECT * FROM usuario_comum";

        List<UsuarioComum> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                UsuarioComum uc = new UsuarioComum();
                
                Bairro b = new Bairro();
                BairroDAO bDAO = new BairroDAO();

                uc.setCdUsuarioComum(resultado.getInt("cod_usuario"));
                uc.setNome(resultado.getString("nome_usuario"));

                b.setCdBairro(resultado.getInt("cod_bairro"));
                b = bDAO.buscar(b);

                //e.setBairro(b);
                uc.setBairro(b);
                uc.setRua(resultado.getString("rua"));
                uc.setNumero(resultado.getInt("numero"));
                uc.setCpf(resultado.getString("cpf"));
                uc.setPontoReferencia(resultado.getString("ponto_referencia"));
                uc.setLogin(resultado.getString("login"));
                uc.setSenha(resultado.getString("senha"));

                retorno.add(uc);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioComumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("retorno === " + retorno);
        this.connection.close();
        return retorno;
    }

    public List<UsuarioComum> listarUsuarios() throws SQLException {
        String sql = "SELECT uc.nome_usuario, uc.cpf, uc.rua, uc.numero, uc.ponto_referencia, b.nome_bairro\n"
                + "	FROM usuario_comum as uc\n"
                + "    INNER JOIN bairro AS b ON uc.cod_bairro = b.cod_bairro\n"
                + "    GROUP BY uc.nome_usuario";

        List<UsuarioComum> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                UsuarioComum uc = new UsuarioComum();
                Endereco e = new Endereco();
                Bairro b = new Bairro();
                BairroDAO bDAO = new BairroDAO();

                
                uc.setNome(resultado.getString("nome_usuario"));
                uc.setRua(resultado.getString("rua"));
                uc.setNumero(resultado.getInt("numero"));
                uc.setPontoReferencia(resultado.getString("ponto_referencia"));

                b.setNomeBairro(resultado.getString("nome_bairro"));
                //b = bDAO.buscar(b);

                //e.setBairro(b);
                uc.setBairro(b);
                //uc.getEndereco.getRua();
                //uc.getEndereco().getNumero();

                retorno.add(uc);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioComumDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("retorno === " + retorno);
        this.connection.close();
        return retorno;
    }
    
    public UsuarioComum buscar(UsuarioComum uc) throws SQLException {
        String sql = "SELECT * FROM usuario_comum WHERE cod_usuario=?;";
        UsuarioComum retorno = new UsuarioComum();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, uc.getCdUsuarioComum());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                Bairro bairro = new Bairro();
                BairroService bs = new BairroService();
                
                uc.setNome(resultado.getString("nome_usuario"));
                bairro.setCdBairro(resultado.getInt("cod_bairro"));
                bairro = bs.buscar(bairro.getCdBairro());
                uc.setBairro(bairro);
                uc.setNumero(resultado.getInt("numero"));
                uc.setRua(resultado.getString("rua"));
                uc.setPontoReferencia(resultado.getString("ponto_referencia"));
                retorno = uc;
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;

    }
    
    public boolean alterar(UsuarioComum uc) throws SQLException {
        String sql = "UPDATE usuario_comum SET nome_usuario =?, cpf=?, login=?, "
                + "senha=?, rua=?, numero=?, ponto_referencia=?, cod_bairro=?,"
                + " longitude=?, latitude=? WHERE cod_usuario = ?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, uc.getNome());
            stmt.setString(2, uc.getCpf());
            stmt.setString(3, uc.getLogin());            
            stmt.setString(4, uc.getSenha());
            stmt.setString(5, uc.getRua());
            stmt.setInt(6, uc.getNumero());
            stmt.setString(7, uc.getPontoReferencia());
            stmt.setInt(8, uc.getBairro().getCdBairro());
            stmt.setDouble(9, uc.getLongitude());
            stmt.setDouble(10, uc.getLatitude());
            stmt.setInt(11, uc.getCdUsuarioComum());

            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioComumDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }
    
    public boolean excluir(UsuarioComum uc) throws SQLException {
        String sql = "DELETE FROM usuario_comum where cod_usuario=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, uc.getCdUsuarioComum());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioComumDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }
}
