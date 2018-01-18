/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Rafael
 */
@javax.ws.rs.ApplicationPath("ws")
public class ApplicationConfig extends Application {

        public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.edu.ifes.webservice.AbrigoService.class);
        resources.add(br.edu.ifes.webservice.AgendamentoVisitaService.class);
        resources.add(br.edu.ifes.webservice.BairroService.class);
        resources.add(br.edu.ifes.webservice.CapturaService.class);
        resources.add(br.edu.ifes.webservice.CidadeService.class);
        resources.add(br.edu.ifes.webservice.ClienteService.class);
        resources.add(br.edu.ifes.webservice.EspecialidadeService.class);
        resources.add(br.edu.ifes.webservice.FamiliaService.class);
        resources.add(br.edu.ifes.webservice.FuncaoService.class);
        resources.add(br.edu.ifes.webservice.FuncionarioAgendaService.class);
        resources.add(br.edu.ifes.webservice.FuncionarioService.class);
        resources.add(br.edu.ifes.webservice.HabitoAlimentarService.class);
        resources.add(br.edu.ifes.webservice.RecolhimentoCerebroService.class);
        resources.add(br.edu.ifes.webservice.SolicitacaoVisitaService.class);
        resources.add(br.edu.ifes.webservice.TipoAbrigoService.class);
        resources.add(br.edu.ifes.webservice.TipoAbrigoSolicitacaoService.class);
        resources.add(br.edu.ifes.webservice.UFService.class);
        resources.add(br.edu.ifes.webservice.UsuarioComumService.class);
        resources.add(br.edu.ifes.webservice.VisitaService.class);
    }
    
}
