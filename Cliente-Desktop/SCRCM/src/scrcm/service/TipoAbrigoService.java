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
import scrcm.domain.TipoAbrigo;


/**
 *
 * @author Oberdan Debona Altoé
 */
public class TipoAbrigoService {
String auxURL = "http://localhost:8080/WebServiceSCRCM";
    public boolean inserir(TipoAbrigo tipoAbrigo) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/abrigos/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(tipoAbrigo);

        // POST Request do TipoAbrigo Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public ArrayList<TipoAbrigo> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/abrigos/listar");

        String tipoAbrigoString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<TipoAbrigo>> mapType = new TypeReference<ArrayList<TipoAbrigo>>() {
        };
        ArrayList<TipoAbrigo> lista = mapper.readValue(tipoAbrigoString, mapType);

        return lista;
    }

    public TipoAbrigo buscar(TipoAbrigo tipoAbrigo) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/abrigos/buscar/" + tipoAbrigo.getCdAbrigo());

        String tipoAbrigoString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(tipoAbrigoString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        tipoAbrigo = mapper.readValue(tipoAbrigoString, TipoAbrigo.class);
        return tipoAbrigo;
    }

    public boolean alterar(TipoAbrigo tipoAbrigo) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/abrigos/alterar");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(tipoAbrigo);

        // PUT Request do TipoAbrigo Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public boolean excluir(TipoAbrigo tipoAbrigo) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/abrigos/excluir/" + tipoAbrigo.getCdAbrigo());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(tipoAbrigo);

        // DELETE Request do TipoAbrigo Jersey
        return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);
    }
}
