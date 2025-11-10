package K23CNT3.LdtDay3.Controller;


import K23CNT3.LdtDay3.Entity.LdtStudent;
import K23CNT3.LdtDay3.Service.LdtServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class LdtStudentController {
    @Autowired
    private LdtServiceStudent studentService;
    @GetMapping("/student-list")
    public List<LdtStudent> getAllStudents() {
        return studentService.getStudents();
    }
    @GetMapping("/student/{id}")
    public LdtStudent getAllStudents(@PathVariable String id)
    {
        Long param = Long.parseLong(id);
        return studentService.getStudent(param);
    }
    @PostMapping("/student-add")
    public LdtStudent addStudent(@RequestBody LdtStudent student)
    {
        return studentService.addStudent(student);
    }
    @PutMapping("/student/{id}")
    public LdtStudent updateStudent(@PathVariable String id,
                                     @RequestBody LdtStudent student) {
        Long param = Long.parseLong(id);
        return studentService.updateStudent(param,
                student);
    }
    @DeleteMapping("/student/{id}")
    public boolean deleteStudent(@PathVariable String id) {
        Long param = Long.parseLong(id);
        return studentService.deleteStudent(param);
    }
}
