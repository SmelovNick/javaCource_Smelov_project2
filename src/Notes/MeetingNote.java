package Notes;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MeetingNote extends Note<NoteType>
{
    public MeetingNote(String author, Scanner scr){
        System.out.print("Введите название заметки: ");
        this.setHeader(scr.nextLine());
        System.out.print("Введите содержание заметки: ");
        this.setBody(scr.nextLine());
        this.setNoteCreationTime(LocalDateTime.now());
        this.setAuthor(author);
        this.setNoteType(NoteType.MEETINGNOTE);
    }

    @Override
    public String toString() {
        return "MeetingNote{" +
                "header='" + getHeader() + '\'' +
                ", body='" + getBody() + '\'' +
                ", author='" + getAuthor() + '\'' +
                ", noteCreationTime='" + getNoteCreationTime() + '\'' +
                ", noteType='" + getNoteType() + '\'' +
                '}';

    }
}
