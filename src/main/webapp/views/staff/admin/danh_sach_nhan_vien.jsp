<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <div>
                <div class="m-content">
                    <h3>Danh sách nhân viên</h3>

                    <div class="mt-5">

                        <section class="fillter-area  mb-5">
                            <div class="m-portlet">
                                <div class="m-portlet__head">
                                    <div class="m-portlet__head-caption">
                                        <div class="m-portlet__head-title">
                                            <span class="m-portlet__head-icon">
                                                <i class="m-menu__link-icon flaticon-web"></i>
                                            </span>
                                            <h3 class="m-portlet__head-text">
                                                Bộ lọc
                                            </h3>
                                        </div>
                                    </div>
                                </div>

                                <form action="${path}/staff/admin/quan-ly-nhan-vien/tim-kiem-nhan-vien" method="post" class="m-portlet__body">
                                    <div class="row">

                                        <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                            <span for="" class="fillter-name col-4 text-center">Chức vụ</span>
                                            <select class="form-control col-8 select2" name="role" id="role">
                                                <option value="-1" selected>Chọn chức vụ</option>

                                                <option value="0" ${0==sessionScope.ScopeRole_NV ?"selected":""}>Admin
                                                </option>
                                                <option value="1" ${1==sessionScope.ScopeRole_NV ?"selected":""}>Phòng
                                                    đào tạo</option>
                                                <option value="2" ${2==sessionScope.ScopeRole_NV ?"selected":""}>Chủ
                                                    nhiệm bộ môn</option>
                                                <option value="3" ${3==sessionScope.ScopeRole_NV ?"selected":""}>Giảng
                                                    viên</option>


                                            </select>
                                        </div>
                                        <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                            <span for="" class="fillter-name col-4 text-center">Cơ sở</span>
                                            <select class="form-control col-8 select2" name="coso" id="coso">
                                                <option value="0" selected>Chọn cơ sở</option>

                                                <c:forEach items="${CoSo}" var="CoSo">

                                                    <option value="${CoSo.id}" ${CoSo.id==sessionScope.ScopeCoSo_QLNV
                                                        ?"selected":""}>
                                                        ${CoSo.ten}</option>

                                                </c:forEach>


                                            </select>
                                        </div>
                                    </div>
                                    <div class="row justify-content-center pt-2">
                                        <div class="col-sm-2 col-12 text-center mt-2">
                                            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                                        </div>
                                        <div class="col-sm-2 col-12 text-center mt-2">
                                            <a href="${path}/staff/admin/quan-ly-nhan-vien" class="btn btn-danger text-white">
                                                Làm mới bộ lọc
                                            </a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </section>

                        <div class="row mb-5 bieumau">
                            <div class="col-6 col-sm-4 col-lg-4">
                                <a href="javascript:" data-toggle="modal" data-target="#import_dssv"> <i class="fa fa-download" aria-hidden="true"></i> Import danh sách nhân viên
                                </a>
                            </div>
                            <div class="col-6 col-sm-4 col-lg-4">
                                <a href="${path}/files/tham-khao-nv.xlsx"> <i class="fa fa-download" aria-hidden="true"></i> Tải mẫu Import
                                </a>
                            </div>
                            <div class="col-6 col-sm-4 col-lg-4">
                                <a href="javascript:" data-toggle="modal" data-target="#delete_dssv"> <i class="fa fa-trash" aria-hidden="true"></i> Xóa nhân viên
                                </a>
                            </div>
                        </div>

                        <form action="${path}/files/upload-addNV" enctype="multipart/form-data" id="form_import_dssv" method="POST">
                            <div class="modal fade" id="import_dssv" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Import Danh sách Nhân viên
                                            </h5>
                                            <button type="button" id="closeFileBieuMau" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">

                                            <div class="col-12">
                                                <label for="">Danh sách nhân viên</label> <input type="file" id="file_dsNv" name="file_dsNv" class="form-control">
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

                        <div class="modal fade" id="delete_dssv" tabindex="-1" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <form method="POST" id="form_delete_sv" action="${path}/files/delete-Nv"
                                        enctype="multipart/form-data">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Xóa Nhân
                                                Viên</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">

                                            <div class="col-12 mt-4">
                                                <label for="">Danh sách nhân viên</label> <input type="file" id="file_dsNv_delete" name="file_dsNv_delete" class="form-control">
                                                <small class="text-danger" id="file_ds_delete_error"></small>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                            <button type="submit" class="btn btn-danger">Xóa Nhân
                                                viên</button>
                                        </div>
                        </form>
                        </div>
                        </div>
                        </div>

                        <div class="m-portlet">

                            <div class="m-portlet__body">
                                <div class="row">
                                    <div class="col-6">
                                        <button data-toggle="modal" data-target="#modal_create" class="col-4 btn btn-success">Thêm Nhân viên</button>

                                        <div class="modal fade" id="modal_create" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog modal-lg">
                                                <div class="modal-content">
                                                    <form method="POST" id="form_create_sv" action="${path}/staff/admin/quan-ly-nhan-vien/insert" onsubmit="return validateNV()">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Thêm Nhân viên
                                                            </h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="form-group">
                                                                <label for="recipient-name" class="col-form-label">Họ
                                                                    và tên</label> <input type="text" class="form-control" id="ten_nv" name="ten_nv" required="required">
                                                                <small class="text-danger" id="name_error"></small>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="message-text" class="col-form-label">Mã
                                                                    Nhân viên</label> <input type="text" class="form-control" name="ma_nv" id="ma_nv" required="required">
                                                                <small class="text-danger" id="ma_nv_error"></small>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="message-text" class="col-form-label">Email FPT</label>
                                                                <input type="email" class="form-control" name="email" id="email_nv" required="required"> <small class="text-danger" id="email_error"></small>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="message-text" class="col-form-label">Email FE</label>
                                                                <input type="email" class="form-control" name="email_nv_fe" id="email_nv_fe" required="required"> <small class="text-danger" id="email_error_fe"></small>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="message-text" class="col-form-label">SĐT</label>
                                                                <input type="text" class="form-control" name="sdt" id="sdt" required="required"> <small class="text-danger" id="sdt_error"></small>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="message-text" class="col-form-label">Cơ sở</label>
                                                                <select class="form-control" name="coso_add" id="coso_add">
                                                                    <option value="0" selected>Chọn cơ sở</option>
                                                                    <c:forEach items="${CoSo}" var="CoSo">
                                                                        <option value="${CoSo.id}">${CoSo.ten}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                <br>
                                                                <label id="coso_error" style="color:red"></label>
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="message-text" class="col-form-label">Vai
                                                                    trò</label>
                                                                <select class="form-control" name="role_nv" onchange="changeRole(this)" id="exampleSelect1">
                                                                    <option value="-1" selected>Chọn chức vụ</option>

                                                                    <option value="0">Admin</option>
                                                                    <option value="1">Phòng đào tạo</option>
                                                                    <option value="2">Chủ nhiệm bộ môn</option>
                                                                    <option value="3">Giảng viên</option>
                                                                </select>
                                                                <br>
                                                                <label id="nv_role_error" style="color:red"></label>
                                                            </div>
                                                            <div id="chuyenNganhCNBM" style="display: none;">
                                                                <div class="form-group" id="content">
                                                                    <label for="message-text" class="col-form-label">Chuyên
                                                                        ngành(Chỉ dành cho Chủ nhiệm bộ môn)</label>
                                                                    <select class="form-control" name="chuyen_nganh_id" id="chuyen_nganh_id">
                                                                        <option value="0" disabled selected>Chọn chuyên
                                                                            ngành</option>
                                                                        <c:forEach var="tenChuyenNganh"
                                                                            items="${tenChuyenNganh }">
                                                                            <option value="${tenChuyenNganh.id}">
                                                                                ${tenChuyenNganh.ten }</option>
                                                                        </c:forEach>

                                                                    </select> <small class="text-danger" id="chuyen_nganh_id_error"></small>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                            <button type="submit" class="btn btn-primary">Thêm
                                                                Nhân viên</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-6 d-inline">
                                        <label for="">Tổng số nhân viên:</label>
                                        <h4 class="d-inline ml-4">${size}</h4>
                                    </div>
                                </div>

                                <table class="table table-bordered m-table d-sm-table m-table--head-bg-primary table-responsive">
                                    <thead>
                                        <th scope="col">STT</th>
                                        <th>Mã Nhân Viên</th>
                                        <th>Tên Nhân Viên</th>
                                        <th>SĐT</th>
                                        <th>Email FPT</th>
                                        <th>Email FE</th>
                                        <th>Role</th>
                                        <th>Chuyên Ngành</th>
                                        <th>Thao tác</th>
                                    </thead>

                                    <tbody>

                                        <c:forEach var="item" items="${NhanVien }" varStatus="loop">
                                            <tr>
                                                <td>${loop.count}</td>
                                                <!--  <td>${item[0] }</td>-->

                                                <td>${item[1] }</td>
                                                <td>${item[2] }</td>
                                                <td>${item[3] }</td>
                                                <td>${item[4] }</td>
                                                <td>${item[8] }</td>
                                                <td>${item[5] }</td>
                                                <td>${item[6] }</td>
                                                <td>
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal_edit_${item[0] }">
                                                        Sửa</button>
                                                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modal_delete_${item[0] }">
                                                        Xóa</button>
                                                </td>
                                                <div class="modal fade" id="modal_edit_${item[0] }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-lg">
                                                        <div class="modal-content">
                                                            <form method="POST" id="form_edit_nv_${item[0] }" onsubmit="return validateUpdate(${item[0] })" action="${path}/staff/admin/quan-ly-nhan-vien/edit/?idNv=${item[0] }">

                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalLabel">
                                                                        Chỉnh sửa thông tin nhân viên</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <label for="recipient-name" class="col-form-label">Họ
                                                                            và tên</label> <input type="text" class="form-control" value="${item[2] }" required="required" id="ten_nv_${item[0] }"
                                                                            name="ten_nv">
                                                                        <small class="text-danger" id="name_error_${item[0] }"></small>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Mã
                                                                            Nhân viên</label> <input type="text" class="form-control" value="${item[1] }" required="required" name="ma_nv"
                                                                            id="ma_nv_${item[0] }">
                                                                        <small class="text-danger" id="ma_nv_error_${item[0] }"></small>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Email</label>
                                                                        <input type="text" class="form-control" value="${item[4] }" name="email" id="email_nv_${item[0] }">
                                                                        <small class="text-danger" id="email_error_${item[0] }"></small>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Email FE</label>
                                                                        <input type="email" class="form-control" value="${item[8] }" name="email_nv_fe" id="email_nv_fe_${item[0]}" required="required">
                                                                        <small class="text-danger" id="email_error_fe_${item[0]}"></small>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">SĐT</label>
                                                                        <input type="text" class="form-control" value="${item[3] }" name="sdt" id="sdt_nv_${item[0] }">
                                                                        <small class="text-danger" id="sdt_error_${item[0] }"></small>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Cơ sở</label>
                                                                        <select class="form-control" name="coso_add" id="coso_nv_${item[0]}">
                                                                            <option value="0" >Chọn cơ sở</option>
                                                                            <c:forEach items="${CoSo}" var="CoSo">
                                                                                <option value="${CoSo.id}" ${CoSo.id==item[9] ?"selected":""}>${CoSo.ten}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                        <br>
                                                                        <label id="coso_error_${item[0]}" style="color:red"></label>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Vai trò</label>
                                                                        <select class="form-control" name="role_nv" onchange="changeRole(this)" id="role_nv_${item[0]}">
                                                                            <option value="${item[7] }" selected>
                                                                                ${item[5] }</option>
                                                                            <option value="0">Admin</option>
                                                                            <option value="1">Phòng đào tạo</option>
                                                                            <option value="2">Chủ nhiệm bộ môn</option>
                                                                            <option value="3">Giảng viên</option>

                                                                        </select> <br>
                                                                        <label id="role_error_${item[0]}" class="text-danger"></label>
                                                                    </div>
                                                                    <div id="chuyenNganhCNBM" style="display: none;">
                                                                        <div class="form-group" id="content">
                                                                            <label for="message-text" class="col-form-label">Chuyên
                                                                                ngành</label>
                                                                            <select class="form-control" name="chuyen_nganh_id" id="chuyen_nganh_id">
                                                                                <option value="0" disabled selected>Chọn
                                                                                    chuyên
                                                                                    ngành</option>
                                                                                <c:forEach var="tenChuyenNganh"
                                                                                    items="${tenChuyenNganh }">
                                                                                    <option
                                                                                        value="${tenChuyenNganh.id}">
                                                                                        ${tenChuyenNganh.ten }</option>
                                                                                </c:forEach>

                                                                            </select> <small class="text-danger" id="chuyen_nganh_id_error"></small>
                                                                        </div>
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
                                                            <form method="POST" id="form_delete_nv_${item[0] }" action="${path}/staff/admin/quan-ly-nhan-vien/delete/?idNv=${item[0] }">

                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalLabel">
                                                                        Xóa nhân viên</h5>
                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <label for="recipient-name" class="col-form-label">Họ
                                                                            và tên</label> <input type="text" disabled="disabled" class="form-control" value="${item[2] }" id="ten_nv_${item[0] }"
                                                                            name="ten_nv">
                                                                        <small class="text-danger" id="name_error_${item[0] }"></small>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Mã
                                                                            Nhân viên</label> <input type="text" class="form-control" disabled="disabled" value="${item[1] }" name="ma_nv"
                                                                            id="ma_nv_${item[0] }">
                                                                        <small class="text-danger" id="ma_nv_error_${item[0] }"></small>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Email</label>
                                                                        <input type="text" class="form-control" value="${item[4] }" disabled="disabled" name="email" id="email_nv_${item[0] }">
                                                                        <small class="text-danger" id="email_error_${item[0] }"></small>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">SĐT</label>
                                                                        <input type="text" class="form-control" value="${item[3] }" disabled="disabled" name="sdt" id="sdt_nv_${item[0] }">
                                                                        <small class="text-danger" id="email_error_${item[0] }"></small>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Vai trò</label>
                                                                        <select class="form-control" name="role_nv" id="role_nv" disabled="disabled">
                                                                            <option value="${item[7] }" selected>
                                                                                ${item[5] }</option>

                                                                            <option value="-1">Chọn chức vụ</option>
                                                                            <option value="0">Admin</option>
                                                                            <option value="1">Phòng đào tạo</option>
                                                                            <option value="2">Chủ nhiệm bộ môn</option>
                                                                            <option value="3">Giảng viên</option>

                                                                        </select> <br>
                                                                        <label id="role_error" style="color:red"></label>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label for="message-text" class="col-form-label">Chuyên
                                                                            ngành</label> <select class="form-control" name="chuyen_nganh_id" id="chuyen_nganh_id" disabled="disabled">
                                                                            <option value="${item[6] }" selected>
                                                                                ${item[6]}</option>
                                                                            <c:forEach var="tenChuyenNganh"
                                                                                items="${tenChuyenNganh }">
                                                                                <option value="${tenChuyenNganh.id }">
                                                                                    ${tenChuyenNganh.ten }</option>
                                                                            </c:forEach>

                                                                        </select> <small class="text-danger" id="chuyen_nganh_id_error"></small>
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
            </div>

            <script type="text/javascript">
                function validateNV() {

                    var email = document.getElementById("email_nv").value.trim();
                    var emailfe = document.getElementById("email_nv_fe").value.trim();

                    var emailTest = /^\S+@\S{3}\.\S{3}\.\S{2}$/;
                    var emailTest2 = /^\S+@\S{2}\.\S{3}\.\S{2}$/;
                    if (document.getElementById("ten_nv").value.trim().length < 5) {
                        document.getElementById("name_error").innerHTML = "Tên sinh nhân phải có độ dài lớn hơn 5 và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("name_error").innerHTML = "";
                        document.getElementById("ten_nv").innerHTML = document.getElementById("ten_nv").value.trim();
                    }
                    if (document.getElementById("ma_nv").value.trim().length < 7) {
                        document.getElementById("ma_nv_error").innerHTML = "Mã nhân viên phải có độ dài lớn hơn 6 và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("ma_nv_error").innerHTML = "";
                        document.getElementById("ma_nv").innerHTML = document.getElementById("ma_nv").value.trim();
                    }
                    var fpt = email.substring(email.indexOf("@") + 1, email.length).toUpperCase();

                    if (!emailTest.test(email) || !fpt == "FPT.EDU.VN") {
                        document.getElementById("email_error").innerHTML = "Vui lòng nhập đúng định dạng email @fpt.edu.vn và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("email_error").innerHTML = "";
                        document.getElementById("email_nv").innerHTML = document.getElementById("email_nv").value.trim();
                    }
                    var fe = email.substring(email.indexOf("@") + 1, email.length).toUpperCase();

                    if (!emailTest2.test(emailfe) || !fe == "FE.EDU.VN") {
                        document.getElementById("email_error_fe").innerHTML = "Vui lòng nhập đúng định dạng email @fe.edu.vn và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("email_error_fe").innerHTML = "";
                        document.getElementById("email_nv_fe").innerHTML = document.getElementById("email_nv_fe").value.trim();
                    }
                    var sdt = document.getElementById("sdt").value.trim();
                    if (sdt.length < 10 || !/^0\d{9}$/.test(sdt)) {
                        document.getElementById("sdt_error").innerHTML = "Vui lòng nhập đúng định dạng SDT và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("sdt_error").innerHTML = "";
                        document.getElementById("sdt").innerHTML = sdt;
                    }
                    var _coso = document.getElementById("coso_add").value;
                    if (_coso == '0') {
                        document.getElementById("coso_error").innerHTML = "Vui lòng chọn cơ sở";
                        return false;
                    } else {
                        document.getElementById("coso_error").innerHTML = "";
                    }

                    var _role = document.getElementById("exampleSelect1").value;
                    if (_role == '-1') {
                        document.getElementById("nv_role_error").innerHTML = "Vui lòng chọn vai trò";
                        return false;
                    } else {
                        document.getElementById("nv_role_error").innerHTML = "";
                    }

                    if (_role == '2') {
                        if (document.getElementById("chuyen_nganh_id").value == '0') {
                            document.getElementById("chuyen_nganh_id_error").innerHTML = "Vui lòng chọn chuyên ngành";
                            return false;
                        } else {
                            document.getElementById("chuyen_nganh_id_error").innerHTML = "";
                        }
                    }

                    return true;
                }

                function validateUpdate(id) {

                    var email = document.getElementById("email_nv_" + id).value.trim();
                    var emailfe = document.getElementById("email_nv_fe_" + id).value.trim();

                    var emailTest = /^\S+@\S{3}\.\S{3}\.\S{2}$/;
                    var emailTest2 = /^\S+@\S{2}\.\S{3}\.\S{2}$/;
                    if (document.getElementById("ten_nv_" + id).value.trim().length < 5) {
                        document.getElementById("name_error_" + id).innerHTML = "Tên nhân viên phải có độ dài lớn hơn 5 và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("name_error_" + id).innerHTML = "";
                        document.getElementById("ten_nv_" + id).innerHTML = document.getElementById("ten_nv_" + id).value.trim();
                    }
                    if (document.getElementById("ma_nv_" + id).value.trim().length < 7) {
                        document.getElementById("ma_nv_error_" + id).innerHTML = "Mã nhân viên phải có độ dài lớn hơn 6 và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("ma_nv_error_" + id).innerHTML = "";
                        document.getElementById("ma_nv_" + id).innerHTML = document.getElementById("ma_nv_" + id).value.trim();
                    }
                    var fpt = email.substring(email.indexOf("@") + 1, email.length).toUpperCase();

                    if (!emailTest.test(email) || !fpt == "FPT.EDU.VN") {
                        document.getElementById("email_error_" + id).innerHTML = "Vui lòng nhập đúng định dạng email @fpt.edu.vn và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("email_error_" + id).innerHTML = "";
                        document.getElementById("email_nv_" + id).innerHTML = document.getElementById("email_nv").value.trim();
                    }
                    var fe = email.substring(email.indexOf("@") + 1, email.length).toUpperCase();

                    if (!emailTest2.test(emailfe) || !fpt == "FE.EDU.VN") {
                        document.getElementById("email_error_fe_" + id).innerHTML = "Vui lòng nhập đúng định dạng email @fe.edu.vn và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("email_error_fe_" + id).innerHTML = "";
                        document.getElementById("email_nv_fe_" + id).innerHTML = document.getElementById("email_nv").value.trim();
                    }
                    var sdt = document.getElementById("sdt_nv_" + id).value.trim();
                    if (sdt.length < 10 || !/^0\d{9}$/.test(sdt)) {
                        document.getElementById("sdt_error_" + id).innerHTML = "Vui lòng nhập đúng định dạng SDT và không được có khoảng trắng ở đầu hoặc cuối";
                        return false;
                    } else {
                        document.getElementById("sdt_error_" + id).innerHTML = "";
                        document.getElementById("sdt_nv" + id).innerHTML = sdt;
                    }
                    var _cosoSua = document.getElementById("coso_nv_" + id).value;
                    if (_cosoSua == '0') {
                        document.getElementById("coso_error_" + id).innerHTML = "Vui lòng chọn cơ sở";
                        return false;
                    } else {
                        document.getElementById("coso_error_" + id).innerHTML = "";
                    }

                    var _role = document.getElementById("role_nv_" + id).value;
                    if (_role == '-1') {
                        document.getElementById("role_error_" + id).innerHTML = "Vui lòng chọn vai trò";
                        return false;
                    } else {
                        document.getElementById("role_error_" + id).innerHTML = "";
                    }

                    if (_role == '2') {
                        if (document.getElementById("chuyen_nganh_id_" + id).value == '0') {
                            document.getElementById("chuyen_nganh_id_error_" + id).innerHTML = "Vui lòng chọn chuyên ngành";
                            return false;
                        } else {
                            document.getElementById("chuyen_nganh_id_error_" + id).innerHTML = "";
                        }
                    }

                    return true;
                }

                function changeRole(obj) {
                    var _role = obj.value;
                    if (_role == '2') {
                        document.getElementById("chuyenNganhCNBM").style.display = 'block';
                    } else {
                        document.getElementById("chuyenNganhCNBM").style.display = 'none';

                    }
                }
            </script>