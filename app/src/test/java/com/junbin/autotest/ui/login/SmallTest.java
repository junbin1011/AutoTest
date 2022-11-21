package com.junbin.autotest.ui.login;

import com.junbin.autotest.data.LoginDataSource;
import com.junbin.autotest.data.LoginRepository;

import org.junit.Assert;
import org.junit.Test;

public class SmallTest {
    @Test
    public void should_return_false_when_password_is_null() {
        LoginViewModel loginViewModel = new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        String password = null;
        boolean result = loginViewModel.isPasswordValid(password);
        Assert.assertFalse(result);
    }

    @Test
    public void should_return_false_when_password_length_is_less_than_5() {
        LoginViewModel loginViewModel = new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        String password = "1234";
        boolean result = loginViewModel.isPasswordValid(password);
        Assert.assertFalse(result);
    }

    @Test
    public void should_return_false_when_password_length_is_equal_5() {
        LoginViewModel loginViewModel = new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        String password = "12345";
        boolean result = loginViewModel.isPasswordValid(password);
        Assert.assertFalse(result);
    }

    @Test
    public void should_return_true_when_password_length_greater_than_5() {
        LoginViewModel loginViewModel = new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
        String password = "123456";
        boolean result = loginViewModel.isPasswordValid(password);
        Assert.assertTrue(result);
    }
}
