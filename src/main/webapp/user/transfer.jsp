<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/28/2021
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Transfer</title>
    <%@ include file="/layout/head.jsp"%>
</head>
<body>
    <div class="be-wrapper be-fixed-sidebar">
        <%---------- NaviBar ---------%>
        <%@ include file="/layout/navbar.jsp"%>

        <%---------- Left sidebar ---------%>
        <%@ include file="/layout/be-left-sidebar.jsp"%>

        <%---------- Content ---------%>
        <div class="be-content">
            <div class="row">
                <div class="col-sm-12">
                    <div class="card card-border-color card-border-color-primary">
                        <div class="card-header">
                            Chuyển khoản
                        </div>
                        <form method="post" class="row" id="formValidate">
                            <c:if test="${user != null}">
                                <input type="hidden" name="idSend" value="<c:out value='${user.id}'/>" />
                            </c:if>
                            <div class="form-group col-12 col-sm-12 col-md-6 row">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">Tên người gửi: </label>
                                <div class="col-12 col-sm-8 col-lg-4">
                                    <input type="text" name="name" class="form-control" id="name" disabled value="<c:out value='${user.name}' />">
                                </div>
                            </div>
                            <div class="form-group col-12 col-sm-12 col-md-6 row">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">Email: </label>
                                <div class="col-12 col-sm-8 col-lg-4">
                                    <input type="email" name="email" class="form-control" id="email" disabled value="<c:out value='${user.email}' />">
                                </div>
                            </div>
                            <div class="form-group col-12 col-sm-12 col-md-6 row">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">Số dư tài khoản: </label>
                                <div class="col-12 col-sm-8 col-lg-4">
                                    <input type="number" name="balance" class="form-control" id="balance" disabled value="<c:out value='${user.balance}' />">
                                </div>
                            </div>
                            <div class="form-group col-12 col-sm-12 col-md-6 row">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">ID người nhận: </label>
                                <div class="col-12 col-sm-8 col-lg-4">
                                    <select name="idReceive" class="form-control">
                                        <c:forEach var="userReceive" items="${userExceptId}">
                                            <option value="<c:out value='${userReceive.id}' />"><c:out value='${userReceive.id}' /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-12 col-sm-6 col-md-6 row">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">Số tiền chuyển: </label>
                                <div class="input-group col-12 col-sm-8 col-lg-4">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">$</div>
                                    </div>
                                    <input type="text" name="salaryR" id="salaryR" oninput="calculatorFee()" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-12 col-sm-6 col-md-6 row">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">Chiết khấu (%): </label>
                                <div class="col-12 col-sm-8 col-lg-4">
                                    <input type="text" name="fee" class="form-control" value="5">
                                </div>
                            </div>
                            <div class="form-group col-12 col-sm-6 col-md-6">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">Phí chuyển: </label>
                                <div class="col-12 col-sm-8 col-lg-4">
                                    <input type="text" name="amount" id="amount" class="form-control">
                                </div>
                            </div>
                            <div class="form-group col-12 col-sm-12 col-md-12">
                                <div class="col col-sm-12 col-lg-12">
                                    <button class="btn btn-space btn-primary btn-lg" type="submit" id="btnSubmit">Xác nhận</button>
                                    <%--                                    <button class="btn btn-space btn-secondary btn-lg">Cancel</button>--%>
                                </div>
                            </div>
                            <div id="alert" class="form-group col-12 col-sm-6 col-md-4">
                                <c:if test='${requestScope["mess-add"] != null}'>
                                    <span class="message" style="color: blue;" ><i class="fa fa-check-circle"></i>&nbsp;${requestScope["mess-add"]}</span>
                                </c:if>
                                <c:if test='${requestScope["message_delete"] != null}'>
                                    <span class="message alert" style="color: red;"><i class="fa fa-exclamation-circle"></i>&nbsp;${requestScope["message_delete"]}</span>
                                </c:if>
                                <c:if test='${requestScope["mess-alert"] != null}'>
                                    <span class="message " style="color: #fa983a;"><i class="fa fa-exclamation-triangle"></i>&nbsp;${requestScope["mess-alert"]}</span>
                                </c:if>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <%---------- Right sidebar ---------%>
        <%@ include file="/layout/be-right-sidebar.jsp"%>

    </div>
    <script src="../js/script-transfer.js"></script>
</body>
</html>
