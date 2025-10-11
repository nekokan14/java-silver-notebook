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
    
    public static class No20Sample{
        void No20Sample(){
            System.out.println("hello.");
        }
    }

    public static class No21Sample{
        No21Sample(){
            System.out.println("A");
        }//コンパイルエラーになりそうだが...
        {
            System.out.println("B");
        }
        //なるほどコンストラクタがどちらかが判断できないから実行エラーになるか？
        //そうか！{}はclassに対する初期化子で、コンストラクタより先に実行されるのか！
    }

    public static class No22Sample{
        static int num;
        {
            num = 10;
        }
        {//初期化子って複数書けるのか?
            num = 20;
        }
        static { //クラスロード時に実行される これはstatic領域に配置されるfieldだから可能で、
                //static領域に配置されないfieldではコンパイルエラーになる?
            num = 20;
        }
        //初期化子複数化書ける。同じくstatic初期化子も複数書ける。
        //ただし、順番は上から順に実行される。
        public No22Sample(){
            num = 100;
        }
    }

    public static class No23Sample{
        //戻り値が明記されているからコンストラクタではないメソッドとして扱われる?
        void No23Sample(){
            System.out.println("A");
        }
        //コンストラクタを定義すると引数が必要ないデフォルトコンストラクタが自動生成されないため
        //インスタンスを生成時には引数を渡す必要がある。
        No23Sample(String str){
            System.out.println(str);
        }
    }
}