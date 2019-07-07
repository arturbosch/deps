package io.gitlab.arturbosch.deps

import org.eclipse.aether.artifact.DefaultArtifact
import org.eclipse.aether.graph.Dependency

data class Artifact(val mavenLikeCoordinates: String, val scope: String = "compile") {

    fun asDependency() = Dependency(DefaultArtifact(mavenLikeCoordinates), scope)
}
