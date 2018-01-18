/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.Especialidade;
import br.edu.ifes.webservice.model.Funcao;
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
public class FuncionarioDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public boolean inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario(cod_funcionario, nome, login, senha, "
                + "cpf, cod_funcao, cod_especialidade) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCdFuncionario());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getLogin());
            stmt.setString(4, funcionario.getSenha());
            stmt.setString(5, funcionario.getCpf());
            stmt.setInt(6, funcionario.getFuncao().getCdFuncao());
            stmt.setInt(7, funcionario.getEspecialidade().getCdEspecialidade());
            stmt.execute();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Funcionario buscar(Funcionario funcionario) throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE cod_funcionario=?;";
        Funcionario retorno = new Funcionario();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCdFuncionario());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
                FuncaoDAO funcaoDAO = new FuncaoDAO();
                Funcao funcao = new Funcao();
                EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
                Especialidade especialidade = new Especialidade();

                funcionario.setCdFuncionario(resultado.getInt("cod_funcionario"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setLogin(resultado.getString("login"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setLogin(resultado.getString("login"));
                funcionario.setCpf(resultado.getString("cpf"));
                
                funcao.setCdFuncao(resultado.getInt("cod_funcao"));
                especialidade.setCdEspecialidade(resultado.getInt("cod_especialidade"));

                funcao = funcaoDAO.buscar(funcao);
                especialidadeDAO.buscar(especialidade);

                funcionario.setFuncao(funcao);
                funcionario.setEspecialidade(especialidade);

                retorno = funcionario;

            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;

    }

    public List<Funcionario> listar() throws SQLException {
        String sql = "SELECT * FROM funcionario;";

        List<Funcionario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                FuncaoDAO funcaoDAO = new FuncaoDAO();
                Funcao funcao = new Funcao();
                EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
                Especialidade especialidade = new Especialidade();

                funcionario.setCdFuncionario(resultado.getInt("cod_funcionario"));
                funcionario.setNome(resultado.getString("nome"));
                funcionario.setLogin(resultado.getString("login"));
                funcionario.setSenha(resultado.getString("senha"));
                funcionario.setCpf(resultado.getString("cpf"));

                funcao.setCdFuncao(resultado.getInt("cod_funcao"));
                especialidade.setCdEspecialidade(resultado.getInt("cod_especialidade"));

                funcao = funcaoDAO.buscar(funcao);
                especialidadeDAO.buscar(especialidade);

                funcionario.setFuncao(funcao);
                funcionario.setEspecialidade(especialidade);

                retorno.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }

    public boolean excluir(Funcionario funcionario) throws SQLException {
        String sql = "DELETE FROM funcionario where cod_funcionario=?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCdFuncionario());
            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }

    public boolean alterar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET nome=?,login=?,senha=?,cpf=?,"
                + "cod_funcao=?,cod_especialidade=? WHERE cod_funcionario =?";
        Boolean retorno = false;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getLogin());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCpf());
            stmt.setInt(5, funcionario.getFuncao().getCdFuncao());
            stmt.setInt(6, funcionario.getEspecialidade().getCdEspecialidade());
            stmt.setInt(7, funcionario.getCdFuncionario());

            if (stmt.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        this.connection.close();
        return retorno;
    }

    public List<Funcionario> listarComNomes() throws SQLException {
        String sql = "SELECT f.nome, ff.desc_funcao, ef.desc_especialidade\n"
                + "   FROM funcionario AS f\n"
                + "   INNER JOIN funcao_funcionario AS ff ON f.cod_funcao = ff.cod_funcao\n"
                + "   INNER JOIN especialidade_funcionario AS ef ON f.cod_especialidade = ef.cod_especialidade\n"
                + "   ORDER BY f.nome, ff.desc_funcao, ef.desc_especialidade";

        List<Funcionario> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Funcionario funcionario = new Funcionario();
                FuncaoDAO funcaoDAO = new FuncaoDAO();
                Funcao funcao = new Funcao();
                EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
                Especialidade especialidade = new Especialidade();

                funcionario.setNome(resultado.getString("nome"));

                funcao.setNomeFuncao(resultado.getString("desc_funcao"));
                especialidade.setNomeEspecialidade(resultado.getString("desc_especialidade"));

                //funcao = funcaoDAO.buscar(funcao);
                //especialidadeDAO.buscar(especialidade);

                funcionario.setFuncao(funcao);
                funcionario.setEspecialidade(especialidade);

                System.out.println("/n funcionario :" + funcionario.getFuncao().getNomeFuncao());

                retorno.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }
}
