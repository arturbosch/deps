# deps

Leverages maven-resolver to resolve transitive dependencies. 

## Getting started

### with Gradle

`implementation("io.gitlab.arturbosch:deps:[version]")`

### with Maven

```xml
<dependency>
    <groupId>io.gitlab.arturbosch</groupId>
    <artifactId>deps</artifactId>
    <version>[version]</version>
</dependency>
```

### with downloading this repo

- `git clone https://github.com/arturbosch/deps`
- `cd deps`
- `gradle build publishToMavenLocal`

## Example

```kotlin
@Test
fun `resolves junit and the transitive dependency hamcrest`() {
    val deps = Deps()
    val artifact = Artifact("junit:junit:4.12")

    val paths: List<File> = deps.resolve(artifact)

    assertTrue { paths.includes("junit-4.12.jar") }
    assertTrue { paths.includes("hamcrest-core-1.3.jar") }
}
```
