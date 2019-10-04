import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.config.XmlConfig.xmlConfig;
import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class Demo1 {

    @Test
    public void test1() {
        int statucecode = get("http://www.baidu.com/").statusCode();
        assertEquals(200, statucecode);
    }


    @Test
    public void testRes(){
        Response response =
                given().
                        when().
                        get("http://www.baidu.com/").
                        then().
                        body("statusCode",equalTo(200)).
                        extract().response();
        String headerValue = response.header("Connection");
        System.out.println("Connection的值是："+headerValue);
    }

    @Test
    public void test2() {
        String json = get("http://www.baidu.com").asString();
        System.out.println("返回信息是："+json);
        String contentType = get("http://www.baidu.com").getContentType();
        System.out.println("返回中，ContentType是："+contentType);
        int statusCode = get("http://www.baidu.com").getStatusCode();
        System.out.println("返回中，statusCode是："+statusCode);
        Headers headers = get("http://www.baidu.com").getHeaders();
        System.out.println("返回中，heades是："+headers);
        String contentEncoding = get("http://www.baidu.com").getHeader("Content-Encoding");
        System.out.println("返回中，Content-Encoding是："+contentEncoding);
        Map<String, String> allCookies = get("http://www.baidu.com").getCookies();
        System.out.println("返回中，cookies是："+allCookies);
        long takeTime = get("http://www.baidu.com").getTime();
        System.out.println("接口响应耗时："+takeTime+"毫秒");


    }

    @Test
    public void test_m(){


        given().
                formParams("access_token", "0a7e7ceef737490cb3cbb2f360b32010",
                        "app_key", "test",
                        "data","%7B%22mobile%22%3A%2218514506336%22%2C%22password%22%3A%22YTExMTExMQ%3D%3D%22%7D",
                        "format","json",
                        "name","gwy.base.login",
                        "sign","83E9AADC3FFE889068F23DEB8F39D50D",
                        "timestamp","2019-09-26 12:37:35",
                        "version","");
                String json = post("https://admin-test300.newtamp.cn/api/admin/gwy").asString();
                System.out.println("返回信息是："+json);


//                then().
//                body("code",equalTo("0"));
    }

    @Test
    public void test_marketing(){
        Response response =
                given().
                formParams("access_token", "b3ba64d3907e45c489f2f07f1147d237",
                        "app_key", "test",
                        "data","%7B%22mobile%22%3A%2218514506336%22%2C%22password%22%3A%22YTExMTExMQ%3D%3D%22%7D",
                        "format","json",
                        "name","gwy.base.login",
                        "sign","259D3FD2A7CEE4442A04FDB062AE4614",
                        "timestamp","2019-09-26 13:04:20",
                        "version","").
                when().
                post("https://admin-test300.newtamp.cn/api/admin/gwy").
                then().
                body("code",equalTo("0")).extract().response();
        int statusCode = response.getStatusCode();
        System.out.println("返回code："+statusCode);
        String json = response.asString();
        System.out.println("返回的response："+json);
    }

    @Test
    public void testAddCategory(){
        Response response =
        given().
                formParams("name", "分类092703").
//                queryParam("name","分类092703").
                //formParams("name", "分类092702").
                headers("Authorization","f1a1c5c512a84d84945c21de140724c8",
                        "token","f1a1c5c512a84d84945c21de140724c8").
                when().
                post("https://admin-test300.newtamp.cn/api/mc/v1/content/category").
                then().
                body("code",equalTo("1")).
                extract().response();
        System.out.println("接口返回信息是："+response);
    }


    @Test
    public void testListCategory(){
        String json =
                given().
                        headers("Authorization","2496b3771d024f01b2e5f38b155c877e",
                                "token","2496b3771d024f01b2e5f38b155c877e").
                        when().
                        get("https://admin-test300.newtamp.cn/api/mc/v1/content/category/list?page=0&pageSize=20").asString();
        System.out.println("接口返回信息是："+json);
    }

    @Test
    public void testListCategory2(){
        String json =
                given().
                        headers("Authorization","2496b3771d024f01b2e5f38b155c877e",
                                "token","2496b3771d024f01b2e5f38b155c877e").
                        when().
                        get("https://admin-test300.newtamp.cn/api/mc/v1/content/category/list?page=0&pageSize=20").
                        then().
                        log().ifError().
                        body("msg",equalTo("成功")).extract().asString();
//                        extract().response();
        System.out.println("接口返回信息是："+json);
    }

    @Test
    public void test3() {
        given()
                .when()
                .get("http://localhost:8892/lotto")
                .then()
                .body("lotto.winners.winnerId", hasItems(23, 54));

    }

    @Test
    public void test4() {
        given()
                .when()
                .get("http://localhost:8892/price")
                .then()
                .body("price", is(12.12f));

    }

//    @Test
//    public void testfour(){
//        given()
//                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
//                .when()
//                .get("http://localhost:8889/price")
//                .then()
//                .body("price",is(new BigDecimal(12.12)));
//
//    }

    @Test
    public void test5() {
        given()
                .when()
                .get("http://localhost:8892/lotto")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("products-schema.json"));

    }

