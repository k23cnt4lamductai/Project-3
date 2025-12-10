package K23CNT3_LamDucTai_WebBanDienThoai.repository;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtKhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LdtKhachHangRepository extends JpaRepository<LdtKhachHang, Integer> {
    Optional<LdtKhachHang> findByUsername(String username);
}