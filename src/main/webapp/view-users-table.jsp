<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"></link>

    <!-- Bootstrap JavaScript (with Popper.js) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        tbody tr:hover {
            cursor: pointer;
        }
    </style>
</head>
<body>
<header class="header bg-dark d-flex align-items-center">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h4 class="text-white">Dashboard Admin Page - Add Product</h4>
                <p class="text-muted mb-0">Dashboard</p>
            </div>
        </div>
    </div>
</header>
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>Gender</th>
            <th>Address</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user" varStatus="loop">
            <tr onclick="goToUserPage(${loop.index})">
                <td>${user.fName}</td>
                <td>Male</td>
                <td>123 Main St</td>
            </tr>
        </c:forEach>

<%--        <tr onclick="goToUserPage(1)">--%>
<%--            <td>Jane Smith</td>--%>
<%--            <td>Female</td>--%>
<%--            <td>456 Park Ave</td>--%>
<%--        </tr>--%>
        <!-- add more rows for additional users -->
        </tbody>
    </table>
</div>
<script src="view-users-table-app.js"></script>
</body>
</html>