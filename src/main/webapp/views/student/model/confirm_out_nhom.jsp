<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<div class="modal fade" id="roi_nhom" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
data-controls-modal="your_div_id" data-backdrop="true" data-keyboard="true" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content minHeightLoading">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Bạn có chắc muốn rời nhóm?</h5>
                <button type="button" class="close" id="close_roi_nhom" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>
            <form action="${path}/Student/out-nhom" method ="post">
                <div class="modal-footer" id="button_roi_nhom">
                    <button style="font-family: tahoma;" type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    <button style="font-family: tahoma;" type="submit" class="btn btn-primary" fo>Đồng ý</button>
                </div>
                <div style="text-align: center" class="d-none boxloading" id="loading_out_nhom">
                    <p style="">
                        <b>Đang tải...</b>
                    </p>
                </div>
            </form>

        </div>
    </div>
</div>