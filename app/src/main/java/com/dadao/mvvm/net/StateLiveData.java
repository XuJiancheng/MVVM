package com.dadao.mvvm.net;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


/**
 * @author dadao on 2020/6/8.
 */
public class StateLiveData<T> extends MutableLiveData<T> {


    private final MutableLiveData<State> state = new MutableLiveData<>();

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }

    public void observeState(@NonNull LifecycleOwner owner, @NonNull Observer<? super State> observer) {
        state.observe(owner, observer);
    }

    public void postBefore() {
        state.postValue(State.BEFORE);
    }

    public void postLoading() {
        state.postValue(State.LOADING);
    }

    public void postFinish() {
        state.postValue(State.FINISH);
    }

    public void postSuccess() {
        state.postValue(State.SUCCESS);
    }

    public void postError() {
        state.postValue(State.ERROR);
    }

    public enum State {
        BEFORE,
        LOADING,
        FINISH,
        SUCCESS,
        ERROR
    }

}
