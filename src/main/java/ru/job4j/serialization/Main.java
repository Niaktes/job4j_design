package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        Cat cat = new Cat(true, 4, "sleep", new Preserves("tuna", 80), "MEOW!", "purr");
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(Cat.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xmlCat = "";

        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(cat, writer);
            xmlCat = writer.getBuffer().toString();
            System.out.println(xmlCat);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xmlCat)) {
            /* Десериализуем */
            Cat resultCat = (Cat) unmarshaller.unmarshal(reader);
            System.out.println(resultCat);
        }
        System.out.println();



        /* Сериализация в JSON
        Person person = new Person(false, 30, new Contact(123456, "11-111"), "Worked", "Married");
        Преобразуем объект person в json-строку.
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        Модифицируем json-строку
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
        */
    }

}
