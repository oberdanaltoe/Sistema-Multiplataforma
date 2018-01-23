package scrcm.domain;

import java.util.ArrayList;

public class TipoAbrigoSolicitacao {

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
        return "Abrigo= " + tAbrigo;
    }

   
    

    
    
    

}
