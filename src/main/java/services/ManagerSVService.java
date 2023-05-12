package services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.NhanVien;

import model.SinhVien;
import repositories.ChuyenNganhRepositories;
import repositories.SinhVienRepositories;

public class ManagerSVService {

	
	private int COSO_ID;

	private SinhVienRepositories svRep ;
	private ChuyenNganhRepositories cnRep;

	public ManagerSVService() {
		this.svRep= new SinhVienRepositories();
		this.cnRep = new ChuyenNganhRepositories();
	}

	public void xoaSinhVien(HttpServletRequest req , HttpServletResponse res) {
		try {
			String idSv = req.getParameter("idSv");
			Integer svid = utils.HelperUtils.parseInteger(idSv);
			SinhVien sinhVien =this.svRep.findById(svid);

			if(sinhVien == this.svRep.delete(sinhVien)) {
				req.setAttribute("cfSinhVien", 1);
			} else {
				req.setAttribute("cfSinhVien", 0);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}


	}

	public void suaSinhVien(HttpServletRequest request, HttpServletResponse response,SinhVien sinhVien) {

		try {

			sinhVien.setMaSv(utils.HelperUtils.changeString(request.getParameter("ma_sv")));
			sinhVien.setHoTen(utils.HelperUtils.changeString(request.getParameter("ten_sv")));
			sinhVien.setEmail(utils.HelperUtils.changeString(request.getParameter("email")));
			sinhVien.setKhoa(utils.HelperUtils.changeString(request.getParameter("khoa")));
			String idCN = request.getParameter("chuyen_nganh_id");
			Integer id = utils.HelperUtils.parseInteger(idCN);
			sinhVien.setChuyenNganhId(id);
			sinhVien = this.svRep.update(sinhVien);

			request.setAttribute("mess", "Thanh cong");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("mess", "That bai");

		}

	}

	public void themSinhVien(HttpServletRequest request, HttpServletResponse response) {
		try {
			NhanVien nv = (NhanVien) request.getSession().getAttribute("staff");
			COSO_ID = nv.getCoSoId();
			String ten = utils.HelperUtils.changeString(request.getParameter("ten_sv"));

			String dotDky = request.getParameter("dot_id");
			int dot_id = utils.HelperUtils.parseInteger(dotDky);

			String ma = utils.HelperUtils.changeString(request.getParameter("ma_sv"));
			String email = utils.HelperUtils.changeString(request.getParameter("email"));
			String khoa = utils.HelperUtils.changeString(request.getParameter("khoa"));
			String idCN = request.getParameter("chuyen_nganh_id");
			Integer id = utils.HelperUtils.parseInteger(idCN);
			String trangThai = request.getParameter("trang_thai");
			Integer trangThaiSv = utils.HelperUtils.parseInteger(trangThai);

			SinhVien sv = new SinhVien();
			sv.setChuyenNganhId(id);
			sv.setCoSoId(COSO_ID);
			sv.setDotDangKyId(dot_id);
			sv.setEmail(email);
			sv.setHocKy(6);
			sv.setHoTen(ten);
			sv.setKhoa(khoa);
			sv.setMaSv(ma);
			sv.setTrangThai(trangThaiSv);

			sv = this.svRep.insert(sv);
			request.setAttribute("mess", "Thành công");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("mess", "Thất bại");
		}
	}




}
