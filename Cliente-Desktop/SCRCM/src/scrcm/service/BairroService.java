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
import static org.codehaus.jackson.map.type.TypeFactory.mapType;
import org.codehaus.jackson.type.TypeReference;
import scrcm.domain.Bairro;



/**
 *
 * @author Oberdan Debona Altoé
 */
public class BairroService {
    
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
    
    public boolean inserir(Bairro bairro) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/bairros/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(bairro);
        
        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
    
    public boolean alterar(Bairro bairro) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/bairros/alterar");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(bairro);

        // PUT Request do Cidade Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
    
    public Bairro buscar(Bairro bairro) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/bairros/buscar/"+bairro.getCdBairro());

        String bairroString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(bairroString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        bairro = mapper.readValue(bairroString, Bairro.class);
        return bairro;
    }
    
    public ArrayList<Bairro> buscarPorCidade(Bairro bairro) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/bairros/buscarPorCidade/"+bairro.getCidade().getCdCidade());

        String bairroString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Bairro>> mapType = new TypeReference<ArrayList<Bairro>>() {};
    	ArrayList<Bairro> lista = mapper.readValue(bairroString, mapType);

        return lista;
    }
    
    public ArrayList<Bairro> listar() throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/bairros/listar");

        String bairroString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Bairro>> mapType = new TypeReference<ArrayList<Bairro>>() {};
    	ArrayList<Bairro> lista = mapper.readValue(bairroString, mapType);

        return lista;
    } 
    
    public boolean excluir(Bairro bairro) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/bairros/excluir/" + bairro.getCdBairro());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(bairro);
       
               return target.request().get(Boolean.class);                

    }
}
