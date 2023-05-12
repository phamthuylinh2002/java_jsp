package services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.PageInfo;
import common.PageType;
import lombok.extern.log4j.Log4j2;
import model.NhanVien;
import repositories.NhanVienRepositories;
import repositories.NhomRepositories;
import repositories.SinhVienRepositories;

@Log4j2
public class GiangVienService {
//	private final int DOTDK_ID = 1;
//	private final int COSO_ID = 1;

	private SinhVienRepositories svRep;
	private NhomRepositories nhomRep;
	private NhanVienRepositories nvRep;
	private static final Logger LOGGER = LoggerFactory.getLogger(GiangVienService.class);
	public GiangVienService() {
		this.nhomRep = new NhomRepositories();
		this.svRep = new SinhVienRepositories();
		this.nvRep = new NhanVienRepositories();
	}

	public void prepareAndForward(HttpServletRequest request, HttpServletResponse response, PageType pageType)
			throws ServletException, IOException {
		PageInfo page = PageInfo.pageRoute.get(pageType);
		request.setAttribute("page", page);
		request.setAttribute("path", request.getContextPath());

		displayStatus(request, response);

		request.getRequestDispatcher("/views/index.jsp").forward(request, response);
	}

	private void displayStatus(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NhanVien nv = (NhanVien) request.getSession().getAttribute("staff");
		List<Object[]> lstGVDDK = nvRep.findGVHD_DotDK_byNVid(nv.getId());
		if (lstGVDDK.size() >0) {
			int GVHD_ID = (int) lstGVDDK.get(0)[0];
			int DOTDK_ID = (int) lstGVDDK.get(0)[1];
			List<Object[]> lst = nhomRep.getListNhom(nv.getCoSoId(), DOTDK_ID, GVHD_ID);
			request.setAttribute("Nhoms", lst);
		}

		// set liên hệ PDT
		List<Object[]> obj = this.nvRep.getNhanVienPDT(nv.getCoSoId());
		if (obj != null) {
			request.setAttribute("email", obj.get(0)[0]);
			request.setAttribute("ten", obj.get(0)[1]);
		}
	}

	public void prepareAndDirect(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		if (uri.contains("thong-tin-nhom")) {
			String id = request.getParameter("nhom_id");

			Integer nhomId = Integer.parseInt(id);

			List<Object[]> lstTVNhom = svRep.getSVByNhomId(nhomId);
			request.setAttribute("Nhoms", lstTVNhom);
			PageInfo page = new PageInfo("Thong Tin Nhom", "/views/staff/giang_vien/thongtinnhom.jsp");
			request.setAttribute("page", page);
			request.setAttribute("path", request.getContextPath());
		}

		request.getRequestDispatcher("/views/index.jsp").forward(request, response);
	}
}
