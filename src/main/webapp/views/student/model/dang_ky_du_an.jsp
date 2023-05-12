<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>


<div class=" mt-5">
	<div class="m-grid__item m-grid__item--fluid m-wrapper">
		<div class="m-subheader mb-5"></div>
		<div class="mb-4">
			<div class="d-flex align-items-center">
				<div class="mr-auto"></div>
			</div>
		</div>
		<div class="">
			<div class="container col-12">
				<div class="row justify-content-center">
					<div class="col-xl-4 col-md-4 mb-md-0 mb-3">

						
						<a href=""><button data-toggle="modal" data-target="#huy_dang_ky"
							type="button" class=" btn btn-block btn-outline-danger btn-lg"
							style="font-family: tahoma">HỦY ĐĂNG KÝ DỰ ÁN TỐT NGHIỆP
						</button></a>

					</div>
					<div class="col-xl-4 col-md-4 mb-md-0 mb-3">

						<a href=""><button data-toggle="modal" data-target="#tao_nhom" type="button"
							class="btn btn-block btn-primary btn-lg"
							style="background-color: #0066B1; font-family: tahoma; border: #F37022;">
							TẠO NHÓM<br> <small style="font-size: 13px;">(Dành
								cho sinh viên đã có nhóm)</small>
						</button></a>
					</div>
					 <div class="col-xl-4 col-md-4 mb-md-0 mb-3">
						<!-- <button type="button" data-toggle="modal"
							data-target="#roi_nhom_modal"
							class="btn btn-block btn-outline-danger btn-lg"
							style="font-family: tahoma">RỜI NHÓM</button>-->
						<!-- <TaoNhom /> -->
						<a href=""> <button data-toggle="modal" data-target="#vao_nhom_co_san"
							type="button" class="btn btn-block btn-primary btn-lg"
							style="background-color: #0066B1; font-family: tahoma; border: #0EB04B;">
							GIA NHẬP NHÓM<br> <small style="font-size: 13px;">(Dành
								cho sinh viên đã có nhóm)</small>
						</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- <NhomCuaSinhVien /> -->
	<div class="m-portlet">
		<div class="m-grid__item m-grid__item--fluid m-wrapper">
			<div class="m-subheader ">
				<div class="d-flex align-items-center">
					<div class="mr-auto">
						<h2 style="font-family: tahoma;">Nhóm của bạn:</h2>
						<h3 style="font-family: tahoma;" class="m-subheader__title "
							style=" color: #111111">ĐỀ TÀI:</h3>
					</div>
				</div>
			</div>
			<div class="">
				<div class="m-portlet">
					<div class="m-portlet__head">
						<div class="m-portlet__head-caption">
							<div class="m-portlet__head-title">
								<h3 style="font-family: tahoma;" class="m-portlet__head-text">
									Giảng Viên:</h3>
							</div>
						</div>
					</div>
					<div class="m-portlet__body">
						<div class="m-section">
							<div class="m-section__content">
								<b>Mã code: </b>
								<button data-toggle="modal" data-target="#cap_nhat_nhom"
									style="font-family: tahoma;"
									class="btn btn-primary float-md-right mb-3">CẬP NHẬT</button>
								<table class="table m-table m-table--head-bg-success">
									<thead>
										<tr>

											<th>Họ và tên</th>
											<th>Mã SV</th>
											<th>Chuyên ngành</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>a</td>
											<td>a</td>
											<td>a</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Đã đăng ký nhưng chưa có nhóm -->
	<div class="m-portlet">
		<div class="m-grid__item m-grid__item--fluid m-wrapper">
			<div class="">
				<div class="m-portlet">
					<div class="m-portlet__head">
						<div class="m-portlet__head-caption">
							<div class="m-portlet__head-title">
								<h3 style="font-family: tahoma;" class="m-portlet__head-text">
									Các nhóm hiện tại:</h3>
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
											<th>Tên đề tài</th>
											<th>Số lượng thành viên</th>
											<th>Chuyên ngành</th>
											<th></th>
										</tr>
									</thead>
									<tbody class="">
										<tr>
											<th>a</th>
											<th>a</th>
											<td>a</td>
											<td>
												<button data-toggle="modal"
													data-target="#gia_nhap_nhom_{{ $nhom->id }}"
													class="btn btn-primary">Tham gia nhóm</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="m-portlet__foot d-flex justify-content-end"></div>
			</div>
		</div>
	</div>
</div>
<style>
.modal-dialog {
	max-width: 660px !important;
}

.m-portlet__body {
	padding: 10px !important;
}
</style>