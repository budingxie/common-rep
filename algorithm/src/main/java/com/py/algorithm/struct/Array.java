package com.py.algorithm.struct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * description
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/12/10
 */
public class Array {

    public static void main(String[] args) {
        List<String> ca = new ArrayList<>();
        ca.add("hell");
        ca.add("hello");
        ca.add("wor");
        ca.add("world");
        dfs(ca, "");
    }

    public static void isComposition(List<String> ca, String target) {
        // 1.先排序
        // 2.遍历target
        // 3.判断

        Collections.sort(ca);

        char[] targetChars = target.toCharArray();
        String tmpTar = "";
        for (int i = 0; i < targetChars.length; i++) {
            char atChar = targetChars[i];
            // 二分查找 开头是 atChar
            List<Integer> index = binSearch(ca, atChar);
            if (index.size() == 0) {
                // 不存在
                break;
            }
            comStr(ca, index, tmpTar, target);
        }
    }

    public static List<Integer> binSearch(List<String> ca, char at) {
        List<Integer> index = new ArrayList<>();
        // 普通查找
        for (int i = 0; i < ca.size(); i++) {
            String tmp = ca.get(i);
            if (tmp.charAt(0) == at) {
                index.add(i);
            }
        }
        return index;
    }

    public static boolean comStr(List<String> ca, List<Integer> index, String tmpTar, String target) {
        for (Integer j : index) {
            String tmp = ca.get(j);
            tmpTar = tmpTar + tmp;
            List<Integer> searchIndex = binSearch(ca, tmpTar.charAt(tmpTar.length() - 1));
            if (tmpTar.length() < target.length()) {
                return comStr(ca, searchIndex, tmpTar, target);
            } else {
                return false;
            }
        }
        return false;
    }


    public static void dfs(List<String> ca, String str) {
        if (str.length() != 0) {
            if ("bec".equals(str)) {
                System.out.println("----cg");
            }
        }
        for (int i = 0; i < ca.size(); i++) {
            LinkedList<String> tmpList = new LinkedList<>(ca);
            String item = tmpList.remove(i);
            dfs(tmpList, str + item);
        }

    }

}
