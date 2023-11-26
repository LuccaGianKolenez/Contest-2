// PersonDAO.java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private Connection connection;

    public PersonDAO() {
        this.connection = DBConnection.getConnection();
    }

    public void createPerson(Person person) {
        String query = "INSERT INTO people (name, age) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        String query = "SELECT * FROM people";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public void updatePerson(Person person) {
        String query = "UPDATE people SET name=?, age=? WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            pstmt.setInt(3, person.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson(int personId) {
        String query = "DELETE FROM people WHERE id=?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, personId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
