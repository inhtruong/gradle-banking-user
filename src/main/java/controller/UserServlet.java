package controller;

import DAO.TransferDAO;
import DAO.UserDAO;
import model.Transfer;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private TransferDAO transferDAO;

    public void init() {
        userDAO = new UserDAO();
        transferDAO = new TransferDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    break;
                case "edit":
                    updateUser(request, response);
                    break;
                case "transfer":
                    transferBalance(request,response);
                    break;
                case "search":
                    searchUser(request, response);
                    break;
                default:
                    insertUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "transfer":
                    showTransferForm(request,response);
                    break;
                case "history":
                    showHistoryTransfer(request,response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showHistoryTransfer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Transfer> transfers = transferDAO.selectAllTransfer();
        request.setAttribute("listTransfer", transfers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/transfer-history.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showTransferForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        List<User> userExceptId = userDAO.selectAllUsersExceptId(id);

        request.setAttribute("user", existingUser);
        request.setAttribute("userExceptId", userExceptId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/transfer.jsp");
        dispatcher.forward(request, response);

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String balance = request.getParameter("balance");
        if (name.equals("") || email.equals("") || country.equals("") || balance.equals("")) {
            request.setAttribute("mess-alert", "Bad or missing input information");
            listUser(request, response);
//        } else if (checkInput.isNumeric(name) || checkInput.isNumeric(email) || checkInput.isNumeric(country)){
//            request.setAttribute("mess-alert", "Value does not match");
//            listUser(request, response);
//        } else if (!checkInput.isNumeric(balance)) {
//            request.setAttribute("mess-alert", "Invalid value");
//            listUser(request, response);
//        } else if (Float.parseFloat(balance) < 0 || Float.parseFloat(balance) > 100000) {
//            request.setAttribute("mess-alert", "Invalid value");
//            listUser(request, response);
        } else {
            User newUser = new User(name, email, country, Float.parseFloat(balance));
            userDAO.insertUser(newUser);

            request.setAttribute("mess-add", "New user was created");
            listUser(request, response);
        }

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        if (name == "" || email == "" || country == "") {
            request.setAttribute("warning", "Bad or missing input information");
            showEditForm(request, response);
        } else if (checkInput.isNumeric(name) || checkInput.isNumeric(email) || checkInput.isNumeric(country)) {
            request.setAttribute("warning", "Value does not match");
            showEditForm(request, response);
        } else {
            User newUser = new User(id, name, email, country, userDAO.selectUser(id).getBalance());
            userDAO.updateUser(newUser);

            request.setAttribute("success", "Editing is successful");
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
            dispatcher.forward(request, response);
        }


    }

    public void transferBalance(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String idSend = request.getParameter("idSend");
        String idReceive = request.getParameter("idReceive");
        String amount = request.getParameter("salaryR");
        String fee = request.getParameter("fee");
        String feeAmount = request.getParameter("amount");
        String amountReceive = String.valueOf(Float.parseFloat(amount) + Float.parseFloat(feeAmount));
        User userSend = userDAO.selectUser(Integer.parseInt(idSend));
        User userReceive = userDAO.selectUser(Integer.parseInt(idReceive));

        if (userReceive != null) {
            userSend.setId(Integer.parseInt(idSend));

            if (userReceive != userSend) {
                if (amount == "") {
                    request.setAttribute("error", "Bad or missing input balance");
                    showTransferForm(request,response);
                } else {
                    if (!checkInput.isNumeric(amount) || !checkInput.isNumeric(idReceive)) {
                        request.setAttribute("error", "The transaction is not executed. The system will be upgraded");
                        showTransferForm(request,response);
                    } else {
                        if (Float.parseFloat(amountReceive) > userSend.getBalance() || Float.parseFloat(amountReceive) < 0) {
                            request.setAttribute("error", "Your balance is not enough");
                            showTransferForm(request,response);

                        } else {
                            if (userDAO.updateBalance(userSend,userReceive,Float.parseFloat(amountReceive))) {
                                Transfer newTransfer = new Transfer(Integer.parseInt(idSend), Integer.parseInt(idReceive), Float.parseFloat(amount),Integer.parseInt(fee), Float.parseFloat(feeAmount));
                                transferDAO.insertTransfer(newTransfer);
                                request.setAttribute("success", "You have successfully transferred to ID: " +  idSend + " the amount of " + amountReceive);
//                            RequestDispatcher dispatcher = request.getRequestDispatcher("user/transfer.jsp");
//                            dispatcher.forward(request, response);
                                showTransferForm(request,response);
                            } else {
                                request.setAttribute("error", "The transaction is not executed. The system will be upgraded");
                                showTransferForm(request,response);
                            }
                        }
                    }
                }
            } else {
                request.setAttribute("error", "Transaction failed!");
                showTransferForm(request,response);
            }
        } else {
            request.setAttribute("warning", "This ID does not exist in the system");
            showTransferForm(request,response);
        }

    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        String str = request.getParameter("string-search");
        List<User> users = userDAO.searchUser(str);
        request.setAttribute("listUser",users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);

        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        request.setAttribute("message_delete", "User has been deleted");
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }

}
