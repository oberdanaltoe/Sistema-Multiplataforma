package scrcm.com.scrcm.model;

import java.io.Serializable;

/**
 * Created by Oberdan on 29/08/2017.
 */

public class TipoAbrigoSolicitacao implements Serializable{

    private SolicitacaoVisita solicitacaoVisita;
    private TipoAbrigo tAbrigo;


    public SolicitacaoVisita getSolicitacaoVisita() {
        return solicitacaoVisita;
    }

    public void setSolicitacaoVisita(SolicitacaoVisita solicitacaoVisita) {
        this.solicitacaoVisita = solicitacaoVisita;
    }


    public TipoAbrigo gettAbrigo() {
        return tAbrigo;
    }

    public void settAbrigo(TipoAbrigo tAbrigo) {
        this.tAbrigo = tAbrigo;
    }

    public TipoAbrigoSolicitacao(SolicitacaoVisita solicitacaoVisita, TipoAbrigo tAbrigo) {
        this.solicitacaoVisita = solicitacaoVisita;
        this.tAbrigo = tAbrigo;
    }

    public TipoAbrigoSolicitacao() {
    }

    @Override
    public String toString() {
        return "TipoAbrigoSolicitacao{" + "solicitacaoVisita=" + solicitacaoVisita + ", tAbrigo=" + tAbrigo + '}';
    }

}
