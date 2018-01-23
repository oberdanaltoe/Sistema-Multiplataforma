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
import scrcm.domain.AgendamentoVisita;

/**
 *
 * @author Oberdan Debona Altoé
 */
public class AgendamentoVisitaService {
    
     String auxURL = "http://localhost:8080/WebServiceSCRCM";
     
         
     public boolean inserir(AgendamentoVisita agendamentoVisita) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/agendamentoVisita/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(agendamentoVisita);

        // POST Request do agendamentoVisita Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }

    public ArrayList<AgendamentoVisita> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/agendamentoVisita/listar");

        String agendamentoVisitaString = target.request().get(String.class);
        System.out.println("\n\n\nscrcm.service.AgendamentoVisitaService.listar(): "+agendamentoVisitaString+"\n\n\n");
         //até aqui a data está correta depois na hora da conversão do objetoMapper que diminui um dia        
         //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<AgendamentoVisita>> mapType = new TypeReference<ArrayList<AgendamentoVisita>>() {
        };
        ArrayList<AgendamentoVisita> lista = mapper.readValue(agendamentoVisitaString, mapType);

        return lista;
    }

    public boolean alterar(AgendamentoVisita agendamentoVisita) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/agendamentoVisita/alterar");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(agendamentoVisita);

        // PUT Request do agendamentoVisita Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }
    
    public boolean alterarStatus(AgendamentoVisita agendamentoVisita) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/agendamentoVisita/alterarStatus");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(agendamentoVisita);

        // PUT Request do agendamentoVisita Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }

    public boolean excluir(AgendamentoVisita agendamentoVisita) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/agendamentoVisita/excluir/" + agendamentoVisita.getCdAgendamentoVisita());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(agendamentoVisita);
        
        //return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);
        return target.request().get(Boolean.class);  
    }

    public AgendamentoVisita buscar(AgendamentoVisita agendamentoVisita) throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/agendamentoVisita/buscar/" + agendamentoVisita.getCdAgendamentoVisita());

        String agendamentoVisitaString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(agendamentoVisitaString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        agendamentoVisita = mapper.readValue(agendamentoVisitaString, AgendamentoVisita.class);
        return agendamentoVisita;
    }
    
    public int buscarUltimo() throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/agendamentoVisita/buscarUltimo/");

        String agendamentoVisitaString = target.request().get(String.class);
               
        return target.request().get(Integer.class);
        
    }
}
