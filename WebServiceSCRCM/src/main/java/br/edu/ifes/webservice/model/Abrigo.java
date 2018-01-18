/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model;

/**
 *
 * @author Oberdan
 */
public class Abrigo {
    private int cdAbrigo;
    private float longitude;
    private float latitude;
    private String pontoReferencia;
    private TipoAbrigo tipoAbrigo;

    public int getCdAbrigo() {
        return cdAbrigo;
    }

    public void setCdAbrigo(int cdAbrigo) {
        this.cdAbrigo = cdAbrigo;
    }

    

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public TipoAbrigo getTipoAbrigo() {
        return tipoAbrigo;
    }

    public void setTipoAbrigo(TipoAbrigo tipoAbrigo) {
        this.tipoAbrigo = tipoAbrigo;
    }

    @Override
    public String toString() {
        return "Abrigo{" + "cdAbrigo=" + cdAbrigo + ", longitude=" + longitude + ", latitude=" + latitude + ", pontoReferencia=" + pontoReferencia + ", tipoAbrigo=" + tipoAbrigo + '}';
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
    
    
    
}
