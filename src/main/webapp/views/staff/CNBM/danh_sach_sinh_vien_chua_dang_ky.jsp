<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
            <div>
                <div class="m-content">
                    <h3>Danh sách sinh viên chưa đăng ký</h3>

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

                                <form action="${path}/staff/CNBM/danh-sach-sinh-vien-chua-dang-ky/tim-kiem-sinh-vien-chua-dang-ky" method="post" class="m-portlet__body">
                                    <div class="row">
                                     <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                        <span for="" class="fillter-name col-4 text-center">Chuyên
											ngành</span> <select class="form-control d-inline col-8 select2" name="chuyen_nganh_id" id="chuyen_nganh_id" onchange="submitData(event)">
											<option value="0" selected>Chọn chuyên ngành</option>

											<c:forEach items="${chuyenNganh}" var="chuyenNganh">
											
												<option value="${chuyenNganh[0]}"
													${chuyenNganh[0]==sessionScope.ScopeCN_ChuaDK ?"selected":""}>
													${chuyenNganh[1]}</option>

											</c:forEach>

										</select>
                                    </div>

                                        <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                            <span for="" class="fillter-name col-4 text-center">Khóa</span>
                                            <select class="form-control col-8 select2" name="khoa" id="khoa">
                                    <option value="0" selected>Chọn khóa</option>
                              
                                         <c:forEach items="${khoa}" var="khoa">
                                        	<option value="${khoa}" ${khoa.equals(sessionScope.ScopeKhoa_chuaDK) ?"selected":""} >${khoa}</option>	

                                        </c:forEach>
                             
                                </select>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center">
                                            <span for="" class="fillter-name col-4 text-center">Đợt Đăng Ký</span>
                                            <select class="form-control d-inline col-8 select2" name="dot_id" id="dot_id">
                                  
                                    	<option value="0" selected>Chọn đợt đăng ký</option>
                                    	<c:forEach items="${dotdk}" var="dotdk">
                                        	<option value="${dotdk[0]}" ${dotdk[0]== sessionScope.ScopeDotDK_chuaDK ?"selected":""} >${dotdk[1]}</option>

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
                                            <a href="${path}/staff/CNBM/danh-sach-sinh-vien-chua-dang-ky" class="btn btn-danger text-white">
                                    Làm mới bộ lọc
                                </a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </section>

                        <div class="row mb-5 bieumau">
                            <div class="col-12 col-sm-4 col-lg-4">
                                <a href="${path}/files/export-danh-sach-sv-chua-dang-ky">
                                    <i class="fa fa-download" aria-hidden="true"></i> Xuất danh sách sinh viên chưa đăng ký
                                </a>
                            </div>
                        </div>


                        <div class="m-portlet">

                            <div class="m-portlet__body">
                                <div class="row">
                                    <div class="col-6">
                                        <label for="">Tổng số sinh viên chưa đăng ký:</label>
                                        <h4 class="d-inline ml-4">${tongSVChuaDK}</h4>
                                    </div>
                                    
                                </div>

                                <table class="table table-bordered m-table d-sm-table m-table--head-bg-primary table-responsive">
                                    <thead>
                                        <th scope="col">STT</th>
                                        <th>Mã Sinh Viên</th>
                                        <th>Tên Sinh Viên</th>
                                        <th>Email</th>
                                        <th>Khóa</th>
                                        <th>Chuyên Ngành</th>
                                    </thead>

                                    <tbody>

                                        <c:forEach var="item" items="${sinhVienChuaDK }" varStatus="loop">
                                            <tr>
                                                <td>${loop.count}</td>
                                               <!--  <td>${item[0] }</td>-->
                                                <!-- id -->
                                                <td>${item[1] }</td>
                                                <!-- ma -->
                                                <td>${item[2] }</td>
                                                <!-- hoten -->
                                                <td>${item[3] }</td>
                                                <!-- email -->
                                                <td>${item[4] }</td>
                                                <!-- khoa-->
                                                <td>${item[5] }</td>
                                                <!-- chuyen nganh-->
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>

            <input type="hidden" id="route_ds_dot" value="{{ " staff.du_an_tot_nghiep.dot_dang_ky.index ") }}">

            <script src="{{ asset('js/staff/du_an_2/filter.js') }}"></script>
            <script src="{{ asset('js/staff/du_an_2/filter_dot.js') }}"></script>
            <script>
                ('#sub_menu_datn').addClass('m-menu__item--open');
                (".select2").select2();

                var closeModal = function(id) {
                    ('#' + id).modal('hide');
                    ('.modal-backdrop').addClass('d-none');
                }
            </script>