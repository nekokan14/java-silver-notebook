public class Main {

    public static void main(String[] args){
        int a = 11;
        int b = 0;
        while (b < 5 ){
            if(5 < a){
                System.out.println(b);
            }
            a--;
            b++;
        }

    }

    /*public static void main(String[] args) {
        int num = -10;
        System.out.println(10 * -num);
    }
        */
}


// public class Main {
//     /**
//      * 実験テーマ: （例）前置/後置インクリメントの評価順
//      * 期待結果:   （例）b=?
//      * 観測結果:   （例）出力ログに記録
//      * 落とし穴:   （例）式評価→代入→インクリメントの順
//      */
//     public static void main(String[] args) {
//         // --- 実験コードここから ---
//         int a = 1;
//         int b = a++ + ++a; // テーマの“いやらしい”書き方を入れる
//         System.out.println("a=" + a + ", b=" + b);

//         // 小さく分けて観測（段階出力で評価順を可視化）
//         int x = 1;
//         System.out.println("x(before) = " + x);
//         System.out.println("x++       -> " + (x++));
//         System.out.println("x(after)  = " + x);
//         // --- 実験コードここまで ---
//     }
// }
