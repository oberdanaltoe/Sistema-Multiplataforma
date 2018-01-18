/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.Bairro;
import br.edu.ifes.webservice.model.Cidade;
import br.edu.ifes.webservice.model.dao.BairroDAO;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Oberdan Debona Altoé
 */
@Path("bairros")
public class BairroService {
   
    @POST
    @Path("inserir")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(Bairro bairro) {
        BairroDAO bairroDAO = new BairroDAO();
        return bairroDAO.inserir(bairro);

    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdBairro}")
    public Bairro buscar(@PathParam("cdBairro") int cdBairro) {
        System.out.println("Buscar ");
        Bairro bairro = new Bairro(cdBairro);
        BairroDAO bairroDAO = new BairroDAO();
        bairro.setCdBairro(cdBairro);
        
        System.out.println("Bairro:" + bairro);
        
        bairro = bairroDAO.buscar(bairro);
                
        return bairro;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscarPorCidade/{cdCidade}")
    public ArrayList<Bairro> buscarPorCidade(@PathParam("cdCidade") int cdCidade) {
        System.out.println("Buscar ");
        Cidade cidade = new Cidade(cdCidade);
        Bairro bairro = new Bairro();
        BairroDAO bairroDAO = new BairroDAO();
        bairro.setCidade(cidade);
        
        System.out.println("Bairro:" + bairro.getCdBairro());
        
        ArrayList<Bairro> lista;
        
        lista = (ArrayList<Bairro>) bairroDAO.buscarBairroPorCidade(bairro);
        return lista;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<Bairro> listar() {
        System.out.println("Listar ");
        ArrayList<Bairro> lista;
        BairroDAO bairroDAO = new BairroDAO();
        lista = (ArrayList<Bairro>) bairroDAO.listar();
        return lista;
    }
    @PUT
    @Path("alterar")
    @Consumes("application/json;charset=UTF-8")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(Bairro bairro) {
        BairroDAO bairroDAO = new BairroDAO();
        return bairroDAO.alterar(bairro);
    }
    
    @GET
    @Path("excluir/{cdBairro}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdBairro") int cdBairro) {
        //System.out.println("codigo=" + cdCidade);
        Bairro bairro = new Bairro(cdBairro);
        BairroDAO bairroDAO = new BairroDAO();
      
        return bairroDAO.excluir(bairro);
    }
}
