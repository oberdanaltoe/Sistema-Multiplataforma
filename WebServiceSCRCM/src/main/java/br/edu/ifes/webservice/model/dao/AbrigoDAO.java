/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.Abrigo;
import br.edu.ifes.webservice.model.TipoAbrigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oberdan
 */
public class AbrigoDAO {

    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();

    public boolean inserir(Abrigo abrigo) {
        String sql = "INSERT INTO abrigo(cod_abrigo, longitude, latitude, ponto_referencia, cod_tipo_abrigo) "
                + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, abrigo.getCdAbrigo());
            stmt.setDouble(2, abrigo.getLongitude());
            stmt.setDouble(3, abrigo.getLatitude());
            stmt.setString(4, abrigo.getPontoReferencia());
            stmt.setDouble(5, abrigo.getTipoAbrigo().getCdAbrigo());
            stmt.execute();
            this.connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AbrigoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Abrigo> listar() throws SQLException {
        String sql = "SELECT * FROM abrigo ORDER BY cod_abrigo DESC;";

        List<Abrigo> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Abrigo abrigo = new Abrigo();
                TipoAbrigo tipoAbrigo = new TipoAbrigo();
                TipoAbrigoDAO tipoAbrigoDAO = new TipoAbrigoDAO();

                abrigo.setCdAbrigo(resultado.getInt("cod_abrigo"));
                abrigo.setLongitude(resultado.getFloat("longitude"));
                abrigo.setLatitude(resultado.getFloat("latitude"));
                abrigo.setPontoReferencia(resultado.getString("ponto_referencia"));
                tipoAbrigo.setCdAbrigo(resultado.getInt("cod_tipo_abrigo"));

                tipoAbrigo = tipoAbrigoDAO.buscar(tipoAbrigo);
                abrigo.setTipoAbrigo(tipoAbrigo);

                retorno.add(abrigo);
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(AbrigoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }

    public Abrigo buscar(Abrigo abrigo) throws SQLException {
        String sql = "SELECT * FROM abrigo WHERE cod_abrigo=?;";
        Abrigo retorno = new Abrigo();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, abrigo.getCdAbrigo());
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                TipoAbrigo tipoAbrigo = new TipoAbrigo();
                TipoAbrigoDAO tipoAbrigoDAO = new TipoAbrigoDAO();
                tipoAbrigo.setCdAbrigo(resultado.getInt("cod_tipo_abrigo"));
                tipoAbrigo = tipoAbrigoDAO.buscar(tipoAbrigo);

                abrigo.setCdAbrigo(resultado.getInt("cod_abrigo"));
                abrigo.setTipoAbrigo(tipoAbrigo);

                retorno = abrigo;

            }
        } catch (SQLException ex) {
            Logger.getLogger(AbrigoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }
}
