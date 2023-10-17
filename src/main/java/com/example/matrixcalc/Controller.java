
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

        textMatrix[0][0] = x11;
        textMatrix[0][1] = x12;
        textMatrix[0][2] = x13;
        textMatrix[1][0] = x21;
        textMatrix[1][1] = x22;
        textMatrix[1][2] = x23;
        textMatrix[2][0] = x31;
        textMatrix[2][1] = x32;
        textMatrix[2][2] = x33;

        textMatrix2[0][0] = y11;
        textMatrix2[0][1] = y12;
        textMatrix2[0][2] = y13;
        textMatrix2[1][0] = y21;
        textMatrix2[1][1] = y22;
        textMatrix2[1][2] = y23;
        textMatrix2[2][0] = y31;
        textMatrix2[2][1] = y32;
        textMatrix2[2][2] = y33;

        result[0][0] = z11;
        result[0][1] = z12;
        result[0][2] = z13;
        result[1][0] = z21;
        result[1][1] = z22;
        result[1][2] = z23;
        result[2][0] = z31;
        result[2][1] = z32;
        result[2][2] = z33;
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
    public void add() {
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
        z11 = result[0][0];
        z12 = result[0][1];
        z13 = result[0][2];
        z21 = result[1][0];
        z22 = result[1][1];
        z23 = result[1][2];
        z31 = result[2][0];
        z32 = result[2][1];
        z33 = result[2][2];
    }

    @FXML
    public void multiply() {
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

        z11 = result[0][0];
        z12 = result[0][1];
        z13 = result[0][2];
        z21 = result[1][0];
        z22 = result[1][1];
        z23 = result[1][2];
        z31 = result[2][0];
        z32 = result[2][1];
        z33 = result[2][2];
    }
}
