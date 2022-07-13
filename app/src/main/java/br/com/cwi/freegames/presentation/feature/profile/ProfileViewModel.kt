package br.com.cwi.freegames.presentation.feature.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.freegames.domain.constants.ProfileConstants
import br.com.cwi.freegames.domain.entity.User
import br.com.cwi.freegames.domain.repository.UserRepository
import br.com.cwi.freegames.presentation.base.BaseViewModel

private const val EMPTY_PHOTO = ""

class ProfileViewModel(
    private val repository: UserRepository
): BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun fetchUser(){
        repository.getUser().let {
            _user.postValue(it)
        }
    }

    private fun createUser(user: User){
        repository.addProfile(user)
    }

    private fun editProfile(user: User){
        repository.editProfile(user)
    }

    fun createOrUpdateUser(name: String, email: String, photoPath: String?){
        val user = transformIntoUser(name, email, photoPath)

        if(repository.isProfileNotExists()){
            createUser(user)
        }else{
            editProfile(user)
        }
    }

    private fun transformIntoUser(name: String, email: String, photoPath: String?): User {
        val newName: String = name.ifEmpty { ProfileConstants.GUEST_NAME }
        val newEmail: String = email.ifEmpty { ProfileConstants.GUEST_EMAIL }
        val userOldPhoto = _user.value?.photo ?: EMPTY_PHOTO
        val newPhoto = photoPath ?: userOldPhoto

        return User(newName, newEmail, newPhoto)
    }
}