/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.Funcao;
import br.edu.ifes.webservice.model.dao.FuncaoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Oberdan Debona Altoé
 */
@Path("funcoes")
public class FuncaoService {
    @Context
    private UriInfo context;

    public FuncaoService() {
    }
    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(Funcao funcao) {
        FuncaoDAO funcaoDAO = new FuncaoDAO();
        return funcaoDAO.inserir(funcao);
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<Funcao> listar() throws SQLException {
        ArrayList<Funcao> lista;
        FuncaoDAO funcaoDAO = new FuncaoDAO();
        lista = (ArrayList<Funcao>) funcaoDAO.listar();
        return lista;
    }
    @DELETE
    @Path("excluir/{cdFuncao}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdFuncao") int cdFuncao) throws SQLException {
        Funcao funcao = new Funcao(cdFuncao);
        FuncaoDAO funcaoDAO = new FuncaoDAO();
        return funcaoDAO.excluir(funcao);
    }

    @PUT
    @Path("alterar")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(Funcao funcao) throws SQLException {
        FuncaoDAO funcaoDAO = new FuncaoDAO();
        return funcaoDAO.alterar(funcao);
    }
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdfuncao}")
    public Funcao buscar(@PathParam("cdfuncao") int cdfuncao) throws SQLException {
        Funcao funcao = new Funcao(cdfuncao);
        funcao.setCdFuncao(cdfuncao);
        FuncaoDAO funcaoDAO = new FuncaoDAO();
        funcao = funcaoDAO.buscar(funcao);
        
        return funcao;
    }
}
