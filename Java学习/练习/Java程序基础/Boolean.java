public class Boolean {
    public static void main(String[] args) {
        boolean isGreater = 5 > 3; // true
        int age = 12;
        boolean isZero = age == 0; // false
        boolean isNonZero = !isZero; // true
        boolean isAdult = age >= 18; // false
        boolean isTeenager = age >6 && age <18; // true
        //短路运算
        boolean b = 5 < 3;
        boolean result = b && (5 / 0 > 0); // 此处 5 / 0 不会报错
        System.out.println(result);
        //三元运算符
        int n = -100;
        int x = n >= 0 ? n : -n;
        System.out.println(x);
        //eg:判断是否为Primary student
        int age1 = 7;
        boolean isPrimaryStudent = age1>=6 && age1<12;
        System.out.println(isPrimaryStudent ? "Yes" : "No");
    }

}
