package org.coding.practice.google;

import java.util.*;

/**
you are given an array of houses in a neighboorhood in a city.
you have to rearrange houses in such a way that in a single neighbourhood the houses are sorted by number in ascending order and no 2 houses with same number are in same neighbourhood.
you can only rearrange house based on the capacity of each neighbourhood . If neighbourhood "1" in input has 2 houses then at output also it can only have 2 houses.

For example-
{
{1,2},
{4,4,7,8},
{4,9,9,9}
}

becomes
{
{4,9},
{1,2,4,9},
{4,7,8,9}
}
 **/
public class HouseNeighbourhood {

    public static void main(String[] args)
    {
//        List<Integer> temp = new ArrayList<>(1);
//        temp.add(1);
//        temp.add(2);
//        System.out.println(temp.size());
        List<List<Integer>> testcase = Arrays.asList(
                Arrays.asList(1,2),
                Arrays.asList(4,4,7,8),
                Arrays.asList(4,9,9,9)
        );
        distributeHouse(testcase);
    }

    public static List<List<Integer>> distributeHouse(List<List<Integer>> neighbourhood)
    {
        int n= neighbourhood.size();
        Map<Integer, Integer> freq = new HashMap<>();
        for(List<Integer> city : neighbourhood)
        {
            for(Integer i : city)
            {
                freq.put(i, freq.getOrDefault(i, 0) + 1);
                if(freq.get(i) > n)
                {
                    throw new RuntimeException("can't distribute");
                }
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare( b[1], a[1]));
        for(Map.Entry<Integer, Integer> entry : freq.entrySet())
        {
            pq.add(new int[] {entry.getKey(), entry.getValue()});
        }

        int[] house_filled = new int[n];
        List<List<Integer>> ans = new ArrayList<>(n);
        for(int i=0;i<n;i++)
        {
            ans.add(new ArrayList<>());
        }
        while(!pq.isEmpty()) {
            int[] house = pq.poll();
            int cnt = house[1];
            for (int i = 0; i < n && cnt > 0; i++) {
                if (house_filled[i] != neighbourhood.get(i).size()) {
                    ans.get(i).add(house[0]);
                    house_filled[i]++;
                    cnt--;
                }
            }
        }
        return ans;
    }
}
