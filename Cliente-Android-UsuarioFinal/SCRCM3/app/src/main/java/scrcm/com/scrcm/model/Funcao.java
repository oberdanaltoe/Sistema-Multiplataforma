package scrcm.com.scrcm.model;

import java.util.ArrayList;

/**
 * Created by Oberdan on 20/09/2017.
 */

public class Funcao {
    private int cdFuncao;

    private String nomeFuncao;

    private ArrayList<Funcionario> funcionario;



    public int getCdFuncao() {
        return cdFuncao;
    }

    public void setCdFuncao(int cdFuncao) {
        this.cdFuncao = cdFuncao;
    }

    public String getNomeFuncao() {
        return nomeFuncao;
    }

    public void setNomeFuncao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }

    public ArrayList<Funcionario> getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(ArrayList<Funcionario> funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return getNomeFuncao();
    }

    public Funcao(int cdFuncao, String nomeFuncao, ArrayList<Funcionario> funcionario) {
        this.cdFuncao = cdFuncao;
        this.nomeFuncao = nomeFuncao;
        this.funcionario = funcionario;

    }

    public Funcao() {
    }

    public Funcao(int cdFuncao, String nomeFuncao) {
        this.cdFuncao = cdFuncao;
        this.nomeFuncao = nomeFuncao;
    }

    public Funcao(int cdFuncao) {
        this.cdFuncao = cdFuncao;
    }

    public Funcao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }
}
