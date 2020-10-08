package pro.edvard.ciat.framework.data.cache.mapper

import pro.edvard.ciat.business.domain.model.User
import pro.edvard.ciat.business.domain.util.EntityMapper
import pro.edvard.ciat.framework.data.cache.model.UserCacheEntity
import javax.inject.Inject

class UserCacheMapper
@Inject
constructor(): EntityMapper<UserCacheEntity, User> {

    override fun mapFromEntity(entity: UserCacheEntity): User {
        return User(
            id = entity.id,
            email = entity.email,
            firstName = entity.firstName,
            lastName = entity.lastName,
            avatar = entity.avatar
        )
    }

    override fun mapToEntity(domainModel: User): UserCacheEntity {
        return UserCacheEntity(
            id = domainModel.id,
            email = domainModel.email,
            firstName = domainModel.firstName,
            lastName = domainModel.lastName,
            avatar = domainModel.avatar
        )
    }

}