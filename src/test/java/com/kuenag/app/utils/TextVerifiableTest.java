package com.kuenag.app.utils;

import org.junit.Assert;
import org.junit.Test;

import static com.kuenag.app.utils.TextVerifiable.*;

public class TextVerifiableTest {

    @Test
    public void testValidPath() {
        Assert.assertTrue(isValidPath.test("src\\main\\resources"));
    }

    @Test
    public void testNotValidPath() {
        Assert.assertFalse(isValidPath.test("src\\main\\not_valid"));
    }

    @Test
    public void testNullPath() {
        Assert.assertFalse(isValidPath.test(null));
    }

    @Test
    public void testValidName() {
        Assert.assertTrue(isValidName.test("Andres Cruz"));
    }

    @Test
    public void testNotValidName() {
        Assert.assertFalse(isValidName.test("Andres"));
    }

    @Test
    public void testNullName() {
        Assert.assertFalse(isValidName.test(null));
    }

    @Test
    public void testValidDecimal() {
        Assert.assertTrue(isValidDecimal.test("11.5"));
    }

    @Test
    public void testNotValidDecimal() {
        Assert.assertFalse(isValidDecimal.test("1t1.5"));
    }

    @Test
    public void testNullDecimal() {
        Assert.assertFalse(isValidDecimal.test(null));
    }

    @Test
    public void testValidTime() {
        Assert.assertTrue(isValidTime.test("3.45.67"));
    }

    @Test
    public void testNotValidTime() {
        Assert.assertFalse(isValidTime.test("88.45.67"));
    }

    @Test
    public void testNullTime() {
        Assert.assertFalse(isValidTime.test(null));
    }
}