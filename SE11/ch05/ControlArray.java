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
        // No08();
        // No09();
        // No10();
        CloneDemo();
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
        int[][] arrayA = { {1,2},{1,2},{1,2,3}} ;
        int [][] arrayB = arrayA.clone(); // 使ったことはないけど、おそらく参照先をコピーしている
        //cloneメソッドはインスタンスを複製するらしい。
        //複製方法が気になるので調べてみる
        /* どうやらclone()はshallow copyらしい
         *   clone()によるshallow copy では、下記の手順でコピーを実行する
         *   １．入れ物として新しいインスタンスを作成して
         *   ２．コピー元の要素の参照先を値として保持する
         *   ３．インスタンスへの参照先を変数に渡す
         *   インスタンスは別モノでも、参照先の値は一緒なので
         *   コピー元、先のどちらかが変更されると両方のインスタンス内の値(要素)が変更される。

         *  例に上がっている実装方法によるdeep cloneでは、
         *   参照先を保持するインスタンスからではなく、
         *  #1{値(オブジェクト)そのものから新しく作成しているため}コピー元との直接の参照はない。
         *  #1 より正確には、参照先のオブジェクトの内容を元に新しいオブジェクトを作成している
         */
        
         //つまりclone()は新しいインスタンスだけど、参照先をコピーしているので、副作用が発生する可能性がある
         //だから、clone()を使うときは注意が必要。というか、安易に使うようなものではない
        int total = 0;
        for (int[] tmp :  arrayB) {
            for (int val : tmp) {
                total += val;
            }
        }
        System.out.println(total); //12が表示される気がするが...
    }

    private static void CloneDemo() {
            System.out.println("=== Shallow Copy vs Deep Copy ===\n");
            demonstratePrimitiveArrayClone();
            demonstrateObjectArrayClone();
            demonstrateShallowCopyProblem();
            demonstrateDeepCopySolution();
    }

    // 1. プリミティブ型配列のclone（問題なし）
    static void demonstratePrimitiveArrayClone() {
        System.out.println("--- 1. プリミティブ型配列のclone ---");
        
        int[] original = {1, 2, 3};
        int[] cloned = original.clone();  // shallow copy
        
        System.out.println("元の配列: " + java.util.Arrays.toString(original));
        System.out.println("クローン: " + java.util.Arrays.toString(cloned));
        
        // クローンを変更
        cloned[0] = 999;
        
        System.out.println("\ncloned[0] = 999 に変更後:");
        System.out.println("元の配列: " + java.util.Arrays.toString(original));
        System.out.println("クローン: " + java.util.Arrays.toString(cloned));
        System.out.println("→ プリミティブ型は値がコピーされるので影響なし\n");
    }

    // 2. オブジェクト配列のclone（shallow copyの挙動）
    static void demonstrateObjectArrayClone() {
        System.out.println("--- 2. オブジェクト配列のclone（Shallow Copy） ---");
        
        Person[] original = {
            new Person("太郎", 25),
            new Person("花子", 30)
        };
        
        Person[] cloned = original.clone();  // shallow copy
        
        System.out.println("元の配列:");
        printPersonArray(original);
        System.out.println("\nクローン:");
        printPersonArray(cloned);
        
        // クローンの配列の要素を変更
        System.out.println("\n--- cloned[0]の名前を変更 ---");
        cloned[0].name = "次郎";
        
        System.out.println("元の配列:");
        printPersonArray(original);
        System.out.println("\nクローン:");
        printPersonArray(cloned);
        System.out.println("→ 元の配列も変更されてしまった！（shallow copyの問題）\n");
    }

    // 3. Shallow Copyの問題を図解
    static void demonstrateShallowCopyProblem() {
        System.out.println("--- 3. Shallow Copyの仕組み ---");
        
        Person[] original = {new Person("太郎", 25)};
        Person[] cloned = original.clone();
        
        System.out.println("メモリ構造:");
        System.out.println("original配列 → [参照A]");
        System.out.println("cloned配列   → [参照A（同じオブジェクトを参照！）]");
        System.out.println("               ↓");
        System.out.println("         Person(太郎, 25) ← 両方がこれを指している");
        
        System.out.println("\noriginal[0] == cloned[0]: " + (original[0] == cloned[0]));
        System.out.println("→ 同じオブジェクトを参照しているのでtrue\n");
    }

    // 4. Deep Copyの実装例
    static void demonstrateDeepCopySolution() {
        System.out.println("--- 4. Deep Copyの実装 ---");
        
        Person[] original = {
            new Person("太郎", 25),
            new Person("花子", 30)
        };
        
        // Deep Copy: 配列だけでなく中身のオブジェクトもコピー
        Person[] deepCloned = new Person[original.length];
        for (int i = 0; i < original.length; i++) {
            deepCloned[i] = new Person(original[i].name, original[i].age);
        }
        
        System.out.println("元の配列:");
        printPersonArray(original);
        System.out.println("\nDeep Clone:");
        printPersonArray(deepCloned);
        
        // Deep Cloneの要素を変更
        System.out.println("\n--- deepCloned[0]の名前を変更 ---");
        deepCloned[0].name = "次郎";
        
        System.out.println("元の配列:");
        printPersonArray(original);
        System.out.println("\nDeep Clone:");
        printPersonArray(deepCloned);
        System.out.println("→ 元の配列は影響を受けない！（Deep Copyの利点）\n");
        
        System.out.println("original[0] == deepCloned[0]: " + (original[0] == deepCloned[0]));
        System.out.println("→ 別のオブジェクトなのでfalse");
    }
        // ヘルパーメソッド
    static void printPersonArray(Person[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("  [" + i + "] " + array[i]);
        }
    }

    // Personクラス
    static class Person {
        String name;
        int age;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return "Person(名前=" + name + ", 年齢=" + age + ")";
        }
    }
}