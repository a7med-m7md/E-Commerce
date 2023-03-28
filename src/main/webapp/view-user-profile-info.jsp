<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Bootstrap CSS -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"></link>

    <!-- Bootstrap JavaScript (with Popper.js) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" id="main-stylesheet" data-version="1.1.0" href="styles/shards-dashboards.1.1.0.min.css">
    <link rel="stylesheet" href="styles/extras.1.1.0.min.css">
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/quill/1.3.6/quill.snow.css">
</head>
<body>
<%@ include file="WEB-INF/header.jsp" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Admin Dashboard</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Orders</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Settings</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Logout</a>
            </li>
        </ul>
    </div>
</nav>
<div class="card">
    <div class="card-body">
        <h5 class="card-title">Orders</h5>
        <h6 class="card-subtitle mb-2 text-muted">Number of orders</h6>
        <p class="card-text">Total orders: 50</p>
        <a href="#" class="card-link">View all orders</a>
    </div>
</div>


<div class="col-lg-8">
    <div class="card card-small mb-4">
        <div class="card-header border-bottom">
            <h6 class="m-0">Account Details</h6>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item p-3">
                <div class="row">
                    <div class="col">
                        <form>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="feFirstName">First Name</label>
                                    <input type="text" class="form-control" id="feFirstName" placeholder="First Name"
                                           value="Sierra">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="feLastName">Last Name</label>
                                    <input type="text" class="form-control" id="feLastName" placeholder="Last Name"
                                           value="Brooks">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="feEmailAddress">Email</label>
                                    <input type="email" class="form-control" id="feEmailAddress" placeholder="Email"
                                           value="sierra@example.com">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="fePassword">Password</label>
                                    <input type="password" class="form-control" id="fePassword" placeholder="Password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="feInputAddress">Address</label>
                                <input type="text" class="form-control" id="feInputAddress" placeholder="1234 Main St">
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="feInputCity">Job</label>
                                    <input type="text" class="form-control" id="feInputCity">
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="feInputState">Gender</label>
                                    <select id="feInputState" class="form-control">
                                        <option selected>Male</option>
                                        <option>Female</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-4">
                                    <label for="datePicker">Birthdate</label>
                                    <input type="date" value="1999-02-20" class="form-control" id="datePicker">
                                </div>
                                <div class="input-group mb-3">
                                    <label style="padding: 5px;">Credit Limit</label>
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">EGP</span>
                                    </div>
                                    <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)"
                                           value="1000">
                                    <div class="input-group-append">
                                        <span class="input-group-text">.00</span>
                                    </div>
                                </div>
                                <!-- <div class="form-group col-md-2">
                                  <label for="inputZip">Credit Limit</label>
                                  <input type="text" class="form-control" id="inputZip">
                                </div> -->
                            </div>
                            <!-- <div class="form-row">
                              <div class="form-group col-md-12">
                                <label for="feDescription">Description</label>
                                <textarea class="form-control" name="feDescription"
                                  rows="5">Lorem ipsum dolor sit amet consectetur adipisicing elit. Odio eaque, quidem, commodi soluta qui quae minima obcaecati quod dolorum sint alias, possimus illum assumenda eligendi cumque?</textarea>
                              </div>
                            </div> -->
                            <button type="submit" class="btn btn-accent">Update Account</button>
                        </form>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
</div>
<!-- End Default Light Table -->
</div>
</body>
</html>