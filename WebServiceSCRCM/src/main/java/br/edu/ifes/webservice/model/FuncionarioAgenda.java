/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oberdan Debona Altoé
 */
public class FuncionarioAgenda {

    private Funcionario funcionario;
    private AgendamentoVisita agendamentoVisita;
    


    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public AgendamentoVisita getAgendamentoVisita() {
        return agendamentoVisita;
    }

    public void setAgendamentoVisita(AgendamentoVisita agendamentoVisita) {
        this.agendamentoVisita = agendamentoVisita;
    }

    public FuncionarioAgenda() {
    }

    
    @Override
    public String toString() {
        return funcionario.getNome();
    }

   
    

}
