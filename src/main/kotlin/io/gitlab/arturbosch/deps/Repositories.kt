package io.gitlab.arturbosch.deps

import org.eclipse.aether.repository.LocalRepository
import org.eclipse.aether.repository.RemoteRepository
import org.eclipse.aether.repository.RepositoryPolicy
import java.io.File

fun defaultRepositories() = listOf(mavenCentral(), jcenter())

fun jcenter(): RemoteRepository {
    return RemoteRepository.Builder(
        "bintray", "default", "https://jcenter.bintray.com"
    ).setSnapshotPolicy(
        RepositoryPolicy(
            false, RepositoryPolicy.UPDATE_POLICY_NEVER,
            RepositoryPolicy.CHECKSUM_POLICY_IGNORE
        )
    ).build()
}

fun mavenCentral(): RemoteRepository =
    RemoteRepository.Builder(
        "central", "default", "https://repo1.maven.org/maven2/"
    ).setSnapshotPolicy(
        RepositoryPolicy(
            false, RepositoryPolicy.UPDATE_POLICY_NEVER,
            RepositoryPolicy.CHECKSUM_POLICY_IGNORE
        )
    ).build()

fun localRepository(): LocalRepository =
    LocalRepository(File(File(System.getProperty("user.home"), ".m2"), "repository"))
