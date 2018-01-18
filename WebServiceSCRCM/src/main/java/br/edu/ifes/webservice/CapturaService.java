/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.Captura;
import br.edu.ifes.webservice.model.dao.CapturaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Oberdan
 */
@Path("captura")
public class CapturaService {

    @Context
    private UriInfo context;

    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(Captura captura) {
        CapturaDAO capturaDAO = new CapturaDAO();
        return capturaDAO.inserir(captura);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<Captura> listar() throws SQLException {
        System.out.println("ws:abrigos:listar");
        ArrayList<Captura> lista;
        CapturaDAO capturaDAO = new CapturaDAO();
        lista = (ArrayList<Captura>) capturaDAO.listar();
        return lista;
    }
}
