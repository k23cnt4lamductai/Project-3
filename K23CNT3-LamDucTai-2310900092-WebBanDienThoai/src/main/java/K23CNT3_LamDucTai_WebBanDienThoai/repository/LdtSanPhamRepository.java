package K23CNT3_LamDucTai_WebBanDienThoai.repository;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LdtSanPhamRepository extends JpaRepository<LdtSanPham, Integer> {
    List<LdtSanPham> findByDanhMucMaDMSP(Integer maDMSP);
    List<LdtSanPham> findByTenSPContainingIgnoreCase(String q);
}