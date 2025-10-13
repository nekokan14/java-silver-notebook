package SE11.ch06.ex27;
import SE11.ch06.other.Book;

public class StoryBook extends Book {
    public void showBookInfo(){
        super.printInfo();
        this.printInfo();
    }
    @Override
    protected void printInfo() {
        setIsbn("this is a Story Book info");
        super.printInfo();
    }
}
