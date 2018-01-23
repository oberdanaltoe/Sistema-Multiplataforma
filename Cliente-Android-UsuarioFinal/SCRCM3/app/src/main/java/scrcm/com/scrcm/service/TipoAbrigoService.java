package scrcm.com.scrcm.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import scrcm.com.scrcm.model.TipoAbrigo;

/**
 * Created by Oberdan on 30/08/2017.
 */

public class TipoAbrigoService {

    public static ArrayList<TipoAbrigo> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"abrigos/listar");

        String tipoAbrigoString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<TipoAbrigo>> mapType = new TypeReference<ArrayList<TipoAbrigo>>() {
        };
        ArrayList<TipoAbrigo> lista = mapper.readValue(tipoAbrigoString, mapType);

        return lista;
    }


}
