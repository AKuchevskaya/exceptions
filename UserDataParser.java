import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
// import java.time.LocalDate;
// import java.util.Date;
import java.util.Scanner;
// import java.text.SimpleDateFormat;
// import java.text.ParseException;
import java.lang.String;

public class UserDataParser {
    public static void main(String[] args) throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные через пробел в формате: Фамилия Имя Отчество, дата рождения(dd.mm.yyyy), телефон, пол(f или m): ");
        String input = scanner.nextLine();
        try {
            String[] data = input.split(" ");
            if (data.length != 5) {
                throw new IllegalArgumentException("Не все данные введены");
            }
            String fullName = data[0] + " " + data[1] + " " + data[2];
            String dateOfBirth = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            // Проверка форматов данных
            if (!((dateOfBirth).matches("\\d{2}.\\d{2}.\\d{4}") || (gender != 'f' && gender != 'm'))) {
                throw new IllegalArgumentException("Неверный формат данных");
            }
            // Парсинг даты рождения
            // Localdate birthDate = LocalDate.parse(dateOfBirth, java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            // Создаем файл с фамилией
            String lastName = data[0];
            File file = new File(lastName + ".txt");
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(file, true);
                writer.write(fullName + dateOfBirth + phoneNumber + gender);
                writer.close();
                System.out.println("Файл " + file.getName() + " создан");
            } else {
            System.out.println("Файл уже существует.");
            }

            // Вывод обработанных данных
            System.out.println("ФИО: " + fullName) ;
            System.out.println("Дата рождения: " + dateOfBirth);
            System.out.println("Номер телефона: " + phoneNumber);
            System.out.println("Пол: " + gender);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: Недостаточно данных");
        } catch (IOException e){
            System.out.println("Ошибка при создании файла");
        }
    }
}