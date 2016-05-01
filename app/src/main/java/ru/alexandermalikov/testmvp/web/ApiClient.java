package ru.alexandermalikov.testmvp.web;


import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.alexandermalikov.testmvp.web.data.Person;
import ru.alexandermalikov.testmvp.web.data.StandardResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApiClient {

    private static final String BASE_URL = "http://demo6620638.mockable.io/";

    private IApi mApi;

    public ApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mApi = retrofit.create(IApi.class);
    }


    @RxLogObservable
    public Observable<List<Person>> getPersonList() {
        return mApi.getPersonList().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @RxLogObservable
    public Observable<StandardResponse> addPerson(Person person) {
        return mApi.addPerson(person).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @RxLogObservable
    public Observable<StandardResponse> deletePerson(long userId) {
        return mApi.deletePerson("1").subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
