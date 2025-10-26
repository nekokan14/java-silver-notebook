package SE11.ch08;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

import SE11.ch07.OopConcept;
// import java.lang.Thread;

public class FunctionalinterfaceAndLambda {
    public static void main(String[] args) {
                System.out.println(OopConcept.class.getName());

    }

    static class No01{
        interface Algorithm{
            void perform(String name);
            // void NotOnlyOneMethod(); //なるほど関数型インタフェースは一つの抽象メソッドしか持てないのか
            //関数型と聞くと難しく感じるが、要は単一存在を保証するための仕組みか
        }   

        static class Service{
            private Algorithm logic;
            public void setLogic(Algorithm logic){
                this.logic = logic;
            }
            public void doProgress(String name){
                System.out.println("start");
                this.logic.perform(name);
                System.out.println("end");
            }
        }
        public static class Main{
            public static void main(String[] args){
                //関数型インタフェースの抽象メソッドperformの実装をラムダ式で記述している
                //tsのアロー関数に似ている
                Algorithm algorithm = (name) ->{
                    System.out.println("hello." + name);
                };
                Service s = new Service();
                s.setLogic(algorithm);
                s.doProgress("Lambda");
            }
        }
    }

    static class No02{
        public static class Main{
            public static void main(String[] args){
                //insert code here
                // Function f = (name) -> return "hello, "+ name; //{}ブロックを省略した場合、returnは記述できない
                Function f = (name) -> "hello, "+ name;
                System.out.println(f.test("Lambda"));
            }
            //{}ブロックを省略した場合にreturnを記述できない理由
            //ラムダをシンプルでクリーンに保つため
            //ラムダは->の右側を式(１行)と文(複数行)で区別している
            //式の場合、式の評価結果が自動的に返される。
            //文の場合、様々な手続きを踏み、それらは{}ブロック形式で記述される。
            //この２つの形式を明確に区別するために、式形式ではreturnを記述できないようにJavaの原則としてルール付けられた

            // "hello, " + name は、評価すると文字列という値を生成する**「式」**
            // return "hello, " + name; は、値を返すという処理を行う**「文」**
            // このようにreturnは文の一部であり、式の一部ではないため、式形式のラムダでは使用できない

            private static  interface Function{
                String test(String name);
            }
        }
    }
    
    //"B"が出力されるか?
    static class No03{
        public static class Main{
            public static void main(String[] args){
                String val = "A";
                //抽象メソッドを実装しているので別スコープだと考えたが
                //ラムダ式はそれを囲むブロックと同じスコープを共有する
                //そのためval = main.valと同じ変数を参照してしまう
                //スコープが同じ変数をメソッドの引数として受け取ることはできないためコンパイルエラーになる
                Function f = (val) -> {
                    System.out.println(val);
                };
                f.test("B");
            }
            interface Function{
                void test(String val);
            }
        }
    }

    //Threadは碌に使ったことが無いからよくわからん
    //ぱっと見0248と出力されそうに見えるが...
    static class No04{
        public static void main(String[] args){
            int cnt = 0;
            //ラムダ式外で宣言されたローカル変数にラムダ式内からアクセスするには
            //実質的にfinalである必要がある
            //これはラムダ式が宣言したタイミングで実行されるわけではないため。
            //もしアクセスする外の変数が宣言後に変更されてしまうとその式は意図しない動作をする可能性があるから
            //例：ラムダ式が実行されるタイミングでは、外側のメソッドのスコープが終了している可能性がある
            Runnable r = () -> {
                // for (cnt = 0; cnt < 10; cnt++){
                //     System.out.print(cnt++);
                // }
                for (int i = 0; i < 10; i++){
                    System.out.print(i++);
                }
            };
            new Thread(r).start();
        }
    }

    static class No05{
        public static class Main{
            public static void main(String[] args){
                No05 outer = new No05();
                List<No05.Sample> list = Arrays.asList(
                                    outer.new Sample(10),
                                    outer.new Sample(20),
                                    outer.new Sample(30));
                
                Predicate<Sample> x = s -> list.contains(s);
                Function<Sample,Boolean> y = s -> list.contains(s);
                if(x.test(outer.new Sample(20)) && y.apply(outer.new Sample(20))){
                    System.out.println("ok");
                }
                //なるほど、関数型インタフェースについて理解が進んできたぞ
                //ジェネリック型による契約も絡んでくるから少し複雑に感じるが
                //理解するとシンプルなコードに見えるから面白い
            }

        }
        class Sample{
            private int num;
            public Sample(int num){
                this.num = num;
            }
            public boolean equals(Object obj){
                if(obj instanceof Sample == false){
                    return false;
                }
                if(this.num == ((Sample) obj).num){
                    return true;
                }
                return false;
            }
        }
    }

    static class No06{
        //サンプルコードなし
        //Supplierの使用例
        public static class Main{
            public static void main(String[] args){
                Supplier<String> msg = () -> "Hello, Lambda";
                System.out.println(msg.get());
            }
        }   
    }

    static class No07{
        public static class Main{
            public static void main(String[] args){
                Function <String,Integer> f = str -> Integer.parseInt(str);
                System.out.println(f.apply("100")*2); 
            }
        }
    }

    static class No08{
        //サンプルコードなし
        //Consumerの使用例
        public static class Main{
            public static void main(String[] args){
                Consumer<String> outputStr = str -> System.out.println("Hello."+str);
                outputStr.accept("Java");
            }
        }
    }
}
