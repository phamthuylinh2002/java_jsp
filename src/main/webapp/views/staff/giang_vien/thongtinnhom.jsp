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
								Thông tin nhóm</h3>
						</div>
					</div>
					
				</div>
				<div class="m-portlet__body">
					<div class="m-section">
						<div class="m-section__content">
							<table style="font-family: tahoma;"
								class="table m-table m-table--head-bg-success">
								<thead>
									<tr>
										<th>Mã sinh viên</th>
										<th>Tên sinh viên</th>
										<th>Chuyên ngành</th>
										<th>Khóa</th>
										<th>Email</th>
										<th>học kỳ</th>
									</tr>
								</thead>
								<c:forEach var="item" items="${Nhoms}">
									<tr>
										<th>${item[1]}</th>
										<th>${item[2]}</th>
										<td>${item[3]}</td>
										<th>${item[5]}</th>
										<th>${item[6]}</th>
										<td>${item[7]}</td>
									</tr>
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