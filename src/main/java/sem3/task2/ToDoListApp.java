package sem3.task2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {

    public static final String FILE_JSON = "task.json";
    public static final String FILE_BIN = "tasks.bin";
    public static final String FILE_XML = "tasks.xml";

    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final XmlMapper xmlMapper = new XmlMapper();

    static {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public static void addNewTask(Scanner scanner, List<ToDo> tasks) {
        System.out.println("Введите название новой задачи:");
        String newTaskTitle = scanner.nextLine();
        tasks.add(new ToDo(newTaskTitle));
        saveTasks(tasks);
        System.out.println("Новая задача добавлена.");
    }

    public static void saveTasks(List<ToDo> tasks) {
        saveTaskToFile(FILE_JSON, tasks);
        saveTaskToFile(FILE_BIN, tasks);
        saveTaskToFile(FILE_XML, tasks);
    }

    public static void saveTaskToFile(String fileName, List<ToDo> tasks) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.writeValue(new File(fileName), tasks);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(tasks);
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), tasks);
            }
        } catch (IOException e) {
            System.err.println("Ошибка сохранения данных в файл " + fileName + ": " + e.getMessage());
        }
    }

    public static List<ToDo> loadTasksFromFile(String fileName) {
        List<ToDo> tasks = new ArrayList<>();
        File file = new File(fileName);

        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    tasks = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, ToDo.class));
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        tasks = (List<ToDo>) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) {
                    tasks = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, ToDo.class));
                }
            } catch (IOException | ClassNotFoundException | ClassCastException e) {
                System.err.println("Ошибка загрузки данных из файла " + fileName + ": " + e.getMessage());
            }
        }

        return tasks;
    }

    public static void markTaskAsDone(Scanner scanner, List<ToDo> tasks) {
        System.out.println("Введите порядковый номер задачи для отметки как выполненной:");
        String input = scanner.nextLine();

        try {
            int taskNumber = Integer.parseInt(input) - 1;
            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                tasks.get(taskNumber).setDone(true);
                saveTasks(tasks);
                System.out.println("Задача отмечена как выполнена.");
            } else {
                System.out.println("Некорректный номер задачи. Попробуйте снова.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Попробуйте снова.");
        }
    }

    public static void displayTasks(List<ToDo> tasks) {
        System.out.println("Список задач:");
        for (int i = 0; i < tasks.size(); i++) {
            ToDo task = tasks.get(i);
            String status = task.isDone() ? "[x]" : "[ ]";
            System.out.println((i + 1) + ". " + status + " " + task.getTitle());
        }
    }
}