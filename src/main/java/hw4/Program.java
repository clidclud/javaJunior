package hw4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory()) {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Выберите действие:\n" +
                        "1. Создать запись в БД\n" +
                        "2. Прочитать запись в БД\n" +
                        "3. Изменить запись в БД\n" +
                        "4. Удалить запись в БД\n" +
                        "5. Вывести все записи\n" +
                        "6. Выйти");

                int num = scanner.nextInt();

                switch (num) {
                    case 1:
                        create(sessionFactory);
                        break;
                    case 2:
                        read(sessionFactory);
                        break;
                    case 3:
                        update(sessionFactory);
                        break;
                    case 4:
                        delete(sessionFactory);
                        break;
                    case 5:
                        readAll(sessionFactory);
                        break;
                    case 6:
                        System.out.println("Выход из программы...");
                        return;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите вариант от 1 до 6.");
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Person person = Person.create(new Scanner(System.in)); // Передаем Scanner
            session.save(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите ID персоны: ");
            int personId = scanner.nextInt();
            Person person = session.get(Person.class, personId);
            System.out.println(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите ID персоны: ");
            int personId = scanner.nextInt();
            scanner.nextLine();

            Person person = session.get(Person.class, personId);
            if (person != null) {
                System.out.print("Введите новое имя: ");
                String newName = scanner.nextLine();
                System.out.print("Введите новый возраст: ");
                int newAge = scanner.nextInt();

                person.setName(newName);
                person.setAge(newAge);

                session.getTransaction().commit();
                System.out.println("Запись обновлена.");
            } else {
                System.out.println("Персона с таким ID не найдена.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void readAll(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            List<Person> people = session.createQuery("from Person", Person.class).list();

            for (Person person : people) {
                System.out.println(person);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите ID персоны: ");
            int personId = scanner.nextInt();
            Person person = session.get(Person.class, personId);
            if (person != null) {
                session.delete(person);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
