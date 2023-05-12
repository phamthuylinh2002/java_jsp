<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <div class="modal fade" id="tao_nhom" tabIndex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-controls-modal="your_div_id" data-backdrop="true" data-keyboard="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel" style="font-family: tahoma">Tạo nhóm</h5>

                        <button id="close" type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
                    </div>
                    <div class="minHeightLoading">
                        <form class="m-form m-form--label-align-right" action="${path}/Student/tao-nhom" method="post" onsubmit="return validate()">
                            <div class="m-portlet__body container " id="modal_tao_nhom" style="width: 80%">
                                <p class="text-danger text-center">
                                    Với những nhóm có 1 thành viên sẽ hủy sau 1 ngày,<br> 2 thành viên nhóm sẽ bị hủy sau 2 ngày<br> và 3 thành viên sẽ bị hủy sau 3 ngày kể từ ngày đăng ký. <br>Thời gian hủy nhóm tiến hành vào lúc 23h55 của ngày được
                                    hủy.
                                </p>
                                <div class="m-form__section m-form__section--first ">
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg col-form-label"> Tên Đề tài: </label>
                                        <div class="col-lg-9">
                                            <input name="ten_de_tai" type="text" id="ten_de_tai" class="form-control m-input" maxlength="255" required="required" />
                                            <small id="ten_de_tai_error" class="text-danger"></small>
                                        </div>
                                    </div>
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg col-form-label"> Trưởng nhóm: </label>
                                        <div class="col-lg-9">
                                            <input type="text" value="${sv.hoTen }" class="form-control m-input" disabled />
                                        </div>
                                    </div>
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg col-form-label"> Mã sinh viên: </label>
                                        <div class="col-lg-9">
                                            <input value="${sv.maSv }" type="text" disabled class="form-control m-input" />
                                        </div>
                                    </div>
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg col-form-label"> Chuyên ngành: </label>
                                        <div class="col-lg-9">
                                            <input value="${chuyenNganhSV }" type="text" disabled class="form-control m-input" />
                                        </div>
                                    </div>
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg col-form-label"> Khóa: </label>
                                        <div class="col-lg-9">
                                            <input value="${sv.khoa}" type="text" disabled class="form-control m-input" />
                                        </div>
                                    </div>
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg col-form-label"> Giảng viên: </label>
                                        <div class="col-lg-9">
                                            <select name="giang_vien_id" class="form-control m-input" id="exampleSelect1">
										<option value="0">Chọn giảng viên</option> 
										<c:forEach items="${gv }" var="gv"> 
											<option value="${gv[0]}">${gv[1]}</option>
										</c:forEach>
										
									
									</select> 
                                            <label id="giang_vien_id_error" class="text-danger"></label>
                                        </div>
                                    </div>
                                    <div class="form-group m-form__group row">
                                        <label class="col-lg col-form-label"> Hiển thị </label>
                                        <div class="col-lg-9">
                                            <select name="is_public" class="form-control m-input" id="exampleSelect1">
										<option selected value="0">
											Nhóm kín</option>
										<option value="1">
											Công khai</option>
									</select> <small id="is_public_error" class="d-none text-danger"></small>
                                        </div>
                                    </div>
                                </div>

                                <div style="text-align: center">
                                    <button class="btn btn-primary" style="font-family: tahoma; margin-bottom: 30px">
								<b>Tạo nhóm</b>
							</button>
                                </div>
                            </div>
                            <div style="text-align: center" class="d-none boxloading" id="loadingTaoNhom">
                                <p style="">
                                    <b>Đang tải...</b>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function validate() {
            	
                if (document.getElementById("ten_de_tai").value.trim().length < 1) {
                    document.getElementById("ten_de_tai_error").innerHTML = "Mời nhập tên đề tài và không được có khoảng trắng đầu hoặc cuối chuỗi";
                    return false;
                } else {
                    document.getElementById("ten_de_tai_error").innerHTML = "";
                    document.getElementById("ten_de_tai").innerHTML=document.getElementById("ten_de_tai").value.trim();
                }
                
                if (document.getElementById('exampleSelect1').value == '0') {
                    document.getElementById("giang_vien_id_error").innerHTML = "Mời chọn giảng viên";
                    return false;
                } else {
                    document.getElementById("giang_vien_id_error").innerHTML = '';
                }
                
                console.log(${ErrorCT});
                if(perror !== null && perror !== '' || perror === undefined ) {
                	alert("Tạo nhóm không thành công");
                	return false;
                	}

                return true;
            }
        </script>