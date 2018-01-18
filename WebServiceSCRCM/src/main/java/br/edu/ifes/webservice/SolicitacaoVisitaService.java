/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;


import br.edu.ifes.webservice.model.SolicitacaoVisita;
import br.edu.ifes.webservice.model.dao.SolicitacaoVisitaDAO;
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
@Path("solicitacaoVisita")
public class SolicitacaoVisitaService {
     @Context
    private UriInfo context;

    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(SolicitacaoVisita solicitacaoVisita) {
        SolicitacaoVisitaDAO solicitacaoVisitaDAO = new SolicitacaoVisitaDAO();

        return solicitacaoVisitaDAO.inserir(solicitacaoVisita);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<SolicitacaoVisita> listar() {
        ArrayList<SolicitacaoVisita> lista;
        SolicitacaoVisitaDAO solicitacaoVisitaDAO = new SolicitacaoVisitaDAO();
        lista = (ArrayList<SolicitacaoVisita>) solicitacaoVisitaDAO.listar();
        return lista;
    }

    //@DELETE
    @GET
    @Path("excluir/{cdSolicicitacao}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdSolicicitacao") int cdSolicicitacao) {
        //System.out.println("codigo=" + cdCidade);
        //Cidade cidade = new Cidade(cdCidade);
        SolicitacaoVisita solicitacaoVisita = new SolicitacaoVisita(cdSolicicitacao);
        SolicitacaoVisitaDAO solicitacaoVisitaDAO = new SolicitacaoVisitaDAO();

        return solicitacaoVisitaDAO.excluir(solicitacaoVisita);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdSolicicitacao}")
    public SolicitacaoVisita buscar(@PathParam("cdSolicicitacao") int cdSolicicitacao) {
        SolicitacaoVisita solicitacaoVisita = new SolicitacaoVisita(cdSolicicitacao);
        SolicitacaoVisitaDAO solicitacaoVisitaDAO = new SolicitacaoVisitaDAO();

        solicitacaoVisita = solicitacaoVisitaDAO.buscar(solicitacaoVisita);
        //   System.out.println("teste = " + cidade.getNomeCidade());
        return solicitacaoVisita;
    }

    @PUT
    @Path("alterar")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(SolicitacaoVisita solicitacaoVisita) {
        SolicitacaoVisitaDAO solicitacaoVisitaDAO = new SolicitacaoVisitaDAO();
        return solicitacaoVisitaDAO.alterar(solicitacaoVisita);
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("buscarUltimo")
    public int buscarUltimo() {
        
        SolicitacaoVisitaDAO solicitacaoVisitaDAO = new SolicitacaoVisitaDAO();
        int cdSolicitacao = solicitacaoVisitaDAO.buscarUltimo();
        System.out.println("cdSolicitação Ultimo" + cdSolicitacao);
        return cdSolicitacao;
    }
}
