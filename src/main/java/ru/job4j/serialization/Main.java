package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Cat cat = new Cat(true, 4, "sleep", new Preserves("tuna", 80), new String[] {"MEOW!", "purr"});
        JSONObject jsonCat = new JSONObject(cat);
        String jsonStringCat = jsonCat.toString();
        System.out.println(jsonStringCat);

        /* Варианты получения json-объектов
        JSONObject из json-строки
        JSONObject jsonContact = new JSONObject("{\"zipCode\":456789,\"phone\":\"111-11-11\"}");

        JSONArray из ArrayList
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        JSONObject напрямую методом put
        final Person person = new Person(false, 30, new Contact(456789, "222-22-22"), "Worker", "Married");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(person).toString());
        */


        /* Сериализация в XML
        Cat cat = new Cat(true, 4, "sleep", new Preserves("tuna", 80), "MEOW!", "purr");
        Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Cat.class);
        Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xmlCat = "";

        try (StringWriter writer = new StringWriter()) {
            Сериализуем
            marshaller.marshal(cat, writer);
            xmlCat = writer.getBuffer().toString();
            System.out.println(xmlCat);
        }
        Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xmlCat)) {
            Десериализуем
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
