package SE11.ch10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExceptionHandling {

    static class No01 {
        public static void main(String[] args) {
            try {
                int[] array = {};
                array[0] = 10;
                System.out.println("finish");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }
    }

    static class No02 {
        public static void main(String[] args) {
            // A,Cが出力される
            try {
                if (args.length == 0) {
                    System.out.println("A");
                }
            } catch (NullPointerException npe) {
                System.out.println("B");
            } finally {
                System.out.println("C");
            }
        }
    }

    static class No03 {
        public static class SampleException extends Exception {
        }

        public static class SubSampleException extends SampleException {
        }

        public static class Main {
            public static void main(String[] args) {
                try {
                    sample();
                    sub();
                } catch (SampleException e) {
                    System.out.println("A");
                } catch (SubSampleException e) {
                    // 到達不能コードのため、コンパイルエラー
                    // 結構かしこいな
                    System.out.println("B");
                }
                // ExceptionやRuntimeExceptionは
                // 例外を表すことが目的のクラス群なので、ポリモーフィズムが働く
            }

            private static void sample() throws SampleException {
                throw new SampleException();
            }

            private static void sub() throws SubSampleException {
                throw new SubSampleException();
            }
        }
    }

    static class No04 {
        public static void main(String[] args){
            //構文エラーによるコンパイルエラー
            try{
                Object obj = null;
                System.out.println(obj.toString());
                System.out.println("A");
            }finally{
                System.out.println("B");
            }catch(NullPointerException npe){
                System.out.println("C");
            }
        }
    }

    static class No05 {
        static class Main {
            public static void main(String[] args) {
                System.out.println(test(null));
            }
            // あれnull,B,Cじゃないの？
            // toString()はnullに対して実行できないのか
            // そして、catchの前にfinallyが実行されるのでB,Aの順になるのか

            private static String test(Object obj) {
                try {
                    System.out.println(obj.toString());
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                    return "A";
                } finally {
                    System.out.println("B");
                }
                return "C";
            }
        }
    }

    static class No06 {
        public static void main(String[] args) {
            int result = sample();
            System.out.println(result);
        }

        // finallyで先にreturnに到達するので20が出力される
        // ↑は誤り。catchとfinallyの両方のreturnは実行されている
        // １．catchでreturnに到達し、戻り値用の領域が確保される
        // ２．finally節内でreturnに到達し、戻り値用の領域が再度確保される
        // そのため20が出力される
        private static int sample() {
            try {
                throw new RuntimeException();
            } catch (RuntimeException re) {
                return 10;
            } finally {
                return 20;
            }
        }
    }

    static class No07 {

        // return時には戻り値用の領域が確保されている
        // finally節が実行された後に、その戻り値用の領域に
        // finally節内で変更された値がセットされる
        // プリミティブ型のため、sample()内のvalと戻り値用の領域は別物
        // そのため、10が出力される

        // returnが参照型の場合は、参照先のオブジェクトがfinally節内で変更されると
        // 戻り値用の領域にセットされるオブジェクトも変更される

        static class Main {
            public static void main(String[] args) {
                int result = sample();
                System.out.println(result);
            }
            // 10が出力される

            private static int sample() {
                int val = 0;
                try {
                    String[] array = { "A", "B", "C" };
                    System.out.println(array[3]);
                } catch (RuntimeException re) {
                    val = 10;
                    // ここでreturnに到達し、戻り値用の領域が確保される
                    // val=10が戻り値用の領域にセットされる
                    return val;
                } finally {
                    val += 10;
                    // 10+10でvalhは20になるが
                    // 戻り値用の領域には影響しない
                    System.out.println("finally val=" + val);
                }
                return val;
            }
        }
    }

    static class No08 {
        // 構文エラーによるコンパイルエラー
        public static void main (String[] args){
            try{
                System.out.println("A");
            }finally{
                System.out.println("B");
            }finally{
                System.out.println("C");
            }
        }
    }

    static class TmpTest {
        public static void main(String[] args) throws Exception {
            System.out.println("A");
            System.out.println("finally");
            throw new RuntimeException();

            // これはコンパイルエラーとなる
            // try{
            // System.out.println("A");
            // throw new RuntimeException();
            // }

            // try{
            // System.out.println("A");
            // throw new RuntimeException();
            // }finally{
            // System.out.println("finally");
            // }
        }
    }

    static class No09 {
        // 凄い構造だな
        // こんな気持ち悪いコード書いたことないや
        // D,E,Gか?
        public static void main(String[] args) {
            try {
                try {
                    String[] array = { "A", "B", "C" };
                    System.err.println(array[3]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("D");
                } finally {
                    System.out.println("E");
                }
            } catch (ArrayIndexOutOfBoundsException parentE) {
                System.out.println("F");
            } finally {
                System.out.println("G");
            }
        }
        // あってた
    }

    static class No10 {
        public static void main(String[] args) {
            // この（）はtry-with-resources構文という
            // リソースを自動的にクローズしてくれる構文
            // 久しぶりに見た

            // この書き方で例外が発生した場合は次の順序で処理される
            // １．リソースの解放
            // ２．catch節の処理
            // ３．finally節の処理

            // ん？リソースの解放に失敗した場合は？
            try (FileInputStream is = new FileInputStream("sample.txt")) {
                throw new FileNotFoundException();
            } catch (Exception e) {
                System.out.println("A");
            } finally {
                // リソースが自動的にクローズされるため、不要
                // 変数としてisも使えない
                if (is != null) {
                    is.close();
                }
                System.out.println("B");
            }
        }
    }

    static class No10_explore {
        // 通常のtry-catch-finally文の例外時の処理の流れ
        // 1.try文内でFileNotFoundExceptionが発生
        // 2.リソース（FileInputStream）の自動クローズ実行
        // 3.catch文の実行（"A"を出力）
        // 4.finally文の実行（"B"を出力）

        public static void main(String[] args) {
            try (MyResource resource = new MyResource()) {
                throw new RuntimeException("メイン例外"); // メイン例外
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("キャッチした例外: " + e.getMessage());
                // 抑制された例外の確認
                Throwable[] suppressed = e.getSuppressed();
                for (Throwable t : suppressed) {
                    System.out.println("抑制された例外: " + t.getMessage());
                }
            }
        }

        static class MyResource implements AutoCloseable {
            @Override
            public void close() throws Exception {
                throw new IOException("クローズ時の例外"); // リソース解放時の例外
            }
        }

        /**
         * try-with-resources構文は**Java言語仕様（JLS §14.20.3）**で明確に定義されており
         * コンパイラが自動的に適切なコードを生成することで保証されている
         * 
         * try-with-resources構文はJVM仕様レベルで保証されているが、銀の弾ではない
         * デッドロックやリソース競合など、リソース管理に関する問題は依然として発生する可能性がある
         * 
         * try-with-resources構文は「ベストエフォート」:
         * JVMレベルではclose()の呼び出しは確実に保証される
         * 外部システムとの相互作用では完全な保証はできない
         * デッドロックや無限待機は発生する可能性がある
         * 追加の安全策（タイムアウト、シャットダウンフックなど）が必要
         */
    }

    static class No11 {

        static class SampleException extends Exception {
        }

        static class TestException extends RuntimeException {
        }

        // public static void hello(String name) throws SampleException, TestException{
        // or
        public static void hello(String name) throws SampleException {
            // TestExceptionはRuntimeExceptionのサブクラスなので
            // throws節に記述する必要はない
            // そういえば、RuntimeException系列はコンパイラが例外処理を記載しているかをチェックしないんだった

            if (name == null) {
                throw new SampleException();
            }
            if ("".equals(name)) {
                throw new TestException();
            }
            // do something
        }
    }

    static class No12 {
        // サンプルコードなし
        // エラー(Error)は例外処理を求められないが(義務ではない)、例外処理を書くことも出来る
        // それは、Errorが発生する状況はプログラム側で対処できないような状況のため

    }

    // NPEが発生しそう
    // ArrayIndexOutOfBoundsExceptionか
    static class No13 {
        // 実行時にJVMが自動的に生成するargs配列はnullではなく、長さ0の配列
        // 引数が渡されなかった場合でもargsはnullにはならない

        // プログラムの安全せいを確保するためにJVM側で配慮されて空配列が生成される
        public static void main(String[] args) {
            System.out.println(args[0].length());
        }

        /**
         * JVMの配慮の目的は、プログラムが動作するための最低限の基盤を提供すること：
         * NPE防止: 配列オブジェクトの存在を保証 → 基本操作が可能
         * API一貫性: .lengthや拡張for文が常に使用可能
         * プログラマ支援: 引数チェックのロジックが書ける
         * 
         * ArrayIndexOutOfBoundsExceptionを許容する理由：
         * 責任分離: JVMは基盤提供、プログラマはロジック実装
         * 診断しやすさ: 問題の原因と解決方法が明確
         * 学習効果: 適切な範囲チェックの重要性を教える
         */
    }


    //IndexOutOfBoundsExceptionかListIndexOutOfBoundsExceptionかな
    static class No14{
        public static void main(String[] args){
            List<String> list = new ArrayList<>();
            list.get(0);
            //IndexOutOfBoundsExceptionだった
            //聞き覚えがないと思ったらListIndexOutOfBoundsExceptionなんてクラスはJavaには存在しないのか
            //随分と丁寧な名前だと思ったら
        }
    }


    static class No15{
        //実行時のキャストでエラーが発生する
        //Aはobjのサブクラスだが、bはAのサブクラスでもスーパークラスでもないため
        //ClassCastExceptionが発生する
        //なお、キャストを明示的に行う場合はプログラマーの責任となるため、コンパイルエラーにはならない
        public  static void main(String[] args){
            A a = new A(10);
            B b = new B(10);
            // System.out.println(a.equals(b));
            System.out.println(a.equals_fixed_modern(b));
        }
        static class A{
            private int num;
            public A(int num){
                this.num = num;
            }
            public boolean equals(Object obj){
                A a = (A) obj;
                return this.num == a.num;
            }
            //Instanceofで型チェックをするように修正
            public boolean equals_fixed(Object obj){
                if (!(obj instanceof A)) {
                    return false;
                }
                A a = (A) obj;
                return this.num == a.num;
            }

            //Java14以降ならパターンマッチングも使える
            public boolean equals_fixed_modern(Object obj){
                if (!(obj instanceof A a)) { // ここで型チェックとキャストと代入を同時に行う
                    return false;
                }
                return this.num == a.num;
            }
        }
        static class B{
            private int num;
            public B(int num){
                this.num = num;
            }
            public boolean equals(Object obj){
                B b = (B) obj;
                return this.num == b.num;
            }
        }
    }

    //"null"と表示される

    //これNPEなのか
    //Stringのequalsメソッドへの理解が薄かった...
    static class No16{
        public static void main(String[] args){
            // String str = null;
            // if(str.equals("")){
            //     System.out.println("blank");
            // }else{
            //     System.out.println("null");
            // }
            Demo.demonstrateNPEFlow();
        }

        static class Demo{
        // 実際の実行手順を可視化
        // nullに対してメソッドを呼び出そうとしているのをイメージするといいか?
            static void demonstrateNPEFlow() {
                System.out.println("=== No16の処理フロー ===");
                
                String str = null;
                System.out.println("1. str = " + str);
                
                try {
                    System.out.println("2. str.equals(\"\")を呼び出し試行...");
                    
                    // ここで実際に何が起こるか
                    // JVMの内部処理：
                    // if (str == null) {
                    //     throw new NullPointerException();
                    // }
                    // str.equals(""); ← ここには到達しない
                    
                    boolean result = str.equals("");
                    System.out.println("3. 結果: " + result); // この行は実行されない
                    
                } catch (NullPointerException e) {
                    System.out.println("3. NullPointerException発生！");
                    System.out.println("4. equals()メソッド内部には到達していない");
                }
            }
        }
    }

    //StackOverflowErrorかな
    static class No17{
        public static void main(String[] args){
            // 再帰呼び出しの例
            main(args);
            //スタック領域が溢れて(不足)、JVM検知することによって
            //StackOverflowErrorが発生する
        }
    }

}
