package DAO;

import model.Transfer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferDAO implements ITransferDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Inhtruong@123";

    private static final String INSERT_TRANSFER_SQL = "INSERT INTO transfers (idSender, idReceive, amountReceive, feesPercent, feesAmount)" +
            " VALUES (?,?,?,?,?);";
    private static final String SELECT_ALL_TRANSFER_SQL = "SELECT t.id, t.idSender, u.name_user, t.idReceive, u2.name_user, t.amountReceive, t.feesPercent, t.feesAmount \n" +
            "FROM transfers t\n" +
            "LEFT JOIN users u\n" +
            "ON u.id = t.idSender\n" +
            "LEFT JOIN users u2 \n" +
            "ON u2.id = t.idReceive";

    public TransferDAO() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertTransfer(Transfer transfer) throws SQLException {
        System.out.println(INSERT_TRANSFER_SQL);
        List<Transfer> transfers = new ArrayList<>();

        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_TRANSFER_SQL);

            ps.setInt(1, transfer.getIdSender());
            ps.setInt(2, transfer.getIdReceive());
            ps.setFloat(3, transfer.getAmountReceive());
            ps.setInt(4, transfer.getFeesPercent());
            ps.setFloat(5, transfer.getFeesAmount());

            ps.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    @Override
    public Transfer selectTransfer(int id) {
        return null;
    }

    @Override
    public List<Transfer> selectAllTransfer() {
        List<Transfer> transfers = new ArrayList<>();

        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_TRANSFER_SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                int idSender = rs.getInt(2);
                String nameSender = rs.getString(3);
                int idReceive = rs.getInt(4);
                String nameReceive = rs.getString(5);
                float amount = rs.getFloat(6);
                int feesPercent = rs.getInt(7);
                float feesAmount = rs.getFloat(8);
                transfers.add(new Transfer(id, idSender, idReceive, amount, feesPercent, feesAmount, nameSender, nameReceive));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return transfers;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
