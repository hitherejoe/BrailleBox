package co.joebirch.braillebox.data.remote;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import co.joebirch.braillebox.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Provide "make" methods to create instances of {@link BrailleBoxService}
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
public class BrailleBoxServiceFactory {

    public static BrailleBoxService makeBrailleBoxService(HttpUrl url) {
        OkHttpClient okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor());
        return makeBrailleBoxService(okHttpClient, makeGson(), url);
    }

    public static BrailleBoxService makeBrailleBoxService() {
        OkHttpClient okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor());
        return makeBrailleBoxService(okHttpClient, makeGson());
    }

    private static BrailleBoxService makeBrailleBoxService(OkHttpClient okHttpClient, Gson gson,
                                                           HttpUrl url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(BrailleBoxService.class);
    }

    private static BrailleBoxService makeBrailleBoxService(OkHttpClient okHttpClient, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(BrailleBoxService.class);
    }

    private static OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Accept", "application/json")
                                .addHeader("x-api-key", BuildConfig.API_KEY)
                                .method(original.method(), original.body());

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    private static Gson makeGson() {
        return new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    private static HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }
}