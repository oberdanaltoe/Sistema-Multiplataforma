/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrcm.domain;

import java.sql.Date;

/**
 *
 * @author Oberdan
 */
public class Captura {
    private int cdCaptura;
    private int qtdMorcegosCapturados;
    private int qtdMorcegosTratados;
    private int qtdEnviadosLaboratorio;
    private Date dataCaptura;
    private float longitude;
    private float latitude;
    private Visita visita;
    private Abrigo abrigo;
   
    public int getCdCaptura() {
        return cdCaptura;
    }

    public void setCdCaptura(int cdCaptura) {
        this.cdCaptura = cdCaptura;
    }

    public int getQtdMorcegosCapturados() {
        return qtdMorcegosCapturados;
    }

    public void setQtdMorcegosCapturados(int qtdMorcegosCapturados) {
        this.qtdMorcegosCapturados = qtdMorcegosCapturados;
    }

    public int getQtdMorcegosTratados() {
        return qtdMorcegosTratados;
    }

    public void setQtdMorcegosTratados(int qtdMorcegosTratados) {
        this.qtdMorcegosTratados = qtdMorcegosTratados;
    }

    public int getQtdEnviadosLaboratorio() {
        return qtdEnviadosLaboratorio;
    }

    public void setQtdEnviadosLaboratorio(int qtdEnviadosLaboratorio) {
        this.qtdEnviadosLaboratorio = qtdEnviadosLaboratorio;
    }

    public Date getDataCaptura() {
        return dataCaptura;
    }

    public void setDataCaptura(Date dataCaptura) {
        this.dataCaptura = dataCaptura;
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

    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    @Override
    public String toString() {
        return "Captura{" + "cdCaptura=" + cdCaptura + ", qtdMorcegosCapturados=" + qtdMorcegosCapturados + ", qtdMorcegosTratados=" + qtdMorcegosTratados + ", qtdEnviadosLaboratorio=" + qtdEnviadosLaboratorio + ", dataCaptura=" + dataCaptura + ", longitude=" + longitude + ", latitude=" + latitude + ", visita=" + visita + '}';
    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo;
    }
 
    
}
