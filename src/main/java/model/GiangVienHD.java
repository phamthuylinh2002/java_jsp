package model;

public class GiangVienHD {
	private String maGv, tenGv, emailFPT, emailFE,sdt;
	private int cnId, nhomHD;
	
	public int getCnId() {
		return cnId;
	}
	public void setTenGv(String tenGv) {
		this.tenGv = tenGv;
	}
	public void setEmailFPT(String emailFPT) {
		this.emailFPT = emailFPT;
	}
	public String getEmailFPT() {
		return emailFPT;
	}
	public void setEmailFE(String emailFE) {
		this.emailFE = emailFE;
	}
	public String getEmailFE() {
		return emailFE;
	}
	public String getSdt() {
		return sdt;
	}
	public void setNhomHD(int nhomHD) {
		this.nhomHD = nhomHD;
	}

	public String getTenGv() {
		return tenGv;
	}
	public void setMaGv(String maGv) {
		this.maGv = maGv;
	}
	public void setCnId(int cnId) {
		this.cnId = cnId;
	}
	public int getNhomHD() {
		return nhomHD;
	}
	public String getMaGv() {
		return maGv;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	

}