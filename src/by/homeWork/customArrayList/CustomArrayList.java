package by.homeWork.customArrayList;

import java.util.*;
import java.util.function.Consumer;

/**
 * Simple custom version of collection ArrayList, supports a few main methods and quicksort with comparator
 *
 * @author https://github.com/Nikolay-Korovin
 */
public class CustomArrayList<T> implements Iterable<T> {

    /**
     * INIT_SIZE set size for any new CustomArrayList
     */
    private final static int INIT_SIZE = 16;

    /**
     *
     * empty static array for optimisation, when it initializes with some value, it gets real T[] array
     */
    public static Object[] emptyStaticArray = new Object[]{};


    /**
     * array is the main field in core of this structure
     */
    private Object[] array;
    /**
     * pointer as itself represents actual number of elements, it increases and decreases when it is necessary
     */
    private int pointer = 0;

    /**
     * when empty static array is erased by actual array, changes to true
     */
    private boolean isInitialized = false;

    /**
     * Constructs an empty list with an initial capacity of 16.
     */
    public CustomArrayList() {
    }

    /**
     * constructor with custom size of collection
     *
     * @param capacity sets a size for collection
     */
    public CustomArrayList(int capacity) {
        if (capacity < 0) {
            throw new NegativeArraySizeException();
        } else {
            array = new Object[capacity];
        }

    }

    public Object[] getEmptyStaticArray(){
        return emptyStaticArray;
    }

    /**
     * add an item to collection and also multiplies its capacity by 1.5,
     * when there is not enough space for additional elements
     *
     * @param item the element to add in this collection
     */
    public void add(T item) {
        if (!isInitialized) {
            array = new Object[INIT_SIZE];
            isInitialized = true;
        }
        if (pointer == array.length - 1) {
            changeSize((array.length * 3) / 2 + 1);
        }
        array[pointer++] = item;
    }

    /**
     * replaces an element by a specific index
     *
     * @param element the element to add in this collection
     * @param index   specific index for element to add
     * @throws IndexOutOfBoundsException if there is no such index
     */
    public void set(int index, T element) {
        if (!isInitialized) {
            array = new Object[INIT_SIZE];
            isInitialized = true;
        }
        if (index >= 0 && index <= pointer) {
            array[index] = element;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * deletes an element by a specific index and also cut its capacity by CUT_RATE in trim method if needed
     *
     * @param index specific index for element to delete
     * @throws IndexOutOfBoundsException if there is no such index
     */
    public void remove(int index) {
        if (index >= 0 && index <= pointer) {
            trim(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * find and deletes first founded element and also cut its capacity by CUT_RATE in trim method if needed
     *
     * @param value specific element to delete
     * @throws IndexOutOfBoundsException if there is no such index
     */
    public void remove(T value) {
        for (int i = 0; i < pointer; i++) {
            if (array[i].equals(value)) {
                trim(i);
                break;
            }
        }
    }

    /**
     * trims an array from index in parameter
     *
     * @param i specific element from to trim
     */
    private void trim(int i) {
        if (pointer - i >= 0) {
            System.arraycopy(array, i + 1, array, i, pointer - i);
        }
        array[pointer] = null;
        pointer--;

        int CUT_RATE = 2;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE) {
            changeSize(array.length / 2);
        }
    }

    /**
     * change a size of collection
     *
     * @param newLength condition for change
     */
    private void changeSize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

    /**
     * return an element from collection by specific index
     *
     * @param index specifies by which index to return the element
     * @return an element from collection by
     */
    public Object get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            return array[index];
        }
    }

    /**
     * drop number of elements to zero
     */
    public void clear() {
        if (!isInitialized) {
            for (int i = 0; i < pointer; i++) {
                array[i] = null;
            }
            pointer = 0;
        }
    }

    /**
     * return number of elements in collection
     *
     * @return actual size of collection
     */
    public int size() {
        return pointer;
    }

    /**
     * method for sorting
     *
     * @param c is comparator to set a condition for sorting
     *          it invoke qsort method (realisation of quick sort algorithm)
     *          also changes its type, due to parameterized comparator
     */
    public void sort(Comparator<? super T> c) {
        qsort((T[]) array, 0, pointer - 1, c);
    }

    /**
     * quick sort with comparator
     *
     * @param array is an arraylist to sort
     * @param left  first element to start sorting from
     * @param right last element to end sort
     * @param comp  is comparator to set a condition for sorting
     *              it invoked by sort method
     *              qsort method encapsulated to simplify user experience
     */
    private void qsort(T[] array, int left, int right, Comparator<? super T> comp) {
        if (pointer == 0) return;

        if (left >= right) return;

        //set a "pivot" element
        int middle = left + (right - left) / 2;
        T pivot = array[middle];

        // divide into subarrays, which is larger and smaller than the pivot element
        int i = left, j = right;
        while (i <= j) {
            while (comp.compare(array[i], pivot) < 0) {
                i++;
            }

            while (comp.compare(array[j], pivot) > 0) {
                j--;
            }

            //swaps
            if (i <= j) {
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        //calling recursion to sort the left and right parts
        if (left < j) qsort(array, left, j, comp);

        if (right > i) qsort(array, i, right, comp);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < pointer && array[currentIndex] != null;
            }

            @Override
            public T next() {
                return (T) array[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomArrayList<?> that = (CustomArrayList<?>) o;
        return pointer == that.pointer && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(pointer);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    /**
     * standard toString from java.util.Arrays with minor changes
     */
    @Override
    public String toString() {
        if (!isInitialized)
            return "[]";
        int iMax = pointer - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(array[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }
}


