package com.yh.kuangjia.util;

import java.math.BigDecimal;
import java.util.*;

public class ArrayUtil {

    public static String[] removeDuplicate(String[] array) {
        Set<String> arraySet = new HashSet<String>();
        for (String a : array)
            arraySet.add(a);
        String[] newArray = new String[arraySet.size()];
        return (String[]) arraySet.toArray(newArray);
    }

    public static boolean contains(String[] arr, String targetValue) {
        if (arr == null) {
            return false;
        }
        for (String s : arr) {
            if (s.equals(targetValue)) return true;
        }
        return false;
    }

    public static boolean contains(String[] arr, Integer targetValue) {
        if (arr == null) {
            return false;
        }
        for (String s : arr) {
            if (s.equals(targetValue.toString())) return true;
        }
        return false;
    }
    public static boolean contains(Integer[] arr, Integer targetValue) {
        if (arr == null) {
            return false;
        }
        for (Integer s : arr) {
            if (Objects.equals(s, targetValue)) return true;
        }
        return false;
    }

    public static int getArrayMaxValue(Integer[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int max = array[0];
        for (int v : array) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }

    public static BigDecimal getArrayMaxValue(BigDecimal[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        BigDecimal max = array[0];
        for (BigDecimal v : array) {
            if (v.compareTo(max) == 1) {
                max = v;
            }
        }
        return max;
    }

    public static BigDecimal getArrayMinValue(BigDecimal[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        BigDecimal min = array[0];
        for (BigDecimal v : array) {
            if (v.compareTo(min) == -1) {
                min = v;
            }
        }
        return min;
    }

    public static int getMaxIndex(Integer[] pageArray) {
        int[] orignArray = new int[pageArray.length];
        for (int i = 0; i < orignArray.length; i++) {
            orignArray[i] = i;
        }
        for (int i = 1; i < pageArray.length; i++) {
            if (pageArray[i - 1] > pageArray[i]) {
                int temp = pageArray[i - 1];
                pageArray[i - 1] = pageArray[i];
                pageArray[i] = temp;
                int result = orignArray[i - 1];
                orignArray[i - 1] = orignArray[i];
                orignArray[i] = result;
            }
        }
        return orignArray[orignArray.length - 1];
    }

    public static int[] stringToInts(String string) {
        String[] strings = StringUtil.splitForArray(string, ",");
        if (strings == null || strings.length == 0) {
            return null;
        }
        int[] result = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
    public static Integer[] stringToIntegers(String string) {
        String[] strings = StringUtil.splitForArray(string, ",");
        if (strings == null || strings.length == 0) {
            return null;
        }
        Integer[] result = new Integer[strings.length];
        for (int i = 0; i < strings.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
    public static List<Integer> splitForInt(String string){
        Integer[] ints = ArrayUtil.stringToIntegers(string);
        if (ints==null) return null;
        return new ArrayList<>(Arrays.asList(ints));
    }
}
