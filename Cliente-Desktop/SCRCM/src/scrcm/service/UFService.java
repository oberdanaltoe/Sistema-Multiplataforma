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
import scrcm.domain.UF;


/**
 *
 * @author Oberdan Debona Altoé
 */
public class UFService {
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
    
    public boolean inserir(UF uf) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/ufs/inserir");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(uf);

        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
    public ArrayList<UF> listar() throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/ufs/listar");

        String ufString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<UF>> mapType = new TypeReference<ArrayList<UF>>() {};
    	ArrayList<UF> lista = mapper.readValue(ufString, mapType);

        return lista;
    } 
    public UF buscar(UF uf) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/ufs/buscar/"+uf.getSigla());

        String ufString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(ufString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        uf = mapper.readValue(ufString, UF.class);
        return uf;
    }
    public boolean alterar(UF uf) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/ufs/alterar");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(uf);

        // PUT Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public boolean excluir(UF uf) throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/ufs/excluir/"+uf.getSigla());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(uf);

        // DELETE Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);        
    }
}
