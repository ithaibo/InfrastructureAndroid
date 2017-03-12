package com.andy.infrastructure.demos.rxjava;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by andy on 17-3-12.
 */

public class OperatorFactory {

    /**
     * simple distinct
     * @return
     */
    public static Observable optDistinct() {
        String[] strArr = { "1", "2", "1", "12", "21", "3"};

        return Observable.from(strArr)
                .distinct();
    }

    public static Observable optDistinctByRule() {
        String[] strArr = { "1", "2", "1", "12", "21", "3"};

        return Observable.from(strArr)
                .distinct(new Func1<String, Character>() {
                    @Override
                    public Character call(String s) {
                        return s.charAt(1);
                    }
                });
    }

    /**
     * 偶数Filter
     * @param source
     * @return
     */
    public static Observable<Integer> optFilter(Observable<Integer> source) {
        return source.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer.intValue() %2 == 0;
            }
        });
    }
}
