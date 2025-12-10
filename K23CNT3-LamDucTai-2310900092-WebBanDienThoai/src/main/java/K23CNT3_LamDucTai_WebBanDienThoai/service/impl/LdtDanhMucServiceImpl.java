package K23CNT3_LamDucTai_WebBanDienThoai.service.impl;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtDanhMucSP;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtDanhMucSPRepository;
import K23CNT3_LamDucTai_WebBanDienThoai.service.LdtDanhMucService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class LdtDanhMucServiceImpl implements LdtDanhMucService {

    @Autowired
    private LdtDanhMucSPRepository repo;

    @Override
    public List<LdtDanhMucSP> findAll() { return repo.findAll(); }

    @Override
    public LdtDanhMucSP findById(Integer id) { return repo.findById(id).orElse(null); }

    @Override
    public LdtDanhMucSP save(LdtDanhMucSP dm) { return repo.save(dm); }

    @Override
    public void delete(Integer id) { repo.deleteById(id); }
}