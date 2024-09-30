// Importing necessary packages
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

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

    private static final String BACKGROUND_MUSIC = "src/bg.wav";
    private static final String KEY_SOUND = "src/keyb.wav";
    private static final String POP_SOUND = "src/pop.wav";
    private static final String ERROR_SOUND = "src/error.wav";
    private static final String SUCCESS_SOUND = "src/yehey.wav";
    private static final String CLEAR_SOUND = "src/meow.wav";

    /**
     * Constructor to initialize
     */
    public Main() {
        // Title for the frame
        setTitle("AGILE - Student Registration Form");

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

        // Create form fields with placeholders
        createFormFields(gbc);

        // Panel for buttons (Submit, Clear, Exit)
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Create buttons
        createButtons(buttonPanel);

        add(buttonPanel, gbc);

        // Play background music
        playSound(BACKGROUND_MUSIC, true);

        // Play key press sound
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> playSound(KEY_SOUND, false), AWTEvent.KEY_EVENT_MASK);

        // Play pop sound if radio button or dropdown is selected
        ActionListener listener = e -> playSound(POP_SOUND, false);
        maleRadio.addActionListener(listener);
        femaleRadio.addActionListener(listener);
        courseBox.addActionListener(listener);
        yearBox.addActionListener(listener);

        // Set frame size and visibility
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createFormFields(GridBagConstraints gbc) {
        // Name Field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
        add(nameLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        nameField = createTextField("Enter your name");
        add(nameField, gbc);

        // Course Field
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setFont(labelFont);
        courseLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        add(courseLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] courses = {"", "BSCSSE", "BSCSDS", "BSITWMA", "BSITAGD", "BSITBA", "BMA", "BSCPE", "BSME", "BSEE", "BSECE", "BSCE"};
        courseBox = new JComboBox<>(courses);
        courseBox.setFont(fieldFont);
        add(courseBox, gbc);

        // Year Field
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(labelFont);
        yearLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        add(yearLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] years = {"", "1st Year", "2nd Year", "3rd Year", "4th Year"};
        yearBox = new JComboBox<>(years);
        yearBox.setFont(fieldFont);
        add(yearBox, gbc);

        // Gender Field
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

        // Address Field
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 5;
        add(addressLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        addressField = createTextField("Enter your address");
        add(addressField, gbc);

        // Email Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 6;
        add(emailLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        emailField = createTextField("email@example.com");
        add(emailField, gbc);

        // Contact Field
        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setFont(labelFont);
        contactLabel.setForeground(primaryColor);
        gbc.gridx = 0; gbc.gridy = 7;
        add(contactLabel, gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        contactField = createTextField("ex. 09123456789");
        add(contactField, gbc);
    }

    private JTextField createTextField(String placeholder) {
        JTextField textField = new JTextField(20);
        textField.setFont(fieldFont);
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });

        return textField;
    }

    private void createButtons(JPanel buttonPanel) {
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

        // Submit button action
        submitButton.addActionListener(e -> {
            if (validateInputs()) {
                String name = nameField.getText().trim();
                String course = (String) courseBox.getSelectedItem();
                String year = (String) yearBox.getSelectedItem();
                String gender = maleRadio.isSelected() ? "Male" : "Female";
                String address = addressField.getText().trim();
                String email = emailField.getText().trim();
                String contact = contactField.getText().trim();

                playSound(SUCCESS_SOUND, false);

                // Display form data in a dialog
                JOptionPane.showMessageDialog(null, "Registration Successful!\n" +
                        "Name: " + name + "\nCourse: " + course + "\nYear: " + year +
                        "\nGender: " + gender + "\nAddress: " + address + "\nEmail: " + email +
                        "\nContact: " + contact);
            }
        });

        // Clear button action to reset all fields
        clearButton.addActionListener(e -> {
            playSound(CLEAR_SOUND, false);
            nameField.setText("Enter your name");
            addressField.setText("Enter your address");
            emailField.setText("Enter your email");
            contactField.setText("Enter your contact number");
            courseBox.setSelectedIndex(0);
            yearBox.setSelectedIndex(0);
            maleRadio.setSelected(false);
            femaleRadio.setSelected(false);
        });

        // Exit button action to close the form
        exitButton.addActionListener(e -> System.exit(0)); // Exit the application
    }

    private boolean validateInputs() {
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
            playSound(ERROR_SOUND, false);
            JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false; // Validation failed
        }

        // Validate email format
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            playSound(ERROR_SOUND, false);
            JOptionPane.showMessageDialog(null, "Please enter a valid email address.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false; // Validation failed
        }

        // Validate contact number (assumed to be numeric and of length 10)
        if (!contact.matches("\\d{11}")) {
            playSound(ERROR_SOUND, false);
            JOptionPane.showMessageDialog(null, "Contact number must be 11 digits long.", "Warning", JOptionPane.WARNING_MESSAGE);
            return false; // Validation failed
        }

        return true; // Validation passed
    }

    private void playSound(String filePath, boolean loop) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(filePath)));
                if (loop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    clip.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Main method to run the registration form
     */
    public static void main(String[] args) {
        new Main();
    }
}