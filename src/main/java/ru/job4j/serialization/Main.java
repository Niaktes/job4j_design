package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact(123456, "8(800)555-35-35"),
                new String[] {"Worker", "Married"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /* Модифицируем json-строку */
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"zipCode\":554433,"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);


        final Cat cat = new Cat(true, 4, "sleep", new Preserves("tuna", 80), new String[] {"MEOW!", "purr"});
        System.out.println(cat);
        String recordedCat = gson.toJson(cat);
        System.out.println(recordedCat);
        final Cat readedCat = gson.fromJson(recordedCat, Cat.class);
        System.out.println(readedCat);
    }

}
