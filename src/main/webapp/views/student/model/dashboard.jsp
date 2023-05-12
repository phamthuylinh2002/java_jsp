<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
            <div class=" mt-5">
                <div class="m-grid__item m-grid__item--fluid m-wrapper">
                    <div class="m-subheader mb-5"></div>
                    <div class="mb-4">
                        <div class="d-flex align-items-center">
                            <div class="mr-auto"></div>
                        </div>
                    </div>
                    <div class="">
                        <div class="container col-12">
                            <div class="row justify-content-center">
                                <div class="col-xl-4 col-md-4 mb-md-0 mb-3">
                                    <c:if test="${trang_thai==0}">
                                        <button data-toggle="modal" data-target="#dang_ky_du_thi_tot_nghiep" type="button" class="btn btn-block btn-primary btn-lg" style="background-color: #0066B1; font-family: tahoma; border: #0066B1">
								ĐĂNG KÝ DỰ ÁN TỐT NGHIỆP<br> <small
									style="font-size: 13px;">(Dành cho sinh viên chưa có
									nhóm)</small>
							</button>
                                        <div class="">
                                            <jsp:include page="dang_ky.jsp"></jsp:include>
                                        </div>
                                    </c:if>
                                    <c:if test="${trang_thai==1}">
                                        <button data-toggle="modal" data-target="#huy_dang_ky_du_thi_tot_nghiep" type="button" class=" btn btn-block btn-outline-danger btn-lg" style="font-family: tahoma">HỦY ĐĂNG KÝ DỰ ÁN TỐT
								NGHIỆP</button>
                                        <div class="">
                                            <jsp:include page="confirm_huy_dang_ky.jsp"></jsp:include>
                                        </div>
                                    </c:if>

                                </div>
                                <div class="">
                                    <jsp:include page="dang_ky.jsp"></jsp:include>
                                </div>
                                <div class="col-xl-4 col-md-4 mb-md-0 mb-3">

                                    <c:if test="${gianhap_roi_nhom==0}">

                                        <button data-toggle="modal" data-target="#tao_nhom" type="button" class="btn btn-block btn-primary btn-lg" style="background-color: #0066B1; font-family: tahoma; border: #F37022;">
								TẠO NHÓM<br> <small style="font-size: 13px;">(Dành
									cho sinh viên đã có nhóm)</small>
							</button>
                                    </c:if>
                                </div>
                                <div class="">
                                    <jsp:include page="tao_nhom.jsp"></jsp:include>
                                </div>
                                <div class="col-xl-4 col-md-4 mb-md-0 mb-3">
                                    <c:if test="${gianhap_roi_nhom==1}">
                                        <button type="button" data-toggle="modal" data-target="#roi_nhom" class="btn btn-block btn-outline-danger btn-lg" style="font-family: tahoma">RỜI NHÓM</button>
                                        <jsp:include page="confirm_out_nhom.jsp"></jsp:include>
                                    </c:if>
                                    <c:if test="${gianhap_roi_nhom==0}">
                                        <button data-toggle="modal" data-target="#vao_nhom_co_san" type="button" class="btn btn-block btn-primary btn-lg" style="background-color: #0066B1; font-family: tahoma; border: #0EB04B;">
								GIA NHẬP NHÓM<br> <small style="font-size: 13px;">(Dành
									cho sinh viên đã có nhóm)</small>
							</button>
                                    </c:if>
                                </div>
                                <div class="">
                                    <jsp:include page="gia_nhap_nhom.jsp"></jsp:include>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <c:if test="${gianhap_roi_nhom==1}">
                    <!-- <NhomCuaSinhVien /> -->
                    <div class="m-portlet">
                        <div class="m-grid__item m-grid__item--fluid m-wrapper">
                            <div class="m-subheader ">
                                <div class="d-flex align-items-center">
                                    <div class="mr-auto">
                                        <h2 style="font-family: tahoma;">Nhóm của bạn</h2>
                                        <h3 style="font-family: tahoma;" class="m-subheader__title " style=" color: #111111">ĐỀ TÀI: ${tenDeTai }</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="">
                                <div class="m-portlet">
                                    <div class="m-portlet__head">
                                        <div class="m-portlet__head-caption">
                                            <div class="m-portlet__head-title">
                                                <h3 style="font-family: tahoma;" class="m-portlet__head-text">
                                                    Giảng Viên: ${giangVien }</h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="m-portlet__body">
                                        <div class="m-section">
                                            <div class="m-section__content">
                                                <b>Mã code: ${maCode }</b>

                                                <c:if test="${capNhatValues==false}">
                                                    <button data-toggle="modal" data-target="#cap_nhat_nhom" style="font-family: tahoma;" id="capNhat" class="btn btn-primary float-md-right mb-3">CẬP NHẬT
										</button>
                                                </c:if>
                                                <div class="">
                                                    <jsp:include page="cap_nhat_nhom.jsp"></jsp:include>
                                                </div>


                                                <table class="table m-table m-table--head-bg-success">
                                                    <thead>
                                                        <tr>
                                                        	<th>STT</th>
                                                            <th>Họ và tên</th>
                                                            <th>Mã SV</th>
                                                            <th>Chuyên ngành</th>
                                                        </tr>
                                                    </thead>
                                                    <c:forEach var="item" items="${sinhVien}" varStatus="loop">
                                                        <tr>
                                                        	<td>${loop.count}</td>
                                                            <th>${item[0] }</th>
                                                            <th>${item[1] }</th>
                                                            <td>${item[2] }</td>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${gianhap_roi_nhom==0}">
                    <!-- Đã đăng ký nhưng chưa có nhóm -->
                    <div class="m-portlet">
                        <div class="m-grid__item m-grid__item--fluid m-wrapper">
                            <div class="">
                                <div class="m-portlet">
                                    <div class="m-portlet__head">
                                        <div class="m-portlet__head-caption">
                                            <div class="m-portlet__head-title">
                                                <h3 style="font-family: tahoma;" class="m-portlet__head-text">
                                                    Các nhóm hiện tại:</h3>
                                            </div>
                                        </div>
                                        <a href="https://docs.google.com/document/d/1XGqJp3WAvHxOK7IfoWmFlsMqtpjPEdMg/edit" class="mt-4 text-right font-weight-bold" target="_blank">HƯỚNG
								DẪN SỬ DỤNG</a>
                                    </div>
                                    <div class="m-portlet__body">
                                        <div class="m-section">
                                            <div class="m-section__content">

                                                <table style="font-family: tahoma;" class="table m-table m-table--head-bg-success">
                                                    <thead>
                                                        <tr>
                                                        	<th>STT</th>
                                                            <th>Tên đề tài</th>
                                                            <th>Số lượng thành viên</th>
                                                            <th>Chuyên ngành</th>
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <c:forEach var="item" items="${Nhoms}" varStatus="loop">
                                                        <tr>
                                                        	<td>${loop.count}</td>
                                                            <th>${item[1] }</th>
                                                            <th>${item[3] }</th>
                                                            <td>${item[2] }</td>
                                                            <td>
                                                                <button data-toggle="modal" data-target="#gia_nhap_nhom_${item[0]}" class="btn btn-primary">Tham gia nhóm</button>
                                                                <jsp:include page="gia_nhap_nhom_public.jsp">
                                                                    <jsp:param value="${item[0]}" name="id" />
                                                                </jsp:include>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="m-portlet__foot d-flex justify-content-end"></div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>

            <style>
                .modal-dialog {
                    max-width: 660px !important;
                }
                
                .m-portlet__body {
                    padding: 10px !important;
                }
            </style>