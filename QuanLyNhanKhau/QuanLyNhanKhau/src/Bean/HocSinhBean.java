package Bean;

import models.GiaDinhModel;
import models.NhanKhauModel;
import models.ThanhVienCuaHoModel;
import models.TieuSuModel;
import models.TraoQuaHsgModel;

public class HocSinhBean {
	private NhanKhauModel nhanKhauModel;
	private ThanhVienCuaHoModel thanhVienCuaHoModel;
	private TraoQuaHsgModel traoQuaHsgModel;
	
	public HocSinhBean(NhanKhauModel nhanKhauModel, ThanhVienCuaHoModel thanhVienCuaHoModel, TraoQuaHsgModel traoQuaHsgModel) {
		super();
		this.nhanKhauModel = nhanKhauModel;
		this.thanhVienCuaHoModel = thanhVienCuaHoModel;
		this.traoQuaHsgModel = traoQuaHsgModel;
	}
	
	public HocSinhBean() {
		super();
		this.nhanKhauModel = new NhanKhauModel();
		this.thanhVienCuaHoModel = new ThanhVienCuaHoModel();
		this.traoQuaHsgModel = new TraoQuaHsgModel();
	}
	
	public NhanKhauModel getNhanKhauModel() {
		return nhanKhauModel;
	}
	
	public void setNhanKhauModel(NhanKhauModel nhanKhauModel) {
		this.nhanKhauModel = nhanKhauModel;
	}
	
	public ThanhVienCuaHoModel getThanhVienCuaHoModel() {
		return thanhVienCuaHoModel;
	}
	
	public void setThanhVienCuaHoModel(ThanhVienCuaHoModel thanhVienCuaHoModel) {
		this.thanhVienCuaHoModel = thanhVienCuaHoModel;
	}
	
	public TraoQuaHsgModel getTraoQuaHsgModel() {
		return traoQuaHsgModel;
	}
	
	public void setTraoQuaHsgModel(TraoQuaHsgModel traoQuaHsgModel) {
		this.traoQuaHsgModel = traoQuaHsgModel;
	}
	
	public String toString() {
        String res =  "<html><style>p {padding: 5px; margin-left: 20px} table, th, td {border: 1px solid black; border-collapse: collapse;} table {width: 500px}</style> <div>"
                + "<h3>Thông tin cơ bản:"
        		+ "<p>ID: <b>" + nhanKhauModel.getID() + "</p>"
                + "<p>Họ tên: <b>" + nhanKhauModel.getHoTen() + "</p>"
                + "<p>Trường: <b>" + nhanKhauModel.getNoiLamViec() + "</p>"
                + "<p>ID Hộ khẩu: <b>" + thanhVienCuaHoModel.getIdHoKhau()+ "</p>"
                + "<p>Quan hệ với chủ hộ: <b>" +thanhVienCuaHoModel.getQuanHeVoiChuHo()+ "</p>";

             /*
                + "<h4>Tiểu sử<table>"
                + "<tr>"
                + "<th>Từ ngày</th>"
                + "<th>Đến ngày</th>"
                + "<th>Địa chỉ</th>"
                + "<th>Nơi làm việc</th>"
                + "</tr>";
        for (TieuSuModel tieuSuModel: listTieuSuModels) {
            res += "<tr>"
                    + "<td>"
                    + tieuSuModel.getTuNgay().toString()
                    + "</td>"
                    + "<td>"
                    + tieuSuModel.getDenNgay().toString()
                    + "</td>"
                    + "<td>"
                    + tieuSuModel.getDiaChi()
                    + "</td>"
                    + "<td>"
                    + tieuSuModel.getNoiLamViec()
                    + "</td>"
                    + "</tr>";
        }
        res += "</table><h4>Gia đình<table>"
                + "<tr>"
                + "<th>Họ tên</th>"
                + "<th>Ngày sinh</th>"
                + "<th>Giới tính</th>"
                + "<th>Quan hệ với nhân khẩu</th>"
                + "<th>Nghề nghiệp</th>"
                + "<th>Địa chỉ hiện tại</th>"
                +"</tr>";
        for (GiaDinhModel giaDinhModel: listGiaDinhModels) {
            res += "<tr>"
                    + "<td>"
                    + giaDinhModel.getHoTen()
                    + "</td>"
                    + "<td>"
                    + giaDinhModel.getNamSinh().toString()
                    + "</td>"
                    + "<td>"
                    + giaDinhModel.getGioiTinh()
                    + "</td>"
                    + "<td>"
                    + giaDinhModel.getQuanHeVoiNhanKhau()
                    + "</td>"
                    + "<td>"
                    + giaDinhModel.getNgheNghiep()
                    + "</td>"
                    + "<td>"
                    + giaDinhModel.getDiaChiHienTai()
                    + "</td>"
                    +"</tr>";
        }
        */
        res +=  
             "</div></html>";
        return res;
    }
}
