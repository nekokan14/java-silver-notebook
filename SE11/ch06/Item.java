package SE11.ch06;
public class Item {
    public static class No1Item {
        private int num = 10;
        public void setNum(int num){
            this.num = num;
        } 
        public int getNum(){return this.num;}
    }
    
    public static class No2Item {
        public String name;
        public int price;
        public void printInfo(){
            System.out.println(name + ", " + price);
        }
    }

    public static class No5Sample{
        static int num = 0;
    }
    
    public class No7Sample{
        public void hello(){
            System.out.println("hello");
        }
    }

    public class No8Sample{
        public int add(Integer a ,Integer b){
            return a + b;//auto unboxing
        }
    }

    public class No11Sample{
        private String value;
        public void setValue(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value; 
        }
    }

    //いい加減Itemをnewするのが面倒になってきたので以降はstatic classにする
    public static class No12Sample{
        float divide (int a, int b){
            return (float)a / (float)b;
        }
    }

    public static class No13Sample{
        public int method(int a, int b) {
            return  a+b;
        }
    }
    
    public static class No20Sample{
        void No20Sample(){
            System.out.println("hello.");
        }
    }

    public static class No21Sample{
        No21Sample(){
            System.out.println("A");
        }//コンパイルエラーになりそうだが...
        {
            System.out.println("B");
        }
        //なるほどコンストラクタがどちらかが判断できないから実行エラーになるか？
        //そうか！{}はclassに対する初期化子で、コンストラクタより先に実行されるのか！
    }

    public static class No22Sample{
        static int num;
        {
            num = 10;
        }
        {//初期化子って複数書けるのか?
            num = 20;
        }
        static { //クラスロード時に実行される これはstatic領域に配置されるfieldだから可能で、
                //static領域に配置されないfieldではコンパイルエラーになる?
            num = 20;
        }
        //初期化子複数化書ける。同じくstatic初期化子も複数書ける。
        //ただし、順番は上から順に実行される。
        public No22Sample(){
            num = 100;
        }
    }

    public static class No23Sample{
        //戻り値が明記されているからコンストラクタではないメソッドとして扱われる?
        void No23Sample(){
            System.out.println("A");
        }
        //コンストラクタを定義すると引数が必要ないデフォルトコンストラクタが自動生成されないため
        //インスタンスを生成時には引数を渡す必要がある。
        No23Sample(String str){
            System.out.println(str);
        }

        
    }
    public static class No24Sample{
        public No24Sample(){
            //thisがまだよくわかっていない
            // No24Sample(null,0);
            this(null,0);

            //thisには２つの使用方法がある
            //1. インスタンスメソッド内で、そのメソッドを呼び出したインスタンスを指す
            //2. オーバーロードされたコンストラクタから、同じクラス内の別のコンストラクタを呼び出す際

            //インスタンスメソッド内?そのメソッド?を呼び出したインスタンス?
            //うーん、関係性がよくわからん

        }
        public No24Sample(String str,int num){
            System.out.println("OK.");
        }


        //書き手が意識していないだけで、同じクラスで定義した変数やクラスを呼び出す際にはthis.が暗黙的についている
        //例えばクラス内でグローバルな変数があり、メソッドの引数と名前が一致する場合
        //書き手は意識的にthis.変数名 と(引数の)変数名を使い分ける必要がある
        //この現象をシャドーイングという
        //外側のスコープにある変数が内側にある同名の変数を隠してしまうことをシャドーイングという
        //その場合、内側の変数が優先されるため、外側の変数にアクセスしたい場合にはthis.をつける必要がある

        //thisの使用例
        // ...existin code...
        public static class Player {
            private String name;

            public Player(String name) {
                this.name = name; // 用途1: フィールドと引数の区別
            }

            // ① 自己紹介するメソッド
            public void introduce() {
                System.out.println("My name is " + this.name);
            }

            // ② 挨拶して自己紹介するメソッド
            public void greet() {
                System.out.println("Hello!");

                // 同じインスタンス内の別のメソッドを呼び出す
                introduce();      // これが普通の書き方 (this. は省略されている)
                this.introduce(); // これも全く同じ意味。明示的に「このインスタンスの」と書いているだけ。
            }
        }
        // ...existing code...
    }

