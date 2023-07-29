package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionsTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;

    int score=0;
    int tq =  QuesAns.ques.length;
    int currentquesindex = 0;
    String selectedans = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.quizapp);
        questionsTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);


        totalQuestionsTextView.setText("Total questions:"+tq);
loadNewQuestion();
    }


    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {
            if (selectedans.equals(QuesAns.correctAns[currentquesindex])) {
                score++;
            }
            currentquesindex++;
            loadNewQuestion();
        } else {
            selectedans = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.GREEN);
        }
    }
    void loadNewQuestion(){
    if(currentquesindex == tq ){
        finishQuiz();
        return;
    }

        questionsTextView.setText(QuesAns.ques[currentquesindex]);
        ansA.setText(QuesAns.choices[currentquesindex][0]);
        ansB.setText(QuesAns.choices[currentquesindex][1]);
        ansC.setText(QuesAns.choices[currentquesindex][2]);
        ansD.setText(QuesAns.choices[currentquesindex][3]);
    }

void finishQuiz(){
String passStatus = "";
if(score> tq*0.60){
    passStatus = "Passed";
}else{
    passStatus = "Failed";
}
new AlertDialog.Builder(this)
        .setTitle(passStatus)
        .setMessage("Score is "+ score+" out of "+ tq)
        .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
}
void restartQuiz(){
        score = 0;
        currentquesindex = 0;
        loadNewQuestion();
}
    }
