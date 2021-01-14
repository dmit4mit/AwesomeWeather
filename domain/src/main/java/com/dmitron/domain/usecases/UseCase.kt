package com.dmitron.domain.usecases

import com.dmitron.common.ResultWrapper
import kotlinx.coroutines.flow.Flow

/**
 * Common interface for use cases to receive result directly.
 */
internal interface UseCase<T> {
    suspend operator fun invoke(): Flow<ResultWrapper<T>>
}
