package com.hit.androidonefinalproject.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hit.androidonefinalproject.R;
import com.hit.androidonefinalproject.databinding.FragmentSignupBinding;
import com.hit.androidonefinalproject.utils.MySharedPreferences;
import com.hit.androidonefinalproject.views.viewmodels.SignupFragmentViewModel;

public class SignupFragment extends Fragment {

    private FragmentSignupBinding binding;
    private SignupFragmentViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        viewModel = new SignupFragmentViewModel(
                new MySharedPreferences(this.getContext())
        );


        setListeners();
        return binding.getRoot();
    }

    private void setListeners() {
        binding.signupBtn.setOnClickListener(v -> {
            boolean savedSuccessfully = viewModel.saveUser(
                    binding.emailEt.getText().toString(),
                    binding.passwordEt.getText().toString(),
                    binding.passwordEtConfirm.getText().toString()
            );

            if(savedSuccessfully) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_signupFragment_to_gamesFragment);
            } else {
                showError();
            }
        });
    }

    private void showError() {
        Toast.makeText(this.getContext(),"Passwords do not match!", Toast.LENGTH_SHORT).show();
    }
}