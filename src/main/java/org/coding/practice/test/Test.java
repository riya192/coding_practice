package org.coding.practice.test;

//array - 0, 1 binary
//length - n
//idx - left 0 = right 1


// 0, 1, 1, 0, 1, 1, 1
//zero_ttl_cnt = 2 , one_ttl_cnt = 4
// 0 idx -> 0s = = 0
// 0 idx -> 1s = total_count - (a[idx] == 1 ? -1 : 0) = 4 - 0 = 4
// 1 idx = 0_cnt = 1
//1st idx = 1_cnt = total_count - (a[idx] == 1 ? -1 : 0) = 4-1 = 3

// zero_cnt = 1, one_cnt = 2
// 3rd idx
//left_zero = 1
//right_one = 4 -2 = 2
//4th idx
// zero_cnt = 2, one_cnt = 2
//left_zero = zero_cnt = 2
//right_one= 4-2 = 2


import java.util.List;

public class Test {

    public static void main(String args[])
    {
        List<Integer> arr1 = List.of(1, 0, 0, 1, 0, 0, 0);
        System.out.println(getLeftZeroRightOneIdx(arr1));
        List<Integer> arr2 = List.of(0, 1, 1, 0, 1, 1);
        System.out.println(getLeftZeroRightOneIdx(arr2));
    }

    private static int getLeftZeroRightOneIdx(List<Integer> arr)
    {
        int one_ttl_cnt=0;
        for(int i : arr)
        {
            if(i == 1) one_ttl_cnt++;
        }
        int n = arr.size();
        int one_cnt=0;
        for(int i = 0;i<n;i++)
        {
            int zero_cnt = i - one_cnt;
            int one_right_count = (one_ttl_cnt-one_cnt) - (arr.get(i) == 1 ? 1 : 0);
            if(zero_cnt == one_right_count)
            {
                return i;
            }
            if(arr.get(i) == 1) one_cnt++;
        }
        return -1;
    }
}
