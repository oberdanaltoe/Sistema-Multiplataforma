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
import scrcm.domain.SolicitacaoVisita;
import scrcm.domain.TipoAbrigoSolicitacao;


/**
 *
 * @author Oberdan Debona Altoé
 */
public class TipoAbrigoSolicitacaoService {
    String auxURL = "http://localhost:8080/WebServiceSCRCM";
     public boolean inserir(TipoAbrigoSolicitacao tipoAbrigoSolicitacao) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/tipoAbrigoSolicitacao/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(tipoAbrigoSolicitacao);
        System.out.println("jsonInString Tipo Abrigo:    " + jsonInString);

        // POST Request do TipoAbrigo Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }

    public ArrayList<TipoAbrigoSolicitacao> listar() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/tipoAbrigoSolicitacao/listar");

        String tipoAbrigoString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<TipoAbrigoSolicitacao>> mapType = new TypeReference<ArrayList<TipoAbrigoSolicitacao>>() {
        };
        ArrayList<TipoAbrigoSolicitacao> lista = mapper.readValue(tipoAbrigoString, mapType);
        System.out.println("tipoAbrigoString"+tipoAbrigoString);

        return lista;
    }

    public TipoAbrigoSolicitacao buscar(TipoAbrigoSolicitacao tipoAbrigoSol) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/tipoAbrigoSolicitacao/"
                + "buscar/" + tipoAbrigoSol.getSolicitacaoVisita().getCdSolicitacao()+"/"+tipoAbrigoSol.gettAbrigo().getCdAbrigo());
        
        String tipoAbrigoString = target.request().get(String.class);
        //System.out.println("Resultado (String): ");
        //System.out.println(tipoAbrigoString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        tipoAbrigoSol = mapper.readValue(tipoAbrigoString, TipoAbrigoSolicitacao.class);
        return tipoAbrigoSol;
    }

    public boolean alterar(TipoAbrigoSolicitacao tipoAbrigoSol) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/tipoAbrigoSolicitacao/alterar");
        
        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(tipoAbrigoSol);

        // PUT Request do TipoAbrigo Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .put(Entity.entity(jsonInString, MediaType.APPLICATION_JSON), Boolean.class);
    }

    public boolean excluir(TipoAbrigoSolicitacao tipoAbrigoSol) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws/tipoAbrigoSolicitacao/"
                + "excluir/" + tipoAbrigoSol.getSolicitacaoVisita().getCdSolicitacao());

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(tipoAbrigoSol);

        // DELETE Request do TipoAbrigo Jersey
        //return target.request(MediaType.TEXT_PLAIN).delete(Boolean.class);
        return target.request().get(Boolean.class); 
    }
    
    public ArrayList<TipoAbrigoSolicitacao> listarPorSolicitacao(SolicitacaoVisita solicitacaoVisita) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(auxURL + "/ws"
                + "/tipoAbrigoSolicitacao/listarPorSolicitacao/" + solicitacaoVisita.getCdSolicitacao());

        String tipoAbrigoSolicitacaoString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<ArrayList<TipoAbrigoSolicitacao>> mapType = new TypeReference<ArrayList<TipoAbrigoSolicitacao>>() {
        };
        ArrayList<TipoAbrigoSolicitacao> lista = mapper.readValue(tipoAbrigoSolicitacaoString, mapType);

        return lista;
    }
}
