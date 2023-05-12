<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div class="modal fade" id="dang_ky_du_thi_tot_nghiep" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
data-controls-modal="your_div_id" data-backdrop="true" data-keyboard="true" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content minHeightLoading">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel" style="font-family: tahoma">
                    Đăng ký dự án tốt nghiệp
                </h5>
                <button type="button" class="close" id="close_dang_ky" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form class="m-form m-form--label-align-right" action="${path}/Student/dang-ky" method="post">
                <div class="m-portlet__body container" style=" width: 80% " id="form_dang_ky">
                    <div class="m-form__section m-form__section--first ">
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Họ và tên
                            </label>
                            <div class="col-lg-9">
                                <input type="text" class="form-control m-input" value="${SinhVien.hoTen}" disabled />
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Mã SV:
                            </label>
                            <div class="col-lg-9">
                                <input type="text" class="form-control m-input" value="${SinhVien.maSv}" disabled />
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Email:
                            </label>
                            <div class="col-lg-9">
                                <input type="text" class="form-control m-input" value="${SinhVien.email}" disabled/>
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Chuyên ngành:
                            </label>
                            <div class="col-lg-9">
                                <input type="text" class="form-control m-input" value="${chuyenNganhSV}" disabled/>
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Khoá:
                            </label>
                            <div class="col-lg-9">
                                <input type="text" class="form-control m-input" value="${SinhVien.khoa}" disabled/>
                            </div>
                        </div>
                        <div class="form-group m-form__group row">
                            <label class="col-lg col-form-label">
                                Học kỳ:
                            </label>
                            <div class="col-lg-9">
                                <input type="text" class="form-control m-input" value="${SinhVien.hocKy}" disabled/>
                            </div>
                        </div>
                    </div>

                    <div style="text-align: center">
                        <button id="clickConfirm" class="btn btn-primary " style="font-family: tahoma;
                                                margin-bottom: 30px">
                            <b>Đăng ký dự án tốt nghiệp </b>
                        </button>
                       	<div id="confirm"></div>
                        <div id="alert"></div>
                    </div>
                </div>
                <div style="text-align: center" class="d-none boxloading" id="dang_ky_du_thi_tot_nghiep">
                    <p style="">
                        <b>Trạng thái</b>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>