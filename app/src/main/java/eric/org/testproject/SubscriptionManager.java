package eric.org.testproject;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.rx.Result;

import java.util.HashMap;
import java.util.Stack;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * rx管理器
 * Created by kunbin.chen on 2016/3/8.
 */
public class SubscriptionManager {

    private static SubscriptionManager manager;

    private HashMap<String, Subscription> subscriptions = null;

    private SubscriptionManager() {
        subscriptions = new HashMap<>();
    }

    public static final SubscriptionManager getInstance() {
        if (null == manager) {
            synchronized (SubscriptionManager.class) {
                if (null == manager) {
                    manager = new SubscriptionManager();
                }
            }
        }

        return manager;
    }

    public void put(String tag, Subscription subscription) {
        if (null != subscriptions && null != tag && null != subscription) {
            remove(tag);
            subscriptions.put(tag, subscription);
        }
    }

    public Subscription get(String tag) {
        return subscriptions.get(tag);
    }

    public void remove(String tag) {
        if (subscriptions.containsKey(tag)) {
            Subscription subscription = get(tag);
            if (subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
            subscriptions.remove(tag);
        }
    }


    /**
     * 获取一个结果
     *
     * @return
     */
    public Observable<Result> result() {
        if (null != rxBuilder()) {
            return rxBuilder().getResult().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

        return null;
    }

    /**
     * 构造一个请求参数
     *
     * @return
     */
    public RxVolley.Builder rxBuilder() {
        return new RxVolley.Builder()
                .httpMethod(RxVolley.Method.POST)
                .encoding("UTF_8")
                .timeout(3000 * 10);
    }

}
