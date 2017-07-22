package com.andy.formmatcher.strategy;

/**
 * Created by Andy on 2017/7/21.
 */

public class NumberStrategy<T extends Number> implements BaseStrategy<T> {
    public static final int TYPE_MIN = 1;
    public static final int TYPE_MAX = 2;
    public static final int TYPE_BOTH = 4;
    public static final int TYPE_EQUAL = 8;

    protected T min;
    protected T max;
    protected int type;

    @Override
    public boolean match(T value) {
        if (value instanceof Double || value instanceof Float ) {
            return matchDouble(value.doubleValue());
        } else if (value instanceof Integer || value instanceof Long || value instanceof Short || value instanceof Byte){
            return matchLong(value.longValue());
        }
        return false;
    }

    /**
     * 浮点数类型校验策略
     * @param value
     * @return
     */
    private boolean matchDouble(Double value) {
        if (value == null) {
            return false;
        }
        switch (type) {
            case TYPE_MIN:
                return value >= min.doubleValue();
            case TYPE_MAX:
                return value <= max.doubleValue();
            case TYPE_EQUAL:
                return value ==  min && value ==  max;
            case TYPE_BOTH:
                return value >= min.doubleValue() && value <= max.doubleValue();
            default:
                return false;
        }
    }

    /**
     * byte, short, int, long校验策略
     * @param value
     * @return
     */
    private boolean matchLong(Long value) {
        if (value == null) {
            return false;
        }
        switch (type) {
            case TYPE_MIN:
                return value >= min.longValue();
            case TYPE_MAX:
                return value <= max.longValue();
            case TYPE_EQUAL:
                return value ==  min && value ==  max;
            case TYPE_BOTH:
                return value >= min.longValue() && value <= max.longValue();
            default:
                return false;
        }
    }

    public NumberStrategy(T min, T max, int type) {
        this.min = min;
        this.max = max;
        this.type = type;
    }
}
