package com.example.salty.domain.usecase

import com.example.salty.domain.model.Album
import com.example.salty.domain.model.SearchAlbum
import com.example.salty.domain.repository.AlbumRepository
import com.example.salty.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend  operator fun invoke(query: String): List<SearchAlbum> {
       return  repository.search(query)
    }
}

/**
 *
class SaveAlbumUseCase(
private val repository: AlbumRepository
) {

suspend operator fun invoke(album: Album) {
repository.insertIntoDatabase(album)
}
}



class GetCoinUseCase @Inject constructor(
private val repository: CoinRepository
) {
operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
try {
emit(Resource.Loading<CoinDetail>())
val coin = repository.getCoinById(coinId).toCoinDetail()
emit(Resource.Success<CoinDetail>(coin))
} catch(e: HttpException) {
emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occured"))
} catch(e: IOException) {
emit(Resource.Error<CoinDetail>("Couldn't reach server. Check your internet connection."))
}
}
 */