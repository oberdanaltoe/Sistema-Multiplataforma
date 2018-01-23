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
import scrcm.domain.Abrigo;


/**
 *
 * @author Oberdan
 */
public class AbrigoService {
    String auxURL = "http://localhost:8080/WebServiceSCRCM";

    public boolean inserir(Abrigo abrigo) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/abrigo/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(abrigo);
        
        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public ArrayList<Abrigo> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/abrigo/listar");

        String abrigoString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Abrigo>> mapType = new TypeReference<ArrayList<Abrigo>>() {
        };
        ArrayList<Abrigo> lista = mapper.readValue(abrigoString, mapType);

        return lista;
    }

    public boolean alterar(Abrigo abrigo) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/abrigo/alterar");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(abrigo);

        // PUT Request do Cidade Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public boolean excluir(Abrigo abrigo) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/abrigo/excluir/" + abrigo.getCdAbrigo());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(abrigo);
  
               return target.request().get(Boolean.class);                

    }

    public Abrigo buscar(Abrigo abrigo) throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/abrigo/buscar/" + abrigo.getCdAbrigo());

        String abrigoString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(abrigoString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        abrigo = mapper.readValue(abrigoString, Abrigo.class);
        //System.out.println("codigo="+cidade.getCdCidade());
        return abrigo;
    }
}
