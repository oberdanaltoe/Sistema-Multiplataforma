package scrcm.com.scrcm.model;

/**
 * Created by Oberdan on 20/09/2017.
 */

public class Funcionario {
    private int cdFuncionario;

    private String login;

    private String senha;

    private String nome;

    private String cpf;

    private String rua;

    private String pontoReferencia;

    private int numero;

    private Bairro bairro;

    private Especialidade especialidade;

    private Funcao funcao;

    public int getCdFuncionario() {
        return cdFuncionario;
    }

    public void setCdFuncionario(int cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Funcionario(int cdFuncionario) {
        this.cdFuncionario = cdFuncionario;
    }

    public String toString() {
        return nome;
    }

    public Funcionario() {
    }
}
