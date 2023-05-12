package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the dot_dang_ky database table.
 * 
 */
@Entity
@Table(name="dot_dang_ky")
@NamedQuery(name="DotDangKy.findAll", query="SELECT d FROM DotDangKy d")
public class DotDangKy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="co_so_id")
	private Integer coSoId;

	@Column(name="hoc_ky")
	private String hocKy;

	@Temporal(TemporalType.DATE)
	@Column(name="thoi_gian_bat_dau")
	private Date thoiGianBatDau;

	@Temporal(TemporalType.DATE)
	@Column(name="thoi_gian_ket_thuc")
	private Date thoiGianKetThuc;

	public DotDangKy() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCoSoId() {
		return this.coSoId;
	}

	public void setCoSoId(Integer coSoId) {
		this.coSoId = coSoId;
	}

	public String getHocKy() {
		return this.hocKy;
	}

	public void setHocKy(String hocKy) {
		this.hocKy = hocKy;
	}

	public Date getThoiGianBatDau() {
		return this.thoiGianBatDau;
	}

	public void setThoiGianBatDau(Date thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public Date getThoiGianKetThuc() {
		return this.thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(Date thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

}