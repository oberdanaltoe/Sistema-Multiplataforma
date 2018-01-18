package br.edu.ifes.webservice.model;

public class Pessoa {
 
	private int cdPessoa;
	 
	private String nome;
	 
	private String cpf;
	 
	private Endereco endereco;

    public int getCdPessoa() {
        return cdPessoa;
    }

    public void setCdPessoa(int cdPessoa) {
        this.cdPessoa = cdPessoa;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", cpf=" + cpf + '}';
    }

    
   
	 
        
}
 
