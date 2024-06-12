package com.culturecode.main.app.connection;

import com.squareup.okhttp.RequestBody;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.http.Query;
import okhttp3.ResponseBody;

public interface UserService {
    @POST("/users/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("/users/register")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @GET("/places")
    Call<List<PlaceRequest>> getAllPlaces();

    @POST("/places/place/qrCodeCheck/{qrCode}")
    Call<ResponseBody> checkQrCode(@Path("qrCode") String qrCode);

    @POST("/places/place/description/{name}")
    Call<ResponseBody> getPlaceDescription(@Path("name") String name);

    @GET("/comments/{placeId}")
    Call<List<CommentResponse>> getCommentsByPlaceId(@Path("placeId") String placeId);

    @POST("/comments/addComment")
    Call<Void> addComment(@Body CommentRequest commentRequest);

    @POST("placesFacts/getFactsByPlaceName/{name}")
    Call<List<FactResponse>> getFactsByPlaceName(@Path("name") String name);

    @POST("/questions/question/{place_id}")
    Call<List<QuestionResponse>> getQuestionsByPlaceId(@Path("place_id") String placeId);

    @POST("/questions_answer/getQuizQuestion/{question_id}")
    Call<List<QuizQuestionsResponse>> getQuizQuestionsByQuestionId(@Path("question_id") int questionId);


}
