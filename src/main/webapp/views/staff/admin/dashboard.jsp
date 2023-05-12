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
								<option value="0" ${0== sessionScope.ScopeDotDKDashboard ?"selected":""}>Chọn đợt đăng ký</option>
								
								<c:forEach items="${dotdk}" var="dotdk">
									<option value="${dotdk.id}" ${dotdk.id== sessionScope.ScopeDotDKDashboard ?"selected":""} >${dotdk.hocKy}</option>
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

                    </div>
                </div>
            </div>
            <input type="hidden" id="route_ds_dot" value="">


            <script src="${path}/views/js/dashboardPDT.js"></script>
            </script>