package org.example.tp;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dongxie on 2022/9/29.
 */
public class TestSet {
    public static void main(String[] args) {

        int i = 1;
        while (true) {

            ConcurrencyTester tester = ThreadUtil.concurrencyTest(10, () -> {
                // 测试的逻辑内容
                long delay = RandomUtil.randomLong(1, 50);
                HttpResponse execute = HttpUtil.createGet("http://localhost:9999/hystrix/test?num=" + delay).execute();
                System.out.println(execute.body());
//            long delay = RandomUtil.randomLong(100, 1000);
//            ThreadUtil.sleep(delay);
//            Console.log("{} test finished, delay: {}", Thread.currentThread().getName(), delay);
            });

            Console.log(tester.getInterval());
            if (i++ >= 10000) {
                break;
            }
        }
// 获取总的执行时间，单位毫秒


//        String result = "";
//        for (int i = 12; i < 1200; i += 12) {
//            String str = "D" + i;
//            result += str;
//            result += ",";
//        }
//        System.out.println(result);
//        File zip = ZipUtil.zip("D:\\testxls","D:\\a.zip");


//        Set<Long> a1= new HashSet<>();
//        a1.add(1L);
//        a1.add(2L);
//        a1.add(3L);
//
//        JSONObject jsonObject = new JSONObject();
//
//        int status = jsonObject.getIntValue("status");
//
//
//        Long a2 = 1L;
//        boolean contains = a1.contains(a2);
//        System.out.println();
    }
}
