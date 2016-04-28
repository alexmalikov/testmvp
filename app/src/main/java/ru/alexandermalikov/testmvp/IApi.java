package ru.alexandermalikov.testmvp;


import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface IApi {

    @GET("person")
    Observable<List<Person>> getPersonList();

    @POST("person")
    Observable<StandardResponse> addPerson(@Body Person person);

    @DELETE("person")
    Observable<StandardResponse> deletePerson(@Body Person person);

}
