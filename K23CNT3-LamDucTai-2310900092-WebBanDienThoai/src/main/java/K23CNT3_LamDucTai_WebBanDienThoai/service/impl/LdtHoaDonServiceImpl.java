package K23CNT3_LamDucTai_WebBanDienThoai.service.impl;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtHoaDon;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtHoaDonRepository;
import K23CNT3_LamDucTai_WebBanDienThoai.service.LdtHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LdtHoaDonServiceImpl implements LdtHoaDonService {

    @Autowired
    private LdtHoaDonRepository repo;

    @Override
    public List<LdtHoaDon> findAll() { return repo.findAll(); }

    @Override
    public LdtHoaDon findById(Integer id) { return repo.findById(id).orElse(null); }

    @Override
    public LdtHoaDon save(LdtHoaDon hd) { return repo.save(hd); }
}