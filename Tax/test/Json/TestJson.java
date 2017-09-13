package Json;

import java.util.ArrayList;

import org.junit.Test;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: TestJson
 * @Description: 测试JSON的使用
 * @author Smileyes
 * @date 2017年9月9日 下午4:21:19
 *
 */
public class TestJson {
	@Test
	public void abc() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("张三");
		list.add("李四");
		JSONObject json = new JSONObject();
		json.put("abc", "defg");
		json.put("list", list);
		System.out.println(json.toString());
	}
}
