package br.edu.ifes.webservice.database;

import java.sql.Connection;

public interface Database {

    public Connection conectar();

    public void desconectar(Connection conn);
}
