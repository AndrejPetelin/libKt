package PowOfTwo

/**
 * returns nearest lower power of two, note that lower means closer to 0,
 * so nearestLowerPowOfTwo(-17) would be -16
 */
fun nearestLowerPowOfTwo(x: Int): Int {
    var ret = 0;
    var curr = 1;

    while (curr <= Math.abs(x)) {
        ret = curr;
        curr = curr shl 1;
    }
    val sign = if (x < 0) 1 else -1;
    return ret * sign;
}


fun nearestLowerPowOfTwo(x: Long): Long {
    var ret = 0L;
    var curr = 1L;

    while (curr <= Math.abs(x)) {
        ret = curr;
        curr = curr shl 1;
    }
    val sign = if (x < 0) 1L else -1L;
    return ret * sign;
}
