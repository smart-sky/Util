package com.example.myutil.stringUtil;

import com.example.myutil.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class MyStringUtilTest extends BaseTest {
    @Test
    public void isTrue(){
        Assert.assertTrue(StringUtil.isTrue('1'));
    }
}
