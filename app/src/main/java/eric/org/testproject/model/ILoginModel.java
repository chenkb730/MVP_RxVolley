package eric.org.testproject.model;

import com.kymjs.rxvolley.client.HttpCallback;

import rx.Subscription;

/**
 * Created by kunbin.chen on 2016/3/8.
 */
public interface ILoginModel {
    Subscription login(String username, String password, HttpCallback callback);
}
