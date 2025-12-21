package SE11.ch10;


public class ExceptionHandling {
    
    static class No01{
        public static void main (String[] args ){
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

    static class No02{
        public static void main (String[] args){
            //A,Cが出力される
            try {
                if(args.length == 0){
                    System.out.println("A");
                }
            }catch(NullPointerException npe){
                System.out.println("B");
            }finally{
                System.out.println("C");
            }
        }
    }

    static class No03{
        public static class SampleException extends Exception{}
        public static class SubSampleException extends SampleException{}
        public static class Main{
            public static void main (String[] args){
                try {
                    sample();
                    sub();
                } catch (SampleException e) {
                    System.out.println("A");
                } catch (SubSampleException e){
                    //到達不能コードのため、コンパイルエラー
                    //結構かしこいな
                    System.out.println("B");
                }
                //ExceptionやRuntimeExceptionは
                //例外を表すことが目的のクラス群なので、ポリモーフィズムが働く
            }
            private static void sample() throws SampleException{
                throw new SampleException();
            }
            private static void sub() throws SubSampleException{
                throw new SubSampleException();
            }
        }
    }

    static class No04{
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

    static class No05{
        static class Main{
            public static void main(String[] args){
                System.out.println(test(null));
            }
            //あれnull,B,Cじゃないの？
            //toString()はnullに対して実行できないのか
            //そして、catchの前にfinallyが実行されるのでB,Aの順になるのか

            private static String test(Object obj){
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

    static class No06{
        public static void main (String[] args){
            int result = sample();
            System.out.println(result);
        } 
        //finallyで先にreturnに到達するので20が出力される
        //↑は誤り。catchとfinallyの両方のreturnは実行されている
        //１．catchでreturnに到達し、戻り値用の領域が確保される
        //２．finally節内でreturnに到達し、戻り値用の領域が再度確保される
        //そのため20が出力される
        private static int sample(){
            try{
                throw new RuntimeException();
            }catch(RuntimeException re){
                return 10;
            }finally{
                return 20;
            }
        }
    }
    
    static class No07{

        //return時には戻り値用の領域が確保されている
        //finally節が実行された後に、その戻り値用の領域に
        //finally節内で変更された値がセットされる
        //プリミティブ型のため、sample()内のvalと戻り値用の領域は別物
        //そのため、10が出力される

        //returnが参照型の場合は、参照先のオブジェクトがfinally節内で変更されると
        //戻り値用の領域にセットされるオブジェクトも変更される

        static class Main{
            public static void main(String[] args){
                int result = sample();
                System.out.println(result);
            }
            //10が出力される

            private static int sample(){
                int val = 0;
                try{
                    String[] array = {"A","B","C"};
                    System.out.println(array[3]);
                }catch(RuntimeException re){
                    val = 10;
                    //ここでreturnに到達し、戻り値用の領域が確保される
                    //val=10が戻り値用の領域にセットされる
                    return val;
                }finally{
                    val += 10;
                    //10+10でvalhは20になるが
                    //戻り値用の領域には影響しない
                    System.out.println("finally val=" + val);
                }
                return val;
            }
        }
    }

    static class No08{
        //構文エラーによるコンパイルエラー
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
        public static void main (String[] args)throws Exception{
            System.out.println("A");
            System.out.println("finally");
            throw new RuntimeException();

            //これはコンパイルエラーとなる
            // try{
            //         System.out.println("A");
            //         throw new RuntimeException();
            // }

            // try{
            //     System.out.println("A");
            //     throw new RuntimeException();
            // }finally{
            //     System.out.println("finally");
            // }
        }
    }
    
    static class No09{
        //凄い構造だな
        //こんな気持ち悪いコード書いたことないや
        //D,E,Gか?
        public static void main (String[] args){
            try{
                try{
                    String[] array = {"A","B","C"};
                    System.err.println(array[3]);
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("D");
                }finally{
                    System.out.println("E");
                }
            }catch(ArrayIndexOutOfBoundsException parentE){
                System.out.println("F");
            }finally{
                System.out.println("G");
            }
        }
        //あってた
    }
    
    static class No10{
        
    }

}
