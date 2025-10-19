package SE11.ch06;
import SE11.ch06.Item.*;

public class Main {

// No30_Sample
public static void main(String[] args) {
    Item.No30Sample s = new Item.No30Sample(10);
    modify(s); // オブジェクトを渡しているため、値参照先は同じ
               // 正確には参照のコピーを渡している
    System.out.println(s.num);
    // 20が出る
}
private static void modify(Item.No30Sample s){
    s.num *= 2;
}


/* 
// No29_Samplej
public static void main(String[] args) {
    Item.No29Sample s = new Item.No29Sample(10);
    modify(s.num);
    System.out.println(s.num);
}

//普段からこの使い方だから気にしていなかったけど
//そうかプリミティブ型だから値はコピーされているか
private static void modify(int num){ 
    //returnしていないからs.numは変わらない
    num *= 2; 
}
 */

/* // No25_Sample
public static void main(String[] args) {
    Item.No25Sample s = new Item.No25Sample();
} */

/*
// No24_Sample
public static void main(String[] args) {
    Item.No24Sample s = new Item.No24Sample();
}
*/

/*
//No23_Sample
public static void main(String[] args) {
    //引数が足りずコンパイルエラーになる?
    Item.No23Sample s = new Item.No23Sample();
}
*/

/*
//No22_Sample
public static void main(String[] args) {
    //newでインスタンスを生成せず、static fieldにアクセスしているため初期化子が呼ばれる。
    //いや、初期化していないからnullのままか？intだから0か?
    //0が出るかな
    System.out.println(Item.No22Sample.num);
}
*/
    
/*
//No21_Sample
public static void main(String[] args) {
    Item.No21Sample s = new Item.No21Sample();
}
*/


/*
//No20_Sample
public static void main(String[] args) {
    Item.No20Sample s = new Item.No20Sample();
    s.No20Sample();
}
*/

/*
//No17_Sample
//m.calcを呼び出す際に整数を渡しているので、どちらを使うかを判断できずコンパイルエラーになる?
//よし 予想通り
public static void main(String[] args) {
    Main m = new Main();
    System.out.println(m.calc(2, 3));
}
private double calc(double a, int b){
    return a + b;
}
private double calc(int a, double b){
    return a + b;
}
*/
}
