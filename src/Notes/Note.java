package Notes;

import java.time.LocalDateTime;

public abstract class Note{
    private String header;
    private String body;
    private String author;
    private LocalDateTime noteCreationTime;
    private NoteType noteType;



    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getNoteCreationTime() {
        return noteCreationTime;
    }

    public void setNoteCreationTime(LocalDateTime noteCreationTime) {
        this.noteCreationTime = noteCreationTime;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }

    @Override
    public String toString() {
        return "Note{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                ", author='" + author + '\'' +
                ", noteCreationTime=" + noteCreationTime +
                ", noteType=" + noteType +
                '}';
    }
}
