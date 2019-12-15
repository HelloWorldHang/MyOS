package tempTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-12-09 17:40
 */
public class Test {
    public static void main(String[] args){
        Map<String, String> map = new HashMap<>();
        map.put("1", "test");
        map.put("2", "test");
        map.put("3", "test");
        String s = map.get("4");
        System.out.println(s);
    }
}
