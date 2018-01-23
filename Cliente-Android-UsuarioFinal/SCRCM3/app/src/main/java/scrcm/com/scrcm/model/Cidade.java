package scrcm.com.scrcm.model;

import java.io.Serializable;

/**
 * Created by Oberdan on 28/08/2017.
 */

public class Cidade implements Serializable{

    private int cdCidade;

    private String nomeCidade;

    private UF uF;

    public int getCdCidade() {
        return cdCidade;
    }

    public void setCdCidade(int cdCidade) {
        this.cdCidade = cdCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }


    public UF getuF() {
        return uF;
    }

    public void setuF(UF uF) {
        this.uF = uF;
    }


    public String toString() {
        return getNomeCidade();
    }

    public Cidade(int cdCidade, String nomeCidade, UF uF) {
        this.cdCidade = cdCidade;
        this.nomeCidade = nomeCidade;
        this.uF = uF;
    }

    public Cidade() {
    }

    public Cidade(int cdCidade) {
        this.cdCidade = cdCidade;
    }

    public Cidade(int cdCidade, String nomeCidade) {
        this.cdCidade = cdCidade;
        this.nomeCidade = nomeCidade;
    }
}
