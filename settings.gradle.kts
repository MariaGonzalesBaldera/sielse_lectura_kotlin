import java.util.Properties

pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}
val keyProps = Properties().apply {
  file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    // Mapbox Maven repository
    maven {
      url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
      // Do not change the username below. It should always be "mapbox" (not your username).
      credentials.username = "mapbox"
      // Use the secret token stored in gradle.properties as the password
      credentials.password = "sk.eyJ1IjoibWFyaWFnYjk3IiwiYSI6ImNsdjRub21majBiaHMyaXBlMjdocHBtMnoifQ.mdb_gBAQGXPutfHvELO6EA"
      authentication.create<BasicAuthentication>("basic")
    }
  }
}

rootProject.name = "SIELSEAppLecturasKotlin"
include(":app")
 