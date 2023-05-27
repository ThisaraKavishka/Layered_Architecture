package superDAO.DAO;

import model.OrderDTO;

import java.sql.*;

public interface OrderDAO {
    public String generateNewOID() throws SQLException, ClassNotFoundException ;

    public boolean exitOrder(String orderId) throws SQLException, ClassNotFoundException ;

    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException ;
}
