package com.hit.androidonefinalproject.views.viewmodels;

import androidx.lifecycle.ViewModel;

import com.hit.androidonefinalproject.model.UserModel;
import com.hit.androidonefinalproject.utils.IMySharedPreferences;
import com.hit.androidonefinalproject.utils.MySharedPreferences;

public class SignupFragmentViewModel extends ViewModel {

    MySharedPreferences preferences;

    public SignupFragmentViewModel(IMySharedPreferences preferences) {
        this.preferences = (MySharedPreferences) preferences;
    }

    public boolean saveUser(String email, String password, String passwordConfirm) {
        boolean passwordsMatch = password.equals(passwordConfirm);

        if(passwordsMatch) {
            preferences.setUserData(
                    new UserModel(email, password)
            );
            return true;
        } else {
            return false;
        }
    }

}
