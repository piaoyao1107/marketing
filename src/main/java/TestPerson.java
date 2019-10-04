import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPerson {

    private Map<String, Object> map = new HashMap<String, Object>();

    @Test
    public void setUp() {
//        listOfPersons.add(new Person(15, "John Doe"));
//        listOfPersons.add(new Person(20, "Janette Doe"));
        map.put("id","12");
        map.put("name","Tom");
        String mapJson = JSON.toJSONString(map);
        System.out.println("mapJson返回的值是："+mapJson);
    }

}
