package com.example.salty.domain.usecase

import com.example.salty.data.remote.topalbumsdto.toAlbum
import com.example.salty.domain.model.Album
import com.example.salty.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAlbumUsecase @Inject constructor(
    private val repository: AlbumRepository
){
    suspend operator fun invoke(): Flow<List<Album>> {
       return repository.getAlbums()

    }
}


/**
 *
 *
class GetCoinsUseCase @Inject constructor(
private val repository: CoinRepository
) {
operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
try {
 *
 *
class GetWordInfo(
private val repository: WordInfoRepository
) {

operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
if(word.isBlank()) {
return flow {  }
}
return repository.getWordInfo(word)
}**/