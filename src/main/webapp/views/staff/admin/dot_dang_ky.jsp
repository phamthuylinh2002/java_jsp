<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <div class="m-content">
                <h3>Các đợt đăng ký đã tạo</h3>

                <div class="mt-5">

                    <section class="fillter-area  mb-5">
                        <div class="m-portlet">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <span class="m-portlet__head-icon"> <i
												class="m-menu__link-icon flaticon-web"></i>
										</span>
                                        <h3 class="m-portlet__head-text">Tạo mới</h3>
                                    </div>
                                </div>
                            </div>

                            <form action="${path}/staff/Admin/dot-dang-ky/tao" method="post" class="m-portlet__body">
                                <div class="row">

                                    <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                        <span for="" class="fillter-name col-4 text-center">Cơ sở</span>
										<input type="text" class="form-control d-inline col-8 "value="${co_so}" disabled/>
                                    </div>

                                    <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                        <span for="" class="fillter-name col-4 text-center">Tên đợt đăng ký</span>
										<input type="text" class="form-control d-inline col-8 " name = "ten_dot_dk" required/>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                        <span for="" class="fillter-name col-4 text-center">Thời gian bắt đầu</span>
                                        <input type="date" class="form-control d-inline col-8 " name="thoi_gian_bat_dau" id = "thoi_gian_bat_dau" required/> 
                                    </div>
                                    <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                        <span for="" class="fillter-name col-4 text-center">Thời gian kết thúc</span>
                                        <input type="date" class="form-control d-inline col-8 " name="thoi_gian_ket_thuc" id = "thoi_gian_ket_thuc" required/> 
											
                                    </div>
                                </div>

                                <div class="row justify-content-center pt-2">
                                    <div class="col-sm-2 col-12 text-center mt-2">
                                        <button type="submit" class="btn btn-primary">Tạo</button>
                                    </div>
                                    <div class="col-sm-2 col-12 text-center mt-2">
                                        <a href="/ToolDangKy/staff/PDT/dot-dang-ky" class="btn btn-danger text-white">
											Làm mới </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </section>

					<div class="m-portlet">

						<div class="m-portlet__body table-scrollable">
							<div class="row">
								<div class="col-6">
									<label for="">Tổng số đợt đăng ký:</label>
									<h4 class="d-inline ml-4">${tong_dot_dk }</h4>
								</div>

							</div>

							<table
								class="table d-sm-table table-bordered table-scrollable m-table m-table--head-bg-primary table-responsive">
								<thead>
									<th scope="col">STT</th>
									<th>Tên Đợt Đăng Ký</th>
									<th>Thời Gian Bắt Đầu</th>
									<th>Thời Gian Kết Thúc</th>
									<th>Thao tác</th>
								</thead>

								<tbody>

									<c:forEach var="item" items="${dsachdot }" varStatus="loop">
										<tr>
											<td>${loop.count}</td>
                                            <!--  <td>${item[0] }</td>-->
											<td>${item[1]}</td>
											<td>${item[2]}</td>
											<td>${item[3]}</td>
											
											<td class=""><button data-toggle="modal"
												data-target="#cap_nhat_dot_dang_ky${item[0] }" type="button"
												class="btn  btn-primary"
												style="background-color: #0066B1; font-family: tahoma; border: #0066B1">
												Sửa<br>
											</button></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
							
							<c:forEach var="item" items="${dsachdot }">
								<div class="">
									<jsp:include page="modalsAdmin/cap_nhap_dot_dk.jsp">
										<jsp:param name="dot_id" value="${item[0]}" />
										<jsp:param name="ten" value="${item[1]}" />							
									</jsp:include>
								</div>
							</c:forEach>
							
						</div>
					</div>
				</div>
			</div>
			</div>

			<input type="hidden" id="route_ds_dot" value="{{ " staff.du_an_tot_nghiep.dot_dang_ky.index") }}">