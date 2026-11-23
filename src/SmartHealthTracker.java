import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.DenseInstance;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.Evaluation;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SmartHealthTracker {

    private static RandomForest model;
    private static Instances dataset;
    private static Normalize normalize;

    // GUI fields
    private static JTextField pregField, glucoseField, bpField, skinField,
            insulinField, bmiField, dpfField, ageField;

    public static void main(String[] args) throws Exception {
        // Load dataset
        DataSource source = new DataSource("diabetes.arff");
        dataset = source.getDataSet();
        dataset.setClassIndex(dataset.numAttributes() - 1);

        // Apply normalization filter
        normalize = new Normalize();
        normalize.setInputFormat(dataset);
        dataset = Filter.useFilter(dataset, normalize);

        // Train RandomForest
        model = new RandomForest();
        model.setNumIterations(500); // more trees for better accuracy
        model.buildClassifier(dataset);

        // Evaluate model
        Evaluation eval = new Evaluation(dataset);
        eval.crossValidateModel(model, dataset, 10, new Random(1));
        System.out.println("Accuracy: " + (1 - eval.errorRate()) * 100 + "%");
        System.out.println(eval.toSummaryString());

        // Launch GUI
        SwingUtilities.invokeLater(SmartHealthTracker::createGUI);
    }

    private static void createGUI() {
        JFrame frame = new JFrame("Smart Health Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new JLabel("Smart Health Tracker", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        frame.add(title, BorderLayout.NORTH);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pregField = new JTextField(); glucoseField = new JTextField();
        bpField = new JTextField(); skinField = new JTextField();
        insulinField = new JTextField(); bmiField = new JTextField();
        dpfField = new JTextField(); ageField = new JTextField();

        inputPanel.add(new JLabel("Pregnancies:")); inputPanel.add(pregField);
        inputPanel.add(new JLabel("Glucose:")); inputPanel.add(glucoseField);
        inputPanel.add(new JLabel("Blood Pressure:")); inputPanel.add(bpField);
        inputPanel.add(new JLabel("Skin Thickness:")); inputPanel.add(skinField);
        inputPanel.add(new JLabel("Insulin:")); inputPanel.add(insulinField);
        inputPanel.add(new JLabel("BMI:")); inputPanel.add(bmiField);
        inputPanel.add(new JLabel("Diabetes Pedigree Function:")); inputPanel.add(dpfField);
        inputPanel.add(new JLabel("Age:")); inputPanel.add(ageField);

        frame.add(inputPanel, BorderLayout.CENTER);

        // Buttons
        JButton predictBtn = new JButton("Predict");
        JButton resetBtn = new JButton("Reset");
        JButton exitBtn = new JButton("Exit");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(predictBtn);
        buttonPanel.add(resetBtn);
        buttonPanel.add(exitBtn);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        // Predict button action
        predictBtn.addActionListener(e -> {
            try {
                // Input validation for each field
                if (pregField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter Pregnancies value.");
                    return;
                }
                if (glucoseField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter Glucose value.");
                    return;
                }
                if (bpField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter Blood Pressure value.");
                    return;
                }
                if (skinField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter Skin Thickness value.");
                    return;
                }
                if (insulinField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter Insulin value.");
                    return;
                }
                if (bmiField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter BMI value.");
                    return;
                }
                if (dpfField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter Diabetes Pedigree Function value.");
                    return;
                }
                if (ageField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter Age value.");
                    return;
                }
                
                double[] values = new double[dataset.numAttributes()];
                values[0] = Double.parseDouble(pregField.getText());
                values[1] = Double.parseDouble(glucoseField.getText());
                values[2] = Double.parseDouble(bpField.getText());
                values[3] = Double.parseDouble(skinField.getText());
                values[4] = Double.parseDouble(insulinField.getText());
                values[5] = Double.parseDouble(bmiField.getText());
                values[6] = Double.parseDouble(dpfField.getText());
                values[7] = Double.parseDouble(ageField.getText());

                Instance newPatient = new DenseInstance(1.0, values);
                newPatient.setDataset(dataset);

                normalize.input(newPatient);
                newPatient = normalize.output();

                double prediction = model.classifyInstance(newPatient);
                String result = (int) prediction == 0 ? "No Diabetes" : "Diabetes Detected";

                // ✅ Show popup
                JOptionPane.showMessageDialog(frame,
                        "Predicted Outcome: " + result,
                        "Prediction Result",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "Error: " + ex.getMessage(),
                        "Prediction Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Reset button action
        resetBtn.addActionListener(e -> {
            pregField.setText(""); glucoseField.setText(""); bpField.setText("");
            skinField.setText(""); insulinField.setText(""); bmiField.setText("");
            dpfField.setText(""); ageField.setText("");
        });

        // Exit button action
        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}
