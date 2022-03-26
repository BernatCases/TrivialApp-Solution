package cat.udl.tidic.amd.trivial.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import cat.udl.tidic.amd.trivial.R;
import cat.udl.tidic.amd.trivial.databinding.ActivityQuestionBinding;
import cat.udl.tidic.amd.trivial.models.Answer;
import cat.udl.tidic.amd.trivial.models.Question;
import cat.udl.tidic.amd.trivial.viewmodels.QuestionViewModel;

public class QuestionActivity extends AppCompatActivity {

    QuestionViewModel viewModel;
    ActivityQuestionBinding activityQuestionBinding;
    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initGame();
    }

    private void initDataBinding() {
        activityQuestionBinding= DataBindingUtil.setContentView(this, R.layout.activity_question);
        viewModel = new QuestionViewModel();
        activityQuestionBinding.setQuestionViewModel(viewModel);
        // Required to update UI with LiveData
        activityQuestionBinding.setLifecycleOwner(this);
    }
    

    public void initGame(){
        viewModel.getQuestion();
        viewModel.isQuestionLoaded().observe(this, new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                Log.d(this.getClass().getSimpleName(), "questionLoaded.");
                activityQuestionBinding.categoryTV
                        .setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                question.getCategory().getColor()));
                activityQuestionBinding.answer1Bt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                        question.getCategory().getColor()));
                activityQuestionBinding.answer2Bt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                        question.getCategory().getColor()));
                activityQuestionBinding.answer3Bt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                        question.getCategory().getColor()));
                activityQuestionBinding.answer4Bt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                        question.getCategory().getColor()));
            }
        });

        viewModel.userAnswer.observe(this, new Observer<Answer>() {
            @Override
            public void onChanged(Answer answer) {
                getPlayerAnswer(answer);
            }
        });

    }

    public void getPlayerAnswer(Answer answer){
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("cat",viewModel.isQuestionLoaded().getValue().getCategory().toString() );
        intent.putExtra("isCorrect",answer.getCorrect());
        setResult(RESULT_OK, intent);
        finish();
    }







}