package cat.udl.tidic.amd.trivial.viewmodels;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cat.udl.tidic.amd.trivial.models.Answer;
import cat.udl.tidic.amd.trivial.models.Question;
import cat.udl.tidic.amd.trivial.repositories.QuestionRepo;

public class QuestionViewModel extends ViewModel {

    private final QuestionRepo questionRepo;
    public MutableLiveData<Answer> userAnswer;

    public QuestionViewModel() {
        this.questionRepo = new QuestionRepo();
        this.userAnswer = new MutableLiveData<>();
    }

    public void getQuestion(){
        this.questionRepo.getQuestion();
    }

    public LiveData<Question> isQuestionLoaded(){
        return this.questionRepo.mQuestion;
    }

    public void onClickedAt(Answer answer){
        userAnswer.setValue(answer);
    }




}

