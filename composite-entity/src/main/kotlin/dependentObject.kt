abstract class DependentObject<T> {
    var data: T? = null
}

object MessageDependentObject : DependentObject<String>()
object SignalDependentObject : DependentObject<String>()