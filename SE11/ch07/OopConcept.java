package SE11.ch07;

public class OopConcept {
    public static void main(String[] args){
        System.out.println(OopConcept.class.getName());

    }   

    //継承による差分プログラミングの例
    //Sample code
    static class No01 extends Parent{
        No01(){
            name = "java";
        }
        void hello(){
            System.out.println("Hello " + name);
        }
    }

    static void No02(){
        //サンプルコードなし
        //アクセス修飾子がデフォルトならpackageが同じクラスやサブクラスは使える
        //継承では次の２つは引き継がれない
        //・コンストラクタ
        //・privateメンバ(field, method)
        //思い出した、継承のイメージはスーパークラスとサブクラスの設計書がそれぞれあって、１つの箱に入れているイメージ
        //正確にはサブクラスのインスタンスはスーパークラスとサブクラスのインスタンスをそれぞれ持っている
    }

    static void No03(){
        //サンプルコードなし
        //インターフェースはよく知らん!!
        //インターフェースってクラスとどう違うの？特に抽象クラスとの違いは？
    }
    
    static class No04{
        public interface A {
            default void sample(){ // defaultメソッドを定義することでインターフェースに実装を定義できる
                System.out.println("sample");
            }
            
        }
        public static class B implements A {
        }
        public static class Main {
            public static void main(String[] args) {
                A a = new B();
                a.sample();
            }
        }
    }

    static class No05{
        public interface A {
            @Override
            default String toString() { //interfaceの実現メソッドをObjectクラスをオーバーライドして表現することはできない
                return "A";
            }
            // これはコンパイルエラーになる
            //なぜできない?
        }
        public class B implements A {
            @Override
            public String toString(){
                return "B";
            }
        }
        public class Main {
            public static void main(String[] args) {
                A a = new B()
                System.out.println(a);
            }
        }
    }

    static class No06{
        public interface A {
            default void sample(){
                System.out.println("Hello");
            }
        }

        interface B extends A{
        }

        public static class C implements B{
            @Override
            public void sample(){
                B.super.sample(); //defaultメソッドを呼び出すにはインターフェース名をsuperの前に付ける
                //ただし、インターフェースを直接実現したクラス内でのみ有効
                //2つ以上の実現や継承の階層をまたいで呼び出すことはできない
                System.out.println("Java");
            }
            
        }

        public static class Main{
            public static void main(String[] args) {
                A a = new C();
                a.sample();
            }
        }
    }

    static class No07{
        
    }

}