/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.AgendamentoVisita;
import br.edu.ifes.webservice.model.RecolhimentoCerebro;
import br.edu.ifes.webservice.model.dao.AgendamentoVisitaDAO;
import br.edu.ifes.webservice.model.dao.RecolhimentoCerebroDAO;
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
@Path("vrc")
public class RecolhimentoCerebroService {
    @Context
    private UriInfo context;

    @POST
    @Path("inserir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(RecolhimentoCerebro vrc) {
        RecolhimentoCerebroDAO vrcdao = new RecolhimentoCerebroDAO();
        System.out.println("vrc = " + vrc.getEstadoCarcaca());
        return vrcdao.inserir(vrc);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<RecolhimentoCerebro> listar() {
        ArrayList<RecolhimentoCerebro> lista;
        RecolhimentoCerebroDAO vrcdao = new RecolhimentoCerebroDAO();
        lista = (ArrayList<RecolhimentoCerebro>) vrcdao.listar();
        return lista;
    }

   /* @DELETE
    @Path("excluir/{cdVrc}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdVrc") int cdVrc) {
        //System.out.println("codigo=" + cdCidade);
        //Cidade cidade = new Cidade(cdCidade);
        RecolhimentoCerebro vrc = new RecolhimentoCerebro(cdVrc);
        RecolhimentoCerebroDAO vrcdao = new RecolhimentoCerebroDAO();        

        return vrcdao.excluir(vrc);
    }
    
    @PUT
    @Path("alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(AgendamentoVisita agendamentoVisita) {
        AgendamentoVisitaDAO agendamentoVisitaDAO = new AgendamentoVisitaDAO();
        return agendamentoVisitaDAO.alterar(agendamentoVisita);
    }
    */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdVrc}")
    public RecolhimentoCerebro buscar(@PathParam("cdVrc") int cdVrc) {
        RecolhimentoCerebro vrc = new RecolhimentoCerebro(cdVrc);
        RecolhimentoCerebroDAO vrcdao = new RecolhimentoCerebroDAO(); 
        //  Cidade cidade = new Cidade(cdCidade);
        //   cidade.setCdCidade(cdCidade);
        
        vrc.setCdVisitaRecolhimentoCerebro(cdVrc);
        vrc = vrcdao.buscar(vrc);
        
        //   System.out.println("teste = " + cidade.getNomeCidade());
        return vrc;
    }

    
}
