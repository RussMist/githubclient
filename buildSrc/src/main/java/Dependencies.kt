@Suppress("MemberVisibilityCanBePrivate")
object Dependencies {
    object Project {
        const val domain = ":domain"
        const val githubCore = ":github:core"
    }
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val androidCoreKtx = "androidx.core:core-ktx:${Versions.Libs.coreKtx}"
    const val annotation = "androidx.annotation:annotation:${Versions.Libs.annotation}"

    /* Android UI libs */
    const val appcompat = "androidx.appcompat:appcompat:${Versions.Libs.appcompat}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.Libs.activityKtx}"
    const val material = "com.google.android.material:material:${Versions.Libs.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Libs.constraintLayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.Libs.recyclerView}"
    /* End of Android UI libs */

    /* Testing libs */
    const val junit = "junit:junit:${Versions.Libs.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.Libs.extJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.Libs.espressoCore}"
    /* End of Testing libs */

    /* Koin libs */
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.Libs.koin}"
    const val koinViewmodel = "org.koin:koin-androidx-viewmodel:${Versions.Libs.koin}"
    const val koinFragment = "org.koin:koin-androidx-fragment:${Versions.Libs.koin}"
    const val koinExt = "org.koin:koin-androidx-ext:${Versions.Libs.koin}"
    const val koinTest = "org.koin:koin-test:${Versions.Libs.koin}"
    /* End of Koin libs */

    /* Android lifecycle libs */
    const val androidLifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Versions.Libs.androidLifecycle}"
    const val androidLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Libs.androidLifecycle}"
    const val androidLifecycleViewModelSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.Libs.androidLifecycle}"
    const val androidLifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Libs.androidLifecycle}"
    const val androidLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Libs.androidLifecycle}"
    /* End of Android lifecycle libs */

    /* Retrofit libs */
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Libs.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.Libs.retrofit}"
    /* End of Retrofit libs */

    /* Facebook Shimmer lib */
    const val facebookShimmer = "com.facebook.shimmer:shimmer:${Versions.Libs.facebookShimmer}"
    /* End of Facebook Shimmer lib */

    /* Android Paging lib */
    const val androidPaging = "androidx.paging:paging-runtime:${Versions.Libs.androidPaging}"
    /* End of Android Paging lib */

    /* Coil lib */
    const val coil = "io.coil-kt:coil:${Versions.Libs.coil}"
    /* End of Coil lib */

    /* Navigation libs */
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.Libs.nav}"
    const val navUI = "androidx.navigation:navigation-ui-ktx:${Versions.Libs.nav}"
    /* End of Navigation libs */


    val androidCoreLibs: List<String> = listOf(
        kotlinStdLib,
        androidCoreKtx,
        annotation
    )

    val androidUiLibs: List<String> = listOf(
        appcompat,
        activityKtx,
        material,
        constraintLayout,
        recyclerview
    )

    val androidLifecycleLibs: List<String> = listOf(
        androidLifecycleExt,
        androidLifecycleViewModel,
        androidLifecycleViewModelSavedState,
        androidLifecycleLiveData,
        androidLifecycleRuntime
    )

    val koinLibs: List<String> = listOf(
        koinScope,
        koinViewmodel,
        koinFragment,
        koinExt
    )

    val retrofitLibs: List<String> = listOf(
        retrofit,
        retrofitGson
    )

    val navLibs: List<String> = listOf(
        navFragment,
        navUI
    )

    val androidTestLibs: List<String> = listOf(
        extJunit,
        espressoCore
    )

    val testLibs: List<String> = listOf(
        koinTest,
        junit
    )
}