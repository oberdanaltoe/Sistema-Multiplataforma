/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.domain;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Oberdan
 */
public class Visita {

     private int cdVisita;
    private Date data;
    private AgendamentoVisita agendamentoVisita;
    private Funcionario funcionario;
    private List<Captura> listaCaptura;
    
    public int getCdVisita() {
        return cdVisita;
    }

    public void setCdVisita(int cdVisita) {
        this.cdVisita = cdVisita;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public AgendamentoVisita getAgendamentoVisita() {
        return agendamentoVisita;
    }

    public void setAgendamentoVisita(AgendamentoVisita agendamentoVisita) {
        this.agendamentoVisita = agendamentoVisita;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "Visita{" + "cdVisita=" + cdVisita + ", data=" + data + ", agendamentoVisita=" + agendamentoVisita + ", funcionario=" + funcionario + '}';
    }

    public List<Captura> getListaCaptura() {
        return listaCaptura;
    }

    public void setListaCaptura(List<Captura> listaCaptura) {
        this.listaCaptura = listaCaptura;
    }
    
    
}
