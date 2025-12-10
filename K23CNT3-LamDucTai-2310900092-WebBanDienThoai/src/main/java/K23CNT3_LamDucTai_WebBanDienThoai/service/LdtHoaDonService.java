package K23CNT3_LamDucTai_WebBanDienThoai.service;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtHoaDon;
import java.util.List;

public interface LdtHoaDonService {
    List<LdtHoaDon> findAll();
    LdtHoaDon findById(Integer id);
    LdtHoaDon save(LdtHoaDon hd);
}