package K23CNT3_LamDucTai_WebBanDienThoai.service;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtSanPham;
import java.util.List;

public interface LdtSanPhamService {
    List<LdtSanPham> findAll();
    LdtSanPham findById(Integer id);
    LdtSanPham save(LdtSanPham sp);
    void deleteById(Integer id);
    List<LdtSanPham> findByDanhMuc(Integer maDMSP);
    List<LdtSanPham> search(String q);
}