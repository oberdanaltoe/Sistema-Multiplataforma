/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.AbrigoService;
import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.Abrigo;
import br.edu.ifes.webservice.model.AgendamentoVisita;
import br.edu.ifes.webservice.model.Captura;
import br.edu.ifes.webservice.model.Visita;
import java.sql.Connection;
import java.sql.Date;
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
public class CapturaDAO {
    private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();
    
    public boolean inserir(Captura captura) {
        System.out.println("captura:" +captura.toString());
        String sql = "INSERT INTO captura(cod_captura, longitude, latitude, "
                + "qtd_morcegos_capturados, cod_abrigo, qtd_morcegos_tratados, "
                + "qtd_morcegos_enviados_laboratorio, data_captura) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, captura.getCdCaptura());
            stmt.setDouble(2, captura.getLongitude());
            stmt.setDouble(3, captura.getLatitude());
            stmt.setInt(4, captura.getQtdMorcegosCapturados());
            stmt.setInt(5, captura.getAbrigo().getCdAbrigo());
            stmt.setInt(6, captura.getQtdMorcegosTratados());
            stmt.setInt(7, captura.getQtdEnviadosLaboratorio());            
            stmt.setDate(8, (Date) captura.getDataCaptura());              
            
            stmt.execute();
            this.connection.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(CapturaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Captura> listar() throws SQLException  {
        String sql = "SELECT * FROM captura;";

        List<Captura> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Abrigo abrigo = new Abrigo();
                AbrigoDAO abrigoDAO = new AbrigoDAO();
                Captura captura = new Captura();
                
                captura.setCdCaptura(resultado.getInt("cod_captura"));
                captura.setLongitude(resultado.getFloat("longitude"));
                captura.setLatitude(resultado.getFloat("latitude"));
                captura.setQtdMorcegosCapturados(resultado.getInt("qtd_morcegos_capturados"));
                captura.setQtdEnviadosLaboratorio(resultado.getInt("qtd_morcegos_enviados_laboratorio"));
                captura.setQtdMorcegosTratados(resultado.getInt("qtd_morcegos_tratados"));                                     
                abrigo.setCdAbrigo(resultado.getInt("cod_abrigo"));
                abrigoDAO.buscar(abrigo);

                captura.setAbrigo(abrigo);
                retorno.add(captura);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CapturaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.connection.close();
        return retorno;
    }
     public boolean alterar(Captura captura) throws SQLException {
        String sql = "UPDATE captura SET cod_visita = ? WHERE cod_captura = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            Visita visita = new Visita();
            VisitaDAO vdao = new VisitaDAO();
            
            visita.setCdVisita(vdao.buscarUltimo());       
            
            stmt.setInt(1, visita.getCdVisita());
            stmt.setInt(2, captura.getCdCaptura());
            

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CapturaDAO.class.getName()).log(Level.SEVERE, null, ex);
            this.connection.close();
            return false;
        }
    }
}
