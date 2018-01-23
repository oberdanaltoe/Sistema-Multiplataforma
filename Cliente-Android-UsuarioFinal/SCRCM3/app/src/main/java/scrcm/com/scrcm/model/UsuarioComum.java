package scrcm.com.scrcm.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Oberdan on 29/08/2017.
 */

public class UsuarioComum implements Serializable{

    private int cdUsuarioComum;

    private String email;

    private String telefone;

    private Date dataCadastro;

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
    private Cidade cidade;

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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
