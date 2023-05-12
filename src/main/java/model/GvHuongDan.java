package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gv_huong_dan database table.
 * 
 */
@Entity
@Table(name="gv_huong_dan")
@NamedQuery(name="GvHuongDan.findAll", query="SELECT g FROM GvHuongDan g")
public class GvHuongDan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="chuyen_nganh_id")
	private Integer chuyenNganhId;

	@Column(name="dot_dang_ky_id")
	private Integer dotDangKyId;

	@Column(name="nhan_vien_id")
	private Integer nhanVienId;

	@Column(name="so_nhom_huong_dan")
	private Integer soNhomHuongDan;

	public GvHuongDan() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getChuyenNganhId() {
		return this.chuyenNganhId;
	}

	public void setChuyenNganhId(Integer chuyenNganhId) {
		this.chuyenNganhId = chuyenNganhId;
	}

	public Integer getDotDangKyId() {
		return this.dotDangKyId;
	}

	public void setDotDangKyId(Integer dotDangKyId) {
		this.dotDangKyId = dotDangKyId;
	}

	public Integer getNhanVienId() {
		return this.nhanVienId;
	}

	public void setNhanVienId(Integer nhanVienId) {
		this.nhanVienId = nhanVienId;
	}

	public Integer getSoNhomHuongDan() {
		return this.soNhomHuongDan;
	}

	public void setSoNhomHuongDan(Integer soNhomHuongDan) {
		this.soNhomHuongDan = soNhomHuongDan;
	}

}