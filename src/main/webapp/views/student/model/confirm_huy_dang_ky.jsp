<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="modal fade" id="huy_dang_ky_du_thi_tot_nghiep" tabindex="-1" role="dialog" 
aria-labelledby="exampleModalLabel" data-controls-modal="your_div_id" data-backdrop="true" data-keyboard="true" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content minHeightLoading">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Bạn có chắc muốn hủy đăng ký tốt nghiệp?</h5>
                <button type="button" class="close" id="close_roi_nhom" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            </div>
            <form action="${path}/Student/huy-dang-ky" method="post">
                <div class="modal-footer" id="button_huy_dang_ky">
                    <button style="font-family: tahoma;" type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    <button style="font-family: tahoma;" type="submit" class="btn btn-primary">Đồng ý</button>
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