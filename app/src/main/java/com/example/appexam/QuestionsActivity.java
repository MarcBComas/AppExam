package com.example.appexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionsActivity extends AppCompatActivity {
    private TextView questionView;
    private Questions[] questionBank = new Questions[] {
            new Questions(R.string.question1),
            new Questions(R.string.question2),
            new Questions(R.string.question3),
    };
    private int currentIndex = 0;

    private LinearLayout layoutCheckBox;
    private LinearLayout layoutRadioButton;
    private LinearLayout qButtons;
    private Button qNext;
    private Button qPrevious;
    private SeekBar questionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_activity);
        questionView = (TextView) findViewById(R.id.questionview);
        layoutCheckBox = (LinearLayout) findViewById(R.id.cblayout);
        layoutRadioButton = (LinearLayout) findViewById(R.id.layoutrb);
        questionbar = (SeekBar) findViewById(R.id.q3);
        qButtons = (LinearLayout) findViewById(R.id.questionBttns);
        qNext = (Button) findViewById(R.id.nextQ);
        qPrevious = (Button) findViewById(R.id.previousQ);
        qNext.setOnClickListener(onClick());
        qPrevious.setOnClickListener(onClick());
        updateQuestion();
    }

    private void updateQuestion() {
        if (currentIndex == 0) {
            layoutCheckBox.setVisibility(View.VISIBLE);
            layoutRadioButton.setVisibility(View.GONE);
            qNext.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            qPrevious.setVisibility(View.GONE);
        } else if (currentIndex == 1) {
            layoutCheckBox.setVisibility(View.GONE);
            questionbar.setVisibility(View.GONE);
            layoutRadioButton.setVisibility(View.VISIBLE);
            qNext.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            qPrevious.setVisibility(View.VISIBLE);
        } else if (currentIndex == 2) {
            layoutRadioButton.setVisibility(View.GONE);
            questionbar.setVisibility(View.VISIBLE);
        }
        questionView.setText(questionBank[currentIndex].getTextId());
    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.nextQ:
                        currentIndex = (currentIndex + 1) % questionBank.length;
                        updateQuestion();
                        break;
                    case R.id.previousQ:
                        currentIndex = (currentIndex - 1) % questionBank.length;
                        updateQuestion();
                        break;
                }
            }
        };
    }
}
