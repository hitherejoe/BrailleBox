package co.joebirch.braillebox.data.remote;

import co.joebirch.braillebox.data.model.NewsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BrailleBoxService {

    @GET("articles")
    Observable<NewsResponse> getArticles(@Query("source") String source,
                                         @Query("sortBy") String sortBy);

}