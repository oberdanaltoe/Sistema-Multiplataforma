package br.edu.ifes.webservice.model;

import java.util.ArrayList;

public class Genero {
 
	private int cdGenero;
	 
	private String nomeGenero;
	 
        private Familia familia;
        
       

    public int getCdGenero() {
        return cdGenero;
    }

    public void setCdGenero(int cdGenero) {
        this.cdGenero = cdGenero;
    }

    public String getNomeGenero() {
        return nomeGenero;
    }

    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

   

    
    public String toString() {
        return "Genero{" + "nomeGenero=" + getNomeGenero() + '}';
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

   

    
        
}
 
