<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
    <div class="m-content">
        <h3>Chia nhóm DATN</h3>
 
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
 
                    <form action="{{ route('staff.du_an_tot_nghiep.chia_nhom') }}" method="get" class="m-portlet__body">
                        <div class="row">
                            <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                <span for="" class="fillter-name col-4 text-center">Chuyên ngành</span>
                                <select class="form-control d-inline col-8 select2" name="chuyen_nganh_id" id="chuyen_nganh_id">
                                    
                                       <c:forEach items="${chuyenNganh}" var="chuyenNganh">
                                        	<option value="${chuyenNganh[0]}">${chuyenNganh[1]}</option>	
                                        </c:forEach>
                                    
                                </select>
                            </div>
 
                            <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                <span for="" class="fillter-name col-4 text-center">Đợt Đăng Ký</span>
                                <select class="form-control d-inline col-8 select2" name="dot_id" id="dot_id">
                                    <c:forEach items="${dotdk}" var="dotdk">
                                        	<option value="${dotdk[0]}">${dotdk[1]}</option>
                                        	<!-- dotdk[0] id dợt dk
                                        		dotdk[1] học kỳ
                                        		dotdk[2] id cơ sở
                                        		dotdk[3] tên cơ sở
                                        	  -->
                                        </c:forEach>
                                </select>
                            </div>
                        </div>
 
                        <div class="row justify-content-center pt-2">
                            <div class="col-sm-2 col-12 text-center mt-2">
                                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                            </div>
                            <div class="col-sm-2 col-12 text-center mt-2">
                                <a
                                    href="{{ route('staff.du_an_tot_nghiep.chia_nhom') }}"
                                    class="btn btn-danger text-white"
                                    >
                                    Làm mới bộ lọc
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
 
            <div class="m-portlet">
                <div class="m-portlet__body table-scrollable">
                	<div class="row">
                        <div class="col-6">
                            <label for="">Tổng số sinh viên đã đăng ký nhưng chưa có nhóm:</label>
                            <h4 class="d-inline ml-4">${tongSVDaDKChuaNhom}</h4>
                        </div>
                        <div class="col-6 form-group m-form__group d-flex justify-content-end">
                            <label class="col-3 col-form-label">Kích thước:</label>
                            <div class="col-4">
                                <select class="form-control" id="page_size">
                               
                                    <option value="{{size}}">{{size}}</option>
                                  
                                </select>
                            </div>
                        </div>
                    </div>
                    
 
                    <table class="table d-sm-table table-bordered table-scrollable m-table m-table--head-bg-primary table-responsive">
                        <thead>
                            <th scope="col">STT</th>
                            <th>Mã Sinh Viên</th>
                            <th>Tên Sinh Viên</th>
                            <th>Email</th>
                            <th>Khóa</th>
                            <th>Chuyên Ngành</th>
                        </thead>
 
                        <tbody>
                         
                            <c:forEach var="item" items="${sinhVienDaDKChuaNhom }" varStatus="loop">
									<tr>
										<td>${loop.count}</td>
                                        <!--  <td>${item[0] }</td>-->
										<td>${item[1] }</td><!-- ma -->
										<td>${item[2] }</td><!-- hoten -->
										<td>${item[3] }</td><!-- email -->
										<td>${item[4] }</td><!-- khoa-->
										<td>${item[5] }</td><!-- chuyen nganh-->
										<td><input
                                        type="checkbox"
                                        name="check_sv_${item[0] }"
                                        id="check_sv_${item[0] }">
                                		</td>
									</tr>
								</c:forEach>
                          
                        </tbody>

                        </tbody>
                    </table>
                </div>
                <div class="m-portlet__foot row">
                    <div class="d-inline col-6">
                        {{ data->links() }}
                    </div>
                </div>
            </div>
 
            <div class="m-portlet">
 
                <div class="row pt-4 pl-4">
                    <div class="col-6 form-group m-form__group">
                        <h3 class="">Danh sách sinh viên đã chọn</h3>
                    </div>
                    <div class="col-6 form-group m-form__group">
                        <form action="{{ route('staff.du_an_tot_nghiep.sinh_vien.search') }}" id="form_search" method="get">
                            <label class="col-3">Tìm kiếm</label>
                            <select type="text" class="form-control col-8 d-inline select2" name="keyword" id="keyword">
                            </select>
                        </form>
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
                                <th>Thao tác</th>
                            </tr>
                        </thead>
 
                        <tbody id="list_sv_chon">
                        </tbody>
                    </table>
                </div>
                <div class="m-portlet__foot row">
                    <div class="d-inline col-6">
                    </div>
                    <div class="d-inline col-6 d-flex justify-content-end">
                        <button class="btn btn-success mr-3" id="btn_create" disabled>
                            Tạo nhóm
                        </button>
                        <button class="btn btn-primary" id="btn_join" disabled>
                            Ghép vào nhóm đã tạo
                        </button>
                    </div>
 
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="route_ds_dot" value="{{ "staff.du_an_tot_nghiep.dot_dang_ky.index") }}">
<input type="hidden" id="route_search_ma_sv" value="{{ "staff.du_an_tot_nghiep.sinh_vien.search_by_ma") }}">

<script>
    let startTblIndex = {{ i - $limit }};
</script>
<script src="{{ asset('js/staff/du_an_2/filter.js') }}"></script>
<script src="{{ asset('js/staff/du_an_2/filter_dot.js') }}"></script>
<script src="{{ asset('js/staff/du_an_2/chia_nhom.js') }}"></script>

 
 

