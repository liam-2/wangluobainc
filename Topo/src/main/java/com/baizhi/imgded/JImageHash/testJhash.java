package com.baizhi.imgded.JImageHash;




import com.baizhi.imgded.imgeded;
import dev.brachtendorf.jimagehash.hash.Hash;
import dev.brachtendorf.jimagehash.hashAlgorithms.AverageHash;
import dev.brachtendorf.jimagehash.hashAlgorithms.HashingAlgorithm;
import dev.brachtendorf.jimagehash.hashAlgorithms.PerceptiveHash;
import dev.brachtendorf.jimagehash.matcher.exotic.SingleImageMatcher;

import java.io.File;
import java.io.IOException;

public class testJhash extends imgeded {
    public static void main(String[] args) throws IOException {

        File img0 = new File("D:\\data\\snap\\"+imgeded.Str1);
        File img1 = new File("D:\\data\\snap\\"+imgeded.Str2);

        HashingAlgorithm hasher = new PerceptiveHash(32);

        Hash hash0 = hasher.hash(img0);
        Hash hash1 = hasher.hash(img1);

        double similarityScore =hash0.normalizedHammingDistance(hash1);
        System.out.println(1-similarityScore);
        if(similarityScore < 0.2) {
            System.out.println("重复图片1");
            //Considered a duplicate in this particular case
        }

        SingleImageMatcher matcher = new SingleImageMatcher();
        matcher.addHashingAlgorithm(new AverageHash(64),0.3);
        matcher.addHashingAlgorithm(new PerceptiveHash(32),0.2);
        if(matcher.checkSimilarity(img0,img1)) {
            System.out.println("重复图片2");
            //Considered a duplicate in this particular case
        }
    }
}
