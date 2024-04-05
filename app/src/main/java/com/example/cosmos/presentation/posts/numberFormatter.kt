package com.example.cosmos.presentation.posts

// to format the number of likes etc;

fun formatNumber(num: Long): String {
    val abbreviations = listOf(
        1_000_000_000L to "B",
        1_000_000L to "M",
        1_000L to "k"
    )

    for ((factor, suffix) in abbreviations) {
        if (Math.abs(num) >= factor) {
            val formattedNum = num.toDouble() / factor
            return String.format("%.1f%s", formattedNum, suffix)
        }
    }

    return num.toString()
}