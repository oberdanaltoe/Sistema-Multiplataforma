package scrcm.domain;

import java.util.ArrayList;

public class Especie {
 
	private int cdEspecie;
	 
	private String nomeEspecie;
	 
	private Genero genero;
	 
	private HabitoAlimentar habitoAlimentar;
       


    public int getCdEspecie() {
        return cdEspecie;
    }

    public void setCdEspecie(int cdEspecie) {
        this.cdEspecie = cdEspecie;
    }

    public String getNomeEspecie() {
        return nomeEspecie;
    }

    public void setNomeEspecie(String nomeEspecie) {
        this.nomeEspecie = nomeEspecie;
    }

  

    public HabitoAlimentar getHabitoAlimentar() {
        return habitoAlimentar;
    }

    public void setHabitoAlimentar(HabitoAlimentar habitoAlimentar) {
        this.habitoAlimentar = habitoAlimentar;
    }


    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    


  
        
        
	 
}
 
