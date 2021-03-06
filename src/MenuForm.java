public enum MenuForm {
    MAINMENU("1. Создать новую заметку\n" +
            "2. Поиск заметки по названию\n" +
            "3. Войти под другим пользователем\n" +
            "0. Выход"),
    NOTESMENU("Выберите тип новой заметки:\n" +
            "1. Дневник\n" +
            "2. Митинг ноуты\n" +
            "3. Список покупок\n" +
            "4. Список дел\n" +
            "0. Назад"),
    SEARCHBYNAME("1. Вывести заметку в консоль\n" +
            "2. Изменение названия заметки\n" +
            "3. Замена слова в заметке\n" +
            "4. Замена тела заметки\n" +
            "5. Вывести автора заметки\n" +
            "6. Удалить заметку\n" +
            "0. Назад\n");

    private String menuForm;

    MenuForm(String menuForm){
        this.menuForm = menuForm;
    }

    public String getMenuForm() {
        return menuForm;
    }
}
