<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <div>
                <div class="m-content">

                    <h3>Chi tiết nhóm
                        <a class="btn btn-primary" href="${path}/staff/CNBM/danh-sach-cac-nhom-da-tao"
                            style="float: right; margin-bottom: 10px">
                            Quay lại </a>

                    </h3>

                    <div class="mt-5">

                        <div class="m-portlet">
                            <c:forEach var="item" items="${dSachNhom }">
                                <div class="m-portlet__body">
                                    <div class="col-12 mt-3">
                                        <label class="col-sm-3">Tên Đề tài</label> <label
                                            class="col-sm-8 font-weight-bold">${item[1]}</label>
                                    </div>
                                    <div class="col-12 mt-3">
                                        <label class="col-sm-3">Giảng viên hướng dẫn</label> <label
                                            class="col-sm-8 font-weight-bold">${item[5]}</label>
                                    </div>
                                    <div class="col-12 mt-3">
                                        <label class="col-sm-3">Chuyên ngành</label> <label
                                            class="col-sm-8 font-weight-bold">${item[3]}</label>
                                    </div>
                                    <div class="col-12 mt-3">
                                        <label class="col-sm-3">Code tham gia nhóm</label> <label
                                            class="col-sm-8 font-weight-bold">${item[2]}</label>
                                    </div>
                                    <div class="col-12 mt-3">
                                        <label class="col-sm-3">Trạng thái hiển thị</label> <label
                                            class="col-sm-8 font-weight-bold">${item[2] == null?'Nhóm công khai':'Nhóm
                                            bí mật' }</label>
                                    </div>
                            </c:forEach>
                        </div>
                        <div class="m-portlet__body table-scrollable">
                            <table
                                class="table d-sm-table table-bordered table-scrollable m-table m-table--head-bg-primary table-responsive">
                                <thead>
                                	<th>STT</th>
                                    <th>Mã sinh viên</th>
                                    <th>Tên thành viên</th>
                                    <th>Email</th>
                                    <th>Khóa</th>
                                    <th>Thao tác</th>
                                </thead>

                                <tbody>
                                    <c:forEach var="item" items="${Nhoms}" varStatus="loop">
                                        <tr>
                                        	<td>${loop.count}</td>
                                            <td>${item.maSv}</td>
                                            <td>${item.hoTen}</td>
                                            <td>${item.email}</td>
                                            <td>${item.khoa }</td>
                                            <td><a class="btn btn-primary"
                                                    href="${path}/staff/CNBM/chi-tiet-nhom/deleteSV?idSV=${item.id }">
                                                    Xóa khỏi nhóm
                                                </a>
                                            </td>

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>


                        <div class="m-portlet__foot d-flex justify-content-end"></div>
                    </div>

                </div>
            </div>