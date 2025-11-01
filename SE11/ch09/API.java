package SE11.ch09;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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


}
