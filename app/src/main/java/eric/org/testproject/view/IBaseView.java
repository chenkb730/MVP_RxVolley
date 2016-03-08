package eric.org.testproject.view;

/**
 * Created by kunbin.chen on 2016/3/8.
 */
public interface IBaseView {

    void onFailed(int errorNo);

    void onStart();

    void onSuccess(String t);
}
