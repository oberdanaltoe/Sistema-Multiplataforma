package scrcm.com.scrcm.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Oberdan on 29/08/2017.
 */

public class Endereco implements Serializable {

    private String rua;

    private String ponto_referencia;

    private int numero;

    private ArrayList<Pessoa> pessoa;

    //private Bairro bairro;

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

    public ArrayList<Pessoa> getPessoa() {
        return pessoa;
    }

    public void setPessoa(ArrayList<Pessoa> pessoa) {
        this.pessoa = pessoa;
    }

    /*public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }*/

    public String getPonto_referencia() {
        return ponto_referencia;
    }

    public void setPonto_referencia(String ponto_referencia) {
        this.ponto_referencia = ponto_referencia;
    }

    @Override
    public String toString() {
        return "Endereco{" + "rua=" + rua + ", ponto_referencia=" + ponto_referencia + '}';
    }

}
