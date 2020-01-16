package com.scau.myframework.test.test;


import com.scau.myframework.myorm.generator.JavaBeanFileGenerator;
import org.junit.Test;

/**
 * @description:
 * @author: lipan
 * @time: 2020/1/13 14:14
 */
public class GeneratorTest {
    @Test
    public void generatorTest(){
        JavaBeanFileGenerator.generate();
    }
}
