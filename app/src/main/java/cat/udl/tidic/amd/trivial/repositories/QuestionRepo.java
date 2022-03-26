package cat.udl.tidic.amd.trivial.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import cat.udl.tidic.amd.trivial.dao.QuestionDAO;
import cat.udl.tidic.amd.trivial.dao.QuestionDAOImpl;
import cat.udl.tidic.amd.trivial.models.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionRepo {

    private static String TAG = "QuestionsRepo";

    private QuestionDAO questionDAO;
    public MutableLiveData<Question> mQuestion;

    public QuestionRepo() {
        this.questionDAO = new QuestionDAOImpl();
        this.mQuestion = new MutableLiveData<>();
    }


    public void getQuestion() {

        questionDAO.getRandomQuestion().enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {

                int code = response.code();
                Log.d(TAG, "getRandomQuestion() -> ha rebut del backend un codi:  " + code);

                if (code == 200) {
                    Question q = response.body();
                    assert q != null;
                    Log.d(TAG, "getRandomQuestion() -> ha rebut la pregunta:  "
                            + q.getQuestion());
                    Log.d(TAG, "getRandomQuestion() -> ha rebut les respostes:  "
                            + q.getAnswers());

                    mQuestion.setValue(q);
                }

            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.d(TAG,  t.getMessage());

            }

        });
    }
}


