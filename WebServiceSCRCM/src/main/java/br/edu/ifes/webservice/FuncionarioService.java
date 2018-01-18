/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.Funcionario;
import br.edu.ifes.webservice.model.dao.FuncionarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE;

/**
 *
 * @author Oberdan Debona Altoé
 */
@Path("funcionarios")
public class FuncionarioService {
    
    
    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(Funcionario funcionario) {
        FuncionarioDAO fdao = new FuncionarioDAO();
        return fdao.inserir(funcionario);

    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdFuncionario}")
    public Funcionario buscar(@PathParam("cdFuncionario") int cdFuncionario) throws SQLException {
        Funcionario funcionario = new Funcionario(cdFuncionario);
        
        funcionario.setCdFuncionario(cdFuncionario);
        FuncionarioDAO fdao = new FuncionarioDAO();
        
        System.out.println("Funcionairo" +cdFuncionario);
        
        funcionario = fdao.buscar(funcionario);
        
        return funcionario;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<Funcionario> listar() throws SQLException {
        ArrayList<Funcionario> lista;
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        lista = (ArrayList<Funcionario>) funcDAO.listar();
        return lista;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listarComNomes")
    public ArrayList<Funcionario> listarComNomes() throws SQLException {
        ArrayList<Funcionario> lista;
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        lista = (ArrayList<Funcionario>) funcDAO.listarComNomes();
        return lista;
    }
    
    @PUT
    @Path("alterar")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(Funcionario funcionario) throws SQLException {
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        return funcDAO.alterar(funcionario);
    }
    
    @DELETE
    @Path("excluir/{cdFuncionario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdFuncionario") int cdFuncionario) throws SQLException {
        Funcionario funcionario = new Funcionario(cdFuncionario);
        FuncionarioDAO fdao = new FuncionarioDAO();
        return fdao.excluir(funcionario);
    }
}
