package superDAO.DAO.DAOImpl;

import entity.Item;
import superDAO.DAO.ItemDAO;
import superDAO.SQLUtil;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        ArrayList<Item> allCustomers = new ArrayList<>();
        while (rst.next()) {
            allCustomers.add(new Item(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getInt(4)));
        }
        return allCustomers;
    }

    @Override
    public boolean add(Item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)", dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand());
    }

    @Override
    public boolean update(Item dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getCode());
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT code FROM Item WHERE code=?", code);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Item WHERE code=?", code);
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?", code);
        rst.next();
        return new Item(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }
}
