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
import scrcm.domain.SolicitacaoVisita;


/**
 *
 * @author Oberdan Debona Altoé
 */
public class SolicitacaoVisitaService {
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
    public boolean inserir(SolicitacaoVisita solicitacaoVisita) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/solicitacaoVisita/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(solicitacaoVisita);
        System.out.println("jsonInString :    " + jsonInString);

        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public ArrayList<SolicitacaoVisita> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/solicitacaoVisita/listar");

        String solicitacaoVisitaString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<SolicitacaoVisita>> mapType = new TypeReference<ArrayList<SolicitacaoVisita>>() {
        };
        ArrayList<SolicitacaoVisita> lista = mapper.readValue(solicitacaoVisitaString, mapType);

        return lista;
    }
    
    public ArrayList<SolicitacaoVisita> listarStatus() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/solicitacaoVisita/listarStatus");

        String solicitacaoVisitaString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<SolicitacaoVisita>> mapType = new TypeReference<ArrayList<SolicitacaoVisita>>() {
        };
        ArrayList<SolicitacaoVisita> lista = mapper.readValue(solicitacaoVisitaString, mapType);

        return lista;
    }

    public boolean alterar(SolicitacaoVisita solicitacaoVisita) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/solicitacaoVisita/alterar");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(solicitacaoVisita);

        // PUT Request do Solcictacao Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public boolean excluir(SolicitacaoVisita solicitacaoVisita) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/solicitacaoVisita/excluir/" + solicitacaoVisita.getCdSolicitacao());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(solicitacaoVisita);
        //return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);
        return target.request().get(Boolean.class); 
    }

    public SolicitacaoVisita buscar(SolicitacaoVisita solicitacaoVisita) throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/solicitacaoVisita/buscar/" + solicitacaoVisita.getCdSolicitacao());

        String solicitacaoVisitaString = target.request().get(String.class);
        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        solicitacaoVisita = mapper.readValue(solicitacaoVisitaString, SolicitacaoVisita.class);
        
        return solicitacaoVisita;
    }
    public int buscarUltimo() throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/solicitacaoVisita/buscarUltimo/");

        String solicitacaoVisitaString = target.request().get(String.class);
                       
        return target.request().get(Integer.class);
        
    }
}
