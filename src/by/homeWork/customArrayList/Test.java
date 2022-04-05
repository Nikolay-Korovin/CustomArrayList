package by.homeWork.customArrayList;

import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        CustomArrayList[] arrayLists = new CustomArrayList[1000000];
        for (int i = 0; i < 1000000; i++) {
            arrayLists[i] = new CustomArrayList();
        }
        for (int i = 0; i < 1000000; i++) {
            System.out.println(arrayLists[i].getEmptyStaticArray().length);
        }

    }
}
