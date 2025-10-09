package SE11.ch06;
public class Item {
    public static class No1Item {
        private int num = 10;
        public void setNum(int num){
            this.num = num;
        } 
        public int getNum(){return this.num;}
    }
    
    public static class No2Item {
        public String name;
        public int price;
        public void printInfo(){
            System.out.println(name + ", " + price);
        }
    }

    public static class No5Sample{
        static int num = 0;
    }
    
    public class No7Sample{
        public void hello(){
            System.out.println("hello");
        }
    }

    public class No8Sample{
        public int add(Integer a ,Integer b){
            return a + b;//auto unboxing
        }
    }

    public class No11Sample{
        private String value;
        public void setValue(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value; 
        }
    }

    //いい加減Itemをnewするのが面倒になってきたので以降はstatic classにする
    public static class No12Sample{
        float divide (int a, int b){
            return (float)a / (float)b;
        }
    }

    public static class No13Sample{
        public int method(int a, int b) {
            return  a+b;
        }
    }
    
}