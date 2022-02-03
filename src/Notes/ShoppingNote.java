package Notes;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

public class ShoppingNote extends Note<NoteType>
{
    private String [] buyPoints;
    public ShoppingNote(String author, Scanner scr) {
        System.out.print("Введите название заметки: ");
        this.setHeader(scr.nextLine());
        System.out.print("Введите через пробел список покупок: ");
        this.setBody(scr.nextLine());
        this.setNoteCreationTime(LocalDateTime.now());
        this.setAuthor(author);
        this.setNoteType(NoteType.SHOPPINGNOTE);
        parseBuyPoints();
    }
    void parseBuyPoints(){
        this.buyPoints = this.getBody().split(" ");
    }

    @Override
    public String toString() {
        return "ShoppingNote{" +
                "header='" + getHeader() + '\'' +
                ", body='" + getBody() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", noteCreationTime='" + getNoteCreationTime() + '\'' +
                ", noteType='" + getNoteType() + '\'' +
                ", Several buy points ='" + Arrays.toString(buyPoints) + '\'' +
                '}';

    }
}
