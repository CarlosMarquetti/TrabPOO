/* 
@authors Carlos,
@authors Gustavo,
@authors Nataly;
 */

package fatec.poo.control;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

import fatec.poo.model.Cliente;
public class DaoCliente{

    private Connection conn;
    
    public DaoCliente(Connection conn) {
         this.conn = conn;
    }
    
    public void inserir(Cliente cliente){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Cliente"
                                                            + "(CPF,"
                                                            + "Nome,"
                                                            + "Endereco,"
                                                            + "Cidade,"
                                                            + "CEP,"
                                                            + "UF,"
                                                            + "TelefoneDDD,"
                                                            + "TelefoneNumero,"
                                                            + "LimiteCredito)"
                                                            + "VALUES(?,?,?,?,?,?,?,?,?)");

            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getEndereco());
	    ps.setString(4, cliente.getCidade());
	    ps.setString(5, cliente.getCep());
	    ps.setString(6, cliente.getUf());
            ps.setString(7, cliente.getDdd());
            ps.setString(8, cliente.getTelefone());
	    ps.setDouble(9, cliente.getLimiteCred());
	    /*adicionar disponiveis;*/

            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void alterar(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE Cliente set Nome = ?, " 
					+ "Endereco = ?, "
					+ "Cidade = ?, "
					+ "CEP = ?, "
					+ "UF = ?, "
					+ "TelefoneDDD = ?, "
					+ "TelefoneNumero = ?, "
					+ "LimiteCredito = ? "
					+ "where CPF = ? ");
                                        /*+ "LimiteDisponivel = ? "*/
                                        /*adicionar o campo de limite disponivel na tabelas*/
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
	    ps.setString(3, cliente.getCidade());
            ps.setString(4, cliente.getCep());
	    ps.setString(5, cliente.getUf());
            ps.setString(6, cliente.getDdd());
	    ps.setString(7, cliente.getTelefone());
            ps.setDouble(8, cliente.getLimiteCred());
            ps.setString(9, cliente.getCpf());
	    /*ps.setDouble(9, cliente.getLimiteDisp());*/
           
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
        
     public Cliente consultar (String CPF) {
        Cliente cliente = null;
       
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * from Cliente where CPF = ?");
            
            ps.setString(1, CPF);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next() == true) {
                cliente = new Cliente (CPF, rs.getString("Nome"), rs.getDouble("LimiteCredito"));
		cliente.setCep(rs.getString("CEP"));
		cliente.setCidade(rs.getString("Cidade"));
		cliente.setDdd(rs.getString("TelefoneDDD"));
		cliente.setEndereco(rs.getString("Endereco"));
                cliente.setTelefone(rs.getString("TelefoneNumero"));
                cliente.setUf(rs.getString("UF"));
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (cliente);
    }    
     
     public void excluir(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM Cliente where CPF = ?");
            ps.setString(1, cliente.getCpf());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
}
