/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import scrcm.domain.UsuarioComum;

/**
 *
 * @author Oberdan Debona Altoé
 */
public class UsuarioComumService {

    String auxURL = "http://localhost:8080/WebServiceSCRCM";

    /*public ArrayList<UsuarioComum> listarh() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/usuarios/listar");

        String usuarioString;
        usuarioString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<UsuarioComum>> mapType = new TypeReference<ArrayList<UsuarioComum>>() {
        };

        ArrayList<UsuarioComum> lista = mapper.readValue(usuarioString, mapType);

        return lista;
    }*/
    
    public boolean excluir(UsuarioComum uc) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/usuarios/excluir/"+uc.getCdUsuarioComum());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(uc);

        // DELETE Request do Funcao Jersey
        return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);        
    }
    
    public boolean inserir(UsuarioComum uc) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/usuarios/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(uc);
        
        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
    
    public ArrayList<UsuarioComum> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/usuarios/listar");

        String usuarioString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<UsuarioComum>> mapType = new TypeReference<ArrayList<UsuarioComum>>() {
        };
        ArrayList<UsuarioComum> lista = mapper.readValue(usuarioString, mapType);

        return lista;
    }

    public ArrayList<UsuarioComum> listarUsuarios() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/usuarios/listarUsuarios");

        String usuarioString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<UsuarioComum>> mapType = new TypeReference<ArrayList<UsuarioComum>>() {
        };

        ArrayList<UsuarioComum> lista = mapper.readValue(usuarioString, mapType);

        return lista;
    }

    public UsuarioComum buscar(UsuarioComum uc) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/usuarios/buscar/" + uc.getCdUsuarioComum());

        String ucString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(ucString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        uc = mapper.readValue(ucString, UsuarioComum.class);
        return uc;
    }
    
    public UsuarioComum buscarLogin(UsuarioComum uc) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/usuarios/buscarLogin/" + uc.getEmail());

        String ucString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(ucString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        uc = mapper.readValue(ucString, UsuarioComum.class);
        return uc;
    }
    
     public UsuarioComum verificaLogin(UsuarioComum uc) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target;
        target = client.target(auxURL + "/ws/usuarios/login/" + uc);

        String ucString = target.request().get(String.class);        

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        uc = mapper.readValue(ucString, UsuarioComum.class);
        return uc;
    }
    
    public boolean alterar(UsuarioComum uc) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/usuarios/alterar");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(uc);

        // PUT Request do Funcao Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
}
