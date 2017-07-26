package indi.jackie.acmpu.p1006;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jackie chen
 * @create 2017/7/25
 * @description P1006
 */
public class P1006 {

    private static final Integer PHYSICAL_CYCLE_DAYS = 23;//体力高峰周期
    private static final Integer EMOTIONAL_CYCLE_DAYS = 28;//情绪高峰周期
    private static final Integer INTELLECTUAL_CYCLE_DAYS = 33;//智力高峰周期

    private static final Integer CONSTANT_5543 = INTELLECTUAL_CYCLE_DAYS * EMOTIONAL_CYCLE_DAYS * 6;//33×28×a被23除余1
    private static final Integer CONSTANT_14421 = PHYSICAL_CYCLE_DAYS * INTELLECTUAL_CYCLE_DAYS * 19;//23×33×b被28除余1
    private static final Integer CONSTANT_1288 = PHYSICAL_CYCLE_DAYS * EMOTIONAL_CYCLE_DAYS * 2;//23×28×c被33除余1
    private static final Integer LOWEST_COMMON_MULTIPLE = PHYSICAL_CYCLE_DAYS * EMOTIONAL_CYCLE_DAYS * INTELLECTUAL_CYCLE_DAYS;//最小公倍数

    @Test
    public void p1006() {
        String data = "0 0 0 0\n" +
                "0 0 0 100\n" +
                "5 20 34 325\n" +
                "4 5 6 7\n" +
                "283 102 23 320\n" +
                "203 301 203 40\n" +
                "-1 -1 -1 -1";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner sin = new Scanner(System.in);
            List<String> cinList = new ArrayList<>();
            String temp;
            while (sin.hasNext()) {
                temp = sin.nextLine();
                if (temp.equals("-1 -1 -1 -1")) {
                    break;
                } else {
                    cinList.add(temp);
                }
            }
            //处理输入列表
            dealList(cinList);
        } finally {
            System.setIn(stdin);
        }
    }

    /**
     * 处理输入数据
     *
     * @param cinList
     */
    private void dealList(List<String> cinList) {
        List<Integer> result = new ArrayList<>();
        for (int j = 0; j < cinList.size(); j++) {
            String[] temp = cinList.get(j).split("\\u0020");
            Integer p = Integer.parseInt(temp[0]);
            Integer e = Integer.parseInt(temp[1]);
            Integer i = Integer.parseInt(temp[2]);
            Integer d = Integer.parseInt(temp[3]);
            //根据p/e/i/d获取所需天数
            result.add(getDays(p, e, i, d));
        }
        //输出结果
        printResult(result);
    }

    /**
     * 根据p/e/i/d获取所需天数
     *
     * @param p
     * @param e
     * @param i
     * @param d
     * @return
     */
    private Integer getDays(Integer p, Integer e, Integer i, Integer d) {
        //this.CONSTANT_5543 * p + this.CONSTANT_14421 * e + this.CONSTANT_1288 * i 必为一个对23取余为p，对28取余为e，对33取余为i的数
        Integer temp = (this.CONSTANT_5543 * p + this.CONSTANT_14421 * e + this.CONSTANT_1288 * i);
        if (temp == 0) {
            return this.LOWEST_COMMON_MULTIPLE - d;
        } else {
            return (this.CONSTANT_5543 * p + this.CONSTANT_14421 * e + this.CONSTANT_1288 * i) % this.LOWEST_COMMON_MULTIPLE - d;
        }
    }

    /**
     * 输出结果
     *
     * @param result
     */
    private void printResult(List<Integer> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Case " + (i + 1) + ": the next triple peak occurs in " + result.get(i) + " days.");
        }
    }
}
