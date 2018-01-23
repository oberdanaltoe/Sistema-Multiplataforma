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

import scrcm.com.scrcm.model.SolicitacaoVisita;

/**
 * Created by Oberdan on 04/09/2017.
 */

public class SolicitacaoVisitaService {

    public static boolean inserir(SolicitacaoVisita solicitacaoVisita) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"solicitacaoVisita/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(solicitacaoVisita);
        System.out.println("jsonInString :    " + jsonInString);

        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }

    public int buscarUltimo() throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"solicitacaoVisita/buscarUltimo/");

        String solicitacaoVisitaString = target.request().get(String.class);
        System.out.print("cdSolcicitacao lado Android; " + solicitacaoVisitaString);

        return Integer.parseInt(solicitacaoVisitaString);

    }

    public static ArrayList<SolicitacaoVisita> listarPorUsuario(int x) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"solicitacaoVisita/listarPorUsuario/" + x);
        String solicitacaoVisitaString = target.request().get(String.class);
        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<SolicitacaoVisita>> mapType = new TypeReference<ArrayList<SolicitacaoVisita>>() {
        };
        ArrayList<SolicitacaoVisita> lista = mapper.readValue(solicitacaoVisitaString, mapType);

        return lista;
    }

    public static SolicitacaoVisita buscar(SolicitacaoVisita solicitacaoVisita) throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"solicitacaoVisita/buscar/" + solicitacaoVisita.getCdSolicitacao());

        String solicitacaoVisitaString = target.request().get(String.class);
        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        solicitacaoVisita = mapper.readValue(solicitacaoVisitaString, SolicitacaoVisita.class);

        return solicitacaoVisita;
    }
}
