package ru.alexandermalikov.testmvp.web;


import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.alexandermalikov.testmvp.web.data.Person;
import ru.alexandermalikov.testmvp.web.data.StandardResponse;
import rx.Observable;

public interface IApi {

    @GET("person")
    Observable<List<Person>> getPersonList();

    @POST("person")
    Observable<StandardResponse> addPerson(@Body Person person);

    @DELETE("person/{user_id}")
    Observable<StandardResponse> deletePerson(@Path("user_id") String userId);

}
