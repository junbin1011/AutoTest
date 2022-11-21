package com.junbin.autotest;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@LargeTest
public class LargetTest {
    private static final String BASIC_SAMPLE_PACKAGE
            = "com.junbin.autotest";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice mDevice;

    @Before
    public void startActivityFromHomeScreen() {
        // 初始化UiDevice
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // 回到主界面
        mDevice.pressHome();
        // 等待launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);
        // 启动目标APP
        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        // 等待应用启动
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }

    //账户密码登录成功后主界面显示用户名
    @Test
    public void should_show_username_in_main_activity_when_login_success() {
        //输入账户名
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "username"))
                .setText("123@163.com");
        //输入密码
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "password"))
                .setText("123456");
        //点击登录
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "login"))
                .click();
        //验证主界面上显示用户名信息
        UiObject2 text = mDevice
                .wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "name")),
                        500);
        assertEquals("Welcome !Jane Doe", text.getText());

    }
}
