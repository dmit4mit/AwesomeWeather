package com.dmitron.domain.usecases

/**
 * Common interface for use cases. Each use case must be synchronous.
 * For use case with params see [ParamsUseCase]
 */
interface SimpleUseCase<T> {
    suspend operator fun invoke(): T
}