package scrcm.com.scrcm.service;

/**
 * Created by Oberdan on 28/08/2017.
 */

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import scrcm.com.scrcm.model.Cidade;

public class CidadeService {

    public static boolean inserir(Cidade cidade) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"cidades/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(cidade);

        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN).post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public static ArrayList<Cidade> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"cidades/listar");

        String cidadeString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Cidade>> mapType = new TypeReference<ArrayList<Cidade>>() {
        };
        ArrayList<Cidade> lista = mapper.readValue(cidadeString, mapType);

        return lista;
    }

  }
