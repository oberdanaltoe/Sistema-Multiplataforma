/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.AgendamentoVisita;
import br.edu.ifes.webservice.model.Bairro;
import br.edu.ifes.webservice.model.Funcionario;
import java.sql.Connection;
import java.sql.Date;
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
public class AgendamentoVisitaDAO {
    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();
    
    public boolean inserir(AgendamentoVisita agendamentoVisita) {
        String sql = "INSERT INTO agendamento_visita( "
                + "rua, numero, tipo_visita, data, longitude, latitude, "
                + "cod_bairro) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, agendamentoVisita.getRua());
            stmt.setInt(2, agendamentoVisita.getNumero());
            stmt.setString(3, agendamentoVisita.getTipoVisita());
            stmt.setDate(4, agendamentoVisita.getData());
            stmt.setFloat(5, agendamentoVisita.getLongitude());
            stmt.setFloat(6, agendamentoVisita.getLatitude());
            //stmt.setInt(7, agendamentoVisita.getFuncionario().getCdFuncionario());
            stmt.setInt(7, agendamentoVisita.getBairro().getCdBairro());            
            
            stmt.execute();
            this.connection.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(AgendamentoVisita agendamentoVisita) {
        String sql = "UPDATE agendamento_visita SET rua=?,numero=?, tipo_visita=?, data=?, longitude=?,"
                + "latitude=?,cod_funcionario=?,"
                + "cod_bairro=? WHERE cod_agendamento_visita = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            
            stmt.setString(1, agendamentoVisita.getRua());
            stmt.setInt(2, agendamentoVisita.getNumero());
            stmt.setString(3, agendamentoVisita.getTipoVisita());
            stmt.setDate(4, (Date) agendamentoVisita.getData());
            stmt.setFloat(5, agendamentoVisita.getLongitude());
            stmt.setFloat(6, agendamentoVisita.getLatitude());
            stmt.setInt(7, agendamentoVisita.getFuncionario().getCdFuncionario());
            stmt.setInt(8, agendamentoVisita.getBairro().getCdBairro()); 
            stmt.setInt(9, agendamentoVisita.getCdAgendamentoVisita());

            stmt.execute();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public AgendamentoVisita buscar(AgendamentoVisita agendamentoVisita) throws SQLException {
        String sql = "SELECT * FROM agendamento_visita WHERE cod_agendamento_visita =?";
        AgendamentoVisita retorno = new AgendamentoVisita();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, agendamentoVisita.getCdAgendamentoVisita());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
                Funcionario funcionario = new Funcionario();
                Bairro bairro = new Bairro();
                
                agendamentoVisita.setCdAgendamentoVisita(resultado.getInt("cod_agendamento_visita"));
                agendamentoVisita.setRua(resultado.getString("rua"));
                agendamentoVisita.setNumero(resultado.getInt("numero"));
                agendamentoVisita.setTipoVisita("tipo_visita");
                //agendamentoVisita.setData("data");
                agendamentoVisita.setLongitude(resultado.getFloat("longitude"));
                agendamentoVisita.setLatitude(resultado.getFloat("latitude"));
                agendamentoVisita.setNumero(resultado.getInt("numero"));
                
                funcionario.setCdFuncionario(resultado.getInt("cod_funcionario"));
                agendamentoVisita.setFuncionario(funcionario);
                bairro.setCdBairro(resultado.getInt("cod_bairro"));
                agendamentoVisita.setBairro(bairro);

                retorno = agendamentoVisita;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }
    public boolean excluir(AgendamentoVisita agendamentoVisita) throws SQLException {
        String sql = "DELETE FROM agendamento_visita where cod_agendamento_visita=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, agendamentoVisita.getCdAgendamentoVisita());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }

    public List<AgendamentoVisita> listar() throws SQLException {
        String sql = "SELECT * FROM agendamento_visita";

        List<AgendamentoVisita> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                AgendamentoVisita agendamentoVisita = new AgendamentoVisita();
                Bairro bairro = new Bairro();
                Funcionario funcionario = new Funcionario();
                BairroDAO bdao = new BairroDAO();
                
                agendamentoVisita.setCdAgendamentoVisita(resultado.getInt("cod_agendamento_visita"));
                agendamentoVisita.setRua(resultado.getString("rua"));
                agendamentoVisita.setNumero(resultado.getInt("numero"));
                agendamentoVisita.setTipoVisita(resultado.getString("tipo_visita"));
                agendamentoVisita.setData(resultado.getDate("data"));                
                agendamentoVisita.setLongitude(resultado.getFloat("longitude"));
                agendamentoVisita.setLatitude(resultado.getFloat("latitude"));
                agendamentoVisita.setNumero(resultado.getInt("numero"));
                
                funcionario.setCdFuncionario(resultado.getInt("cod_funcionario"));
                agendamentoVisita.setFuncionario(funcionario);
                bairro.setCdBairro(resultado.getInt("cod_bairro"));
                bdao.buscar(bairro);
                agendamentoVisita.setBairro(bairro);

                retorno.add(agendamentoVisita);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }
    
    public int buscarUltimo() {
        String sql = "SELECT MAX(cod_agendamento_visita) AS codigo FROM agendamento_visita";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                AgendamentoVisita agendamentoVisita = new AgendamentoVisita();
                     int x = resultado.getInt("codigo");           
                     agendamentoVisita.setCdAgendamentoVisita(x);
                
                return agendamentoVisita.getCdAgendamentoVisita();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoVisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
}
