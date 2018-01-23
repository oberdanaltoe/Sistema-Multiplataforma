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
import scrcm.domain.Especialidade;


/**
 *
 * @author Oberdan Debona Altoé
 */
public class EspecialidadeService {
    
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
    
    public boolean inserir(Especialidade especialidade) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL +"/ws/especialidades/inserir");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(especialidade);

        // POST Request do especialidade Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
    public ArrayList<Especialidade> listar() throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/especialidades/listar");

        String especialidadeString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Especialidade>> mapType = new TypeReference<ArrayList<Especialidade>>() {};
    	ArrayList<Especialidade> lista = mapper.readValue(especialidadeString, mapType);

        return lista;
    } 
    public Especialidade buscar(Especialidade especialidade) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/especialidades/buscar/"+especialidade.getCdEspecialidade());

        String especialidadeString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(especialidadeString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        especialidade = mapper.readValue(especialidadeString, Especialidade.class);
        return especialidade;
    }
    public boolean alterar(Especialidade especialidade) throws IOException{
        
        System.out.println("especialidade.toString : " + especialidade.toString());
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/especialidades/alterar");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(especialidade);
        
        System.out.println("jsonInString : " + jsonInString);

        // PUT Request do especialidade Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public boolean excluir(Especialidade especialidade) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/especialidades/excluir/"+especialidade.getCdEspecialidade());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(especialidade);

        // DELETE Request do especialidade Jersey
        return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);        
    }
}
