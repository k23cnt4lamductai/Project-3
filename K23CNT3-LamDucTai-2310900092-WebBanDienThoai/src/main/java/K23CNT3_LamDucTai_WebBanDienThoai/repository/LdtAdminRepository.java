package K23CNT3_LamDucTai_WebBanDienThoai.repository;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LdtAdminRepository extends JpaRepository<LdtAdmin, Integer> {
    Optional<LdtAdmin> findByUsername(String username);
}