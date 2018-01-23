package scrcm.com.scrcm.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Oberdan on 29/08/2017.
 */

public class Bairro implements Serializable {

    private int cdBairro;

    private String nomeBairro;

    private ArrayList<Endereco> endereco;

    private Cidade cidade;

    public int getCdBairro() {
        return cdBairro;
    }

    public void setCdBairro(int cdBairro) {
        this.cdBairro = cdBairro;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public ArrayList<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(ArrayList<Endereco> endereco) {
        this.endereco = endereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String toString() {
        return getNomeBairro();
    }

    public Bairro(int cdBairro) {
        this.cdBairro = cdBairro;
    }

    public Bairro() {
    }
}
