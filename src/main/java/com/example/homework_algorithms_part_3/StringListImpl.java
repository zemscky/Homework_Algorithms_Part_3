package com.example.homework_algorithms_part_3;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] data;
    private int size = 0;

    public StringListImpl() {
        this.data = new String[]{};
    }

    @Override
    public String add(String item) { // Метод вызывает метод add по index (добавляем в хвост списка)
        return add(size,item);
    }

    @Override
    public String add(int index, String item) { // Добавляем в любой индекс который есть в ArrayList
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
    public String set(int index, String item) {
        checkItem(item);
        checkIndex(index);
        this.data[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        int elementIndex = indexOf(item);
        if (elementIndex == -1) { // Если элемента нет
            throw new IllegalArgumentException("Failed to find the element to remove");
        }
        return remove(elementIndex); // Удаляем элемент по index
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String elem = this.data[index];
        this.data[index] = null;
        if(index <size - 1){
            System.arraycopy(this.data,index +1,this.data,index,size - index - 1);
        }
        size--;
        this.data[index] = null;
        return elem;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        checkItem(item);
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkItem(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(this.data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return this.data[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            return false;
        }
        StringListImpl otherListImpl = (StringListImpl) otherList;
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
    public String[] toArray() {
        String [] strings = new String[size];
        System.arraycopy(this.data,0,strings,0,size);
        return strings;
    }

    private void checkIndex(int index) { // Проверяем не вышел ли index за границы
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Wrong index");
        }
    }
    private void checkItem(String item) {
        if (item == null){ // Проверка, что добавляемый item не равен null
            throw new IllegalArgumentException("Value in list cannot be null");
        }
    }
    private void grow() { // Увеличивает размерность ArrayList (увеличивает размерность внутреннего хранилища)
        int newSize = this.data.length * 2 + 1;
        this.data = Arrays.copyOf(this.data,newSize);
    }
}
