package com.wangjiayu.springboot;

import com.wangjiayu.springboot.mapper.UserMapper;
import com.wangjiayu.springboot.model.User;
import com.wangjiayu.springboot.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootApplicationTests {

    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;

    @Test
    public void test4() {
        redisUtil.set("usersafdsa", 1321312);
        redisUtil.set("usersaffdsafdsa", "fdsafdasf");
        //System.out.println(redisUtil.get("shiro:cache:retrylimit:root"));
    }

    @Test
    public void test1() {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() {
        User root = userMapper.findUserByName("root");
        System.out.println(root);
    }

    @Test
    public void searchUser() {
        //1.创建QueryBuilder(即设置查询条件)这儿创建的是组合查询(也叫多条件查询),后面会介绍更多的查询方法
        /*组合查询BoolQueryBuilder
         * must(QueryBuilders)   :AND
         * mustNot(QueryBuilders):NOT
         * should:               :OR
         */
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //builder下有must、should以及mustNot 相当于sql中的and、or以及not
        //设置模糊搜索,博客的简诉中有学习两个字
        builder.must(QueryBuilders.fuzzyQuery("name", "wangjiayu5"));
        //按照博客的评论数的排序是依次降低
        FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        //设置分页(从第一页开始，一页显示10条)
        //注意开始是从0开始，有点类似sql中的方法limit 的查询
        PageRequest page1 = new PageRequest(0, 3);

        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(page1);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        ////3.执行方法1
        //Page<User> page = userES.search(query);
        ////4.获取总条数(用于前端分页)
        //int total = (int) page.getTotalElements();
        ////5.获取查询到的数据内容（返回给前端）
        //List<User> content = page.getContent();
        //
        //System.err.println(total);
        //for (User user : content) {
        //    System.err.println(user);
        //}
    }

}
