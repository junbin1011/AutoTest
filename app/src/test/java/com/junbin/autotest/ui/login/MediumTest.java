package com.junbin.autotest.ui.login;


import androidx.test.annotation.UiThreadTest;

import com.junbin.autotest.data.LoginDataSource;
import com.junbin.autotest.data.LoginRepository;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MediumTest {

    @Test
    @UiThreadTest
    public void should_pass_valid_when_username_and_password_is_valid() {
        LoginViewModel loginViewModel = new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        loginViewModel.loginDataChanged("123@163.com", "123456");
        LoginFormState value = LiveDataTestUtil.getValue(loginViewModel.getLoginFormState());
        Assert.assertNull(value.getUsernameError());
        Assert.assertNull(value.getPasswordError());
        Assert.assertEquals(true, value.isDataValid());
    }

    @Test
    @UiThreadTest
    public void should_return_invalid_username_state_when_username_is_invalid() {
        LoginViewModel loginViewModel = new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        loginViewModel.loginDataChanged("1", "123456");
        LoginFormState value = LiveDataTestUtil.getValue(loginViewModel.getLoginFormState());
        Assert.assertNotNull(value.getUsernameError().intValue());
        Assert.assertNull(value.getPasswordError());
        Assert.assertEquals(false, value.isDataValid());
    }

    @Test
    @UiThreadTest
    public void should_return_invalid_password_state_when_password_is_invalid() {
        LoginViewModel loginViewModel = new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        loginViewModel.loginDataChanged("123@163.com", "1234");
        LoginFormState value = LiveDataTestUtil.getValue(loginViewModel.getLoginFormState());
        Assert.assertNull(value.getUsernameError());
        Assert.assertNotNull(value.getPasswordError().intValue());
        Assert.assertEquals(false, value.isDataValid());
    }
}