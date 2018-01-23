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
import scrcm.domain.HabitoAlimentar;


/**
 *
 * @author Oberdan Debona Altoé
 */
public class HabitoAlimentarService {
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
     public boolean inserir(HabitoAlimentar habitoAlimentar) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/habitoalimenta/inserir");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(habitoAlimentar);

        // POST Request do HabitoAlimentar Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }
    public ArrayList<HabitoAlimentar> listar() throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/habitoalimenta/listar");

        String habitoAlimentarString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<HabitoAlimentar>> mapType = new TypeReference<ArrayList<HabitoAlimentar>>() {};
    	ArrayList<HabitoAlimentar> lista = mapper.readValue(habitoAlimentarString, mapType);

        return lista;
    } 
    public HabitoAlimentar buscar(HabitoAlimentar habitoAlimentar) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/habitoalimenta/buscar/"+habitoAlimentar.getCdHabito());

        String ufString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(ufString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        habitoAlimentar = mapper.readValue(ufString, HabitoAlimentar.class);
        return habitoAlimentar;
    }
    public boolean alterar(HabitoAlimentar habitoAlimentar) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/habitoalimenta/alterar");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(habitoAlimentar);

        // PUT Request do HabitoAlimentar Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }

    public boolean excluir(HabitoAlimentar habitoAlimentar) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/habitoalimenta/excluir/"+habitoAlimentar.getCdHabito());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(habitoAlimentar);

        // DELETE Request do HabitoAlimentar Jersey
        return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);        
    }
}
