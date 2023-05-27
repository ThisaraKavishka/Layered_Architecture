package superDAO.DAO.DAOImpl;

import db.DBConnection;
import model.OrderDTO;
import superDAO.DAO.OrderDAO;
import superDAO.SQLUtil;

import java.sql.*;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String generateNewOID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean exitOrder(String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?", orderId);
    }

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)", dto.getOrderId(), Date.valueOf(dto.getOrderDate()), dto.getCustomerId());
    }
}
