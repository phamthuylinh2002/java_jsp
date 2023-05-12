<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
            <div>
                <div class="m-content">

                    <h3>Quản lý sinh viên</h3>

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
                                <form action="${path}/staff/PDT/quan-ly-sinh-vien/tim-kiem" method="post" class="m-portlet__body">
                                    <div class="row">
                                        <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                            <span for="" class="fillter-name col-4 text-center">Từ
									khóa</span> <input type="text" class="form-control col-8" name="keyword" id="keyword" value ="${sessionScope.ScopeTK }">
                                        </div>
                                        <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                            <span for="" class="fillter-name col-4 text-center">Chuyên
									ngành</span> <select class="form-control d-inline col-8 select2" name="chuyen_nganh_id" id="chuyen_nganh_id">
									<option value="0" selected>Chọn chuyên ngành</option>

									<c:forEach items="${chuyenNganh}" var="chuyenNganh">
										<option value="${chuyenNganh.id}"
											${chuyenNganh.id== sessionScope.ScopeCN_DS ?"selected":""}>${chuyenNganh.ten}</option>
									</c:forEach>

								</select>
                                        </div>
                                        <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                            <span for="" class="fillter-name col-4 text-center">Đợt Đăng Ký</span>
                                            <select class="form-control d-inline col-8 select2" name="dot_id" id="dot_id">
									<option value="0" selected>Chọn đợt đăng ký</option>
									<c:forEach items="${dotdk}" var="dotdk">

                                        	<option value="${dotdk[0]}" ${dotdk[0]== sessionScope.ScopeDotDK_DS ?"selected":""} >${dotdk[1]}</option>

                                        </c:forEach>

								</select>
                                        </div>
                                    </div>

                                    <div class="row justify-content-center pt-2">
                                        <div class="col-sm-2 col-12 text-center mt-2">
                                            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                                        </div>
                                        <div class="col-sm-2 col-12 text-center mt-2">
                                            <a href="${path}/staff/PDT/quan-ly-sinh-vien" class="btn btn-danger text-white"> Làm mới bộ lọc </a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </section>

                        <div class="row mb-5 bieumau">
                            <div class="col-6 col-sm-4 col-lg-4">
                                <a href="javascript:" data-toggle="modal" data-target="#import_dssv"> <i class="fa fa-download" aria-hidden="true"></i> Import danh sách sinh viên
                                </a>
                            </div>
                            <div class="col-6 col-sm-4 col-lg-4">
                                <a href="${path}/files/tham-khao.xlsx"> <i class="fa fa-download" aria-hidden="true"></i> Tải mẫu Import
                                </a>
                            </div>
                            <div class="col-6 col-sm-4 col-lg-4">
                                <a href="javascript:" data-toggle="modal" data-target="#delete_dssv"> <i class="fa fa-trash" aria-hidden="true"></i> Xóa sinh viên
                                </a>
                            </div>
                        </div>

                        <form action="${path}/files/upload-them" enctype="multipart/form-data" id="form_import_dssv" method="POST">
                            <div class="modal fade" id="import_dssv" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Import Danh sách sinh viên</h5>
                                            <button type="button" id="closeFileBieuMau" class="close" data-dismiss="modal" aria-label="Close" >
									<span aria-hidden="true">&times;</span>
								</button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="col-12">
                                                <label for="">Đợt DATN</label> <select name="dot_id" class="form-control">
										<c:forEach items="${dotdk}" var="dotdk">
											<option value="${dotdk[0]}">${dotdk[1]}</option>
											<!-- dotdk[0] id dợt dk
                                        		dotdk[1] học kỳ
                                        		dotdk[2] id cơ sở
                                        		dotdk[3] tên cơ sở
                                        	  -->
										</c:forEach>
									</select>
                                            </div>
                                            <div class="col-12">
                                                <label for="">Danh sách sinh viên</label> <input type="file" id="file_dssv" name="file_dssv" class="form-control">
                                                <small class="text-danger" id="file_dssv_error"></small>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                            <button type="submit" class="btn btn-primary">
									Tải</a>
							</div>
						</div>
					</div>
				</div>
			</form>

			<div class="modal fade" id="delete_dssv" tabindex="-1"
				aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<form method="POST" id="form_delete_sv" action="${path}/files/delete-sv" enctype="multipart/form-data">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Xóa Sinh
									Viên</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="col-12">
                                                <label for="">Đợt DATN</label> <select name="dot_id" id="dot_id" class="form-control select2">

										<c:forEach items="${dotdk}" var="dotdk">
											<option value="${dotdk[0]}">${dotdk[1]}</option>
											<!-- dotdk[0] id dợt dk
                                        		dotdk[1] học kỳ
                                        		dotdk[2] id cơ sở
                                        		dotdk[3] tên cơ sở
                                        	  -->
										</c:forEach>

									</select> <small class="text-danger" id="dot_id_error"></small>
                                            </div>
                                            <div class="col-12 mt-4">
                                                <label for="">Danh sách sinh viên</label> <input type="file" id="file_ds_delete" name="file_ds_delete" class="form-control">
                                                <small class="text-danger" id="file_ds_delete_error"></small>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                            <button type="submit" class="btn btn-danger">Xóa sinh
									viên</button>
                                        </div>
                        </form>
                        </div>
                        </div>
                        </div>

                        <div class="m-portlet">
							 <div class="m-portlet__body table-scrollable">
				                    
										<div class="col-6">
											<label for="">Tổng số sinh viên:</label>
											<h4 class="d-inline ml-4">${tongSV }</h4>
										</div>
										
                                        <div class="col-6">
                                 
                                            <button data-toggle="modal" data-target="#modal_create" class="col-4 btn btn-success">Thêm sinh viên</button>

						                    
                                            <div class="modal fade" id="modal_create" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-lg">
                                                    <div class="modal-content">
                                                        <form method="POST" id="form_create_sv" onsubmit="return validate()" action="${path}/staff/PDT/quan-ly-sinh-vien/insert">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Thêm sinh viên</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="form-group">
                                                                    <label for="recipient-name" class="col-form-label">Họ
															và tên</label> <input type="text" class="form-control" required="required" maxlength="100" id="ten_sv" name="ten_sv">
                                                                    <small class="text-danger" id="name_error"></small>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="message-text" class="col-form-label">Mã
															sinh viên</label> <input type="text" class="form-control" required="required" maxlength="100" id="ma_sv" name="ma_sv"> <small class="text-danger" id="ma_sv_error"></small>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="message-text" class="col-form-label">Email</label>
                                                                    <input type="email" class="form-control" name="email" required="required" maxlength="255" id="email_sv"> <small class="text-danger" id="email_error"></small>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="message-text" class="col-form-label">Khóa</label>
                                                                    <select class="form-control" name="khoa" id="khoa_sv">
															<option value="0"  >Chọn khóa</option>
															<c:forEach var="khoa" items="${khoa }">
																<option value="${khoa}">${khoa }</option>
															</c:forEach>

														</select> <small class="text-danger" id="khoa_error"></small>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="message-text" class="col-form-label">Chuyên
															ngành</label> <select class="form-control" name="chuyen_nganh_id" id="sv_chuyen_nganh">
															<option value="0"  >Chọn chuyên
																ngành</option>
															<c:forEach var="tenChuyenNganh"
																items="${tenChuyenNganh }">
																<option value="${tenChuyenNganh.id}">
																	${tenChuyenNganh.ten }</option>
															</c:forEach>

														</select> <small class="text-danger" id="chuyen_nganh_error"></small>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="message-text" class="col-form-label d-block">Đợt
															Đăng Ký</label>
                                                                    <select class="form-control select2" name="dot_id" style="width: 100%;" id="dot_id">
															<c:forEach items="${dotdk}" var="dotdk">
																<option value="${dotdk[0]}">${dotdk[1]}</option>
																	<!-- dotdk[0] id dợt dk
						                                        		dotdk[1] học kỳ
						                                        		dotdk[2] id cơ sở
						                                        		dotdk[3] tên cơ sở
						                                        	  -->
																</c:forEach>
														</select> <small class="text-danger" id="dot_id_error"></small>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="message-text" class="col-form-label">Trạng
															thái</label> <select class="form-control" name="trang_thai" id="sv_trang_thai">
															<option value="0">Chưa đăng ký</option>
															<option value="1">Đăng ký</option>
														</select> <small class="text-danger" id="trang_thai_error"></small>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                                <button type="submit" class="btn btn-primary">Thêm
														sinh viên</button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                        
                                        
                                    
										
                                    
				                    
                                   
                                    <br>

                                    <table class="table table-bordered m-table d-sm-table m-table--head-bg-primary table-responsive">
                                        <thead>
                                            <th scope="col">STT</th>
                                            <th>Mã Sinh Viên</th>
                                            <th>Tên Sinh Viên</th>
                                            <th>Email</th>
                                            <th>Khóa</th>
                                            <th>Chuyên Ngành</th>
                                            <th>Thao tác</th>
                                        </thead>

                                        <tbody>

                                            <c:forEach var="item" items="${sinhVien }" varStatus="loop">
                                                <tr>
                                                    <input type="hidden" id="url_get_info_sv_${item[0] }" value="">
                                                    <td>${loop.count}</td>
                                               		<!--  <td>${item[0] }</td>-->
                                                    <!-- id -->
                                                    <td>${item[1] }</td>
                                                    <!-- ma -->
                                                    <td>${item[2] }</td>
                                                    <!-- hoten -->
                                                    <td>${item[3] }</td>
                                                    <!-- email -->
                                                    <td>${item[4] }</td>
                                                    <!-- khoa-->
                                                    <td>${item[5] }</td>
                                                    <!-- chuyen nganh-->
                                                    <td>
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal_edit_${item[0] }">
												Sửa</button>
                                                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modal_delete_${item[0] }">
												Xóa</button>
                                                    </td>
                                                    <div class="modal fade" id="modal_edit_${item[0] }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog modal-lg">
                                                            <div class="modal-content">
                                                                <form method="POST" id="form_edit_sv_${item[0] }" onsubmit="return validateUpdate(${item[0]})" action="${path}/staff/PDT/quan-ly-sinh-vien/edit/?idSv=${item[0] }">

                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="exampleModalLabel">
                                                                            Chỉnh sửa thông tin sinh viên</h5>
                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
                                                                    </div>
                                                                    <div class="modal-body">


                                                                        <div class="form-group">
                                                                            <label for="recipient-name" class="col-form-label">Họ
																	và tên</label> <input type="text" class="form-control" value="${item[2] }" id="ten_sv_${item[0] }" required="required" maxlength="100" name="ten_sv"> <small class="text-danger"
                                                                                id="name_error_${item[0] }"></small>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="message-text" class="col-form-label">Mã
																	sinh viên</label> <input type="text" class="form-control" required="required" maxlength="100" value="${item[1] }" name="ma_sv" id="ma_sv_${item[0] }">
                                                                            <small class="text-danger" id="ma_sv_error_${item[0] }"></small>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="message-text" class="col-form-label">Email</label>
                                                                            <input type="email" class="form-control" required="required" maxlength="255" value="${item[3] }" name="email" id="email_sv_${item[0] }"> <small class="text-danger" id="email_error_${item[0] }"></small>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="message-text" class="col-form-label">Khóa</label>
                                                                            <select class="form-control" name="khoa" id="khoa_sv_${item[0]}">
																	<option value="${item[4] }" selected>
																		${item[4] }</option>
																	<c:forEach var="khoa" items="${khoaHoc }">
																		<option value="${khoa }">${khoa }</option>
																	</c:forEach>

																</select> <small class="text-danger" id="khoa_error_${item[0] }"></small>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="message-text" class="col-form-label">Chuyên
																	ngành</label> <select class="form-control" name="chuyen_nganh_id" id="sv_chuyen_nganh_${item[0] }">
																	<option value="${item[6] }" selected>
																		${item[5]}</option>
																	<c:forEach var="tenChuyenNganh"
																		items="${tenChuyenNganh }">
																		<option value="${tenChuyenNganh.id }">
																			${tenChuyenNganh.ten }</option>
																	</c:forEach>

																</select> <small class="text-danger" id="chuyen_nganh_error_${item[0]}"></small>
                                                                        </div>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                                        <button type="submit" class="btn btn-primary">Sửa</button>

                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal fade" id="modal_delete_${item[0] }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog modal-lg">
                                                            <div class="modal-content">
                                                                <form method="POST" id="form_delete_sv_${item[0] }" action="${path}/staff/PDT/quan-ly-sinh-vien/delete/?idSv=${item[0] }">

                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="exampleModalLabel">Xóa sinh viên</h5>
                                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
																<span aria-hidden="true">&times;</span>
															</button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <h3>Xác nhận xóa sinh viên</h3>
                                                                        <div class="form-group">
                                                                            <label for="recipient-name" class="col-form-label">Họ
																	và tên</label> <input type="text" class="form-control" disabled="disabled" value="${item[2] }" id="ten_sv_${item[0] }"> <small class="text-danger" id="name_error_{{ item->id }}"></small>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="message-text" class="col-form-label">Mã
																	sinh viên</label> <input type="text" class="form-control" disabled="disabled" value="${item[1] }" name="ma_sv" id="ma_sv_${item[0] }">
                                                                            <small class="text-danger" id="ma_sv_error_${item[0] }"></small>
                                                                        </div>

                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                                        <button type="submit" class="btn btn-primary">Xóa</button>
                                                                    </div>
                                                                </form>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                               

                            </div>
                        </div>
                    </div>
                </div>

            <script>
                function setSelected(event) {
                    const dot_id = event.target.value;
                    if (Number(dot_id) == 1) {
                        document.getElementById('chuyen_nganh_id').value = Person_ID;

                    }
                }

                function validate() {

                    var email = document.getElementById("email_sv").value.trim();

                    var emailTest = /^\S+@\S{3}\.\S{3}\.\S{2}$/;
                    if (document.getElementById("ten_sv").value.trim().length < 5) {
                        document.getElementById("name_error").innerHTML = "Tên sinh viên phải có độ dài lớn hơn 5 và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("name_error").innerHTML = "";
                        document.getElementById("ten_sv").innerHTML=document.getElementById("ten_sv").value.trim();
                    }
                    if (document.getElementById("ma_sv").value.trim().length < 7) {
                        document.getElementById("ma_sv_error").innerHTML = "Mã sinh viên phải có độ dài lớn hơn 6 và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("ma_sv_error").innerHTML = "";
                        document.getElementById("ma_sv").innerHTML=document.getElementById("ma_sv").value.trim();
                    }
                    var fpt = email.substring(email.indexOf("@") + 1, email.length).toUpperCase();

                    if (!emailTest.test(email) || !fpt == "FPT.EDU.VN") {
                        document.getElementById("email_error").innerHTML = "Vui lòng nhập đúng định dạng email @fpt.edu.vn và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("email_error").innerHTML = "";
                        document.getElementById("email_sv").innerHTML=document.getElementById("email_sv").value.trim();
                    }
                    if (document.getElementById("khoa_sv").value == "0") {
                        document.getElementById("khoa_error").innerHTML = "Vui lòng chọn khóa học";
                        return false;
                    } else {
                        document.getElementById("khoa_error").innerHTML = "";
                    }
                    if (document.getElementById("sv_chuyen_nganh").value == "0") {
                        document.getElementById("chuyen_nganh_error").innerHTML = "Vui lòng chọn chuyên ngành";
                        return false;
                    } else {
                        document.getElementById("chuyen_nganh_error").innerHTML = "";
                    }

                    return true;
                }

                function validateUpdate(id) {

                    var email = document.getElementById("email_sv_" + id).value.trim();

                    var emailTest = /^\S+@\S{3}\.\S{3}\.\S{2}$/;
                    if (document.getElementById("ten_sv_" + id).value.trim().length < 5) {
                        document.getElementById("name_error_" + id).innerHTML = "Tên sinh viên phải có độ dài lớn hơn 5 và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("name_error_" + id).innerHTML = "";
                        document.getElementById("ten_sv_" + id).innerHTML=document.getElementById("ten_sv_" + id).value.trim();
                    }
                    if (document.getElementById("ma_sv_" + id).value.trim().length < 7) {
                        document.getElementById("ma_sv_error_" + id).innerHTML = "Mã sinh viên phải có độ dài lớn hơn 6 và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("ma_sv_error_" + id).innerHTML = "";
                        document.getElementById("ma_sv_" + id).innerHTML=document.getElementById("ma_sv_" + id).value.trim();
                    }
                    var fpt = email.substring(email.indexOf("@") + 1, email.length).toUpperCase();

                    if (!emailTest.test(email) || !fpt == "FPT.EDU.VN") {
                        document.getElementById("email_error_" + id).innerHTML = "Vui lòng nhập đúng định dạng email @fpt.edu.vn và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("email_error_" + id).innerHTML = "";
                        document.getElementById("email_sv_" + id).innerHTML=document.getElementById("email_sv_" + id).value.trim();
                    }
                    if (document.getElementById("khoa_sv_" + id).value == "0") {
                        document.getElementById("khoa_error_" + id).innerHTML = "Vui lòng chọn khóa học";
                        return false;
                    } else {
                        document.getElementById("khoa_error_" + id).innerHTML = "";
                    }
                    if (document.getElementById("sv_chuyen_nganh_" + id).value == "0") {
                        document.getElementById("chuyen_nganh_error_" + id).innerHTML = "Vui lòng chọn chuyên ngành";
                        return false;
                    } else {
                        document.getElementById("chuyen_nganh_error_" + id).innerHTML = "";
                    }

                    return true;
                }
            </script>