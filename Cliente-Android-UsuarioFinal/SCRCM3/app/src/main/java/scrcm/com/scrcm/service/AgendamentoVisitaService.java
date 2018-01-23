package scrcm.com.scrcm.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import scrcm.com.scrcm.model.AgendamentoVisita;

/**
 * Created by Oberdan on 27/09/2017.
 */

public class AgendamentoVisitaService {
    public static ArrayList<AgendamentoVisita> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"agendamentoVisita/listar");

        String agendamentoVisitaString = target.request().get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<AgendamentoVisita>> mapType = new TypeReference<ArrayList<AgendamentoVisita>>() {
        };
        ArrayList<AgendamentoVisita> lista = mapper.readValue(agendamentoVisitaString, mapType);

        return lista;
    }
}
