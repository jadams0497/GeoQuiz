package com.example.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button myTrueButton;
    private Button myFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true)
    };

    private int mCurrentIndex = 0;
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        }
        else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResID();
        mQuestionTextView.setText(question);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        //int question = mQuestionBank[mCurrentIndex].getTextResID();
       // mQuestionTextView.setText(question);
        updateQuestion();

        myTrueButton = findViewById(R.id.true_button);
        myTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, R.string.true_button, Toast.LENGTH_SHORT).show();
                checkAnswer(true);
            }
        });

        myFalseButton = findViewById(R.id.false_button);
        myFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, R.string.false_button, Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick (View v){
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
            //int question = mQuestionBank[mCurrentIndex].getTextResID();
           //mQuestionTextView.setText(question);
                updateQuestion();

            }
        });

        mCheatButton = findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){
                        Intent i = new Intent(MainActivity.this, CheatActivity.class);
                        startActivity(i);
            }
        });
    }
}
