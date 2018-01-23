package scrcm.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class HabitoAlimentar implements Serializable{
 
	private int cdHabito;
	 
	private String nomeHabito;
	 
	private ArrayList<Especie> especie;

    public HabitoAlimentar() {
    }

    public HabitoAlimentar(int cdHabito) {
        this.cdHabito = cdHabito;
    }

    public HabitoAlimentar(int cdHabito, String nomeHabito) {
        this.cdHabito = cdHabito;
        this.nomeHabito = nomeHabito;
    }
        
    

    public int getCdHabito() {
        return cdHabito;
    }

    public void setCdHabito(int cdHabito) {
        this.cdHabito = cdHabito;
    }

    public String getNomeHabito() {
        return nomeHabito;
    }

    public void setNomeHabito(String nomeHabito) {
        this.nomeHabito = nomeHabito;
    }

    public ArrayList<Especie> getEspecie() {
        return especie;
    }

    public void setEspecie(ArrayList<Especie> especie) {
        this.especie = especie;
    }

    
    public String toString() {
        return "HabitoAlimentar{" + "nomeHabito=" + getNomeHabito() + '}';
    }
        
        
	 
}
 
