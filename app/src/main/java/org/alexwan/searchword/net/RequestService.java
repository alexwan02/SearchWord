package org.alexwan.searchword.net;

import org.alexwan.searchword.model.Response;
import org.alexwan.searchword.model.SentenceModel;
import org.alexwan.searchword.model.TranslationModel;
import org.alexwan.searchword.model.WordModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * RequestService
 * Created by alexwan on 16/6/14.
 */
public interface RequestService {

    /**
     * Get word from shanbei api
     *
     * @param word word that you want to search ， query only be single word
     * @return word json response
     */
    @GET("bdc/search/")
    Observable<Response<WordModel>> getWord(@Query("word") String word);

    /**
     * Get translation of query , query can be word ，phrase or sentence
     *
     * @param keyfrom keyfrom search word
     * @param key     youdao api key
     * @param data    fixed value "data"
     * @param doctype here use json as response
     * @param version 1.1
     * @param query   input text that query
     * @return translation response
     */
    @GET
    Observable<TranslationModel> getTranslation(@Query("keyfrom") String keyfrom, @Query("key") String key,
                                                @Query("type") String data, @Query("doctype") String doctype,
                                                @Query("version") String version, @Query("q") String query);

    /**
     * Get Sentence specified date using iciba api
     *
     * @param date sentence of specified date. date can be null , indicate today's sentence
     * @param type fixed value "last" or "next" . default value is null .
     * @return Sentence model
     */
    @GET("dsapi/")
    Observable<SentenceModel> getSentence(@Query("date") String date, @Query("type") String type);

}
