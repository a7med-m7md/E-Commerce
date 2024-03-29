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
						<li class="active"><a href="store">All products</a></li>
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
							<li class="active">All Products</li>
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
					<!-- ASIDE -->
					<div id="aside" class="col-md-3">
						
						<!-- aside Widget -->
						<div class="aside">
							<h3 class="aside-title">Brand</h3>
							<div class="checkbox-filter">

							</div>
						</div>
						<!-- /aside Widget -->

						<!-- aside Widget -->
						<div class="aside">
							<h3 class="aside-title">Price</h3>
							<div class="price-filter">
								<div id="price-slider"></div>
								<div class="input-number price-min">
									<input id="price-min" type="number">
									<span class="qty-up">+</span>
									<span class="qty-down">-</span>
								</div>
								<span>-</span>
								<div class="input-number price-max">
									<input id="price-max" type="number">
									<span class="qty-up">+</span>
									<span class="qty-down">-</span>
								</div>
							</div>
						</div>
						<!-- /aside Widget -->
						<div class="aside">
							<button class="filter-btn"><i class="fa fa-shopping-cart"></i> Filter</button>
						</div>
<%--						<!-- aside Widget -->--%>
<%--						<div class="aside">--%>
<%--							<h3 class="aside-title">Top selling</h3>--%>
<%--							<div class="product-widget">--%>
<%--								<div class="product-img">--%>
<%--									<img src="./img/product01.png" alt="">--%>
<%--								</div>--%>
<%--								<div class="product-body">--%>
<%--									<p class="product-category">Category</p>--%>
<%--									<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--									<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--								</div>--%>
<%--							</div>--%>

<%--							<div class="product-widget">--%>
<%--								<div class="product-img">--%>
<%--									<img src="./img/product02.png" alt="">--%>
<%--								</div>--%>
<%--								<div class="product-body">--%>
<%--									<p class="product-category">Category</p>--%>
<%--									<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--									<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--								</div>--%>
<%--							</div>--%>

<%--							<div class="product-widget">--%>
<%--								<div class="product-img">--%>
<%--									<img src="./img/product03.png" alt="">--%>
<%--								</div>--%>
<%--								<div class="product-body">--%>
<%--									<p class="product-category">Category</p>--%>
<%--									<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--									<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--						</div>--%>
<%--						<!-- /aside Widget -->--%>
					</div>
					<!-- /ASIDE -->

					<!-- STORE -->
					<div id="store" class="col-md-9">
						<!-- store top filter -->
						<div class="store-filter clearfix">
							<div class="store-sort">
								<label>
									Sort By:
									<select id="sortBy" class="input-select">
										<option value="PRICE">Price</option>
										<option value="RATE">Rate</option>
									</select>
								</label>

								<label>
									Show:
									<select id="count" class="input-select">
										<option value="10">10</option>
										<option value="20">20</option>
									</select>
								</label>
							</div>
<%--							<ul class="store-grid">--%>
<%--								<li class="active"><i class="fa fa-th"></i></li>--%>
<%--								<li><a href="#"><i class="fa fa-th-list"></i></a></li>--%>
<%--							</ul>--%>
						</div>
						<!-- /store top filter -->

						<!-- store products -->
						<div class="row" id="products">
<%--							<!-- product -->--%>
<%--							<div class="col-md-4 col-xs-6">--%>
<%--								<div class="product">--%>
<%--									<div class="product-img">--%>
<%--										<img src="./img/product01.png" alt="">--%>
<%--										<div class="product-label">--%>
<%--											<span class="sale">-30%</span>--%>
<%--											<span class="new">NEW</span>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="product-body">--%>
<%--										<p class="product-category">Category</p>--%>
<%--										<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--										<div class="product-rating">--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--										</div>--%>
<%--										<div class="product-btns">--%>
<%--											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>--%>
<%--											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>--%>
<%--											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="add-to-cart">--%>
<%--										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<!-- /product -->--%>

<%--							<!-- product -->--%>
<%--							<div class="col-md-4 col-xs-6">--%>
<%--								<div class="product">--%>
<%--									<div class="product-img">--%>
<%--										<img src="./img/product02.png" alt="">--%>
<%--										<div class="product-label">--%>
<%--											<span class="new">NEW</span>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="product-body">--%>
<%--										<p class="product-category">Category</p>--%>
<%--										<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--										<div class="product-rating">--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star-o"></i>--%>
<%--										</div>--%>
<%--										<div class="product-btns">--%>
<%--											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>--%>
<%--											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>--%>
<%--											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="add-to-cart">--%>
<%--										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<!-- /product -->--%>

<%--							<div class="clearfix visible-sm visible-xs"></div>--%>

<%--							<!-- product -->--%>
<%--							<div class="col-md-4 col-xs-6">--%>
<%--								<div class="product">--%>
<%--									<div class="product-img">--%>
<%--										<img src="./img/product03.png" alt="">--%>
<%--									</div>--%>
<%--									<div class="product-body">--%>
<%--										<p class="product-category">Category</p>--%>
<%--										<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--										<div class="product-rating">--%>
<%--										</div>--%>
<%--										<div class="product-btns">--%>
<%--											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>--%>
<%--											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>--%>
<%--											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="add-to-cart">--%>
<%--										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<!-- /product -->--%>

