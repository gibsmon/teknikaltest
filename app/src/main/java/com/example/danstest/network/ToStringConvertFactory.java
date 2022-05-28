package com.example.danstest.network;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by wandhie on 3/9/2017.
 */

public class ToStringConvertFactory extends Converter.Factory {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json");
    private static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        if (String.class.equals(type)) {
            return (Converter<ResponseBody, String>) ResponseBody::string;
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {

        if (String.class.equals(type)) {
            return (Converter<String, RequestBody>) value -> {
                MediaType type1;
                if (value.contains("{")) {
                    type1 = MEDIA_TYPE;
                } else {
                    type1 = MEDIA_TYPE_TEXT;
                }
                System.out.println("type1 :" + type1);
                return RequestBody.create(type1, value);
            };
        }
        return null;
    }
}