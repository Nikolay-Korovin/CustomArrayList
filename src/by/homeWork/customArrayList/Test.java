package by.homeWork.customArrayList;

import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        CustomArrayList<Integer> arrayList = new CustomArrayList<>();
        arrayList.add(1);
        arrayList.add(4);
        arrayList.add(3);
        arrayList.add(7);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(0);

        arrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(arrayList);
    }
}
