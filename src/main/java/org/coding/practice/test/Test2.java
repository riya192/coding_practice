package org.coding.practice.test;

//arrival time train
//departure time train
//min platform
//

import java.util.ArrayList;
import java.util.List;

//
//
//
public class Test2 {

    public static void main(String args[])
    {
        List<Integer> arrival = new ArrayList<>();
        arrival.add(910);
        arrival.add(950);
        arrival.add(800);
        arrival.add(1000);
        List<Integer> dep = new ArrayList<>();
        dep.add(980);
        dep.add(960);
        dep.add(1050);
        dep.add(1000);
        System.out.println(getMinPlatforms(arrival, dep));
        //800, 910, 950, 1000
        //960, 980, 1000, 1050
    }

    private static int getMinPlatforms(List<Integer> arr, List<Integer> dep)
    {
        arr.sort(Integer::compareTo);
        dep.sort(Integer::compareTo);

        int arr_i=0; int dep_i =0;
        int max_platform =0;
        int curr_platform=0;
        int n = arr.size();
        while (arr_i<n && dep_i<n)
        {
            if(arr.get(arr_i) <= dep.get(dep_i))
            {
                curr_platform++;
                arr_i++;
            }else
            {
                curr_platform--;
                dep_i++;
            }

            max_platform = Math.max(curr_platform, max_platform);
        }
        return max_platform;

    }
}


//chocolate - idx - sweetness of the choc
//length - n
//k guest- max sweetness, k+1
//can give more tiles to my guest [100, 90, 80] k,n -> -1
//n - (k+1)
//sort -> arr[k+2]
//n - (k+1)
