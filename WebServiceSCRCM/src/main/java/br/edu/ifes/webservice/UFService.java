/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.UF;
import br.edu.ifes.webservice.model.dao.UFDAO;
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
@Path("ufs")
public class UFService {

    @Context
    private UriInfo context;

    public UFService() {
    }

    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(UF uf) {
        UFDAO ufdao = new UFDAO();
        System.out.println("Uf "+uf.getNomeUF());
        System.out.println("siglaUf "+uf.getSigla());
        return ufdao.inserir(uf);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<UF> listar() throws SQLException {
        System.out.println("WS: br.edu.ifes.webservice.UFService.listar()");
        ArrayList<UF> lista;
        UFDAO ufdao = new UFDAO();
        lista = (ArrayList<UF>) ufdao.listar();
        return lista;
    }

    @DELETE
    @Path("excluir/{sigla_uf}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("sigla_uf") String sigla_uf) throws SQLException {
        UF uf = new UF(sigla_uf);
        UFDAO ufdao = new UFDAO();
        return ufdao.excluir(uf);
    }

    @PUT
    @Path("alterar")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(UF uf) throws SQLException {
        UFDAO ufdao = new UFDAO();
        return ufdao.alterar(uf);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{sigla_uf}")
    public UF buscar(@PathParam("sigla_uf") String sigla_uf) throws SQLException {
        UF uf = new UF(sigla_uf);
        uf.setSigla(sigla_uf);

        UFDAO ufdao = new UFDAO();
        uf = ufdao.buscar(uf);
        return uf;
    }
}
