package scrcm.domain;

import java.util.ArrayList;

public class Coordenador extends Pessoa {

    private int cdCoordenador;

    private String login;

    private String senha;

    private ArrayList<AgendamentoVisita> agendamentoVisita;

    private Especialidade especialidade;
    private Funcao funcao;

    public int getCdCoordenador() {
        return cdCoordenador;
    }

    public void setCdCoordenador(int cdCoordenador) {
        this.cdCoordenador = cdCoordenador;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<AgendamentoVisita> getAgendamentoVisita() {
        return agendamentoVisita;
    }

    public void setAgendamentoVisita(ArrayList<AgendamentoVisita> agendamentoVisita) {
        this.agendamentoVisita = agendamentoVisita;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Coordenador{" + "login=" + getLogin() + ", senha=" + getSenha() + '}';
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
    
    

}
