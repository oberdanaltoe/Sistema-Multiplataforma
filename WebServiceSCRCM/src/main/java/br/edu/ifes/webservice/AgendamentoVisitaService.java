/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.AgendamentoVisita;
import br.edu.ifes.webservice.model.dao.AgendamentoVisitaDAO;
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
@Path("agendamentoVisita")
public class AgendamentoVisitaService {

    @Context
    private UriInfo context;

    @POST
    @Path("inserir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(AgendamentoVisita agendamentoVisita) {
        AgendamentoVisitaDAO agendamentoVisitaDAO = new AgendamentoVisitaDAO();

        return agendamentoVisitaDAO.inserir(agendamentoVisita);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<AgendamentoVisita> listar() throws SQLException {
        ArrayList<AgendamentoVisita> lista;
        AgendamentoVisitaDAO agendamentoVisitaDAO = new AgendamentoVisitaDAO();
        lista = (ArrayList<AgendamentoVisita>) agendamentoVisitaDAO.listar();
        return lista;
    }

    @GET
    @Path("excluir/{cdAgendamento}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdAgendamento") int cdAgendamento) throws SQLException {
        //System.out.println("codigo=" + cdCidade);
        //Cidade cidade = new Cidade(cdCidade);
        AgendamentoVisita agendamentoVisita = new AgendamentoVisita(cdAgendamento);
        AgendamentoVisitaDAO agendamentoVisitaDAO = new AgendamentoVisitaDAO();

        return agendamentoVisitaDAO.excluir(agendamentoVisita);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdAgendamento}")
    public AgendamentoVisita buscar(@PathParam("cdAgendamento") int cdAgendamento) throws SQLException {
        AgendamentoVisita agendamentoVisita = new AgendamentoVisita(cdAgendamento);
        //  Cidade cidade = new Cidade(cdCidade);
        //   cidade.setCdCidade(cdCidade);
        agendamentoVisita.setCdAgendamentoVisita(cdAgendamento);

        AgendamentoVisitaDAO agendamentoVisitaDAO = new AgendamentoVisitaDAO();

        agendamentoVisita = agendamentoVisitaDAO.buscar(agendamentoVisita);
        //   System.out.println("teste = " + cidade.getNomeCidade());
        return agendamentoVisita;
    }

    @PUT
    @Path("alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(AgendamentoVisita agendamentoVisita) {
        AgendamentoVisitaDAO agendamentoVisitaDAO = new AgendamentoVisitaDAO();
        return agendamentoVisitaDAO.alterar(agendamentoVisita);
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("buscarUltimo")
    public int buscarUltimo() {
        
        AgendamentoVisitaDAO avdao = new AgendamentoVisitaDAO();
        int cdSolicitacao = avdao.buscarUltimo();
        return cdSolicitacao;
    }
}
