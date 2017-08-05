package indi.jackie.acmpu.p1009;

import indi.jackie.acmpu.util.CommonUtil;
import indi.jackie.acmpu.util.Constants;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jackie chen
 * @create 2017/7/31
 * @description P1009
 */
public class P1009 {

    private static final Integer DAYS_OF_ONE_HAAB_YEAR = 365;//Haab一年365天
    private static final Integer DAYS_OF_ONE_TZOLINK_YEAR = 260;//Tzolink一年260天

    @Test
    public void p1009() {

        String data = "7\n" +
                "15 4\n" +
                "100 15\n" +
                "25 2\n" +
                "175 2\n" +
                "25 5\n" +
                "175 2\n" +
                "25 5\n" +
                "0 0\n" +
                "10\n" +
                "35 500000000\n" +
                "200 500000000\n" +
                "0 0\n" +
                "3\n" +
                "255 1\n" +
                "10 1\n" +
                "255 2\n" +
                "10 1\n" +
                "255 2\n" +
                "10 1\n" +
                "255 1\n" +
                "0 0\n" +
                "0";
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner cin = new Scanner(System.in);
            Integer count;
            Integer index = 0;
            List<String> cinList = new ArrayList<>();
            if (cin.hasNext()) {
                count = Integer.parseInt(cin.nextLine());
                while (cin.hasNext() && index++ < count) {
                    cinList.add(cin.nextLine());
                }
                //处理输入列表
                dealList(cinList);
            }
        } finally {
            System.setIn(stdin);
        }
    }

    private void dealList(List<String> cinList) {

    }

}