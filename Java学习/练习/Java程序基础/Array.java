public class Array {
    public static void main(String[] args){
        int[] ns = new int[5];
        ns[0] = 68;
        ns[1] = 79;
        ns[2] = 90;
        ns[3] = 89;
        System.out.println(ns.length);//用数组变量.length获取数组大小

        int[] cj = new int[] {68 , 79, 88};//注意数组是引用类型，并且数组大小不可变。
        System.out.println(cj.length);//或者是简写为int[] ns = { 68, 79, 91, 85, 62 };

        //字符串数组
        String[] names = {
                "ABC","XYZ"
        };
        String[] names1 = {"ABC", "XYZ", "zoo"};
        String s = names1[1];
        names1[1] = "cat";
        System.out.println(s);// s是"XYZ"还是"cat"?
        //数组是同一个数据类型的集合，数组一旦创建后，大小就不可以变化
        //可以通过索引访问数组元素，但是索引超出范围将报错
        //数组类型可以为值类型（如int）或者是引用类型（如String），但是数组本身是引用类型




    }
}
