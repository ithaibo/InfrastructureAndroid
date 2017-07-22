# Form Validation
在日常开发中经常会有表达的校验，这里就针对这种需求进行一个简单的整理。

## 数据类型
目前表单的常用数据类型有：字符串、数字类型（Long Integer Float Double Byte Short）。

## 校验需求
### 字符串
字符串校验，一般都可以用正则表达式来进行匹配。

### 数字类型
数字类型的数据校验，在业务上可以能有最大、最小、是否相等...
并且数字的类型还不太一样。

## 实现设计
具体某项数据的校验，主要是满足一个规则，即一个策略。Yes，策略模式就是我们要用的。

1. 实现一个基础BaseStrategy
 ```Java
 public interface BaseStrategy<T> {
     boolean match(T value);
 }
 ```
 解释一下：match方法就是具体的匹配算法，其中value是需要校验的源数据。
 
2. 字符串校验策略：
 ```Java
 public class TextRegularStrategy implements BaseStrategy<String> {
     private String regular;
 
     public TextRegularStrategy(String regular) {
         this.regular = regular;
     }
 
     @Override
     public boolean match(String value) {
         if (TextUtils.isEmpty(regular) || TextUtils.isEmpty(value)){
             return false;
         }
         return Pattern.matches(regular, value);
     }
 }
 ```
 
3. 数字校验策略：
```Java

public class NumberStrategy<T extends Number> implements BaseStrategy<T> {
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
        //...
    }

    /**
     * byte, short, int, long校验策略
     * @param value
     * @return
     */
    private boolean matchLong(Long value) {
        //...
    }
    
}
```
可以看出，数字的校验分成了两种：浮点数、非浮点数。

### 校验策略中数据从哪来？
答案是MatchStrategy对象：
```Java
public class MatchStrategy<T, ST extends BaseStrategy> {
    private T data;
    private ST strategy;

    public MatchStrategy(T data, ST strategy) {
        this.data = data;
        this.strategy = strategy;
    }

    public boolean doMatchAction() {
        return strategy.match(data);
    }
}
```
该类中负责保存需要校验的源数据，和执行校验策略。

### 如何同时校验多个数据？
为了统一校验流程，这里设计了一个FilterChain，管理校验策略及结果。
```Java
public class FilterChain {
    private Map<String, MatchStrategy> filterMap;

    public static class Builder {
        private FilterChain filterChain;
        public Builder() {
            filterChain = new FilterChain();
            filterChain.filterMap = new HashMap<>();
        }

        public Builder addFilter(String filterKey, MatchStrategy filter) {
            if(filterKey!=null && filter!=null) {
                filterChain.filterMap.put(filterKey, filter);
            }
            return this;
        }

        public FilterChain build() {
            return filterChain;
        }
    }

    public void startFilter(FilterCallBack callBack) {
        new MatchTask(callBack).execute(filterMap);
    }

    /**
     * 异步执行校验策略
     */
    private class MatchTask extends AsyncTask<Map<String, MatchStrategy>, Integer, Map<String, Boolean>>{
        private FilterCallBack callBack;

        public MatchTask(FilterCallBack callBack) {
            this.callBack = callBack;
        }

        public MatchTask() {
        }

        @Override
        protected Map<String, Boolean> doInBackground(Map<String, MatchStrategy>... params) {
            if (filterMap == null || filterMap.size()<=0) {
                return null;
            }

            Set<String> filterKeySet = filterMap.keySet();
            if (filterKeySet == null || filterKeySet.size()<=0) {
                return null;
            }

            Map<String, Boolean> result = new HashMap<>();
            for (String key : filterKeySet) {
                MatchStrategy strategy = filterMap.get(key);
                if (strategy !=null) {
                    result.put(key, strategy.doMatchAction());
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(Map<String, Boolean> stringBooleanMap) {
            super.onPostExecute(stringBooleanMap);
            if (callBack !=null) {
                callBack.onFilterCompleted(stringBooleanMap);
            }
        }
    }

    public interface FilterCallBack{
        void onFilterCompleted(Map<String, Boolean> result);
    }
}

```
上面的代码中可以看到，其基本步骤是
1. 通过Build添加MatchStrategy,并保存在HashMap中
2. 遍历Map执行所有的数据校验策略
3. 执行完毕，将结果返回给回掉接口

## 总结
虽然这里实现了一个完整的校验方案，但是比较简单。还有一些问题：
1. 目前只实现了数字、字符串的校验策略
2. 如何校验是否该项数据有更新？