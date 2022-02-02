package Notes;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ToDoNote extends Note<NoteType>
{
    String[] toDoPoints;
    public ToDoNote(String author, Scanner scr) {
        System.out.print("Введите название заметки: ");
        this.setHeader(scr.nextLine());
        System.out.print("Введите содержание заметки: ");
        this.setBody(scr.nextLine());
        this.setNoteCreationTime(LocalDateTime.now());
        this.setAuthor(author);
        this.setNoteType(NoteType.TODONOTE);
        parseBuyPoints();
    }

    void parseBuyPoints(){
        this.toDoPoints = this.getBody().split(" ");
    }

    @Override
    public String toString() {
        return "ToDoNote{" +
                "header='" + getHeader() + '\'' +
                ", body='" + getBody() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", noteCreationTime=" + getNoteCreationTime() + '\'' +
                ", noteType=" + getNoteType() +
                "Several TODO points =" + toDoPoints.toString() +
                '}';

    }
}
