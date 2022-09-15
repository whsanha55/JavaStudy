package com.whsanha55.study.algorithm;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 * <p>
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 123 Output: 321
 * <a href="https://leetcode.com/problems/reverse-integer/">reverse-integer link</a>
 */
public class ReverseInteger {

    public int reverse(int x) {
        long res = 0;

        while (x != 0) {
            res *= 10;
            res += x % 10;
            x /= 10;
        }

        if (res >= Integer.MIN_VALUE && res <= Integer.MAX_VALUE) {
            return (int) res;
        } else {
            return 0;
        }
    }

    //no need to use long type
    public int reverseImproved(int x) {
        int result = 0;

        while (x != 0) {
            int temp = x % 10;
            int nextResult = result * 10 + temp;
            if ((nextResult - temp) / 10 != result) {
                return 0;
            }
            result = nextResult;
            x /= 10;
        }

        return result;
    }

}
