fun main(args: Array<String>) {

    fun convert(s: String, numRows: Int): String {
        if ((numRows == 1) or (numRows >= s.length))
            return s

        val lastRowIndex = numRows - 1
        val increase = 2 * (numRows - 1)
        val charArray = CharArray(s.length)
        var index = 0

        for (row in 0..lastRowIndex) {
            var i = row

            while (i < s.length) {
                charArray[index++] = s.elementAt(i)

                if (row in 1..(lastRowIndex - 1) && (i + increase - row * 2) < s.length) {
                    charArray[index++] = s.elementAt(i + increase - row * 2)
                }
                i += increase
            }
        }
        return String(charArray)
    }


    println(convert("A", 1))
}