package com.rahul.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    private EditText mEtDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtDisplay = findViewById(R.id.etInput);

        mEtDisplay.setShowSoftInputOnFocus(false);
    }

    private void updateInput(String input){
        String oldStr = mEtDisplay.getText().toString();
        int curPos = mEtDisplay.getSelectionStart();
        String leftStr = oldStr.substring(0,curPos);
        String rightStr = oldStr.substring(curPos);

        if(getString(R.string.display).equals(mEtDisplay.getText().toString())){
            mEtDisplay.setText(input);
        }
        else {
            mEtDisplay.setText(String.format("%s%s%s", leftStr,input,rightStr));
        }
        mEtDisplay.setSelection(curPos+1);


    }

    public void onClickZero( View view){
        updateInput("0");
    }
    public void onClickOne( View view){
        updateInput("1");
    }
    public void onClickTwo( View view){
        updateInput("2");
    }
    public void onClickThree( View view){
        updateInput("3");
    }
    public void onClickFour( View view){
        updateInput("4");
    }
    public void onClickFive( View view){
        updateInput("5");
    }
    public void onClickSix( View view){
        updateInput("6");
    }
    public void onClickSeven( View view){
        updateInput("7");
    }
    public void onClickEight( View view){
        updateInput("8");
    }
    public void onClickNine( View view){
        updateInput("9");
    }
    public void onClickAdd( View view){
        updateInput("+");
    }
    public void onClickSub( View view){
        updateInput("-");
    }
    public void onClickMul( View view){
        updateInput("*");
    }
    public void onClickDiv( View view){
        updateInput("/");
    }
    public void onClickPoint( View view){
        updateInput(".");
    }

    public void onClickExponent( View view){
        updateInput("^");
    }

    public void onClickClr( View view){
        mEtDisplay.setText("");
    }

    public void onClickDel( View view){
        int cursorPos = mEtDisplay.getSelectionStart();
        int txtLen = mEtDisplay.getText().length();

        if (cursorPos!=0 && txtLen !=0 ){
            SpannableStringBuilder selection = (SpannableStringBuilder) mEtDisplay.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            mEtDisplay.setText(selection);
            mEtDisplay.setSelection(cursorPos-1);
        }

    }

    public void onClickParen( View view){
        int cursorPos = mEtDisplay.getSelectionStart();
        int openPar  = 0;
        int closePar= 0;
        int textLen = mEtDisplay.getText().length();
        for (int i=0;i<cursorPos;i++){
            if(mEtDisplay.getText().toString().substring(i,i+1).equals("("))
                openPar+=1;
            else if (mEtDisplay.getText().toString().substring(i,i+1).equals(")"))
                closePar+=1;
        }
        if (openPar ==closePar || mEtDisplay.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateInput("(");

        }
        else if (closePar < openPar &&  !mEtDisplay.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateInput(")");

        }
        mEtDisplay.setSelection(cursorPos+1);
    }

    public void onClickEqual( View view){
        String userExp = mEtDisplay.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression expression = new Expression(userExp);
        String result = String.valueOf(expression.calculate());

        mEtDisplay.setText(result);
        mEtDisplay.setSelection(result.length());
    }
}