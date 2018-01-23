package scrcm.domain;

import java.io.Serializable;

public class Familia implements Serializable {

    private int cdFamilia;

    private String nomeFamilia;

    public Familia() {
    }

    public Familia(int cdFamilia) {
        this.cdFamilia = cdFamilia;
    }

    public Familia(int cdFamilia, String nomeFamilia) {
        this.cdFamilia = cdFamilia;
        this.nomeFamilia = nomeFamilia;
    }

    public int getCdFamilia() {
        return cdFamilia;
    }

    public void setCdFamilia(int cdFamilia) {
        this.cdFamilia = cdFamilia;
    }

    public String getNomeFamilia() {
        return nomeFamilia;
    }

    public void setNomeFamilia(String nomeFamilia) {
        this.nomeFamilia = nomeFamilia;
    }

    @Override
    public String toString() {
        return "Familia{" + "nomeFamilia=" + getNomeFamilia() + '}';
    }

}
