package DAO;

import model.Transfer;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface ITransferDAO {
    public void insertTransfer(Transfer transfer) throws SQLException;

    public Transfer selectTransfer(int id);

    public List<Transfer> selectAllTransfer();

//    public List<Transfer> searchTransfer(int id);
}
