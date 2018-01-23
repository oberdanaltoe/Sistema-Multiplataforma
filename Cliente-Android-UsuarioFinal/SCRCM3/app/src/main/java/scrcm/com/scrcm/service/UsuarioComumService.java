package scrcm.com.scrcm.service;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import scrcm.com.scrcm.model.UsuarioComum;

/**
 * Created by Oberdan on 05/10/2017.
 */

public class UsuarioComumService {

    public static boolean inserir(UsuarioComum uc) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"usuarios/inserir");

        // Objeto para JSON em String
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(uc);

        // POST Request do UF Jersey
        return target.request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(jsonInString, "application/json;charset=UTF-8"), Boolean.class);
    }
/*
    public static UsuarioComum verificaLogin(UsuarioComum uc) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target;
        target = client.target(IpConexao.IP+"usuarios/login/" + uc.getEmail() + uc.getEmail());

        String ucString = target.request().get(String.class);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        uc = mapper.readValue(ucString, UsuarioComum.class);
        return uc;
    }
*/
    public static boolean verificaLogin1(UsuarioComum uc) throws IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(IpConexao.IP+"usuarios/login1/" + uc.getEmail()+"/"+uc.getSenha());

        boolean resultado = target.request().get(Boolean.class);

        return resultado;
    }

    public UsuarioComum buscarLogin(UsuarioComum uc) throws IOException {
        Client client = ClientBuilder.newClient();
        System.out.println("Resultado (String): " + uc.getEmail());
        WebTarget target = client.target(IpConexao.IP+"usuarios/buscarLogin/" + uc.getLogin());

        String ucString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(ucString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        uc = mapper.readValue(ucString, UsuarioComum.class);
        return uc;
    }

    public static UsuarioComum buscarLoginEmail(UsuarioComum uc) throws IOException {
        Client client = ClientBuilder.newClient();
        System.out.println("Email busca: " + uc.getEmail().toString());
        WebTarget target = client.target(IpConexao.IP+"usuarios/buscarLoginEmail/" + uc.getEmail().toString());

        String ucString = target.request().get(String.class);
        System.out.println("Resultado (String): ");
        System.out.println(ucString);

        //Convertendo JSON para Java Object
        ObjectMapper mapper = new ObjectMapper();
        UsuarioComum uc1 = new UsuarioComum();
        uc1 = mapper.readValue(ucString, UsuarioComum.class);
        return uc1;
    }

}
