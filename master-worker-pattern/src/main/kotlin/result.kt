abstract class Result<T>(val data: T)
class ArrayResult(data: Array<IntArray>) : Result<Array<IntArray>>(data)