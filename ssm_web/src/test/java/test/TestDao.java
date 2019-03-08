package test;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.utils.MD5Utils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class TestDao {
    @Test
    public void findAll(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-web.xml");
        ProductDao dao = ac.getBean(ProductDao.class);
        List<Product> list = dao.findAll();
        System.out.println(list);
    }

    @Test
    public void getMD5(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String xiaoming83 = encoder.encode("123");
        System.out.println(xiaoming83);
    }
}
