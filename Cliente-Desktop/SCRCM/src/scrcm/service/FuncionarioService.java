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

import scrcm.domain.Funcionario;

/**
 *
 * @author Oberdan Debona Altoé
 */
public class FuncionarioService {
    
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
    
    public boolean inserir(Funcionario funcionario) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcionarios/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(funcionario);
        
        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
    
    public Funcionario buscar(Funcionario funcionario) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcionarios/buscar/"+funcionario.getCdFuncionario());

        String funcionarioString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(funcionarioString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        funcionario = mapper.readValue(funcionarioString, Funcionario.class);
        
        System.out.println("nome Funcionario :::: " +funcionario.getNome());
        return funcionario;
    }
     public ArrayList<Funcionario> listar() throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcionarios/listar");

        String funcString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Funcionario>> mapType = new TypeReference<ArrayList<Funcionario>>() {};
    	ArrayList<Funcionario> lista = mapper.readValue(funcString, mapType);

        return lista;
    } 
     
     public boolean alterar(Funcionario funcionario) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcionarios/alterar");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(funcionario);

        // PUT Request do Funcao Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
    public ArrayList<Funcionario> listarComNomes() throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcionarios/listarComNomes");

        String funcString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Funcionario>> mapType = new TypeReference<ArrayList<Funcionario>>() {};
    	ArrayList<Funcionario> lista = mapper.readValue(funcString, mapType);
        System.out.println("funcString: "+ funcString);

        return lista;
    } 
    
    public boolean excluir(Funcionario funcionario) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/funcionarios/excluir/"+funcionario.getCdFuncionario());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(funcionario);

        // DELETE Request do Funcao Jersey
        return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);        
    }
}
