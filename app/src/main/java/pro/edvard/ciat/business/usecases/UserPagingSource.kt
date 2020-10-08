package pro.edvard.ciat.business.usecases

import androidx.paging.PagingSource
import pro.edvard.ciat.business.domain.model.User
import pro.edvard.ciat.framework.data.network.abstraction.ReqresService
import pro.edvard.ciat.framework.data.network.util.NetworkResponse

class UserPagingSource(
    private val reqresService: ReqresService
): PagingSource<Int, User>() {

    companion object {
        const val DEFAULT_INIT_KEY = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: DEFAULT_INIT_KEY
        return try {
            when (val response = reqresService.getUsers(page)) {
                is NetworkResponse.Success -> {
                    response.body.let {
                        LoadResult.Page(
                            data = it.users,
                            prevKey = if (page > 1) page-1 else null,
                            nextKey = page + 1
                        )
                    }
                }

                else -> {
                    LoadResult.Page(
                        data = listOf(),
                        prevKey = null,
                        nextKey = page + 1
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}