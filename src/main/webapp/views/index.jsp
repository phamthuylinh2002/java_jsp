<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${page.title }</title>

<link rel="stylesheet" href="${path}/views/css/tailwind.css"> 
<link rel="stylesheet" href="${path}/views/css/style.bundle.css">
<link rel="stylesheet" href="${path}/views/build/tailwind.css">

</head>

<body>
	<header id="m_header" class="m-grid__item    m-header "
		m-minimize-offset="200" m-minimize-mobile-offset="200"
		style="background-color: white">
		<div class="m-container m-container--fluid m-container--full-height">
			<div class="m-stack m-stack--ver m-stack--desktop">
				<div class="m-stack__item m-stack__item--fluid m-header-head"
					id="m_header_nav">
					<button
						class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-dark "
						id="m_aside_header_menu_mobile_close_btn">
						<i class="la la-close"></i>
					</button>

					<div class="d-flex justify-content-start"
						style="display: inline-block !important; position: relative; z-index: 9999;">
						<a style="display: inline-block; padding: 5px 10px;" href="">
							<img
							src="https://caodang.fpt.edu.vn/wp-content/uploads/logo-3.png"
							class="img-fluid">
						</a>
					</div>

					<div id="m_header_menu"
						class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-dark m-aside-header-menu-mobile--submenu-skin-dark ">
					</div>

<style>
.m-page--wide .m-header, .m-page--fluid .m-header {
	padding-bottom: 74px !important;
}

.m-topbar {
	top: -73px !important;
	height: 73px !important;
}

.m-footer--push.m-aside-left--enabled:not(.m-footer--fixed) .m-wrapper {
	margin-bottom: 60px !important;
}
</style>
					<div id="m_header_topbar"
						class="m-topbar m-stack m-stack--ver m-stack--general m-stack--fluid mr-5">
						<div
							class="m-stack__item m-topbar__nav-wrapper align-middle text-right">
							<a href="${path}/Student/logout"
								class="btn m-btn--pill btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder">
								<span class="fontTahoma">Đăng xuất<span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>


	<div class="">
		
		
		 <jsp:include page="${page.contentUrl }"></jsp:include>
	</div>


	<footer>
		<footer class="" style="line-height: 70px; background-color: white">
			<div class="container-fluid">
				<div class="row pt-3 pb-3">
					<span class="col-3 m-footer__copyright"> Â© 2021 FPT
						Polytechnic </span> <span class="text-right font-weight-bold col-9">
						Mọi thông tin cần hỗ trợ, sinh viên liên hệ với CB phụ trách theo email ${email } (${ten }) </span>
				</div>
			</div>
		</footer>

		<style>
footer {
  width: 100%;
  height: 50px;
}

@media ( min-width : 920px) {
	#contact {
		display: flex;
	}
}
</style>
	</footer>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
