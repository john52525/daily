package com.kidoo.daily.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {
    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 从当前IOC获取bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanId) {
        Object object = null;
        object = applicationContext.getBean(beanId);
        return (T) object;
    }

    /**
     * 从当前IOC获取bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        Object object = null;
        object = applicationContext.getBean(clazz);
        return (T) object;
    }
}
