//package com.example.apicallexample;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class PostsVIewModel extends ViewModel {
//
//    private  MutableLiveData<Boolean> isLoading = new MutableLiveData<Boolean>();
//
//    private  MutableLiveData<List<Posts>> posts = new MutableLiveData<List<Posts>>();
//
//    private   MutableLiveData<String> error = new MutableLiveData<String>();
//
//    public LiveData<Boolean> getIsLoading(){
//        return  isLoading;
//    }
//
//    public LiveData<List<Posts>> getPosts(){
//        return  posts;
//    }
//
//    public LiveData<String> getError(){
//        return  error;
//    }
//
//    public  void fetchPosts(){
//        isLoading.setValue(true);
//
//        //make api call
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        final Call<List<Posts>> call = apiInterface.getPosts();
//
//        call.enqueue(
//                new Callback<List<Posts>>() {
//                    @Override
//                    public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
//                        isLoading.setValue(false);
//                        if(response.isSuccessful()){
//                            posts.setValue(response.body());
//                        }else{
//                            error.setValue("Error Text" + response.code());
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Posts>> call, Throwable t) {
//                        isLoading.setValue(false);
//                        error.setValue(t.getMessage());
//
//                    }
//                }
//        );
//    }
//
//
//}
