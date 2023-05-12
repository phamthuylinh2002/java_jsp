<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div
    class="modal fade"
    id="modal_join"
    tabindex="-1"
    aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form
                method="POST"
                id="form_join_nhom_sv"
                action="">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ghép nhóm DATN</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <input type="hidden" id="search_nhom_url" value="{{ route('staff.du_an_tot_nghiep.nhom.search') }}">
                <input type="hidden" id="ghep_nhom_url" value="{{ route('staff.du_an_tot_nghiep.nhom.ghep_nhom') }}">
                <input type="hidden" id="chi_tiet_nhom_url" value="{{ route('staff.du_an_tot_nghiep.chi_tiet_nhom', [
                    'nhom' => '000',
                ]) }}">
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-6">
                            <label for="message-text" class="col-form-label ml-3">Tìm kiếm</label>
                            <div class="col-12">
                                <select
                                    class="form-control select2"
                                    name="search_nhom"
                                    id="search_nhom">
                                    <option value="0">Tìm kiếm nhóm</option>
                                </select>
                                <small class="text-danger" id="nhom_id_error"></small>
                            </div>
                        </div>
                        <div class="form-group col-6">
                            <label for="message-text" class="col-form-label">Đợt DATN</label>
                            <select
                                class="form-control select2"
                                name="dot_id"
                                id="dot_id">
                                <option value="{{ dot_id }}">
                                    {{ dot->ten_dot }}
                                </option>
                            </select>
 
                            <small class="text-danger" id="dot_id_error"></small>
                        </div>
                    </div>
 
                    <div class="m-portlet">
                        <div class="col-12 form-group m-form__group">
                            <h3 class="pt-4">Danh sách sinh viên đã chọn</h3>
                            <small class="text-danger" id="users_error"></small>
                        </div>
 
                        <div class="m-portlet__body table-scrollable">
                            <table class="table d-sm-table table-bordered table-scrollable m-table m-table--head-bg-primary table-responsive">
                                <thead>
                                    <tr>
                                        <th>Mã Sinh viên</th>
                                        <th>Họ và tên</th>
                                        <th>Khóa</th>
                                        <th>Chuyên ngành</th>
                                        <th>Nhóm trưởng</th>
                                    </tr>
                                </thead>
 
                                <tbody id="list_sv_chon">
                                </tbody>
                            </table>
                        </div>
                    </div>
 
                    <div class="m-portlet">
                        <div class="col-12 form-group m-form__group">
                            <h3 class="pt-4">Danh sách thành viên nhóm</h3>
                        </div>
 
                        <div class="col-12 form-group m-form__group">
                            <div class="row">
                                <p class="col-3">Tên đề tài: </p>
                                <p class="col-9 font-weight-bold" id="chon_nhom_ten_de_tai"></p>
                            </div>
                            <div class="row">
                                <p class="col-3">Giảng viên hướng dẫn: </p>
                                <p class="col-9 font-weight-bold" id="chon_nhom_gv"></p>
                            </div>
                        </div>
 
                        <div class="m-portlet__body table-scrollable">
                            <table class="table d-sm-table table-bordered table-scrollable m-table m-table--head-bg-primary table-responsive">
                                <thead>
                                    <tr>
                                        <th>Mã Sinh viên</th>
                                        <th>Họ và tên</th>
                                        <th>Khóa</th>
                                        <th>Chuyên ngành</th>
                                        <th>Nhóm trưởng</th>
                                    </tr>
                                </thead>
 
                                <tbody id="list_tv_nhom">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">Ghép nhóm</button>
                </div>
            </form>
        </div>
    </div>
</div>
<style>
    .select2 {
        width: 100% !important;
    }
</style>
 

