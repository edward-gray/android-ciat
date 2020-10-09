package pro.edvard.ciat.business.usecases

import pro.edvard.ciat.framework.data.cache.abstraction.UserDaoService
import pro.edvard.ciat.framework.data.cache.abstraction.UserRemoteKeyDaoService

class RefreshUsers(
    private val userDaoService: UserDaoService,
    private val userRemoteKeyDaoService: UserRemoteKeyDaoService
) {

    suspend fun execute() {
        userDaoService.reset()
        userRemoteKeyDaoService.reset()
    }

}