<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>Dashboard Admin Page - Add Product</title>
	<meta name="description"
		  content="A high-quality &amp; free Bootstrap admin dashboard template pack that comes with lots of templates and components.">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
		  integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" id="main-stylesheet" data-version="1.1.0" href="styles/shards-dashboards.1.1.0.min.css">
	<link rel="stylesheet" href="styles/extras.1.1.0.min.css">
	<link rel="stylesheet" href="add-laptop.css">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>

</head>
<body>
<!-- HEADER -->
<header class="header bg-dark d-flex align-items-center">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h4 class="text-white">Dashboard Admin Page - Add Product</h4>
				<p class="text-muted mb-0"><a href="home" style="border: none; ">Home</a></p>
			</div>
		</div>
	</div>
</header>
<!-- /HEADER -->
<!-- SECTION -->
<section class="section">
	<div class="container">
		<div class="row">
			<div class="col-lg-9 col-md-12 mx-auto">
				<!-- Add New Post Form -->
				<div class='container-fluid card shadow-sm p-3 mb-5 bg-white rounded'>
					<div class="card-header border-bottom">
						<h6 class="m-0">Add Product</h6>
					</div>
					<div class='card-body p-3'>
						<form method="post" action="addLaptop" enctype="multipart/form-data" onsubmit="showSpinner();return validateForm()">
							<div class="form-group">
								<label for="laptop-name">Laptop Name</label>
								<input type="text" class="form-control" id="laptop-name" name="laptop-name" placeholder="Enter Laptop Name" required>
							</div>
							<div class="form-group">
								<label for="laptop-price">Laptop Price</label>
								<input type="number" min="1" class="form-control" id="laptop-price" name="laptop-price" placeholder="Enter Laptop Price">
							</div>
							<div class="form-group">
								<label for="laptop-description">Laptop Description</label>
								<textarea class="form-control" id="laptop-description" name="laptop-description" rows="5" style="resize: none"></textarea>
							</div>
							<div class="form-group">
								<label for="laptop-quantity">Laptop Quantity</label>
								<input type="number" min="1" class="form-control" id="laptop-quantity" placeholder="Enter Laptop Quantity" name="laptop-quantity">
							</div>
							<div class="form-group">
								<label for="laptop-photo">Laptop Photo</label>
								<input type="file" class="form-control-file" id="laptop-photo" name="product-image">
							</div>
							<div class="form-group">
								<label>Additional Photos</label>
								<div id="additional-photos-container">
<%--									<input type="file" class="form-control-file" id="additional-photos" name="additional-photos[]" multiple>--%>
								</div>
								<button type="button" class="btn btn-secondary btn-sm mt-2" id="add-photo-button"><i class="fas fa-plus"></i> Add Photo</button>
							</div>
							<input id="hidden-category" hidden="hidden" name="category" value="">
							<button class="btn btn-primary" id="saveBtn"  type="submit" style="cursor: pointer; padding: 5px 10px">
								Save
							</button>
<%--							<input type="submit" class="btn btn-primary" value="Save">--%>
						</form>
					</div>
				</div>
				<!-- /Add New Post Form -->
			</div>
				<!-- Post Overview -->
			<div class="col-lg-3 col-md-12">
				<div class="card card-small mb-3">
					<div class="card-header border-bottom">
						<h6 class="m-0">Categories</h6>
					</div>
					<div class="card-body p-0">
						<ul id="list-category-val" class="list-group list-group-flush">
							<li class="list-group-item">
								<form onsubmit="addNewCategory(event)">
									<div class="input-group">
										<input type="text" class="form-control" placeholder="New Category" name="category-name" id="category-name">
										<div class="input-group-append">
											<button class="btn btn-sm btn-outline-success" type="submit">
												<i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
								</form>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="errorModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="errorModalLabel">Error</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<p>The Category name already exist</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"  id="close" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

		<%--				<div class="card card-small mb-3">--%>
<%--					<div class="card-header border-bottom">--%>
<%--						<h6 class="m-0">Actions</h6>--%>
<%--					</div>--%>
<%--					<div class='card-body p-0'>--%>
<%--						<ul class="list-group list-group-flush">--%>
<%--							<li class="list-group-item d-flex px-3">--%>
<%--								<button class="btn btn-sm btn-outline-accent">--%>
<%--									<i class="material-icons">save</i> Save Draft</button>--%>
<%--								<button class="btn btn-sm btn-accent ml-auto">--%>
<%--									<i class="material-icons">file_copy</i> Publish</button>--%>
<%--							</li>--%>
<%--							<li class="list-group-item d-flex px-3">--%>
<%--								<div class="form-group mb-0">--%>
<%--									<div class="custom-control custom-checkbox">--%>
<%--										<input type="checkbox" class="custom-control-input" id="notify-users">--%>
<%--										<label class="custom-control-label" for="notify-users">Notify Users</label>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</li>--%>
<%--							<li class="list-group-item d-flex px-3">--%>
<%--								<button class="btn btn-sm btn-outline-danger">--%>
<%--									<i class="material-icons">delete</i> Delete</button>--%>
<%--								<button class="btn btn-sm btn-outline-primary ml-auto">--%>
<%--									<i class="material-icons">visibility</i> Preview</button>--%>
<%--							</li>--%>
<%--						</ul>--%>
<%--					</div>--%>
<%--				</div>--%>
				<!-- / Post Overview -->
			</div>
		</div>

	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
			integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIeXg2mwMwC5q0skrqVgUVxMfs2nFOTg8WYgDoc+7p6U"
			crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
	<script src="scripts/shards.min.js"></script>
	<script src="scripts/app/app-blog-new-post.1.1.0.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/quill/1.3.6/quill.min.js"></script>

	<script src="add-laptop-category.js"></script>
	<script src="js/addlaptop-validation.js"></script>
	<script>
		$('#add-photo-button').click(function() {
			// create a new "input" element of type "file"
			const inputFile = $('<input>').attr({
				'type': 'file',
				'class': 'form-control-file',
				'id': 'additional-photos',
				'name': 'additional-photos[]',
				'multiple': ''
			});

			// append the new "input" element to the DOM
			$('.form-group:last-of-type').append(inputFile);
		});
	</script>

</section>
</body>
</html>