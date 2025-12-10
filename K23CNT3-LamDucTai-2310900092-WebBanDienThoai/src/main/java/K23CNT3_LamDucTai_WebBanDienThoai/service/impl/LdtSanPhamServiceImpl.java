package K23CNT3_LamDucTai_WebBanDienThoai.service.impl;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtSanPham;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtSanPhamRepository;
import K23CNT3_LamDucTai_WebBanDienThoai.service.LdtSanPhamService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class LdtSanPhamServiceImpl implements LdtSanPhamService {

    @Autowired
    private LdtSanPhamRepository repo;

    @Override
    public List<LdtSanPham> findAll() { return repo.findAll(); }

    @Override
    public LdtSanPham findById(Integer id) { return repo.findById(id).orElse(null); }

    @Override
    public LdtSanPham save(LdtSanPham sp) { return repo.save(sp); }

    @Override
    public void deleteById(Integer id) { repo.deleteById(id); }

    @Override
    public List<LdtSanPham> findByDanhMuc(Integer maDMSP) { return repo.findByDanhMucMaDMSP(maDMSP); }

    @Override
    public List<LdtSanPham> search(String q) { return repo.findByTenSPContainingIgnoreCase(q); }
}