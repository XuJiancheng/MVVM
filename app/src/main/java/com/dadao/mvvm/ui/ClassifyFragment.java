package com.dadao.mvvm.ui;

/**
 * @author dadao on 4/25/21
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dadao.mvvm.databinding.FragmentClassifyBinding;
import com.dadao.mvvm.utils.DL;
import com.dadao.mvvm.vm.UserViewModel;

public class ClassifyFragment extends Fragment {

    private final String TAG = "<--" + this.getClass().getSimpleName() + "-->";


    UserViewModel userViewModel;
    FragmentClassifyBinding binding;

    public ClassifyFragment() {

    }

    public static ClassifyFragment newInstance() {
        return new ClassifyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DL.e(TAG, getViewModelStore().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClassifyBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        DL.e(TAG, "___" + requireActivity().getViewModelStore().toString());
        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {

            if (null == user) {
                return;
            }

            binding.tvName.setText(user.getName());
            binding.tvAge.setText(user.getDisplayAge());

            binding.tvAge.setOnClickListener(tvAge -> {
                userViewModel.setUserFlower(true);
            });

        });

        userViewModel.getUser().observeState(getViewLifecycleOwner(), state -> {
            switch (state) {
                case BEFORE:
                    break;
                case FINISH:
                    break;
                case ERROR:
                    break;
                default:
                    break;
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        DL.e(TAG, "onResume");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
