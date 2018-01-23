package scrcm.com.scrcm.model;

import java.util.Date;

/**
 * Created by Oberdan on 04/10/2017.
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
