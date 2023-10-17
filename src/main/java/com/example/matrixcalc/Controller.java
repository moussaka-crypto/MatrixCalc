package com.example.matrixcalc;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    private TextField x11, x12, x13,
                      x21, x22, x23,
                      x31, x32, x33;

    @FXML
    private TextField y11, y12, y13,
                      y21, y22, y23,
                      y31, y32, y33;


    @FXML
    private Text z11, z12, z13,
                 z21, z22, z23,
                 z31, z32, z33;

    @FXML
    private Text messageText;

    private TextField[][] textMatrix;
    private TextField[][] textMatrix2;
    private Text[][] result;
    private int[][] matrix;
    private int[][] matrix2;

    @FXML
    void initialize() {
        matrix = new int[3][3];
        matrix2 = new int[3][3];
        textMatrix = new TextField[3][3];
        textMatrix2 = new TextField[3][3];
        result = new Text[3][3];

        populateArray(textMatrix, "x", TextField.class);
        populateArray(textMatrix2, "y", TextField.class);
        populateArray(result, "z", Text.class);
    }

    // template methods evala
    private <T> void populateArray(T[][] array, String prefix, Class<T> fieldType) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String fieldName = prefix + (i + 1) + (j + 1);
                try {
                    array[i][j] = fieldType.cast(this.getClass().getDeclaredField(fieldName).get(this));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean numberFormatChecker(String text) {
        String regularExpression = "[-+]?[0-9]*\\.?[0-9]+$";
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    private void parseText() {
        messageText.setText("");
        int a = 0;
        while (a < 9) {
            // makes a loop through a row of matrix
            for (int x = 0; x < 3; x++) {
                // makes a loop through a column
                for (int y = 0; y < 3; y++) {
                    if (textMatrix[x][y].getText().isEmpty()) {
                        textMatrix[x][y].setText("0");
                    }
                    if (textMatrix2[x][y].getText().isEmpty()) {
                        textMatrix2[x][y].setText("0");
                    }
                    if (!numberFormatChecker(textMatrix[x][y].getText())) {
                        messageText.setText("Input in the first matrix is not a number.");
                    }
                    if (!numberFormatChecker(textMatrix2[x][y].getText())) {
                        messageText.setText("Input in the second matrix is not a number.");
                    }
                    matrix[x][y] = Integer.parseInt(textMatrix[x][y].getText());
                    matrix2[x][y] = Integer.parseInt(textMatrix2[x][y].getText());
                    a++;
                }
            }
        }
    }

    @FXML
    public void add()
            throws NoSuchFieldException, IllegalAccessException {
        parseText();
        int[][] sum = new int[3][3];
        // looping through rows
        for (int x = 0; x < this.matrix.length; x++) {
            // looping through columns
            for (int y = 0; y < this.matrix[x].length; y++) {
                sum[x][y] = this.matrix[x][y] + matrix2[x][y];
                result[x][y].setText(Integer.toString(sum[x][y]));
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String fieldName = "z" + (i + 1) + (j + 1);
                this.getClass().getDeclaredField(fieldName).set(this, result[i][j]);
            }
        }
    }

    @FXML
    public void multiply()
            throws NoSuchFieldException, IllegalAccessException{
        parseText();
        // creates a new 2D variable for the result of multiplying of two matrices
        int[][] output = new int[3][3];
        // looping through rows
        for (int x = 0; x < output.length; x++) {
            // looping through column
            for (int y = 0; y < output.length; y++) {
                // integer to fill each intersection between column and row
                output[x][y] = 0;
                // fills each position of the matrix
                for (int a = 0; a < output.length; a++) {
                    output[x][y] += matrix[x][a] * matrix2[a][y];
                    result[x][y].setText(Integer.toString(output[x][y]));
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String fieldName = "z" + (i + 1) + (j + 1);
                this.getClass().getDeclaredField(fieldName).set(this, result[i][j]);
            }
        }
    }
}

// Javata basi mastiqta