# deps

[![Build Status](https://travis-ci.org/arturbosch/deps.svg?branch=master)](https://travis-ci.org/arturbosch/deps)
[![CodeFactor](https://www.codefactor.io/repository/github/arturbosch/deps/badge)](https://www.codefactor.io/repository/github/arturbosch/deps)
[ ![Download](https://api.bintray.com/packages/arturbosch/generic/deps/images/download.svg) ](https://bintray.com/arturbosch/generic/deps/_latestVersion)
 
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
