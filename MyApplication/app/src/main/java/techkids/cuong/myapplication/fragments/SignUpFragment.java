package techkids.cuong.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.events.LoginEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    @BindView(R.id.bt_login)
    Button btLogin;

    @BindView(R.id.ll_facebook)
    LinearLayout llFacebook;

    @BindView(R.id.ll_google)
    LinearLayout llGoogle;

    @BindView(R.id.bt_sign_up)
    Button btSignUp;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.bt_login)
    public void onLoginPressed() {
        EventBus.getDefault().post(new LoginEvent());
    }

    @OnClick(R.id.bt_sign_up)
    public void onSignUpPressed() {
        EventBus.getDefault().post(new LoginEvent());
    }

    @OnClick(R.id.ll_google)
    public void onGoogleAccountPressed() {
        EventBus.getDefault().post(new LoginEvent());
    }

    @OnClick(R.id.ll_facebook)
    public void onFacebookAccountPressed() {
        EventBus.getDefault().post(new LoginEvent());
    }

}
