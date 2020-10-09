package pro.edvard.ciat.business.usecases

import androidx.paging.PagingSource
import pro.edvard.ciat.business.domain.model.User
import pro.edvard.ciat.framework.data.network.abstraction.ReqresService
import pro.edvard.ciat.framework.data.network.util.NetworkResponse
import retrofit2.HttpException


class UserPagingSource(
    private val reqresService: ReqresService
) : PagingSource<Int, User>() {

    companion object {
        const val REQRES_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val page = params.key ?: REQRES_STARTING_PAGE_INDEX
        return try {
            when (val response = reqresService.getUsers(page)) {
                is NetworkResponse.Success -> {
                    response.body.let {
                        LoadResult.Page(
                            data = it.users,
                            prevKey = if (page == REQRES_STARTING_PAGE_INDEX) null else page - 1,
                            nextKey = if (it.users.isEmpty()) null else page + 1
                        )
                    }
                }

                else -> {
                    LoadResult.Page(
                        data = listOf(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}