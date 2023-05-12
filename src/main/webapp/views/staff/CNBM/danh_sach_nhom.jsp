<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <div class="m-content">
                <h3>Danh sách các nhóm đã tạo</h3>

                <div class="mt-5">

                    <section class="fillter-area  mb-5">
                        <div class="m-portlet">
                            <div class="m-portlet__head">
                                <div class="m-portlet__head-caption">
                                    <div class="m-portlet__head-title">
                                        <span class="m-portlet__head-icon"> <i
												class="m-menu__link-icon flaticon-web"></i>
										</span>
                                        <h3 class="m-portlet__head-text">Bộ lọc</h3>
                                    </div>
                                </div>
                            </div>

                            <form action="${path}/staff/CNBM/danh-sach-cac-nhom-da-tao/tim-kiem" method="post" class="m-portlet__body">
                                <div class="row">

                                    <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                        <span for="" class="fillter-name col-4 text-center">Giảng
											viên hướng dẫn</span> <select class="form-control col-8 select2" name="giang_vien_id" id="giang_vien_id">
											<option value="0" selected>Chọn giảng viên</option>

											<c:forEach items="${GVHuongDan}" var="GVHuongDan">

												<option value="${GVHuongDan[0]}"
													${GVHuongDan[0]==sessionScope.ScopeGVHD_nhom ?"selected":""}>
													${GVHuongDan[1]}</option>
											</c:forEach>

										</select>
                                    </div>
                                     <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                        <span for="" class="fillter-name col-4 text-center">Chuyên
											ngành</span> <select class="form-control d-inline col-8 select2" name="chuyen_nganh_id" id="chuyen_nganh_id" onchange="submitData(event)">
											<option value="0" selected>Chọn chuyên ngành</option>

											<c:forEach items="${chuyenNganh}" var="chuyenNganh">
											
												<option value="${chuyenNganh[0]}"
													${chuyenNganh[0]==sessionScope.ScopeCN_nhom ?"selected":""}>
													${chuyenNganh[1]}</option>

											</c:forEach>

										</select>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                        <span for="" class="fillter-name col-4 text-center">Đợt
											Đăng Ký</span> <select class="form-control d-inline col-8 select2" name="dot_id" id="dot_id">

											<option value="0" selected>Chọn đợt đăng ký</option>
											<c:forEach items="${dotdk}" var="dotdk">

												<option value="${dotdk[0]}" ${dotdk[0]==sessionScope.ScopeDotDK_nhom
													?"selected":""}>${dotdk[1]}</option>

												<!-- dotdk[0] id dợt dk
                                        		dotdk[1] học kỳ
                                        		dotdk[2] id cơ sở
                                        		dotdk[3] tên cơ sở
                                        	  -->
											</c:forEach>
										</select>
                                    </div>
                                    <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                        <span for="" class="fillter-name col-4 text-center">Số
											thành viên</span> <select class="form-control d-inline col-8 select2" name="so_thanh_vien" id="so_thanh_vien">
											<option value="0" ${0==sessionScope.ScopeSLTV_nhom ?"selected":""}>Chọn
												số thành viên</option>
											<option value="1" ${1==sessionScope.ScopeSLTV_nhom ?"selected":""}>1
											</option>
											<option value="2" ${2==sessionScope.ScopeSLTV_nhom ?"selected":""}>2
											</option>
											<option value="3" ${3==sessionScope.ScopeSLTV_nhom ?"selected":""}>3
											</option>
											<option value="4" ${4==sessionScope.ScopeSLTV_nhom ?"selected":""}>4
											</option>
											<option value="5" ${5==sessionScope.ScopeSLTV_nhom ?"selected":""}>5
											</option>
											<option value="6" ${6==sessionScope.ScopeSLTV_nhom ?"selected":""}>6
											</option>
											<option value="7" ${7==sessionScope.ScopeSLTV_nhom ?"selected":""}>7
											</option>
										</select>
                                    </div>
                                </div>

                                <div class="row justify-content-center pt-2">
                                    <div class="col-sm-2 col-12 text-center mt-2">
                                        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                                    </div>
                                    <div class="col-sm-2 col-12 text-center mt-2">
                                        <a href="${path}/staff/CNBM/danh-sach-cac-nhom-da-tao" class="btn btn-danger text-white">
											Làm mới bộ lọc </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </section>

                    <div class="row mb-5 bieumau">
                        <div class="col-12 col-sm-4 col-lg-4">
                            <a href="${path}/files/exportDSNhom">
                                <!-- data-toggle="modal"
                        data-target="#export_ds_nhom">--><i class="fa fa-download" aria-hidden="true"></i> Xuất danh sách nhóm
                            </a>
                        </div>
                    </div>

                    <form action="${path}/files/exportDSNhom" method="POST">
                        <div class="modal fade" id="export_ds_nhom" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Xuất danh sách nhóm</h5>
                                        <button type="button" id="closeFileBieuMau" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="col-12 mt-4">
                                            <label>Chuyên ngành</label> <select name="chuyen_nganh_id" class="form-control col-12">
												<option value="0">Chọn chuyên ngành</option>

												<c:forEach items="${chuyenNganh}" var="chuyenNganh">
													<option value="${chuyenNganh[0]}">${chuyenNganh[1]}</option>
												</c:forEach>

											</select>
                                        </div>
                                        <div class="col-12 mt-4">
                                            <label>Giảng viên hướng dẫn</label> <select name="giang_vien_id" class="form-control col-12">
												<option value="0">Chọn giảng viên</option>

												<c:forEach items="${GVHuongDan}" var="GVHuongDan">
													<option value="${GVHuongDan[0]}">${GVHuongDan[1]}</option>
												</c:forEach>

											</select>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                        <button type="submit" onclick="closeModal('export_ds_nhom')" class="btn btn-primary">
											Tải</a>
									</div>
								</div>
							</div>
						</div>
					</form>

					<div class="m-portlet">

						<div class="m-portlet__body table-scrollable">
							<div class="row">
								<div class="col-6">
									<label for="">Tổng số nhóm:</label>
									<h4 class="d-inline ml-4">${tongNhom }</h4>
								</div>

							</div>

							<table
								class="table d-sm-table table-bordered table-scrollable m-table m-table--head-bg-primary table-responsive">
								<thead>
									<th scope="col">STT</th>
									<th>Tên Đề Tài</th>
									<th>Mã Tham Gia</th>
									<th>Chuyên Ngành</th>
									<th>Số thành viên</th>
									<th>Giảng viên Hướng dẫn</th>
									<th>Trạng thái hiển thị</th>
									<th>Thao tác</th>
								</thead>

								<tbody>

									<c:forEach var="item" items="${dSachNhom }" varStatus="loop">
										<tr>
											<td>${loop.count}</td>
                                            <!--  <td>${item[0] }</td>-->
											<td>${item[1]}</td>
											<td>${item[2]}</td>
											<td>${item[3]}</td>
											<td>${item[4]}</td>
											<td>${item[5]}</td>
											<td>${item[2] == null?'Nhóm công khai':'Nhóm bí mật' }</td>
											<td><a class="btn btn-primary"
													href="${path}/staff/CNBM/chi-tiet-nhom?nhom_id=${item[0]}">
													Chi tiết nhóm </a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>

					</div>
				</div>
			</div>
			</div>

			<input type="hidden" id="route_ds_dot" value="{{ " staff.du_an_tot_nghiep.dot_dang_ky.index") }}">


			<script src="{{ asset('js/staff/du_an_2/filter_dot.js') }}"></script>
			<script src="{{ asset('js/staff/du_an_2/filter.js') }}"></script>
			<script>
				('#sub_menu_datn').addClass('m-menu__item--open');
				(".select2").select2();

				var closeModal = function (id) {
					('#' + id).modal('hide');
					('.modal-backdrop').addClass('d-none');
				}
			</script>