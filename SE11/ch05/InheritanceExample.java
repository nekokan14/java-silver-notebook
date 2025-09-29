package SE11.ch05;

// インターフェース（契約の定義）
interface A {
    // メソッドのシグネチャのみ定義
}

// 抽象クラス（部分的な実装）
abstract class B implements A {
    // インターフェースAを「実装」する
    // 具象メソッドも抽象メソッドも持てる
}

// 具象クラス（完全な実装）
class C extends B {
    // 抽象クラスBを「継承」する
    // 全ての抽象メソッドを実装する必要がある
}

// 継承チェーン
class D extends C {
    // クラスCを「継承」する
}