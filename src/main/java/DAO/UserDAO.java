package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{

    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Inhtruong@123";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name_user, email, country, balance) VALUES " +
            " (?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "SELECT id,name_user,email,country,balance FROM users WHERE id =?";
    private static final String SELECT_ALL_USERS = "SELECT id,name_user,email,country,balance FROM users order by id desc";
    private static final String SELECT_ALL_USERS_EXCEPT_ID = "SELECT id,name_user,email,country,balance FROM users where id != ?";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET name_user = ?,email= ?, country =?, balance=? WHERE id = ?;";
    private static final String UPDATE_BALANCE_SEND_SQL = "UPDATE users SET balance = balance - ? WHERE id = ?;";
    private static final String UPDATE_BALANCE_RECEIVE_SQL = "UPDATE users SET balance = balance + ? WHERE id = ?;";
    private static final String SEARCH_USER_BY_NAME = "SELECT * FROM users WHERE name_user LIKE ?;";

    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<User> searchUser(String nameFind) throws SQLException {
        List<User> users = new ArrayList<User>();
        Connection connection = getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(SEARCH_USER_BY_NAME);
            ps.setString(1,"%" + nameFind + "%");
            System.out.println(SEARCH_USER_BY_NAME);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name_user");
                String email = rs.getString("email");
                String country = rs.getString("country");
                float balance = Float.parseFloat(rs.getString("balance"));
                users.add(new User(id, name, email, country, balance));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    @Override
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        List<User> users = new ArrayList<>();
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setFloat(4,user.getBalance());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name_user");
                String email = rs.getString("email");
                String country = rs.getString("country");
                Float balance = rs.getFloat("balance");
                user = new User(id, name, email, country, balance);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<User> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name_user");
                String email = rs.getString("email");
                String country = rs.getString("country");
                float balance = rs.getFloat("balance");
                users.add(new User(id, name, email, country, balance));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    @Override
    public List<User> selectAllUsersExceptId(int id) {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_EXCEPT_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int idNo = rs.getInt("id");
                String name = rs.getString("name_user");
                String email = rs.getString("email");
                String country = rs.getString("country");
                Float balance = rs.getFloat("balance");
                users.add(new User(idNo, name, email, country, balance));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted = false;
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(DELETE_USERS_SQL);

            ps.setInt(1, id);
            ps.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            rowDeleted = true;
        } catch (SQLException e) {
            connection.rollback();
            printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateBalance(User userSend, User useReceive, float balance) throws SQLException {
        boolean update = false;
        Connection connection =  getConnection();
        try{
            connection.setAutoCommit(false);
            PreparedStatement psSend = connection.prepareStatement(UPDATE_BALANCE_SEND_SQL);

            psSend.setFloat(1,balance);
            psSend.setInt(2,userSend.getId());
            psSend.executeUpdate();

            PreparedStatement psReceive = connection.prepareStatement(UPDATE_BALANCE_RECEIVE_SQL);

            psReceive.setFloat(1,balance);
            psReceive.setInt(2,useReceive.getId());
            psReceive.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            update = true;
        }catch (SQLException e){
            connection.rollback();
            printSQLException(e);
        }

        return update;
    }


    @Override
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated = false;
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_USERS_SQL);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setFloat(4,user.getBalance());
            ps.setInt(5, user.getId());
            ps.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            rowUpdated = true;

        } catch (SQLException e){
            connection.rollback();
            printSQLException(e);
        }

        return rowUpdated;
    }

//    @Override
//    public boolean sreachUser(User user) throws SQLException {
//
//    }

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
