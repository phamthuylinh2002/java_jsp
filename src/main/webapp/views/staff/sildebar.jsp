<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<button class="m-aside-left-close  m-aside-left-close--skin-light"
	id="m_aside_left_close_btn">
	<i class="la la-close"></i>
</button>

<div id="m_aside_left"
	class="m-grid__item m-aside-left m-aside-left--skin-dark">
	<div id="m_ver_menu"
		class="m-aside-menu m-aside-menu--skin-light m-aside-menu--submenu-skin-light"
		m-menu-vertical="1" m-menu-scrollable="1"
		m-menu-dropdown-timeout="500" style="position: relative;">
		<ul class="m-menu__nav m-menu__nav--dropdown-submenu-arrow">
			<li class="m-menu__item m-menu__item--submenu" aria-haspopup="true"
				id="sub_menu_datn" m-menu-submenu-toggle="hover"><a
				href="javascript:;" class="m-menu__link m-menu__toggle"> <i
					class="m-menu__link-icon flaticon-share"></i> <span
					class="m-menu__link-text">Đăng ký DATN</span> <i
					class="m-menu__ver-arrow la la-angle-right"></i>
			</a>
				<div class="m-menu__submenu ">
					<span class="m-menu__arrow"></span>
					<ul class="m-menu__subnav">
						<li class="m-menu__item" aria-haspopup="true"><a href="${path}/staff/PDT/dashboard?dot-dang-ky-id=${sessionScope.dashboard }"
							class="m-menu__link "> <i
								class="m-menu__link-icon flaticon-line-graph"></i> <span
								class="m-menu__link-title"> <span
									class="m-menu__link-wrap"> <span
										class="m-menu__link-text">Dashboard</span>
								</span>
							</span>
						</a></li>

						<li class="m-menu__item  m-menu__item--submenu"
							aria-haspopup="true" m-menu-submenu-toggle="hover"><a
							href="${path}/staff/PDT/danh-sach-sinh-vien-chua-dang-ky" class="m-menu__link m-menu__toggle"> <i
								class="m-menu__link-icon flaticon-multimedia"></i> <span
								class="m-menu__link-text">Danh sách sinh viên chưa đăng
									ký</span>
						</a></li>

						<li class="m-menu__item  m-menu__item--submenu"
							aria-haspopup="true" m-menu-submenu-toggle="hover"><a
							href="${path}/staff/PDT/danh-sach-sinh-vien-da-dang-ky" class="m-menu__link m-menu__toggle"> <i
								class="m-menu__link-icon flaticon-multimedia"></i> <span
								class="m-menu__link-text">Danh sách sinh viên đã đăng ký</span>
						</a></li>

						<li class="m-menu__item  m-menu__item--submenu"
							aria-haspopup="true" m-menu-submenu-toggle="hover"><a
							href="${path}/staff/PDT/danh-sach-cac-nhom-da-tao" class="m-menu__link m-menu__toggle"> <i
								class="m-menu__link-icon flaticon-graphic"></i> <span
								class="m-menu__link-text">Danh sách các nhóm đã tạo</span>
						</a></li>
						<li class="m-menu__item  m-menu__item--submenu"
							aria-haspopup="true" m-menu-submenu-toggle="hover"><a
							href="${path}/staff/PDT/quan-ly-giang-vien" class="m-menu__link m-menu__toggle"> <i
								class="m-menu__link-icon flaticon-graphic"></i> <span
								class="m-menu__link-text">Quản lý giảng viên</span>
						</a></li>


						<li class="m-menu__item  m-menu__item--submenu"
							aria-haspopup="true" m-menu-submenu-toggle="hover"><a
							href="${path}/staff/PDT/quan-ly-sinh-vien" class="m-menu__link m-menu__toggle"> <i
								class="m-menu__link-icon flaticon-graphic"></i> <span
								class="m-menu__link-text">Quản lý sinh viên</span>
						</a></li>
						
						<li class="m-menu__item  m-menu__item--submenu"
							aria-haspopup="true" m-menu-submenu-toggle="hover"><a
							href="${path}/staff/PDT/dot-dang-ky" class="m-menu__link m-menu__toggle"> <i
								class="m-menu__link-icon flaticon-graphic"></i> <span
								class="m-menu__link-text">Quản lý đợt đăng ký</span>
						</a></li>


						<!-- <li class="m-menu__item  m-menu__item--submenu"
							aria-haspopup="true" m-menu-submenu-toggle="hover"><a
							href="/ToolDangKy/staff/PDT/chia-nhom" class="m-menu__link m-menu__toggle"> <i
								class="m-menu__link-icon flaticon-graphic"></i> <span
								class="m-menu__link-text">Chia Nhóm</span>
						</a></li> -->
					</ul>
				</div></li>
		</ul>
	</div>
</div>




