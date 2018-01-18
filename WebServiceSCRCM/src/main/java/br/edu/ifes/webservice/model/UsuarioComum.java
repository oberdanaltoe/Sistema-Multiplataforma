package br.edu.ifes.webservice.model;

import java.util.ArrayList;

public class UsuarioComum {

    private int cdUsuarioComum;

    private float longitude;

    private float latitude;

    private String login;

    private String senha;

    private String nome;

    private String cpf;

    private String rua;

    private String pontoReferencia;

    private int numero;

    private Bairro bairro;

    //private Endereco endereco;

    private ArrayList<SolicitacaoVisita> solicitacaoVisita;

    @Override
    public String toString() {
        return  nome;
    }

    public int getCdUsuarioComum() {
        return cdUsuarioComum;
    }

    public void setCdUsuarioComum(int cdUsuarioComum) {
        this.cdUsuarioComum = cdUsuarioComum;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

   /* public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }*/

    public ArrayList<SolicitacaoVisita> getSolicitacaoVisita() {
        return solicitacaoVisita;
    }

    public void setSolicitacaoVisita(ArrayList<SolicitacaoVisita> solicitacaoVisita) {
        this.solicitacaoVisita = solicitacaoVisita;
    } 

    
}
