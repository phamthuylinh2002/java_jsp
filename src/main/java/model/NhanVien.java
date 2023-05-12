package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nhan_vien database table.
 * 
 */
@Entity
@Table(name="nhan_vien")
@NamedQuery(name="NhanVien.findAll", query="SELECT n FROM NhanVien n")
public class NhanVien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="_role")
	private Integer role;

	@Column(name="bo_mon_id")
	private Integer boMonId;

	@Column(name="co_so_id")
	private Integer coSoId;

	private String emailFE;

	private String emailFPT;

	private String manv;

	private String sdt;

	private String ten;

	public NhanVien() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getBoMonId() {
		return this.boMonId;
	}

	public void setBoMonId(Integer boMonId) {
		this.boMonId = boMonId;
	}

	public Integer getCoSoId() {
		return this.coSoId;
	}

	public void setCoSoId(Integer coSoId) {
		this.coSoId = coSoId;
	}

	public String getEmailFE() {
		return this.emailFE;
	}

	public void setEmailFE(String emailFE) {
		this.emailFE = emailFE;
	}

	public String getEmailFPT() {
		return this.emailFPT;
	}

	public void setEmailFPT(String emailFPT) {
		this.emailFPT = emailFPT;
	}

	public String getManv() {
		return this.manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

}