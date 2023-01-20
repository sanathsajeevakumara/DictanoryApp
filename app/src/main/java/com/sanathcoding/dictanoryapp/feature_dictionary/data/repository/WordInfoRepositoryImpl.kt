package com.sanathcoding.dictanoryapp.feature_dictionary.data.repository

import com.sanathcoding.dictanoryapp.core.util.Resource
import com.sanathcoding.dictanoryapp.feature_dictionary.data.local.WordInfoDao
import com.sanathcoding.dictanoryapp.feature_dictionary.data.remote.DictionaryApi
import com.sanathcoding.dictanoryapp.feature_dictionary.domain.model.WordInfo
import com.sanathcoding.dictanoryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfo = api.getWordInfo(word = word)
            remoteWordInfo.let {
                dao.deleteWordInfos(remoteWordInfo.map { it.word })
                dao.insertWordInfos(remoteWordInfo.map { it.toWordInfoEntity() })
            }
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, Something went wrong!",
                    data = wordInfos
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach the server, Check your internet connection",
                    data = wordInfos
                )
            )
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))

    }
}