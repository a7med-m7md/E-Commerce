<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Ahmed Mohamed
  Date: 3/23/2023
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
  <style>

    body {
      background-color: #b4b2b2; /* light gray background */
    }

    .card {
      background-color: #FFFFFF; /* white background */
      box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* shadow */
      margin: 0 auto; /* center the card */
      max-width: 800px; /* set the maximum width */
    }
  </style>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<body>

  <c:if test="${not empty successMSG}">
    <div class="alert alert-success" role="alert">
      Success! Your profile has been updated!
    </div>
  </c:if>
<c:set var="userInfoo" value="${sessionScope.userInfo}" />
<div class="col-lg">
  <div style="background-color: #f2f2f2; padding: 10px;">
    <a href="home" style="color: #5e0505; text-decoration: none; font-weight: bold;">Home</a>
    <span style="font-weight: bold;">Profile Page</span>
  </div>
  <br>
  <br>


    <div class="card card-small mb-4">
      <div class="card-header border-bottom">
        <h6 class="m-0">Account Details</h6>
      </div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item p-3">
          <div class="row">
            <div class="col">
              <form  action="profile" method="post">
                <input style="visibility: hidden" name="uuid" value= <c:out value="${userInfoo.uuid}" /> />
                <div class="form-row">
                  <div class="form-group col-md-6">
                    <label for="feFirstName">First Name</label>
                    <input type="text" class="form-control" id="feFirstName" placeholder="First Name" name="firstname"
                           value= <c:out value="${userInfoo.fName}" /> >
                  </div>
                  <div class="form-group col-md-6">
                    <label for="feLastName">Last Name</label>
                    <input type="text" class="form-control" id="feLastName" placeholder="Last Name" name="lastname"
                           value= <c:out value="${userInfoo.lName}"/> >
                  </div>
                </div>
                <div class="form-row">
                  <div class="form-group col-md-6">
                    <label for="feEmailAddress">Email</label>
                    <input type="email" class="form-control" id="feEmailAddress" placeholder="Email" name="email"
                           value= <c:out value="${userInfoo.eMail}" /> >
                  </div>
                  <div class="form-group col-md-6">
                    <label for="fePassword">Password</label>
                    <input type="password" class="form-control" id="fePassword" placeholder="Password" name="password"
                          value= <c:out value="${userInfoo.password}" /> >
                  </div>
                </div>
                <div class="form-group">
                  <label for="feInputAddress">Address</label>
                  <input type="text" class="form-control" id="feInputAddress" placeholder="1234 Main St" name="address"
                          value=  <c:out value="${userInfoo.address}" /> >
                </div>
                <div class="form-row">
                  <div class="form-group col-md-4">
                    <label for="feInputCity">Job</label>
                    <input type="text" class="form-control" id="feInputCity" name="job" value= <c:out value="${userInfoo.job}" /> >
                  </div>
                  <div class="form-group col-md-4">
                    <label for="genderOption">Gender</label>
                    <select id="genderOption" class="form-control" name="gender">
                      <option>Male</option>
                      <option>Female</option>
                    </select>
                    <p id="gender" style="visibility: hidden"><c:out value="${userInfoo.gender}" /></p>
                  </div>
                  <div class="form-group col-md-4">
                    <label for="datePicker">Birthdate</label>
                    <input type="date" name="birthday" value= <c:out value="${userInfoo.birthDay}" /> class="form-control" id="datePicker">
                  </div>
                    <c:set var="userRole" value="${sessionScope.userInfo.role}" />
                    <c:if test="${userRole == 'USER'}">
                  <div class="input-group mb-3">
                    <label style="padding: 5px;">Credit Limit</label>
                    <div class="input-group-prepend">
                      <span class="input-group-text">EGP</span>
                    </div>
                    <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)" name="creditlimit"
                           value= <c:out value="${userInfoo.creditLimit}" />>
                    <div class="input-group-append">
                      <span class="input-group-text">.00</span>
                    </div>
                  </div>
                    </c:if>

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
                <div class="align-self-end ml-auto">
                  <button type="submit" class="btn btn-outline-info">Update</button>
                </div>
              </form>
            </div>
          </div>
        </li>
      </ul>
    </div>
</div>


<script>
  <%--const genderValue = "${userInfoo.gender}";--%>
  let genderType = document.getElementById("gender").innerHTML;
  if(genderType == "MALE")
    document.getElementById("genderOption").value = "Male"
  else
    document.getElementById("genderOption").value = "Female"
  console.log(document.getElementById("gender").innerHTML)
</script>

</body>
</html>
