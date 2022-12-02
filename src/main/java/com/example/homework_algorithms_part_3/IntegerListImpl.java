package com.example.homework_algorithms_part_3;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] data;
    private int size = 0;

    public IntegerListImpl() {
        this.data = new Integer[]{};
    }

    @Override
    public Integer add(Integer item) { // Метод вызывает метод add по index (добавляем в хвост списка)
        return add(size,item);
    }

    @Override
    public Integer add(int index, Integer item) { // Добавляем в любой индекс который есть в ArrayList
        checkItem(item);
        if (index < 0 || index > size) { // Проверка, что index не выходит за границы ArrayList (мы не даем добавлять элементы дальше чем размер ArrayList
            throw new IllegalArgumentException("Wrong index");
        }
        if (size + 1 > data.length){ // Если размер ArrayList больше, чем размер внутреннего хранилища, то метод grow увеличивает размерность массива
            grow();
        }
        System.arraycopy(this.data,index,this.data,index + 1,size - index); // Копируем массив до текущего размера в тот же самый массив со сдвигом
        this.data[index] = item; // Присваивание по index без сдвига
        this.size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItem(item);
        checkIndex(index);
        this.data[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int elementIndex = indexOf(item);
        if (elementIndex == -1) { // Если элемента нет
            throw new IllegalArgumentException("Failed to find the element to remove");
        }
        return remove(elementIndex); // Удаляем элемент по index
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer element = this.data[index];
        this.data[index] = null;
        if(index <size - 1){
            System.arraycopy(this.data,index +1,this.data,index,size - index - 1);
        }
        size--;
        this.data[index] = null;
        return element;
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        checkItem(item);
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    public int indexOfBinary(Integer item) {
        Integer[] array = toArray();
        sort(array);
        return binarySearch(array,item);
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(this.data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return this.data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            return false;
        }
        IntegerListImpl otherListImpl = (IntegerListImpl) otherList;
        if (otherListImpl.size != this.size){
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.data[i].equals(otherListImpl.data[i])){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            this.data[i] = null;
        }
        this.size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer [] integers = new Integer[size];
        System.arraycopy(this.data,0,integers,0,size);
        return integers;
    }

    private void checkIndex(int index) { // Проверяем не вышел ли index за границы
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Wrong index");
        }
    }
    private void checkItem(Integer item) {
        if (item == null){ // Проверка, что добавляемый item не равен null
            throw new IllegalArgumentException("Value in list cannot be null");
        }
    }

    private void sort(Integer[] array) {
        for (int i = 1; i < array.length; i++){
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }
    private int binarySearch(Integer[] array, int element) {
        int min = 0;
        int max = array.length - 1;
        while (min < max){
            int mid = (min + max) / 2;
            if(element == array[mid]){
                return mid;
            }
            if (element < array[mid]){
                max = mid - 1;
            }
            else {
                min = mid + 1;
            }
        }
        return -1;
    }
    private void grow() { // Увеличивает размерность ArrayList (увеличивает размерность внутреннего хранилища)
        this.data = Arrays.copyOf(this.data, this.data.length+1);
    }
}
