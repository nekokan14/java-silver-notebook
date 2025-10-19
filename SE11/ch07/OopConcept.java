package SE11.ch07;

import SE11.ch06.ex26.Parent;
import SE11.ch07.OopConcept.No09.ConcreteSample;

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
        public interface A {
            default void test(){
                System.out.println("A");
            }
        }
        public interface B {
            default void test(){
                System.out.println("B");
            }
        }
        public static class Main implements A,B{
            public static void main(String[] args){
               new Main().test();
            }
        }
    }

    static class No08{
        //サンプルコードなし
        //抽象クラスとはインターフェースとクラスの両方を兼ね備えたもの
        //つまり、実装を持たない抽象メソッドと実装を持つ具象メソッドの両方を持てる

        public abstract class AbstractSample{
            //抽象メソッド (サブクラスが引き継ぐ)
            public abstract void methodB();
            
            //具象メソッド (サブクラスで実装する)
            public void methodA(){
                //any code
            }
        }

        public class Main {
            public static void main(String[] args){
                //インターフェースの性質をもっているため
                //(具象メソッドしかなくても) 抽象クラスはインスタンス化できない
                //AbstractSample abs = new AbstractSample(); //コンパイルエラー
                //抽象クラスを継承した具象クラスをインスタンス化する
                AbstractSample abs = new ConcreteSample();
            }
        }
   }
    static class No09{
        static abstract class AbstractSample{
            public void sample(){
                System.out.println("A");
                test();
                System.out.println("C");
            }
            protected abstract void test();
        }
        
        static class ConcreteSample extends AbstractSample{
            protected void test(){
                System.out.println("B");
            }
        }
        
        public static class Main{
            
            public static void main(String[] args){
                AbstractSample as = new ConcreteSample();
                as.sample();
            }
        }
    }

    static class No10 {
        //サンプルコードなし
        //オーバーライドについてだった。
        //今まで上書きのイメージだったが、正確には再定義である。
        //継承によるインスタンスの構造を理解したのちに改めて学ぶと理解しやすい。
        //overwriteではなくoverrideであることの納得があった。
    }
    
    static class No11 {
        public class Sample {
            protected void hello(){
                System.out.println("hello.");
            }
        }

        //hello()をサブクラスでオーバーライドするならprotectedよりも厳しいアクセス修飾子しかできないはず
        //デフォルトとprivateはできそう
        //できなかった...
        //ルールが逆だった、より厳しいアクセス修飾子にはできず、緩いアクセス修飾子にはできる
        //これはポリモーフィズムを成立させるためのルール
        //「子クラスのオブジェクトは親クラスのオブジェクトとしても扱ってもプログラムは問題なく動作しなければならない」 リスコフの置換原則
        //子クラスで親クラスよりも厳しいアクセス修飾子にしてしまうと、親クラスのオブジェクトとして扱ったときにアクセスできなってしまう可能性がある
        //そのため、オーバーライドするメソッドのアクセス修飾子は親クラスと同じか、より緩いアクセス修飾子にしなければならない

        public class tmp extends Sample {
            @Override
            public void hello(){ //コンパイルエラー
                System.out.println("hekko.");
            }
        }
    }

    static class No12 {

        static class A {
            String val = "A";
            void print(){
                System.out.println(val);
            }
        }
        
        static class B extends A {
            String val = "B";
            // void print(){
            //     System.out.println(val);
            // }
        }

        //スーパークラスとサブクラスで同じ名前のメンバが定義されている場合
        //次のルールに従ってアクセスされる
        //１．フィールドを参照した場合には、変数の型で宣言されたほうを使う
        //２．メソッドを呼び出した場合には、メソッド内の指示に従う
        //面白いなこれ
        //Javaについて基礎部分を学んできたことによって、納得感を感じられるところが特に面白い
        public static class Main {
            public static void main(String[] args){
                A a = new A();
                B b = new B();
                System.out.println(a.val);
                System.out.println(b.val);
                a.print(); 
                b.print();
           }
        }
    }

    //勉強を始める前だったら難問だったものが、今なら解けるようになっている
    static class No13 {
        interface Worker {
            void work();
        }

        static class Employee {
            public void work(){
                System.out.println("work");
            }
        }

        static class Engineer extends Employee implements Worker {}

        public static class Main {
            public static void main(String[] args){
                Worker worker = new Engineer();
                worker.work();
            }
        }
    }

    static class No14{

        public interface Wroker {
            void work();
        }

        static class Employee implements Wroker {
            public void work(){
                System.out.println("work");
            }

            public void report(){
                System.out.println("report");
            }
        }

        static class Engineer extends Employee {
            public void create(){
                System.out.println("create future");
            }
        }

        public static class Main {
            public static void main(String[] args){
                Wroker a = new Engineer();
                Employee b = new Engineer();
                Engineer c = new Engineer();
                a.create();
                b.work();
                b.create();
                c.report();
            }
        }
    }


    //考えたこともないけど正常に動作するように感じられるな...
    //ミスった、インターフェースはインスタンス化できないんだった
    static class No15{
        public interface A {}
        public class B implements A {}
        public class C extends B {}
        public class D{}

        static class Main {
            //ポリモーフィズムがどこまで許されるかの例
            //関係性があるものはポリモーフィズムが許される
            //関係性は、継承関係か、実現関係のどちらかであるかで判断される
            //Dは無関係、Aはインターフェース、BはAの実現クラス、CはBのサブクラス(継承関係)
            public static void main(String[] args) {
                A[] array = {
                    new B(),
                    new C(),
                    new A(),
                    new D()
                };
            }
        }
    }


    //サブクラスをスーパークラス型にキャストするアップキャストと
    //逆にスーパークラスをサブクラス型にキャストするダウンキャストについての問題
    //インスタンスがどのような差分を持っていいようとも、扱っている型に定義されているものしか使えない
    static class No16{
        static class A {}
        static class B extends A{
            void hello(){
                System.out.println("hello");
            }
        }
        public static class Main {
            public static void main(String[] args){
                A a = new B();
                B b = (B)a;
                b.hello();
                //これ自動的にキャストされないのか
                //基本データ型じゃないからかな？
                //ダウンキャストだから、スーパークラスであるAはBを認識できないから自動的にキャストされない
                B b2 = a;
            }
        }
    }
}  

