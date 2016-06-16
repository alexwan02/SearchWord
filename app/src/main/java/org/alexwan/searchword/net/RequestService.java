package org.alexwan.searchword.net;

import org.alexwan.searchword.model.Response;
import org.alexwan.searchword.model.WordModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * RequestService
 * Created by alexwan on 16/6/14.
 */
public interface RequestService {

    @GET("bdc/search/")
    Observable<Response<WordModel>> getWord(@Query("word") String word);

}
