package scrcm.com.scrcm.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import scrcm.com.scrcm.model.Mensagens;

/**
 * Created by Oberdan on 04/10/2017.
 */

public class MensagemService {

    public static ArrayList<Mensagens> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"mensagens/listar");

        String abrigoString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Mensagens>> mapType = new TypeReference<ArrayList<Mensagens>>() {
        };
        ArrayList<Mensagens> lista = mapper.readValue(abrigoString, mapType);

        return lista;
    }
}
