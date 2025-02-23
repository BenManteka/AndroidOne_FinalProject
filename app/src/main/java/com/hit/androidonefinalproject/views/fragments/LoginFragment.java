package com.hit.androidonefinalproject.views.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.hit.androidonefinalproject.R;
import com.hit.androidonefinalproject.databinding.FragmentLoginBinding;
import com.hit.androidonefinalproject.utils.IMySharedPreferences;
import com.hit.androidonefinalproject.utils.MySharedPreferences;
import com.hit.androidonefinalproject.views.viewmodels.LoginFragmentViewModel;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater,container,false);

        viewModel = new LoginFragmentViewModel(
                new MySharedPreferences(this.getContext())
        );

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListeners();
    }


    private void setListeners() {
        binding.btnLogin.setOnClickListener(v -> {
            Boolean isSaved = viewModel.isUserSaved(
                    binding.emailEt.getText().toString(),
                    binding.passwordEt.getText().toString()
            );

            if(isSaved) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_gamesFragment);
            } else {
                showError();
            }
        });

        binding.btnSignup.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_loginFragment_to_signupFragment);
        });
    }

    private void showError() {
        Toast.makeText(this.getContext(),"User not found!", Toast.LENGTH_SHORT).show();
    }
}