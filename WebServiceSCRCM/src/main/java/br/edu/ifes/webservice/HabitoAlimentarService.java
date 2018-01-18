/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.HabitoAlimentar;

import br.edu.ifes.webservice.model.dao.HabitoAlimentarDAO;
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
@Path("habitoalimenta")
public class HabitoAlimentarService {

    @Context
    private UriInfo context;

    public HabitoAlimentarService() {
    }

    @POST
    @Path("inserir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(HabitoAlimentar habitoAlimentar) {
        HabitoAlimentarDAO habitoAlimentarDAO = new HabitoAlimentarDAO();
        return habitoAlimentarDAO.inserir(habitoAlimentar);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<HabitoAlimentar> listar() {
        ArrayList<HabitoAlimentar> lista;
        HabitoAlimentarDAO habitoAlimentarDAO = new HabitoAlimentarDAO();
        lista = (ArrayList<HabitoAlimentar>) habitoAlimentarDAO.listar();
        return lista;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdabrigo}")
    public HabitoAlimentar buscar(@PathParam("cdabrigo") int cdabrigo) {
        HabitoAlimentar habitoAlimentar = new HabitoAlimentar();
        habitoAlimentar.setCdHabito(cdabrigo);

        HabitoAlimentarDAO habitoAlimentarDAO = new HabitoAlimentarDAO();
        habitoAlimentar = habitoAlimentarDAO.buscar(habitoAlimentar);

        return habitoAlimentar;
    }
    @DELETE
    @Path("excluir/{cdHabito}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdHabito") int cdHabito) {
        HabitoAlimentar habitoAlimentar = new HabitoAlimentar(cdHabito);
        HabitoAlimentarDAO habitoAlimentarDAO = new HabitoAlimentarDAO();
        
        return habitoAlimentarDAO.excluir(habitoAlimentar);
    }

    @PUT
    @Path("alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(HabitoAlimentar habito) {
        HabitoAlimentarDAO habitoAlimentarDAO = new HabitoAlimentarDAO();
        return habitoAlimentarDAO.alterar(habito);
    }
}
