package K23CNT3_LamDucTai_WebBanDienThoai.service.impl;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtKhachHang;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtKhachHangRepository;
import K23CNT3_LamDucTai_WebBanDienThoai.service.LdtKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional; // Cần thiết cho các phương thức find và login

@Service
public class LdtKhachHangServiceImpl implements LdtKhachHangService {

    @Autowired
    private LdtKhachHangRepository repo;

    // =======================================================
    // Phương thức CRUD cơ bản
    // =======================================================

    @Override
    public List<LdtKhachHang> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<LdtKhachHang> findById(Integer id) {
        // Thay vì .orElse(null), trả về Optional để thống nhất với Interface
        return repo.findById(id);
    }

    @Override
    public LdtKhachHang save(LdtKhachHang kh) {
        return repo.save(kh);
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }

    // =======================================================
    // Phương thức nghiệp vụ (Đăng ký/Đăng nhập)
    // =======================================================

    @Override
    public LdtKhachHang registerKhachHang(LdtKhachHang khachHang) {
        // Trong môi trường phát triển: chỉ cần lưu
        // TODO: Trong thực tế, bạn sẽ mã hóa mật khẩu ở đây trước khi lưu!
        return repo.save(khachHang);
    }

    @Override
    public boolean isUsernameExist(String username) {
        // Kiểm tra xem Repository có tìm thấy username đó không
        return repo.findByUsername(username).isPresent();
    }

    @Override
    public Optional<LdtKhachHang> login(String username, String password) {
        // 1. Tìm kiếm khách hàng bằng username
        Optional<LdtKhachHang> khachHangOpt = repo.findByUsername(username);

        if (khachHangOpt.isPresent()) {
            LdtKhachHang kh = khachHangOpt.get();

            // 2. So sánh mật khẩu
            // Lưu ý: Hiện tại là so sánh chuỗi trần vì DB của bạn lưu mật khẩu trần ('123456')
            // Khi dùng BCryptPasswordEncoder, logic sẽ là:
            // if (passwordEncoder.matches(password, kh.getPassword()))

            if (kh.getPassword().equals(password)) {
                return khachHangOpt; // Đăng nhập thành công
            }
        }

        return Optional.empty(); // Đăng nhập thất bại (Không tìm thấy user hoặc sai password)
    }
}
