package K23CNT3_LamDucTai_WebBanDienThoai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "khachhang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LdtKhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKH")
    private Integer maKH;

    @Column(name = "HoTen", nullable = false) // NOT NULL
    private String hoTen;

    @Column(name = "SDT", nullable = false) // NOT NULL
    private String sdt;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Email")
    private String email;

    @Column(name = "Username", nullable = false, unique = true) // NOT NULL & UNIQUE
    private String username;

    @Column(name = "Password", nullable = false) // NOT NULL
    private String password;
}