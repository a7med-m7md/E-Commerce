<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.List" %>
<%@ page import="com.laphup.persistence.entities.User" %>

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
                <h4 class="text-white">Dashboard Admin Page - Users</h4>
                <p class="text-muted mb-0"><a href="home" style="border: none; ">Home</a></p>
            </div>
        </div>
    </div>
</header>


<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
            <th>UID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Address</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr data-toggle="modal" data-target='#userModal<c:out value="${user.uuid}" />'>
                <td><c:out value="${user.uuid}" /></td>
                <td><c:out value="${user.FName}" /></td>
                <td><c:out value="${user.gender}" /></td>
                <td><c:out value="${user.address}" /></td>
            </tr>

            <!-- Modal -->
            <div class="modal fade" id='userModal<c:out value="${user.uuid}"/>' tabindex="-1" role="dialog" aria-labelledby="userModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="userModalLabel">User Information</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p><strong>UID:</strong><c:out value="${user.uuid}" /></p>
                            <p><strong>Name:</strong><c:out value="${user.FName}" /></p>
                            <p><strong>Gender:</strong><c:out value="${user.gender}" /></p>
                            <p><strong>Address:</strong><c:out value="${user.address}" /> </p>
                            <p><strong>Email:</strong><c:out value="${user.EMail}" /></p>
                            <p><strong>Job:</strong><c:out value="${user.job}" /></p>
                            <p><strong>Credit Limit:</strong><c:out value="${user.creditLimit}" /></p>
                            <p><strong>Birthdate:</strong><c:out value="${user.birthDay}" /></p>
<%--                            <p><strong>Number of orders:</strong> <c:out value="${(user.order)}" /></p>--%>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary closeBTN" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        </tbody>

    </table>
</div>
<script src="js/view-users-table-app.js"></script>
</body>
</html>