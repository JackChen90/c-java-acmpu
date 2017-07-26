package indi.jackie.acmpu.p1007;

import indi.jackie.acmpu.util.CommonUtil;
import indi.jackie.acmpu.util.StringUtil;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static java.util.Comparator.comparing;

/**
 * @author jackie chen
 * @create 2017/7/25
 * @description P1007
 */
public class P1007 {

    private Integer length;//单行长度
    private Integer row;//行数

    @Test
    public void p1007() {
        String data = "10 6\n" +
                "AACATGAAGG\n" +
                "TTTTGGCCAA\n" +
                "TTTGGCCAAA\n" +
                "GATCAGATTA\n" +
                "CCCGGGGGGA\n" +
                "ATCGATGCAT";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner sin = new Scanner(System.in);
            List<DNADto> cinList = new ArrayList<>();
            if (sin.hasNext()) {
                this.length = sin.nextInt();
            }
            if (sin.hasNext()) {
                this.row = sin.nextInt();
            }
            if (this.length == null || this.row == null) {
                return;
            }
            Integer index = 0;
            DNADto dto;
            while (sin.hasNext() && index++ < this.row) {
                dto = new DNADto();
                String a = sin.nextLine();
                if (!StringUtil.isEmpty(a)) {
                    dto.setValue(a);
                    cinList.add(dto);
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
    private void dealList(List<DNADto> cinList) {
        Integer sortWeight;
        for (int i = 0; i < cinList.size(); i++) {
            //获取排序权值
            sortWeight = getSortWeight(cinList.get(i).getValue());
            cinList.get(i).setSortWeight(sortWeight);
        }
//        cinList.sort(Comparator.comparing(DNADto::getSortWeight)); //lambda表达式
        Collections.sort(cinList);
        //输出结果
        CommonUtil.printListResult(cinList);
    }

    /**
     * 获取基因数列排序权重值
     *
     * @param value
     * @return
     */
    private Integer getSortWeight(String value) {
        char[] arrayStr = value.toCharArray();
        Integer sum = 0;
        char tempL;
        char tempR;
        for (int i = 0; i < this.length; i++) {
            tempL = arrayStr[i];
            for (int j = i + 1; j < this.length; j++) {
                tempR = arrayStr[j];
                if (tempL > tempR) {
                    sum++;
                } else {
                    break;
                }
            }
        }
        return sum;
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
