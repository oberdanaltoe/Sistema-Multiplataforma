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
import scrcm.domain.Captura;

/**
 *
 * @author Oberdan
 */
public class CapturaService {
    
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
    public boolean inserir(Captura captura) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL +"/ws/captura/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(captura);
        System.out.println("jsonInString :    " + jsonInString);

        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
        
    }
    
     public ArrayList<Captura> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/captura/listar");

        String abrigoString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Captura>> mapType = new TypeReference<ArrayList<Captura>>() {
        };
        ArrayList<Captura> lista = mapper.readValue(abrigoString, mapType);

        return lista;
    }
}
