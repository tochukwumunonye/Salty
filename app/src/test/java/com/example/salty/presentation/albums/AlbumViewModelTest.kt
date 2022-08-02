package com.example.salty.presentation.albums

import com.example.salty.domain.model.Album
import com.example.salty.domain.usecase.GetAlbumUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
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
class AlbumViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var albumViewModel: AlbumViewModel
    private val mockUsecase: GetAlbumUsecase = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `do the test`() {
        val expected = getListZ()
        runTest {
            whenever(mockUsecase.invoke()).thenReturn(expected)
            albumViewModel = AlbumViewModel(mockUsecase)
            albumViewModel.getAlbums()
            val stateFlow = albumViewModel.viewState.value
            assertEquals(stateFlow, AlbumViewState.Success(listOf(
                Album("good", "love", "lol"))))
        }
    }

    private fun getListZ() : Flow<List<Album>> {
        return flowOf(
            listOf(Album("good", "love", "lol"))
        )
    }
}


/**
 *


@Test
fun `Given products are loaded When data source returns success Then emit success view state`() {
val expectedProducts = getListOfProduct()
runTest {
whenever(mockProductRepository.searchProduct("books")).thenReturn(expectedProducts)
productListViewModel = ProductListViewModel(mockProductRepository)
productListViewModel.search("books")
val stateFlow = productListViewModel.viewState.value
assertEquals(stateFlow, ProductListViewState.Success(expectedProducts))
}
}

@Test
fun `When data source returns error Then emit error view state`() {
runTest {
whenever(mockProductRepository.searchProduct("book")).thenThrow(RuntimeException(""))
productListViewModel = ProductListViewModel(mockProductRepository)
productListViewModel.search("book")
val stateFlow = productListViewModel.viewState.value
assertEquals(stateFlow, ProductListViewState.Error(""))
}
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
}

 */