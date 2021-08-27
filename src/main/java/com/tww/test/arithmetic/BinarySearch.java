package com.tww.test.arithmetic;

import scala.concurrent.impl.FutureConvertersImpl;

public class BinarySearch {
    public static void main(String[] args) {
        int base = base(new int[]{1, 3, 4, 5, 7, 8, 12}, 6);
        System.out.println("base : " + base);
        int firstEq = firstEq(new int[]{1, 2, 5, 5, 5, 7, 8, 12}, 5);
        System.out.println("firstEq : " + firstEq);
        int lastEq = lastEq(new int[]{1, 2, 5, 5, 5, 7, 8, 12}, 5);
        System.out.println("lastEq : " + lastEq);
        int lastLt = lastLt(new int[]{1, 2, 5, 5, 5, 7, 8, 12}, 1);
        System.out.println("lastLt : " + lastLt);
        int lastLte = lastLte(new int[]{1, 2, 5, 5, 5, 7, 8, 12}, 0);
        System.out.println("lastLte : " + lastLte);
    }

    // 基本二分查找
    private static int base(int[] arr, int target) {
        int length = arr.length;
        // 查找区间[0,length - 1],也可用查找区间[0,length)的方式
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                // 1.此处 +1 因为已经查找了[0,mid]区间，只需要查找[mid + 1,length]区间）
                // 2.防止死循环（当数组格式为[1,3] 查找2时，mid = 0,left = mid 会发生死循环
                left = mid + 1;
            } else if (arr[mid] > target) {
                // 同上面 +1 的原因
                right = mid - 1;
            }
        }
        return -1;
    }

    // 连续的目标值，找到第一个出现的目标值[1,2,2,2,3]
    private static int firstEq(int[] arr, int target) {
        int length = arr.length;
        // 查找区间[0,length - 1],也可用查找区间[0,length)的方式
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                // *******关键处理********
                // 相等时缩小查找空间, [left,right] => [left,mid - 1]
                // right不断向左推进，直到遇到一个小于target的值时停住等待left和它相遇,此时right即为target左边第一个数
                right = mid - 1;
            } else if (arr[mid] < target) {
                // 1.此处 +1 因为已经查找了[0,mid]区间，只需要查找[mid + 1,length]区间）
                // 2.防止死循环（当数组格式为[1,3] 查找2时，mid = 0,left = mid 会发生死循环
                left = mid + 1;
            } else if (arr[mid] > target) {
                // 同上面 +1 的原因
                right = mid - 1;
            }
        }

        // 返回值，right 有四种情况
        // 1.正常情况，right停在target左边第一个数上
        // 2.target比所有数大时，right处于 length-1 位置未向左推进过
        // 3.target不存在于数组中，此时right处于最后一个小于target的数上
        // 4.target比所有数x小,right位于-1
        // 综上，判断right是否位于 length-1 判断情况 2，判断 right + 1 是否等于 target 判断情况 3

        if (right == length - 1) {
            return -1;
        }

        if (arr[right + 1] != target) {
            return -1;
        }

        return right + 1;
    }

    // 连续的目标值，找到最后一个出现的目标值[1,2,2,2,3]
    // 和 firstEq()类似，不同的是 firstEq 是在 mid 和 target 相等时推进 right 向左缩小查找区间
    // 该方法需要的是，在 mid 和 target 相等时推进 left 向右缩小查找区间
    private static int lastEq(int[] arr, int target) {
        int length = arr.length;
        // 查找区间[0,length - 1],也可用查找区间[0,length)的方式
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                // *******关键处理********
                // 相等时缩小查找空间, [left,right] => [mid + 1,right]
                // left不断向右推进，直到遇到一个大于target的值时停住等待right和它相遇,此时left即为target右边第一个数
                left = mid + 1;
            } else if (arr[mid] < target) {
                // 1.此处 +1 因为已经查找了[0,mid]区间，只需要查找[mid + 1,length]区间）
                // 2.防止死循环（当数组格式为[1,3] 查找2时，mid = 0,left = mid 会发生死循环
                left = mid + 1;
            } else if (arr[mid] > target) {
                // 同上面 +1 的原因
                right = mid - 1;
            }
        }

        // 返回值，left 有三种情况，
        // 1.正常情况，left停在target右边第一个数上
        // 2.target比所有数小时，left处于 0 位置未向右推进过
        // 3.target不存在于数组中，此时left处于第一个大于target的数上
        // 4.target比所有数大，left位于length
        // 综上，判断left是否位于 0 判断情况 2，判断 left - 1 是否等于 target 判断情况 3

        if (left == 0) {
            return -1;
        }

        if (arr[left - 1] != target) {
            return -1;
        }

        return left - 1;
    }

    // 找到最后一个小于target的数
    public static int lastLt(int[] arr,int target) {
        int length = arr.length;
        // 查找区间[0,length - 1]
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                // 压缩区间，right向左推进
                right = mid - 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        // 1. 正常情况，right位于最后一个小于target的值
        // 2. target 比所有值大,right位于length - 1
        // 3. target 比所有值小,right位于-1

        return right;
    }

    //找到最后一个小于等于target的数
    public static int lastLte(int[] arr,int target) {
        int length = arr.length;
        // 查找区间[0,length - 1]
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                // 压缩区间，left向左推进
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            }
        }
        // 1. 正常情况，left位于第一个大于target的值
        // 2. target 比所有值大,left位于length
        // 3. target 比所有值小,left位于0

        if (left == 0) {
            return -1;
        } else {
            return left - 1;
        }
    }
}