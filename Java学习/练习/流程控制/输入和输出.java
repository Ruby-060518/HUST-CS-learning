import java.util.Scanner;

public class 输入和输出 {
    public static void main(String[] args){
        //println是print line的缩写，表示输出并换行。因此，如果输出后不想换行，可以用print()：
        System.out.print("A,");
        System.out.print("B,");
        System.out.print("C.");
        System.out.println();
        System.out.println("END");

        double d = 12900000;
        System.out.println(d);//格式化输出

        double d1 = 3.1415926;
        System.out.printf("%.2f\n", d1);
        System.out.printf("%.4f\n", d1);

        //%d整数
        //%x十六进制整数
        //%f浮点数
        //%e科学计数法表示的浮点数
        //%s字符串
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input your name please");
        String name = scanner.nextLine();
        System.out.print("Input your age please");
        int age = scanner.nextInt();
        System.out.printf("Hi,%s,you are %d\n",name,age);

        Scanner scanner1 = new Scanner(System.in);
        System.out.print("输入你上次考试的成绩");
        int grade1 =  scanner1.nextInt();
        System.out.print("输入你这次考试的成绩");
        int grade2 =  scanner1.nextInt();
        double improvement = (grade2-grade1)*100/grade1;
        System.out.printf("\"%.2f%%",improvement);


    }


}
