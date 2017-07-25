package indi.jackie.acmpu.p1004;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jackie chen
 * @create 2017/7/25
 * @description P1004
 */
public class P1004 {

    private static final Integer MONTH_TWELVE = 12;

    @Test
    public void p1004() {
        String data = "100.00\n" +
                "489.12\n" +
                "12454.12\n" +
                "1234.10\n" +
                "823.05\n" +
                "109.20\n" +
                "5.27\n" +
                "1542.25\n" +
                "839.18\n" +
                "83.99\n" +
                "1295.01\n" +
                "1.75";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner cin = new Scanner(System.in);
            List<Float> cinList = new ArrayList<>();
            int i = 0;
            while (i++ < this.MONTH_TWELVE) {
                if (cin.hasNext()) {
                    cinList.add(cin.nextFloat());
                } else {
                    break;
                }
            }
            //处理P1004输入
            dealList(cinList);
        } finally {
            System.setIn(stdin);
        }
    }

    /**
     * 处理P1004输入
     *
     * @param cinList
     */
    private void dealList(List<Float> cinList) {
        if (cinList.size() == 0) {
            return;
        }
        Integer index = 0;
        //计算结余总额
        Float sum = addBalance(cinList, index);
        DecimalFormat df = new DecimalFormat("##0.00");
        System.out.println("$" + df.format(sum / this.MONTH_TWELVE));
    }

    /**
     * 递归计算结余总额
     *
     * @param cinList
     * @param index
     * @return
     */
    private Float addBalance(List<Float> cinList, Integer index) {
        Float sum = 0F;
        if (index < cinList.size() - 1) {//不是最后一个元素，递归调用
            sum += cinList.get(index) + addBalance(cinList, ++index);
        } else {//最后一个，直接赋值
            sum = cinList.get(index);
        }
        return sum;
    }
}
