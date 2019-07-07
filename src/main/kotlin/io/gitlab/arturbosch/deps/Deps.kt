package io.gitlab.arturbosch.deps

import org.apache.maven.repository.internal.MavenRepositorySystemUtils
import org.eclipse.aether.RepositorySystem
import org.eclipse.aether.RepositorySystemSession
import org.eclipse.aether.collection.CollectRequest
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory
import org.eclipse.aether.impl.DefaultServiceLocator
import org.eclipse.aether.repository.LocalRepository
import org.eclipse.aether.repository.RemoteRepository
import org.eclipse.aether.repository.RepositoryPolicy
import org.eclipse.aether.resolution.DependencyRequest
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory
import org.eclipse.aether.spi.connector.transport.TransporterFactory
import org.eclipse.aether.transport.file.FileTransporterFactory
import org.eclipse.aether.transport.http.HttpTransporterFactory
import org.eclipse.aether.util.graph.visitor.PreorderNodeListGenerator
import java.io.File

class Deps(
    localRepository: LocalRepository = localRepository(),
    private val remoteRepositories: List<RemoteRepository> = defaultRepositories()
) {

    private val session: RepositorySystemSession
    private val repositorySystem: RepositorySystem

    init {
        val locator = MavenRepositorySystemUtils.newServiceLocator()
        locator.addService(RepositoryConnectorFactory::class.java, BasicRepositoryConnectorFactory::class.java)
        locator.addService(TransporterFactory::class.java, FileTransporterFactory::class.java)
        locator.addService(TransporterFactory::class.java, HttpTransporterFactory::class.java)
        locator.setErrorHandler(object : DefaultServiceLocator.ErrorHandler() {
            override fun serviceCreationFailed(type: Class<*>?, impl: Class<*>?, ex: Throwable) {
                error("Could not provide service $type ($impl). Exiting:\n$ex")
            }
        })
        repositorySystem = locator.getService(RepositorySystem::class.java)
        session = MavenRepositorySystemUtils.newSession()
        session.localRepositoryManager = repositorySystem.newLocalRepositoryManager(session, localRepository)
        session.updatePolicy = RepositoryPolicy.UPDATE_POLICY_ALWAYS
    }

    fun resolve(vararg artifacts: Artifact): List<File> = resolve(artifacts.toList())

    fun resolve(artifacts: List<Artifact>): List<File> {
        val collectRequest = CollectRequest()
        artifacts.forEach { collectRequest.addDependency(it.asDependency()) }
        remoteRepositories.forEach { collectRequest.addRepository(it) }
        val node = repositorySystem.collectDependencies(session, collectRequest).root
        repositorySystem.resolveDependencies(session, DependencyRequest(node, null))
        return PreorderNodeListGenerator().apply { node.accept(this) }.files
    }
}
