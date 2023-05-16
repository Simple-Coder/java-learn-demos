package demos.test.diabates;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Created by xiedong
 * 2023/5/16 10:46
 */
public class FilterTest {
    public static void main(String[] args) {
        String path = "D:\\dev\\PycharmProjects\\diabetes-KGQA\\server\\data\\intent_and_slot_data\\origin.json";
        String path1 = "D:\\dev\\PycharmProjects\\diabetes-KGQA\\server\\data\\intent_and_slot_data\\origin1.json";

        String fileContent = FileUtil.readString(path, Charset.defaultCharset());
        JSONArray array = JSON.parseArray(fileContent);
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            String text = jsonObject.getString("text");
            String slots = jsonObject.getString("slots");
            JSONObject slotJson = JSON.parseObject(slots);
            if (StrUtil.contains(text, "分期类型")) {
                slotJson.put("class", "分期类型");
                jsonObject.put("slots", slotJson);
            }
            System.out.println();
        }


        FileUtil.writeBytes(array.toString().getBytes(StandardCharsets.UTF_8), new File(path1));


        System.out.println();

    }
}
