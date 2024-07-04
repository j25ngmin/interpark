package com.interpark.ncl.config;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

    @SuppressWarnings("unchecked")
    public static OrderSpecifier<?>[] getOrderSpecifiers(Sort sorts, Class<?> clazz) {
        String className = clazz.getSimpleName();
        final String orderVariable = String.valueOf(Character.toLowerCase(className.charAt(0)))
                .concat(className.substring(1));
        return sorts.stream().map(order -> new OrderSpecifier(Order.valueOf(order.getDirection().toString()),
                new PathBuilder(clazz, orderVariable).get(order.getProperty()))).toArray(OrderSpecifier[]::new);
    }
}
