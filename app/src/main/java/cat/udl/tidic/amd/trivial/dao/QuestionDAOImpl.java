package cat.udl.tidic.amd.trivial.dao;

import cat.udl.tidic.amd.trivial.models.Question;
import cat.udl.tidic.amd.trivial.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Retrofit;

public class QuestionDAOImpl implements QuestionDAO{

    private  Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();

    @Override
    public Call<Question> getRandomQuestion() {
        return retrofit.create(QuestionDAO.class).getRandomQuestion();
    }
}
