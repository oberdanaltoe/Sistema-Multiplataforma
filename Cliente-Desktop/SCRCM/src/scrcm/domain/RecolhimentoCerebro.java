package scrcm.domain;

import java.sql.Date;
import java.sql.Time;

public class RecolhimentoCerebro {

   private int cdVisitaRecolhimentoCerebro;
	 
	private float longitude;
	 
	private float latitude;
	 
	private String animalMorto;
        private String estadoCarcaca;
        
	private Date data;
	 
	private Visita visita;
        public String getEstadoCarcarca() {
        return estadoCarcaca;
    }

    public void setEstadoCarcarca(String estadoCarcarca) {
        this.estadoCarcaca = estadoCarcarca;
    }

    public int getCdVisitaRecolhimentoCerebro() {
        return cdVisitaRecolhimentoCerebro;
    }

    public void setCdVisitaRecolhimentoCerebro(int cdVisitaRecolhimentoCerebro) {
        this.cdVisitaRecolhimentoCerebro = cdVisitaRecolhimentoCerebro;
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

    public String getAnimalMorto() {
        return animalMorto;
    }

    public void setAnimalMorto(String animalMorto) {
        this.animalMorto = animalMorto;
    }

    

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public RecolhimentoCerebro() {
    }

    public RecolhimentoCerebro(int cdVisitaRecolhimentoCerebro) {
        this.cdVisitaRecolhimentoCerebro = cdVisitaRecolhimentoCerebro;
    }

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

}
