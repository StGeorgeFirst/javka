package course.task;

import static course.task.ArraySort.*;

public class App {

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 7, 8, 5, 6, 9, 8};
        var staticArray = new StaticArray(a);
        ///
        System.out.println("------------");
        System.out.println(staticArray.toString());
        System.out.println("start sort");
        staticArray.sort(QUICK);
        System.out.println("end sort");
        System.out.println(staticArray.toString());
        System.out.println("------------");
        ///
        System.out.println("------------");
        System.out.println(staticArray);
        System.out.println("------------");

        var sortedArray = new SortedArray(a);
        System.out.println(sortedArray);

        var dynamicArray = new DynamicArray();
        dynamicArray.add(0);
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);
        dynamicArray.add(4);
        dynamicArray.add(5);
        dynamicArray.add(6);
        dynamicArray.add(7);
        dynamicArray.add(8);
        dynamicArray.add(9);
//        dynamicArray.add(11);
        System.out.println(dynamicArray);
        Array arr = new StaticArray(new int[]{3, 4});
        dynamicArray.addAll(arr);
        System.out.println(dynamicArray);

        SortedArray sa1 = new SortedArray(new int[]{1, 2, 3, 4, 5});
        System.out.println(sa1.indexOf(3));
        SortedArray sa2 = new SortedArray(new int[]{6, 7, 8, 9, 10});
        System.out.println(sa1);
        System.out.println(sa2);
        SortedArray sa3 = sa2.merge(sa1);
        System.out.println(sa3);

        SortedArray sa4 = sa1.merge(sa2);
        System.out.println(sa4);

        SortedArray sa5 = new SortedArray(new int[]{1, 2, 3, 4, 5});
        SortedArray sa6 = new SortedArray(new int[]{-7, -8, 0});
        SortedArray sa7 = new SortedArray(new int[]{1, 4, 8, 8});
        sa5.mergeAll(sa7, sa6, sa5);
        System.out.println(sa5.mergeAll(sa7, sa6));
        System.out.println(sa5.mergeAll(sa6, sa7));
        System.out.println(sa5.mergeAll(sa6, sa7));

    }
}
