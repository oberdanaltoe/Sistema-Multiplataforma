/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.Familia;

import br.edu.ifes.webservice.model.dao.FamiliaDAO;

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
@Path("familias")
public class FamiliaService {
    
    @Context
    private UriInfo context;
    
    @POST
    @Path("inserir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(Familia familia) {
        FamiliaDAO familiaDAO = new FamiliaDAO();
        return familiaDAO.inserir(familia);
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<Familia> listar() {
        ArrayList<Familia> lista;
        FamiliaDAO familiaDAO = new FamiliaDAO();
        lista = (ArrayList<Familia>) familiaDAO.listar();
        return lista;
    }
    @DELETE
    @Path("excluir/{cdfamilia}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdfamilia") int cdfamilia) {
        Familia familia = new Familia(cdfamilia);
        FamiliaDAO familiaDAO = new FamiliaDAO();
        return familiaDAO.excluir(familia);
    }

    @PUT
    @Path("alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(Familia familia) {
        FamiliaDAO familiaDAO = new FamiliaDAO();
        return familiaDAO.alterar(familia);
    }
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdfamilia}")
    public Familia buscar(@PathParam("cdfamilia") int cdfamilia) {
        Familia familia = new Familia(cdfamilia);
        familia.setCdFamilia(cdfamilia);
        FamiliaDAO familiaDAO = new FamiliaDAO();
        familia = familiaDAO.buscar(familia);
        
        return familia;
    }
}
