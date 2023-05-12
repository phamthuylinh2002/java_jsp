package services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ChuyenNganhGv;
import model.GvHuongDan;
import model.NhanVien;
import repositories.ChuyenNganhGvRepositories;
import repositories.GvHuongDanRepositories;
import repositories.NhanVienRepositories;

public class ManagerNVService {
	private int COSO_ID;
	private NhanVienRepositories nv_rep;
	private GvHuongDanRepositories gvHDRep;
	private ChuyenNganhGvRepositories cnGv_rep;

	public ManagerNVService() {
		this.nv_rep = new NhanVienRepositories();
		this.gvHDRep = new GvHuongDanRepositories();
		this.cnGv_rep = new ChuyenNganhGvRepositories();
	}

	public void themNhanVien(HttpServletRequest request, HttpServletResponse response) {
		try {
			NhanVien nv = (NhanVien) request.getSession().getAttribute("staff");
			COSO_ID = nv.getCoSoId();
			String ten = utils.HelperUtils.changeString(request.getParameter("ten_nv"));
			String sdt = request.getParameter("sdt");
			String ma = utils.HelperUtils.changeString(request.getParameter("ma_nv"));
			String email = utils.HelperUtils.changeString(request.getParameter("email"));
			String emailFe = utils.HelperUtils.changeString(request.getParameter("email_nv_fe"));

			String role = utils.HelperUtils.changeString(request.getParameter("role_nv"));
			String coso = utils.HelperUtils.changeString(request.getParameter("coso_add"));
			Integer role_Int = utils.HelperUtils.parseInteger(role);
			String idCN = request.getParameter("chuyen_nganh_id");
			Integer idCN_Int = utils.HelperUtils.parseInteger(idCN);
			Integer coso_Int = utils.HelperUtils.parseInteger(coso);
			if (nv_rep.findByEmai(email)==null && nv_rep.findByMaNV(ma)==null) {
				NhanVien nv_add = new NhanVien();
				nv_add.setCoSoId(coso_Int);
				nv_add.setTen(ten);
				nv_add.setSdt(sdt);
				nv_add.setManv(ma);
				nv_add.setEmailFPT(email);
				nv_add.setRole(role_Int);
				nv_add.setBoMonId(idCN_Int);
				nv_add.setEmailFE(emailFe);
				
				nv_add = this.nv_rep.insert(nv_add);
				request.setAttribute("mess", "Thành công");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("mess", "Thất bại");
		}
	}
	public void suaNhanVien(HttpServletRequest request, HttpServletResponse response, Integer id) {
		try {
			NhanVien nv = (NhanVien) request.getSession().getAttribute("staff");
			COSO_ID = nv.getCoSoId();
			String ten = utils.HelperUtils.changeString(request.getParameter("ten_nv"));
			String sdt = request.getParameter("sdt");
			String ma = utils.HelperUtils.changeString(request.getParameter("ma_nv"));
			String email = utils.HelperUtils.changeString(request.getParameter("email"));
			String emailFe = utils.HelperUtils.changeString(request.getParameter("email_nv_fe"));
			
			String role = utils.HelperUtils.changeString(request.getParameter("role_nv"));
			String coso = utils.HelperUtils.changeString(request.getParameter("coso_add"));
			Integer role_Int = utils.HelperUtils.parseInteger(role);
			String idCN = request.getParameter("chuyen_nganh_id");
			Integer idCN_Int = utils.HelperUtils.parseInteger(idCN);
			Integer coso_Int = utils.HelperUtils.parseInteger(coso);
			
			NhanVien nv_add = new NhanVien();
			nv_add.setCoSoId(coso_Int);
			nv_add.setTen(ten);
			nv_add.setSdt(sdt);
			nv_add.setManv(ma);
			nv_add.setEmailFPT(email);
			nv_add.setRole(role_Int);
			nv_add.setBoMonId(idCN_Int);
			nv_add.setId(id);
			nv_add.setEmailFE(emailFe);
			
			nv_add = this.nv_rep.update(nv_add);
			request.setAttribute("mess", "Thành công");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("mess", "Thất bại");
		}
	}
	public void xoaNhanVien(HttpServletRequest req , HttpServletResponse res, Integer id) {
		xoaNhanVien(id);
	}
	
	public void xoaNhanVien(Integer id) {
		try {
			NhanVien nv = nv_rep.getById(id);
			if (nv!=null) {
				if (deleteGVHDbyIdNV(nv.getId())==true && deleCN_GVHDbyIDdNV(nv.getId())==true) {
					this.nv_rep.delete(nv);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}

	}
	private boolean deleteGVHDbyIdNV(int idNV) {
		List<GvHuongDan> lst = this.gvHDRep.findGVHDbyID(idNV);
		if (lst==null || lst.size()==0) {
			return true;
		}
		try {
			for (GvHuongDan x : lst) {
				this.gvHDRep.delete(x);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	private boolean deleCN_GVHDbyIDdNV(int idnv) {
		List<ChuyenNganhGv> lst = this.cnGv_rep.findCNGVbyIdNV(idnv);
		if (lst==null || lst.size()==0) {
			return true;
		}
		try {
			for (ChuyenNganhGv x : lst) {
				this.cnGv_rep.delete(x);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
