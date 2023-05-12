<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div class="modal fade" id="gia_nhap_nhom_${param.id}" tabIndex="-1" role="dialog" 
aria-labelledby="exampleModalLabel" data-controls-modal="your_div_id" data-backdrop="true" data-keyboard="true" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content minHeightLoading">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel" style=" font-family: tahoma ">
                    Gia nhập nhóm
                </h5>

                <button type="button" class="close" id="close_tham_gia_nhom_public" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <form style="min-height:200px " action="${path}/Student/join-public?nhom_id=${param.id }"  method="post" class="m-form m-form--label-align-right mt-3">
                <div style="text-align: center">
                    <small id="join_nhom_public_error" class="hidden"></small>
                </div>
                <div class="m-portlet__body container" style=" width: 80%;min-height:200px " id="button_tham_gia_nhom_public">
                    <div class="m-form__section m-form__section--first ">
                        <div class="form-group m-form__group row">
                            <label style="text-align: center" class="col-lg col-form-label">
                                Bạn có chắc muốn tham gia nhóm này ?
                            </label>
                        </div>
                    </div>
                    <div style="text-align: center">
                        <button class="btn btn-primary" style="font-family: tahoma; margin-bottom: 30px">
                            Tham gia
                        </button>
                    </div>

                </div>
                <div style="text-align: center" class="d-none boxloading" id="loading_join_nhom_public">
                    <p style="">
                        <b>Đang tải...</b>
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>