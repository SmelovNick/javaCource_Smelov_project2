import java.util.Scanner;
import CustomExceptions.InvalidCredentialsInputException;
import CustomExceptions.NullNoteException;
import Notes.*;

public class Application {
    static boolean flag = true;
    Scanner scr = new Scanner(System.in);
    private User activeUser;
    private final User[] users = new User[3];
    private Note[] notes;

    public Application(){
    }

    //TODO доделать
    public void run(){
        initUsers();
        login();
        while(flag){
            showMenu(MenuForm.MAINMENU);
            switch (chooseOption()) {
                case 1 -> createNewNote();
                case 2 -> searchNote();
                case 3 -> login();
                case 0 -> flag = false;
                default -> System.out.println("Выбрана невалидная опция, попробуйте ещё раз");
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

    //TODO протестировать
    public void createNewNote() {
        boolean createNoteflag = true;
        while (createNoteflag) {
            showMenu(MenuForm.NOTESMENU);
            switch (chooseOption()) {
                case 1 -> addNoteToArray(new DiaryNote(activeUser.getLogin(), scr));
                case 2 -> addNoteToArray(new MeetingNote(activeUser.getLogin(), scr));
                case 3 -> addNoteToArray(new ShoppingNote(activeUser.getLogin(), scr));
                case 4 -> addNoteToArray(new ToDoNote(activeUser.getLogin(), scr));
                case 0 -> createNoteflag = false;
                default -> System.out.println("Выбрана невалидная опция, попробуйте ещё раз");
            }
        }
    }

    //TODO доделать
    public void searchNote() {
        boolean searchNoteflag = true;
        while (searchNoteflag) {
            showMenu(MenuForm.SEARCHBYNAME);
            switch (chooseOption()) {
                case 1 -> showNoteByHeader();
                case 2 -> changeNoteHeader(activeUser);
                case 3 -> changeWordInNote(activeUser);
                case 4 -> changeNoteBody(activeUser);
                case 5 -> showNoteAuthor();
                case 6 -> deleteNote();
                case 0 -> searchNoteflag = false;
                default -> System.out.println("Выбрана невалидная опция, попробуйте ещё раз");
            }
        }
    }


    //TODO протестировать
    private boolean validateUser(User activeUser) {
        if(activeUser.getRole() != Role.USER){
            return true;
        }
        else System.out.println("Текущий пользователь не имеет права на выполнение данной операции.");
        return false;
    }

    //TODO протестировать
    private int findNote() {
        System.out.print("Введите заголовок заметки: ");
        String estHeader = scr.nextLine();
        if (notes != null) {
            for (int i = 0; i< notes.length; i++) {
                if (notes[i]!= null && estHeader.equals(notes[i].getHeader())) return i;
            }
            System.out.println("По заданному заголовку заметок не найдено.");
        } else
            System.out.println("Не сохранено ни одной заметки");
        return -1;
    }

    private void showNoteByHeader() {
        int showNoteIndex = findNote();
//        if(showNoteIndex>=0) System.out.println(notes[showNoteIndex]);
        try {
            if(showNoteIndex>=0) {
                System.out.println(notes[showNoteIndex]);
            }
            else throw new NullNoteException("Данная заметка была удалена ранее.");
        } catch (NullNoteException e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO дописать и протестировать
    private void changeNoteHeader(User activeUser) {
        if (validateUser(activeUser)){
            int showNoteIndex = findNote();
//            if(showNoteIndex>=0){
//                System.out.print("Введите новый заголовок для заметки");
//                String newHeader = scr.nextLine();
//                if (newHeader !=null) notes[showNoteIndex].setHeader(newHeader);
//                System.out.println("Установлен новый заголовок");
//            }
            try {
                if(showNoteIndex>=0) {
                    System.out.print("Введите новый заголовок для заметки");
                    String newHeader = scr.nextLine();
                    if (newHeader !=null) notes[showNoteIndex].setHeader(newHeader);
                    System.out.println("Установлен новый заголовок");
                }
                else throw new NullNoteException("Данная заметка была удалена ранее.");
            } catch (NullNoteException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void changeWordInNote(User activeUser) {
        if (validateUser(activeUser)){
            int showNoteIndex = findNote();

//            if(showNoteIndex>=0){
//                System.out.print("Введите через пробел заменяемое и новое слово: ");
//                String[] replaceWords = scr.nextLine().split(" ");
//                if (replaceWords.length == 2) {
//                    for(String word : words) if(word.equals(replaceWords[1])) word = replaceWords[1];
//                }
//                notes[showNoteIndex].setBody(String.join(" ", words));
//            }
            try {
                if(showNoteIndex>=0) {
                    String[] words = notes[showNoteIndex].getBody().split(" ");
                    System.out.print("Введите через пробел заменяемое и новое слово: ");
                    String[] replaceWords = scr.nextLine().split(" ");
                    if (replaceWords.length == 2) {
                        for(String word : words) if(word.equals(replaceWords[1])) word = replaceWords[1];
                    }
                    notes[showNoteIndex].setBody(String.join(" ", words));
                }
                else throw new NullNoteException("Данная заметка была удалена ранее.");
            } catch (NullNoteException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void changeNoteBody(User activeUser) {
        if (validateUser(activeUser)){
            int showNoteIndex = findNote();
//            if(showNoteIndex>=0){
//                System.out.print("Введите новое содержимое заметки: ");
//                String newBody = scr.nextLine();
//                if (newBody!=null) notes[showNoteIndex].setBody(newBody);
//            }
            try {
                if(showNoteIndex>=0) {
                    System.out.print("Введите новое содержимое заметки: ");
                    String newBody = scr.nextLine();
                    if (newBody!=null) notes[showNoteIndex].setBody(newBody);
                }
                else throw new NullNoteException("Данная заметка была удалена ранее.");
            } catch (NullNoteException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showNoteAuthor() {
        int showNoteIndex = findNote();
//        if(showNoteIndex>=0){
//            System.out.println(notes[showNoteIndex].getAuthor());
//        }
        try {
            if(showNoteIndex>=0) System.out.println(notes[showNoteIndex].getAuthor());
            else throw new NullNoteException("Данная заметка была удалена ранее.");
        } catch (NullNoteException e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO уточнить, нужно ли удалять заметку или переводить в null
    private void deleteNote() {
        int showNoteIndex = findNote();
        try {
            if(showNoteIndex>=0) notes[showNoteIndex] = null;
            else throw new NullNoteException("Данная заметка была удалена ранее.");
        } catch (NullNoteException e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO протестировать
    private void addNoteToArray(Note note){
        System.out.println("Создана новая заметка:\n" + note.getHeader());
        if(notes ==null){
            notes = new Note[]{note};
        }
        else{
            Note[]tempNotesArray = new Note[notes.length+1];
            for(int i=0; i < tempNotesArray.length; i++){
                if(i == tempNotesArray.length-1) tempNotesArray[i] = note;
                else tempNotesArray[i] = notes[i];
            }
            notes = tempNotesArray;
        }
    }
}
