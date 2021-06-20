package app.domain.model;

import java.util.Arrays;

public class BruteForceAlgorithm {
    public BruteForceAlgorithm(){

    }

    public static int [] Max(int[] seq) {
        int maximumSubArraySum = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < seq.length; i++) {
            int sum = 0;
            for (int j = i; j < seq.length; j++) {
                sum += seq[j];
                if (sum > maximumSubArraySum) {
                    maximumSubArraySum = sum;
                    start = i;
                    end = j;
                }
            }
        }
        return Arrays.copyOfRange(seq, start, end+1);
    }

}
