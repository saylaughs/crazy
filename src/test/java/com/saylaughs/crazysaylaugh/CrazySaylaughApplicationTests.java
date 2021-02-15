package com.saylaughs.crazysaylaugh;

import com.github.pagehelper.PageInfo;
import com.saylaughs.crazysaylaugh.Dao.TestMapper;
import com.saylaughs.crazysaylaugh.conf.ServerConfig;
import com.saylaughs.crazysaylaugh.pojo.Say_User;
import com.saylaughs.crazysaylaugh.service.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@Slf4j
class CrazySaylaughApplicationTests {

    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;

    @Autowired
    ServerConfig  serverConfig;

    @Autowired
    CommentService commentService;

    @Autowired
   ReplyService replyService;

    @Autowired
    TestMapper testMapper;


    @Test
    void contextLoads() {
        int y = replyService.addLike("34", "Y");
        System.out.println(y);
//
//        com.saylaughs.crazysaylaugh.pojo.Test test=new com.saylaughs.crazysaylaugh.pojo.Test();
//        test.setSay_name("亮亮");
//         testMapper.add(test);
//        System.out.println(test.toString());

//        List<Say_Reply> replyForComment = replyService.getReplyForComment(1);
//        for (Say_Reply say_reply:replyForComment){
//            System.out.println(say_reply.toString());
//        }
//        System.out.println(replyForComment.size());
//         log.debug("hello----debug");
//         log.info("hello ----info");
//         log.warn("hello----warn");
//         log.error("hello ----error");
//        List<Say_Article> list = articleService.allArticle(new Say_Article(),1,5);
//        for (Say_Article say_article:list) {
//            System.out.println(say_article.toString());
//        }

    }

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    void test2() {
        redisTemplate.opsForValue().set("hwl","就哈哈哈哈哈");
    }

    @Test
    void test3() {
        PageInfo<Say_User> pageInfo = userService.queryUserListPaged(new Say_User(), 1, 5);
//        int[] navigatepageNums = pageInfo.getNavigatepageNums();
//        for (int i=0;i<navigatepageNums.length;i++){
//            System.out.println(navigatepageNums[i]);
//        }
//        List<Say_User> list = pageInfo.getList();
//        for (Say_User say:list) {
//            System.out.println(say.toString());
//        }
        //int navigateFirstPage = pageInfo.getNavigateFirstPage();
//        System.out.println(navigateFirstPage);
//        System.out.println(1);
    }

}
