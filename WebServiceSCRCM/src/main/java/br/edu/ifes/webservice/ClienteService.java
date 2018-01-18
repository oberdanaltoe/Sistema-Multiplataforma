/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import br.edu.ifes.webservice.model.Cliente;
import br.edu.ifes.webservice.model.dao.ClienteDAO;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Rafael
 */
@Path("clientes")
public class ClienteService {

    @Context
    private UriInfo context;

    public ClienteService() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("buscar/{cdCliente}")
    public Cliente buscar(@PathParam("cdCliente") int cdCliente) {
        Cliente cliente = new Cliente();
        cliente.setCdCliente(cdCliente);
        ClienteDAO clienteDAO = new ClienteDAO();
        cliente = clienteDAO.buscar(cliente);
        return cliente;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listar")
    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> lista;
        ClienteDAO clienteDAO = new ClienteDAO();
        lista = clienteDAO.listar();
        return lista;
    }

    @POST
    @Path("inserir")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean inserir(Cliente cliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.inserir(cliente);
    }
    
    @PUT
    @Path("alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean alterar(Cliente cliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.alterar(cliente);
    }
    
    @GET
    @Path("excluir/{cdCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean excluir(@PathParam("cdCliente") int cdCliente) {
        Cliente cliente = new Cliente(cdCliente);
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.excluir(cliente);
    }
}
