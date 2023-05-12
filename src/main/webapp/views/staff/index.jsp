<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<!-- begin::Head -->
<head>
<meta charset="utf-8" />
<title>${page.title}</title>
<meta name="description" content="Static table examples">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="${path}/views/css/style.bundle.css">
<link rel="stylesheet" href="${path}/views/vendors/vendors.bundle.css">
<link rel="shortcut icon"
	href="https://ap.poly.edu.vn/images/favicon.ico" />

</head>
<!-- end::Head -->

<!-- begin::Body -->
<body
	class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--fixed m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default">

	<!-- begin:: Page -->
	<div class="m-grid m-grid--hor m-grid--root m-page">

		<!-- BEGIN: Header -->

		<jsp:include page="/views/staff/header.jsp"></jsp:include>


		<!-- END: Header -->

		<!-- begin::Body -->
		<div
			class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">

			<!-- BEGIN: Left Aside -->

			<jsp:include page="/views/staff/sildebar.jsp"></jsp:include>

			<!-- END: Left Aside -->
			<div class="m-grid__item m-grid__item--fluid m-wrapper">
				<!-- END: Subheader -->
				<jsp:include page="${page.contentUrl}"></jsp:include>
			</div>
		</div>
		<!-- end:: Body -->
	</div>
	<script src="${path}/views/vendors/vendors.bundle.js"
		type="text/javascript"></script>
	<script src="${path}/views/js/dashboard.js" type="text/javascript"></script>
	<script src="${path}/views/js/scripts.bundle.js" type="text/javascript"></script>
	
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js "
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo "
		crossorigin="anonymous "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js "
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1 "
		crossorigin="anonymous "></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js "
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM "
		crossorigin="anonymous "></script>

	<script>
		$('#myTab a').on('click', function(event) {
			event.preventDefault()
			$(this).tab('show')
		})
	</script >
	<script src="${path}/views/js/dashboard.js" type="text/javascript"></script>

</body>
</html>