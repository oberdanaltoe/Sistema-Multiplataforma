package scrcm.com.scrcm.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import scrcm.com.scrcm.model.Bairro;

/**
 * Created by Oberdan on 05/10/2017.
 */

public class BairroService {

    public static ArrayList<Bairro> buscarPorCidade(Bairro bairro) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"bairros/buscarPorCidade/"+bairro.getCidade().getCdCidade());

        String bairroString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<Bairro>> mapType = new TypeReference<ArrayList<Bairro>>() {};
        ArrayList<Bairro> lista = mapper.readValue(bairroString, mapType);

        return lista;
    }
}
