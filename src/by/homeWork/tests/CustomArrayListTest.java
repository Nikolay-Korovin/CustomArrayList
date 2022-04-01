package by.homeWork.tests;

import by.homeWork.customArrayList.CustomArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomArrayListTest {

    @Test
    void add() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        CustomArrayList<String> expected = new CustomArrayList<>();
        expected.add("one");
        actual.add("one");
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void set() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        CustomArrayList<String> expected = new CustomArrayList<>();
        expected.set(0,"one");
        actual.set(0,"one");
        Assertions.assertEquals(actual,expected);
    }

    @Test
    void remove() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        CustomArrayList<String> expected = new CustomArrayList<>();
        actual.add("one");
        actual.remove(0);
        Assertions.assertEquals(actual,expected);
    }

    @Test
    void testRemove() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        CustomArrayList<String> expected = new CustomArrayList<>();
        actual.add("one");
        actual.remove("one");
        Assertions.assertEquals(actual,expected);
    }

    @Test
    void get() {
        CustomArrayList<String> array = new CustomArrayList<>();
        array.add("one");
        String expected = (String)array.get(0);
        String actual = "one";
        Assertions.assertEquals(actual,expected);
    }

    @Test
    void clear() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        CustomArrayList<String> expected = new CustomArrayList<>();
        actual.add("one");
        actual.clear();
        Assertions.assertEquals(actual,expected);
    }

    @Test
    void size() {
        CustomArrayList<String> array = new CustomArrayList<>();
        array.add("one");
        int actual = array.size();
        int expected = 1;
        Assertions.assertEquals(actual,expected);
    }

    @Test
    void sort() {
        CustomArrayList<String> actual = new CustomArrayList<>();
        CustomArrayList<String> expected = new CustomArrayList<>();
        actual.add("c");
        actual.add("a");
        actual.add("d");
        actual.add("b");
        expected.add("a");
        expected.add("b");
        expected.add("c");
        expected.add("d");
        actual.sort(String::compareTo);
        Assertions.assertEquals(actual,expected);
    }
}