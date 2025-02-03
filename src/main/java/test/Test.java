package test;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class Test {

    // A : 01
    // B : 02

    //Z : 026

    @org.junit.Test
    public void test(){

        EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
        Assert.assertEquals(encryptDecrypt.encrypt("ABC"), "010203");
        Assert.assertEquals(encryptDecrypt.decrypt(encryptDecrypt.encrypt("ABC")), "ABC");

    }
}
