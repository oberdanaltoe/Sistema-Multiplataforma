package scrcm.com.scrcm.model;

import java.io.Serializable;

/**
 * Created by Oberdan on 29/08/2017.
 */

public class TipoAbrigo implements Serializable{
    private int cdAbrigo;

    private String nomeAbrigo;



    public TipoAbrigo(int cdAbrigo, String nomeAbrigo) {
        this.cdAbrigo = cdAbrigo;
        this.nomeAbrigo = nomeAbrigo;
    }

    public TipoAbrigo() {
    }

    public TipoAbrigo(int cdAbrigo) {
        this.cdAbrigo = cdAbrigo;
    }

    public int getCdAbrigo() {
        return cdAbrigo;
    }

    public void setCdAbrigo(int cdAbrigo) {
        this.cdAbrigo = cdAbrigo;
    }

    public String getNomeAbrigo() {
        return nomeAbrigo;
    }

    public void setNomeAbrigo(String nomeAbrigo) {
        this.nomeAbrigo = nomeAbrigo;
    }

    public String toString() {
        return getNomeAbrigo();
    }
}