<%--							<div class="clearfix visible-lg visible-md"></div>--%>

<%--							<!-- product -->--%>
<%--							<div class="col-md-4 col-xs-6">--%>
<%--								<div class="product">--%>
<%--									<div class="product-img">--%>
<%--										<img src="./img/product04.png" alt="">--%>
<%--									</div>--%>
<%--									<div class="product-body">--%>
<%--										<p class="product-category">Category</p>--%>
<%--										<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--										<div class="product-rating">--%>
<%--										</div>--%>
<%--										<div class="product-btns">--%>
<%--											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>--%>
<%--											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>--%>
<%--											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="add-to-cart">--%>
<%--										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<!-- /product -->--%>

<%--							<div class="clearfix visible-sm visible-xs"></div>--%>

<%--							<!-- product -->--%>
<%--							<div class="col-md-4 col-xs-6">--%>
<%--								<div class="product">--%>
<%--									<div class="product-img">--%>
<%--										<img src="./img/product05.png" alt="">--%>
<%--									</div>--%>
<%--									<div class="product-body">--%>
<%--										<p class="product-category">Category</p>--%>
<%--										<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--										<div class="product-rating">--%>
<%--										</div>--%>
<%--										<div class="product-btns">--%>
<%--											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>--%>
<%--											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>--%>
<%--											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="add-to-cart">--%>
<%--										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<!-- /product -->--%>

<%--							<!-- product -->--%>
<%--							<div class="col-md-4 col-xs-6">--%>
<%--								<div class="product">--%>
<%--									<div class="product-img">--%>
<%--										<img src="./img/product06.png" alt="">--%>
<%--									</div>--%>
<%--									<div class="product-body">--%>
<%--										<p class="product-category">Category</p>--%>
<%--										<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--										<div class="product-rating">--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star-o"></i>--%>
<%--										</div>--%>
<%--										<div class="product-btns">--%>
<%--											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>--%>
<%--											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>--%>
<%--											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="add-to-cart">--%>
<%--										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<!-- /product -->--%>

<%--							<div class="clearfix visible-lg visible-md visible-sm visible-xs"></div>--%>

<%--							<!-- product -->--%>
<%--							<div class="col-md-4 col-xs-6">--%>
<%--								<div class="product">--%>
<%--									<div class="product-img">--%>
<%--										<img src="./img/product07.png" alt="">--%>
<%--									</div>--%>
<%--									<div class="product-body">--%>
<%--										<p class="product-category">Category</p>--%>
<%--										<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--										<div class="product-rating">--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--											<i class="fa fa-star"></i>--%>
<%--										</div>--%>
<%--										<div class="product-btns">--%>
<%--											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>--%>
<%--											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>--%>
<%--											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="add-to-cart">--%>
<%--										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<!-- /product -->--%>

<%--							<!-- product -->--%>
<%--							<div class="col-md-4 col-xs-6">--%>
<%--								<div class="product">--%>
<%--									<div class="product-img">--%>
<%--										<img src="./img/product08.png" alt="">--%>
<%--									</div>--%>
<%--									<div class="product-body">--%>
<%--										<p class="product-category">Category</p>--%>
<%--										<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--										<div class="product-rating">--%>
<%--										</div>--%>
<%--										<div class="product-btns">--%>
<%--											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>--%>
<%--											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>--%>
<%--											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="add-to-cart">--%>
<%--										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<!-- /product -->--%>

<%--							<div class="clearfix visible-sm visible-xs"></div>--%>

<%--							<!-- product -->--%>
<%--							<div class="col-md-4 col-xs-6">--%>
<%--								<div class="product">--%>
<%--									<div class="product-img">--%>
<%--										<img src="./img/product09.png" alt="">--%>
<%--									</div>--%>
<%--									<div class="product-body">--%>
<%--										<p class="product-category">Category</p>--%>
<%--										<h3 class="product-name"><a href="#">product name goes here</a></h3>--%>
<%--										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>--%>
<%--										<div class="product-rating">--%>
<%--										</div>--%>
<%--										<div class="product-btns">--%>
<%--											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>--%>
<%--											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>--%>
<%--											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="add-to-cart">--%>
<%--										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</div>--%>
<%--							<!-- /product -->--%>
						</div>
						<!-- /store products -->

						<!-- store bottom filter -->
						<div class="store-filter clearfix">
							<span class="store-qty">Showing 20-100 products</span>
							<ul class="store-pagination">
								<li class="active"><a>1</a></li>
							</ul>
						</div>
						<!-- /store bottom filter -->
					</div>
					<!-- /STORE -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

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
		<script type="module" src="js/store.js"></script>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>
		<script type="module" src="js/cart-widget.js"></script>
	</body>
</html>