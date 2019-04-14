package com.other;

import com.util.UUIDUtils;

public class UUIDTest {
    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) {
            System.out.println(UUIDUtils.getUUID());
        }
    }
}
