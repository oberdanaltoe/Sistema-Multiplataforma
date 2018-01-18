package br.edu.ifes.webservice.model;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int cdCliente;
    private String nome;
    private String cpf;

    public Cliente() {
    }

    public Cliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public Cliente(int cdCliente, String nome, String cpf) {
        this.cdCliente = cdCliente;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(int cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    @Override
    public String toString(){
        return this.getNome();
    }

}
