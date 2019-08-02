package com.gt.mynews.usecases

import com.gt.mynews.data.repositories.SharedPreferencesInterface

class ApiSettingsSaveUseCase(private val repo: SharedPreferencesInterface): SettingsSaveUseCaseInterface {

    override fun saveKeyword(stringToSave: String?){
        repo.saveApiRequestKeyword(stringToSave)
    }

    override fun saveKeywordFilter(keywordFiltersToSave: Collection<String>){
        repo.saveApiRequestKeywordFilter(keywordFiltersToSave)
    }

    override fun getKeyword(): String{
        return repo.getKeywordToSearch() ?: return ""
    }

    override fun getKeywordFilter(): Collection<String> = repo.getKeywordFilterToSearch()

}