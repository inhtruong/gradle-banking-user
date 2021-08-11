<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/27/2021
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit User</title>
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
                                Chỉnh sửa khách hàng ID: <c:out value='${user.id}' />
                                <p>
                                    <a href="user">Back to home page</a>
                                </p>
                            </div>
                            <form method="post" class="row" id="formValidate">
                                <c:if test="${user != null}">
                                    <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
                                </c:if>

                                <div class="form-group col-12 col-sm-12 col-md-4 row">
                                    <label class="col-12 col-sm-3 col-form-label text-sm-right">Họ và tên: </label>
                                    <div class="col-12 col-sm-8 col-lg-6">
                                        <input type="text" name="name" class="form-control" id="name" value="<c:out value='${user.name}' />">
                                    </div>
                                </div>
                                <div class="form-group col-12 col-sm-12 col-md-4 row">
                                    <label class="col-12 col-sm-3 col-form-label text-sm-right">Quốc tịch: </label>
                                    <div class="col-12 col-sm-8 col-lg-6">
                                        <input type="text" name="country" class="form-control" id="country" value="<c:out value='${user.country}' />">
                                    </div>

                                </div>
                                <div class="form-group col-12 col-sm-12 col-md-4 row">
                                    <label class="col-12 col-sm-3 col-form-label text-sm-right">Email: </label>
                                    <div class="col-12 col-sm-8 col-lg-6">
                                        <input type="email" name="email" class="form-control" id="email" value="<c:out value='${user.email}' />">
                                    </div>
                                </div>
                                <div class="form-group col-12 col-sm-12 col-md-6 row">
                                    <label class="col-12 col-sm-3 col-form-label text-sm-right">Số dư tài khoản: </label>
                                    <div class="input-group-prepend col-12 col-sm-8 col-lg-6">
                                        <div class="input-group-text">$</div>
                                        <input type="number" name="balance" class="form-control" id="balance" disabled value="<c:out value='${user.balance}' />">
                                    </div>
                                </div>
                                <div class="form-group col-12 col-sm-12 col-md-3">
                                    <div class="col col-sm-12 col-lg-12">
                                        <button class="btn btn-space btn-primary btn-lg" type="submit" id="btnSubmit">Xác nhận</button>
                                        <%--                                    <button class="btn btn-space btn-secondary btn-lg">Cancel</button>--%>
                                    </div>
                                </div>
                                <div id="alert" class="form-group col-12 col-sm-6 col-md-4">
                                    <c:if test='${requestScope["success"] != null}'>
                                        <span class="message" style="color: blue;" ><i class="fa fa-check-circle"></i>&nbsp;${requestScope["success"]}</span>
                                    </c:if>
                                    <c:if test='${requestScope["message_delete"] != null}'>
                                        <span class="message alert" style="color: red;"><i class="fa fa-exclamation-circle"></i>&nbsp;${requestScope["message_delete"]}</span>
                                    </c:if>
                                    <c:if test='${requestScope["warning"] != null}'>
                                        <span class="message " style="color: #fa983a;"><i class="fa fa-exclamation-triangle"></i>&nbsp;${requestScope["warning"]}</span>
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

    <%@ include file="/layout/script.jsp"%>

</body>
</html>
