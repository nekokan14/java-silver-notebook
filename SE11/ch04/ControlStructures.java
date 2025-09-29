package SE11.ch04;

//第４章-制御構造
public class ControlStructures {
    public static void main(String[] args) {
        System.out.println(ControlStructures.class.getPackageName());
        // No1();
        // No2();
        // No3();
        // No4();
        // No5();
        // No6();
        // No7();
        // No8.No8();
        // No9();
        // No10();
        // No11();
        // No12();
        // No13.No13();
        // No14();
        // No15();
        // No16();
        No17();
        No17MoreReadable();
    }

    static void No1() {
        int a = 11;
        int b = 0;
        while (b < 5) {
            if (5 < a) {
                System.out.println(b);
            }
            a--;
            b++;
        }
    }

    static void No2() {
        int a = 0;
        do {
            System.out.println(a++);
        } while (a < 5);
    }

    static void No3(){
        int a = 0;
        while (a < 5) //{
            do //{ ブロック記述がないため１文(;まで)しか書けない + ネストされた無限ループ
                a++;
                System.out.println(a);
            /** }*/while(true);
        // }
    }

    static void No4(){
        for (int i = 0, long j = 2; i < 5; i++) { // 初期化式で複数の変数を宣言できるが、型は同じでなければならない
            System.out.println(i * j);
        }
    }

    static void No5() {
        int a = 1;
        for (int b = 2, total = 0; b <= 5; b++) {
            total = a * b;
        }
        System.out.println(total);// totalはfor分の初期化式で宣言されているため、for文の外では使用できない
    }

    static void No6() {
        for (int i = 0; i == 0; i++) {
            System.out.println(i);
        }
    }

    static void No7(){
        for (int i = 0, j = 0; i < 3 , j < 5; i++){ // 条件文は１つのみ記載できる。複数条件にしたいなら論理演算子を使用する
            System.out.println(i++);
            j+= i;
        }
    }

    private class No8 {
        static void No8() {
            for (int i = 0; i < 3; i++, period()) {
                System.out.print(i);
            }
            System.out.println();
        }

        private static void period() {
            System.out.print(",");
        }
    }

    // やりたいことはわかるが、面倒だ...
    static void No9() {
        int array[][] = new int[][] { { 1, 2 }, { 2, 3, 4 } };
        int total = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array[i].length; j++) {
                total += array[i][j];
            }
        }
        System.out.println(total);
    }

    static void No10() {
        // 無限ループ
        // for ( int i = 0; true ; i++){
        // for ( int i = 0; ; i++){
        for (int i = 0; i < 5;) {
            System.out.println(i);
        }
    }

    static void No11() {
        String[][] array = { { "A", "B", "C" } };
        for (Object obj : array) {
            // for (Object obj : array[0]){ //分かりやすい結果になるからこっちのほうが良いのでは?
            System.out.println(obj);
        }
    }

    static void No12() {
        // こんなバカなコード書くのは最初の頃ぶりだな...
        String[] array = { "A", "B", "C" };
        for (String str : array) {
            str = "D";
        }
        for (String str : array) {
            System.out.print(str);
        }
        System.out.println();
    }

    static class No13 {
        static void No13() {
            System.out.println("----Sample----");
            compatibleSample();
            System.out.println("----Answer----");
            compatibleAnswer();
        }

        static void compatibleSample() {
            int num = 10;
            do {
                num++;
            } while (++num < 10);
            System.out.println(num);
        }

        static void compatibleAnswer() {
            int num = 10;
            while (num++ <= 10) { // 判定後にインクリメントして、次のループでもう一度判定するので、合計3回インクリメントされる
                num++;
            }
            System.out.println(num);
        }
    }

    static void No14() {
        String[] array = { "A", "B" };
        for (String a : array) {
            for (String b : array) {
                if ("B".equals(b))
                    break;
                System.out.print(b);
            }
        }
    }

    static void No15() {
        int[] array = { 1, 2, 3, 4, 5 };
        int total = 0;
        for (int i : array) {
            if (i % 2 == 0) // { 癖でブロックをつけてしまって問題とは関係ない部分で詰まってしまった XD
                continue;
            total += i;
        }
        System.out.println(total);
    }

    static void No16() {
        // ラベルなんて使ったことなかったなあ
        // if,switch && 式 && 代入 && reurn && tryブロック すべてで使えるらしい
        sample: for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (3 < j) {
                    break sample;
                }
            }
            System.out.println("ラベルでbreakするのでここには来ない");
        }
    }

    //こんなコード書いてる奴いたら殴りたくなるな
    //ラベルを使うくらい複雑な処理をするならメソッドに分けるべき
    static void No17() {
        int total = 0;
        a: for (int i = 0; i < 5; i++) {
            b: for (int j = 0; j < 5; j++) {
                if (i % 2 == 0) continue a;
                if (3 < j) break b;
                total += j;
            }
        }
        System.out.println(total);
    }

    //上のコードをメソッドに分けてみた
    //書き方が引っ張られて若干ハック的になっているが、まあラベルよりはマシかな
    static void No17MoreReadable() {
        int total = 0;
        for (int i = 0; i < 5; i++){
            total += (i % 2 == 0) ? 0 /*like a continue */ : sumJ();
            /* ハック的でない書き方 
             if (i % 2 != 0) {
                total += sumJ();
             }
            */
        }
        System.out.println(total);
    }

    static private int sumJ(){        
        int subtotal = 0;
        for (int j = 0; j < 5; j++){
            if (3 < j) {
                break;
            }
            subtotal += j;
        }
        return subtotal;
    }

}