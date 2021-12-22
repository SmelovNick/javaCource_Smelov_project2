package Notes;

import java.time.LocalDateTime;

public abstract class Note{
    private String header;
    private String body;
    private String author;
    private LocalDateTime noteCreationTime;

    public abstract void editNote();


}
