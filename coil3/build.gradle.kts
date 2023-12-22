

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidLibrary)
}

android {
  namespace = "uz.rsteam.coil3"
  compileSdk = 34
  defaultConfig {
    minSdk = 24
  }
}


kotlin {
  applyDefaultHierarchyTemplate()
  androidTarget {
    compilations.all {
      kotlinOptions {
        jvmTarget = "1.8"
      }
    }
  }
  iosX64()
  iosArm64()
  iosSimulatorArm64()

  sourceSets {
    val commonMain by getting {
      dependencies {

        api(libs.coil3)
        api(libs.coil3.network)
      }
    }
    val androidMain by getting {
      dependsOn(commonMain)
      dependencies {
        api(libs.coil3.gif)
        api(libs.coil3.svg)
        api(libs.coil3.video)
        api(libs.ktor.engine.android)
      }
    }
    val iosMain by getting {
      dependsOn(commonMain)
      dependencies {
        api(libs.ktor.engine.darwin)
      }
    }
  }
}
