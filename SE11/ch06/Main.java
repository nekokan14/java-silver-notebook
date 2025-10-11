package SE11.ch06;
import SE11.ch06.Item.*;

public class Main {

//No23_Sample
public static void main(String[] args) {
    //引数が足りずコンパイルエラーになる?
    Item.No23Sample s = new Item.No23Sample();
}
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
