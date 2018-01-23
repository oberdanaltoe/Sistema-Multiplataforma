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
import scrcm.domain.Cidade;


/**
 *
 * @author Oberdan Debona Altoé
 */
public class CidadeService {
    
String auxURL = "http://localhost:8080/WebServiceSCRCM";

    public boolean inserir(Cidade cidade) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/cidades/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(cidade);
        
        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public ArrayList<Cidade> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/cidades/listar");

        String cidadeString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Cidade>> mapType = new TypeReference<ArrayList<Cidade>>() {
        };
        ArrayList<Cidade> lista = mapper.readValue(cidadeString, mapType);

        return lista;
    }

    public boolean alterar(Cidade cidade) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/cidades/alterar");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(cidade);

        // PUT Request do Cidade Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public boolean excluir(Cidade cidade) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/cidades/excluir/" + cidade.getCdCidade());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(cidade);
        //System.out.println("codigo=" + cidade.getCdCidade());
        // DELETE Request do Cidade Jersey
        //return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);
               return target.request().get(Boolean.class);                

    }

    public Cidade buscar(Cidade cidade) throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/cidades/buscar/" + cidade.getCdCidade());

        String cidadeString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(cidadeString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        cidade = mapper.readValue(cidadeString, Cidade.class);
        //System.out.println("codigo="+cidade.getCdCidade());
        return cidade;
    }
}
