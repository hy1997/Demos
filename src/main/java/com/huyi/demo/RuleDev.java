package com.huyi.demo;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * QQ 165149324
 * 臧英明
 *
 * @author
 * @create 2019-07-24
 */
public class RuleDev {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        /**
         * 扫描出来所以的Bean
         * 封装一个Bean 对象
         */
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        //
        BeanFactoryPostProcessor BeanFactoryPostProcessor;

        /**
         * DefaultListableBeanFactory
         * 	包含 定义 扫描出来所以的Bean
         * 	/** Map of bean definition objects, keyed by bean name */
        Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(256);
        //可能需要区实例化的类
        // List<String> beanNames = new ArrayList<String>(this.beanDefinitionNames);

        //扫描所有类并put到beanDefinitionMap 里面
        //invokeBeanFactoryPostProcessors

        //实例化Bean
        //finishBeanFactoryInitialization(beanFactory);
        //preInstantiateSingletons

//        List<String> beanNames = new ArrayList<String>(this.beanDefinitionNames);

        //获取Bean对象
        //getBean（）
        //doGetBean

        //第一次
        // getSingleton
        //从单例池里面拿   所有（springBean实例化后存放的的Map）
        final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);
        //判断对象是否在创建中   （循环依赖）
        //     isSingletonCurrentlyInCreation(beanName)
        //当前正在创建的bean的名称
        final Set<String> singletonsCurrentlyInCreation = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>(16));

        //单例  原型
        //后置处理器
        //resolveBeforeInstantiation


//        BeanPostProcessor


        rootBeanDefinition.setBeanClassName("");
    }


}
