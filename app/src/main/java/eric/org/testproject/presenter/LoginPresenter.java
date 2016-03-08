package eric.org.testproject.presenter;

import com.kymjs.rxvolley.client.HttpCallback;

import eric.org.testproject.model.ILoginModel;
import eric.org.testproject.model.LoginModelImpl;
import eric.org.testproject.view.ILoginView;
import rx.Subscription;

/**
 * Created by kunbin.chen on 2016/3/8.
 */
public class LoginPresenter extends HttpCallback {

    ILoginView loginView;
    ILoginModel loginModel;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;

        loginModel = new LoginModelImpl();
    }

    String key = "e680e780ab54441da5af5e5f94454a97";
    String city = "CN101280601";


    public Subscription login(String username, String password) {
        return loginModel.login(username, password, this);
    }


    @Override
    public void onPreStart() {
        if (null != loginView) {
            loginView.onStart();
        }
    }

    @Override
    public void onSuccess(String t) {
        super.onSuccess(t);

        if (null != loginView) {
            loginView.navigateToHome();
        }
    }

    @Override
    public void onFailure(int errorNo, String strMsg) {
        super.onFailure(errorNo, strMsg);

        if (null != loginView) {
            loginView.onFailed(errorNo);
        }
    }
}
