package br.edu.ifes.webservice.model;

import java.util.ArrayList;


public class Endereco {
 
	private String rua;
        
        private String ponto_referencia;
	 
	private int numero;
	 
	private ArrayList<Pessoa> pessoa;
	 
	//private Bairro bairro;

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public ArrayList<Pessoa> getPessoa() {
        return pessoa;
    }

    public void setPessoa(ArrayList<Pessoa> pessoa) {
        this.pessoa = pessoa;
    }

    /*public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }*/

    public String getPonto_referencia() {
        return ponto_referencia;
    }

    public void setPonto_referencia(String ponto_referencia) {
        this.ponto_referencia = ponto_referencia;
    }

    @Override
    public String toString() {
        return "Endereco{" + "rua=" + rua + ", ponto_referencia=" + ponto_referencia + '}';
    }

    
  
	 
        
}
 
