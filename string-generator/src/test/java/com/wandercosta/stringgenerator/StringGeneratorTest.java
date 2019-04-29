package com.wandercosta.stringgenerator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class to test the {@link StringGenerator}.
 *
 * @author Wander Costa (www.wandercosta.com)
 * @version 1.0
 */
public class StringGeneratorTest {

    private final StringGenerator generator = new StringGenerator();

    @Test
    public void shouldGenerate() {
        assertEquals(1, generator.generate(1).length());
        assertEquals(2, generator.generate(2).length());
        assertEquals(3, generator.generate(3).length());
        assertEquals(4, generator.generate(4).length());
        assertEquals(5, generator.generate(5).length());
        assertEquals(10, generator.generate(10).length());
        assertEquals(100, generator.generate(100).length());
        assertEquals(1000, generator.generate(1000).length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFalToGenerateWithLenght0() {
        generator.generate(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFalToGenerateWithLenghtNegative1() {
        generator.generate(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFalToGenerateWithLenghtNegative1000() {
        generator.generate(-1000);
    }

}
