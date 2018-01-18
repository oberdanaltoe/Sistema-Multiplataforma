/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.TipoAbrigo;
import br.edu.ifes.webservice.model.dao.TipoAbrigoDAO;
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
@Path("abrigos")
public class TipoAbrigoService {

    @Context
    private UriInfo context;

    public TipoAbrigoService() {

    }

    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(TipoAbrigo tipoAbrigo) {
        TipoAbrigoDAO tipoAbrigoDAO = new TipoAbrigoDAO();
        return tipoAbrigoDAO.inserir(tipoAbrigo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<TipoAbrigo> listar() throws SQLException {
        ArrayList<TipoAbrigo> lista;
        TipoAbrigoDAO tipoAbrigoDAO = new TipoAbrigoDAO();
        lista = (ArrayList<TipoAbrigo>) tipoAbrigoDAO.listar();
        return lista;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdabrigo}")
    public TipoAbrigo buscar(@PathParam("cdabrigo") int cdabrigo) throws SQLException {
        TipoAbrigo tipoAbrigo = new TipoAbrigo();
        tipoAbrigo.setCdAbrigo(cdabrigo);

        TipoAbrigoDAO tipoAbrigoDAO = new TipoAbrigoDAO();
        tipoAbrigo = tipoAbrigoDAO.buscar(tipoAbrigo);

        return tipoAbrigo;
    }

    @DELETE
    @Path("excluir/{cdAbrigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdAbrigo") int cdtipoabrigo) throws SQLException {
        
        TipoAbrigo tipoAbrigo = new TipoAbrigo(cdtipoabrigo);
        tipoAbrigo.setCdAbrigo(cdtipoabrigo);
        TipoAbrigoDAO tipoAbrigoDAO = new TipoAbrigoDAO();

        return tipoAbrigoDAO.excluir(tipoAbrigo);
    }
    
    @PUT
    @Path("alterar")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(TipoAbrigo tipoAbrigo) throws SQLException {
        TipoAbrigoDAO tipoAbrigoDAO = new TipoAbrigoDAO();
       
        return tipoAbrigoDAO.alterar(tipoAbrigo);
    }
}
