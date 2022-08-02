package com.example.salty.data.repository

import com.example.salty.data.local.AlbumEntityDao
import com.example.salty.data.remote.api.ApiService
import com.example.salty.data.remote.topalbumsdto.*
import com.example.salty.domain.model.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class AlbumRepositoryImplementationTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val api: ApiService = mock()


    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when success return list of api album`() {
        val responseFromApi = getResponseApi()
        val albumss = getListOfAlbum()
        runTest {
            whenever(api.getTopAlbums()).thenReturn(responseFromApi)
            val repo = AlbumRepositoryImplementation(api)
            val  list = repo.getAlbums()
            val kk = list.first()
            assertEquals(list.first().first(), albumss.size)
        }
    }
    private fun getResponseApi(): TopAlbumsResult {
        return TopAlbumsResult(
            feed = createFeed()
        )
    }

    private fun createFeed() = Feed(
        entry  = getListOfResultsFromApi()
    )

    private fun getListOfResultsFromApi(): List<Entry> {
        return listOf(
            Entry(
                imArtist = ImArtist("jazzy"),
                imImage = listOf(ImImage("lol")),
                imName = ImName("cool")
            )
        )
    }

    private fun getListOfAlbum() : List<Album> {
        return listOf(
            Album(
                albumArtist = "jazzy",
                albumImage = "lol",
                albumName = "cool"
            )
        )
    }
}

/**



private fun getListOfResultFromApi(): List<Result> {
return listOf(
Result(
id = "book1",
price = 2.3,
thumbnail = "https://book.com",
title = "macBook pro"
),
Result(
id = "book2",
price = 2.5,
thumbnail = "https://book2.com",
title = "macBook pro2"
)
)
@Test
fun `when search for a product return list of products`() {
val product = getListOfProduct()
val catalogue = getResponseFromAPI()
runTest {
whenever(api.getProducts("books")).thenReturn(catalogue)
val productRepo = ProductRepositoryImplementation(api, dao)
val productList = productRepo.searchProduct("books")
assertEquals(product, productList)
}
}

@Test
fun `get details of product`() {
val productItem = getProductDetails()
val catalogueItem = getProductDetailsFromAPI()
runTest {
whenever(api.getProductDetails("MLU479")).thenReturn(catalogueItem)
val productDetailRepo = ProductRepositoryImplementation(api,dao)
val productDetail = productDetailRepo.getProductDetails("MLU479")
assertEquals(productItem.id, productDetail.id)
assertEquals(productItem.date_created, productDetail.date_created)
assertEquals(productItem.last_updated, productDetail.last_updated)
assertEquals(productItem.initial_quantity, productDetail.initial_quantity)
}
}


 *
 *
private fun  getProductDetails(): ProductItem {
return ProductItem(
date_created = "2021",
id = "MLU479",
initial_quantity = 2,
international_delivery_mode = "none",
last_updated = "2022",
listing_source = "http://articulo",
permalink = "10"
)
}

private fun  getProductDetailsFromAPI(): CatalogueItem {
return CatalogueItem(
date_created = "2021",
id = "MLU479",
initial_quantity = 2,
international_delivery_mode = "none",
last_updated = "2022",
listing_source = "http://articulo",
permalink = "10"
)
}

private fun getListOfProduct(): List<Product> {
return listOf(
Product(
id = "book1",
price = 2.3,
thumbnail = "https://book.com",
title = "macBook pro"
),
Product(
id = "book2",
price = 2.5,
thumbnail = "https://book2.com",
title = "macBook pro2"
)
)
}

private fun getResponseFromAPI(): Catalogue {
return Catalogue(
getListOfResultFromApi()
)
}

private fun getListOfResultFromApi(): List<Result> {
return listOf(
Result(
id = "book1",
price = 2.3,
thumbnail = "https://book.com",
title = "macBook pro"
),
Result(
id = "book2",
price = 2.5,
thumbnail = "https://book2.com",
title = "macBook pro2"
)
)
}

}


 */