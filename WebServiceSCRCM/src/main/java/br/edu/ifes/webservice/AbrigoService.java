/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.Abrigo;
import br.edu.ifes.webservice.model.dao.AbrigoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Oberdan
 */
@Path("abrigo")
public class AbrigoService {
    @Context
    private UriInfo context;

    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(Abrigo abrigo) {
        AbrigoDAO abrigoDAO = new AbrigoDAO();
        return abrigoDAO.inserir(abrigo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<Abrigo> listar() throws SQLException {
        System.out.println("ws:abrigos:listar");
        ArrayList<Abrigo> lista;
        AbrigoDAO abrigoDAO = new AbrigoDAO();
        lista = (ArrayList<Abrigo>) abrigoDAO.listar();
        return lista;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdabrigo}")
    public Abrigo buscar(@PathParam("cdabrigo") int cdabrigo) throws SQLException {
        Abrigo abrigo = new Abrigo();
        abrigo.setCdAbrigo(cdabrigo);

        AbrigoDAO abrigoDAO = new AbrigoDAO();
        abrigo = abrigoDAO.buscar(abrigo);

        return abrigo;
    }
}
