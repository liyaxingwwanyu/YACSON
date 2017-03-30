package util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sheenly on 16/3/7.
 */
public class CollectionUtil {

    public static <T> Map<String, T> arrayToMap(String aKeys[], T aValues[]) {
        if (aKeys == null || aValues == null) {
            return null;
        }
        int lKeySize = aKeys.length;
        int lValueSize = aValues.length;
        if (lKeySize > lValueSize) {
            lKeySize = lValueSize;
        }
        Map<String, T> lMap = new HashMap<String, T>();
        for (int i = 0; i < lKeySize; i++) {
            lMap.put(aKeys[i], aValues[i]);
        }
        return lMap;
    }

    public static <T> Map<String, List<T>> arrayToMap(String aKeys[], T aValues[][]) {
        if (aKeys == null || aValues == null) {
            return null;
        }
        int lKeySize = aKeys.length;
        int lValueSize = aValues.length;
        if (lKeySize > lValueSize) {
            lKeySize = lValueSize;
        }
        Map<String, List<T>> lMap = new HashMap<String, List<T>>();
        for (int i = 0; i < lKeySize; i++) {
            T lItemValue[] = aValues[i];
            if (lItemValue != null) {
                List<T> lValueItem = Arrays.asList(lItemValue);
                lMap.put(aKeys[i], lValueItem);
            }
        }
        return lMap;
    }

    public static List<Integer> convertStrListToInt(List<String> aList) {
        List<Integer> lList = new ArrayList<Integer>();
        try {
            for (String lStr : aList) {
                lList.add(Integer.parseInt(lStr));
            }
        } catch (Exception e) {
            lList.clear();
            e.printStackTrace();
        }
        return lList;
    }

    public static List<String> stringToListOfComma(String aSource) {
        List<String> aList = new ArrayList<String>();
        if (!TextUtils.isEmpty(aSource)){
            aList = Arrays.asList(aSource.split(","));
        }
        return aList;
    }

    public static List<String> stringToListOfCommaEx(String aSource) {
        List<String> aList = new ArrayList<String>();
        if (!TextUtils.isEmpty(aSource)){
            aList.addAll(Arrays.asList(aSource.split(",")));
        }
        return aList;
    }

    public static <T> String listToStringOfComma(List<T> aList) {
        if (aList == null || aList.size() == 0)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aList.size(); i++) {
            sb.append(aList.get(i));
            sb.append(",");
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static <T> List<Integer> getChangeSort(List<T> aSourceList, List<T> aCompareList) {
        List<Integer> lSortList = new ArrayList<Integer>();
        if (aSourceList != null && aCompareList != null && aSourceList.size() >= aCompareList.size()) {
            for (int i = 0; i < aCompareList.size(); i++) {
                int lIndex = aSourceList.indexOf(aCompareList.get(i));
                lSortList.add(lIndex);
            }
        }
        return lSortList;
    }
}
