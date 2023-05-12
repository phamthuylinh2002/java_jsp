<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <div>
                <div class="m-content">

                    <h3>Danh sách các nhóm
                        <a class="btn btn-primary" href="${path}/staff/CNBM/danh-sach-cac-nhom-da-tao" style="float: right; margin-bottom: 10px">
                            Quay lại </a>

                    </h3>



                    <div class="m-portlet">
                        <div class="m-portlet">

                            <div class="m-portlet__body">

                                <table class="table table-bordered m-table d-sm-table m-table--head-bg-primary table-responsive">
                                    <thead>
                                        <th scope="col">STT</th>
                                        <th>Tên Đề Tài</th>
                                        <th>Mã Tham Gia</th>
                                        <th>Chuyên Ngành</th>
                                        <th>Số thành viên</th>
                                        <th>Giảng viên Hướng dẫn</th>
                                        <th>Trạng thái hiển thị</th>
                                        <th>Thao tác</th>
                                    </thead>

                                    <tbody>

                                        <c:forEach var="item" items="${dSachNhom }" varStatus="loop">
                                            <tr>
                                                <td>${loop.count}</td>
                                               <!--  <td>${item[0] }</td>-->
                                                <td>${item[1]}</td>
                                                <td>${item[2]}</td>
                                                <td>${item[3]}</td>
                                                <td>${item[4]}</td>
                                                <td>${item[5]}</td>
                                                <td>${item[2] == null?'Nhóm công khai':'Nhóm bí mật' }</td>
                                                <td><a class="btn btn-primary" href="${path}/staff/CNBM/chi-tiet-nhom?nhom_id=${item[0]}">
                                                    Chi tiết nhóm </a></td>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                </table>
                            </div>

                        </div>

                    </div>

                </div>
            </div>