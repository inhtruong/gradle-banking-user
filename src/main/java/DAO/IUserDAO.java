package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public void insertUser(User user) throws SQLException;

    public User selectUser(int id);

    public List<User> selectAllUsers();

    public List<User> selectAllUsersExceptId (int id);

    public boolean deleteUser(int id) throws SQLException;

    public boolean updateBalance(User userSend,User userReceive, float balance) throws SQLException;

//    public boolean updateReceive(User user) throws SQLException;

    public boolean updateUser(User user) throws SQLException;

    public List<User> searchUser(String name) throws SQLException;
}
