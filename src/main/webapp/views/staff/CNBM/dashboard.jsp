<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

            <div>
                <div class="m-content">
                    <h3>Dashboard</h3>
                    <div class="mt-5">
                        <div class="col-md-5 p-0 offset-md-1">

                            <form class="m-form m-form--label-align-right" method="GET">
                                <div class="card-body p-0">
                                    <div class="d-flex align-items-center rounded pt-3 pb-3">
                                        <select class="form-control d-inline col-8 select2" name="dot_id" onchange="myFunction(event)" id="dot_id">
								<option value="0" ${0== sessionScope.ScopeDotDKCNDashboard ?"selected":""}>Chọn đợt đăng ký</option>
								
								<c:forEach items="${dotdk}" var="dotdk">
									<option value="${dotdk.id}" ${dotdk.id== sessionScope.ScopeDotDKCNDashboard ?"selected":""} >${dotdk.hocKy}</option>
								</c:forEach>
							</select>
                                    </div>

                                </div>
                            </form>
                        </div>

                        <div class="card card-custom d-md-inline-flex card-stretch gutter-b mb-3 col-12 col-md-5 d-flex offset-md-1">
                            <div class="card-body p-0">
                                <div class="d-flex align-items-center bg-light-warning rounded pt-3 pb-3">
                                    <div class="col-10 p-0">
                                        <span class="text-dark-75 p-0 text-hover-primary font-size-lg text-primary">
								Sinh viên chưa đăng ký </span>
                                    </div>
                                    <span class="font-weight-bolder font-weight-bold col-2 text-right text-danger py-1 p-0 font-size-lg">
							${SinhVienChuaDangKy} </span>
                                </div>
                            </div>
                        </div>

                        <div class="card card-custom d-md-inline-flex card-stretch gutter-b mb-3 col-12 col-md-5 d-flex">
                            <div class="card-body p-0">
                                <div class="d-flex align-items-center bg-light-warning rounded pt-3 pb-3">
                                    <div class="col-10 p-0">
                                        <span class="text-dark-75 p-0 text-primary text-hover-primary font-size-lg">
								Sinh viên đã đăng ký </span>
                                    </div>
                                    <span class="font-weight-bolder font-weight-bold col-2 text-right text-info py-1 p-0 font-size-lg">
							${SinhVienDaDangKy} </span>
                                </div>
                            </div>
                        </div>

                        <div class="card card-custom d-md-inline-flex card-stretch gutter-b mb-3 col-12 col-md-5 offset-md-1">
                            <div class="card-body p-0">
                                <div class="d-flex align-items-center bg-light-warning rounded pt-3 pb-3">
                                    <div class="col-10 p-0">
                                        <span class="text-dark-75 text-primary p-0 text-hover-primary font-size-lg">
								Số nhóm đã tạo </span>
                                    </div>
                                    <span class="font-weight-bolder font-weight-bold col-2 text-right text-success py-1 p-0 font-size-lg">
							${SoNhomDaTao} </span>
                                </div>
                            </div>
                        </div>

                        <div class="card card-custom d-md-inline-flex card-stretch gutter-b mb-3 col-12 col-md-5">
                            <div class="card-body p-0">
                                <div class="d-flex align-items-center bg-light-warning rounded pt-3 pb-3">
                                    <div class="col-10 p-0">
                                        <span class="text-dark-75 p-0 text-primary text-hover-primary font-size-lg">
								Số giảng viên có thể đăng ký </span>
                                    </div>
                                    <span class="font-weight-bolder font-weight-bold col-2 text-right text-warning py-1 p-0 font-size-lg">
							${SoGVcoTheDK}
						</span>
                                </div>
                            </div>
                        </div>

                        <br> <br>

                        <c:if test="${check==1 }">

                            <div class="m-portlet">
                                <div class="m-grid__item m-grid__item--fluid m-wrapper">
                                    <div class="">
                                        <div class="m-portlet">
                                            <div class="m-portlet__head">
                                                <div class="m-portlet__head-caption">
                                                    <div class="m-portlet__head-title">
                                                        <h3 style="font-family: tahoma;" class="m-portlet__head-text">
                                                            Các nhóm hướng dẫn</h3>
                                                    </div>
                                                </div>
                                                <a href="https://docs.google.com/document/d/1bzV6MOXqHQRk-KEfKVY1jWNie5TcvuUN" class="mt-4 text-right font-weight-bold" target="_blank">HƯỚNG
								DẪN SỬ DỤNG</a>
                                            </div>
                                            <div class="m-portlet__body">
                                                <div class="m-section">
                                                    <div class="m-section__content">
                                                        <table style="font-family: tahoma;" class="table m-table m-table--head-bg-success">
                                                            <thead>
                                                                <tr>
                                                                    <th>Tên đề tài</th>
                                                                    <th>Số lượng thành viên</th>
                                                                    <th>Chuyên ngành</th>
                                                                    <th>Trạng thái nhóm</th>
                                                                    <th></th>
                                                                </tr>
                                                            </thead>

                                                            <c:forEach var="item" items="${Nhoms}">

                                                                <tr>
                                                                    <th>${item[1] }</th>
                                                                    <th>${item[4] }</th>
                                                                    <td>${item[3] }</td>
                                                                    <td>${item[2] == null?'Nhóm công khai':'Nhóm bí mật' }</td>
                                                                    <th>
                                                                        <a class="btn btn-primary" href="${path}/staff/CNBM/chi-tiet-nhom?nhom_id=${item[0]}">
										Chi tiết nhóm </a>

                                                                    </th>
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
                </div>
            </div>
            <input type="hidden" id="route_ds_dot" value="">


            <script src="${path}/views/js/dashboardPDT.js"></script>
            <!--
<script type="text/javascript">
function myFunction(event){
	console.log(104, event.target.value);
	const dot_id = event.target.value;
	
	window.location.href = window.location.href+"?nhom-id="+dot_id;
	
}; -->
            </script>