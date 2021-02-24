package com.noodle.leetcode.router;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author heshufan
 * @date 2021/2/5
 */
@Target(ElementType.TYPE)//作用于类、接口、泛型等
@Retention(RetentionPolicy.CLASS)//只保留在class文件中
public @interface Route {
    /**
     * 路由的路径
     * @return
     */
    String path();

    /**
     * 将路由节点进行分组，可以实现动态加载
     * @return
     */
    String group() default "";
}
