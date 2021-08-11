<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/1/2021
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Transaction history</title>
    <%@ include file="/layout/head.jsp"%>
</head>
<body>
    <div class="container">
        <h1>User Management</h1>
        <p>
            <a href="user">Back to home page</a>
        </p>
        <caption>
            <h4>Transfer</h4>
        </caption>
<%--        <div class="navbar navbar-light bg-light">--%>
<%--            <form class="form-inline my-2 my-lg-0" method="post" action="/user?action=search">--%>
<%--                <input class="form-control mr-sm-2" name="string-search" type="search" placeholder="Search" aria-label="Search">--%>
<%--                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>--%>
<%--            </form>--%>
<%--        </div>--%>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">ID người gửi</th>
                <th scope="col">Tên người gửi</th>
                <th scope="col">ID người nhận</th>
                <th scope="col">Tên người nhận</th>
                <th scope="col">Số tiền chuyển</th>
                <th scope="col">Phí chuyển</th>
                <th scope="col">Tiền phí</th>
            </tr>
            </thead>
            <c:forEach var="transfer" items="${listTransfer}">
                <tbody>
                <tr>
                    <td scope="row">${transfer.getId()}</td>
                    <td>${transfer.getIdSender()}</td>
                    <td>${transfer.getNameSender()}</td>
                    <td>${transfer.getIdReceive()}</td>
                    <td>${transfer.getNameReceive()}</td>
                    <td>${transfer.getAmountReceive()}</td>
                    <td>${transfer.getFeesPercent()}</td>
                    <td>${transfer.getFeesAmount()}</td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <script type="text/javascript" src="../js/script.js"></script>

</body>
</html>
