public class Float {
    public static void main(String[] args) {
        //浮点数并不精确
        double x = 1.0 / 10;
        double u = 1 - 9.0 / 10;
        System.out.println(x);
        System.out.println(u);
        //类型提升
        int n = 5;
        double d = 1.2 + 24.0 / n;//如果参与运算的两个数其中一个是整型，那么整型可以自动提升到浮点型
        System.out.println(d);
        double d1 = 1.2 + 24 / n;
        System.out.println(d1);//需要特别注意，在一个复杂的四则运算中，两个整数的运算不会出现自动提升的情况。
        //强制转型：可以将浮点数强制转型为整数。在转型时，浮点数的小数部分会被丢掉。如果转型后超过了整型能表示的最大范围，将返回整型的最大值。
        int n1 = (int) 12.3; // 12
        int n2 = (int) 12.7; // 12
        int n3 = (int) -12.7; // -12
        int n4 = (int) (12.7 + 0.5); // 13
        int n5 = (int) 1.2e20; // 2147483647
        //如果要进行四舍五入，可以对浮点数加上0.5再强制转型
        double d6 = 2.6;
        int d7 = (int) (d + 0.5);
        System.out.println(d7);

    }
}
