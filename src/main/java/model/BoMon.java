package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bo_mon database table.
 * 
 */
@Entity
@Table(name="bo_mon")
@NamedQuery(name="BoMon.findAll", query="SELECT b FROM BoMon b")
public class BoMon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String ten;

	public BoMon() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

}