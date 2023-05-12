package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ChuyenNganhGv;

import model.GvHuongDan;
import model.NhanVien;
import model.SinhVien;
import repositories.ChuyenNganhGvRepositories;
import repositories.GvHuongDanRepositories;

import repositories.NhanVienRepositories;


public class ManagerGVService {
	private NhanVienRepositories nvRep;
	private ChuyenNganhGvRepositories cnRep ;

	private GvHuongDanRepositories gvRep ;

	
	public ManagerGVService() {
		this.nvRep = new NhanVienRepositories();
		this.cnRep = new ChuyenNganhGvRepositories();

		this.gvRep = new GvHuongDanRepositories();

		
	}
	
	public void xoaGV(HttpServletResponse response, HttpServletRequest request) 
			throws ServletException, IOException {
		
		try {
			String id = request.getParameter("idGv");
			Integer idGv = utils.HelperUtils.parseInteger(id);
			
			ChuyenNganhGv gv = this.cnRep.getGV(idGv);
			
			gv = this.cnRep.delete(gv);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}
	

	
	public void suaGV(HttpServletRequest request, HttpServletResponse response, NhanVien nv, int cnId, int dot) 

			throws ServletException, IOException{
		try {
			nv.setManv(request.getParameter("ma_gv").trim());
			String ten = utils.HelperUtils.changeString(request.getParameter("ten_gv").trim());
			nv.setTen(ten);
			nv.setEmailFPT(request.getParameter("email").trim());
			nv.setSdt(request.getParameter("sdt").trim());

			nv= this.nvRep.update(nv);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


}

