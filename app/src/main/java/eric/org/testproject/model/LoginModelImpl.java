package eric.org.testproject.model;

import android.telephony.SmsMessage;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.rx.Result;

import eric.org.testproject.SubscriptionManager;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 实现
 * Created by kunbin.chen on 2016/3/8.
 */
public class LoginModelImpl implements ILoginModel {

    @Override
    public Subscription login(String username, String password, final HttpCallback callback) {

        RxVolley.Builder builder = SubscriptionManager.getInstance().rxBuilder();
        builder.url("");
        HttpParams httpParams = new HttpParams();
        httpParams.put("username", username);
        httpParams.put("password", password);
        builder.contentType(RxVolley.ContentType.JSON);
        return builder.getResult().map(new Func1<Result, String>() {
            @Override
            public String call(Result result) {
                if (null != result && null != result.data) {
                    return new String(result.data);
                }

                return null;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                callback.onSuccess(s);
            }
        });
    }
}
