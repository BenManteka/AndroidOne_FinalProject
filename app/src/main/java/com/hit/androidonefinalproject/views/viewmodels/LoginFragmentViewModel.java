package com.hit.androidonefinalproject.views.viewmodels;
import androidx.lifecycle.ViewModel;

import com.hit.androidonefinalproject.model.UserModel;
import com.hit.androidonefinalproject.utils.IMySharedPreferences;
import com.hit.androidonefinalproject.utils.MySharedPreferences;

public class LoginFragmentViewModel extends ViewModel {

    MySharedPreferences preferences;

    public LoginFragmentViewModel(IMySharedPreferences preferences) {
        this.preferences = (MySharedPreferences) preferences;
    }


    public Boolean isUserSaved(String email, String password) {

        UserModel newUser = new UserModel(email,password);
        UserModel storedUser = preferences.getUserData();

        if(storedUser == null) return false;

        return newUser.getEmail().equals(storedUser.getEmail()) &&
                newUser.getPassword().equals(storedUser.getPassword());

    }
}
