package id.ac.ui.cs.mobileprogramming.justin.tripenary.utils

enum class Months {
    Jan,
    Feb,
    Mar,
    Apr,
    May,
    Jun,
    Jul,
    Aug,
    Sep,
    Oct,
    Nov,
    Dec;

    companion object {
        fun mmToMonth(mm : Int) : String {
            for (month in values()) {
                if (mm-1 == month.ordinal) {
                    return month.name
                }
            }
            return "invalid-mm"
        }
    }
}

