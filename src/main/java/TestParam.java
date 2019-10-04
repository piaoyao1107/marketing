import com.alibaba.fastjson.JSON;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestParam {

    private List<Param> listOfParams = new ArrayList<Param>();

    public void setUp() {
        listOfParams.add(new Param("Tom", 20));
        listOfParams.add(new Param("John", 22));
    }

    @Test
    public void whenJavaList_thanConvertToJsonCorrect() {
        String jsonOutput= JSON.toJSONString(listOfParams);
        System.out.println("jsonOutput的值是："+jsonOutput);
    }
}
