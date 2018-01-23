package scrcm.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Especialidade implements Serializable {

    private int cdEspecialidade;

    private String nomeEspecialidade;

    private ArrayList<Funcionario> funcionario;

    private ArrayList<Coordenador> coordenador;

    public Especialidade(int cdEspecialidade, String nomeEspecialidade) {
        this.cdEspecialidade = cdEspecialidade;
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public Especialidade(int cdEspecialidade, String nomeEspecialidade, ArrayList<Funcionario> funcionario, ArrayList<Coordenador> coordenador) {
        this.cdEspecialidade = cdEspecialidade;
        this.nomeEspecialidade = nomeEspecialidade;
        this.funcionario = funcionario;
        this.coordenador = coordenador;
    }
    public Especialidade(int cdEspecialidade) {
        this.cdEspecialidade = cdEspecialidade;
    }

    public Especialidade() {
    }

    public int getCdEspecialidade() {
        return cdEspecialidade;
    }

    public void setCdEspecialidade(int cdEspecialidade) {
        this.cdEspecialidade = cdEspecialidade;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public ArrayList<Funcionario> getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(ArrayList<Funcionario> funcionario) {
        this.funcionario = funcionario;
    }

    public ArrayList<Coordenador> getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(ArrayList<Coordenador> coordenador) {
        this.coordenador = coordenador;
    }

    public String toString() {
        return getNomeEspecialidade();
    }

}
