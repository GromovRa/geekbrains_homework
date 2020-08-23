package ru.sgol;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class testClassProcessor {

    public static void process(Class testClass) throws IllegalAccessException, InstantiationException {
        HashMap<Integer, List<Method>> methodsMap = new HashMap<>();

        Object obj = testClass.newInstance();

        Method[] methodArr = testClass.getMethods();

        for (Method m : methodArr) {


            if (m.isAnnotationPresent(Test.class)) {
                int methodPriority = m.getAnnotation(Test.class).priority();
                if (methodPriority > 10 || methodPriority < 1) {
                    throw new IllegalArgumentException("Не верно указан приоритет теста у метода " + m + " должен быть от 1 до 10");
                }

                List<Method> methodList = methodsMap.computeIfAbsent(methodPriority, k -> new ArrayList<>());
                methodList.add(m);

            } else if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (methodsMap.get(0) != null) {
                    throw new RuntimeException();
                }

                methodsMap.put(0, Collections.singletonList(m));

            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                if (methodsMap.get(11) != null) {
                    throw new RuntimeException();
                }

                methodsMap.put(11, Collections.singletonList(m));
            }
        }

        methodsMap.entrySet().stream()
                .sorted((o1, o2) -> o1.getKey()-o2.getKey())
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .forEach(method -> {
                    try {
                        method.invoke(obj);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });


    }

    public static void process(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        process(Class.forName(className));
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        process(MyAppTest.class);
    }
}
