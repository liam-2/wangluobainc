package com.baizhi.imgded.averagehash;

import java.io.IOException;

public class testavg {

    public static void main(String[] args) throws IOException {

        String img1="D:\\data\\snap\\320000000001050101030000000020250471608976914900010120220402072205187808_000.jpg";
        String img2="D:\\data\\snap\\320000000001050101030000000020250471608976914900010120220402072205187808_002.jpg";

        long finger = AHash.fingerprint(img1);
        long finger2 = AHash.fingerprint(img2);

        System.out.println(1.0-(double) HammingDistance.distance(finger, finger2)/12);



    }


}
