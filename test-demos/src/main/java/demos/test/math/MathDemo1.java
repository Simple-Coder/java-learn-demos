package demos.test.math;

/**
 * @ClassName: MathDemo1
 * @author: xiedong
 * @date 2021/9/22 15:29
 * @Desc:
 */
public class MathDemo1 {
    public static void main(String[] args) {
        for (int i = 4; i <= 12; i++) {
            t1(i);
        }
    }

    public static void t1(int k) {
        int count = 0;
        int a = (3 * k + 3);
        while (a <= (3 * k + 3)) {
            for (int b = 0; b <= a; b++) {
                for (int r = 0; r <= b; r++) {
                    int left = a + b + r;
                    int right = 3 * k + 5;
                    if (right == left && (a * b) <= (9 * k + 5) && a >= b && b >= r) {
                        count++;
//                        int mod = a % k;
//                        int div = a / k;
//                        if (mod == 0) {
//                            System.out.println("k=" + k + "时a:" + a + "," + div + "k" + ",b:" + b + ",r:" + r);
//                        } else if (div == 1 && mod != 0) {
//                            System.out.println("k=" + k + "时a:" + a + ","  + "k+" + mod + ",b:" + b + ",r:" + r);
//                        }else if (div == 1 && mod != 0){
//
//                        }
                        int k3 = 3 * k;
                        int tmp1 = a - k3;
                        if (tmp1 > 0) {
                            System.out.println("当前k=" + k + "时a:" + a + ",3k+" + tmp1 + "\tb:" + b + "\tr:" + r);
                        } else if (tmp1 < 0) {
                            System.out.println("当前k=" + k + "时a:" + a + ",3k-" + Math.abs(tmp1) + "\tb:" + b + "\tr:" + r);
                        } else {
                            System.out.println("当前k=" + k + "时a:" + a + ",3k" + "\tb:" + b + "\tr:" + r);
                        }
                    }
                }
            }
            a--;
        }
        System.out.println("~~~~~~~~~~~~~~~~k=" + k + "共有" + count + "条满足条件~~~~~~~~~~~");
        System.out.println();
    }
}
