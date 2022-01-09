package models;
import java.util.Date;

public class LeTetModel {
	private int idSuKien;
	private Date thoiGian;
	private String tenSuKien;
	private String motSuatQua;
	private double tongGiaTriMotSuat;
	private Date thoiGianTao;
	private int idNguoiTao;
	private String tenNguoiTao;
	public int getIdSuKien() {
		return idSuKien;
	}
	public void setIdSuKien(int idSuKien) {
		this.idSuKien = idSuKien;
	}
	public Date getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(Date thoiGian) {
		this.thoiGian = thoiGian;
	}
	public String getTenSuKien() {
		return tenSuKien;
	}
	public void setTenSuKien(String tenSuKien) {
		this.tenSuKien = tenSuKien;
	}
	public String getMotSuatQua() {
		return motSuatQua;
	}
	public void setMotSuatQua(String motSuatQua) {
		this.motSuatQua = motSuatQua;
	}
	public double getTongGiaTriMotSuat() {
		return tongGiaTriMotSuat;
	}
	public void setTongGiaTriMotSuat(double tongGiaTriMotSuat) {
		this.tongGiaTriMotSuat = tongGiaTriMotSuat;
	}
	public Date getThoiGianTao() {
		return thoiGianTao;
	}
	public void setThoiGianTao(Date thoiGianTao) {
		this.thoiGianTao = thoiGianTao;
	}
	public int getIdNguoiTao() {
		return idNguoiTao;
	}
	public void setIdNguoiTao(int idNguoiTao) {
		this.idNguoiTao = idNguoiTao;
	}
	public String getTenNguoiTao() {
		return tenNguoiTao;
	}
	public void setTenNguoiTao(String tenNguoiTao) {
		this.tenNguoiTao = tenNguoiTao;
	}
}