package models;
import java.util.Date;

/*
 * @author Bang Nguyen Trong
 * 
 */

public class LichSuThayDoiNhanKhauModel {
	
	private int ID;
	private String idHoKhauMoi;
	private int idNhanKhau;
	private String suKien;
	private Date ngay;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	public String getIdHoKhauMoi() {
		return idHoKhauMoi;
	}
	public void setIdHoKhauMoi(String idHoKhauMoi) {
		this.idHoKhauMoi = idHoKhauMoi;
	}
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int inNhanKhau) {
		this.idNhanKhau = inNhanKhau;
	}
	public String getSuKien() {
		return suKien;
	}
	public void setSuKien(String suKien) {
		this.suKien = suKien;
	}
	public Date getNgay() {
		return ngay;
	}
	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}
	
	
}
