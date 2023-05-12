package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chuyen_nganh_gv database table.
 * 
 */
@Entity
@Table(name="chuyen_nganh_gv")
@NamedQuery(name="ChuyenNganhGv.findAll", query="SELECT c FROM ChuyenNganhGv c")
public class ChuyenNganhGv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="chuyen_nganh_id")
	private Integer chuyenNganhId;

	@Column(name="nhan_vien_id")
	private Integer nhanVienId;

	public ChuyenNganhGv() {
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

	public Integer getNhanVienId() {
		return this.nhanVienId;
	}

	public void setNhanVienId(Integer nhanVienId) {
		this.nhanVienId = nhanVienId;
	}

}