package com.fangj.functor;

import java.util.function.Function;

/**
 * @author fangjie
 * @date Created in 下午3:05 2021/3/30.
 */
class Identity<T> implements Functor<T, Identity<?>> {

    public final T value;

    Identity(T value) {
        this.value = value;
    }

    @Override
    public <R> Identity<R> map(Function<T, R> f) {

        final R result = f.apply(value);

        return new Identity<>(result);

    }

}
