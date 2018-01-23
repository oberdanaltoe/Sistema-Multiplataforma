package scrcm.com.scrcm.service;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import scrcm.com.scrcm.model.TipoAbrigoSolicitacao;

/**
 * Created by Oberdan on 04/09/2017.
 */

public class TipoAbrigoSolicitacaoService {

    public boolean inserir(TipoAbrigoSolicitacao tipoAbrigoSolicitacao) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"tipoAbrigoSolicitacao/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(tipoAbrigoSolicitacao);
        System.out.println("jsonInString TipoAbrigo:    " + jsonInString);
        // POST Request do TipoAbrigo Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }
}
