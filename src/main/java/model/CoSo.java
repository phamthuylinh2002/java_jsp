package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the co_so database table.
 * 
 */
@Entity
@Table(name="co_so")
@NamedQuery(name="CoSo.findAll", query="SELECT c FROM CoSo c")
public class CoSo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="nhan_vien_id")
	private Integer nhanVienId;

	private String ten;

	public CoSo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNhanVienId() {
		return this.nhanVienId;
	}

	public void setNhanVienId(Integer nhanVienId) {
		this.nhanVienId = nhanVienId;
	}

	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

}