package sem2.task3;

import java.util.UUID;

public class Program {
    public static void main(String[] args) throws IllegalAccessException {
        Employee user = new Employee("Станислав", "sample@gmail.com");
        UUID pk = UUID.randomUUID();
        user.setId(pk);

        QueryBuilder queryBuilder = new QueryBuilder();
        String insertQuery = queryBuilder.buildInsertQuery(user);
        System.out.printf("INSERT QUERY: %s\n", insertQuery);

        String selectQuery = queryBuilder.builderSelectQuery(Employee.class, pk);
        System.out.printf("SELECT QUERY: %s\n", selectQuery);

        String updateQuery = queryBuilder.buildUpdateQuery(user);
        System.out.printf("UPDATE QUERY: %s\n", updateQuery);

        String deleteQuery = queryBuilder.buildDeleteQuery(Employee.class, pk);
        System.out.printf("DELETE QUERY: %s\n", deleteQuery);
    }
}
