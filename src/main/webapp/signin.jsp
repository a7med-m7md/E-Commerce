<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <script src="https://code.jquery.com/jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
    <title>Sign In</title>

    <style>
        .input_style {
            width:70%;
            margin-left: 10%;

        }
        .buttonsinin {
            width:50%;
            margin-left: -1rem;
        }
    </style>

    <style>
        .cascading-right {
            margin-right: -50px;
        }

        @media (max-width: 991.98px) {
            .cascading-right {
                margin-right: 0;
            }
        }
         .imgLogo {
                    margin: 12%;
                    }
    </style>

</head>
<!-- Section: Design Block -->

<body>


<c:if test="${not empty param.invalidCredentials}">
    <div class="alert alert-danger" role="alert">
        Invalid email or password. Please try again.
    </div>
</c:if>
<section class="text-center text-lg-start">



    <!-- Jumbotron -->
    <div class="container py-4">
        <div class="row g-0 align-items-center">
            <div class="col-lg-6 mb-5 mb-lg-0">
                <div class="card cascading-right" style="
            background: hsla(0, 0%, 100%, 0.55);
            backdrop-filter: blur(30px);
            ">
                    <div class="card-body p-5 shadow-5 text-center">
                        <h2 class="fw-bold mb-5">Sign In now </h2>
                        <form method="post" action="signin">
                            <!-- Email input -->
                            <div class="form-outline mb-4 input_style">
                                <input type="email" id="emailL" name="emailL" required class="form-control 3"/>
                                <label class="form-label" for="emailL">Email address</label>
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-4 input_style">
                                <input type="password" id="passwordL" minlength="6" maxlength="20" required name="passwordL" class="form-control"/>
                                <label class="form-label" for="passwordL">Password</label>
                            </div>

                            <!-- Checkbox -->
                            <div class="form-check d-flex justify-content-center mb-4">
                                <input class="form-check-input me-2" type="checkbox" name="remember-me-m" value="" id="form2Example33"
                                       checked/>
                                <label class="form-check-label" for="form2Example33">
                                    Remember Me
                                </label>
                            </div>

                            <!-- Submit button -->
                            <button id="loginbtn" type="submit" onclick="validateEmail()" class="btn btn-primary btn-block mb-4 buttonsinin">
                                Sign In
                            </button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-lg-6 mb-5 mb-lg-0">
               <a href="home"><img  src="img/logo2.png"
                                   class="w-80 rounded-4 shadow-4" alt=""/></a>
            </div>
        </div>
    </div>
    <!-- Jumbotron -->
</section>
<!-- Section: Design Block -->
<script src="js/validation.js"></script>

</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"></script>
</html>