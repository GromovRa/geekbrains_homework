package ru.sgol;

public class MyAppTest {

    public MyAppTest() {
    }

    @Test (priority = 1)
    public void myTest11 () {
        System.out.println("я 1 1");
    }

    @Test (priority = 1)
    public void myTest12 () {
        System.out.println("я 1 2");
    }

    @Test (priority = 1)
    public void myTest13 () {
        System.out.println("я 1 3");
    }

    @Test (priority = 2)
    public void myTest2 () {
        System.out.println("я 2");
    }

    @Test (priority = 10)
    public void myTest10 () {
        System.out.println("я 10");
    }
    @BeforeSuite
    public void before () {
        System.out.println("я до");

    }

    @AfterSuite
    public void after () {
        System.out.println("я после");

    }

}
