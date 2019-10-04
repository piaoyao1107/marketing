import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Person {

    @JSONField(name = "AGE")
    private int age;

    @JSONField(name = "FULL NAME")
    private String fullName;


    public Person(int age, String fullName) {
        super();
        this.age = age;
        this.fullName= fullName;
    }

    // 标准 getters & setters
}