package K23CNT3_LamDucTai_WebBanDienThoai.repository;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtKhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface LdtKhachHangRepository extends JpaRepository<LdtKhachHang, Integer> {
    Optional<LdtKhachHang> findByUsername(String username);

    // Thêm chức năng tìm kiếm khách hàng theo tên (không phân biệt hoa thường)
    List<LdtKhachHang> findByHoTenContainingIgnoreCase(String hoTen);
}