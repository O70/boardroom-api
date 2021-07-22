# Boardroom API

## Dependencies

### [JitPack](https://jitpack.io)

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.THRAEXS:toolkit:dev-SNAPSHOT'
}
```

### Gradle

> [添加构建依赖项](https://developer.android.com/studio/build/dependencies?hl=zh-cn)

> [Settings](https://docs.gradle.org/current/dsl/org.gradle.api.initialization.Settings.html)


依赖外部项目
- Way 1

**Deprecated Gradle features were used in this build, making it incompatible with Gradle 8.0.**

`settings.gradle`:
```groovy
// ...

include('toolkit')
//project(':toolkit').projectDir = new File(settingsDir, '../toolkit')
project(':toolkit').projectDir = file('../toolkit')
```

`build.gradle`:
```groovy
dependencies {
	implementation project(':toolkit')
}
```

- Way 2

> [DependencyHandler](https://docs.gradle.org/current/dsl/org.gradle.api.artifacts.dsl.DependencyHandler.html)

`build.gradle`:
```groovy
dependencies {
	implementation fileTree(dir: '../toolkit/build/libs', includes: ['*.jar'])
	// or
	implementation fileTree('../toolkit/build/libs')
	// or
	implementation files('../toolkit/build/libs/toolkit-1.0.0.jar')
}
```
