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
public class Mensagens {
   private int codMensagem;
    private String titulo;
    private String mensagem;
    private Date dataAtual;
    private Date dataExpira;

    public int getCodMensagem() {
        return codMensagem;
    }

    @Override
    public String toString() {
        return "Título: " + titulo;
    }

    public void setCodMensagem(int codMensagem) {
        this.codMensagem = codMensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataExpira() {
        return dataExpira;
    }

    public void setDataExpira(Date dataExpira) {
        this.dataExpira = dataExpira;
    }

    public Date getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }
    
    
}
