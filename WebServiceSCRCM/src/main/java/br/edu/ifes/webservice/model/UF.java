package br.edu.ifes.webservice.model;

import java.io.Serializable;


public class UF implements Serializable{
 
	private String sigla;
	 
	private String nomeUF;

    public UF(String sigla) {
        this.sigla = sigla;
    }

    

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNomeUF() {
        return nomeUF;
    }

    public void setNomeUF(String nomeUF) {
        this.nomeUF = nomeUF;
    }

    @Override
    public String toString() {
        return getSigla();
    }
    
    public UF(){
    }
      
        
    public UF(String sigla, String nomeUF) {
        this.sigla  = sigla;
        this.nomeUF = nomeUF;
        
    }
}
 
