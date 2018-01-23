package scrcm.com.scrcm.model;

/**
 * Created by Oberdan on 20/09/2017.
 */

public class FuncionarioAgenda {
    private Funcionario funcionario;
    private AgendamentoVisita agendamentoVisita;


    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public AgendamentoVisita getAgendamentoVisita() {
        return agendamentoVisita;
    }

    public void setAgendamentoVisita(AgendamentoVisita agendamentoVisita) {
        this.agendamentoVisita = agendamentoVisita;
    }

    @Override
    public String toString() {
        return funcionario.getNome();
    }
}
