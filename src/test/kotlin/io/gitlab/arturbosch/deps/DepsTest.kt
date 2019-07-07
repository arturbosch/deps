package io.gitlab.arturbosch.deps

import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class DepsTest {

    @Test
    fun `resolves junit and the transitive dependency hamcrest`() {
        val deps = Deps()
        val artifact = Artifact("junit:junit:4.12")

        val paths = deps.resolve(artifact)

        assertTrue { paths.includes("junit-4.12.jar") }
        assertTrue { paths.includes("hamcrest-core-1.3.jar") }
    }

    private fun List<File>.includes(artifact: String) =
        find { it.toString().contains(artifact) } != null
}
