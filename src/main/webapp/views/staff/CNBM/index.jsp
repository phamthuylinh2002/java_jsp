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
                <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, shrink-to-fit=no">
                <link rel="stylesheet" href="${path}/views/css/style.bundle.css">
                <link rel="stylesheet" href="${path}/views/vendors/vendors.bundle.css">
                <link rel="shortcut icon" href="https://ap.poly.edu.vn/images/favicon.ico" />

            </head>
            <!-- end::Head -->

            <!-- begin::Body -->

            <body class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--fixed m-aside-left--offcanvas m-footer--push m-aside--offcanvas-default">

                <!-- begin:: Page -->
                <div class="m-grid m-grid--hor m-grid--root m-page">

                    <!-- BEGIN: Header -->

                    <jsp:include page="/views/staff/CNBM/header.jsp"></jsp:include>


                    <!-- END: Header -->

                    <!-- begin::Body -->
                    <div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body">

                        <!-- BEGIN: Left Aside -->

                        <jsp:include page="/views/staff/CNBM/sildebar.jsp"></jsp:include>

                        <!-- END: Left Aside -->
                        <div class="m-grid__item m-grid__item--fluid m-wrapper">
                            <!-- END: Subheader -->
                            <jsp:include page="${page.contentUrl}"></jsp:include>
                        </div>
                    </div>
                    <!-- end:: Body -->
                </div>
                <footer>
                    <footer class="" style="line-height: 70px; background-color: white">
                        <div class="container-fluid">
                            <div class="row pt-3 pb-3">
                                <span class="col-3 m-footer__copyright"> Â© 2021 FPT
						Polytechnic </span> <span class="text-right font-weight-bold col-9">
						Mọi thông tin cần hỗ trợ, giảng viên liên hệ với CB phụ trách theo email ${email } (${ten }) </span>
                            </div>
                        </div>
                    </footer>

                    <script src="${path}/views/vendors/vendors.bundle.js" type="text/javascript"></script>
                    <script src="${path}/views/js/dashboard.js" type="text/javascript"></script>
                    <script src="${path}/views/js/scripts.bundle.js" type="text/javascript"></script>

            </body>

            </html>