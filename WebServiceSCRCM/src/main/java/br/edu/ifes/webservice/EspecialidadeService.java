/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;


import br.edu.ifes.webservice.model.Especialidade;

import br.edu.ifes.webservice.model.dao.EspecialidadeDAO;
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
@Path("especialidades")
public class EspecialidadeService {
    @Context
    private UriInfo context;
    
    public EspecialidadeService(){
    }

 @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdEspecialidade}")
    public Especialidade buscar(@PathParam("cdEspecialidade") int cdEspecialidade) throws SQLException {
        Especialidade especialidade = new Especialidade();
        especialidade.setCdEspecialidade(cdEspecialidade);
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        especialidade = especialidadeDAO.buscar(especialidade);
        
        return especialidade;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<Especialidade> listar() throws SQLException {
        ArrayList<Especialidade> lista;
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        lista = (ArrayList<Especialidade>) especialidadeDAO.listar();
        return lista;
    }

    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(Especialidade especialidade ) {
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        return especialidadeDAO.inserir(especialidade);
    }
    
    @DELETE
    @Path("excluir/{cdEspecialidade}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdEspecialidade") int cdEspecialidade) throws SQLException {
        Especialidade especialidade = new Especialidade(cdEspecialidade);
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        return especialidadeDAO.excluir(especialidade);
    }

    @PUT
    @Path("alterar")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(Especialidade especialidade) throws SQLException {
        EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
        return especialidadeDAO.alterar(especialidade);
    }
}