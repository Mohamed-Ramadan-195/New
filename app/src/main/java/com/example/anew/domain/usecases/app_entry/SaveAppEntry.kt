package com.example.anew.domain.usecases.app_entry

import com.example.anew.domain.manger.LocalUserManger

class SaveAppEntry (
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }

}