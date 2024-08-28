package hw3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersonDAO {
    private EntityManagerFactory emf;

    public PersonDAO() {
        this.emf = Persistence.createEntityManagerFactory("PersonPU");
    }

    public void addPerson(Person person) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void updatePerson(Person person) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deletePerson(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);
            if (person != null) {
                em.remove(person);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null) {
            emf.close();
        }
    }

    public static void main(String[] args) {
        PersonDAO dao = new PersonDAO();
        Person person = new Person("Petr", 28);
        dao.addPerson(person);
        person.setAge(29);
        dao.updatePerson(person);
        dao.deletePerson(person.getId());
        dao.close();
    }
}
