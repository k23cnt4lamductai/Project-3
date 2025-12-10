package K23CNT3_LamDucTai_WebBanDienThoai.service;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtKhachHang;
import java.util.List;

public interface LdtKhachHangService {
    List<LdtKhachHang> findAll();
    LdtKhachHang findById(Integer id);
    LdtKhachHang save(LdtKhachHang kh);
    void delete(Integer id);
}