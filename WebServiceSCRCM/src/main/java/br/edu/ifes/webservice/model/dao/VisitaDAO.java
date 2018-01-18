
package br.edu.ifes.webservice.model.dao;

import br.edu.ifes.webservice.database.DatabaseFactory;
import br.edu.ifes.webservice.model.Visita;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oberdan
 */
public class VisitaDAO {
     private final Connection connection = DatabaseFactory.getDatabase("mysql").conectar();
    
    public boolean inserir(Visita visita) {
        String sql = "INSERT INTO visita(data_visita,"
                + " cod_funcionario, cod_agendamento, observacoes)"
                + " VALUES (?,?,?,?);";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);           
            
            stmt.setDate(1, visita.getData()); 
            stmt.setInt(2, visita.getFuncionario().getCdFuncionario());
            stmt.setInt(3, visita.getAgendamentoVisita().getCdAgendamentoVisita());
            stmt.setString(4, visita.getObservacoes());                   
            
            stmt.execute();
            this.connection.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(VisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public int buscarUltimo() {
        String sql = "SELECT MAX(cod_visita) AS codigo FROM visita";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                Visita visita = new Visita();
                     int x = resultado.getInt("codigo");           
                     visita.setCdVisita(x);
                
                return visita.getCdVisita();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(VisitaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
}
