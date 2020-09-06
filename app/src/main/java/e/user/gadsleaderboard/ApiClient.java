package e.user.gadsleaderboard;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://gadsapi.herokuapp.com/";
    public static final String GOOGLE_DOCS_BASE_URL = "https://docs.google.com/forms/d/e/";

    public static Api getClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }

    public static Api getGoogleDocsClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GOOGLE_DOCS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }
}
