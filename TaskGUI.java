import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Scanner;

class FrameClass extends JFrame{
    JButton button, lazyButton;
    JLabel label, doneLabel, notDoneLabel, totalLabel, taskLabel, finishLabel, notFinishLabel;
    JPanel panel;
    String getLabelInput;
    int done = 0, notDone = 0, total = 0;
    Scanner scanner = new Scanner(System.in);
    FrameClass(){
        System.out.println("Enter the name of the task:");
        getLabelInput = scanner.next();
        label = new JLabel(getLabelInput);
        doneLabel = new JLabel();
        notDoneLabel = new JLabel();
        taskLabel = new JLabel("Task");
        finishLabel = new JLabel("Finish");
        notFinishLabel = new JLabel("Not Finish");
        JLabel infoTotalLabel = new JLabel("Total Days");
        totalLabel = new JLabel();
        button = new JButton("+");
        lazyButton = new JButton("-");
        panel = new JPanel();
        JPanel infoPanel = new JPanel();

        infoPanel.add(taskLabel);
        infoPanel.add(finishLabel);
        infoPanel.add(notFinishLabel);
        infoPanel.add(infoTotalLabel);

        panel.add(label);
        panel.add(button);
        panel.add(doneLabel);
        panel.add(lazyButton);
        panel.add(notDoneLabel);
        panel.add(totalLabel);

        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Add components to the content pane using BorderLayout
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(infoPanel, BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.CENTER);

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                done++;
                total=done+notDone;
                String doneString= Integer.toString(done);
                String notDoneString =Integer.toString(notDone);
                String totalString=Integer.toString(total);
                doneLabel.setText(doneString);
                notDoneLabel.setText(notDoneString);
                totalLabel.setText(totalString);
            }
        });
        lazyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                notDone++;
                total=notDone+done;
                String doneString= Integer.toString(done);
                String notDoneString =Integer.toString(notDone);
                String totalString=Integer.toString(total);
                doneLabel.setText(doneString);
                notDoneLabel.setText(notDoneString);
                totalLabel.setText(totalString);
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,650);
        this.setVisible(true);
    }
}

public class TaskGUI{
    public static void main(String args[]){
        new FrameClass();
    }
}
