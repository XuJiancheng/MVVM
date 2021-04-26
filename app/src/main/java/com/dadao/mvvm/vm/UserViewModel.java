package com.dadao.mvvm.vm;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.dadao.mvvm.bean.User;
import com.dadao.mvvm.net.StateLiveData;
import com.dadao.mvvm.repository.UserRepository;


/**
 * @author dadao
 */

public class UserViewModel extends ViewModel {

    UserRepository userRepository = new UserRepository();
    SavedStateHandle savedStateHandle;

//    @ViewModelInject
//    UserViewModel(UserRepository repository, @Assisted SavedStateHandle savedStateHandle) {
//        this.userRepository = repository;
//        this.savedStateHandle = savedStateHandle;
//    }

    private StateLiveData<User> mUser;

    public StateLiveData<User> getUser() {

        if (mUser == null) {
            mUser = new StateLiveData<>();
        }
        return mUser;
    }

    /**
     * 加载user信息
     */
    public void loadUser() {
        userRepository.loadUser(getUser());
    }


    public void setUserFlower(boolean isFlower) {
        User user = getUser().getValue();
        if (null == user) {
            user = new User();
        }

        if (isFlower) {
            user.setAge(40);
        } else {
            user.setAge(28);
        }

        getUser().setValue(user);
    }


}
