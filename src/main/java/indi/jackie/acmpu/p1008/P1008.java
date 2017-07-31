package indi.jackie.acmpu.p1008;

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
 * @description P1008
 */
public class P1008 {

    private static final Integer DAYS_OF_ONE_HAAB_YEAR = 365;//Haab一年365天
    private static final Integer DAYS_OF_ONE_TZOLINK_YEAR = 260;//Tzolink一年260天

    @Test
    public void p1008() {

        String data = "4\n" +
                "10. zac 0\n" +
                "0. pop 0\n" +
                "10. zac 1995\n" +
                "4. uayet 259";
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

    /**
     * 处理输入列表
     *
     * @param cinList
     */
    private void dealList(List<String> cinList) {
        String[] tempArray;
        MayaDto.Haab haab = new MayaDto().new Haab();
        List<MayaDto.Tzolkin> result = new ArrayList<>();
        for (int i = 0; i < cinList.size(); i++) {
            tempArray = cinList.get(i).split(Constants.WHITE_SPACE);
            haab.setDay(Integer.valueOf(tempArray[0].substring(0, tempArray[0].length() - 1)));
            haab.setHaabMonth(HaabMonth.valueOf(tempArray[1]));
            haab.setYear(Integer.valueOf(tempArray[2]));
            result.add(convertTzolink(haab));
        }

        //输出结果
        System.out.println(result.size());
        CommonUtil.printListResult(result);
    }

    /**
     * Haab 转 Tzolink
     *
     * @param haab
     * @return
     */
    private MayaDto.Tzolkin convertTzolink(MayaDto.Haab haab) {
        MayaDto.Tzolkin tzolkin = new MayaDto().new Tzolkin();
        Integer sumDay = haab.getYear() * this.DAYS_OF_ONE_HAAB_YEAR + haab.getHaabMonth().ordinal() * 20 + haab.getDay() + 1;
        //年从0开始，注意一年的最后一天
        if (sumDay % this.DAYS_OF_ONE_TZOLINK_YEAR != 0) {
            tzolkin.setYear(sumDay / 260);
            tzolkin.setTzolinkDay(sumDay % 20 == 0 ? TzolinkDay.ahau : TzolinkDay.values()[sumDay % 20 - 1]);
            //Tzolink number 从1开始
            tzolkin.setNumber(sumDay % 13 == 0 ? 13 : sumDay % 13);
        } else{
            tzolkin.setYear(sumDay / 260 == 0 ? 0 : sumDay / 260 - 1);
            tzolkin.setTzolinkDay(TzolinkDay.ahau);
            tzolkin.setNumber(13);
        }
        return tzolkin;
    }
}