package scrcm.com.scrcm.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import scrcm.com.scrcm.model.UF;


/**
 * Created by Oberdan on 24/08/2017.
 */

public class UFService {



    public static boolean inserir(UF uf) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"ufs/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(uf);

        // POST Request do Cliente Jersey
        return target.request(MediaType.TEXT_PLAIN).post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public static ArrayList<UF> listar() throws IOException{
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"ufs/listar");

        String ufString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<UF>> mapType = new TypeReference<ArrayList<UF>>() {};
        ArrayList<UF> lista = mapper.readValue(ufString, mapType);

        return lista;
    }

}
