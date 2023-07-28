package demos.test.fastjson;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by xiedong
 * 2023/7/28 10:12
 */
@Slf4j
public class TestFastJson {
    public static void main(String[] args) {
        A a = new A();

        int id = a.getId();
        System.out.println(id);

    }
}

@Data
@Slf4j
class A {
    private B b;


    public int getId() {
        try {
            return this.b.getId();
        } catch (Exception e) {
            log.error("A:getId-error:32,a:{} error", JSON.toJSONString(this), e);
            return 0;
        }
    }
}

@Data
class B {
    private Integer id;
}
