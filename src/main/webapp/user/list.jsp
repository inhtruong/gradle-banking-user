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
    <title>User Management Application</title>
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
                            Thêm người dùng
                        </div>
                        <form method="post" class="row" id="formValidate">
                            <div class="form-group col-12 col-sm-12 col-md-6 row">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">Họ và tên: </label>
                                <div class="col-12 col-sm-8 col-lg-6">
                                    <input type="text" name="name" class="form-control" id="name">
                                </div>
                            </div>
                            <div class="form-group col-12 col-sm-12 col-md-6 row">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">Quốc tịch: </label>
                                <div class="col-12 col-sm-8 col-lg-6">
                                    <input type="text" name="country" class="form-control" id="country">
                                </div>

                            </div>
                            <div class="form-group col-12 col-sm-12 col-md-6 row">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">Email: </label>
                                <div class="col-12 col-sm-8 col-lg-6">
                                    <input type="email" name="email" class="form-control" id="email" placeholder="Enter a valid e-mail">
                                </div>
                            </div>
                            <div class="form-group col-12 col-sm-12 col-md-6 row">
                                <label class="col-12 col-sm-3 col-form-label text-sm-right">Balance: </label>
                                <div class="col-12 col-sm-8 col-lg-6">
                                    <input type="number" name="balance" class="form-control" id="balance">
                                </div>
                            </div>
                            <div class="form-group col-12 col-sm-12 col-md-3">
                                <div class="col col-sm-12 col-lg-12">
                                    <button class="btn btn-space btn-primary btn-lg" type="submit" id="btnSubmit">Xác nhận</button>
<%--                                    <button class="btn btn-space btn-secondary btn-lg">Cancel</button>--%>
                                </div>
                            </div>
<%--                            <div id="alert" class="form-group col-12 col-sm-6 col-md-4">--%>
<%--                                <c:if test='${requestScope["mess-add"] != null}'>--%>
<%--                                    <span class="message" style="color: blue;" ><i class="fa fa-check-circle"></i>&nbsp;${requestScope["mess-add"]}</span>--%>
<%--                                </c:if>--%>
<%--                                <c:if test='${requestScope["message_delete"] != null}'>--%>
<%--                                    <span class="message alert" style="color: red;"><i class="fa fa-exclamation-circle"></i>&nbsp;${requestScope["message_delete"]}</span>--%>
<%--                                </c:if>--%>
<%--                                <c:if test='${requestScope["mess-alert"] != null}'>--%>
<%--                                    <span class="message " style="color: #fa983a;"><i class="fa fa-exclamation-triangle"></i>&nbsp;${requestScope["mess-alert"]}</span>--%>
<%--                                </c:if>--%>
<%--                            </div>--%>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <div class="card card-table">
                        <div class="card-header">
                            <form class="form-inline text-right" method="post" action="/user?action=search">
                                <input class="form-control" name="string-search" type="search" placeholder="Search" aria-label="Search">
                                <button class="btn btn-success btn-space btn-lg" type="submit" style="margin: 10px;">Tìm kiếm</button>
                            </form>
                        </div>
                        <div class="card-body">
                            <table class="table table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Country</th>
                                    <th scope="col">Balance</th>
                                    <th scope="col">Action</th>
                                </tr>
                                </thead>
                                <c:forEach var="user" items="${listUser}">
                                    <tbody>
                                    <tr>
                                        <td scope="row">${user.getId()}</td>
                                        <td>${user.getName()}</td>
                                        <td>${user.getEmail()}</td>
                                        <td>${user.getCountry()}</td>
                                        <td>
                                            <fmt:formatNumber value="${user.getBalance()}" type="currency" currencySymbol="$"></fmt:formatNumber>
                                        </td>
                                        <td>
                                            <a href="/user?action=edit&id=${user.getId()}" class="btn btn-primary" title="Edit"><i class="fas fa-edit"></i></a>
                                            <a href="/user?action=delete&id=${user.getId()}" class="btn btn-danger " title="Delete"><i class="fas fa-trash"></i></a>
                                            <a href="/user?action=transfer&id=${user.getId()}" class="btn btn-secondary" title="Transfer"><i class="fas fa-exchange-alt"></i></a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%---------- Right sidebar ---------%>
        <%@ include file="/layout/be-right-sidebar.jsp"%>

    </div>

<%@ include file="/layout/script.jsp"%>
    <script>
        $(document).ready(function () {
            var $formValidate = $('#formValidate');
            if ($formValidate.length) {
                $('#formValidate').validate({
                    onfocusout: false,
                    onkeyup: false,
                    onclick: false,
                    rules: {
                        name: {
                            require: true,
                            minlength: 3
                        },
                        country: {
                            require: true,
                            maxlength: 15
                        },
                        email: {
                            required: true,
                            email: true
                        }
                    },
                    message: {
                        name: {
                            require: "Vui lòng nhập tên của bạn",
                            minlength: "Vui lòng nhập đầy đủ họ và tên"
                        },
                        country: {
                            require: "Vui lòng nhập quê quán của bạn",
                            maxlength: "Vui lòng nhập chính xác quê quán"
                        },
                        email: {
                            require: "Vui lòng nhập email của bạn",
                            email: "Email phải đúng định dạng sau: abc@domain.tld"
                        }
                    }
                });
            }
        });


    </script>
</body>
</html>
