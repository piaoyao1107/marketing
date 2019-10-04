import com.alibaba.fastjson.annotation.JSONField;



public class Param {

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "age")
    private int age;

    public Param(String name,int age) {
        super();
        this.name = name;
        this.age = age;
    }
}
