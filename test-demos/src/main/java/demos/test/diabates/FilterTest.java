package demos.test.diabates;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by xiedong
 * 2023/5/16 10:46
 */
public class FilterTest {
    public static void main(String[] args) {
//        String path = "D:\\dev\\PycharmProjects\\diabetes-KGQA\\server\\data\\intent_and_slot_data\\origin.json";
        String path = "/Users/xiedong/PycharmProjects/diabetes-KGQA/server/data/intent_and_slot_data/origin.json";
//        String path1 = "D:\\dev\\PycharmProjects\\diabetes-KGQA\\server\\data\\intent_and_slot_data\\origin1.json";
        String path1 = "/Users/xiedong/PycharmProjects/diabetes-KGQA/server/data/intent_and_slot_data/origin2.json";

        String fileContent = FileUtil.readString(path, Charset.defaultCharset());
        JSONArray array = JSON.parseArray(fileContent);


        JSONArray tmps = new JSONArray();

        int idCount = 0;
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            jsonObject.put("id", ++idCount);
            String text = jsonObject.getString("text");

            if (StrUtil.contains(text, "临床表现是什么")) {
                JSONObject tmp1 = new JSONObject();
                tmp1.put("id", ++idCount);
                tmp1.put("slots", jsonObject.get("slots"));
                tmp1.put("intent", jsonObject.get("intent"));
                String replace = text.replace("临床表现是什么", "临床表现有哪些");
                tmp1.put("text", replace);
                tmps.add(tmp1);


                JSONObject tmp2 = new JSONObject();
                tmp2.put("id", ++idCount);
                tmp2.put("slots", jsonObject.get("slots"));
                tmp2.put("intent", jsonObject.get("intent"));
                String replace2 = text.replace("临床表现是什么", "有什么症状？");
                tmp2.put("text", replace2);
                tmps.add(tmp2);

                JSONObject tmp3 = new JSONObject();
                tmp3.put("slots", jsonObject.get("slots"));
                tmp3.put("intent", jsonObject.get("intent"));
                tmp3.put("id", ++idCount);
                String replace3 = text.replace("临床表现是什么", "的症状是什么？");
                tmp3.put("text", replace3);
                tmps.add(tmp3);


                JSONObject tmp4 = new JSONObject();
                tmp4.put("slots", jsonObject.get("slots"));
                tmp4.put("intent", jsonObject.get("intent"));
                tmp4.put("id", ++idCount);
                String replace4 = text.replace("临床表现是什么", "的症状有哪些啊？");
                tmp4.put("text", replace4);
                tmps.add(tmp4);

            }



            if (StrUtil.contains(text, "症状表现是怎样的呢")) {
                JSONObject tmp1 = new JSONObject();
                tmp1.put("id", ++idCount);
                tmp1.put("slots", jsonObject.get("slots"));
                tmp1.put("intent", jsonObject.get("intent"));
                String replace = text.replace("症状表现是怎样的呢", "临床表现有哪些");
                tmp1.put("text", replace);
                tmps.add(tmp1);


                JSONObject tmp2 = new JSONObject();
                tmp2.put("id", ++idCount);
                tmp2.put("slots", jsonObject.get("slots"));
                tmp2.put("intent", jsonObject.get("intent"));
                String replace2 = text.replace("症状表现是怎样的呢", "有什么症状？");
                tmp2.put("text", replace2);
                tmps.add(tmp2);

                JSONObject tmp3 = new JSONObject();
                tmp3.put("slots", jsonObject.get("slots"));
                tmp3.put("intent", jsonObject.get("intent"));
                tmp3.put("id", ++idCount);
                String replace3 = text.replace("症状表现是怎样的呢", "的症状是什么？");
                tmp3.put("text", replace3);
                tmps.add(tmp3);


                JSONObject tmp4 = new JSONObject();
                tmp4.put("slots", jsonObject.get("slots"));
                tmp4.put("intent", jsonObject.get("intent"));
                tmp4.put("id", ++idCount);
                String replace4 = text.replace("症状表现是怎样的呢", "的症状有哪些啊？");
                tmp4.put("text", replace4);
                tmps.add(tmp4);

            }

            if (StrUtil.contains(text, "哪些不良反应")) {
                JSONObject tmp1 = new JSONObject();
                tmp1.put("id", ++idCount);
                tmp1.put("slots", jsonObject.get("slots"));
                tmp1.put("intent", jsonObject.get("intent"));
                String replace = text.replace("哪些不良反应", "的不良反应有哪些");
                tmp1.put("text", replace);
                tmps.add(tmp1);


                JSONObject tmp2 = new JSONObject();
                tmp2.put("id", ++idCount);
                tmp2.put("slots", jsonObject.get("slots"));
                tmp2.put("intent", jsonObject.get("intent"));
                String replace2 = text.replace("哪些不良反应", "有什么不良反应？");
                tmp2.put("text", replace2);
                tmps.add(tmp2);

                JSONObject tmp3 = new JSONObject();
                tmp3.put("slots", jsonObject.get("slots"));
                tmp3.put("intent", jsonObject.get("intent"));
                tmp3.put("id", ++idCount);
                String replace3 = text.replace("哪些不良反应", "的不良反应是什么？");
                tmp3.put("text", replace3);
                tmps.add(tmp3);

            }

            if (StrUtil.contains(text, "副作用")) {
                JSONObject tmp1 = new JSONObject();
                tmp1.put("id", ++idCount);
                tmp1.put("slots", jsonObject.get("slots"));
                tmp1.put("intent", jsonObject.get("intent"));
                String replace = text.replace("副作用", "的副作用有哪些");
                tmp1.put("text", replace);
                tmps.add(tmp1);


                JSONObject tmp2 = new JSONObject();
                tmp2.put("id", ++idCount);
                tmp2.put("slots", jsonObject.get("slots"));
                tmp2.put("intent", jsonObject.get("intent"));
                String replace2 = text.replace("副作用", "的副作用是什么？");
                tmp2.put("text", replace2);
                tmps.add(tmp2);

                JSONObject tmp3 = new JSONObject();
                tmp3.put("slots", jsonObject.get("slots"));
                tmp3.put("intent", jsonObject.get("intent"));
                tmp3.put("id", ++idCount);
                String replace3 = text.replace("副作用", "有什么副作用？");
                tmp3.put("text", replace3);
                tmps.add(tmp3);

            }
            if (StrUtil.contains(text, "有哪些不良反应、副作用")) {
                JSONObject tmp1 = new JSONObject();
                tmp1.put("id", ++idCount);
                tmp1.put("slots", jsonObject.get("slots"));
                tmp1.put("intent", jsonObject.get("intent"));
                String replace = text.replace("有哪些不良反应、副作用", "的副作用有哪些");
                tmp1.put("text", replace);
                tmps.add(tmp1);


                JSONObject tmp2 = new JSONObject();
                tmp2.put("id", ++idCount);
                tmp2.put("slots", jsonObject.get("slots"));
                tmp2.put("intent", jsonObject.get("intent"));
                String replace2 = text.replace("有哪些不良反应、副作用", "的副作用是什么？");
                tmp2.put("text", replace2);
                tmps.add(tmp2);

                JSONObject tmp3 = new JSONObject();
                tmp3.put("slots", jsonObject.get("slots"));
                tmp3.put("intent", jsonObject.get("intent"));
                tmp3.put("id", ++idCount);
                String replace3 = text.replace("有哪些不良反应、副作用", "有什么副作用？");
                tmp3.put("text", replace3);
                tmps.add(tmp3);

            }


//            String slots = jsonObject.getString("slots");
//            JSONObject slotJson = JSON.parseObject(slots);
          /*  if (StrUtil.contains(text, "分期类型")) {
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
            }*/
//            System.out.println();
        }
        array.addAll(tmps);

        FileUtil.writeBytes(JSON.toJSONString(array, SerializerFeature.DisableCircularReferenceDetect).getBytes(StandardCharsets.UTF_8), new File(path1));


        System.out.println("写入完成..");

    }
}
