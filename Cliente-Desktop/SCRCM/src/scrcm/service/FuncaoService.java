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
import scrcm.domain.Funcao;


/**
 *
 * @author Oberdan Debona Altoé
 */
public class FuncaoService {
    
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
    
     public boolean inserir(Funcao funcao) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcoes/inserir");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(funcao);

        // POST Request do Funcao Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
    public ArrayList<Funcao> listar() throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcoes/listar");

        String funcaoString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Funcao>> mapType = new TypeReference<ArrayList<Funcao>>() {};
    	ArrayList<Funcao> lista = mapper.readValue(funcaoString, mapType);

        return lista;
    } 
    public Funcao buscar(Funcao funcao) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcoes/buscar/"+funcao.getCdFuncao());

        String funcaoString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(funcaoString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        funcao = mapper.readValue(funcaoString, Funcao.class);
        return funcao;
    }
    public boolean alterar(Funcao funcao) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcoes/alterar");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(funcao);

        // PUT Request do Funcao Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public boolean excluir(Funcao funcao) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcoes/excluir/"+funcao.getCdFuncao());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(funcao);

        // DELETE Request do Funcao Jersey
        return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);        
    }
}
