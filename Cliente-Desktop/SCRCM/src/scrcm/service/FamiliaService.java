/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.service;


import java.io.IOException;
import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import scrcm.domain.Familia;


/**
 *
 * @author Oberdan Debona Altoé
 */
public class FamiliaService {
    
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
    
    public boolean inserir(Familia familia) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/familias/inserir");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(familia);

        // POST Request do familia Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }
    public ArrayList<Familia> listar() throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/familias/listar");

        String familiaString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Familia>> mapType = new TypeReference<ArrayList<Familia>>() {};
    	ArrayList<Familia> lista = mapper.readValue(familiaString, mapType);

        return lista;
    } 
    public Familia buscar(Familia familia) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/familias/buscar/"+familia.getCdFamilia());

        String familiaString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(familiaString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        familia = mapper.readValue(familiaString, Familia.class);
        return familia;
    }
    public boolean alterar(Familia familia) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/familias/alterar");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(familia);

        // PUT Request do familia Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }

    public boolean excluir(Familia familia) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/familias/excluir/"+familia.getCdFamilia());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(familia);

        // DELETE Request do familia Jersey
        return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);        
    }
}
