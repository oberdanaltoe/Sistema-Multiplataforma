package scrcm.domain;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class AgendamentoVisita {
 
	private int cdAgendamentoVisita;
        
        private int status;
	 
	private float longitude;
	 
	private float latitude;
        
        private String rua;
        
        private String tipoVisita;
        
        private int numero;
	 
	private Date data;
        
        private Bairro bairro;
        
        private UsuarioComum usuarioComum;
	 
	private SolicitacaoVisita solicitacaoVisita;
        
        private Funcionario funcionario;
	 
	private List<FuncionarioAgenda> listfa;          
        

        public AgendamentoVisita(){
           this.bairro = new Bairro();
           this.bairro.setCidade(new Cidade());
        }
        
    public int getCdAgendamentoVisita() {
        return cdAgendamentoVisita;
    }

    public void setCdAgendamentoVisita(int cdAgendamentoVisita) {
        this.cdAgendamentoVisita = cdAgendamentoVisita;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public SolicitacaoVisita getSolicitacaoVisita() {
        return solicitacaoVisita;
    }

    public void setSolicitacaoVisita(SolicitacaoVisita solicitacaoVisita) {
        this.solicitacaoVisita = solicitacaoVisita;
    }
     

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Cod:" + getCdAgendamentoVisita()+", Produtor:" +solicitacaoVisita.getUsuarioComum().getNome() ;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public AgendamentoVisita(int cdAgendamentoVisita) {
        this.cdAgendamentoVisita = cdAgendamentoVisita;
    }

    
        
        public String getTipoVisita() {
        return tipoVisita;
    }

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }
   

    public List<FuncionarioAgenda> getListfa() {
        return listfa;
    }

    public void setListfa(List<FuncionarioAgenda> listfa) {
        this.listfa = listfa;
    }

    public UsuarioComum getUsuarioComum() {
        return usuarioComum;
    }

    public void setUsuarioComum(UsuarioComum usuarioComum) {
        this.usuarioComum = usuarioComum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }   

  
	 
}
 
