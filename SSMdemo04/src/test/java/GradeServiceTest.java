import com.bdqn.entity.Grade;
import com.bdqn.service.GradeService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9/009.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class GradeServiceTest {
    @Resource
    private GradeService gradeService;

    @Test
    public void deleteGradeById() throws Exception {
        int n=gradeService.deleteGradeById(38);
        System.out.println(n);
    }

    @Test
    public void deleteGradeByIds() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(31);
        list.add(32);
        list.add(33);
        Integer n = gradeService.deleteGradeByIds(list);
        System.out.println(n);
    }

    //根据年级id查询年级内所有学员信息
    @Test
    public void queryGradeById() throws Exception {
        Grade grade=gradeService.queryGradeById(1);

        System.out.println(grade);
    }

    //4.查询年级内所有学生信息
    @Test
    public void queryAllStudent() throws Exception {
        PageInfo<Grade> pageInfo=gradeService.queryAll(2,3);
        if(null!=pageInfo){
            List<Grade> list=pageInfo.getList();
            if(null!=list){
                for (Grade grade : list) {
                    System.out.println(grade);
                }
            }
            System.out.println("一共" + pageInfo.getTotal() + "条记录");
            System.out.println("一共" + pageInfo.getPages() + "页");
        }
    }

    //5.添加年级信息
    @Test
    public void addGrade() throws Exception {
        Grade grade=new Grade();
        grade.setGradeName("tcmp088");
        grade.setDetails("tcmp088的描述");
        int n = gradeService.addGrade(grade);
        System.out.println(n);
        //打印输出添加获取的年级编号
        System.out.println(grade.getId());
    }

    //6.修改年级信息
    @Test
    public void updateGrade() throws Exception {
        Grade grade=new Grade();
        grade.setId(43);
        grade.setDetails("描述描述");
        int n = gradeService.updateGrade(grade);
        System.out.println(n);
    }


}