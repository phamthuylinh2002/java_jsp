package model;

import java.io.Serializable;
import javax.persistence.*;

import com.google.api.client.util.DateTime;

import java.sql.Timestamp;


/**
 * The persistent class for the sinh_vien database table.
 * 
 */
@Entity
@Table(name="sinh_vien")
@NamedQuery(name="SinhVien.findAll", query="SELECT s FROM SinhVien s")
public class SinhVien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="chuyen_nganh_id")
	private Integer chuyenNganhId;

	@Column(name="co_so_id")
	private Integer coSoId;

	@Column(name="dot_dang_ky_id")
	private Integer dotDangKyId;

	private String email;

	@Column(name="ho_ten")
	private String hoTen;

	@Column(name="hoc_ky")
	private Integer hocKy;

	private String khoa;

	@Column(name="ma_sv")
	private String maSv;

	@Column(name="nhom_id")
	private Integer nhomId;

	@Column(name="thoi_gian_dk_nhom")
	private String thoiGianDkNhom;

	@Column(name="trang_thai")
	private Integer trangThai;

	public SinhVien() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChuyenNganhId() {
		return this.chuyenNganhId;
	}

	public void setChuyenNganhId(Integer chuyenNganhId) {
		this.chuyenNganhId = chuyenNganhId;
	}

	public Integer getCoSoId() {
		return this.coSoId;
	}

	public void setCoSoId(Integer coSoId) {
		this.coSoId = coSoId;
	}

	public Integer getDotDangKyId() {
		return this.dotDangKyId;
	}

	public void setDotDangKyId(Integer dotDangKyId) {
		this.dotDangKyId = dotDangKyId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHoTen() {
		return this.hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public Integer getHocKy() {
		return this.hocKy;
	}

	public void setHocKy(Integer hocKy) {
		this.hocKy = hocKy;
	}

	public String getKhoa() {
		return this.khoa;
	}

	public void setKhoa(String khoa) {
		this.khoa = khoa;
	}

	public String getMaSv() {
		return this.maSv;
	}

	public void setMaSv(String maSv) {
		this.maSv = maSv;
	}

	public Integer getNhomId() {
		return this.nhomId;
	}

	public void setNhomId(Integer nhomId) {
		this.nhomId = nhomId;
	}

	public String getThoiGianDkNhom() {
		return this.thoiGianDkNhom;
	}

	public void setThoiGianDkNhom(String thoiGianDkNhom) {
		this.thoiGianDkNhom = thoiGianDkNhom;
	}

	public Integer getTrangThai() {
		return this.trangThai;
	}

	public void setTrangThai(Integer trangThai) {
		this.trangThai = trangThai;
	}

}