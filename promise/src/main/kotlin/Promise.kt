import java.util.concurrent.Executor

class Promise<T> : PromiseSupport<T?>() {
    private var fulfillmentAction: (() -> Unit)? = null
    private var exceptionHandler: ((Throwable?) -> Unit)? = null

    override fun fulfill(value: T?) {
        super.fulfill(value)
        postFulfillment()
    }

    override fun fulfillExceptionally(exception: Exception?) {
        super.fulfillExceptionally(exception)
        handleException(exception)
        postFulfillment()
    }

    private fun handleException(exception: Exception?) {
        exceptionHandler?.invoke(exception)
    }

    private fun postFulfillment() {
        fulfillmentAction?.invoke()
    }

    fun fulfillInAsync(executor: Executor, task: () -> T): Promise<T> = apply {
        executor.execute {
            try {
                fulfill(task())
            } catch (e: Exception) {
                fulfillExceptionally(e)
            }
        }
    }

    fun thenAccept(action: (T?) -> Unit): Promise<Unit> {
        val dest = Promise<Unit>()
        fulfillmentAction = ConsumeAction(this, dest, action)
        return dest
    }

    fun onError(exceptionHandler: (Throwable?) -> Unit): Promise<T> = apply {
        this.exceptionHandler = exceptionHandler
    }

    fun <V> thenApply(func: (T?) -> V): Promise<V> {
        val dest = Promise<V>()
        fulfillmentAction = TransformAction(this, dest, func)
        return dest
    }

    private inner class ConsumeAction(
        private val src: Promise<T>,
        private val dest: Promise<Unit>,
        private val action: (T?) -> Unit
    ) : () -> Unit {
        override fun invoke() = try {
            action(src.get())
            dest.fulfill(null)
        } catch (throwable: Throwable) {
            dest.fulfillExceptionally(throwable.cause as Exception?)
        }
    }

    private inner class TransformAction<V>(
        private val src: Promise<T>,
        private val dest: Promise<V>,
        private val func: (T?) -> V
    ) : () -> Unit {
        override fun invoke() = try {
            dest.fulfill(func(src.get()))
        } catch (throwable: Throwable) {
            dest.fulfillExceptionally(throwable.cause as Exception?)
        }
    }
}
