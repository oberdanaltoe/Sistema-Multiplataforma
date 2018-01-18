 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;


import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.RecolhimentoCerebro;
import br.edu.ifes.webservice.model.Visita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Oberdan Debona Altoé
 */
public class RecolhimentoCerebroDAO {
    
    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();
    
    public boolean inserir(RecolhimentoCerebro recolhimentoCerebro) {
        String sql = "INSERT INTO recolhimento_cerebro(cod_recolhimento_cerebro,"
                + " longitude, latitude, animal_morto, data, estado_carcaca) "
                + "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, recolhimentoCerebro.getCdVisitaRecolhimentoCerebro());
            stmt.setFloat(2, recolhimentoCerebro.getLongitude());
            stmt.setFloat(3, recolhimentoCerebro.getLatitude());
            stmt.setString(4, recolhimentoCerebro.getAnimalMorto());
            stmt.setDate(5, recolhimentoCerebro.getData());
            stmt.setString(6, recolhimentoCerebro.getEstadoCarcaca());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RecolhimentoCerebroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public RecolhimentoCerebro buscar(RecolhimentoCerebro recolhimentoCerebro) {
        String sql = "SELECT * FROM recolhimento_cerebro WHERE cod_recolhimento_cerebro=?;";
        RecolhimentoCerebro retorno = new RecolhimentoCerebro();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, recolhimentoCerebro.getCdVisitaRecolhimentoCerebro());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                
                Visita visita = new Visita();
                
                recolhimentoCerebro.setCdVisitaRecolhimentoCerebro(resultado.getInt("cod_recolhimento_cerebro"));
                recolhimentoCerebro.setLongitude(resultado.getFloat("longitude"));
                recolhimentoCerebro.setLatitude(resultado.getFloat("latitude"));
                recolhimentoCerebro.setAnimalMorto(resultado.getString("animalMorto"));
                recolhimentoCerebro.setData(resultado.getDate("data"));
                recolhimentoCerebro.setVisita(visita);
                
                retorno = recolhimentoCerebro;

            }
        } catch (SQLException ex) {
            Logger.getLogger(RecolhimentoCerebroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    public List<RecolhimentoCerebro> listar() {
        String sql = "SELECT * FROM recolhimento_cerebro";

        List<RecolhimentoCerebro> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Visita visita = new Visita();
                RecolhimentoCerebro recolhimentoCerebro = new RecolhimentoCerebro();
                
                recolhimentoCerebro.setCdVisitaRecolhimentoCerebro(resultado.getInt("cod_recolhimento_cerebro"));
                recolhimentoCerebro.setLongitude(resultado.getFloat("longitude"));
                recolhimentoCerebro.setLatitude(resultado.getFloat("latitude"));
                recolhimentoCerebro.setAnimalMorto(resultado.getString("animal_morto"));
                recolhimentoCerebro.setEstadoCarcaca("estado_carcada");
                recolhimentoCerebro.setData(resultado.getDate("data"));
                recolhimentoCerebro.setVisita(visita);
                
                retorno.add(recolhimentoCerebro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecolhimentoCerebroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
