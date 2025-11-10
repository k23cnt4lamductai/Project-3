package K23CNT3.LdtDay3.Service;

import K23CNT3.LdtDay3.Entity.LdtStudent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class LdtServiceStudent {
    List<LdtStudent> students
            = new ArrayList<LdtStudent>();
    public LdtServiceStudent() {
        students.addAll(Arrays.asList(
                new LdtStudent(1L,"Lâm Đức Tài",22,"male","HN","0327047118","taidz123@gmail.com"),
                new LdtStudent(2L,"Devmaster 2",25,"Non","Số 25VNP","0978611889","contact@devmaster.edu.vn"),
                new LdtStudent(3L,"Devmaster 3",22,"Non","Số 25VNP","0978611889","chungtrinhj@gmail.com")
        ));
    }
    // Lấy toàn bộ danh sách sinh viên
    public List<LdtStudent> getStudents() {
        return students;
    }
    // Lấy sinh viên theo id
    public LdtStudent getStudent(Long id) {
        return students.stream()
                .filter(student -> student
                        .getId().equals(id))
                .findFirst().orElse(null);
    }
    // Thêm mới một sinh viên
    public LdtStudent addStudent(LdtStudent student) {
        students.add(student);
        return student;
    }
    // Cập nhật thông tin sinh viên
    public LdtStudent updateStudent(Long id, LdtStudent student)
    {
        LdtStudent check = getStudent(id);
        if (check == null) {
            return null;
        }
        students.forEach(item -> {
            if (item.getId()==id) {
                item.setName(student.getName());
                item.setAddress(student.getAddress());
                item.setEmail(student.getEmail());
                item.setPhone(student.getPhone());
                item.setAge(student.getAge());
                item.setGender(student.getGender());
            }
        });
        return student;
    }
    // Xóa thông tin sinh viên
    public boolean deleteStudent(Long id){
        LdtStudent check = getStudent(id);
        return students.remove(check);
    }
}