package br.edu.ifes.webservice.model;

import java.util.ArrayList;

public class Bairro {

    private int cdBairro;

    private String nomeBairro;

    private ArrayList<Endereco> endereco;

    private Cidade cidade;

    public int getCdBairro() {
        return cdBairro;
    }

    public void setCdBairro(int cdBairro) {
        this.cdBairro = cdBairro;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public ArrayList<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(ArrayList<Endereco> endereco) {
        this.endereco = endereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String toString() {
        return getNomeBairro();
    }

    public Bairro(int cdBairro) {
        this.cdBairro = cdBairro;
    }

    public Bairro() {
    }
}

 
