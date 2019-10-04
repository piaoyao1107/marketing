import com.alibaba.fastjson.JSON;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Marketing {

    private String token;
    private String authorId;

    //登陆接口
    @BeforeClass
    public void testLogin(){
        Response response =
                given().
                        formParams("access_token","2496b3771d024f01b2e5f38b155c877e",
                                "app_key","test",
                                "data","%7B%22mobile%22%3A%2218514506336%22%2C%22password%22%3A%22YTExMTExMQ%3D%3D%22%7D",
                                "format","json",
                                "name","gwy.base.login",
                                "sign","BF4D59FF8A18584F66B8EE957EAF4E76",
                                "timestamp","2019-09-27 12:14:05",
                                "version","").
                        when().
                        post("https://admin-test300.newtamp.cn/api/admin/gwy").
                        then().
                        body("code",equalTo("0")).
                        extract().response();
        System.out.println("【登陆】接口返回信息是："+response.asString());
        token = response.path("data.token");
//        System.out.println("接口返回的token是："+resToken);
    }

//
//    //添加分类接口
//    @Test
//    public void testAddCategory(){
//        long nowDate =  new Date().getTime();
//        String categoryName = "分类"+nowDate;
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("name",categoryName);
//        String mapJson = JSON.toJSONString(map);
//        System.out.println("mapJson的值是："+mapJson);
//        Response response =
//                given().
//                        body(mapJson).
//                        headers("token",token,
//                                "Content-Type","application/json; charset=UTF-8").
//                        when().
//                        post("https://admin-test300.newtamp.cn/api/mc/v1/content/category").
//                        then().
//                        body("msg",equalTo("成功")).
//                        body("success",equalTo(true)).
//                        body("param.status",equalTo("INITIALIZED")).
//                        extract().response();
//        System.out.println("【添加分类】接口返回信息是："+response.asString());
//    }
//
//    //查询分类列表接口
//    @Test
//    public void testListCategory(){
//        Response response =
//                given().
//                        headers("Authorization",token,
//                                "token",token).
//                        when().
//                        get("https://admin-test300.newtamp.cn/api/mc/v1/content/category/list?page=0&pageSize=20").
//                        then().
//                        body("msg",equalTo("成功")).
//                        extract().response();
//        System.out.println("【查询分类列表1】接口返回信息是："+response.asString());
//    }
//
//    //查询作者列表接口
//    @Test
//    public void testListAuthor(){
//        Response response =
//                given().
//                        headers("Authorization",token,
//                                "token",token).
//                        when().
//                        get("https://admin-test300.newtamp.cn/api/mc/v2/content/list?page=0&pageSize=20&categoryId=&authorId=&media=").
//                        then().log().ifError().
//                        body("msg",equalTo("成功")).
//                        extract().response();
//        System.out.println("【查询作者列表】接口返回信息是："+response.asString());
//    }

    //添加作者
    @Test(priority = 1)
    public void testAddAuthor(){

        long nowDate =  new Date().getTime();
        String authorName = "作者"+nowDate;
        String avatar = "https://fs.newbanker.cc/img/2099/2019/9/28/7480d22aee1945fba582dbd931ad016c.jpg";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name",authorName);
        map.put("avatar",avatar);
        String mapJson = JSON.toJSONString(map);

        Response response =
                given().
                        headers("Authorization",token,
                                "token",token,
                                "Content-Type","application/json; charset=UTF-8").
                        body(mapJson).
                        when().
                        post("https://admin-test300.newtamp.cn/api/mc/v1/content/author").
                        then().log().ifError().
                        body("msg",equalTo("成功")).
                        extract().response();
        System.out.println("【添加作者】接口返回信息是："+response.asString());

        //获取作者id
        authorId = response.path("param.id");


    }

    //修改作者name
    @Test(priority = 2)
    public void testEditAuthor(){

        //拼接请求地址
        String getAuthorUrl = "https://admin-test300.newtamp.cn/api/mc/v1/content/author?authorId="+authorId;

        Response response =
                given().
                        headers("Authorization",token,
                                "token",token,
                                "Content-Type","application/json; charset=UTF-8").
                        when().
                        get(getAuthorUrl).
                        then().log().ifError().
                        body("msg",equalTo("成功")).
                        extract().response();
        System.out.println("【编辑作者】接口返回信息是："+response.asString());
        String timeCreated = response.path("param.timeCreated");
        String timeLastUpdated = response.path("param.timeLastUpdated");
        String groupId = response.path("param.groupId");
        String avatar = response.path("param.avatar");
        String status = response.path("param.status");
        String channel = response.path("param.channel");
        int contentCount = response.path("param.contentCount");
        int shareNumber = response.path("param.shareNumber");

        long nowDate =  new Date().getTime();
        String authorName = "作者"+nowDate;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("avatar",avatar);
        map.put("channel",channel);
        map.put("contentCount",contentCount);
        map.put("groupId",groupId);
        map.put("id",authorId);
        map.put("name",authorName);
        map.put("shareNumber",shareNumber);
        map.put("status",status);
        map.put("timeCreated",timeCreated);
        map.put("timeLastUpdated",timeLastUpdated);

        String mapJson = JSON.toJSONString(map);

        Response response1 =
                given().
                        headers("Authorization",token,
                                "token",token,
                                "Content-Type","application/json; charset=UTF-8").
                        body(mapJson).
                        when().
                        post("https://admin-test300.newtamp.cn/api/mc/v1/content/author").
                        then().log().ifError().
                        body("msg",equalTo("成功")).
                        extract().response();
        System.out.println("【保存编辑作者】接口返回信息是："+response1.asString());
    }

    //删除作者
    @Test(priority = 3,enabled = false)
    public void testDeleteAuthor(){

        String deleteAuthorUrl = "https://admin-test300.newtamp.cn/api/mc/v1/content/author/archive?authorId="+authorId;

        Response response =
                given().
                        headers("Authorization",token,
                                "token",token,
                                "Content-Type","application/json; charset=UTF-8").
                        when().
                        post(deleteAuthorUrl).
                        then().log().ifError().
                        body("msg",equalTo("成功")).
                        extract().response();
        System.out.println("【删除作者】接口返回信息是："+response.asString());

    }
}
