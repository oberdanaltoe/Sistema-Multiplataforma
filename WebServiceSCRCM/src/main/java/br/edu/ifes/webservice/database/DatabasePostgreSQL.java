package br.edu.ifes.webservice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabasePostgreSQL implements Database {

    private Connection conn;

    @Override
    public Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/loja", "postgres", "ifes");
            System.out.println("Conectado com Sucesso!");
            return this.conn;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public void desconectar(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao desconectar: " + ex.getMessage());
        }
    }
}
