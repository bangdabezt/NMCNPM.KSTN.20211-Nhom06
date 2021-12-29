package models;

import java.sql.Date;

import java.sql.Blob;

public class TraoQuaHsgModel {
	private int idNhanKhau;
	private String thanhTich;
	private String namHoc;
	private Blob minhChung;
	public int getIdNhanKhau() {
		return idNhanKhau;
	}
	public void setIdNhanKhau(int idNhanKhau) {
		this.idNhanKhau = idNhanKhau;
	}
	public String getThanhTich() {
		return thanhTich;
	}
	public void setThanhTich(String thanhTich) {
		this.thanhTich = thanhTich;
	}
	public String getNamHoc() {
		return namHoc;
	}
	public void setNamHoc(String namHoc) {
		this.namHoc = namHoc;
	}
	public Blob getMinhChung() {
		return minhChung;
	}
	public void setMinhChung(Blob blob) {
		this.minhChung = blob;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public Date getNgayNhan() {
		return ngayNhan;
	}
	public void setNgayNhan(Date ngayNhan) {
		this.ngayNhan = ngayNhan;
	}
	private String trangThai;
	private Date ngayNhan;
}
