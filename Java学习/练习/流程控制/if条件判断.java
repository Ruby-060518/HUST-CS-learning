public class if条件判断 {
    public static void main(String[] args) {
        //浮点数在计算机中常常无法精确表示，并且计算可能出现误差，因此，判断浮点数相等用==判断不靠谱：
        double x = 1 - 9.0 / 10;
        if (Math.abs(x - 0.1) < 0.00001) {
            System.out.println("x is 0.1");
        } else {
            System.out.println("x is NOT 0.1");
        }
        String s1 = "hello";
        String s2 = "HELLO".toLowerCase();
        System.out.println(s1);
        System.out.println(s2);
        if (s1 == s2) {
            System.out.println("s1 == s2");
        } else {
            System.out.println("s1 != s2");
        }
        //要判断引用类型的变量内容是否相等，必须使用equals()
        if (s1.equals(s2)) {
            System.out.println("s1 == s2");
        } else  {
            System.out.println("s1 != s2");
        }
        //注意：执行语句s1.equals(s2)时，如果变量s1为null，会报NullPointerException
        //要避免NullPointerException错误，可以利用短路运算符&&
    }
    }

