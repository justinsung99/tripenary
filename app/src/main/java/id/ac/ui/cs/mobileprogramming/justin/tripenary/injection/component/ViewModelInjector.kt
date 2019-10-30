package id.ac.ui.cs.mobileprogramming.justin.tripenary.injection.component

import dagger.Component
import id.ac.ui.cs.mobileprogramming.justin.tripenary.injection.module.NetworkModule
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
//    fun inject(postListViewModel: PostListViewModel)
//
//    @Component.Builder
//    interface Builder {
//        fun build(): ViewModelInjector
//
//        fun networkModule(networkModule: NetworkModule): Builder
//    }
}