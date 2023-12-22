package uz.rsteam.coil3test

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform