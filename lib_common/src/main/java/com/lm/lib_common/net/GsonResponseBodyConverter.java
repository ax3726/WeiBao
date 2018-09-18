package com.lm.lib_common.net;

import com.google.gson.Gson;
import com.lm.lib_common.model.BaseBean;
import com.lm.lib_common.model.ResponseCodeEnum;
import com.lm.lib_common.model.ResultResponse;
import com.lm.lib_common.net.ex.ApiException;
import com.lm.lib_common.net.ex.ResultException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by lm on 2017/11/22.
 * Description：自定义gson转换器
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            ResultResponse resultResponse = gson.fromJson(response, ResultResponse.class);
            if (resultResponse.getCode() == 200) {
                return gson.fromJson(response, type);
            }

            /*else if (resultResponse.getCode() == 401) {
                throw new ApiException(ResponseCodeEnum.AUTH_FAILURE);
            }*/

            else {
                BaseBean baseBean = gson.fromJson(response, BaseBean.class);
                throw new ResultException(baseBean.getCode(), baseBean.getMessage());
            }

        } finally {
            value.close();
        }
    }
}
