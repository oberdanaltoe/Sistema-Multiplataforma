package scrcm.com.scrcm.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Oberdan on 24/08/2017.
 */

public class UF implements Serializable {
    private String sigla;

    private String nomeUF;

    public UF(String sigla) {
        this.sigla = sigla;
    }

    ArrayList<UF> arrayList = new ArrayList<>();

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
