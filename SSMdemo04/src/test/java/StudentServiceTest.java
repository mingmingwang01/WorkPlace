import com.bdqn.entity.Grade;
import com.bdqn.entity.Student;
import com.bdqn.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/13/013.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StudentServiceTest {

    @Resource
    private StudentService studentService;
    //1.分页查询所有学生信息
    @Test
    public void queryAll() throws Exception {
        PageInfo<Student> pageInfo = studentService.queryAll(2, 3);
        if (null != pageInfo) {
            System.out.println(pageInfo);
            List<Student> list=pageInfo.getList();
            System.out.println("当前是第："+pageInfo.getPageNum()+"页");
            System.out.println("页长："+pageInfo.getPageSize());
            System.out.println("总记录数："+pageInfo.getTotal());
            if (null != list) {
                for (Student student : list) {
                    System.out.println(student);
                }
            }
        }
    }
    //2.批量删除学生
    @Test
    public void deleteStudentByIds() throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        list.add(90);
        list.add(50);
        list.add(60);
        int n=studentService.deleteStudentByIds(list);
        System.out.println(n);
    }
    //3.添加学生信息
    @Test
    public void addStudent() throws Exception {
        Student student=new Student();
        student.setStudentName("明明");
        //student.setStudentNum("tcmp063001");
        student.setAge(18);
        student.setGender("男");

        Grade grade=new Grade();
        grade.setId(4);
        grade.setGradeName("TCMP0900AA");

        student.setGrade(grade);

        int n=studentService.addStudent(student);
        System.out.println(n);
    }

    //4.通过id查询学生
    @Test
    public void queryStudentById() throws Exception {
        Student student=studentService.queryStudentById(1);
        if(student!=null){
            System.out.println(student);
        }
    }
    //5.修改学生信息
    @Test
    public void updateStudent() throws Exception {
        Student student=new Student();
        student.setId(90);
        student.setStudentName("丽丽");
        student.setAge(10);
        student.setGender("女");

        Grade grade=new Grade();
        grade.setId(10);

        student.setGrade(grade);
        int n = studentService.updateStudent(student);
        System.out.println(n);
    }


}