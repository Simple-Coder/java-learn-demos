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


        int idCount = 1;

        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            jsonObject.put("id", idCount++);
            String text = jsonObject.getString("text");
            String slots = jsonObject.getString("slots");
            JSONObject slotJson = JSON.parseObject(slots);
            if (StrUtil.contains(text, "分期类型")) {
                slotJson.put("class", "分期类型");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.contains(text, "治疗药物")) {
                slotJson.put("drug", "治疗药物");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.contains(text, "用药剂量")) {
                slotJson.put("amount", "用药剂量");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.contains(text, "持续时间")) {
                slotJson.put("duration", "持续时间");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.contains(text, "检查方法")) {
                slotJson.put("test", "检查方法");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.contains(text, "发病的部位")) {
                slotJson.put("anatomy", "发病的部位");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.contains(text, "手术")) {
                slotJson.put("operation", "手术");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.containsAny(text, "不良反应", "副作用")) {
                slotJson.put("ade1", "不良反应");
                slotJson.put("ade2", "副作用");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.containsAny(text, "病因")) {
                slotJson.put("reason", "病因");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.containsAny(text, "发病机制")) {
                slotJson.put("pathogenesis", "发病机制");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.containsAny(text, "不吃药的治疗方法")) {
                slotJson.put("treatment", "不吃药的治疗方法");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.containsAny(text, "需要吃什么药")) {
                slotJson.put("drug", "药");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.containsAny(text, "用药频率", "一天吃几次")) {
                slotJson.put("frequency1", "用药频率");
                slotJson.put("frequency2", "一天吃几次");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.containsAny(text, "检查指标")) {
                slotJson.put("test_items", "检查指标");
                jsonObject.put("slots", slotJson);
            }
            if (StrUtil.containsAny(text, "临床表现")) {
                slotJson.put("symptom", "临床表现");
                jsonObject.put("slots", slotJson);
            }
            System.out.println();
        }


        FileUtil.writeBytes(array.toString().getBytes(StandardCharsets.UTF_8), new File(path1));


        System.out.println();

    }
}