public class switch多重选择 {
    public static void main(String[] args) {
//如果有几个case语句执行的是同一组语句块，可以这么写
                int option = 2;
                switch (option) {
                    case 1:
                        System.out.println("Selected 1");
                        break;
                    case 2:
                    case 3:
                        System.out.println("Selected 2, 3");
                        break;
                    default:
                        System.out.println("Selected other");
                        break;
                }//不要忘记break，不要忘记default
        //新代码格式
        String fruit = "ap";
        int opt = switch(fruit){
            case "apple" ->1;
            case "orange","mango" ->2;
            case "banan" ->3;
            default->{
                int code = fruit.hashCode();
                yield code;
            }
        };//注意赋值语句要以分号结束
        System.out.println("opt = " +opt);

            }
        }



