import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

class TaskPanel extends JPanel {
    JTextField userField;
    JLabel taskLabel;
    JLabel taskInfo;
    Queue<String> taskQueue;
    File taskFile; 
    TaskPanel() {
        userField = new JTextField(10);
        taskLabel = new JLabel();
        taskInfo = new JLabel("Enter the Task:");
        taskQueue = new LinkedList<>();
        taskFile = new File("tasks.txt"); 
        loadTasks();

        userField.addActionListener(e -> {
            String newTask = userField.getText();
            taskQueue.offer(newTask);
            updateTaskLabel();
            userField.setText("");
            saveTasks(); 
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(taskInfo);
        inputPanel.add(userField);
        inputPanel.add(taskLabel);
        inputPanel.setLayout(new GridLayout(3, 1));
        this.add(inputPanel);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        taskLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                taskLabel.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                taskLabel.setForeground(UIManager.getColor("Label.foreground"));
            }
        });
    }

    public void updateTaskLabel() {
        StringBuilder sb = new StringBuilder("<html>Tasks:<br/>");
        for (String task : taskQueue) {
            sb.append("-").append(task).append("<br/>");
        }
        sb.append("<html>");
        taskLabel.setText(sb.toString());
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile))) {
            for (String task : taskQueue) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTasks() {
        if (taskFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(taskFile))) {
                String task;
                while ((task = reader.readLine()) != null) {
                    taskQueue.offer(task);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        updateTaskLabel();
    }
}

class TaskGUI extends JFrame {
    TaskGUI() {
        super("This is a Task GUI");
        TaskPanel taskPanel = new TaskPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(taskPanel);
        this.setLayout(new FlowLayout());
        this.setSize(600, 600);
        this.setVisible(true);
    }

    public static void main(String args[]) {
        new TaskGUI();
    }
}
