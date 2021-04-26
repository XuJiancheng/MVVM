package com.dadao.mvvm.ui;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dadao.mvvm.databinding.FragmentUserBinding;
import com.dadao.mvvm.utils.DL;
import com.dadao.mvvm.utils.DT;
import com.dadao.mvvm.view.LoadingDialog;
import com.dadao.mvvm.vm.UserViewModel;

public class UserFragment extends Fragment {

    private final String TAG = "<--" + this.getClass().getSimpleName() + "-->";

    protected FragmentUserBinding binding;

    UserViewModel userViewModel;

    LoadingDialog loadingDialog;

    public UserFragment() {
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DL.e(TAG, getViewModelStore().toString());
        loadingDialog = new LoadingDialog(requireActivity());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {

            if (null == user) {
                return;
            }

            binding.tvName.setText(user.getName());
            binding.tvAge.setText(user.getDisplayAge());
            if (user.isFlower()) {
                binding.tvAge.setText(user.getDisplayAge() + " 你开花了");
            }

            binding.tvName.post(() -> {
                DT.showShort(requireActivity(), "数据刷新_view是否用户可见" + binding.tvName.getGlobalVisibleRect(new Rect()));
            });


        });

        userViewModel.getUser().observeState(getViewLifecycleOwner(), state -> {
            switch (state) {
                case BEFORE:
                    if (null != loadingDialog) {
                        loadingDialog.show();
                    }
                    break;
                case FINISH:
                    if (null != loadingDialog) {
                        loadingDialog.hide();
                    }
                    break;
                case ERROR:
                    if (null != loadingDialog) {
                        loadingDialog.hide();
                    }
                    break;
                default:
                    if (null != loadingDialog) {
                        loadingDialog.hide();
                    }
                    break;
            }
        });

        binding.tvName.setOnClickListener(tvName -> {
            userViewModel.loadUser();
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        DL.e(TAG, "onResume");
        userViewModel.loadUser();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}