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

import scrcm.domain.Mensagens;

/**
 *
 * @author Oberdan
 */
public class MensagemService {
    String auxURL = "http://localhost:8080/WebServiceSCRCM";

    public boolean inserir(Mensagens mensagens) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/mensagens/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(mensagens);
        
        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
    
    public ArrayList<Mensagens> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/mensagens/listar");

        String abrigoString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Mensagens>> mapType = new TypeReference<ArrayList<Mensagens>>() {
        };
        ArrayList<Mensagens> lista = mapper.readValue(abrigoString, mapType);

        return lista;
    }
}
