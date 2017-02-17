package techkids.cuong.myapplication.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import techkids.cuong.myapplication.R;
import techkids.cuong.myapplication.events.HideToolbarEvent;
import techkids.cuong.myapplication.events.LoginEvent;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    @BindView(R.id.bt_login)
    Button btLogin;

    @BindView(R.id.ll_google)
    LinearLayout llGoogle;

    @BindView(R.id.bt_sign_up)
    Button btSignUp;

    @BindView(R.id.bt_facebook)
    LoginButton btFacebook;

    CallbackManager callbackManager;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);

        EventBus.getDefault().post(new HideToolbarEvent(true));
        callbackManager = CallbackManager.Factory.create();
        btFacebook.setReadPermissions("email");
        // If using in a fragment
        btFacebook.setFragment(this);

        // Callback registration
        btFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                EventBus.getDefault().post(new LoginEvent());
            }

            @Override
            public void onCancel() {
                EventBus.getDefault().post(new LoginEvent());
            }

            @Override
            public void onError(FacebookException exception) {
                EventBus.getDefault().post(new LoginEvent());
            }
        });

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

//    @OnClick(R.id.ll_facebook)
//    public void onFacebookAccountPressed() {
//        EventBus.getDefault().post(new LoginEvent());
//        btFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancel() {
//                Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
