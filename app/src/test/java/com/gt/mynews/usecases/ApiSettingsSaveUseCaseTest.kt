package com.gt.mynews.usecases

import com.gt.mynews.data.repositories.SharedPreferencesInterface
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiSettingsSaveUseCaseTest {

    private val mockSharedPreferencesInterface = Mockito.mock(SharedPreferencesInterface::class.java)

    private val apiSettingsSaveUseCase = ApiSettingsSaveUseCase(mockSharedPreferencesInterface)

    @Test
    fun `should save empty collection when no keyword filters`(){
        //given
        val keywordFilterToSave: Collection<String> = setOf()
        doReturn(setOf<String>()).`when`(mockSharedPreferencesInterface).getKeywordFilterToSearch()

        //when
        apiSettingsSaveUseCase.saveKeywordFilter(keywordFilterToSave)

        //then
        verify(mockSharedPreferencesInterface).saveApiRequestKeywordFilter(eq(listOf()))
    }

    @Test
    fun `should save new collection when new keyword filters`(){
        //given
        val keywordFilterToSave: Collection<String> = setOf("hello", "non")
        doReturn(setOf<String>()).`when`(mockSharedPreferencesInterface).getKeywordFilterToSearch()

        //when
        apiSettingsSaveUseCase.saveKeywordFilter(keywordFilterToSave)

        //then
        verify(mockSharedPreferencesInterface).saveApiRequestKeywordFilter(eq(listOf("hello", "non")))
    }

    @Test
    fun `should save new collection plus old one when new keyword filters and old one non null`(){
        //given
        val keywordFilterToSave: Collection<String> = setOf("hello", "non")
        doReturn(setOf("oui", "peut-être")).`when`(mockSharedPreferencesInterface).getKeywordFilterToSearch()

        //when
        apiSettingsSaveUseCase.saveKeywordFilter(keywordFilterToSave)

        //then
        verify(mockSharedPreferencesInterface).saveApiRequestKeywordFilter(eq(listOf("hello", "non", "oui", "peut-être")))
    }

    @Test
    fun `should save old collection when no new keyword filters and old one non null`(){
        //given
        val keywordFilterToSave: Collection<String> = setOf()
        doReturn(setOf("oui", "peut-être")).`when`(mockSharedPreferencesInterface).getKeywordFilterToSearch()

        //when
        apiSettingsSaveUseCase.saveKeywordFilter(keywordFilterToSave)

        //then
        verify(mockSharedPreferencesInterface).saveApiRequestKeywordFilter(eq(listOf("oui", "peut-être")))
    }
    @Test
    fun `should save new collection plus ols one when new keyword filters and old one non null with no duplicate`(){
        //given
        val keywordFilterToSave: Collection<String> = setOf("peut-être", "oui", "non")
        doReturn(setOf("oui", "peut-être")).`when`(mockSharedPreferencesInterface).getKeywordFilterToSearch()

        //when
        apiSettingsSaveUseCase.saveKeywordFilter(keywordFilterToSave)

        //then
        verify(mockSharedPreferencesInterface).saveApiRequestKeywordFilter(eq(listOf("peut-être", "oui", "non")))
    }
}