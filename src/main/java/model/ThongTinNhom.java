package model;

public class ThongTinNhom {
	private String tenDeTai, chuyenNganh;
	private int id;
	private long soLuongThanhVien;


	public ThongTinNhom() {

	}

	public ThongTinNhom(String tenDeTai, String chuyenNganh, long soLuongThanhVien, int id) {
		super();
		this.tenDeTai = tenDeTai;
		this.chuyenNganh = chuyenNganh;
		this.soLuongThanhVien = soLuongThanhVien;
		this.id = id;
	}

	public String getTenDeTai() {
		return tenDeTai;
	}

	public void setTenDeTai(String tenDeTai) {
		this.tenDeTai = tenDeTai;
	}

	public String getChuyenNganh() {
		return chuyenNganh;
	}

	public void setChuyenNganh(String chuyenNganh) {
		this.chuyenNganh = chuyenNganh;
	}

	public long getSoLuongThanhVien() {
		return soLuongThanhVien;
	}

	public void setSoLuongThanhVien(long soLuongThanhVien) {
		this.soLuongThanhVien = soLuongThanhVien;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}