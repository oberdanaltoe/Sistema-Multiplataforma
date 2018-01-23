package scrcm.com.scrcm.service;

import scrcm.com.scrcm.model.UsuarioComum;

/**
 * Created by Oberdan on 11/10/2017.
 */

public class Logado {

    private static UsuarioComum usuarioComumStatic;
    private static Boolean estaLogado;

    public UsuarioComum getUsuarioComumStatic() {
        return usuarioComumStatic;
    }

    public void setUsuarioComumStatic(UsuarioComum usuarioComumStatic) {
        this.usuarioComumStatic = usuarioComumStatic;
    }

    public Boolean getEstaLogado() {
        return estaLogado;
    }

    public void setEstaLogado(Boolean estaLogado) {
        this.estaLogado = estaLogado;
    }
}
