package SE11.ch05;

import SE11.ch05.ControlArray;

public class ControlArray {
    public static void main(String[] args){
        System.out.println(ControlArray.class.getPackageName());
        // No01();
        // No02();
        // No03();
        // No04();
        // No05();
        // No06();
        // No07();
        No08();
        // No09();
        // No10();
    }

    static void No01(){
        int[] array = new int[0];
        System.out.println(array); 
        // オブジェクト自身を渡しているのでハッシュコードが表示される
        // 正確にはint型の値の集合を扱うインスタンスの参照が表示される
    }

    static void No02(){
        int [] a;
        int b[];
        int[][] c;
        int d[][];
        //[]はまとめて書く必要はない
        //よって
        int[] e[];    // ここと
        int [][] f[]; // ここでコンパイルエラー /*は起きない*/
        //int[] e[]; は int[][] e; と同じ2次元配列
        //int [][] f[]; は int[][][] f; と同じ3次元配列
        int [] X[][][]; // int ~ ; でパイプみたいに単純にみているからこんな書き方もできるってことか?
    }
    
    //この章のポイント
    //今までしっかり理解していなかったのが恥ずかしい
    static void No03(){
        //つまり、こんな書き方も許される
        //あれ、配列の要素数は指定できなかったけか?
        int[3] a;
        int b[2];
        int[2] c[];
        int d[3][];

        //なるほど int[] array は配列型変数で持っているのは配列の参照先か
        //だから int[3] array は配列の要素数を指定しているように見えるが、実際は配列型変数の宣言としては間違い

        //そして配列とは 配列instance と それが扱う要素の情報 の2つを合わせて配列と呼んでいる
        //だから int[] array = new int[3];
        //は 配列への参照 array = 要素数4まで扱うint型配列インスタンス となる
    }

    static void No04(){
        //コンパイルエラーになるのは a , b , f
        int[] a = new int [2][3]; // 2時次元配列を生成しているのに参照型変数が1次元配列
        int[] b = new int[2.3]; //要素数は/*int型の*/整数で指定する必要がある (整数でもlong型は不可) なぜ?

        // 単純にint型で宣言しているのにlong型を渡すとオーバーーフローするから?
        long hoge = 3L; 
        int fuga = 3;
        int[] il = new int [hoge];
        long[] ll = new long[fuga];

        int c[] = new int [ 2 * 3];
        int x = 2, y = 3;
        int[] d = new int [x * y];
        int[][] e = new int [2][];
        int f[][] = new int [][3]; //2次元配列の1次元目の要素数が不明だからメモリ確保できない

    }

    static void No05(){
        //300が表示される?
        //そうか型として配列の要素数は決まっているが
        //配列の要素自体はオブジェクトなのでnullで初期化されている
        //だから items[0].price でNullPointerExceptionが発生する
        //Item[] items = new Item[3]; で確保されるのは
        //Item型の要素を3つ持つ配列のメモリ領域だけ

        //あいまいだった配列への理解が深まってきたぞ...
        Item[] items = new Item[3];
        Item item1 = new Item();
        int total = 0;
        for (int i =0; i < items.length; i++){
            total += items[i].price;
        }
        System.out.println(total);
    }
    
    static protected class Item {
        String name;
        int price = 100;
        Item(){
            this.name = "item";
            this.price = 100;
        }
    }

    static void No06(){
        String[] array = {"A", "B", "C", "D"};
        array[0] = null;
        for (String str : array){
            System.out.print(str); //nullも表示されてた気がする
            //BCDかな //あ、nullは文字列として表示されてたか
            //なるほど nullを参照しているから null と表示されるのか
            //"null"でも結果が同じだから悪さできそう...
        }
        System.out.println();
    }

    static void No07(){
        //コンパイルエラーにならないのは b , c , d]
        int [] a = new int [2]{2,3}; //初期化子{}を使う際は自動的に要素数が決まるので要素数を指定できない
        //int b[][] = {};
        //int[][] c = new int[][]{};
        //int [] d;
        //d = new int[]{2,3};
        int e[];
        e = {2,3}; //初期化子{}は自動的に配列の次元数を計算するため、使う際は宣言と同時に行う必要がある
        //例
        e = new int[]{2,3}; //これはOK
    }    

    static void No08(){
        // nullを加算して実行時エラーになる？
        String [][] array = {{"A","B"},null,{"C","D","E"}};
        int total = 0;
        for (String[] foo : array){
            total += foo.length; //ここでNPE
            //tips: 配列の要素数を数えるためlengthは実装的にはメソッドに近い
        }
        System.out.println(total);
    }
    
    //なるほど少しわかってきたかもしれない。javaではクラスが大きいと抽象度が高い、クラスがより小さいと具体度が高まり、抽象的でなくなる。
    //そして、何かを出力するなどするときは具体的でないと表現ができない。
    static void No09(){
        //A[] array の部分でコンパイルエラー
        //Aは implemets されているが、extendsされていない

        //BはAの実装クラスなのでB[] array = {new B(), null, new B()}; はOK
        //CはBを継承しているので C[] array = {new C(), null, new C()}; もOK
        //DはCを継承しているので D[] array = {new D(), null, new D()}; もOK
        //よって A <- B <- C <- D という継承関係において
        //A[] array = {new C(), null, new D()}; もOK
        A[]array = {new C(),null,new D()};
        Object[] objArray = array;
    }

    static void demonstratePolymorphism() {
    // これが成り立つ理由
    A[] array = {new C(), null, new D()};
    
    // 理由1: CはBを継承 → BはAを実装 → CはAの実装クラス
    A ref1 = new C();  // OK: CはAを実装している
    
    // 理由2: DはCを継承 → CはBを継承 → BはAを実装 → DはAの実装クラス  
    A ref2 = new D();  // OK: DはAを実装している（間接的に）
    
    // 理由3: 配列も同じポリモーフィズムが適用される
    A[] arrayOfA = new C[3];  // OK: C[]はA[]の派生型
}

    // Object (すべてのクラスの親)
    // ↑
    // A (interface) ←────────── implements
    // ↑                              ↑
    // B (abstract class) ────────────┘
    // ↑
    // C (concrete class) ←── extends
    // ↑
    // D (concrete class) ←── extends

    // インターフェース（契約の定義）
    interface A {
        // メソッドのシグネチャのみ定義
    }

    // 抽象クラス（部分的な実装）
    static abstract class B implements A {
        // インターフェースAを「実装」する
        // 具象メソッドも抽象メソッドも持てる
    }

    // 具象クラス（完全な実装）
    static class C extends B {
        // 抽象クラスBを「継承」する
        // 全ての抽象メソッドを実装する必要がある
    }

    // 継承チェーン
    static class D extends C {
        // クラスCを「継承」する
    }
    
    static void No10(){

    }

}
