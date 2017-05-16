package indi.jackie.acmpu.p1002;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * @author jackie chen
 * @create 2017-05-04
 * @description 487-3279
 */
public class P1003 {

    @Test
    public void p1003() {
        String data = "12\n" +
                "4873279\n" +
                "ITS-EASY\n" +
                "888-4567\n" +
                "3-10-10-10\n" +
                "888-GLOP\n" +
                "TUT-GLOP\n" +
                "967-11-11\n" +
                "310-GINO\n" +
                "F101010\n" +
                "888-1200\n" +
                "-4-8-7-3-2-7-9-\n" +
                "487-3279";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner cin = new Scanner(System.in);
            int index = 0;
            List<String> cinList = new ArrayList<String>();
            if (cin.hasNext()) {
                int num = cin.nextInt();
                while (cin.hasNext() && index++ < num) {
                    cinList.add(cin.next());
                }
                dealList(cinList);
            }
        } finally {
            System.setIn(stdin);
        }

    }

    /**
     * 处理输入字符
     *
     * @param cinList 字符列表
     */
    private void dealList(List<String> cinList) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        for (int i = 0; i < cinList.size(); i++) {
            String temp = dealStr(cinList.get(i));
            if (result.containsKey(temp)) {
                result.put(temp, result.get(temp) + 1);
            } else {
                result.put(temp, 1);
            }
        }

        //筛选符合条件的结果并输出
        printRealResult(result);
    }

    /**
     * 输出重复的电话number
     *
     * @param result
     */
    private void printRealResult(Map<String, Integer> result) {
        Iterator<Map.Entry<String, Integer>> iterator = result.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (entry.getValue() > 1) {
                System.out.print(entry.getKey());
                System.out.print(" ");
                System.out.println(entry.getValue());
            }
        }
    }

    /**
     * 转换输入字符串为目标电话号码
     *
     * @param inputStr
     * @return
     */
    private String dealStr(String inputStr) {
        if (null == inputStr) {
            return null;
        }
        inputStr = inputStr.replace("-", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputStr.length(); i++) {
            sb.append(replaceChar(inputStr.charAt(i)));
        }
        sb.insert(3, '-');
        return sb.toString();
    }

    /**
     * 替换字符（字母或数字）为数字
     *
     * @param c 输入字符
     * @return 数字
     */
    private Integer replaceChar(char c) {
        Integer result;
        switch (c) {
            case '1':
                result = 1;
                break;
            case '2':
            case 'A':
            case 'B':
            case 'C':
                result = 2;
                break;
            case '3':
            case 'D':
            case 'E':
            case 'F':
                result = 3;
                break;
            case '4':
            case 'G':
            case 'H':
            case 'I':
                result = 4;
                break;
            case '5':
            case 'J':
            case 'K':
            case 'L':
                result = 5;
                break;
            case '6':
            case 'M':
            case 'N':
            case 'O':
                result = 6;
                break;
            case '7':
            case 'P':
            case 'R':
            case 'S':
                result = 7;
                break;
            case '8':
            case 'T':
            case 'U':
            case 'V':
                result = 8;
                break;
            case '9':
            case 'X':
            case 'Y':
                result = 9;
                break;
            case '0':
            default:
                result = 0;
                break;
        }
        return result;
    }


}
