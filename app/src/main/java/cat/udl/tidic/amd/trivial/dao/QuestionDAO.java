package cat.udl.tidic.amd.trivial.dao;

import cat.udl.tidic.amd.trivial.models.Question;
import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionDAO {

    //Exemple 1. Com obtenir una resposta aleatoria sense filtre.
    @GET("trivial/question")
    Call<Question> getRandomQuestion();

    //Exemple 2. Com obtenir una resposta filtrada per una categoria




}
