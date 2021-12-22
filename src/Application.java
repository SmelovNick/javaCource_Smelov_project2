import Exceptions.InvalidCredentialsInputException;

import java.util.Scanner;

public class Application {
    static boolean flag = true;
    Scanner scr = new Scanner(System.in);
    private User[] users = new User[3];
    private User activeUser;

    public Application(){
    }

    //TODO доделать
    public void run(){
        initUsers();
        login();
        while(flag){
            showMenu();
            switch (chooseOption()){
//                case 1: createNewNote();
//                    break;
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
        addNewUser(Role.USER,0);
        addNewUser(Role.MODERATOR,1);
        addNewUser(Role.ADMIN,2);
    }

    public void addNewUser(Role role, int position){
        System.out.print("Введите через пробел логин и пароль для нового пользователя с ролью " + role.getRole() + ": ");
        boolean flag = false;
        try {
            String[] creds = scr.nextLine().split(" ");
            if (creds.length < 2)
                throw new InvalidCredentialsInputException("Логин/пароль нового пользователя введены некорректно");

            users[position] = new User(creds[0], creds[1], role);
        }
        catch (InvalidCredentialsInputException icie){
            System.out.println(icie.getMessage());
            run();
        }
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

    void showMenu(){
        System.out.println("1. Создать новую заметку\n" +
                "2. Поиск заметки по названию\n" +
                "3. Войти под другим пользователем\n" +
                "0. Выход");
    }

    public static int chooseOption(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int option=-1;
        if(!input.isEmpty() && input.length() ==1) option = Integer.parseInt(input);
        return option;
    }

    public void createNewNote(){

    }

    public void searchNote(){

    }
}
