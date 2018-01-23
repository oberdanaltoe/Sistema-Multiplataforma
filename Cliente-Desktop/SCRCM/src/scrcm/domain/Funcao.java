/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Oberdan Debona Altoé
 */
public class Funcao implements Serializable{
    private int cdFuncao;
	 
	private String nomeFuncao;
	 
	private ArrayList<Funcionario> funcionario;
	 
	private ArrayList<Coordenador> coordenador;

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

    public ArrayList<Coordenador> getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(ArrayList<Coordenador> coordenador) {
        this.coordenador = coordenador;
    }

    @Override
    public String toString() {
        return getNomeFuncao();
    }

    public Funcao(int cdFuncao, String nomeFuncao, ArrayList<Funcionario> funcionario, ArrayList<Coordenador> coordenador) {
        this.cdFuncao = cdFuncao;
        this.nomeFuncao = nomeFuncao;
        this.funcionario = funcionario;
        this.coordenador = coordenador;
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
