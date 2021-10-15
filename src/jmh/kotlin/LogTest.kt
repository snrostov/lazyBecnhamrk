package justdoit

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole

@State(Scope.Benchmark)
open class LazyTest {

    class Expensive

    val lazy: Expensive by lazy { Expensive() }

    class Lazy {
        @State(Scope.Benchmark)
        companion object {
            val lazy = Expensive()
        }
    }

    @Benchmark
    fun lazyCompanion(bh: Blackhole) {
        bh.consume(Lazy.lazy)
    }

    @Benchmark
    fun lazyLazy(bh: Blackhole) {
        bh.consume(lazy)
    }
}