package com.alicms.example.demo.demo.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * <p>
 *     注解demo
 * https://www.jianshu.com/p/596d389282a0
 * 一、常用内建注解（java.lang包下）：
 *  1.@Override：方法覆写
 *  2.@Deprecated：方法过期
 *  3.@SuppressWarnings：去除警告，all/deprecation/unchecked/..
 *  4.@FunctionalInterface：函数式接口（只能包含一个抽象方法）
 *
 * 二、自定义注解：
 *  当一个接口直接继承java.lang.annotation.Annotation接口时，
 *  仍是接口，而并非注解。要想自定义注解类型，只能通过@interface关键字的方式，其实通过该方式会隐含地继承Annotation接口。
 *
 * 三：常用元Annotation（自定义注解上的注解）：
 *  1.@Documented：实现了元数据的第一个功能：编写文档
 *  2.@Inherited：指定被它修饰的Annotation将具有继承性——如果某个类使用了@Xxx注解（定义@Xxx时使用了@Inherited修饰）修饰，则其子类将自动被@Xxx修饰。
 *  3.@Retention：表示该注解类型的注解保留的时长，当注解类型声明中没有@Retention元注解，则默认保留策略为RetentionPolicy.CLASS
 *      三种保留策略：
 *          1.SOURCE：存在源码中，经编译器丢弃。
 *          2.CLASS：经编译器生成class字节码文件，运行时vm丢弃。
 *          3.RUNTIME：运行时保留再vm中，可通过反射读取（此时才可进行代码分析）
 *  4.@Target：表示该注解类型的所适用的程序元素类型。当注解类型声明中没有@Target元注解，则默认为可适用所有的程序元素。
 *      程序元素(ElementType)8种程序元素：
 *          1.ANNOTATION_TYPE：类型声明
 *          2.CONSTRUCTOR：构造方法声明
 *          3.FIELD：字段声明
 *          4.LOCAL_VARIABLE:局部变量声明
 *          5.METHOD：方法声明
 *          6.PACKAGE：包声明
 *          7.PARAMETER：参数声明
 *          8.type：类、接口（包括注解）、枚举声明
 * </p>
 *
 * @author zhenghao
 * @date 2020/11/11 9:48
 */
public class AnnotationDemo {
    public static void main(String[] args) throws Exception {
        analysisRuntimeAnnotation2();
    }


    /**
     * 运行时解析注解：使用包名反射出Class获取注解信息
     *  需要注解是RUNTIME范围：注解信息也会加载到虚拟机VM中，所以可以通过反射来获取注解的相关信息
     *
     *  Java在java.lang.reflect 包下新增了AnnotatedElement接口，该接口代表程序中可以接受注解的程序元素，该接口主要有如下几个实现类：
     * 　　Class：类定义
     * 　　Constructor：构造器定义
     * 　　Field：累的成员变量定义
     * 　　Method：类的方法定义
     * 　　Package：类的包定义
     *  AnnotationElement接口api：
     *      1：<T extends Annotation> T getAnnotation(Class<T> annotationClass): 返回改程序元素上存在的、指定类型的注解，不存在返回null。
     *      2：Annotation[] getAnnotations()：返回该程序元素上存在的所有注解。
     * 　　 3：boolean is AnnotationPresent(Class< ? extends Annotation> annotationClass):判断该程序元素上是否包含指定类型的注解
     * 　　 4：Annotation[] getDeclaredAnnotations()：返回直接存在于此元素上的所有注解（忽略继承的注释）。（不存在则返回长度为零的一个数组。）该方法的调用者可以随意修改返回的数组；这不会对其他调用者返回的数组产生任何影响。
     */
    private static void analysisRuntimeAnnotation() throws Exception {
        //获取AnnotationExample的Class对象
        Class<?> cls = Class.forName("com.alicms.example.demo.demo.annotation.AnnotationTest");
        Object obj = cls.newInstance();
        //获取AnnotationExample类中的方法
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            //过滤不含自定义注解AnnotationInfo的方法
            boolean isHasAnnotation = method.isAnnotationPresent(MyAnnotation.class);
            if (isHasAnnotation) {
                // 私有方法也可访问
                method.setAccessible(true);
                //获取方法上的注解
                MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
                if (myAnnotation == null) return;
                //解析注解上对应的信息
                String[] value = myAnnotation.value();
                System.out.println("----------------------------start");
                System.out.println(Arrays.toString(value));
                System.out.println(myAnnotation.name());
                System.out.println(myAnnotation.revision());
                System.out.println("----------------------------end");
                method.invoke(obj);
            }
        }
    }

    private static void analysisRuntimeAnnotation2() throws Exception {
        String strFruitName = " 水果名称：";
        String strFruitColor = " 水果颜色：";

        Field[] fields = Apple.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName = field.getAnnotation(FruitName.class);
                strFruitName = strFruitName + fruitName.value();
                System.out.println(strFruitName);
            } else if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                strFruitColor = strFruitColor + fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            } else if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                String strFruitProvicer = " 供应商编号：" + fruitProvider.id()
                        + " 供应商名称：" + fruitProvider.name()
                        + " 供应商地址：" + fruitProvider.address();
                System.out.println(strFruitProvicer);
            }
        }
        Class<Apple> appleClass = Apple.class;
        Apple apple = appleClass.newInstance();
        Method[] methods = appleClass.getDeclaredMethods();
        for (Method method : methods) {
            if ("displayName".equals(method.getName())) {
                method.setAccessible(true);
                method.invoke(apple);
            }
        }

    }

    public static void analysisCompileAnnotation() {

    }
}
