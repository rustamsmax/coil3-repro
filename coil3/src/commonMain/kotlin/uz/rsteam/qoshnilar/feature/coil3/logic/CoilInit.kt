package uz.rsteam.qoshnilar.feature.coil3.logic

import coil3.ComponentRegistry
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.disk.DiskCache
import coil3.util.DebugLogger
import io.ktor.client.HttpClient
import okio.Path.Companion.toPath
//import uz.rsteam.qoshnilar.core.base.api.PathProducer
//import uz.rsteam.qoshnilar.core.base.api.PlatformInfo

@OptIn(ExperimentalCoilApi::class)
fun initCoil3(context: PlatformContext, debug: Boolean) {
  SingletonImageLoader.set {
    ImageLoader.Builder(context)
      .components {
        add(coil3.fetch.NetworkFetcher.Factory(lazy { httpClient() }))
        addPlatformComponents()
      }
      .configureDefaultCache()
      .apply {
        if (debug)
          logger(DebugLogger())
      }
      .build()
  }
}

internal val defaultHeaders: Map<String, String>
  get() = mapOf(
    "x-app-version" to "", // PlatformInfo.getAppVersion(),
    "x-os-name" to "" // PlatformInfo.getOsName().name
  )

internal fun ImageLoader.Builder.configureDefaultCache() = diskCache(
  DiskCache.Builder()
//    .directory(PathProducer.produceCachePath("image_cache").toPath())
    .maxSizeBytes(1024 * 1024 * 1024L) // 1GB
    .build()
)

internal expect fun httpClient(): HttpClient
internal expect fun ComponentRegistry.Builder.addPlatformComponents()
