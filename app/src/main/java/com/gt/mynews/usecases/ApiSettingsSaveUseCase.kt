package com.gt.mynews.usecases

import com.gt.mynews.data.repositories.SharedPreferencesInterface

class ApiSettingsSaveUseCase(private val repo: SharedPreferencesInterface): SettingsSaveUseCaseInterface {

    override fun saveKeyword(stringToSave: String?){
        repo.saveApiRequestKeyword(stringToSave)
    }

    override fun saveKeywordFilter(keywordFiltersToSave: Collection<String>){
        val oldKeywordFilter = repo.getKeywordFilterToSearch()

        val totalKeywordFilters: Collection<String> = keywordFiltersToSave.plus(oldKeywordFilter).distinct()

        repo.saveApiRequestKeywordFilter(totalKeywordFilters)
    }

    override fun getKeyword(): String{
        return repo.getKeywordToSearch() ?: return ""
    }

    override fun getKeywordFilter(): Collection<String> = repo.getKeywordFilterToSearch()

}