import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Register {
    // frame
    private JFrame frame;
    // text fields - grade
    private JTextField grade;
    // button
    private JButton register;

    // constructor
    public Register() {
        // create frame
        frame = new JFrame("Register");
        // create text fields
        grade = new JTextField(10);
        // size
        grade.setSize(100, 50);
        // placeholder
        grade.setText("Grade");
        // create button
        register = new JButton("Register");
        register.setSize(100, 50);

        // call method register when button is clicked
        register.addActionListener(e -> register());

        // add components to frame
        frame.add(grade);
        frame.add(register);

        // positioning

        // grade - top left
        grade.setBounds(10, 10, 100, 50);

        // register - top left + 50
        register.setBounds(10, 60, 100, 50);

        // set frame properties
        frame.setSize(500, 300);
        frame.setLayout(null);
    }

    // show
    public void show() {
        frame.setVisible(true);
    }

    // register - grade is below 10 set to "F", between 10 and 20 set to "D",
    // between 20 and 30 set to "C", between 30 and 40 set to "B", between 40 and 50
    // set to "A" - show pop up with grade and print
    private void register() {
        // get grade
        String gradeString = grade.getText();
        // convert to int
        int gradeInt = Integer.parseInt(gradeString);
        // if grade is below 10
        if (gradeInt < 10) {
            // set grade to "F"
            gradeString = "F";
        }
        // if grade is between 10 and 20
        else if (gradeInt >= 10 && gradeInt <= 20) {
            // set grade to "D"
            gradeString = "D";
        }
        // if grade is between 20 and 30
        else if (gradeInt >= 20 && gradeInt <= 30) {
            // set grade to "C"
            gradeString = "C";
        }
        // if grade is between 30 and 40
        else if (gradeInt >= 30 && gradeInt <= 40) {
            // set grade to "B"
            gradeString = "B";
        }
        // if grade is between 40 and 50
        else if (gradeInt >= 40 && gradeInt <= 50) {
            // set grade to "A"
            gradeString = "A";
        }
        // create pop up
        JFrame popUp = new JFrame("Grade");
        // create label
        JLabel label = new JLabel("Grade: " + gradeString);

        // ok button
        JButton ok = new JButton("OK");
        // size
        ok.setSize(100, 50);
        // position - bottom
        ok.setBounds(10, 110, 100, 50);
        ok.addActionListener(e -> popUp.dispose());
        // size
        label.setSize(100, 50);
        // position
        label.setBounds(10, 10, 100, 50);
        // add label to pop up
        popUp.add(label);
        // add ok button to pop up
        popUp.add(ok);
        // set pop up properties
        popUp.setSize(300, 200);
        popUp.setLayout(null);
        popUp.setVisible(true);
    }

}
