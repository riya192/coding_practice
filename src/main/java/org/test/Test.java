package org.test;

import java.util.ArrayList;
import java.util.List;

/**
//Given a set of time intervals in any order, our task is to merge all overlapping intervals into one and output the result which should have only mutually exclusive intervals.
//
//        Example:
//
//Input: arr = {{1,3},{2,4},{6,8},{9,10}}
//Output: {{1, 4}, {6, 8}, {9, 10}}
//Explanation: Given intervals: [1,3],[2,4],[6,8],[9,10], we have only two overlapping intervals here,[1,3] and [2,4]. Therefore we will merge these two and return [1,4],[6,8], [9,10].
//
//Input: arr = {{6,8},{1,9},{2,4},{4,7}}
//Output: {{1, 9}}
 **/

class Interval implements Comparable
{
    int strt;
    int end;

    Interval(int s, int e)
    {
        this.strt = s;
        this.end = e;
    }

    @Override
    public int compareTo(Object o) {
        Interval interval = (Interval) o;
        if(interval.strt == this.strt) return Integer.compare(this.end, interval.end);
        return Integer.compare(this.strt, interval.strt);
    }

    @Override
    public String toString() {
        return "Interval{" +
                "strt=" + strt +
                ", end=" + end +
                '}';
    }
}
public class Test {

    public static void main(String[] args)
    {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(-5,1));
        intervals.add(new Interval(-3,2));
        intervals.add(new Interval(0, 6));
//        intervals.add(new Interval(1, 7));
        List<Interval> merged = getMergedInterval(intervals);
        for(Interval newInterval : merged)
        {
            System.out.println(newInterval.toString());
        }
    }

    private static List<Interval> getMergedInterval(List<Interval> intervals)
    {
        if(intervals.size() == 1) return intervals;
        intervals.sort(Interval::compareTo);
        List<Interval> mergedIntervals = new ArrayList<>();
        Interval prevInterval = intervals.get(0);

        for(int i=1;i< intervals.size();i++)
        {
            Interval curr = intervals.get(i);
            if(prevInterval.end < curr.strt) //no overlap
            {
                mergedIntervals.add(prevInterval);
                prevInterval = curr;
            }
            else {
                prevInterval = new Interval(Math.min(prevInterval.strt, curr.strt), Math.max(prevInterval.end, curr.end));
            }
        }
        mergedIntervals.add(prevInterval);
        return mergedIntervals;
    }
}
