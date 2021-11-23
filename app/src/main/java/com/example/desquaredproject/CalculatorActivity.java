package com.example.desquaredproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    //Edit Text elements
    private EditText calculation;
    //Edit Text expressions, (curr = topEditText, res = botEditText)
    private String curr,res;
    //Numbers
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    //operations
    private Button btnAdd,btnSub,btnMul,btnDiv,btnEq,btnDot,btnC;
    private ImageButton btnDel;
    //Used to check if we are allowed to use an operator or a dot
    private boolean dot,operator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        calculation= (EditText) findViewById(R.id.calculation);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        setupVariables();
        setupButtons();
        setupListeners();
    }
    //sets cursor at the end of calculations EditText
    private void setCursor(){
       calculation.setSelection(calculation.getText().length());
    }

    private void setupVariables() {
        curr="";
        res="";
        dot=false;
        operator=false;
        calculation.setShowSoftInputOnFocus(false);
    }


    private void setupButtons() {
        btn0=(Button) findViewById(R.id.zeroBtn);
        btn1=(Button) findViewById(R.id.oneBtn);
        btn2=(Button) findViewById(R.id.twoBtn);
        btn3=(Button) findViewById(R.id.threeBtn);
        btn4=(Button) findViewById(R.id.fourBtn);
        btn5=(Button) findViewById(R.id.fiveBtn);
        btn6=(Button) findViewById(R.id.sixBtn);
        btn7=(Button) findViewById(R.id.sevenBtn);
        btn8=(Button) findViewById(R.id.eightBtn);
        btn9=(Button) findViewById(R.id.nineBtn);
        btnAdd=(Button) findViewById(R.id.plusBtn);
        btnSub=(Button) findViewById(R.id.minusBtn);
        btnMul=(Button) findViewById(R.id.multiplyBtn);
        btnDiv=(Button) findViewById(R.id.divideBtn);
        btnEq=(Button) findViewById(R.id.equalsBtn);
        btnDot=(Button) findViewById(R.id.dotBtn);
        btnDel=(ImageButton) findViewById(R.id.deleteBtn);
        btnC=(Button) findViewById(R.id.clearBtn);
    }

    //Adding number to Calculation EditText
    public void addNumber(String x){
        curr+=x;
        displayOne();
    }

    private void setupListeners() {
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber("9");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if its empty => 0., dot=true
                if(curr.isEmpty()||curr.charAt(curr.length()-1)==' '){
                    curr+="0.";
                    dot=true;
                    displayOne();
                }
                //check dot==false => .
                if(!dot){
                    curr+=".";
                    dot=true;
                    displayOne();
                }
            }
        });
        //Button Clear
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               clear();
            }
        });
        //Button Delete (Backspace)
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace();
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set dot=>false
                dot=false;
                //check if curr is !empty
                if(!curr.isEmpty()){
                    //check if last digit=.
                    if(curr.charAt(curr.length() - 1) == '.'){
                        backspace();
                    }
                    //check if operator==false=>append operator to curr
                    if(operator==false){
                        curr+=" ÷ ";
                        operator=true;
                    }
                    //If there is an operator, replace it with " ÷ "
                    else{
                        changeOperator(" ÷ ");;
                    }
                }
                //if there is not a number at calculation EditText
                else{
                    showToast("Please add a number first");
                }
                displayOne();
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set dot=>false
                dot=false;
                //check if curr is !empty
                if(!curr.isEmpty()){
                    //check if last digit=.
                    if(curr.charAt(curr.length() - 1) == '.'){
                        backspace();
                    }
                    //check if operator==false=>append operator to curr
                    if(operator==false){
                        curr+=" × ";
                        operator=true;
                    }
                    //If there is an operator, replace it with " × "
                    else{
                        changeOperator(" × ");
                    }
                }
                //if there is not a number at calculation EditText
                else{
                    showToast("Please add a number first");
                }
                displayOne();
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set dot=>false
                dot=false;
                //check if curr is !empty
                if(!curr.isEmpty()){
                    //check if last digit=.
                    if(curr.charAt(curr.length() - 1) == '.'){
                        backspace();
                    }
                    //check if operator==false=>append operator to curr
                    if(operator==false){
                        curr+=" - ";
                        operator=true;
                    }
                    //If there is an operator, replace it with " - "
                    else{
                        changeOperator(" - ");
                    }
                }
                //if there is not a number at calculation EditText
                else{
                    showToast("Please add a number first");
                }
                displayOne();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set dot=>false
                dot=false;
                //check if curr is !empty
                if(!curr.isEmpty()){
                    //check if last digit=.
                    if(curr.charAt(curr.length() - 1) == '.'){
                        backspace();
                    }
                    //check if operator==false=>append operator to curr
                    if(operator==false){
                        curr+=" + ";
                        operator=true;
                    }
                    //If there is an operator, replace it with " - "
                    else{
                        changeOperator(" + ");
                    }
                }
                //if there is not a number at calculation EditText
                else{
                    showToast("Please add a number first");
                }
                displayOne();
            }
        });
        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curr.isEmpty()){
                    showToast("Please insert an expression to calculate");
                }

                //check if last digit !operator
                else if(operator && curr.charAt(curr.length() - 1) != ' '){

                    String [] tokens = curr.split(" ");
                    switch(tokens[1].charAt(0)){
                        case '÷':
                            res=Double.toString(Double.parseDouble(tokens[0]) / Double.parseDouble(tokens[2]));
                            break;
                        case'×':
                            res=Double.toString(Double.parseDouble(tokens[0]) * Double.parseDouble(tokens[2]));
                            break;
                        case '+':
                            res=Double.toString(Double.parseDouble(tokens[0]) + Double.parseDouble(tokens[2]));
                            break;
                        case '-':
                            res=Double.toString(Double.parseDouble(tokens[0]) - Double.parseDouble(tokens[2]));
                            break;
                    }

                    //if the last char is '.', remove it
                    //3.4/2. -> 3.4/2
                    if( curr.charAt(curr.length() - 1) == '.'){
                        curr=curr.substring(0,curr.length()-1);
                    }

                    //result = current input + " = " + result
                    curr=res;

                    //resetting variables for new calculations

                    displayOne();
                    operator=false;
                    dot=false;

                }
                //if there is not a second number
                else if(curr.charAt(curr.length()-1)==' '){
                    showToast("Please add a second number");
                }
            }
        });
    }

    //Displaying curr to first EditText
    public void displayOne(){
        calculation.setText(curr);
        setCursor();
    }

    //Clears EditTexts and variables
    public void clear(){
        curr = "";
        res="";
        dot=false;
        operator=false;
        displayOne();

    }
    //Deletes last char
    public void backspace(){
        //if curr !empty => delete last char
        if(!curr.isEmpty()){
            //if curr.last == "." -> dot=false
            if(curr.charAt(curr.length() - 1) == '.'){
                dot=false;
            }
            //if there is operator => delete 3 digits & set operator=false
            if(curr.charAt(curr.length() - 1) == ' '){
                curr=curr.substring(0,curr.length()-3);
                operator=false;
            }
            else
            curr=curr.substring(0,curr.length()-1);
        }
        displayOne();
    }
    //changes Operator
    public void changeOperator(String newOperator){
        char lastItem = curr.charAt(curr.length()-1);
        //if there is another operator in the expression, change it
        if(lastItem==' ') {
            curr = curr.substring(0, curr.length() - 3);
            curr += newOperator;
            operator = true;
        }
        //if last char is number or '.' , Don't change it
        else{
            showToast("You can only do one operation at a time");
        }

    }
    //Displays a Toast on the screen
    public void showToast(String message){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        toast.show();
    }


}
