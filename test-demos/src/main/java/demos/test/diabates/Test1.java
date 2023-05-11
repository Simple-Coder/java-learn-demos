package demos.test.diabates;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiedong
 * Date: 2023/3/23 21:04
 */
public class Test1 {
    public static void main(String[] args) {
//        String initDataPath = "D:\\dev\\PycharmProjects\\diabetes-KGQA\\server\\data\\kg\\kg_data";
//        String destiDataPath = "D:\\dev\\PycharmProjects\\diabetes-KGQA\\server\\data\\kg\\kg_data\\all.json";


        String initDataPath = "/Users/xiedong/PycharmProjects/diabetes-KGQA/server/data/kg/kg_data";
        String destiDataPath = "/Users/xiedong/PycharmProjects/diabetes-KGQA/server/data/kg/clear_data/clear.json";


        File[] listFiles = FileUtil.ls(initDataPath);
        int countRecord = 0;
        int countFIle = 0;
        for (File listFile : listFiles) {
            countFIle++;
            String fileContent = FileUtil.readString(listFile, Charset.defaultCharset());
            if (listFile.getName().equals("clear.json")) {
                continue;
            }
            JSONObject jsonObject = JSON.parseObject(fileContent);
            JSONArray paragraphs = jsonObject.getJSONArray("paragraphs");
            for (int i = 0; i < paragraphs.size(); i++) {
                JSONObject lineJson = paragraphs.getJSONObject(i);
                FileUtil.appendUtf8Lines(Arrays.asList(lineJson.toJSONString()), new File(destiDataPath));
                countRecord++;
            }
        }
        System.out.println("记录行数：" + countRecord + ",文件个数：" + countFIle);


        Map<String, Object> entitiesMap = new HashMap<>();//实体
        Map<String, Object> relsMap = new HashMap<>();//关系
        List<String> strings = FileUtil.readLines(destiDataPath, Charset.defaultCharset());
        for (String string : strings) {

            JSONObject json = JSON.parseObject(string);
            JSONArray sentences = json.getJSONArray("sentences");
            for (int i = 0; i < sentences.size(); i++) {
                JSONObject jsonObject = sentences.getJSONObject(i);

                //实体记录
                JSONArray entities = jsonObject.getJSONArray("entities");
                for (int i1 = 0; i1 < entities.size(); i1++) {
                    JSONObject jsonObject1 = entities.getJSONObject(i1);
                    String key = jsonObject1.getString("entity");
                    String value = jsonObject1.getString("entity_type");
                    entitiesMap.put(key, value);
                }

                //关系提取
                JSONArray relations = jsonObject.getJSONArray("relations");
                for (int i1 = 0; i1 < relations.size(); i1++) {
                    JSONObject jsonObject1 = relations.getJSONObject(i1);
                    //head_entity_id -> T2 tail_entity_id -> T0
                    String head_entity_id = jsonObject1.getString("head_entity_id");
                    String head_entity_Str = "";
                    String tail_entity_id = jsonObject1.getString("tail_entity_id");
                    String tail_entity_Str = "";

                    for (int i2 = 0; i2 < entities.size(); i2++) {
                        JSONObject entitiesJSONObject = entities.getJSONObject(i2);
                        String entity_id = entitiesJSONObject.getString("entity_id");
                        String entity = entitiesJSONObject.getString("entity");
                        if (StrUtil.equals(entity_id, head_entity_id)) {
                            head_entity_Str = entity;
                        }
                        if (StrUtil.equals(entity_id, tail_entity_id)) {
                            tail_entity_Str = entity;
                        }
                    }

                    String key = jsonObject1.getString("relation_type");
                    List<String> split = StrUtil.split(key, StrUtil.UNDERLINE);
                    String str1 = split.get(0);
                    String str2 = split.get(1);
                    relsMap.put(str2 + ":" + tail_entity_Str + "|" + str1 + ":" + head_entity_Str, key);
                }
            }

        }
//        System.out.println("发现实体记录：" + JSON.toJSONString(entitiesMap));

//        Map<String, Object> disease = MapUtil.filter(entitiesMap, entry -> entry.getValue().equals("Disease"));
//        System.out.println("疾病实体：" + JSON.toJSONString(disease));

//        System.out.println("发现关系记录：" + JSON.toJSONString(relsMap));
//        Map<String, Object> Reason_Disease = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Reason_Disease"));
//        System.out.println("发现病因：" + JSON.toJSONString(Reason_Disease));


//        Map<String, Object> Drug_Disease = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Drug_Disease"));
//        System.out.println("发现疾病治疗：" + JSON.toJSONString(Drug_Disease));

//        Map<String, Object> Class_Disease = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Class_Disease"));
//        System.out.println("发现分期类型->疾病：" + JSON.toJSONString(Class_Disease));
        System.out.println();
//        Map<String, Object> Amount_Drug = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Amount_Drug"));
//        System.out.println("发现用药剂量->药品名称：" + JSON.toJSONString(Amount_Drug));

//        Map<String, Object> Duration_Drug = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Duration_Drug"));
//        System.out.println("发现持续时间->药品名称：" + JSON.toJSONString(Duration_Drug));


//        Map<String, Object> Test_Disease = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Test_Disease"));
//        System.out.println("发现检查方法->疾病：" + JSON.toJSONString(Test_Disease));


//        Map<String, Object> Method_Drug = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Method_Drug"));
//        System.out.println("发现用药方法->药品名称：" + JSON.toJSONString(Method_Drug));

        //
//        Map<String, Object> Anatomy_Disease = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Anatomy_Disease"));
//        System.out.println("发现部位->疾病：" + JSON.toJSONString(Anatomy_Disease));

//        Map<String, Object> Operation_Disease = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Operation_Disease"));
//        System.out.println("发现手术->疾病：" + JSON.toJSONString(Operation_Disease));

//        Map<String, Object> ADE_Drug = MapUtil.filter(relsMap, entry -> entry.getValue().equals("ADE_Drug"));
//        System.out.println("发现不良反应->药品名称：" + JSON.toJSONString(ADE_Drug));


//        Map<String, Object> Pathogenesis_Disease = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Pathogenesis_Disease"));
//        System.out.println("发现发病机制->疾病：" + JSON.toJSONString(Pathogenesis_Disease));

//        Map<String, Object> Treatment_Disease = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Treatment_Disease"));
//        System.out.println("发现非药治疗->疾病：" + JSON.toJSONString(Treatment_Disease));


        Map<String, Object> Frequency_Drug = MapUtil.filter(relsMap, entry -> entry.getValue().equals("Frequency_Drug"));
        System.out.println("发现用药频率->药品名称：" + JSON.toJSONString(Frequency_Drug));
    }

