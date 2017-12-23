import com.bdqn.entity.TUser;
import com.bdqn.service.TUserService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/12/6/006.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TUserServiceTest {
    @Resource
    private TUserService tUserService;
    //1.用户登录
    @Test
    public void login() throws Exception {
//        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//        TUserService tUserService=(TUserService)context.getBean("tUserService");
        TUser user=new TUser();
        user.setUserName("admin");
        user.setPassword("abc");
        TUser tUser=tUserService.login(user);
        if (tUser != null) {
            System.out.println(tUser);
        }
    }

    //2.查询所有用户信息  进行分页查询
    @Test
    public void testQueryAll() {
        PageInfo<TUser> pageInfo=tUserService.queryAll(-1,3);
        List<TUser> list=pageInfo.getList();

        System.out.println("当前是第："+pageInfo.getPageNum()+"页");
        System.out.println("页长："+pageInfo.getPageSize());
        System.out.println("总记录数："+pageInfo.getTotal());
        if(pageInfo!=null){
            for (TUser tUser : list) {
                System.out.println(tUser);
            }
        }
    }
}