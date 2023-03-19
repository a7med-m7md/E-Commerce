<%@ page import="java.util.List" %>
<%@ page import="com.laphup.persistence.entities.User" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
            <th>UID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Address</th>
        </tr>
        </thead>
        <tbody>
        <% for (int i = 0; i < ((List<User>) request.getAttribute("users")).size(); i++) { %>
        <tr data-toggle="modal" data-target="#userModal<%= ((List<User>) request.getAttribute("users")).get(i).getUuid() %>">
            <td><%= ((List<User>) request.getAttribute("users")).get(i).getUuid() %></td>
            <td><%= ((List<User>) request.getAttribute("users")).get(i).getFName() %></td>
            <td><%= ((List<User>) request.getAttribute("users")).get(i).getGender() %></td>
            <td><%= ((List<User>) request.getAttribute("users")).get(i).getAddress() %></td>
        </tr>

        <!-- Modal -->
        <div class="modal fade" id="userModal<%= ((List<User>) request.getAttribute("users")).get(i).getUuid() %>" tabindex="-1" role="dialog" aria-labelledby="userModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="userModalLabel">User Information</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p><strong>UID:</strong> <%= ((List<User>) request.getAttribute("users")).get(i).getUuid() %></p>
                        <p><strong>Name:</strong> <%= ((List<User>) request.getAttribute("users")).get(i).getFName() + " " + ((List<User>) request.getAttribute("users")).get(i).getLName()%></p>
                        <p><strong>Gender:</strong> <%= ((List<User>) request.getAttribute("users")).get(i).getGender() %></p>
                        <p><strong>Address:</strong> <%= ((List<User>) request.getAttribute("users")).get(i).getAddress() %></p>
                        <p><strong>Email:</strong> <%= ((List<User>) request.getAttribute("users")).get(i).getEMail() %></p>
                        <p><strong>Job:</strong> <%= ((List<User>) request.getAttribute("users")).get(i).getJob() %></p>
                        <p><strong>Credit Limit:</strong> <%= ((List<User>) request.getAttribute("users")).get(i).getCreditLimit() %></p>
                        <p><strong>Birthdate:</strong> <%= ((List<User>) request.getAttribute("users")).get(i).getBirthDay() %></p>
                        <p><strong>Number of orders:</strong> <%= ((List<User>) request.getAttribute("users")).get(i).getOrder().size() %></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary closeBTN" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        </tbody>

    </table>
</div>
<script src="view-users-table-app.js"></script>
</body>
</html>