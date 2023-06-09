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

import fatec.poo.model.Pedido;

public class DaoPedido {
    private Connection conn;
    
    public DaoPedido(Connection conn) {
         this.conn = conn;
    }
    
}
