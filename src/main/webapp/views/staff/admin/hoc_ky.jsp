<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

            <div class="m-content">
                <div class="mt-5">
                    <h3>
                        Học kỳ <a class="btn btn-primary" href="${path}/staff/CNBM/danh-sach-cac-nhom-da-tao" style="float: right; margin-bottom: 10px"> Quay lại </a>
                    </h3>

                    <form action="${path}/staff/admin/hoc-ky/insert" onsubmit="return validate()" method="post" class="m-portlet__body  mt-5 mb-5">
                        <div class="row">
                            <div class="form-group d-inline col-md-6 col-12 d-flex justify-content-around align-items-center ">

                                <span for="" class="fillter-name col-4 text-center">Tạo học
						kỳ mới</span>

                                <input type="text" class="form-control col-8" name="hocky" id="hocky" />

                            </div>

                            <div class="form-group d-inline col-md-1 col-6 d-flex justify-content-around align-items-center">
                                <button type="submit" class="btn btn-primary">Tạo học kỳ mới</button>
                            </div>
                        </div>
                        <div class="row ml-5">
                            <small class="text-danger ml-5" id="tendot_error"></small>
                        </div>


                    </form>
                    <div class="m-portlet">
                        <div class="m-portlet__body table-scrollable">
                            <table class=" table d-sm-table table-bordered table-scrollable m-table m-table--head-bg-primary table-responsive ">
                                <thead>
                                    <th>Tên</th>
                                    <th>TGBD</th>
                                    <th>TGKT</th>
                                    <th>Cơ sở</th>
                                </thead>

                                <tbody>
                                    <c:forEach var="item" items="${hocky}">
                                        <tr>
                                            <td>${item[0]}</td>
                                            <td>${item[1]}</td>
                                            <td>${item[2]}</td>
                                            <td>${item[3]}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="m-portlet__foot d-flex justify-content-end"></div>
                </div>
            </div>

            <script>
                function setSelected(event) {
                    const dot_id = event.target.value;
                    if (Number(dot_id) == 1) {
                        document.getElementById('chuyen_nganh_id').value = Person_ID;

                    }
                }

                function validate() {
                    if (document.getElementById("hocky").value.trim().length < 1) {
                        document.getElementById("tendot_error").innerHTML = "Vui lòng nhập vào tên học kỳ";
                        return false;
                    } else {
                        document.getElementById("tendot_error").innerHTML = "";
                        document.getElementById("hocky").innerHTML = document.getElementById("").value.trim();
                    }

                    return true;
                }
            </script>