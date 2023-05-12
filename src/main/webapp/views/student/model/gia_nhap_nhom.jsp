<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<div class="modal fade" id="vao_nhom_co_san" tabIndex="-1" role="dialog" 
aria-labelledby="exampleModalLabel" aria-hidden="true" data-controls-modal="your_div_id" data-backdrop="true" data-keyboard="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel" style=" font-family: tahoma">
                    Gia nhập nhóm có sẵn
                </h5>
                <button type="button" class="close" id="closeJoinNhom" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="minHeightLoading">
                <form id="formJoinNhom" action="${path}/Student/join-private" method="post" class="m-form m-form--label-align-right mt-3">
                    <div class="m-portlet__body container" style="width: 80%">
                        <div class="m-form__section m-form__section--first " id="formCode">
                            <div class="form-group m-form__group row">
                                <label class="col-lg col-form-label">
                                Mã code:
                            </label>
                                <div class="col-lg-9">
                                    <input type="text" id="code_join" name="code_join" class="form-control m-input" required="required" />
                                    <small id="code_error" class="text-danger"></small>
                                </div>
                            </div>
                        </div>
                        <div style="text-align: center" class="d-none boxloading" id="loading">
                            <p style="">
                                <b>Đang tải...</b>
                            </p>
                        </div>
                        <div style="text-align: center" id="buttonJoinNhom">
                            <button data-toggle="modal" class="btn btn-primary" id="test" style="font-family: tahoma; margin-bottom: 30px">
                            Tham gia
                        </button>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>

<script src="${path}/views/js/tham_gia_nhom_private.js"></script>
