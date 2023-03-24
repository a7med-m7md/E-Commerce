<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!-- HEADER -->

		<header	>
			<!-- TOP HEADER -->
			<div id="top-header">
				<div class="container">
					<ul class="header-links pull-left">
						<li><a href="#"><i class="fa fa-phone"></i>+20-106-658-6741</a></li>
						<li><a href="#"><i class="fa fa-envelope-o"></i>laphup@gmail.com</a></li>
						<li><a href="#"><i class="fa fa-map-marker"></i>1734 Smart Village</a></li>
					</ul>
					<ul class="header-links pull-right">

						<c:set var="userRole" value="${sessionScope.userInfo.role}" />
<%--						<li><a href="addLaptop"><i class=""><c:out value="${userRole}" /></i></a></li>--%>
						<c:set var="userInfo" value="${sessionScope.userInfo}" />
						<c:if test="${userRole == 'ADMIN'}">
<%--							<li><a href="addLaptop"><i class=""><c:out value="${userRole}" /></i></a></li>--%>
							<li><a href="addLaptop"><i class="">Add Laptop</i></a></li>
							<li><a href="users"><i class="">Users</i></a></li>
						</c:if>
						<c:if test="${userRole == 'USER'}">
						<li><a href="#"><i class="fa fa-dollar"></i> <c:out value="${userInfo.creditLimit}" /> EG</a></li>
						</c:if>
<%--						<c:if test="not emptry userInfo">--%>
<%--							<li><a href="signIn.html"><i class="fa fa-user-o"></i> <c:out value="${userInfo}" /></a></li>--%>
<%--						</c:if>--%>

						<c:if test="${not empty userInfo}">
							<style>
								/*.drop-menu-hov:hover{*/
								/*	color: #D10024;*/
								/*}*/
								/*.drop-menu-hov{*/
								/*	color: #1E1F29*/
								/*}*/
								.header-links li a[class="dropdown-item drop-menu-hov"]{
									color: #1E1F29
								}
							</style>

							<li>
								<div class="dropdown show">
									<i class="fa fa-user-o"></i>
									<a class="btn btn-secondary dropdown-toggle product-name" href="#" role="button"
									   id="dropdownMenuLink" data-toggle="dropdown"
									   aria-haspopup="true" aria-expanded="false"
									   style="padding: 0px; color: white"><c:out value="${userInfo.fName}" /></a>
								<div class="dropdown-menu" aria-labelledby="dropdownMenuLink" style="min-width: 115px;  padding: 5px; ">
									<div><a class="dropdown-item drop-menu-hov" href="profile" style="">Profile</a></div>
									<hr style="visibility: hidden; height: 10px; margin: 0px">
									<a class="dropdown-item drop-menu-hov" href="home" style="">Order History</a><br>
									<hr style="margin: 7.5px">
									<a class="dropdown-item drop-menu-hov" href="logout" style="">Logout</a>
								</div>
								</div>
							</li>
						</c:if>


						<c:if test="${empty userInfo}">
							<li><a href="signin"><i class="fa fa-user-o"></i> Sign in</a></li>
							<li><a href="signup"><i class="fa fa-user-o"></i> Sign up</a></li>
						</c:if>

					</ul>
				</div>
			</div>
			<!-- /TOP HEADER -->

			<!-- MAIN HEADER -->
			<div id="header">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<!-- LOGO -->
						<div class="col-md-3">
							<div class="header-logo">
								<a href="home" class="logo">
									<img src="img/logo.png" width="160px" height="70px" alt="">
								</a>
							</div>
						</div>
						<!-- /LOGO -->

						<!-- SEARCH BAR -->
						<div class="col-md-6">
							<div class="header-search">
								<form>
									<select class="input-select">
										<option value="0">All Categories</option>
										<option value="1">Category 01</option>
										<option value="1">Category 02</option>
									</select>
									<input class="input" placeholder="Search here">
									<button class="search-btn">Search</button>
								</form>
							</div>
						</div>
						<!-- /SEARCH BAR -->

						<!-- ACCOUNT -->
						<div class="col-md-3 clearfix">
							<div class="header-ctn">
								<!-- Wishlist -->
								<div>
									<a href="#">
										<i class="fa fa-heart-o"></i>
										<span>Your Wishlist</span>
										<div class="qty">2</div>
									</a>
								</div>
								<!-- /Wishlist -->

								<!-- Cart -->
								<div class="dropdown">
									<a class="dropdown-toggle" id="Card" data-toggle="dropdown" aria-expanded="true">
										<i class="fa fa-shopping-cart"></i>
										<span>Your Cart</span>
										<div class="qty">3</div>
									</a>
									<div class="cart-dropdown">
										<div class="cart-list">
											<div class="product-widget">
												<div class="product-img">
													<img src="../img/product01.png" alt="">
												</div>
												<div class="product-body">
													<h3 class="product-name"><a href="#">product name goes here</a></h3>
													<h4 class="product-price"><span class="qty">1x</span>$980.00</h4>
												</div>
												<button class="delete"><i class="fa fa-close"></i></button>
											</div>

											<div class="product-widget">
												<div class="product-img">
													<img src="../img/product02.png" alt="">
												</div>
												<div class="product-body">
													<h3 class="product-name"><a href="#">product name goes here</a></h3>
													<h4 class="product-price"><span class="qty">3x</span>$980.00</h4>
												</div>
												<button class="delete"><i class="fa fa-close"></i></button>
											</div>
										</div>
										<div class="cart-summary">
											<small>3 Item(s) selected</small>
											<h5>SUBTOTAL: $2940.00</h5>
										</div>
										<div class="cart-btns">
											<a href="#">View Cart</a>
											<a href="#">Checkout  <i class="fa fa-arrow-circle-right"></i></a>
										</div>
									</div>
								</div>
								<!-- /Cart -->

								<!-- Menu Toogle -->
								<div class="menu-toggle">
									<a href="#">
										<i class="fa fa-bars"></i>
										<span>Menu</span>
									</a>
								</div>
								<!-- /Menu Toogle -->
							</div>
						</div>
						<!-- /ACCOUNT -->
					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</div>
			<!-- /MAIN HEADER -->
		</header>
		<!-- /HEADER -->
