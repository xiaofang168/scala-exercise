package com.fangj.functor;

import java.util.function.Function;

/**
 * @author fangjie
 * @date Created in 下午3:04 2021/3/30.
 */
interface Functor<T, F extends Functor<?, ?>> {

    <R> F map(Function<T, R> f);

}
