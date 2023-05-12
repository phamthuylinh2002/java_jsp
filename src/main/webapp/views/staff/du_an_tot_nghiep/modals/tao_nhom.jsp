<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div
    class="modal fade"
    id="modal_create"
    tabindex="-1"
    aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form
                method="POST"
                id="form_create_sv"
                action="{{ route('staff.du_an_tot_nghiep.tao_nhom') }}">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Tạo nhóm DATN</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group col-6">
                            <label for="message-text" class="col-form-label">Tên đề tài</label>
                            <input
                                type="text"
                                class="form-control"
                                name="ten_de_tai"
                                id="ten_de_tai">
 
                            <small class="text-danger" id="ten_de_tai_error"></small>
                        </div>
                        <div class="form-group col-6">
                            <label for="message-text" class="col-form-label">
                                Giảng viên hướng dẫn
                            </label>
                            <select
                                    name="giang_vien_id"
                                    class="form-control m-input"
                                    id="giang_vien_id">
                                <option>Chọn giảng viên</option>

                                <option value="{{ gv->id }}">{{ gv->name }}</option>
                                
                            </select>
 
                            <small class="text-danger" id="giang_vien_id_error"></small>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-6">
                            <label for="message-text" class="col-form-label">Đợt DATN</label>
                            <select
                                class="form-control select2"
                                name="dot_id"
                                id="dot_id">
                                <option value="{{ $dot->id }}">
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
                            <small class="text-danger" id="danh_sach_thanh_vien_error"></small>
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
 
                                <tbody id="list_thanh_vien">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary">Tạo nhóm</button>
                </div>
            </form>
        </div>
    </div>
</div>
 