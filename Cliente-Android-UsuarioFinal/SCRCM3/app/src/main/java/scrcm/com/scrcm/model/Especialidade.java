package scrcm.com.scrcm.model;

import java.util.ArrayList;

/**
 * Created by Oberdan on 20/09/2017.
 */

public class Especialidade {
    private int cdEspecialidade;

    private String nomeEspecialidade;

    private ArrayList<Funcionario> funcionario;



    public Especialidade(int cdEspecialidade, String nomeEspecialidade) {
        this.cdEspecialidade = cdEspecialidade;
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public Especialidade(int cdEspecialidade, String nomeEspecialidade, ArrayList<Funcionario> funcionario) {
        this.cdEspecialidade = cdEspecialidade;
        this.nomeEspecialidade = nomeEspecialidade;
        this.funcionario = funcionario;

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


    public String toString() {
        return getNomeEspecialidade();
    }
}
