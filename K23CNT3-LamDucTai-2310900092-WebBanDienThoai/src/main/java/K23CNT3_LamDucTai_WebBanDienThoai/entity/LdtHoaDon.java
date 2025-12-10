package K23CNT3_LamDucTai_WebBanDienThoai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hoadon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LdtHoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHD")
    private Integer maHD;

    @ManyToOne
    @JoinColumn(name = "MaKH")
    private LdtKhachHang khachHang;

    @Column(name = "NgayLap")
    private java.sql.Date ngayLap;

    @Column(name = "TongTien")
    private Double tongTien;
}