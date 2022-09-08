package com.baizhi.imgded.averagehash;

public class HammingDistance {

    public static int distance(long fg1, long fg2) {
        int distance = 0;
        long res = fg1 ^ fg2;
        for (int i = 0; i < 64; i++) {
            distance += (res >> i & 1);
        }
        return distance;
    }
}
