package com.dadao.mvvm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dadao.mvvm.adapter.ViewPagerAdapter;
import com.dadao.mvvm.databinding.ActivityMainBinding;
import com.dadao.mvvm.ui.ClassifyFragment;
import com.dadao.mvvm.ui.HomeFragment;
import com.dadao.mvvm.ui.UserFragment;
import com.dadao.mvvm.utils.DL;
import com.dadao.mvvm.vm.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "<--" + this.getClass().getSimpleName() + "-->";


    ActivityMainBinding binding;
    UserViewModel userViewModel;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ViewPagerAdapter viewPagerAdapter;

    int mCurIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        doBusiness();

    }

    UserFragment userFragment;

    private void initView() {
        mFragmentList.add(HomeFragment.newInstance());
        mFragmentList.add(ClassifyFragment.newInstance());
        mFragmentList.add(UserFragment.newInstance());
        viewPagerAdapter = new ViewPagerAdapter(this, getSupportFragmentManager(), mFragmentList);
        binding.vpContent.setAdapter(viewPagerAdapter);

        binding.vpContent.setOffscreenPageLimit(1);

        binding.llHome.setOnClickListener(view -> {
            setCurrentMenuIndex(0);
        });

        binding.llClassify.setOnClickListener(view -> {
            setCurrentMenuIndex(1);
        });

        binding.llMe.setOnClickListener(view -> {
            setCurrentMenuIndex(2);
        });
    }

    private void doBusiness() {
        userViewModel.loadUser();

        DL.e(TAG, getViewModelStore().toString());
    }

    private void setCurrentMenuIndex(int index) {
        mCurIndex = index;
        binding.vpContent.setCurrentItem(mCurIndex, false);
        switch (index) {
            case 0:

                break;

            case 1:

                break;

            case 2:

                break;
            default:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", mCurIndex);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("index")) {
            mCurIndex = savedInstanceState.getInt("index");
            setCurrentMenuIndex(mCurIndex);
        }
    }

}