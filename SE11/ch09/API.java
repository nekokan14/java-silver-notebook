package SE11.ch09;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import SE11.ch06.Item;

public class API {
    public static void main(String[] args){
        System.out.println(API.class.getName());
        No01 no01 = new No01();
        no01.print();
    }


    static class No01{
        //毎年積み立ての複利計算
        int m = 10000 ,x = 20;
        double r = 0.05;
        //Math.pow(基数,指数) 指数の累乗を求める
        int n = (int)(m * (Math.pow(1+r,x)-1)/r);

        void print(){
            System.out.println(n);
        }
    }

    static class No02{

        public static class Main{

            public static void main(String[] args){
                Sample[] samples = {
                    new Sample(2,"B"),
                    new Sample(3,"C"),
                    new Sample(1,"A"),
                };
                List<Sample> list = new ArrayList<Sample>(Arrays.asList(samples));
                //内部でTimSortが使われ、ソートで使われる比較の評価式は独自に実装したSampleComparatorクラスのcompareメソッドが使われる
                //TimSortは評価式に基づいてソートを行うだけ
                list.sort(new SampleComparator());
                //特定の理由を持ってソートアルゴリズムを別クラスに実装している
                //そこまで複雑でないアルゴリズムなので、実際にはこんな感じで書いたほうがいい気がする
                // list.sort(Comparator.comparingInt(Sample::getId).reversed());
                for(Sample s : list){
                    System.out.println(s.getName());
                }
                
            }
        }

        public static class Sample{
            private int id;
            private String name;
            public Sample(int id, String name){
                super();
                this.id = id;
                this.name = name;
            }

            public int getId(){
                return id;
            }

            public String getName(){
                return name;
            }
        }

        //Comparator.compareToを実装するのではなく
        //Comparatorインタフェースを実装したクラスを定義して、compareメソッドを独自に実装する(オーバーライド)
        public static class SampleComparator implements Comparator<Sample>{ 
            @Override 
            public int compare(Sample s1,Sample s2){
                if(s1.getId() < s2.getId()){
                    return 1;
                }
                if(s1.getId() > s2.getId()){
                    return -1;
                }
                return 0;
            }
        }
    }

    static class No03{
        public static class Main{
            public static void main(String[] args){
                List<Integer> list = Arrays.asList(new Integer[] {1,2,3});
                //ハック的な書き方に見える...
                // list.sort((a,b) -> -a.compareTo(b));
                //こっちのほうがわかりやすい
                list.sort(Comparator.reverseOrder());
                for(Integer num:list){
                    System.out.println(num);
                }
            }
        }
    }

    static class No04{
        public static class  Sample{
            public static void main(String[] args){
                char a = '0';
                int num = 0;
                if (Character.isAlphabetic(a)){
                    num++;
                }
                if(Character.isDigit(a)){
                    num++;
                }
                //へー アルファベットだけでなくUnicodeの小文字を判定してくれるのか
                if(Character.isLowerCase(a)){
                    num++;
                }
                System.out.println(num);
            }
        }
    }


    //LocalDateは不変(immutable)なので、ofメソッドで生成したインスタンスを変更することはできない
    static class No05{
        public static class Sample{
            public static void main(String[] args){
                LocalDate hoge = LocalDate.of(2015,0,1); //これコンパイルエラー教えてくれないのか
                LocalDate fuga = LocalDate.parse("2015-01-01"); 
                System.out.println(hoge.equals(fuga));
            }
        }
    }

    static class No06{
        public static class Sample{
            public static void main(String[] args){
                LocalDate hoge = LocalDate.of(2019,8,19);
                // LocalDate fuga = LocalDate.now();
                LocalDate fuga = LocalDate.of(2019,8,20);
                fuga.with(DayOfWeek.MONDAY);
                System.out.println(hoge.equals(fuga)+ "," + hoge.isBefore(fuga));
                //fugaが月曜日になるので、hogeが日曜日扱いでisBefore判定の結果が前の日付になる？
                //fugaが20190819(月)~20190825(日)の月曜日に変更される。
                //しかし、LocalDateは不変(immutable)なので、fugaの日付(値)自体は変わらない
                //なので、hoge.isBefore(fuga)はtrueになる
                
                //そもそもhogeとfugaの日付を勘違いしていた。やはりa,bで書くか
            }
        }
    }

    static class No07{
        //サンプルコードなし

        //java.util.ArrayListクラスはコレクションAPIの１つで動的配列とも呼ばれる
        //配列のように扱えるが、要素の追加や削除が容易にできる
        //ArrayListの特徴
        //1.オブジェクトであればどのような型でも格納可能
        //2.必要に応じて要素数を自動的に拡張
        //3.追加した順に要素を保持
        //4.nullも格納可能
        //5.重複する要素も格納可能
        //6.スレッドセーフではない(同期化されていない)

        //スレッドセーフなリストが必要な場合は
        //java.util.Vectorクラスを使用するか
        //java.util.CollectionsクラスのsynchronizedListメソッドを使用して同期化
    }

    static class No08{

        public static class Main{
            public static void main(String[] args){
                ArrayList<String> list = new ArrayList();
                //明示的にキャストしていないのでStringのリストとして扱われるか? 
                //ジェネリック型による型パラメータが指定されていない場合はObject型の変数として扱われる
                //そのため、異なる型のオブジェクトを格納できる
                list.add("A"); //String型
                list.add(10); //intがボクシングされてInteger型
                list.add('B'); //charがボクシングされてCharacter型
                for(Object obj : list){
                    // System.out.print(obj);
                    System.out.print("値: " + obj + ", 型: " + obj.getClass().getName() + "\n");
                }
                System.out.println("");
                System.out.print(list.getClass().getTypeName());
            }
        }
    }

