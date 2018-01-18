/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;


import br.edu.ifes.webservice.model.AgendamentoVisita;
import br.edu.ifes.webservice.model.Funcionario;
import br.edu.ifes.webservice.model.FuncionarioAgenda;
import br.edu.ifes.webservice.model.dao.FuncionarioAgendaDAO;
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
@Path("funcionarioAgenda")
public class FuncionarioAgendaService {
    @Context
    private UriInfo context;

    @POST
    @Path("inserir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(FuncionarioAgenda funcionarioAgenda) {
        FuncionarioAgendaDAO funcionarioAgendaDAO = new FuncionarioAgendaDAO();

        return funcionarioAgendaDAO.inserir(funcionarioAgenda);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<FuncionarioAgenda> listar() {
        ArrayList<FuncionarioAgenda> lista;
        FuncionarioAgendaDAO funcionarioAgendaDAO = new FuncionarioAgendaDAO();
        lista = (ArrayList<FuncionarioAgenda>) funcionarioAgendaDAO.listar();
        return lista;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listarPorAgendamento/{cdAgenda}")
    public ArrayList<FuncionarioAgenda> listarPorAgendamento(@PathParam("cdAgenda") int cdAgenda) {
        ArrayList<FuncionarioAgenda> lista;
        FuncionarioAgenda funcionarioAgenda = new FuncionarioAgenda();
      //  Funcionario funcionario = new Funcionario(cdFuncionario);
        AgendamentoVisita agendamentoVisita = new AgendamentoVisita(cdAgenda);
        
        funcionarioAgenda.setAgendamentoVisita(agendamentoVisita);
        FuncionarioAgendaDAO funcionarioAgendaDAO = new FuncionarioAgendaDAO();
        lista = (ArrayList<FuncionarioAgenda>) funcionarioAgendaDAO.listarPorAgendamento(agendamentoVisita);
        return lista;
    }

    @GET
    @Path("excluir/{cdfuncionarioagenda}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdfuncionarioagenda") int cdfuncionarioagenda) {
        
        FuncionarioAgenda funcionarioAgenda = new FuncionarioAgenda();
        AgendamentoVisita av = new AgendamentoVisita(cdfuncionarioagenda);
        FuncionarioAgendaDAO funcionarioAgendaDAO = new FuncionarioAgendaDAO();
        
        funcionarioAgenda.setAgendamentoVisita(av);
               
        return funcionarioAgendaDAO.excluir(funcionarioAgenda);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdAgenda}")
    public FuncionarioAgenda buscar(@PathParam("cdAgenda") int cdAgenda) {
        
        //@PathParam("cdSolicitacao") int cdSolicitacao, @PathParam("cdTipoAbrigo") int cdTipoAbrigo
        
        FuncionarioAgenda funcionarioAgenda = new FuncionarioAgenda();
      //  Funcionario funcionario = new Funcionario(cdFuncionario);
        AgendamentoVisita agendamentoVisita = new AgendamentoVisita(cdAgenda);
        
        funcionarioAgenda.setAgendamentoVisita(agendamentoVisita);
       // funcionarioAgenda.setFuncionario(funcionario);
        
        FuncionarioAgendaDAO funcionarioAgendaDAO = new FuncionarioAgendaDAO();

        funcionarioAgenda = funcionarioAgendaDAO.buscar(funcionarioAgenda);
        System.out.println("Resultado (String)funcionariowebservice: ");
        System.out.println(funcionarioAgenda);
        return  funcionarioAgenda;
    }

    @PUT
    @Path("alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(FuncionarioAgenda funcionarioAgenda) {
        FuncionarioAgendaDAO funcionarioAgendaDAO = new FuncionarioAgendaDAO();
       
        return funcionarioAgendaDAO.alterar(funcionarioAgenda);
    }
}