//    @Test
//    public void test6(){
//        //post入参为form 表单参数，返回类型为xml
//        given()
//                .proxy(8888)//连接代理
//                .formParam("firstName", "John")
//                .formParam("lastName","Doe")
//                .when()
//                .post("http://localhost:8892/getxml")
//                .then()
//                .using()
//                .defaultParser(Parser.XML)//返回类型是xml格式
//                .body("greeting.firstName",equalTo("John"))
//                .body("greeting.lastName",equalTo("Doe"));//校验结果
//    }

    @Test
    public void test7() {

        with().formParams("firstName", "John", "lastName", "Doe").when().post("http://localhost:8892/getxml").then().body("greeting.firstName", equalTo("John"), "greeting.lastName", equalTo("Doe"));

    }

    @Test
    public void test70(){
        given().
                formParams("firstName", "John", "lastName", "Doe").
                when().
                post("http://localhost:8892/getxml").
                then().
                body("greeting.firstName", equalTo("John")).
                body("greeting.lastName", equalTo("Doe"));
    }

    @Test
    public void test8() {
        given().
                config(RestAssured.config().xmlConfig(xmlConfig().declareNamespace("test", "http://localhost/"))).
                when().
                get("http://localhost:8892/getxmlwithnamespace").
                then().
                body("foo.bar.text()", equalTo("sudo make me a sandwich!")).
                body(":foo.:bar.text()", equalTo("sudo ")).
                body("foo.test:bar.text()", equalTo("make me a sandwich!"));

    }

    @Test
    public void test9(){
        given().parameters("firstName", "John", "lastName", "Doe").when().post("http://localhost:8892/getxmlwithjson").then().body(hasXPath("/greeting/firstName", containsString("Jo")));
    }

    @Test
    public void test10(){
        Map map = new HashMap();
        map.put("firstName","John");
        map.put("lastName","Doe");

        given()
                .proxy(8888)
                .body(map)
                .when()
                .post("http://localhost:8892/getxmlwithjson")
                .then()
                .body(hasXPath("/greeting/firstName",containsString("Jo")));
    }


    //调用一个post接口，不带入参，返回json格式
    @Test
    public void test11(){
        given().
                when().
                post("http://localhost:8892/postdemo").
                then().
                body("login", equalTo("success")).
                body("msg",equalTo("恭喜你登陆成功！"));
    }

    //调用一个post接口，带参数，返回json格式
    @Test
    public void test12(){
        given().
                formParams("count", "13900001111", "status", "1").
                when().
                post("http://localhost:8892/postwithparam").
                then().
                body("login",equalTo("success"));
    }

    //调用一个get接口，不带参数，返回json格式
    @Test
    public void test13() {
        given()
                .when()
                .get("http://localhost:8892/getdemo")
                .then()
                .body("score.English", equalTo("88"));

    }

    //验证Rest Assured的日志功能
    @Test
    public void test131() {
        given().
                log().all().
                when().
                get("http://localhost:8892/getdemo").
                then().
                log().body().
                body("score.English", equalTo("88"));

    }

    //调用一个get接口，带参数，返回json格式
    @Test
    public void test14(){
        given().
                formParams("count","13900001111","status","1").
                when().
                get("http://localhost:8892/getwithparam").
                then().
                body("search",equalTo("success")).
                body("score.Chinese",equalTo("97"));
    }



}