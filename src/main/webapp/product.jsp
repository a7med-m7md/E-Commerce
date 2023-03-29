<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="u" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Laphup - Ecommerce</title>

 		<!-- Google font -->
 		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

 		<!-- Bootstrap -->
 		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

 		<!-- Slick -->
 		<link type="text/css" rel="stylesheet" href="css/slick.css"/>
 		<link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

 		<!-- nouislider -->
 		<link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

 		<!-- Font Awesome Icon -->
 		<link rel="stylesheet" href="css/font-awesome.min.css">

 		<!-- Custom stlylesheet -->
 		<link type="text/css" rel="stylesheet" href="css/style.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    </head>
	<body>

	<%@ include file="WEB-INF/header.jsp" %>

		<!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li><a href="home">Home</a></li>
						<li><a href="store">All products</a></li>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->

		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<ul class="breadcrumb-tree">
							<li><a href="home">Home</a></li>
							<li><a href="store">All Products</a></li>
							<li class="active">My Product</li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div id="myProduct">
						<c:set var="userRole" value="${sessionScope.userInfo.role}" />
						<c:if test="${userRole == 'ADMIN'}">
							<button class="btn btn-warning"><a id="updateBTN" href="updateProduct?uuid=${laptop.uuid}">Update product</a></button>
							<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmDeleteModal">Delete Item</button>

							<!-- Confirm Delete Modal -->
							<div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Deletion</h5>
											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<div class="modal-body">
											Are you sure you want to delete this item?
										</div>
<%--										<c:set var="currentproduct" value="${param.uuid}"/>--%>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
											<button type="button" class="btn btn-danger" id="confirmDeleteButton"><a id="confirmDeleteButtonAnch">Delete</a></button>
										</div>
									</div>
								</div>
							</div>

						</c:if>
					</div>
					<!-- Product tab -->
					<div class="col-md-12">
						<div id="product-tab">
							<!-- product tab nav -->
							<ul class="tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
<%--								<li><a data-toggle="tab" href="#tab2">Details</a></li>--%>
<%--								<li><a data-toggle="tab" href="#tab3">Reviews (3)</a></li>--%>
							</ul>
							<!-- /product tab nav -->

							<!-- product tab content -->
							<div class="tab-content">
								<!-- tab1  -->
								<div id="tab1" class="tab-pane fade in active">
									<div class="row">
										<div class="col-md-12" id="desc">
										</div>
									</div>
								</div>
								<!-- /tab1  -->
							</div>
							<!-- /product tab content  -->
						</div>
					</div>
					<!-- /product tab -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- Section -->

		<!-- /Section -->

<%--		<!-- NEWSLETTER -->--%>
<%--		<div id="newsletter" class="section">--%>
<%--			<!-- container -->--%>
<%--			<div class="container">--%>
<%--				<!-- row -->--%>
<%--				<div class="row">--%>
<%--					<div class="col-md-12">--%>
<%--						<div class="newsletter">--%>
<%--							<p>Sign Up for the <strong>NEWSLETTER</strong></p>--%>
<%--							<form>--%>
<%--								<input class="input" type="email" placeholder="Enter Your Email">--%>
<%--								<button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>--%>
<%--							</form>--%>
<%--&lt;%&ndash;							<ul class="newsletter-follow">&ndash;%&gt;--%>
<%--&lt;%&ndash;								<li>&ndash;%&gt;--%>
<%--&lt;%&ndash;									<a href="#"><i class="fa fa-facebook"></i></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;								</li>&ndash;%&gt;--%>
<%--&lt;%&ndash;								<li>&ndash;%&gt;--%>
<%--&lt;%&ndash;									<a href="#"><i class="fa fa-twitter"></i></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;								</li>&ndash;%&gt;--%>
<%--&lt;%&ndash;								<li>&ndash;%&gt;--%>
<%--&lt;%&ndash;									<a href="#"><i class="fa fa-instagram"></i></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;								</li>&ndash;%&gt;--%>
<%--&lt;%&ndash;								<li>&ndash;%&gt;--%>
<%--&lt;%&ndash;									<a href="#"><i class="fa fa-pinterest"></i></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;								</li>&ndash;%&gt;--%>
<%--&lt;%&ndash;							</ul>&ndash;%&gt;--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--				<!-- /row -->--%>
<%--			</div>--%>
<%--			<!-- /container -->--%>
<%--		</div>--%>
<%--		<!-- /NEWSLETTER -->--%>

		<!-- FOOTER -->
		<footer id="footer">
			<%@ include file="footer.jsp" %>
		</footer>
		<!-- /FOOTER -->

		<!-- jQuery Plugins -->
		<script type="module" src="js/product.js"></script>
		<script>
			const urlParams = new URLSearchParams(window.location.search);
			const id = urlParams.get('uuidProduct');
			document.getElementById("confirmDeleteButtonAnch").href = `removeProduct?uuid=${id}`
		</script>

	</body>
</html>
