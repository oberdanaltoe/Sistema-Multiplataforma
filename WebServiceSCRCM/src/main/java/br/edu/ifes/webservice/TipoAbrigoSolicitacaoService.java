/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.SolicitacaoVisita;
import br.edu.ifes.webservice.model.TipoAbrigo;
import br.edu.ifes.webservice.model.TipoAbrigoSolicitacao;
import br.edu.ifes.webservice.model.dao.SolicitacaoVisitaDAO;
import br.edu.ifes.webservice.model.dao.TipoAbrigoSolicitacaoDAO;
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
@Path("tipoAbrigoSolicitacao")
public class TipoAbrigoSolicitacaoService {

    @Context
    private UriInfo context;

    @POST
    @Path("inserir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(TipoAbrigoSolicitacao tipoAbrigo) {
        TipoAbrigoSolicitacaoDAO tipoAbrigoDAO = new TipoAbrigoSolicitacaoDAO();
        System.out.println("Tipo Abrigo: " + tipoAbrigo.gettAbrigo().getCdAbrigo());
        return tipoAbrigoDAO.inserir(tipoAbrigo);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<TipoAbrigoSolicitacao> listar() {
        ArrayList<TipoAbrigoSolicitacao> lista;
        TipoAbrigoSolicitacaoDAO tipoAbrigoSolDAO = new TipoAbrigoSolicitacaoDAO();
        lista = (ArrayList<TipoAbrigoSolicitacao>) tipoAbrigoSolDAO.listar();
        System.out.println("list: " + lista);
        return lista;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdSolicitacao}/{cdTipoAbrigo}")
    public TipoAbrigoSolicitacao buscar(@PathParam("cdSolicitacao") int cdSolicitacao, @PathParam("cdTipoAbrigo") int cdTipoAbrigo) {
        TipoAbrigoSolicitacao tipoAbrigoSol = new TipoAbrigoSolicitacao();
        SolicitacaoVisita sv = new SolicitacaoVisita(cdSolicitacao);
        TipoAbrigo ta = new TipoAbrigo(cdTipoAbrigo);

        tipoAbrigoSol.setSolicitacaoVisita(sv);
        tipoAbrigoSol.settAbrigo(ta);

        TipoAbrigoSolicitacaoDAO tipoAbrigoDAO = new TipoAbrigoSolicitacaoDAO();
        tipoAbrigoSol = tipoAbrigoDAO.buscar(tipoAbrigoSol);

        return tipoAbrigoSol;
    }

    //@DELETE
    @GET
    @Path("excluir/{cdSolicitacao}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdSolicitacao") int cdSolicitacao) {

        TipoAbrigoSolicitacao tipoAbrigoSol = new TipoAbrigoSolicitacao();
        SolicitacaoVisita sv = new SolicitacaoVisita(cdSolicitacao);
        TipoAbrigoSolicitacaoDAO tipoAbrigoDAO = new TipoAbrigoSolicitacaoDAO();
        
        tipoAbrigoSol.setSolicitacaoVisita(sv);        

        return tipoAbrigoDAO.excluir(tipoAbrigoSol);
    }

    @PUT
    @Path("alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(TipoAbrigoSolicitacao tipoAbrigoSol) {
        TipoAbrigoSolicitacaoDAO tipoAbrigoDAO = new TipoAbrigoSolicitacaoDAO();
        TipoAbrigo tipoAbrigo = new TipoAbrigo();
        SolicitacaoVisita solicitacaoVisita = new SolicitacaoVisita();
        TipoAbrigoSolicitacao tipoAbrigoSolicitacao = new TipoAbrigoSolicitacao(solicitacaoVisita, tipoAbrigo);
        System.out.println("Cheguo no alterar WEBservice");
        System.out.println("Chegou Solicitacao :" + solicitacaoVisita.getCdSolicitacao());
        System.out.println("Cheguo Tipo Abrigo :" + tipoAbrigo.getCdAbrigo());

        return tipoAbrigoDAO.alterar(tipoAbrigoSolicitacao);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listarPorSolicitacao/{cdSolicitacao}")
    public ArrayList<TipoAbrigoSolicitacao> listarPorAgendamento(@PathParam("cdSolicitacao") int cdSolicitacao) {
        ArrayList<TipoAbrigoSolicitacao> lista;
        TipoAbrigoSolicitacao tas = new TipoAbrigoSolicitacao();
        SolicitacaoVisita sv = new SolicitacaoVisita(cdSolicitacao);

        tas.setSolicitacaoVisita(sv);
        TipoAbrigoSolicitacaoDAO tasdao = new TipoAbrigoSolicitacaoDAO();

        lista = (ArrayList<TipoAbrigoSolicitacao>) tasdao.listarPorSolicitacao(sv);
        return lista;
    }
}
