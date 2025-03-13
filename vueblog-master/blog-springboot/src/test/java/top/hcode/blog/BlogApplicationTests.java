package top.hcode.blog;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hcode.blog.entity.MEvent;
import top.hcode.blog.mapper.MEventMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@MapperScan("top.hcode.blog.mapper")
class BlogApplicationTests {

    @Test
    void contextLoads() {
        int num[] = {1,2};
        List list = Collections.singletonList(num);
        Collections.sort(list);
        System.out.println(list.get(0));
    }

    @Autowired
    private MEventMapper mEventMapper;

    @Test
    public void testSelectA(){

        DataSource dataSource = new MysqlDataSource();
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("数据库连接成功：" + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("select all method test");
        List<MEvent> eventList = mEventMapper.selectList(null);
        for(MEvent mEvent: eventList){
            System.out.println("get mEvent");
            System.out.println(mEvent.toString());
        }
    }

}
