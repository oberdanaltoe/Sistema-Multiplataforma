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
import scrcm.domain.FuncionarioAgenda;

/**
 *
 * @author Oberdan Debona Altoé
 */
public class FuncionarioAgendaService {
    
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
    
     public boolean inserir(FuncionarioAgenda funcionarioAgenda) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcionarioAgenda/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(funcionarioAgenda);

        // POST Request do funcionarioAgenda Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }

    public ArrayList<FuncionarioAgenda> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcionarioAgenda/listar");

        String funcionarioAgendaString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<FuncionarioAgenda>> mapType = new TypeReference<ArrayList<FuncionarioAgenda>>() {
        };
        ArrayList<FuncionarioAgenda> lista = mapper.readValue(funcionarioAgendaString, mapType);

        return lista;
    }
/*
    public boolean alterar(FuncionarioAgenda funcionarioAgenda) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/WebServiceRESTful/ws/funcionarioAgenda/alterar");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(funcionarioAgenda);

        // PUT Request do Cidade Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }
*/
    public boolean excluir(FuncionarioAgenda funcionarioAgenda) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcionarioAgenda/excluir/" + funcionarioAgenda.getAgendamentoVisita().getCdAgendamentoVisita());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(funcionarioAgenda);
        //System.out.println("codigo=" + cidade.getCdCidade());
        // DELETE Request do Cidade Jersey
       // return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);
        return target.request().get(Boolean.class);  
    }

    public FuncionarioAgenda buscar(FuncionarioAgenda funcionarioAgenda) throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcionarioAgenda/buscar/" + funcionarioAgenda);

        String funcionarioAgendaString = target.request().get(String.class);
        System.out.println("\n\n\n\n funcionarioAgendaString: ");
        System.out.println(funcionarioAgendaString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        funcionarioAgenda = mapper.readValue(funcionarioAgendaString, FuncionarioAgenda.class);
        //System.out.println("codigo="+cidade.getCdCidade());
        return funcionarioAgenda;
    }
    
    public ArrayList<FuncionarioAgenda> listarPorAgendamento(AgendamentoVisita agendamentoVisita) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws"
                + "/funcionarioAgenda/listarPorAgendamento/" + agendamentoVisita.getCdAgendamentoVisita());

        String funcionarioAgendaString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<FuncionarioAgenda>> mapType = new TypeReference<ArrayList<FuncionarioAgenda>>() {
        };
        ArrayList<FuncionarioAgenda> lista = mapper.readValue(funcionarioAgendaString, mapType);

        return lista;
    }
}
