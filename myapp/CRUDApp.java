// CRUDApp.java
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CRUDApp {
    private DefaultListModel<Person> listModel;
    private PersonDAO personDAO;

    public CRUDApp() {
        JFrame frame = new JFrame("CRUD App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        listModel = new DefaultListModel<>();
        JList<Person> personList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(personList);

        frame.add(scrollPane, BorderLayout.CENTER);

        personDAO = new PersonDAO();

        JButton addButton = new JButton("Add Person");
        addButton.addActionListener(e -> addPerson());

        JButton updateButton = new JButton("Update Person");
        updateButton.addActionListener(e -> updatePerson(personList.getSelectedValue()));

        JButton deleteButton = new JButton("Delete Person");
        deleteButton.addActionListener(e -> deletePerson(personList.getSelectedValue()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        refreshList();

        frame.setVisible(true);
    }

    private void refreshList() {
        listModel.clear();
        List<Person> people = personDAO.getAllPeople();
        for (Person person : people) {
            listModel.addElement(person);
        }
    }

    private void addPerson() {
        String name = JOptionPane.showInputDialog("Enter name:");
        if (name != null && !name.trim().isEmpty()) {
            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter age:"));
            Person newPerson = new Person(name, age);
            personDAO.createPerson(newPerson);
            refreshList();
        }
    }

    private void updatePerson(Person selectedPerson) {
        if (selectedPerson != null) {
            String newName = JOptionPane.showInputDialog("Enter new name:", selectedPerson.getName());
            if (newName != null && !newName.trim().isEmpty()) {
                int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter new age:", selectedPerson.getAge()));
                selectedPerson.setName(newName);
                selectedPerson.setAge(newAge);
                personDAO.updatePerson(selectedPerson);
                refreshList();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a person to update.");
        }
    }

    private void deletePerson(Person selectedPerson) {
        if (selectedPerson != null) {
            int option = JOptionPane.showConfirmDialog(null, "Do you want to delete " + selectedPerson.getName() + "?");
            if (option == JOptionPane.YES_OPTION) {
                personDAO.deletePerson(selectedPerson.getId());
                refreshList();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a person to delete.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CRUDApp::new);
    }
}
