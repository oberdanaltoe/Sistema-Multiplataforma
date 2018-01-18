/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.RecolhimentoCerebro;
import br.edu.ifes.webservice.model.Visita;
import br.edu.ifes.webservice.model.dao.RecolhimentoCerebroDAO;
import br.edu.ifes.webservice.model.dao.VisitaDAO;
import javax.ws.rs.Consumes;
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
@Path("visita")
public class VisitaService {
    @Context
    private UriInfo context;

    @POST
    @Path("inserir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(Visita visita) {
        VisitaDAO visitaDAO = new VisitaDAO();
        
        return visitaDAO.inserir(visita);

    }
}
