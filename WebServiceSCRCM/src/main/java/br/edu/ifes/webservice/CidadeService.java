/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.Cidade;

import br.edu.ifes.webservice.model.dao.CidadeDAO;
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
@Path("cidades")
public class CidadeService {

    @Context
    private UriInfo context;

    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(Cidade cidade) {
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO.inserir(cidade);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<Cidade> listar() throws SQLException {
        System.out.println("ws:cidades:listar");
        ArrayList<Cidade> lista;
        CidadeDAO cidadeDAO = new CidadeDAO();
        lista = (ArrayList<Cidade>) cidadeDAO.listar();
        return lista;
    }

    //@DELETE
    @GET
    @Path("excluir/{cdCidade}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdCidade") int cdCidade) throws SQLException {
        //System.out.println("codigo=" + cdCidade);
        Cidade cidade = new Cidade(cdCidade);
        CidadeDAO cidadeDAO = new CidadeDAO();
      
        return cidadeDAO.excluir(cidade);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdCidade}")
    public Cidade buscar(@PathParam("cdCidade") int cdCidade) throws SQLException {
        Cidade cidade = new Cidade(cdCidade);
        cidade.setCdCidade(cdCidade);
        
        CidadeDAO cidadeDAO = new CidadeDAO();
        cidade = cidadeDAO.buscar(cidade);
        System.out.println("\n\n CidadeService.buscar(): " +cidade.getNomeCidade());
     //   System.out.println("teste = " + cidade.getNomeCidade());
        return cidade;
    }
    
    @PUT
    @Path("alterar")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(Cidade cidade) throws SQLException {
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO.alterar(cidade);
    }
}
