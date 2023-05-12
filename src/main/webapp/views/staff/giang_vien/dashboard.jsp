<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="m-portlet mt-6">
	<div class="m-grid__item m-grid__item--fluid m-wrapper">
		<div class="">
			<div class="m-portlet">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<h3 style="font-family: tahoma;" class="m-portlet__head-text">
								Các nhóm hiện đang hướng dẫn:</h3>
						</div>
					</div>
					<a
						href="https://docs.google.com/document/d/1bzV6MOXqHQRk-KEfKVY1jWNie5TcvuUN"
						class="mt-4 text-right font-weight-bold" target="_blank">HƯỚNG
						DẪN SỬ DỤNG</a>
				</div>
				<div class="m-portlet__body">
					<div class="m-section">
						<div class="m-section__content">
							<table style="font-family: tahoma;"
								class="table m-table m-table--head-bg-success">
								<thead>
									<tr>
										<th>Tên Đề Tài</th>
										<th>Mã Tham Gia</th>
										<th>Chuyên Ngành</th>
										<th>Số thành viên</th>
										<th>Giảng viên Hướng dẫn</th>
										<th>Trạng thái hiển thị</th>
										<th>Thao tác</th>
									</tr>
								</thead>
								<c:forEach var="item" items="${Nhoms}">
									<form
										action="${path}/staff/GiangVien/thong-tin-nhom?nhom_id=${item[0]}"
										method="post">
										<tr>
											<td>${item[1]}</td>
											<td>${item[2]}</td>
											<td>${item[3]}</td>
											<td>${item[4]}</td>
											<td>${item[5]}</td>
											<td>${item[2] == null?'Nhóm công khai':'Nhóm bí mật' }</td>
											<th>
												<button class="btn btn-primary">Xem chi tiết nhóm</button>

											</th>
										</tr>
									</form>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="m-portlet__foot d-flex justify-content-end"></div>
		</div>
	</div>
</div>