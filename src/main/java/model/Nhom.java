package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the nhom database table.
 * 
 */
@Entity
@Table(name="nhom")
@NamedQuery(name="Nhom.findAll", query="SELECT n FROM Nhom n")
public class Nhom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String code;

	@Column(name="dot_dang_ky_id")
	private Integer dotDangKyId;

	@Column(name="giang_vien_hd_id")
	private Integer giangVienHdId;

	@Column(name="ngay_tao")
	private String ngayTao;

	@Column(name="nhom_truong_id")
	private String nhomTruongId;

	@Column(name="ten_de_tai")
	private String tenDeTai;

	public Nhom() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDotDangKyId() {
		return this.dotDangKyId;
	}

	public void setDotDangKyId(Integer dotDangKyId) {
		this.dotDangKyId = dotDangKyId;
	}

	public Integer getGiangVienHdId() {
		return this.giangVienHdId;
	}

	public void setGiangVienHdId(Integer giangVienHdId) {
		this.giangVienHdId = giangVienHdId;
	}

	public String getNgayTao() {
		return this.ngayTao;
	}

	public void setNgayTao(String ngayTao) {
		this.ngayTao = ngayTao;
	}

	public String getNhomTruongId() {
		return this.nhomTruongId;
	}

	public void setNhomTruongId(String nhomTruongId) {
		this.nhomTruongId = nhomTruongId;
	}

	public String getTenDeTai() {
		return this.tenDeTai;
	}

	public void setTenDeTai(String tenDeTai) {
		this.tenDeTai = tenDeTai;
	}

}