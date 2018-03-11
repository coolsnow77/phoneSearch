package com.example.cui.phonesearch.mvp.impl;

import android.util.Log;

import com.example.cui.phonesearch.business.HttpUntil;
import com.example.cui.phonesearch.mvp.MvpMainView;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.cui.phonesearch.model.Phone;
import com.google.gson.Gson;

/**
 * Created by cui on 2018/3/11.
 */

public class MainPresenter extends BasePresenter
{
//    String api = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=1894232193";
    String api = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm";

    Phone mPhone;
    MvpMainView mvpMainView;
    private final  String TAG = "MainPresenter";

    public MainPresenter(MvpMainView mainView)
    {
        mvpMainView = mainView;
    }

    public Phone getPhoneInfo()
    {
        return mPhone;
    }

    public void searchPhoneInfo(String phone)
    {
        if(phone.length() !=11)
        {
            mvpMainView.showToast("请输入正确的手机号");
            return;
        }

        mvpMainView.showLoading();
        //http 的请求处理逻辑
        sendHttp(phone);
    }

    private void sendHttp(String phone)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("tel", phone);
        HttpUntil httpUntil = new HttpUntil(new HttpUntil.HttpResponse() {
            @Override
            public void onSuccess(Object object) {
                String json = object.toString();
                /**
                 * __GetZoneResult_ = {
                 mts:'1314194',
                 province:'甘肃',
                 catName:'中国联通',
                 telString:'13141948900',
                 areaVid:'30505',
                 ispVid:'137815084',
                 carrier:'甘肃联通'
                 }
                 */
                //截取字符串
                int index = json.indexOf("{");
                json = json.substring(index, json.length());
                mPhone = parseModelWithOrgJson(json);
                //Gson
                mPhone = parseModelWithGson(json);
                //fastjson
                mPhone = parseModelWithFastJson(json);
                mvpMainView.hidenLoading();
                mvpMainView.updateView();
            }

            @Override
            public void onFail(String error) {
                mvpMainView.showToast(error);
                mvpMainView.hidenLoading();
            }
        });
        httpUntil.sendGetHttp(api, map);
    }

    /**
     * 采用 JSONObject 对象解析json 数据
     *
     * @param json
     * @return
     */
    private Phone parseModelWithOrgJson(String json)
    {
        Phone phone = new Phone();

        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.d(TAG, "parseModelWithOrgJson: " + json);
            String value = jsonObject.getString("telString");
            phone.setTelString(value);

            value = jsonObject.getString("province");
            phone.setProvince(value);

            value = jsonObject.getString("catName");
            phone.setCatName(value);

            value = jsonObject.getString("carrier");
            phone.setCarrier(value);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return phone;
    }

    /**
     * 采用Google Gson 转换json 对象
     *
     * @param json
     * @return
     */
    private Phone parseModelWithGson(String json)
    {
        Gson gson = new Gson();
        Phone phone = gson.fromJson(json, Phone.class);
        return phone;
    }

    /**
     * 采用alibaba fastjson 解析json 对象
     *
     * @param json
     * @return
     */
    private Phone parseModelWithFastJson(String json)
    {
        Phone phone = com.alibaba.fastjson.JSONObject.parseObject(json, Phone.class);
        return phone;
    }

}
