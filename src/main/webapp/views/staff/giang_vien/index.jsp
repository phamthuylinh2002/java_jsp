<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div>
    <div class="m-content">
 
        <h3>Quản lý giảng viên</h3>
 
        <div class="mt-5">
            <section class="fillter-area  mb-5">
                <div class="m-portlet">
                    <div class="m-portlet__head">
                        <div class="m-portlet__head-caption">
                            <div class="m-portlet__head-title">
                                <span class="m-portlet__head-icon">
                                    <i class="m-menu__link-icon flaticon-web"></i>
                                </span>
                                <h3 class="m-portlet__head-text">
                                    Bộ lọc
                                </h3>
                            </div>
                        </div>
                    </div>
                    <form
                        action="{{ route('staff.du_an_tot_nghiep.giang_vien.index') }}"
                        method="get"
                        class="m-portlet__body">
                        <div class="row">
                            <div
                                class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                <span for="" class="fillter-name col-4 text-center">Từ khóa</span>
                                <input type="text" class="form-control col-8" name="keyword" id="keyword">
                            </div>
                            
                        </div>
 
                        <div class="row justify-content-center pt-2">
                            <div class="col-sm-2 col-12 text-center mt-2">
                                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                            </div>
                            <div class="col-sm-2 col-12 text-center mt-2">
                                <a href="{{ route('staff.du_an_tot_nghiep.giang_vien.index') }}"
                                    class="btn btn-danger text-white">
                                    Làm mới bộ lọc
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
 
            <div class="row mb-5 bieumau">
                <div class="col-6 col-sm-4 col-lg-4">
                    <a
                        href="javascript:"
                        data-toggle="modal"
                        data-target="#import_dssv">
                        <i class="fa fa-download" aria-hidden="true"></i>
                        Import danh sách giảng viên
                    </a>
                </div>
                <div class="col-6 col-sm-4 col-lg-4">
                    <a href="{{ route('staff.du_an_tot_nghiep.giang_vien.import_template') }}">
                        <i class="fa fa-download" aria-hidden="true"></i>
                        Tải mẫu Import
                    </a>
                </div>
                <div class="col-6 col-sm-4 col-lg-4">
                    <a
                        href="javascript:"
                        data-toggle="modal"
                        data-target="#delete_dsgv">
                        <i class="fa fa-trash" aria-hidden="true"></i>
                        Xóa sinh viên
                    </a>
                </div>
            </div>
 
            <form
                action="{{ route('staff.du_an_tot_nghiep.giang_vien.import') }}"
                enctype="multipart/form-data"
                id="form_import_dsgv"
                method="POST">

                <div
                    class="modal fade"
                    id="import_dssv"
                    tabindex="-1"
                    role="dialog"
                    aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Import Danh sách giảng viên</h5>
                                <button type="button" id="closeFileBieuMau" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="col-12">
                                    <label for="">Danh sách giảng viên</label>
                                    <input
                                        type="file"
                                        id="file_dsgv"
                                        name="file_dsgv"
                                        class="form-control">
                                    <small class="text-danger" id="file_dsgv_error"></small>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                <button type="submit" class="btn btn-primary">Tải</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
 
            <div
                class="modal fade"
                id="delete_dsgv"
                tabindex="-1"
                aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <form
                            method="POST"
                            id="form_delete_gv"
                            action="{{ route('staff.du_an_tot_nghiep.giang_vien.remove_excel') }}">
                        
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Xóa Giảng Viên</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="col-12 mt-4">
                                    <label for="">Danh sách giảng viên</label>
                                    <input
                                        type="file"
                                        id="file_ds_delete"
                                        name="file_ds_delete"
                                        class="form-control">
                                    <small class="text-danger" id="file_ds_delete_error"></small>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                <button type="submit" class="btn btn-danger">Xóa giảng viên</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
 
            <div class="m-portlet">
 
                <div class="m-portlet">
 
                    <div class="m-portlet__body">
 
                        {{-- <div class="row">
                            <div class="col-6">
                                <button
                                    data-toggle="modal"
                                    data-target="#modal_create"
                                    class="col-4 btn btn-success">
                                    Thêm giảng viên
                                </button>
 
                                <div
                                    class="modal fade"
                                    id="modal_create"
                                    tabindex="-1"
                                    aria-labelledby="exampleModalLabel"
                                    aria-hidden="true">
                                    <div class="modal-dialog modal-lg">
                                        <div class="modal-content">
                                            <form
                                                method="POST"
                                                id="form_create_sv"
                                                action="{{ route('staff.du_an_tot_nghiep.sinh_vien.store') }}">
                                         
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Thêm sinh viên</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-group">
                                                        <label for="recipient-name" class="col-form-label">Họ và tên</label>
                                                        <input
                                                            type="text"
                                                            class="form-control"
                                                            id="ten_sv">
 
                                                        <small class="text-danger" id="name_error"></small>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="message-text" class="col-form-label">Mã sinh viên</label>
                                                        <input
                                                            type="text"
                                                            class="form-control"
                                                            name="ma_sv"
                                                            id="ma_sv">
 
                                                        <small class="text-danger" id="ma_sv_error"></small>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="message-text" class="col-form-label">Email</label>
                                                        <input
                                                            type="text"
                                                            class="form-control"
                                                            name="email"
                                                            id="email_sv">
 
                                                        <small class="text-danger" id="email_error"></small>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="message-text" class="col-form-label">Khóa</label>
                                                        <select class="form-control" name="khoa" id="sv_khoa">
                                                            <option value="0" disabled selected>Chọn khóa</option>
                                                          
                                                            <option value="{{ khoa->khoa }}">{{ khoa->khoa }}</option>
                                                      
                                                        </select>
                                                        <small class="text-danger" id="khoa_error"></small>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="message-text" class="col-form-label">Chuyên ngành</label>
                                                        <select class="form-control" name="chuyen_nganh_id" id="sv_chuyen_nganh">
                                                            <option value="0" disabled selected>Chọn chuyên ngành</option>
                                                          
                                                            <option value="{{ chuyenNganh->id }}">
                                                                {{ chuyenNganh->ten_chuyen_nganh }}
                                                            </option>
                                                         
                                                        </select>
                                                        <small class="text-danger" id="chuyen_nganh_id_error"></small>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="message-text" class="col-form-label d-block">Đợt Đăng Ký</label>
                                                        <select
                                                            class="form-control select2"
                                                            name="dot_id"
                                                            style="width: 100%;"
                                                            id="dot_id">
                                                            <option value="{{ dot->id }}">
                                                                {{ dot->ten_dot }}
                                                            </option>
                                                        </select>
                                                        <small class="text-danger" id="dot_id_error"></small>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="message-text" class="col-form-label">Trạng thái</label>
                                                        <select class="form-control" name="khoa" id="sv_trang_thai">
                                                            <option value="{{ config('common.status.inactive') }}">
                                                                Chưa đăng ký
                                                            </option>
                                                            <option value="{{ config('common.status.active') }}">
                                                                Đăng ký
                                                            </option>
                                                        </select>
                                                        <small class="text-danger" id="trang_thai_error"></small>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                    <button type="submit" class="btn btn-primary">Thêm sinh viên</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
 
                            </div>
                            <div class="col-6 d-inline">
                                <div class="col-12 form-group m-form__group d-flex justify-content-end">
                                    <label class="col-3 col-form-label">Kích thước:</label>
                                    <div class="col-4">
                                        <select class="form-control" id="page_size">
                                          
                                            <option value="{{size}}">{{size}}</option>
                                        
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div> --}}
 
                        <table
                            class="table table-bordered m-table d-sm-table m-table--head-bg-primary table-responsive">
                            <thead>
                                <th scope="col">STT</th>
                                <th>Giảng Viên</th>
                                <th>Email</th>
                                <th>SĐT</th>
                                <th>Chuyên Ngành</th>
                                <th>Thao tác</th>
                            </thead>
 
                            <tbody>
                               
                                <tr>
                                    {{-- <input
                                        type="hidden"
                                        id="url_get_info_sv_{{ item->id }}"
                                        value="{{ route('staff.du_an_tot_nghiep.sinh_vien.show', [
                                            'user' => item->id,
                                        ]) }}"> --}}
                                    <td>{{ i++ }}</td>
                                    <td>{{ item->name }}</td>
                                    <td>{{ item->email }}</td>
                                    <td>{{ item->sdt }}</td>
                                    <td>{{ item->chuyenNganh->ten_chuyen_nganh }}</td>
                                    <td>
                                        <button
                                            type="button"
                                            class="btn btn-primary"
                                            data-toggle="modal"
                                            data-target="#modal_edit_{{item->id}}">
                                            Sửa
                                        </button>
                                    </td>
 
                                    {{-- <div
                                        class="modal fade"
                                        id="modal_edit_{{item->id}}"
                                        tabindex="-1"
                                        aria-labelledby="exampleModalLabel"
                                        aria-hidden="true">
                                        <div class="modal-dialog modal-lg">
                                            <div class="modal-content">
                                                <form
                                                    method="POST"
                                                    id="form_edit_sv_{{ item->id }}"
                                                    action="{{ route('staff.du_an_tot_nghiep.sinh_vien.update', [
                                                        'user' => item->id
                                                    ]) }}">
                                               
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Chỉnh sửa thông tin sinh viên</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
 
                                                        <div class="form-group">
                                                            <label for="recipient-name" class="col-form-label">Họ và tên</label>
                                                            <input
                                                                type="text"
                                                                class="form-control"
                                                                value="{{ item->name }}"
                                                                id="ten_sv_{{ item->id }}">
                                                                <small class="text-danger" id="name_error_{{ item->id }}"></small>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="message-text" class="col-form-label">Mã sinh viên</label>
                                                            <input
                                                                type="text"
                                                                class="form-control"
                                                                value="{{ item->ma_sv }}"
                                                                name="ma_sv"
                                                                id="ma_sv_{{ item->id }}">
                                                                <small class="text-danger" id="ma_sv_error_{{ item->id }}"></small>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="message-text" class="col-form-label">Email</label>
                                                            <input
                                                                type="text"
                                                                class="form-control"
                                                                value="{{ item->email }}"
                                                                name="email"
                                                                id="email_sv_{{ item->id }}">
                                                                <small class="text-danger" id="email_error_{{ item->id }}"></small>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="message-text" class="col-form-label">Khóa</label>
                                                            <select class="form-control" name="khoa" id="sv_khoa_{{ item->id }}">
                                                                <option value="{{ item->khoa }}" selected>{{ item->khoa }}</option>
                                                             
                                                                <option value="{{ khoa->khoa }}">{{ khoa->khoa }}</option>
                                                        
                                                            </select>
                                                            <small class="text-danger" id="khoa_error_{{ item->id }}"></small>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="message-text" class="col-form-label">Chuyên ngành</label>
                                                            <select class="form-control" name="chuyen_nganh_id" id="sv_chuyen_nganh_{{ item->id }}">
                                                                <option  value="{{ item->chuyen_nganh_id }}" selected>{{ item->chuyenNganh->ten_chuyen_nganh }}</option>
                                                             
                                                                <option value="{{ chuyenNganh->id }}">
                                                                    {{ chuyenNganh->ten_chuyen_nganh }}
                                                                </option>
                                                                
                                                            </select>
                                                            <small class="text-danger" id="chuyen_nganh_id_error_{{ item->id }}"></small>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                                        <button type="submit" class="btn btn-primary">Sửa</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </tr>
                               
                            </tbody>
                        </table>
                    </div>
 
                </div>
                <div class="m-portlet__foot d-flex justify-content-end">
                        {{-- {{ data->links() }} --}}
                </div>
            </div>
        </div>
    </div>
</div>



