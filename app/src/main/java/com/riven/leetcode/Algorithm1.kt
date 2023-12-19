package com.riven.leetcode

import java.util.*

/**
 * Function:
 * Author Name: Riven.zhang
 * Date: 2022/8/17
 * Copyright © 2006-2021 高顿网校, All Rights Reserved.
 */
object Algorithm1 {

    /**
     * 1.两数之和
     *
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，
     * 并返回它们的数组下标。
     */
    fun twoSum(nums: IntArray, target: Int): IntArray? {
        val n = nums.size
        for (i in 0 until n) {// 0-->n
            for (j in i + 1 until n) {// i+1-->n
                if (nums[i] + nums[j] == target) {// 判断条件
                    return intArrayOf(i, j)// 满足,返回两数下标
                }
            }
        }
        return IntArray(0)
    }

    /**
     * 2.两数相加
     *
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，
     * 并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var l1 = l1
        var l2 = l2
        val dummy = ListNode(-1)
        var cur = dummy
        var carry = 0
        while (l1 != null || l2 != null) {
            val d1 = l1?.value ?: 0
            val d2 = l2?.value ?: 0
            val sum = d1 + d2 + carry
            carry = if (sum >= 10) 1 else 0
            cur.next = ListNode(sum % 10)
            cur = cur.next
            if (l1 != null) l1 = l1.next
            if (l2 != null) l2 = l2.next
        }
        if (carry == 1) cur.next = ListNode(1)
        return dummy.next
    }

    /**
     * 3.无重复字符串的最长子串
     *
     * Q:给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 用map记录下标，比如字符串abcdecf，到遇到第二个c，即便从bcde任意一个开始，
     * 长度都无法超过a，只有从decf开始计算才是新一轮查找
     */
    fun lengthOfLongestSubstring(s: String?): String {
        // 异常输入排查
        if (s == null || s.isEmpty()) {
            return "0"
        }
        var ans = 0 // 左右指针的绝对值
        val len = s.length //字符串长度
        var start = 0 // 左指针，相当于最长子串左的位置
        var end = 0 // 右指针，相当于最长子串右的位置
        val map: HashMap<Char, Int?> = HashMap()//使用map记录下标，key-字符,value-下标

        // 右指针不断向前，直到字符串尾部
        while (end < len) {
            ans = Math.max(ans, end - start)
            // 当遇到重复值，说明左指针需要跳转，跳转的位置是该重复值的下标+1
            // 比如字符串abcdecf，到遇到第二个c，即便从bcde任意一个开始，长度都无法超过a，只有从decf开始计算才是新一轮查找
            // 值得注意的是，如果碰到了重复值的下标比左指针还小的情况，不应该跳转，因为左指针左边的元素不再窗口内，比如abba
            if (map.containsKey(s[end]) && map[s[end]]!! >= start) {
                start = map[s[end]]!! + 1
            }
            // 无论重不重复都需要更新，该元素最近的下标并使用map记录
            map[s[end]] = end
            end++
        }
        ans = Math.max(ans, end - start)
        return "从第$start" + "开始,到第$end" + "位结束,总length：$ans"
    }

    /**
     * 4.寻找两个正序数组的中位数
     *
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     */
    fun findMedianSortedArrays(A: IntArray, B: IntArray): Double {
        val m = A.size
        val n = B.size
        val len = m + n
        var left = -1
        var right = -1
        var aStart = 0
        var bStart = 0
        for (i in 0..len / 2) {//0..len/2位置
            left = right
            // aStart＜m&&A[aStart]< B[bStart] 移动A
            // bStart >= n 防止b数组越界
            right = if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                A[aStart++]
            } else {
                B[bStart++]
            }
        }
        return if (len and 1 == 0) (left + right) / 2.0 else right.toDouble()
    }

    /**
     * 5.最长回文子串
     *
     * 中心扩散算法
     */
    fun longestPalindrome(s: String?): String? {
        if (s == null || s.length < 2) {
            return s
        }
        val strLen = s.length
        var maxStart = 0 //最长回文串的起点
        var maxEnd = 0 //最长回文串的终点
        var maxLen = 1 //最长回文串的长度
        val dp =
            Array(strLen) { BooleanArray(strLen) }
        for (r in 1 until strLen) {
            for (l in 0 until r) {
                if (s[l] == s[r] && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1
                        maxStart = l
                        maxEnd = r
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1)
    }


}