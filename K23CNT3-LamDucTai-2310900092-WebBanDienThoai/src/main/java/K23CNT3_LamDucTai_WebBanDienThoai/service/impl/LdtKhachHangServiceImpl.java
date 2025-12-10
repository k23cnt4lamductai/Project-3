package K23CNT3_LamDucTai_WebBanDienThoai.service.impl;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtKhachHang;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtKhachHangRepository;
import K23CNT3_LamDucTai_WebBanDienThoai.service.LdtKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LdtKhachHangServiceImpl implements LdtKhachHangService {

    @Autowired
    private LdtKhachHangRepository repo;

    @Override
    public List<LdtKhachHang> findAll() { return repo.findAll(); }

    @Override
    public LdtKhachHang findById(Integer id) { return repo.findById(id).orElse(null); }

    @Override
    public LdtKhachHang save(LdtKhachHang kh) { return repo.save(kh); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }
}