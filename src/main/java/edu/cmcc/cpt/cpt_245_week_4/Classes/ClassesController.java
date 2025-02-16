package edu.cmcc.cpt.cpt_245_week_4.Classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class ClassesController {

  @Autowired
  private jdbcTemplate jdbcTemplate;

  @GetMapping
  public List<classes> getAllClasses() {
        String sql = "SELECT * FROM classes;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
          Classes classes = new Classes();
          classes.setClassCode(rs.getString(columnLabel: "class_code"));
          classes.setClassName(rs.getString(columnLabel: "class_code"));
          return classes;
           
        });
    }

    @GetMapping("/{id}")
    public Student getClassByCode(@PathVariable int class_code) {
        String sql = "SELECT * FROM classes WHERE class_code = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
          Classes classes = new Classes();
          classes.setClassCode(rs.getString(columnLabel: "class_code"));
          classes.setClassName(rs.getString(columnLabel: "class_code"));
          return classes;
        });
    }

    @PostMapping
    public void createClass(@RequestBody Classes classes) {
        String sql = "INSERT INTO classes (class_code, class_name) VALUES (?, ?)";
        jdbcTemplate.update(sql, Classes);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getName(), student.getAge(), student.getEmail(), id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

}