    public static final String a = "{\n" +
            "  \"doc_id\": \"1\",\n" +
            "  \"paragraphs\": [\n" +
            "    {\n" +
            "      \"paragraph_id\": \"0\",\n" +
            "      \"paragraph\": \"中国成人2型糖尿病胰岛素促泌剂应用的专家共识\",\n" +
            "      \"sentences\": [\n" +
            "        {\n" +
            "          \"sentence_id\": \"0\",\n" +
            "          \"sentence\": \"中国成人2型糖尿病胰岛素促泌剂应用的专家共识\",\n" +
            "          \"start_idx\": 0,\n" +
            "          \"end_idx\": 22,\n" +
            "          \"entities\": [\n" +
            "            {\n" +
            "              \"entity_id\": \"T0\",\n" +
            "              \"entity\": \"2型糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 4,\n" +
            "              \"end_idx\": 9\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T1\",\n" +
            "              \"entity\": \"2型\",\n" +
            "              \"entity_type\": \"Class\",\n" +
            "              \"start_idx\": 4,\n" +
            "              \"end_idx\": 6\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T2\",\n" +
            "              \"entity\": \"胰岛素促泌剂\",\n" +
            "              \"entity_type\": \"Drug\",\n" +
            "              \"start_idx\": 9,\n" +
            "              \"end_idx\": 15\n" +
            "            }\n" +
            "          ],\n" +
            "          \"relations\": [\n" +
            "            {\n" +
            "              \"relation_type\": \"Drug_Disease\",\n" +
            "              \"relation_id\": \"R0\",\n" +
            "              \"head_entity_id\": \"T2\",\n" +
            "              \"tail_entity_id\": \"T0\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Class_Disease\",\n" +
            "              \"relation_id\": \"R1\",\n" +
            "              \"head_entity_id\": \"T1\",\n" +
            "              \"tail_entity_id\": \"T0\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"paragraph_id\": \"1\",\n" +
            "      \"paragraph\": \"随着社会经济的发展，糖尿病患病率逐渐增加，已成为严重的世界性问题。糖尿病特别是其慢性并发症影响患者的生活质量，甚至威胁患者的生命，给社会、家庭以及患者带来沉重的经济负担。中国的流行病学调查显示，中国20岁以上人群2型糖尿病患病率达9.7%；且近2/3的患者HbA1c得不到有效控制(HbA1c≤7%)；60.7%的患者因未被诊断而无法及早进行有效的治疗和指导。\",\n" +
            "      \"sentences\": [\n" +
            "        {\n" +
            "          \"sentence_id\": \"0\",\n" +
            "          \"sentence\": \"随着社会经济的发展，糖尿病患病率逐渐增加，已成为严重的世界性问题。糖尿病特别是其慢性并发症影响患者的生活质量，甚至威胁患者的生命，给社会、家庭以及患者带来沉重的经济负担。中国的流行病学调查显示，中国20岁以上人群2型糖尿病患病率达9.7%；且近2/3的患者HbA1c得不到有效控制(HbA1c≤7%)；60.7%的患者因未被诊断而无法及早进行有效的治疗和指导。\",\n" +
            "          \"start_idx\": 0,\n" +
            "          \"end_idx\": 180,\n" +
            "          \"entities\": [\n" +
            "            {\n" +
            "              \"entity_id\": \"T3\",\n" +
            "              \"entity\": \"糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 10,\n" +
            "              \"end_idx\": 13\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T4\",\n" +
            "              \"entity\": \"糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 33,\n" +
            "              \"end_idx\": 36\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T5\",\n" +
            "              \"entity\": \"HbA1c\",\n" +
            "              \"entity_type\": \"Test_items\",\n" +
            "              \"start_idx\": 128,\n" +
            "              \"end_idx\": 133\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T6\",\n" +
            "              \"entity\": \"HbA1c\",\n" +
            "              \"entity_type\": \"Test_items\",\n" +
            "              \"start_idx\": 141,\n" +
            "              \"end_idx\": 146\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T7\",\n" +
            "              \"entity\": \"≤7%\",\n" +
            "              \"entity_type\": \"Test_Value\",\n" +
            "              \"start_idx\": 146,\n" +
            "              \"end_idx\": 149\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T8\",\n" +
            "              \"entity\": \"2型糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 106,\n" +
            "              \"end_idx\": 111\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T9\",\n" +
            "              \"entity\": \"2型\",\n" +
            "              \"entity_type\": \"Class\",\n" +
            "              \"start_idx\": 106,\n" +
            "              \"end_idx\": 108\n" +
            "            }\n" +
            "          ],\n" +
            "          \"relations\": [\n" +
            "            {\n" +
            "              \"relation_type\": \"Test_items_Disease\",\n" +
            "              \"relation_id\": \"R2\",\n" +
            "              \"head_entity_id\": \"T5\",\n" +
            "              \"tail_entity_id\": \"T8\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Test_items_Disease\",\n" +
            "              \"relation_id\": \"R3\",\n" +
            "              \"head_entity_id\": \"T6\",\n" +
            "              \"tail_entity_id\": \"T8\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Class_Disease\",\n" +
            "              \"relation_id\": \"R4\",\n" +
            "              \"head_entity_id\": \"T9\",\n" +
            "              \"tail_entity_id\": \"T8\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"paragraph_id\": \"2\",\n" +
            "      \"paragraph\": \"中国成人2型糖尿病发生的病理生理特点与欧美国家存在差异，β细胞胰岛素分泌功能下降比胰岛素敏感性降低更明显，且糖尿病肾病发生率更高(2c级，2b级)。因此根据中国2型糖尿病患者自身特点，进行合理的降糖治疗，努力控制其他多种血管病变危险因素，降低血管并发症风险和提高患者生活质量是目前亟需解决的问题。尽管随着糖尿病防治进展，新降糖药物不断问世，而多数权威指南仍将胰岛素促泌剂作为成人2型糖尿病治疗的一线药物。英国糖尿病前瞻性研究(UKPDS)及其后续研究与糖尿病和心血管疾病行动(ADVANCE)等多个大型临床研究证实，磺脲类促泌剂或以其为基础的降糖治疗可显著提高2型糖尿病患者的血糖达标率，降低糖尿病血管并发症风险(1b级)。由于胰岛素促泌剂的发展，其种类逐渐增多，为了针对中国成人2型糖尿病患者的自身特点，更有效指导临床正确使用胰岛素促泌剂，中华医学会内分泌分会组织专家对胰岛素促泌剂在中国成人2型糖尿病的临床应用进行了多次认真的讨论并达成了共识。共识中的证据等级根据牛津循证医学中心的标准分类如(表1)。\",\n" +
            "      \"sentences\": [\n" +
            "        {\n" +
            "          \"sentence_id\": \"0\",\n" +
            "          \"sentence\": \"中国成人2型糖尿病发生的病理生理特点与欧美国家存在差异，β细胞胰岛素分泌功能下降比胰岛素敏感性降低更明显，且糖尿病肾病发生率更高(2c级，2b级)。因此根据中国2型糖尿病患者自身特点，进行合理的降糖治疗，努力控制其他多种血管病变危险因素，降低血管并发症风险和提高患者生活质量是目前亟需解决的问题。尽管随着糖尿病防治进展，新降糖药物不断问世，而多数权威指南仍将胰岛素促泌剂作为成人2型糖尿病治疗的一线药物。英国糖尿病前瞻性研究(UKPDS)及其后续研究与糖尿病和心血管疾病行动(ADVANCE)等多个大型临床研究证实，磺脲类促泌剂或以其为基础的降糖治疗可显著提高2型糖尿病患者的血糖达标率，降低糖尿病血管并发症风险(1b级)。由于胰岛素促泌剂的发展，其种类逐渐增多，为了针对中国成人2型糖尿病患者的自身特点，更有效指导临床正确使用胰岛素促泌剂，中华医学会内分泌分会组织专家对胰岛素促泌剂在中国成人2型糖尿病的临床应用进行了多次认真的讨论并达成了共识。\",\n" +
            "          \"start_idx\": 0,\n" +
            "          \"end_idx\": 424,\n" +
            "          \"entities\": [\n" +
            "            {\n" +
            "              \"entity_id\": \"T10\",\n" +
            "              \"entity\": \"2型糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 4,\n" +
            "              \"end_idx\": 9\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T11\",\n" +
            "              \"entity\": \"2型\",\n" +
            "              \"entity_type\": \"Class\",\n" +
            "              \"start_idx\": 4,\n" +
            "              \"end_idx\": 6\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T12\",\n" +
            "              \"entity\": \"β细胞胰岛素分泌功能下降比胰岛素敏感性降低更明显\",\n" +
            "              \"entity_type\": \"Pathogenesis\",\n" +
            "              \"start_idx\": 28,\n" +
            "              \"end_idx\": 52\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T13\",\n" +
            "              \"entity\": \"糖尿病肾病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 54,\n" +
            "              \"end_idx\": 59\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T14\",\n" +
            "              \"entity\": \"肾\",\n" +
            "              \"entity_type\": \"Anatomy\",\n" +
            "              \"start_idx\": 57,\n" +
            "              \"end_idx\": 58\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T15\",\n" +
            "              \"entity\": \"2型糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 80,\n" +
            "              \"end_idx\": 85\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T16\",\n" +
            "              \"entity\": \"2型\",\n" +
            "              \"entity_type\": \"Class\",\n" +
            "              \"start_idx\": 80,\n" +
            "              \"end_idx\": 82\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T17\",\n" +
            "              \"entity\": \"血管病变\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 110,\n" +
            "              \"end_idx\": 114\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T18\",\n" +
            "              \"entity\": \"血管\",\n" +
            "              \"entity_type\": \"Anatomy\",\n" +
            "              \"start_idx\": 121,\n" +
            "              \"end_idx\": 123\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T19\",\n" +
            "              \"entity\": \"糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 152,\n" +
            "              \"end_idx\": 155\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T20\",\n" +
            "              \"entity\": \"胰岛素促泌剂\",\n" +
            "              \"entity_type\": \"Drug\",\n" +
            "              \"start_idx\": 179,\n" +
            "              \"end_idx\": 185\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T21\",\n" +
            "              \"entity\": \"2型糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 189,\n" +
            "              \"end_idx\": 194\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T22\",\n" +
            "              \"entity\": \"2型\",\n" +
            "              \"entity_type\": \"Class\",\n" +
            "              \"start_idx\": 189,\n" +
            "              \"end_idx\": 191\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T23\",\n" +
            "              \"entity\": \"糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 204,\n" +
            "              \"end_idx\": 207\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T24\",\n" +
            "              \"entity\": \"糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 226,\n" +
            "              \"end_idx\": 229\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T25\",\n" +
            "              \"entity\": \"心血管疾病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 230,\n" +
            "              \"end_idx\": 235\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T26\",\n" +
            "              \"entity\": \"心血管\",\n" +
            "              \"entity_type\": \"Anatomy\",\n" +
            "              \"start_idx\": 230,\n" +
            "              \"end_idx\": 233\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T27\",\n" +
            "              \"entity\": \"磺脲类促泌剂\",\n" +
            "              \"entity_type\": \"Drug\",\n" +
            "              \"start_idx\": 258,\n" +
            "              \"end_idx\": 264\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T28\",\n" +
            "              \"entity\": \"2型糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 280,\n" +
            "              \"end_idx\": 285\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T29\",\n" +
            "              \"entity\": \"2型\",\n" +
            "              \"entity_type\": \"Class\",\n" +
            "              \"start_idx\": 280,\n" +
            "              \"end_idx\": 282\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T30\",\n" +
            "              \"entity\": \"糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 296,\n" +
            "              \"end_idx\": 299\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T31\",\n" +
            "              \"entity\": \"血管\",\n" +
            "              \"entity_type\": \"Anatomy\",\n" +
            "              \"start_idx\": 299,\n" +
            "              \"end_idx\": 301\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T32\",\n" +
            "              \"entity\": \"胰岛素促泌剂\",\n" +
            "              \"entity_type\": \"Drug\",\n" +
            "              \"start_idx\": 314,\n" +
            "              \"end_idx\": 320\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T33\",\n" +
            "              \"entity\": \"胰岛素促泌剂\",\n" +
            "              \"entity_type\": \"Drug\",\n" +
            "              \"start_idx\": 364,\n" +
            "              \"end_idx\": 370\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T34\",\n" +
            "              \"entity\": \"2型糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 340,\n" +
            "              \"end_idx\": 345\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T35\",\n" +
            "              \"entity\": \"2型\",\n" +
            "              \"entity_type\": \"Class\",\n" +
            "              \"start_idx\": 340,\n" +
            "              \"end_idx\": 342\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T36\",\n" +
            "              \"entity\": \"胰岛素促泌剂\",\n" +
            "              \"entity_type\": \"Drug\",\n" +
            "              \"start_idx\": 386,\n" +
            "              \"end_idx\": 392\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T37\",\n" +
            "              \"entity\": \"2型糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 397,\n" +
            "              \"end_idx\": 402\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T38\",\n" +
            "              \"entity\": \"2型\",\n" +
            "              \"entity_type\": \"Class\",\n" +
            "              \"start_idx\": 397,\n" +
            "              \"end_idx\": 399\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T39\",\n" +
            "              \"entity\": \"血管\",\n" +
            "              \"entity_type\": \"Anatomy\",\n" +
            "              \"start_idx\": 110,\n" +
            "              \"end_idx\": 112\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T40\",\n" +
            "              \"entity\": \"血糖\",\n" +
            "              \"entity_type\": \"Test_items\",\n" +
            "              \"start_idx\": 288,\n" +
            "              \"end_idx\": 290\n" +
            "            }\n" +
            "          ],\n" +
            "          \"relations\": [\n" +
            "            {\n" +
            "              \"relation_type\": \"Class_Disease\",\n" +
            "              \"relation_id\": \"R5\",\n" +
            "              \"head_entity_id\": \"T11\",\n" +
            "              \"tail_entity_id\": \"T10\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Pathogenesis_Disease\",\n" +
            "              \"relation_id\": \"R6\",\n" +
            "              \"head_entity_id\": \"T12\",\n" +
            "              \"tail_entity_id\": \"T10\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Anatomy_Disease\",\n" +
            "              \"relation_id\": \"R7\",\n" +
            "              \"head_entity_id\": \"T14\",\n" +
            "              \"tail_entity_id\": \"T13\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Class_Disease\",\n" +
            "              \"relation_id\": \"R8\",\n" +
            "              \"head_entity_id\": \"T16\",\n" +
            "              \"tail_entity_id\": \"T15\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Anatomy_Disease\",\n" +
            "              \"relation_id\": \"R9\",\n" +
            "              \"head_entity_id\": \"T18\",\n" +
            "              \"tail_entity_id\": \"T15\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Anatomy_Disease\",\n" +
            "              \"relation_id\": \"R10\",\n" +
            "              \"head_entity_id\": \"T39\",\n" +
            "              \"tail_entity_id\": \"T17\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Drug_Disease\",\n" +
            "              \"relation_id\": \"R11\",\n" +
            "              \"head_entity_id\": \"T20\",\n" +
            "              \"tail_entity_id\": \"T21\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Class_Disease\",\n" +
            "              \"relation_id\": \"R12\",\n" +
            "              \"head_entity_id\": \"T22\",\n" +
            "              \"tail_entity_id\": \"T21\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Anatomy_Disease\",\n" +
            "              \"relation_id\": \"R13\",\n" +
            "              \"head_entity_id\": \"T26\",\n" +
            "              \"tail_entity_id\": \"T25\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Drug_Disease\",\n" +
            "              \"relation_id\": \"R14\",\n" +
            "              \"head_entity_id\": \"T27\",\n" +
            "              \"tail_entity_id\": \"T28\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Class_Disease\",\n" +
            "              \"relation_id\": \"R15\",\n" +
            "              \"head_entity_id\": \"T29\",\n" +
            "              \"tail_entity_id\": \"T28\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Test_items_Disease\",\n" +
            "              \"relation_id\": \"R16\",\n" +
            "              \"head_entity_id\": \"T40\",\n" +
            "              \"tail_entity_id\": \"T28\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Anatomy_Disease\",\n" +
            "              \"relation_id\": \"R17\",\n" +
            "              \"head_entity_id\": \"T31\",\n" +
            "              \"tail_entity_id\": \"T30\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Drug_Disease\",\n" +
            "              \"relation_id\": \"R18\",\n" +
            "              \"head_entity_id\": \"T32\",\n" +
            "              \"tail_entity_id\": \"T34\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Drug_Disease\",\n" +
            "              \"relation_id\": \"R19\",\n" +
            "              \"head_entity_id\": \"T33\",\n" +
            "              \"tail_entity_id\": \"T37\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Class_Disease\",\n" +
            "              \"relation_id\": \"R20\",\n" +
            "              \"head_entity_id\": \"T35\",\n" +
            "              \"tail_entity_id\": \"T34\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Drug_Disease\",\n" +
            "              \"relation_id\": \"R21\",\n" +
            "              \"head_entity_id\": \"T36\",\n" +
            "              \"tail_entity_id\": \"T37\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"relation_type\": \"Class_Disease\",\n" +
            "              \"relation_id\": \"R22\",\n" +
            "              \"head_entity_id\": \"T38\",\n" +
            "              \"tail_entity_id\": \"T37\"\n" +
            "            }\n" +
            "          ]\n" +
            "        },\n" +
            "        {\n" +
            "          \"sentence_id\": \"1\",\n" +
            "          \"sentence\": \"共识中的证据等级根据牛津循证医学中心的标准分类如(表1)。\",\n" +
            "          \"start_idx\": 424,\n" +
            "          \"end_idx\": 453,\n" +
            "          \"entities\": [],\n" +
            "          \"relations\": []\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"paragraph_id\": \"3\",\n" +
            "      \"paragraph\": \"糖尿病发病机制与中国2型糖尿病患者特点\",\n" +
            "      \"sentences\": [\n" +
            "        {\n" +
            "          \"sentence_id\": \"0\",\n" +
            "          \"sentence\": \"糖尿病发病机制与中国2型糖尿病患者特点\",\n" +
            "          \"start_idx\": 0,\n" +
            "          \"end_idx\": 19,\n" +
            "          \"entities\": [\n" +
            "            {\n" +
            "              \"entity_id\": \"T41\",\n" +
            "              \"entity\": \"糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 0,\n" +
            "              \"end_idx\": 3\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T42\",\n" +
            "              \"entity\": \"2型糖尿病\",\n" +
            "              \"entity_type\": \"Disease\",\n" +
            "              \"start_idx\": 10,\n" +
            "              \"end_idx\": 15\n" +
            "            },\n" +
            "            {\n" +
            "              \"entity_id\": \"T43\",\n" +
            "              \"entity\": \"2型\",\n" +
            "              \"entity_type\": \"Class\",\n" +
            "              \"start_idx\": 10,\n" +
            "              \"end_idx\": 12\n" +
            "            }\n" +
            "          ],\n" +
            "          \"relations\": [\n" +
            "            {\n" +
            "              \"relation_type\": \"Class_Disease\",\n" +
            "              \"relation_id\": \"R23\",\n" +
            "              \"head_entity_id\": \"T43\",\n" +
            "              \"tail_entity_id\": \"T42\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
