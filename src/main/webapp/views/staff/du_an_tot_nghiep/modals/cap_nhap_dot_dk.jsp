<%@ page language="java" contentType="text/html; charset=uft-8"
    pageEncoding="utf-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="cap_nhat_dot_dang_ky${param.dot_id }" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
aria-hidden="true" data-controls-modal="your_div_id" data-backdrop="true" data-keyboard="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content minHeightLoading">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel" style="font-family: tahoma">
                    Cập nhật đợt đăng ký
                </h5>
                <button type="button" class="close" id="close_cap_nhat_nhom" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form id="capNhatNhom" method="post" class="m-form m-form--label-align-right"  action="${path}/staff/PDT/dot-dang-ky/update?dotdk=${param.dot_id}"> 
                <div id="error" class="d-none text-danger text-center mt-4"></div>
                <div class="m-portlet__body container" style=" width: 80% " id="modal_cap_nhat_nhom">
                    <div class="m-form__section m-form__section--first ">
                    	<div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                ID
                            </label>
                            <div class="col-lg-9">
                                <input type="text" id="dotid" name="dotid" class="form-control m-input" value="${param.dot_id }" disabled="" />
                            
                                <small id="ten_de_tai_error" class="text-danger"></small>
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Tên đợt đăng ký
                            </label>
                            <div class="col-lg-9">
                                <input type="text" id="ten_dot_dk" name="ten_dot_dk" class="form-control m-input" value="${param.ten }" required="required" maxlength="255" />
                            
                                <small id="ten_dot_dk_error" class="text-danger"></small>
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Thời gian bắt đầu
                            </label>
                            <div class="col-lg-9">
                                <input type="date" id="thoi_gian_bat_dau" name="thoi_gian_bat_dau" class="form-control m-input"  required="required" />
                                <small id="giang_vien_id_error" class="d-none text-danger"></small>
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Thời gian kết thúc
                            </label>
                            <div class="col-lg-9">
                                <input type="date" id="thoi_gian_ket_thuc" name="thoi_gian_ket_thuc" class="form-control m-input"  required="required" />
                                <small id="giang_vien_id_error" class="d-none text-danger"></small>
                            </div>
                        </div>
                    </div>

                    <div style="text-align: center">
                        <button type="submit" class="btn btn-primary " style="font-family: tahoma;
                                                margin-bottom: 30px">
                            <b>Cập nhật</b>
                        </button>
                    </div>
                </div>
                <div style="text-align: center" class="d-none boxloading" id="loading_cap_nhat_nhom">
                    <p style="">
                        <b>Đang tải...</b>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- <script type="text/javascript">
	function validate(){
		console.log(document.getElementById("ten_de_tai").value);
		if(document.getElementById("ten_de_tai").value.trim().length < 1){
			document.getElementById("ten_de_tai_error").innerHTML="Mời nhập tên đề tài và không được có khoảng trắng đầu hoặc cuối chuỗi";
			return false;
		}else{
			document.getElementById("ten_de_tai_error").innerHTML="";
		}
		return true;
	}
</script>
 -->