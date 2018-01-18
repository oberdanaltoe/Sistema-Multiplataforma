/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.AgendamentoVisita;
import br.edu.ifes.webservice.model.FuncionarioAgenda;
import br.edu.ifes.webservice.model.Funcionario;
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
public class FuncionarioAgendaDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();
    private List<FuncionarioAgenda> listFuncioAgenda;

    public boolean inserir(FuncionarioAgenda funcionarioAgenda) {
        String sql = "INSERT INTO funcionario_agenda(cod_funcionario,"
                + " cod_agendamento_visita) VALUES (?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionarioAgenda.getFuncionario().getCdFuncionario());
            stmt.setInt(2, funcionarioAgenda.getAgendamentoVisita().getCdAgendamentoVisita());

            stmt.execute();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioAgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<FuncionarioAgenda> listar() {
        String sql = "SELECT * FROM funcionario_agenda;";

        List<FuncionarioAgenda> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                FuncionarioAgenda funcionarioAgenda = new FuncionarioAgenda();
                Funcionario funcionario = new Funcionario();
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                AgendamentoVisita agendamentoVisita = new AgendamentoVisita();
                AgendamentoVisitaDAO agendamentoVisitaDAO = new AgendamentoVisitaDAO();

                funcionario.setCdFuncionario(resultado.getInt("cod_funcionario"));
                funcionario = funcionarioDAO.buscar(funcionario);
                agendamentoVisita.setCdAgendamentoVisita(resultado.getInt("cod_agendamento_visita"));
                agendamentoVisita = agendamentoVisitaDAO.buscar(agendamentoVisita);

                funcionarioAgenda.setFuncionario(funcionario);
                funcionarioAgenda.setAgendamentoVisita(agendamentoVisita);
               // listFuncioAgenda.add(funcionarioAgenda);
                
                retorno.add(funcionarioAgenda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioAgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public FuncionarioAgenda buscar(FuncionarioAgenda funcionarioAgenda) {
        String sql = "SELECT * FROM funcionario_agenda WHERE cod_agendamento_visita = ?;";
        FuncionarioAgenda retorno = new FuncionarioAgenda();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, funcionarioAgenda.getAgendamentoVisita().getCdAgendamentoVisita());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {

                Funcionario funcionario = new Funcionario();
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                AgendamentoVisita agendamentoVisita = new AgendamentoVisita();
                AgendamentoVisitaDAO agendamentoVisitaDAO = new AgendamentoVisitaDAO();

                funcionario.setCdFuncionario(resultado.getInt("cod_funcionario"));
                funcionario = funcionarioDAO.buscar(funcionario);
                agendamentoVisita.setCdAgendamentoVisita(resultado.getInt("cod_agendamento_visita"));
                agendamentoVisita = agendamentoVisitaDAO.buscar(agendamentoVisita);

                funcionarioAgenda.setFuncionario(funcionario);
                funcionarioAgenda.setAgendamentoVisita(agendamentoVisita);

                retorno = funcionarioAgenda;

            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioAgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;

    }

    public boolean excluir(FuncionarioAgenda funcionarioAgenda) {
        String sql = "DELETE FROM funcionario_agenda WHERE cod_agendamento_visita = ? ";
                
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, funcionarioAgenda.getAgendamentoVisita().getCdAgendamentoVisita());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioAgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }

    public boolean alterar(FuncionarioAgenda funcionarioAgenda) {
        String sql = "UPDATE funcionario_agenda SET cod_funcionario=?, cod_agendamento_visita=?"
                + " WHERE cod_funcionario=? AND cod_agendamento_visita=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionarioAgenda.getFuncionario().getCdFuncionario());
            stmt.setInt(2, funcionarioAgenda.getAgendamentoVisita().getCdAgendamentoVisita());

            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioAgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        return retorno;
    }
    
    public List<FuncionarioAgenda> listarPorAgendamento(AgendamentoVisita agendamentoVisita) {
        String sql = "SELECT * FROM funcionario_agenda WHERE cod_agendamento_visita = ?;";

        List<FuncionarioAgenda> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);            
            stmt.setInt(1, agendamentoVisita.getCdAgendamentoVisita());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                FuncionarioAgenda funcionarioAgenda = new FuncionarioAgenda();
                Funcionario funcionario = new Funcionario();
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                
                AgendamentoVisitaDAO agendamentoVisitaDAO = new AgendamentoVisitaDAO();

                funcionario.setCdFuncionario(resultado.getInt("cod_funcionario"));
                funcionario = funcionarioDAO.buscar(funcionario);
                agendamentoVisita.setCdAgendamentoVisita(resultado.getInt("cod_agendamento_visita"));
                agendamentoVisita = agendamentoVisitaDAO.buscar(agendamentoVisita);

                funcionarioAgenda.setFuncionario(funcionario);
                funcionarioAgenda.setAgendamentoVisita(agendamentoVisita);
               // listFuncioAgenda.add(funcionarioAgenda);
                
                retorno.add(funcionarioAgenda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioAgendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
