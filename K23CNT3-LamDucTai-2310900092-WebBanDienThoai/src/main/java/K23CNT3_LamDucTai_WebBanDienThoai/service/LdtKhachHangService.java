package K23CNT3_LamDucTai_WebBanDienThoai.service;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtKhachHang;
import java.util.List;
import java.util.Optional; // Cần import Optional

public interface LdtKhachHangService {

    // =============================
    // Phương thức CRUD cơ bản (Giữ nguyên)
    // =============================
    List<LdtKhachHang> findAll();

    // Nên sử dụng Optional cho findById để xử lý trường hợp không tìm thấy
    Optional<LdtKhachHang> findById(Integer id);

    LdtKhachHang save(LdtKhachHang kh);

    void delete(Integer id);

    // =============================
    // Phương thức nghiệp vụ (Authentication/Authorization)
    // =============================

    Optional<LdtKhachHang> login(String username, String password);

    LdtKhachHang registerKhachHang(LdtKhachHang khachHang);

    boolean isUsernameExist(String username);
}