package K23CNT3_LamDucTai_WebBanDienThoai.service;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtDanhMucSP;
import java.util.List;

public interface LdtDanhMucService {
    List<LdtDanhMucSP> findAll();
    LdtDanhMucSP findById(Integer id);
    LdtDanhMucSP save(LdtDanhMucSP dm);
    void delete(Integer id);
}