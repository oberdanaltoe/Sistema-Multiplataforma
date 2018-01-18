package br.edu.ifes.webservice.model;

import java.util.ArrayList;

public class SolicitacaoVisita {
 
	private int cdSolicitacao;
	 
	private int status;
	 
	private String possuiPropriedade;
	 
	private int qtdMediaAnimais;
	 
	private int qtdAnimaisMordidos;
	 
	private String casosMorteRegiao;
	 
	private String proprieLocaisProximos;
	 
	private String tempoOcorridoMorte;
	 
	private String conheceAbrigo;
	 
	private String solicitarRecolhimento;
        
	private String observacoes;
	 
	private String foto;
	 
	private UsuarioComum usuarioComum;
	 
	//private AgendamentoVisita agendamentoVisita;
	 
		 
	private ArrayList<TipoAbrigoSolicitacao> tipoAbrigoSolicitacao;

    public int getCdSolicitacao() {
        return cdSolicitacao;
    }

    public void setCdSolicitacao(int cdSolicitacao) {
        this.cdSolicitacao = cdSolicitacao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

   

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public UsuarioComum getUsuarioComum() {
        return usuarioComum;
    }

    public void setUsuarioComum(UsuarioComum usuarioComum) {
        this.usuarioComum = usuarioComum;
    }
/*
    public AgendamentoVisita getAgendamentoVisita() {
        return agendamentoVisita;
    }

    public void setAgendamentoVisita(AgendamentoVisita agendamentoVisita) {
        this.agendamentoVisita = agendamentoVisita;
    }*/

    public ArrayList<TipoAbrigoSolicitacao> getTipoAbrigoSolicitacao() {
        return tipoAbrigoSolicitacao;
    }

    public void setTipoAbrigoSolicitacao(ArrayList<TipoAbrigoSolicitacao> tipoAbrigoSolicitacao) {
        this.tipoAbrigoSolicitacao = tipoAbrigoSolicitacao;
    }

    public String getPossuiPropriedade() {
        return possuiPropriedade;
    }

    public void setPossuiPropriedade(String possuiPropriedade) {
        this.possuiPropriedade = possuiPropriedade;
    }

    public int getQtdMediaAnimais() {
        return qtdMediaAnimais;
    }

    public void setQtdMediaAnimais(int qtdMediaAnimais) {
        this.qtdMediaAnimais = qtdMediaAnimais;
    }

    public int getQtdAnimaisMordidos() {
        return qtdAnimaisMordidos;
    }

    public void setQtdAnimaisMordidos(int qtdAnimaisMordidos) {
        this.qtdAnimaisMordidos = qtdAnimaisMordidos;
    }

    public String getCasosMorteRegiao() {
        return casosMorteRegiao;
    }

    public void setCasosMorteRegiao(String casosMorteRegiao) {
        this.casosMorteRegiao = casosMorteRegiao;
    }

    public String getProprieLocaisProximos() {
        return proprieLocaisProximos;
    }

    public void setProprieLocaisProximos(String proprieLocaisProximos) {
        this.proprieLocaisProximos = proprieLocaisProximos;
    }

    public String getTempoOcorridoMorte() {
        return tempoOcorridoMorte;
    }

    public void setTempoOcorridoMorte(String tempoOcorridoMorte) {
        this.tempoOcorridoMorte = tempoOcorridoMorte;
    }

    public String getConheceAbrigo() {
        return conheceAbrigo;
    }

    public void setConheceAbrigo(String conheceAbrigo) {
        this.conheceAbrigo = conheceAbrigo;
    }

    public String getSolicitarRecolhimento() {
        return solicitarRecolhimento;
    }

    public void setSolicitarRecolhimento(String solicitarRecolhimento) {
        this.solicitarRecolhimento = solicitarRecolhimento;
    }

    @Override
    public String toString() {
        return "Cod: " + cdSolicitacao + ", Produtor: " + usuarioComum.getNome();
    }

    

    public SolicitacaoVisita(int cdSolicitacao) {
        this.cdSolicitacao = cdSolicitacao;
    }

    public SolicitacaoVisita() {
    }
	
        
}
 
