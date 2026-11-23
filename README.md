# Smart Health Tracker

A desktop-based intelligent health monitoring and diabetes prediction application developed in Java using the Weka machine learning library and Java Swing GUI.

## 🩺 Features

- **Machine Learning Classification**: Utilizes the Random Forest algorithm (500 iterations/trees) for high predictive accuracy.
- **Data Preprocessing**: Implements unsupervised data normalization (`Normalize` filter) to standardize numerical attributes before classification.
- **Model Evaluation**: Evaluates performance using 10-fold cross-validation with accuracy metrics and evaluation summary.
- **Interactive Swing GUI**: User-friendly graphical interface with form validation to input clinical parameters and receive real-time prediction results.

## 📊 Dataset

The application is trained on the Pima Indians Diabetes Dataset (`diabetes.arff`), which includes the following clinical parameters:
- **Pregnancies**: Number of times pregnant
- **Glucose**: Plasma glucose concentration
- **Blood Pressure**: Diastolic blood pressure (mm Hg)
- **Skin Thickness**: Triceps skin fold thickness (mm)
- **Insulin**: 2-Hour serum insulin (mu U/ml)
- **BMI**: Body mass index (weight in kg/(height in m)^2)
- **Diabetes Pedigree Function**: Genetic diabetes predisposition score
- **Age**: Age in years
- **Outcome**: Binary class (0 = No Diabetes, 1 = Diabetes Detected)

## 🛠️ Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher (Java 21 recommended)
- **Weka Machine Learning Library**: Version 3.8+
  - `weka.jar` added to the project classpath
- **MTJ (Matrix Toolkits Java)**:
  - `mtj-1.0.4.jar` (optional, for linear algebra performance)

## 🚀 Getting Started

### Using Eclipse IDE
1. Clone the repository:
   ```bash
   git clone https://github.com/Ksairupesh/Smart-Health-Tracker.git
   ```
2. Open Eclipse and select **File** > **Import...** > **General** > **Existing Projects into Workspace**.
3. Select the cloned folder as the root directory.
4. Ensure `weka.jar` is configured in Java Build Path under **Libraries**.
5. Run `SmartHealthTracker.java` as a **Java Application**.

### Running from Terminal
```bash
# Compile (with weka.jar in classpath)
javac -cp ".;path/to/weka.jar" -d bin src/SmartHealthTracker.java

# Run
java -cp "bin;path/to/weka.jar;." SmartHealthTracker
```

## 👤 Author
- **Ksairupesh** - [GitHub Profile](https://github.com/Ksairupesh)