    static class No09{
        public static class Main{
            public static void main(String[] args){
                ArrayList<String> list = new ArrayList<>();
                list.add("A");
                list.add(2,"B"); //そうか、次の次の位置を指定しているのでIndexOutOfBoundsExceptionになるのか
                //まだ用意されていないインデックスを指定して要素を追加して実行時例外が発生している
                list.add("C");
                list.add("D");
                for(String str : list){
                    System.out.print(str);
                }
            }
        }        
    }

    static class No10{
        public static class Main{
            public static void main(String[] args){
                ArrayList<String> list = new ArrayList<>();
                list.add("A");
                list.set(0,"B");
                list.add("C");
                list.set(1,"D");
                for(String str : list){
                    System.out.print(str);
                }
            }
        }
    }

    static class No11{

        public static class Main{
            public static void main(String[] args){
                ArrayList<Item> list = new ArrayList<>();
                list.add(new Item("A",100));
                list.add(new Item("B",200));
                list.add(new Item("C",300));
                list.add(new Item("A",100));
                // list.remove(new Item("A",500));
                //removeが何をしているかは知らないが
                //Itemクラスでequalsメソッドを定義していることから、getName()の値が等しいものを削除していると考えられる
                //B,Cが出力される?
                
                //removeメソッドの引数にObjectを渡せる。
                //今回はItemクラスのインスタンスを渡しているため、null出ない場合に、内部でItemクラスのequalsメソッドが呼び出される
                //Itemクラスのequals()はnameが一致しているかどうかのみで判定しているため、nameが"A"の要素が削除される
                //そして、removeメソッドは最初に見つかった(=equals()がtrueを返した)一致する要素のみを削除するため、最初の"A"のみが削除される
                //2番目の"A"は削除されない
                
                //remove()を使って複数の要素を削除する方法を検討してみる

                // ハック的な書き方: ループを逆順に回す
/*                 for (int i = list.size() - 1; i >= 0; i--) {
                    if (list.get(i).getName().equals("A")) {
                        list.remove(i);
                    }
                } */

                //クリーンな書き方(removeIfメソッドを使用)
                list.removeIf(l -> l.getName().equals("A"));

                for(Item item : list){
                    System.out.println(item.getName());
                }
            }
        }

        public static class Item{
            private String name;
            private int price;
            public Item(String name, int price){
                this.name = name;
                this.price = price;
            }

            public boolean equals(Object obj){
                if(obj instanceof Item){
                    Item tmp = (Item) obj;
                    if(tmp.name.equals(this.name)){
                        return true;
                    }  
                }
                return false;
            }
            public String getName(){
                return name;
            }
        }
    }

    static class No12{
        public static class Main{
            public static void main(String[] args){
                ArrayList<String> list = new ArrayList<>();
                list.add("A");
                list.add("B");
                list.add("C");

                for(String str: list){
                    if(("B".equals(str))){
                        list.remove(str);
                        // {"A","B","C"}のstrというObjectを削除している
                        //そのため、"A","C"が出力されるのではなく、"A"のみ出力される
                        //A -> Bでstrオブジェクトを削除 -> 終了
                        //いや違う
                        //ArrayListは要素が隙間なく配置されるように動作するため
                        //"B"が削除されると、"C"が前に詰められる = index 1に"C"が移動
                        //"C"はまだループで処理されていないため、次のループで処理されると思いきや
                        //ループはインデックスで管理されているため、"C"はスキップされてしまう
                        //結果的に"A"のみが出力される
                        //また、インデックス1に"C"が移動したことで、ループの終了条件であるインデックス2に達しなくなる
                        //そのため、ConcurrentModificationExceptionが検知される前にループが終了してしまう
                    } else{
                        System.out.println(str);
                    }
                }

            }
        }
    }

    static class No13{
        public static class Main{
            public static void main(String[] args){
                ArrayList<String> list = new ArrayList<>();
                list.add("A");
                list.add("B");
                list.add("C");
                list.add("D");
                list.add("E");

                for(String str : list){
                    if("C".equals(str)){
                        list.remove(str);//ここでConcurrentModificationExceptionが発生
                    }
                }
                
                for(String str : list){
                    System.out.println(str);
                }
                /* //forEachメソッドは内部でIteratorを使用しているため、要素の削除が安全に行える
                list.forEach(new Consumer<String>(){
                    @Override
                    public void accept(String str){
                        if("B".equals(str)){
                            list.remove(str);
                        } else{
                            System.out.println(str);
                        }
                    }
                }); */
            }
        }
    }

    static class No14{
        //サンプルコードなし
    }

    static class No15{
        public static class Main{
            public static void main(String[] args){
                String[] a = {"B","C"};
                String[] b = {"A","B","C"};
                System.out.println(Arrays.mismatch(a, b));
                //mismatchメソッドは2つの配列を比較し、最初に発見した異なる要素のインデックスを返す
                //インデックスを返すのでbooleanではなくintが戻り値となる
                //インデックスは0から始まるため、不一致が見つからなかった場合は-1が返される
            }
        }
    }

}
