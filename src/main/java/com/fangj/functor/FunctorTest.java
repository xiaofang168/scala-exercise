package com.etouch.test.functor;

/**
 * @author fangjie
 * @date Created in 下午3:06 2021/3/30.
 */
public class FunctorTest {
    public static void main(String[] args) {
        Identity<String> idString = new Identity<>("abc");
        Identity<Integer> idInt = idString.map(String::length);
        System.out.println(idInt.value);
    }
}
