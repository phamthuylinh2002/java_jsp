<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <div>
                <div class="m-content">

                    <h3>Quản lý giảng viên</h3>

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
                                <form action="${path }/staff/CNBM/quan-ly-giang-vien/tim-kiem" method="post"
                                    class="m-portlet__body">
                                    <div class="row">
                                     <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                        <span for="" class="fillter-name col-4 text-center">Chuyên
											ngành</span> <select class="form-control d-inline col-8 select2" name="chuyen_nganh_id" id="chuyen_nganh_id" onchange="submitData(event)">
											<option value="0" selected>Chọn chuyên ngành</option>

											<c:forEach items="${chuyenNganh}" var="chuyenNganh">
												
													<option value="${chuyenNganh[0]}"
														${chuyenNganh[0]==sessionScope.ScopeCN_TKGV ?"selected":""}>
														${chuyenNganh[1]}</option>
	
												</c:forEach>

										</select>
                                    </div>
                                        <div
                                            class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                            <span for="" class="fillter-name col-4 text-center">Từ
                                                khóa</span> <input type="text" class="form-control col-8" name="keyword"
                                                id="keyword" maxlength="255" value ="${sessionScope.ScopeTK }">
                                        </div>


                                    </div>

                                    <div class="row justify-content-center pt-2">
                                        <div class="col-sm-2 col-12 text-center mt-2">
                                            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                                        </div>
                                        <div class="col-sm-2 col-12 text-center mt-2">
                                            <a href="${path }/staff/CNBM/quan-ly-giang-vien"
                                                class="btn btn-danger text-white"> Làm mới bộ lọc </a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </section>

                        <div class="row mb-5 bieumau">
                            <div class="col-6 col-sm-4 col-lg-4">
                                <a href="javascript:" data-toggle="modal" data-target="#import_dsgv"> <i
                                        class="fa fa-download" aria-hidden="true"></i> Import danh sách giảng viên
                                </a>
                            </div>
                            <div class="col-6 col-sm-4 col-lg-4">
                                <a href="${path}/files/tham-khao.xlsx"> <i class="fa fa-download"
                                        aria-hidden="true"></i> Tải mẫu Import
                                </a>
                            </div>
                            <div class="col-6 col-sm-4 col-lg-4">
                                <a href="javascript:" data-toggle="modal" data-target="#delete_dssv"> <i
                                        class="fa fa-trash" aria-hidden="true"></i> Xóa giảng viên
                                </a>
                            </div>
                        </div>

                        <form action="${path}/files/upload-them" enctype="multipart/form-data" id="form_import_dssv"
                            method="POST">
                            <div class="modal fade" id="import_dsgv" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Import Danh sách giảng viên
                                            </h5>
                                            <button type="button" id="closeFileBieuMau" class="close"
                                                data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="col-12">
                                                <label for="">Danh sách giảng viên</label> <input type="file"
                                                    id="file_dsgv" name="file_dsgv" class="form-control">
                                                <small class="text-danger" id="file_dsgv_error"></small>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                data-dismiss="modal">Hủy</button>
                                            <button type="submit" class="btn btn-primary">
                                                Tải</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <div class="modal fade" id="delete_dssv" tabindex="-1" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <form method="POST" id="form_delete_sv" action="${path}/files/delete-gv"
                                        enctype="multipart/form-data">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Xóa Giảng
                                                Viên</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">

                                            <div class="col-12 mt-4">
                                                <label for="">Danh sách giảng viên</label> <input type="file"
                                                    id="file_ds_delete" name="file_ds_delete" class="form-control">
                                                <small class="text-danger" id="file_ds_delete_error"></small>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                data-dismiss="modal">Hủy</button>
                                            <button type="submit" class="btn btn-danger">Xóa giảng
                                                viên</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="m-portlet">

                            <div class="m-portlet">
								<div class="row">
								<div class="col-6">
									<label for="">Tổng số giảng viên:</label>
									<h4 class="d-inline ml-4">${tongGv }</h4>
								</div>

							</div>
                                <div class="m-portlet__body">

                                    <table
                                        class="table table-bordered m-table d-sm-table m-table--head-bg-primary table-responsive">
                                        <thead>
                                            <th scope="col">STT</th>
                                            <th>Tên giảng viên</th>
                                            <th>SDT</th>
                                            <th>EmailFPT</th>
                                            <th>EmailFE</th>
                                            <th>Chuyên ngành</th>
                                            <th>Số nhóm đang hướng dẫn</th>                             
                                            <th>Số nhóm hướng dẫn tối đa</th>
                                            <th>Thao tác</th>
                                        </thead>

                                        <tbody>

                                            <c:forEach var="item" items="${giangVien }" varStatus="loop">
                                                <tr>
                                                    <input type="hidden" id="url_get_info_sv_${item[0] }" value="">
                                                    <td>${loop.count}</td>
                                               		<!--  <td>${item[0] }</td>-->
                                                    <!-- id -->
                                                    <td>${item[1] }</td>
                                                    <!-- tên giảng viên -->
                                                    <td>${item[2] }</td>
                                                    <!-- sdt -->
                                                    <td>${item[3] }</td>
                                                    <!-- emailfpt -->
                                                    <td>${item[4] }</td>
                                                    <!-- emailfe-->
                                                    <td>${item[7] }</td>
                                                    <!-- chuyên ngành -->
                                                    <td>${item[6] }</td>
                                                    <!-- Số nhóm hướng dẫn -->
                                                    <td>${item[5] }</td>
                                                     <td>
                                                        <button type="button" class="btn btn-primary"
                                                            data-toggle="modal" data-target="#modal_edit_${item[0] }">
                                                            Sửa</button>
                                                         <button type="button" class="btn btn-danger"
                                                            data-toggle="modal" data-target="#modal_delete_${item[0] }">
                                                            Xóa</button>
                                                        <a href="${path }/staff/CNBM/chi-tiet-gv?id-GV=${item[0] }&dotdk=${dotdky}"
                                                			class="btn btn-primary text-white"> Danh sách nhóm </a>
                                                    </td> 
                                                    <div class="modal fade" id="modal_edit_${item[0] }" tabindex="-1"
                                                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog modal-lg">
                                                            <div class="modal-content">
                                                                <form method="POST" id="form_edit_gv_${item[0] }"
                                                                    onsubmit="return validate(${item[0]})"
                                                                    action="${path}/staff/CNBM/quan-ly-giang-vien/edit/?idGv=${item[0] }">

                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="exampleModalLabel">
                                                                            Chỉnh sửa thông tin giảng viên</h5>
                                                                        <button type="button" class="close"
                                                                            data-dismiss="modal" aria-label="Close">
                                                                            <span aria-hidden="true">&times;</span>
                                                                        </button>
                                                                    </div>
                                                                    <div class="modal-body">


                                                                        <div class="form-group">
                                                                            <label for="recipient-name"
                                                                                class="col-form-label">Họ
                                                                                và tên</label> <input type="text"
                                                                                class="form-control" required="required"
                                                                                value="${item[2] }"
                                                                                id="ten_gv_${item[0] }" name="ten_gv"
                                                                                maxlength="255">
                                                                            <small id="name_error_${item[0] }"
                                                                                class="text-danger"></small>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="message-text"
                                                                                class="col-form-label">Mã
                                                                                giảng viên</label> <input type="text"
                                                                                class="form-control" maxlength="50"
                                                                                required="required" value="${item[1] }"
                                                                                name="ma_gv" id="ma_gv_${item[0] }">
                                                                            <small id="ma_gv_error_${item[0] }"
                                                                                class="text-danger"></small>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="message-text"
                                                                                class="col-form-label">SDT</label>
                                                                            <input type="text" class="form-control"
                                                                                required="required" value="${item[3] }"
                                                                                name="sdt" id="sdt_gv_${item[0] }">
                                                                            <small id="sdt_error_${item[0] }"
                                                                                class="text-danger"></small>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="message-text"
                                                                                class="col-form-label">Email</label>
                                                                            <input type="email" class="form-control"
                                                                                value="${item[4] }" name="email"
                                                                                required="required"
                                                                                id="email_gv_${item[0] }"> <small
                                                                                id="email_error_${item[0]}"
                                                                                style="color: red"></small>
                                                                        </div>
                                                                        
		                                                                <div class="form-group" id="content">
		                                                                    <label for="message-text"
		                                                                        class="col-form-label">Chuyên
		                                                                        ngành</label>
		                                                                    <select class="form-control" name="chuyen_nganh_id"
		                                                                        id="chuyen_nganh_${item[0] }">
		                                                                        
		                                                                        <c:forEach items="${chuyenNganh}" var="chuyenNganh">
												
																				<option value="${chuyenNganh[0]}"
																					${chuyenNganh[0]==sessionScope.ScopeCN_TKGV ?"selected":""}>
																					${chuyenNganh[1]}</option>
								
																			</c:forEach>
		
		                                                                    </select> <small class="text-danger"
		                                                                        id="chuyen_nganh_id_error"></small>
		                                                                </div>
                                                            
                                                                        

                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary"
                                                                            data-dismiss="modal">Hủy</button>
                                                                        <button type="submit"
                                                                            class="btn btn-primary">Sửa</button>

                                                                    </div>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal fade" id="modal_delete_${item[0] }" tabindex="-1"
                                                        aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog modal-lg">
                                                            <div class="modal-content">
                                                                <form method="POST" id="form_delete_gv_${item[0] }"
                                                                    action="${path}/staff/CNBM/quan-ly-giang-vien/delete/?idGv=${item[0] }">

                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="exampleModalLabel">
                                                                            Xóa giảng viên</h5>
                                                                        <button type="button" class="close"
                                                                            data-dismiss="modal" aria-label="Close">
                                                                            <span aria-hidden="true">&times;</span>
                                                                        </button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <h3>Xác nhận xóa giảng viên</h3>
                                                                        <div class="form-group">
                                                                            <label for="recipient-name"
                                                                                class="col-form-label">Họ
                                                                                và tên</label> <input type="text"
                                                                                class="form-control" disabled="disabled"
                                                                                value="${item[2] }"
                                                                                id="ten_gv_${item[0] }"> <small
                                                                                class="text-danger"
                                                                                id="name_error_${item[0] }"></small>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="message-text"
                                                                                class="col-form-label">Mã
                                                                                giảng viên</label> <input type="text"
                                                                                class="form-control" disabled="disabled"
                                                                                value="${item[1] }" name="ma_gv"
                                                                                id="ma_gv_${item[0] }">
                                                                            <small class="text-danger"
                                                                                id="ma_gv_error_${item[0] }"></small>
                                                                        </div>

                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary"
                                                                            data-dismiss="modal">Hủy</button>
                                                                        <button type="submit"
                                                                            class="btn btn-primary">Xóa</button>
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
                </div>
            </div>

            <script>
                function setSelected(event) {
                    const dot_id = event.target.value;
                    if (Number(dot_id) == 1) {
                        document.getElementById('chuyen_nganh_id').value = Person_ID;

                    }
                }

                function validate(id) {
                    var email = /^\S+@\S{3}\.\S{3}\.\S{2}$/;
                    
                    console.log(document.getElementById("ma_gv_" + id).value.trim());
                    if (document.getElementById("ma_gv_" + id).value.trim().length < 2) {
                        document.getElementById("ma_gv_error_" + id).innerHTML = "Mã giảng viên phải từ 2 kí tự trở nên";
                        return false;
                    } else {
                        document.getElementById("ma_gv_error_" + id).innerHTML = "";
                        
                    }
                    if (document.getElementById("ten_gv_" + id).value.trim().length < 5 || !isNaN(document.getElementById("ten_gv_" + id).value.trim())) {
                        document.getElementById("name_error_" + id).innerHTML = "Tên giảng viên phải dài hơn 5 kí tự và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {

                        document.getElementById("name_error_" + id).innerHTML = "";
                      

                    }
                    if (document.getElementById("sdt_gv_" + id).value.trim().length < 10 || !/^0\d{9}$/.test(document.getElementById("sdt_gv_" + id).value.trim())) {
                        document.getElementById("sdt_error_" + id).innerHTML = "Vui lòng nhập đúng định dạng SDT và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("sdt_error_" + id).innerHTML = "";
                        
                    }
					var email_gv =document.getElementById("email_gv_" + id).value.trim();
                    var fpt = email_gv.substring(email_gv.indexOf("@") + 1, email_gv.length).toUpperCase();

                    if (!email.test(document.getElementById("email_gv_" + id).value.trim()) || !fpt == "FPT.EDU.VN") {
                        document.getElementById("email_error_" + id).innerHTML = "Vui lòng nhập đúng định dạng email @fpt.edu.vn và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("email_error_" + id).innerHTML = "";
             
                    }
                    
                    return true;
                }
            </script>