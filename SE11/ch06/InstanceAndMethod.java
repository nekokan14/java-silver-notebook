package SE11.ch06;
import javax.swing.text.StyledEditorKit;

import SE11.ch06.Item.*;

public class InstanceAndMethod {
    public static void main(String[] args) {
        // No01();
        // No02();
        // No03();
        // No04();
        // No05();
        // No06();
        // No07();
        // No08();
        // No09();
        // No10();

        // No11();
        No12();
        // No13();
        // No14();
        // No15();
        // No16();
        // No17();
        // No18();
        // No19();
        // No20();

        // No21();
        // No22();
        // No23();
        // No24();
        // No25();
        // No26();
        // No27();
        // No28();
        // No29();
        // No30();
    }
    static void No01(){
        Item.No1Item a = new Item.No1Item();
        Item.No1Item b = new Item.No1Item();
        b.setNum(20);
        //当然10が出力される
        System.out.println(a.getNum());
    }   

    static void No02(){
        Item.No2Item a = new Item.No2Item();
        Item.No2Item b = new Item.No2Item();
        a.name = "apple";
        b.price = 100;
        a.price = 200;
        b.name = "banana";
        a = b;
        a.printInfo(); //banana 100が出る
    }
    
    static void No03(){
        //ObjectでNULLを出したいなら 1と3じゃないか?
        // nullはNULLとしてprintされない
        Object obj1 = null;
        Object obj2 = false;
        // Object obj3 = NULL; //""で囲ってなかったわ
        Object obj4 = "";
        System.out.println("obj1: " + obj1);
        System.out.println("obj2: " + obj2);
        // System.out.println("obj3: " + obj3);
        System.out.println("obj4: " + obj4);
    }

    //勉強になるな
    static void No04(){
        //ガベージコレクションはよく知らない!
        //cが対象になりそうだが...
        Object a = new Object();
        Object b = new Object();
        Object c = a;
        a = null; //aのnullはcが参照しているため、GCの対象にはならない。
        b = null; //objのnullはどこからも参照されないことを意味するため、このタイミングでGCの対象になる。
        //more code...    
    }

    //static fieldの扱いを通してstatic領域とheap領域の違いを理解する
    static void No05(){
        Item.No5Sample.num = 10;
        Item.No5Sample s = new Item.No5Sample();
        Item.No5Sample s2 = new Item.No5Sample();
        s.num += 10;
        s2.num += 30;
        //Sampleクラスのnum fieldはint型なので同じ場所に値が格納される。
        //Integer ならば、s.num += 10;で新しいIntegerオブジェクトが生成されるため、numは変化しない。
        //↑は誤り

        //static fieldはクラスに属するため、インスタンスを生成しなくてもアクセスできる。
        //JVMはコンパイル時にクラスをロードする、この時staticな部分とそれ以外に分離される。
        //staticな部分はstatic領域に、その他の部分はheap領域のメモリに格納される。
        //インスタンスが生成されるときはheap領域のクラス定義に従って生成される。
        //static fieldはインスタンスを生成しなくてもアクセスできる。
        //sやs2ではインスタンスを生成しているが、Sampleクラスはstatic fieldしかないため
        //Sample.numを参照するための"空っぽな"インスタンスを生成しているに過ぎない。
        System.out.println(Item.No5Sample.num); // 50が出力される
    }

    static void No06(){
        //サンプルコードなし
        //staticなメソッドからstaticでないメソッドを呼び出すことは可能に思えるが
        //static領域のメソッドはインスタンスを生成する必要がないため、heap領域のメソッドを呼び出す行為は
        //存在しないものを呼び出す可能性があるため、エラー(禁止される)になる。
        //このことから、staticなメソッドからstaticでないメンバ(field, method)を参照することが禁止されコンパイルエラーとなる
    }

    static void No07(){
        Item item = new Item();
        Item.No7Sample sample = item.new No7Sample();
        sample.hello();
    }

    static void No08(){
        Item item = new Item();
        Item.No8Sample s = item.new No8Sample();
        System.out.println(s.add(10));//そもそも引数が足りないからコンパイルエラー
    }

    static void No09(){
        int a = 1;
        int b = 2;
        System.out.println(a);
        System.out.println(b+2);
        int c = b;
    }

    static void No10(){
        //サンプルコードなし
        class LocalSample{
            void sample(){}
        }

        LocalSample ls = new LocalSample();
    }

    static void No11(){
        //setValueは戻り値がvoidなのでStaring valでコンパイルエラー
        Item item = new Item();
        Item.No11Sample s = item.new No11Sample();
        Strin val = s.setValue("hello");
        s.getValue();
        Sytsetm.out.println(val);
    }

    static void No12(){
        Item.No12Sample s = new Item.No12Sample();
        float folatResult = s.divide(10, 2);
        double doubleResult = s.divide(10, 2);
        System.out.println(folatResult);
    }

    static void No13(){
        //s.method()の引数が足りない
        Item.No13Sample s = new Item.No13Sample();
        int result = s.method(10);
        System.out.println(result);
    }

    static void No14(){
        //サンプルコードなし
        class LocalSample{
            void methodA(void){}
            void methodB(int values...){}
            //あれレスト構文って使えないの?
            //Javaではレスト構文は可変長引数と(varargs)と呼ばれる。
            void methodC(int... values,String name){}
            void methodD(int... a,int... b){}
            //そうか、最後にしか使えないんだった
        }
    }

    public static class No15{
        public static void method(int num){
            if (num < 0) return;
            System.out.println("A");
            return;
            //わかりやすく到達不可能なコード
            System.out.println("B");
        }
    }

}
