<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/28/2021
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Transfer</title>
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

        <form method="post" class="row">
            <c:if test="${user != null}">
                <input type="hidden" name="idSend" value="<c:out value='${user.id}'/>" />
            </c:if>

            <div class="form-group col-12 col-sm-12 col-md-12">
                <label>Sender Information: </label>
            </div>
            <div class="form-group col-12 col-sm-6 col-md-4">
                <label>Tên người gửi: </label>
                <input type="text" name="name" class="form-control" disabled value="<c:out value='${user.name}' />">
            </div>
            <div class="form-group col-12 col-sm-6 col-md-4">
                <label>Email: </label>
                <input type="text" name="email" class="form-control" disabled value="<c:out value='${user.email}' />">
            </div>
            <div class="form-group col-12 col-sm-6 col-md-4">
                <label>Số dư tài khoản: </label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <div class="input-group-text">$</div>
                    </div>
                    <input type="text" name="balance" class="form-control" disabled value="<c:out value='${user.balance}' />">
                </div>
            </div>

            <div class="form-group col-12 col-sm-12 col-md-12">
                <label>Receiver Information: </label>
            </div>
            <div class="form-group col-12 col-sm-6 col-md-4">
                <label>ID người nhận: </label>
<%--                <input type="text" name="idReceive" class="form-control">--%>
                <select name="idReceive" class="form-control">
                    <c:forEach var="userReceive" items="${userExceptId}">
                        <option value="<c:out value='${userReceive.id}' />"><c:out value='${userReceive.id}' /></option>
                    </c:forEach>
                </select>
            </div>
<%--            <div class="form-group col-12 col-sm-6 col-md-4">--%>
<%--                <label>Tên người nhận: </label>--%>
<%--                <input type="text" name="name" class="form-control" disabled value="<c:out value='${userReceive.name}' />">--%>
<%--            </div>--%>
            <div class="form-group col-12 col-sm-6 col-md-4">
                <label>Số tiền chuyển: </label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <div class="input-group-text">$</div>
                    </div>
                    <input type="text" name="salaryR" id="salaryR" oninput="calculatorFee()" class="form-control">
                </div>
            </div>

            <div class="form-group col-12 col-sm-12 col-md-12">
                <label>Service Fee: </label>
            </div>
            <div class="form-group col-12 col-sm-6 col-md-4">
                <label>Chiết khấu (%): </label>
                <input type="text" name="fee" class="form-control" value="5">
            </div>
              <div class="form-group col-12 col-sm-6 col-md-4">
                <label>Phí chuyển: </label>
                <input type="text" name="amount" id="amount" class="form-control">
            </div>


            <div class="col-12 col-sm-12 col-md-12">
                <label>
                    Status:
                    <c:if test='${requestScope["success"] != null}'>
                        <span class="message" style="color: blue;"><i class="fa fa-check-circle"></i> ${requestScope["success"]}</span>
                    </c:if>
                    <c:if test='${requestScope["error"] != null}'>
                        <span class="message" style="color: red;"><i class="fa fa-exclamation-circle"></i> ${requestScope["error"]}</span>
                    </c:if>
                    <c:if test='${requestScope["warning"] != null}'>
                        <span class="message" style="color: #fa983a;"><i class="fa fa-exclamation-triangle"></i> ${requestScope["warning"]}</span>
                    </c:if>
                </label>
            </div>
            <button type="submit" class="btn btn-primary" >Submit</button>
        </form>
    </div>
    <script src="../js/script-transfer.js"></script>
</body>
</html>
