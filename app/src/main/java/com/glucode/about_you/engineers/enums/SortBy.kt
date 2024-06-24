package com.glucode.about_you.engineers.enums

enum class SortBy {
    NONE,
    YEARS_ASC,
    YEARS_DESC,
    COFFEES_ASC,
    COFFEES_DESC,
    BUGS_ASC,
    BUGS_DESC;

    fun toggle(): SortBy =
        when (this) {
            YEARS_ASC -> YEARS_DESC
            YEARS_DESC -> YEARS_ASC
            COFFEES_ASC -> COFFEES_DESC
            COFFEES_DESC -> COFFEES_ASC
            BUGS_ASC -> BUGS_DESC
            BUGS_DESC -> BUGS_ASC
            else -> this
        }
}
