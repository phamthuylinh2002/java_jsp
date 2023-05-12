package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chuyen_nganh database table.
 * 
 */
@Entity
@Table(name="chuyen_nganh")
@NamedQuery(name="ChuyenNganh.findAll", query="SELECT c FROM ChuyenNganh c")
public class ChuyenNganh implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="bo_mon_id")
	private Integer boMonId;

	private String ten;

	public ChuyenNganh() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBoMonId() {
		return this.boMonId;
	}

	public void setBoMonId(Integer boMonId) {
		this.boMonId = boMonId;
	}

	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

}