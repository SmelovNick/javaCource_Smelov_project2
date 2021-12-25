import java.time.LocalDateTime;
import java.util.Scanner;
import CustomExceptions.InvalidCredentialsInputException;
import Notes.*;

public class Application {
    static boolean flag = true;
    Scanner scr = new Scanner(System.in);
    private User activeUser;
    private User[] users = new User[3];
    private Note[] notes;

    public Application(){
    }

    //TODO доделать
    public void run(){
        initUsers();
        login();
        while(flag){
            showMenu(MenuForm.MAINMENU);
            switch (chooseOption()){
                case 1: createNewNote();
                    break;
//                case 2: searchNote();
//                    break;
                case 3: login();
                    break;
                case 0: flag = false;
                    break;
                default:
                    System.out.println("Выбрана невалидная опция, попробуйте ещё раз");
            }
        }
    }

    public void initUsers() {
        System.out.println("Перед началом работы с программой необходимо зарегистрировать новых пользователей.");
        try{
            addNewUser(Role.USER,0);
            addNewUser(Role.MODERATOR,1);
            addNewUser(Role.ADMIN,2);
        }
        catch(InvalidCredentialsInputException icie){
            System.out.println(icie.getMessage());
            initUsers();
        }

    }

    public void addNewUser(Role role, int position) throws InvalidCredentialsInputException{
        System.out.print("Введите через пробел логин и пароль для нового пользователя с ролью " + role + ": ");
        boolean flag = false;
            String[] creds = scr.nextLine().split(" ");
            if (creds.length < 2)
                throw new InvalidCredentialsInputException("Логин/пароль нового пользователя введены некорректно");

            users[position] = new User(creds[0], creds[1], role);
    }

    void login() {
        User tempUser;
        boolean flag = false;
        while (!flag) {
            System.out.print("Введите логин: ");
            String login = scr.nextLine();
            for (User user : users) {
                if (login != null && login.equals(user.getLogin())) {
                    boolean _flag = false;
                    tempUser = user;
                    while (!_flag) {
                        System.out.print("Введите пароль: ");
                        String password = scr.nextLine();
                        if (password != null && password.equals(tempUser.getPassword())) {
                            activeUser = tempUser;
                            System.out.println("Выполнен вход под учетной записью " + activeUser.getLogin());
                            _flag = true;
                        }
                        else System.out.println("Неверно введен пароль, попробуйте снова");
                    }
                    flag = true;
                }
            }
            if (!flag) System.out.println("Неверно введен логин, попробуйте снова");
        }
    }

    void showMenu(MenuForm menuForm){
        System.out.println(menuForm.getMenuForm());
    }

    public static int chooseOption(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int option=-1;
        if(input.length() ==1) option = Integer.parseInt(input);
        return option;
    }

    public void createNewNote(){
        showMenu(MenuForm.NOTESMENU);
        switch (chooseOption()){
            case 1: createNote(NoteType.DIARYNOTE);
                break;
            case 2: createNote(NoteType.MEETINGNOTE);
                break;
            case 3: createNote(NoteType.SHOPPINGNOTE);
                break;
            case 4: createNote(NoteType.TODONOTE);
                break;
            case 0: flag = false;
                break;
            default:
                System.out.println("Выбрана невалидная опция, попробуйте ещё раз");
        }
    }
//TODO доделать
    void createNote(NoteType noteType) {
        Note note;
        switch(noteType){
            case DIARYNOTE: note = new DiaryNote(activeUser.getLogin());
            case MEETINGNOTE: note = new MeetingNote(activeUser.getLogin());
            case SHOPPINGNOTE: note = new ShoppingNote(activeUser.getLogin());
            case TODONOTE: note = new ToDoNote(activeUser.getLogin());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + noteType);
        }
        System.out.print("Введите название заметки: ");
        note.setHeader(scr.nextLine());
        System.out.print("Введите содержание заметки: ");
        note.setBody(scr.nextLine());
        note.setNoteCreationTime(LocalDateTime.now());
        note.setAuthor(activeUser.getLogin());
        System.out.println(note);
    }

    public void searchNote(){
        showMenu(MenuForm.SEARCHBYNAME);
    }
}
