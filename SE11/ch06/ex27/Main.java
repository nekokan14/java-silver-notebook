package SE11.ch06.ex27;

public class Main {
    //Story book classはBook classをextendsしているからprotectedなメソッドを呼び出せるはず...
    public static void main(String[] args) {
        StoryBook story = new StoryBook();
        story.setIsbn("xxx-x-xxxxxx-xx-x");
        // story.printInfo();
        story.showBookInfo();
    } 
    //インスタンスの定義は複数のクラスから作られる
    //StoryBook extends Book だと BookをもとにしたStoryBookの集合ができるイメージだが
    //実際にはStory Book + Book の(暗黙的なクラスを除く)2つの定義を持つ
    //Bookの定義はother packageにあるが、StoryBookの定義は ex27 packageにある
    //StoryBookの定義はBookの定義を継承しているので、Bookの定義にアクセスできる
}
