package com.tekinarslan.material.sample.data.retrofit;

import android.content.Context;

import com.tekinarslan.material.sample.bean.CelebrityBean;
import com.tekinarslan.material.sample.bean.MovieDetailBean;
import com.tekinarslan.material.sample.bean.Top250Bean;

import rx.Observable;


/**
 * 修改历史：
 */
public class RetrofitRepository implements Repository {

    private static RetrofitRepository mRetrofitRepository;
    private Context mContext;
    private MovieApiService mMovieApiService;

    private RetrofitRepository(Context mContext) {
        this.mContext = mContext;
        mMovieApiService = MovieApiService.Factory.createService(mContext);
    }

    public synchronized static RetrofitRepository getInstance(Context context) {
        if (mRetrofitRepository == null) {
            mRetrofitRepository = new RetrofitRepository(context);
        }
        return mRetrofitRepository;
    }

    /*@Override
    public Observable<List<TheatersMoive.SubjectsEntity>> gettheatersMovie(String city) {
        return mMovieApiService.getTheatersMovie(city)
                .map(new Func1<TheatersMoive, List<TheatersMoive.SubjectsEntity>>() {
                    @Override
                    public List<TheatersMoive.SubjectsEntity> call(TheatersMoive theatersMoive) {
                        return theatersMoive.getSubjects();
                    }
                });
    }*/

    /*@Override
    public Observable<CommonBean> getCommingSoonMovie(int start, int count) {
        return mMovieApiService.getCommongSoonMovie(start, count);
               *//* .map(new Func1<CommonBean, List<CommonBean.SubjectsBean>>() {
                    @Override
                    public List<CommonBean.SubjectsBean> call(CommonBean commonBean) {
                        return commonBean.getSubjects();
                    }
                });*//*
    }*/


    public Observable<MovieDetailBean> getMovieDetail(int id) {
        return mMovieApiService.getMovieDetail(id);
       /* map(new Func1<MovieDetailBean, MovieDetailBean>() {
            @Override
            public MovieDetailBean call(MovieDetailBean movieDetailBean) {
                return movieDetailBean;
            }
        });*/
    }


    public Observable<CelebrityBean> getCelebrityDetail(int id) {
        return mMovieApiService.getCelebrityDetail(id);
    }

    @Override
    public Observable<Top250Bean> getTop250Movie(int start, int count) {
        return mMovieApiService.getTop250Movie(start, count);
    }

    /*@Override
    public Observable<UsBoxBean> getUsBoxMovie() {
        return mMovieApiService.getUsBoxMovie();
    }
*/

}