package Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.HoKhauModel;
import models.LeTetModel;
import models.NhanKhauModel;
import models.ThanhVienCuaHoModel;
import models.UserMoldel;

public class TraoQuaHoKhauBean {
    private HoKhauModel hoKhauModel;
    private NhanKhauModel chuHo;
    private List<NhanKhauModel> listNhanKhauModels;
    private List<ThanhVienCuaHoModel> listThanhVienCuaHo;
    private LeTetModel leTetModel;
    private String trangThaiNhanQua;
    private int soLuongQua;
    private Date ngayNhanQua;
    private UserMoldel nguoiGhiNhan;
    private Date ngayGhiNhan;

    public TraoQuaHoKhauBean(HoKhauModel hoKhauModel, NhanKhauModel chuHo, List<NhanKhauModel> listNhanKhauModels, List<ThanhVienCuaHoModel> listThanhVienCuaHo) {
        this.hoKhauModel = hoKhauModel;
        this.chuHo = chuHo;
        this.listNhanKhauModels = listNhanKhauModels;
        this.listThanhVienCuaHo = listThanhVienCuaHo;
    }
    
    public TraoQuaHoKhauBean() {
        this.hoKhauModel = new HoKhauModel();
        this.chuHo = new NhanKhauModel();
        this.listNhanKhauModels = new ArrayList<>();
        this.listThanhVienCuaHo = new ArrayList<>();
        this.leTetModel = new LeTetModel();
        this.nguoiGhiNhan = new UserMoldel();
    }

    
    public HoKhauModel getHoKhauModel() {
        return hoKhauModel;
    }

    public void setHoKhauModel(HoKhauModel hoKhauModel) {
        this.hoKhauModel = hoKhauModel;
    }

    public NhanKhauModel getChuHo() {
        return chuHo;
    }

    public void setChuHo(NhanKhauModel chuHo) {
        this.chuHo = chuHo;
    }

    public List<NhanKhauModel> getListNhanKhauModels() {
        return listNhanKhauModels;
    }

    public void setListNhanKhauModels(List<NhanKhauModel> listNhanKhauModels) {
        this.listNhanKhauModels = listNhanKhauModels;
    }

    public List<ThanhVienCuaHoModel> getListThanhVienCuaHo() {
        return listThanhVienCuaHo;
    }

    public void setListThanhVienCuaHo(List<ThanhVienCuaHoModel> listThanhVienCuaHo) {
        this.listThanhVienCuaHo = listThanhVienCuaHo;
    }

    @Override
    public String toString() {
        String res = "<html> <style>p {padding: 5px; margin-left: 20px} table, th, td {border: 1px solid black; border-collapse: collapse;} table {width: 500px}</style> <div>"
                + "<h3>Thông tin cơ bản"
                + "<p>Mã hộ khẩu: <b>" + hoKhauModel.getMaHoKhau() + "</p>"
                + "<p>Họ tên chủ hộ: <b>" + chuHo.getHoTen() + "</p>"
                + "<p>Địa chỉ: <b>" + hoKhauModel.getDiaChi() + "</p>"
                + "<p>Ngày lập: <b>" + hoKhauModel.getNgayLap().toString() + "</p>"
                + "<h3>Thông tin phát quà của hộ vào dịp " + leTetModel.getTenSuKien() + ", thời gian " + leTetModel.getThoiGian().toString()
                + "<p>Quà cơ bản: <b>" + leTetModel.getMotSuatQua() + "</p>"
                + "<p>Giá trị một phần quà: <b>" + leTetModel.getTongGiaTriMotSuat() + "</p>"
                + "<p>Số lượng suất quà: <b>" + soLuongQua + "</p>"
                + "<p>Tổng giá trị quà của hộ: <b>" + (long) (soLuongQua * leTetModel.getTongGiaTriMotSuat()) +" đồng" + "</p>"
                + "<p>Trạng thái nhận quà: <b>" + trangThaiNhanQua + "</p>"
                + "<p>Ngày nhận quà: <b>" + ngayNhanQua + "</p>"
                + "<p>Người ghi nhận: <b>" + nguoiGhiNhan.getUserName() + "</p>"
                + "<p>Ngày ghi nhận: <b>" + ngayGhiNhan + "</p>"
                + "<h4>Danh sách thành viên<table>"
                + "<tr>"
                + "<th>Họ tên</th>"
                + "<th>Ngày sinh</th>"
                + "<th>Giới tính</th>"
                + "<th>Quan hệ với chủ hộ</th>"
                + "</tr>";
        for (int i = 0; i < listNhanKhauModels.size(); i++) {
            res += "<tr>"
                    + "<td>"
                    + listNhanKhauModels.get(i).getHoTen()
                    + "</td>"
                    + "<td>"
                    + listNhanKhauModels.get(i).getNamSinh().toString()
                    + "</td>"
                    + "<td>"
                    + listNhanKhauModels.get(i).getGioiTinh()
                    + "</td>"
                    + "<td>"
                    + listThanhVienCuaHo.get(i).getQuanHeVoiChuHo()
                    + "</td>"
                    + "</tr>";
        }
        res += "</table></div></html>";
        return res;
    }

	public LeTetModel getLeTetModel() {
		return leTetModel;
	}

	public void setLeTetModel(LeTetModel leTetModel) {
		this.leTetModel = leTetModel;
	}

	public String getTrangThaiNhanQua() {
		return trangThaiNhanQua;
	}

	public void setTrangThaiNhanQua(String trangThaiNhanQua) {
		this.trangThaiNhanQua = trangThaiNhanQua;
	}

	public int getSoLuongQua() {
		return soLuongQua;
	}

	public void setSoLuongQua(int soLuongQua) {
		this.soLuongQua = soLuongQua;
	}

	public Date getNgayNhanQua() {
		return ngayNhanQua;
	}

	public void setNgayNhanQua(Date ngayNhanQua) {
		this.ngayNhanQua = ngayNhanQua;
	}

	public UserMoldel getNguoiGhiNhan() {
		return nguoiGhiNhan;
	}

	public void setNguoiGhiNhan(UserMoldel nguoiGhiNhan) {
		this.nguoiGhiNhan = nguoiGhiNhan;
	}

	public Date getNgayGhiNhan() {
		return ngayGhiNhan;
	}

	public void setNgayGhiNhan(Date ngayGhiNhan) {
		this.ngayGhiNhan = ngayGhiNhan;
	}

}
