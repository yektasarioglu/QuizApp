package com.example.yekta.myquiz;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Database database = new Database();
    private ArrayList trueOption = new ArrayList();
    private int questionNo = 0;

    private ImageView imageView;
    private TextView questionNo_textView, textView;
    private RadioGroup radioGroup;
    private RadioButton[] radioButton = new RadioButton[4];

    private int selectedRadioButtonId;
    private RadioButton selectedRadioButton;

    private boolean isDone = false;
    private int correctAnswersNumber = 0;

    private LinearLayout linearLayout;
    private EditText editText;
    private Button button, button2;
    private String userAnswer;

    private CheckBox[] checkBox;

    private LinearLayout linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.inside);
        editText = new EditText(this);
        button2 = new Button(this);

        checkBox = new CheckBox[4];
        for (int i = 0; i < 4; i++) {
            checkBox[i] = new CheckBox(this);
        }

        radioButton[0] = (RadioButton) findViewById(R.id.OptionA);
        radioButton[1] = (RadioButton) findViewById(R.id.OptionB);
        radioButton[2] = (RadioButton) findViewById(R.id.OptionC);
        radioButton[3] = (RadioButton) findViewById(R.id.OptionD);

        trueOption.add(radioButton[1].getId());
        trueOption.add(radioButton[0].getId());
        trueOption.add(radioButton[0].getId());
        trueOption.add(radioButton[3].getId());
        trueOption.add(radioButton[0].getId());
        trueOption.add("No");


        for (int i = 0; i < radioButton.length; i++) {
            radioButton[i].setText(database.option[questionNo][i]);
        }

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        textView = (TextView) findViewById(R.id.question);
        button = (Button) findViewById(R.id.button);
        textView.setText(database.question[questionNo]);

        questionNo_textView = (TextView) findViewById(R.id.questionNo);
        questionNo_textView.setText("Q" + (questionNo + 1));

        imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.vader));

        linearLayout2 = (LinearLayout) findViewById(R.id.linear);
        linearLayout2.setBackgroundColor(Color.BLACK);
    }

    private void checkForMultipleOptions() {
        int checked = 0;
        for (int i = 0; i < radioButton.length; i++) {
            if (checked > 1) {
                checked = radioButton.length;
                Toast.makeText(this, "Check only one option!", Toast.LENGTH_LONG).show();
            }
            if (radioButton[i].isChecked()) {
                checked++;
            }
        }
    }

    public void checked(View view) {

        selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonId);

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.OptionA:
                if (checked)
                    Toast.makeText(this, radioButton[0].getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.OptionB:
                if (checked)
                    Toast.makeText(this, radioButton[1].getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.OptionC:
                if (checked)
                    Toast.makeText(this, radioButton[2].getText().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.OptionD:
                if (checked)
                    Toast.makeText(this, radioButton[3].getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void proceed(View view) {

        if (!isDone) {
            radioGroup.setVisibility(View.VISIBLE);
            linearLayout.removeView(editText);
            linearLayout.removeView(button2);
            if (questionNo != database.questionNumber - 1) {
                if (selectedRadioButtonId == 0) {
                    Toast.makeText(this, "Select a option", Toast.LENGTH_SHORT).show();
                } else if (trueOption.get(questionNo).equals(selectedRadioButtonId)) {
                    Toast.makeText(this, "True :)", Toast.LENGTH_SHORT).show();
                    Log.i(getClass().toString(), "true and questionNo is : " + questionNo);
                    correctAnswersNumber++;
                    questionNo++;
                } else if (!(trueOption.get(questionNo).equals(selectedRadioButtonId))) {
                    Toast.makeText(this, "False :/", Toast.LENGTH_SHORT).show();
                    Log.i(getClass().toString(), "false and questionNo is: " + questionNo);
                    questionNo++;
                }
            }


            switch (questionNo) {
                case 1:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.obi));
                    break;
                case 2:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.yoda));
                    break;
                case 3:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.sidious));
                    break;
                case 4:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.luke));
                    break;
                case 5:
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.mace));
                    break;
            }


            if (!(questionNo == 5)) {
                for (int i = 0; i < radioButton.length; i++) {
                    radioButton[i].setText(database.option[questionNo][i]);
                    Log.i(getClass().toString(), "questionNo: " + questionNo);
                }
            } else {
                textView.setText(database.question[questionNo]);
                radioGroup.setVisibility(View.GONE);
                editText.setLayoutParams(
                        new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.MATCH_PARENT,
                                100));
                editText.setHint("Type it!");
                editText.setTextColor(Color.argb(255, 183, 0, 0));
                editText.setHintTextColor(Color.argb(255, 183, 0, 0));
                editText.setBackgroundTintList(ColorStateList.valueOf(Color.argb(255, 183, 0, 0)));
                editText.setPadding(40, 0, 40, 0);
                linearLayout.addView(editText);
                button.setBackgroundColor(Color.BLACK);
                button.setTextColor(Color.BLACK);
                button2.setText("Check");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userAnswer = editText.getText().toString();
                        Log.i(getClass().toString(), "userAnswer is " + userAnswer);
                        if (trueOption.get(questionNo).toString().toLowerCase().equals(userAnswer.toLowerCase())) {
                            Toast.makeText(MainActivity.this, "It matches", Toast.LENGTH_SHORT).show();
                            correctAnswersNumber++;
                            isDone = true;
                        } else {
                            Toast.makeText(MainActivity.this, "Noooooooohhhh!", Toast.LENGTH_SHORT).show();
                            isDone = true;
                        }
                        if (isDone) {
                            textView.setTextSize(18);
                            button.setVisibility(View.VISIBLE);
                            button.setBackgroundColor(Color.GRAY);
                            button.setTextColor(Color.WHITE);
                            button2.setVisibility(View.INVISIBLE);
                            if (correctAnswersNumber <= 5) {
                                textView.setPadding(20, 0, 0, 0);
                                textView.setText("You are noob young padawan :)\n");
                                imageView.setImageDrawable(getResources().getDrawable(R.drawable.noob));
                            } else if (correctAnswersNumber == 6) {
                                textView.setPadding(40, 0, 0, 0);
                                textView.setText("You are true die hard fan :)\n");
                                imageView.setImageDrawable(getResources().getDrawable(R.drawable.diehardfan));
                            }


                            editText.setVisibility(View.INVISIBLE);
                            textView.append("You have " + correctAnswersNumber + " out of " + database.questionNumber + "\n" +
                                    "How was the quiz, rate it.");
                            textView.setGravity(Gravity.CENTER_HORIZONTAL);
                            questionNo_textView.setText("");
                            linearLayout.removeView(radioGroup);

                            for (int i = 0; i < 4; i++) {
                                linearLayout.removeView(radioButton[i]);
                            }

                            checkBox[0].setLayoutParams(new RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.MATCH_PARENT,
                                    50));
                            checkBox[0].setText("Funny");
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(checkBox[0].getLayoutParams());
                            layoutParams.setMargins(30, 0, 0, 200);

                            checkBox[1].setLayoutParams(new RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.MATCH_PARENT,
                                    50));
                            checkBox[1].setText("Bad");

                            checkBox[2].setLayoutParams(new RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.MATCH_PARENT,
                                    50));
                            checkBox[2].setText("Horrible");

                            checkBox[3].setLayoutParams(new RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.MATCH_PARENT,
                                    50));
                            checkBox[3].setText("Great");
                            checkBox[3].setPadding(20, 0, 37, 0);


                            for (int i = 0; i < 4; i++) {
                                checkBox[i].setButtonTintList(ColorStateList.valueOf(Color.RED));
                                checkBox[i].setLayoutParams(layoutParams);
                                checkBox[i].setTextColor(Color.WHITE);
                                linearLayout2.addView(checkBox[i]);
                            }
                        }
                    }
                });
                linearLayout.addView(button2);
            }


            if (!isDone) {
                questionNo_textView.setText("Q" + (questionNo + 1));
                textView.setText(database.question[questionNo]);
            }

            selectedRadioButton = null;
        }
    }
}
