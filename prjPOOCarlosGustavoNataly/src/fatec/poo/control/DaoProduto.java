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

import fatec.poo.model.Produto;

public class DaoProduto {
    private Connection conn;
    
    public DaoProduto(Connection conn) {
         this.conn = conn;
    }
    
    public void inserir(Produto produto){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Vendedor(CodigoProduto, Descricao, QtdeDisponivel, UnidadeMedida, PrecoUnit, EstoqueMinimo) VALUES(?,?,?,?,?,?)");
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getQtdeEstoque());
            ps.setString(4, produto.getUnidadeMedida());
	    ps.setDouble(5, produto.getPreco());
	    ps.setDouble(6, produto.getEstoqueMinimo());

            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
    public void alterar(Produto produto) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("UPDATE Produto set Descricao = ? " 
					+ "QtdeDisponivel = ? "
					+ "PrecoUnit = ? "
					+ "EstoqueMinimo = ? "
                                        + "UnidadeMedia = ? "
					+ "where CodigoProduto  = ? ");
            
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getDescricao());
	    ps.setDouble(3, produto.getQtdeEstoque());
            ps.setString(4, produto.getUnidadeMedida());
            ps.setDouble(5, produto.getPreco());
	    ps.setDouble(6, produto.getEstoqueMinimo());
           
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
        
     public Produto consultar (String CodigoProduto) {
        Produto produto = null;
       
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("SELECT * from Produto where CodigoProduto = ?");
            
            ps.setString(1, CodigoProduto);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next() == true) {
                produto = new Produto(CodigoProduto, rs.getString("Descricao"));
                produto.setEstoqueMinimo(rs.getDouble("EstoqueMinimo"));
                produto.setUnidadeMedida(rs.getString("UnidadeMedida"));
                produto.setPreco(rs.getDouble("PrecoUnit"));
		produto.setQtdeEstoque(rs.getDouble("QtdeDisponivel"));
            }
        }
        catch (SQLException ex) { 
             System.out.println(ex.toString());   
        }
        return (produto);
    }    
     
     public void excluir(Produto produto) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM Produto where CodigoProduto = ?");
            ps.setString(1, produto.getCodigo());
                      
            ps.execute();
        } catch (SQLException ex) {
             System.out.println(ex.toString());   
        }
    }
    
}
