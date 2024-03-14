import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

class TaskGUI extends JFrame {
    int done = 0, notDone = 0, total = 0;
    JTextField taskField;
    JLabel taskLabel, infoTaskLabel, doneLabel, notDoneLabel, totalLabel;
    JButton doneButton, notDoneButton, saveButton;
    JPanel gridPanel, taskPanel, buttonPanel;

    TaskGUI() {
        infoTaskLabel = new JLabel("Enter the Task:");
        taskField = new JTextField(20);
        taskLabel = new JLabel();
        doneButton = new JButton("Done for the Day");
        notDoneButton = new JButton("Not Done for the Day");
        saveButton = new JButton("Save");
        doneLabel = new JLabel();
        notDoneLabel = new JLabel();
        totalLabel = new JLabel();
        JLabel tLabel = new JLabel("Total Number of Days Done:");
        doneLabel.setBackground(new Color(120, 140, 240));
        notDoneLabel.setBackground(new Color(90, 80, 60));
        totalLabel.setBackground(new Color(1, 10, 100));

        taskField.addActionListener(e -> {
            taskLabel.setText(taskField.getText());
        });

        doneButton.addActionListener(e -> {
            done++;
            total = done + notDone;
            String doneS = String.valueOf(done);
            String totalS = String.valueOf(total);
            doneLabel.setText(doneS);
            totalLabel.setText(totalS);
        });

        notDoneButton.addActionListener(e -> {
            notDone++;
            total = done + notDone;
            String notDoneS = String.valueOf(notDone);
            String totalS = String.valueOf(total);
            notDoneLabel.setText(notDoneS);
            totalLabel.setText(totalS);
        });

        saveButton.addActionListener(e -> {
            saveStateToFile();
        });

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(0, 2));
        gridPanel.add(infoTaskLabel);
        gridPanel.add(taskField);
        taskPanel = new JPanel();
        taskPanel.add(taskLabel, new BorderLayout().NORTH);
        buttonPanel = new JPanel();
        buttonPanel.add(doneButton);
        buttonPanel.add(doneLabel);
        buttonPanel.add(notDoneButton);
        buttonPanel.add(notDoneLabel);
        buttonPanel.add(tLabel);
        buttonPanel.add(totalLabel);
        buttonPanel.add(saveButton);
        buttonPanel.setLayout(new GridLayout(0, 6));
        taskPanel.setBackground(new Color(0, 38, 77));
        buttonPanel.setBackground(new Color(255, 153, 51));
        gridPanel.setBackground(new Color(255, 69, 0));

        
        loadStateFromFile();

        this.add(gridPanel);
        this.add(taskPanel);
        this.add(buttonPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.getContentPane().setBackground(new Color(255, 105, 180));
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

   
    private void saveStateToFile() {
        try (PrintWriter writer = new PrintWriter("task_state.txt")) {
            writer.println(taskField.getText());
            writer.println(done);
            writer.println(notDone);
            writer.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStateFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("task_state.txt"))) {
            taskLabel.setText(reader.readLine());
            done = Integer.parseInt(reader.readLine());
            notDone = Integer.parseInt(reader.readLine());
            total = Integer.parseInt(reader.readLine());
            doneLabel.setText(String.valueOf(done));
            notDoneLabel.setText(String.valueOf(notDone));
            totalLabel.setText(String.valueOf(total));
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        new TaskGUI();
    }
}