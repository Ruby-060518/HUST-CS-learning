public class CharandString {
    public static void main(String[] args){
        //字符类型
        char c1 = 'A';
        char c2 = '中';

        int n1 = 'A';
        int n2 = '中';//要显示一个字符的Unicode编码，只需将char类型直接赋值给int类型即可

        // 注意是十六进制:
        char c3 = '\u0041'; // 'A'，因为十六进制0041 = 十进制65
        char c4 = '\u4e2d'; // '中'，因为十六进制4e2d = 十进制20013

        //字符串类型:和char类型不同，字符串类型String是引用类型，我们用双引号"..."表示字符串。一个字符串可以存储0个到任意个字符
        String s = "";//空字符串，包含0个字符
        String s1 = "A";//包含一个字符
        String s2 = "ABC";//包含三个字符
        String s3 = "中文 abc";//包含6个字符，其中有一个空格
        String s4 = "abc\"xyz"; // 包含7个字符: a, b, c, ", x, y, z
        String s5 = "abc\\xyz"; // 包含7个字符: a, b, c, \, x, y, z
        //常见的转义字符包括：\":"  \':'  \\:\  \n：换行  \r:回车  \t:tab
        String s6 = "ABC\n\u4e2d\u6587"; // 包含6个字符: A, B, C, 换行符, 中, 文

        //字符串连接：Java的编译器对字符串做了特殊照顾，可以使用+连接任意字符串和其他数据类型，这样极大地方便了字符串的处理。
        String s7 = s2 + s3 + s4 + s5 + s6;
        System.out.println(s7);
        int age = 25;
        String s8 = "age is " + age;//如果用+连接字符串和其他数据类型，会将其他数据类型先自动转型为字符串，再连接
        System.out.println(s8);// age is 25

        //多行字符串
        String s9 = """
                Java
                Users
                Intellij IDEA
                """;
                System.out.println(s9);//上述多行字符串实际上是4行，在最后一个DESC后面还有一个\n
        String s10 = """
                Java
                Users
                Intellij IDEA""";//如果我们不想在字符串末尾加一个\n，就需要这么写
        System.out.println(s10);//还需要注意到，多行字符串前面共同的空格会被去掉，且总是以最短的行首空格为基准
        //空值null
        String s11 = null; // s1是null
        String s12 = s1; // s2也是null
        String s13 = ""; // s3指向空字符串，不是null
        //注意要区分空值null和空字符串""，空字符串是一个有效的字符串对象，它不等于null
        int a = 72;
        int b = 105;
        int c = 65281;

        String s14 = ""+(char)a+(char)b+(char)c;//转化
        System.out.println(s14);

    }
}
