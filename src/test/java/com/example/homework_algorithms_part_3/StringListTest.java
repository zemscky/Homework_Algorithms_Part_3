package com.example.homework_algorithms_part_3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringListTest {
    private StringList stringList;

    @BeforeEach
    public void setUp() {
        this.stringList = new StringListImpl();
    }

    @Test
    public void whenItemAddedThenItCanBeFoundItList() {
        this.stringList.add("test");
        Assertions.assertEquals(1, this.stringList.size());
    }

    @Test
    public void whenItemAddedToSpecificIndexWhenElementsIsShiftedRight() {
        this.stringList.add("test");
        this.stringList.add(0, "test1");
        this.stringList.add(1, "test2");
        Assertions.assertEquals(3, this.stringList.size());
        Assertions.assertEquals("test1", this.stringList.get(0));
        Assertions.assertEquals("test2", this.stringList.get(1));
        Assertions.assertEquals("test", this.stringList.get(2));
    }

    @Test
    public void whenValueIsSetWhenGetReturnsThisValue() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.add("test2");

        this.stringList.set(1, "NEW_VALUE");
        Assertions.assertEquals(3, this.stringList.size());
        Assertions.assertEquals("NEW_VALUE", this.stringList.get(1));
    }

    @Test
    public void whenTwoElementsAddedInListThenIndexOfReturnsFirst() {
        this.stringList.add("test");
        this.stringList.add("test");
        Assertions.assertEquals(0, this.stringList.indexOf("test"));
    }

    @Test
    public void whenTwoElementsAddedInListThenIndexOfUnknownReturnsMinusOne() {
        this.stringList.add("test");
        this.stringList.add("test");
        Assertions.assertEquals(-1, this.stringList.indexOf("NON_EXISTING_VALUE"));
    }

    @Test
    public void whenTwoElementsAddedInListThenLastIndexOfReturnsSecond() {
        this.stringList.add("test");
        this.stringList.add("test1");

        Assertions.assertEquals(0, this.stringList.indexOf("test"));
    }

    @Test
    public void whenTwoElementsAddedAndSecondRemovedTheSizeIsOne() {
        this.stringList.add("test");
        this.stringList.add("test");
        this.stringList.remove(1);
        Assertions.assertEquals(1, this.stringList.size());
        Assertions.assertEquals("test", this.stringList.get(0));
    }

    @Test
    public void whenTwoElementsAddedAndFirstRemovedTheSizeIsOne() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.remove(1);
        Assertions.assertEquals(1, this.stringList.size());
        Assertions.assertEquals("test", this.stringList.get(0));
    }

    @Test
    public void whenTwoElementsAddedAndSecondRemovedTheSizeIsTwo() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.add("test2");
        this.stringList.remove(2);
        Assertions.assertEquals(2, this.stringList.size());
        Assertions.assertEquals("test", this.stringList.get(0));
        Assertions.assertEquals("test1", this.stringList.get(1));
    }

    @Test
    public void whenElementsAddedAndClearIsCalledThenListIsEmpty() {
        this.stringList.add("test");
        this.stringList.add("test1");
        this.stringList.add("test2");
        this.stringList.clear();
        Assertions.assertTrue(this.stringList.isEmpty());
        Assertions.assertEquals(0, this.stringList.size());

    }
}