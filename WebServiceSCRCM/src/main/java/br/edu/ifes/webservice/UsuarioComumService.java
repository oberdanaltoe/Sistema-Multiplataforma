/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;


import br.edu.ifes.webservice.model.UsuarioComum;
import br.edu.ifes.webservice.model.dao.UsuarioComumDAO;
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
@Path("usuarios")
public class UsuarioComumService {
    
    @Context
    private UriInfo context;

    public UsuarioComumService() {
    }
    
    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(UsuarioComum uc) {
        UsuarioComumDAO ucdao = new UsuarioComumDAO();
        return ucdao.inserir(uc);

    }
    
    @PUT
    @Path("alterar")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(UsuarioComum uc) throws SQLException {
        UsuarioComumDAO ucdao = new UsuarioComumDAO();
        return ucdao.alterar(uc);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<UsuarioComum> listar() throws SQLException {
        System.out.println("WS: br.edu.ifes.webservice.UFService.listar()");
        ArrayList<UsuarioComum> lista;
        UsuarioComumDAO ucdao = new UsuarioComumDAO();
        lista = (ArrayList<UsuarioComum>) ucdao.listar();
        return lista;
    }
    
    


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listarUsuarios")
    public ArrayList<UsuarioComum> listarUsuarios() throws SQLException {
        System.out.println("WS: br.edu.ifes.webservice.UFService.listar()");
        ArrayList<UsuarioComum> lista;
        UsuarioComumDAO ucdao = new UsuarioComumDAO();
        lista = (ArrayList<UsuarioComum>) ucdao.listarUsuarios();
        return lista;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdusuario}")
    public UsuarioComum buscar(@PathParam("cdusuario") int cdusuario) throws SQLException {
        UsuarioComum uc = new UsuarioComum();
        UsuarioComumDAO ucdao = new UsuarioComumDAO();

        uc.setCdUsuarioComum(cdusuario);
        uc = ucdao.buscar(uc);
               
        return uc;
    }
    
    @DELETE
    @Path("excluir/{cdUsuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdUsuario") int cdUsuario) throws SQLException {
        UsuarioComum uc = new UsuarioComum();
        uc.setCdUsuarioComum(cdUsuario);
        UsuarioComumDAO ucdao = new UsuarioComumDAO();
        return ucdao.excluir(uc);
    }
    
}
