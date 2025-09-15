import java.sql.*;
import java.util.ArrayList;

public class DataIO {
    private final String CONNECTION_STRING = "jdbc:sqlite:customers.db";
    private final String USER_NAME = "N/A";
    private final String PASSWORD = "N/A";

    public DataIO() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);
        String sql = "CREATE TABLE IF NOT EXISTS customers (" +
                "customerID INTEGER PRIMARY KEY, " +
                "name TEXT, address TEXT, yardType TEXT, " +
                "length REAL, width REAL, totalCost REAL)";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        conn.close();
    }

    public void add(Customer c) throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);
        String sql = "INSERT INTO customers VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, c.getCustomerID());
        ps.setString(2, c.getName());
        ps.setString(3, c.getAddress());
        ps.setString(4, c.getYardType());
        ps.setDouble(5, c.getLength());
        ps.setDouble(6, c.getWidth());
        ps.setDouble(7, c.getTotalCost());
        ps.execute();
        conn.close();
    }

    public boolean delete(int customerID) throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);
        String sql = "DELETE FROM customers WHERE customerID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, customerID);
        int rows = ps.executeUpdate();
        conn.close();
        return rows > 0;
    }

    public boolean update(int customerID, double newTotalCost) throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);
        String sql = "UPDATE customers SET totalCost = ? WHERE customerID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, newTotalCost);
        ps.setInt(2, customerID);
        int rows = ps.executeUpdate();
        conn.close();
        return rows > 0;
    }

    public ArrayList<Customer> getAll() throws SQLException {
        ArrayList<Customer> list = new ArrayList<>();
        Connection conn = DriverManager.getConnection(CONNECTION_STRING);
        String sql = "SELECT * FROM customers";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            list.add(new Customer(
                rs.getInt("customerID"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("yardType"),
                rs.getDouble("length"),
                rs.getDouble("width"),
                rs.getDouble("totalCost")
            ));
        }
        conn.close();
        return list;
    }
}