    public static class No25Sample{
        public No25Sample(){
            System.out.println("A");
            this("B");//動くのは理解できたが、気持ち悪いなこの書き方
            //いやコンパイルエラーになるな
            //どうやらthis()は最初の処理として呼び出す必要があるらしい
            //つまりこう
            // this("B");
            // System.out.println("A");
            //なぜこのルールを守らないといけないのか？
            //暗黙的にsuper()が呼ばれるから
            //this()は、「このコンストラクタでの初期化処理を、同じクラスの別のコンストラクタに**委譲（delegate）**します」という意味
            //コンパイルエラーになる場合のコードの処理は、Objectクラスのコンストラクタを呼び出すsuper()が
            //暗黙的に呼ばれる前にSystem.out.println("A")が実行されてしまう
            //つまり、オブジェクトが完全に初期化される前に、そのオブジェクトを操作してしまうことになる
            //これを防ぐために、this()やsuper()はコンストラクタの最初に書かなければならないというルールがある
        }
        public No25Sample(String str){
            // ここで暗黙的に super(); が呼ばれている (親クラスであるObjectのコンストラクタ呼び出し)
            System.out.println(str);
        }

        // なぜthis()やsuper()は先頭でなければならないのか？
        // 答え: 「オブジェクトが不完全に初期化された状態で使われる」のを防ぐため。
        //
        // 1. オブジェクトの初期化は、親クラスから子クラスへと順番に行われる必要がある。
        //    (Carを作るには、まず親のVehicleが完成していなければならない)
        //
        // 2. 親クラスの初期化は、親のコンストラクタを呼び出すことで行われる。
        //    これが super() の役割。コンパイラは、コンストラクタの先頭に super() を自動で挿入する。
        //
        // 3. もし this() を先頭以外に置けると、親クラスが初期化される前(super()が呼ばれる前)に
        //    子クラスの処理(System.out.println("A")など)が実行できてしまう。
        //    これは、未完成のオブジェクトを操作することになり、非常に危険。
        //
        // 4. そのため、this() を先頭に置くことで「初期化処理の責任」を別のコンストラクタに委譲し、
        //    最終的にどこか一つのコンストラクタで super() が最初に呼ばれる、という連鎖を保証している。


        class Vehicle {
            Vehicle() {
                // ここで暗黙的に super(); が呼ばれ、Objectクラスが構築される
                System.out.println("Vehicle is constructed.");
            }
        }

        class Car extends Vehicle {
            Car() {
                // ここで暗黙的に super(); が呼ばれ、Vehicleクラスのコンストラクタが実行される
                System.out.println("Car is constructed.");
            }
        }

        // new Car() を実行すると...
        // 1. Car() が呼ばれる
        // 2. Car() の先頭で super() が呼ばれ、Vehicle() が実行される
        // 3. Vehicle() の先頭で super() が呼ばれ、Object() が実行される
        // 4. Object() が完了
        // 5. "Vehicle is constructed." と表示
        // 6. Vehicle() が完了
        // 7. "Car is constructed." と表示
        // 8. Car() が完了
    }

    //このクラスにカプセル化を適用するらしい
    //呼び出し元が直接アクセスできずに、
    //メソッドを介してアクセスするように制限するか、そもそもstaticなインスタンスを返すのかな?

    public static class No28Sample{
        int num;
        int getNum(){return num;}
        void setNum(int num){this.num = num;}
    }
    public static class No28SampleCap{
        private int num;
        public int getNum(){return num;}
        private void setNum(int num){this.num = num;}
    }

    /* 
    //static ファクトリメソッドを実装してみたかったが、まだ理解が浅くてよくわからんため保留
    public static class No28Sample2{
        private int num;
        private static No28Sample2 instance = new No28Sample2();
        private No28Sample2(){}
        public static No28Sample2 getInstance(){
            return instance;
        }
        public int getNum(){return num;}
        public void setNum(int num){this.num = num;
    } 
    */

    public static class No29Sample{
        int num;
        public No29Sample(int num){
            this.num = num;
        }
    }
    public static class No30Sample{
        int num;
        public No30Sample(int num){
            this.num = num;
        }
    }

}

