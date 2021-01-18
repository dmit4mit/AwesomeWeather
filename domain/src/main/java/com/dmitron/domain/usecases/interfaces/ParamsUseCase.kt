package com.dmitron.domain.usecases.interfaces

import com.dmitron.common.ResultWrapper
import kotlinx.coroutines.flow.Flow

/**
 * Common interface for use cases which require parameters. Each use case must be synchronous.
 * For use case without params see [UseCase]
 */
internal interface ParamsUseCase<T, Params> {
    suspend operator fun invoke(params: Params): Flow<ResultWrapper<T>>
}