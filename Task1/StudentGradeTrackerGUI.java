import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI extends JFrame {
    private ArrayList<Integer> grades = new ArrayList<>();
    private JTextArea outputArea;
    private JTextField gradeInput;

    public StudentGradeTrackerGUI() {
        setTitle("Student Grade Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input field and button
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Grade:"));
        gradeInput = new JTextField(5);
        inputPanel.add(gradeInput);
        JButton addButton = new JButton("Add Grade");
        inputPanel.add(addButton);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Summary button
        JButton summaryButton = new JButton("Show Summary");

        // Add listeners
        addButton.addActionListener(e -> addGrade());
        summaryButton.addActionListener(e -> showSummary());

        // Layout
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(summaryButton, BorderLayout.SOUTH);
    }

    private void addGrade() {
        try {
            int grade = Integer.parseInt(gradeInput.getText());
            if (grade < 0 || grade > 100) {
                JOptionPane.showMessageDialog(this, "Enter a grade between 0 and 100.");
                return;
            }
            grades.add(grade);
            outputArea.append("Grade added: " + grade + "\n");
            gradeInput.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Enter a number.");
        }
    }

    private void showSummary() {
        if (grades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No grades entered yet.");
            return;
        }

        int total = 0, highest = grades.get(0), lowest = grades.get(0);
        for (int grade : grades) {
            total += grade;
            if (grade > highest) highest = grade;
            if (grade < lowest) lowest = grade;
        }
        double average = (double) total / grades.size();

        outputArea.append("\n--- Summary ---\n");
        outputArea.append("Total Students: " + grades.size() + "\n");
        outputArea.append("Average Score: " + String.format("%.2f", average) + "\n");
        outputArea.append("Highest Score: " + highest + "\n");
        outputArea.append("Lowest Score: " + lowest + "\n");
        outputArea.append("-----------------\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentGradeTrackerGUI().setVisible(true));
    }
}
