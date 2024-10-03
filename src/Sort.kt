class Sort {
    fun bubbleSort(array: IntArray): IntArray {
        val n = array.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (array[j] > array[j + 1]) {
                    val temp = array[j]
                    array[j] = array[j + 1]
                    array[j + 1] = temp
                }
            }
        }
        return array
    }

    fun quickSort(array: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pi = partition(array, low, high)
            quickSort(array, low, pi - 1)
            quickSort(array, pi + 1, high)
        }
    }

    private fun partition(array: IntArray, low: Int, high: Int): Int {
        val pivot = array[high]
        var i = low - 1
        for (j in low until high) {
            if (array[j] < pivot) {
                i++
                val temp = array[i]
                array[i] = array[j]
                array[j] = temp
            }
        }
        val temp = array[i + 1]
        array[i + 1] = array[high]
        array[high] = temp
        return i + 1
    }

    fun getRandomArray(size: Int): IntArray {
        val intArray = IntArray(size)
        for (i in 0 until size) {
            intArray[i] = (Math.random() * 100).toInt()
        }
        return intArray
    }

    fun printArray(int: IntArray) {
        for ((n, i) in int.withIndex()) {
            print("$i ")
            if (n != 0 && n % 20 == 0) {
                println()
            }
        }
        println()
        println("=====================================\n")
    }
}

fun main() {
    val sort = Sort()
    val arrayLength = 1000
    val bubbleSortArray = sort.getRandomArray(arrayLength)

    println("Array length for sorting: $arrayLength\n")
    println("Bubble sort\n")
    println("Unsorted array:")
    sort.printArray(bubbleSortArray)

    val startTimeBubbleSort = System.nanoTime()
    sort.bubbleSort(bubbleSortArray)
    val endTimeBubbleSort = System.nanoTime()

    println("Bubble sorted array:")
    sort.printArray(bubbleSortArray)

    val durationBubbleSort = (endTimeBubbleSort - startTimeBubbleSort) / 1000000.0 // Convert to milliseconds
    println("Execution time: $durationBubbleSort ms")

    val quickSortArray = sort.getRandomArray(arrayLength)

    println()
    println("Quick sort\n")
    println("Unsorted array:")
    sort.printArray(quickSortArray)

    val startTimeQuickSort = System.nanoTime()
    sort.quickSort(quickSortArray, 0, quickSortArray.size - 1)
    val endTimeQuickSort = System.nanoTime()

    println("Quick sorted array:")
    sort.printArray(quickSortArray)


    val durationQuickSort = (endTimeQuickSort - startTimeQuickSort) / 1000000.0 // Convert to milliseconds
    println("Execution time: $durationQuickSort ms\n\n")

    println("Bubble sort time: $durationBubbleSort ms, Quick sort time: $durationQuickSort ms")
    println("Difference in execution time: ${durationBubbleSort - durationQuickSort} ms")
}