<%@ page language="java" contentType="text/html; charset=uft-8"
    pageEncoding="utf-8"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="cap_nhat_nhom" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
aria-hidden="true" data-controls-modal="your_div_id" data-backdrop="true" data-keyboard="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content minHeightLoading">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel" style="font-family: tahoma">
                    Cập nhật nhóm
                </h5>
                <button type="button" class="close" id="close_cap_nhat_nhom" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form id="capNhatNhom" method="post" class="m-form m-form--label-align-right"
            onsubmit="return validate(${ErrorCT})"  action="${path}/Student/update-team"> 
                <div id="error" class="d-none text-danger text-center mt-4"></div>
                <div class="m-portlet__body container" style=" width: 80% " id="modal_cap_nhat_nhom">
                    <div class="m-form__section m-form__section--first ">
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Tên đề tài
                            </label>
                            <div class="col-lg-9">
                                <input type="text" id="ten_de_tai" name="ten_de_tai" class="form-control m-input" value="${tenDeTai }" required="required" maxlength="255" />
                            
                                <small id="ten_de_tai_error" class="text-danger"></small>
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Giảng viên hướng dẫn
                            </label>
                            <div class="col-lg-9">
                                <select id="giang_vien_id" class="form-control m-input" name="giang_vien_id" >
                                		<c:forEach items="${gv }" var="gv"> 
											<option value="${gv[0]}" 
											${gv[0]==sessionScope.ScopeGV ?"selected":""}>${gv[1]}</option>
										</c:forEach>
                                </select>
                                <small id="giang_vien_id_error" class="d-none text-danger"></small>
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Hiển thị
                            </label>
                            <div class="col-lg-9">
                                <select name="is_public" class="form-control m-input" id="is_public">
                                    <option value="0" selected >
                                        Nhóm kín
                                    </option>
                                    <option value="1" ${1==sesionScope.ScopeTT?"selected":"" }>
                                        Công khai
                                    </option>
                                </select>
                                <small id="is_public_error" class="d-none text-danger"></small>
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
 <script type="text/javascript">
	function validate(perror){
		console.log(${ErrorCT});
		console.log(perror);
		if (perror !=null || perror =='no'){
			alert(perror);
			return false;
		}
		return true;
	}
</script>
 