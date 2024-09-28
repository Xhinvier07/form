// Importing necessary packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * RegistrationForm
 */
public class Main extends JFrame {

    // Declare all components for the form
    private JTextField nameField, addressField, emailField, contactField;
    private JComboBox<String> courseBox, yearBox;
    private JRadioButton maleRadio, femaleRadio;
    private JButton submitButton, clearButton, exitButton;

    // Define custom font
    private Font labelFont = new Font("Montserrat", Font.BOLD, 14);
    private Font fieldFont = new Font("Montserrat", Font.PLAIN, 12);

    // Define colors
    private Color primaryColor = new Color(0, 26, 52); // Dark Blue
    private Color secondaryColor = new Color(0, 57, 120); // Blue
    private Color buttonColor = new Color(0, 120, 88); // Green
    private Color dangerColor = new Color(120, 0, 0); // Red

    /**
     * Constructor to initialize
     */
    public Main() {
        // Title for the frame
        setTitle("Student Registration Form");

        // Set layout as GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Title label
        JLabel titleLabel = new JLabel("Student Registration Form");
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 18));
        titleLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Reset gridwidth next components
        gbc.gridwidth = 1;

        // Labels and input fields for the form
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        add(nameLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        nameField = new JTextField(20);
        nameField.setFont(fieldFont);
        add(nameField, gbc);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setFont(labelFont);
        courseLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        add(courseLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] courses = {"","BSCSSE", "BSCSDS", "BSITWMA", "BSITAGD", "BSITBA", "BMA","BSCPE","BSME","BSEE","BSECE","BSCE"};
        courseBox = new JComboBox<>(courses);
        courseBox.setFont(fieldFont);
        add(courseBox, gbc);

        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(labelFont);
        yearLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        add(yearLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] years = {"","1st Year", "2nd Year", "3rd Year", "4th Year"};
        yearBox = new JComboBox<>(years);
        yearBox.setFont(fieldFont);
        add(yearBox, gbc);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        genderLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        add(genderLabel, gbc);
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maleRadio = new JRadioButton("Male");
        maleRadio.setFont(fieldFont);
        femaleRadio = new JRadioButton("Female");
        femaleRadio.setFont(fieldFont);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        add(genderPanel, gbc);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 5;
        add(addressLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        addressField = new JTextField(20);
        addressField.setFont(fieldFont);
        add(addressField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 6;
        add(emailLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        emailField = new JTextField(20);
        emailField.setFont(fieldFont);
        add(emailField, gbc);

        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setFont(labelFont);
        contactLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 7;
        add(contactLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        contactField = new JTextField(20);
        contactField.setFont(fieldFont);
        add(contactField, gbc);

        // Panel for buttons (Submit, Clear, Exit)
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Submit button with action listener
        submitButton = new JButton("Submit");
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(labelFont);
        buttonPanel.add(submitButton);

        // Clear button to reset the form
        clearButton = new JButton("Clear");
        clearButton.setBackground(secondaryColor);
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(labelFont);
        buttonPanel.add(clearButton);

        // Exit button to close the form
        exitButton = new JButton("Exit");
        exitButton.setBackground(dangerColor);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(labelFont);
        buttonPanel.add(exitButton);

        add(buttonPanel, gbc);

        // Submit button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle form submission
                String name = nameField.getText().trim();
                String course = (String) courseBox.getSelectedItem();
                String year = (String) yearBox.getSelectedItem();
                String gender = maleRadio.isSelected() ? "Male" : femaleRadio.isSelected() ? "Female" : "";
                String address = addressField.getText().trim();
                String email = emailField.getText().trim();
                String contact = contactField.getText().trim();

                // Check for empty fields
                if (name.isEmpty() || course.isEmpty() || year.isEmpty() || gender.isEmpty() ||
                        address.isEmpty() || email.isEmpty() || contact.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return; // Exit the method if validation fails
                }

                // Display form data in a dialog
                JOptionPane.showMessageDialog(null, "Registration Successful!\n" +
                        "Name: " + name + "\nCourse: " + course + "\nYear: " + year +
                        "\nGender: " + gender + "\nAddress: " + address + "\nEmail: " + email +
                        "\nContact: " + contact);
            }
        });

        // Clear button action to reset all fields
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                courseBox.setSelectedIndex(0);
                yearBox.setSelectedIndex(0);
                genderGroup.clearSelection();
                addressField.setText("");
                emailField.setText("");
                contactField.setText("");
            }
        });

        // Exit button action to close the form
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        // Set frame size and visibility
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Main method to run the registration form
     *
     * @param args
     */
    public static void main(String[] args) {
        new Main();
    }
}
