package com.example.salty.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.example.salty.R
import com.example.salty.ToastMatcher
import com.example.salty.clickRecyclerViewFabAction
import com.example.salty.di.launchFragmentInHiltsContainer
import com.example.salty.domain.model.SearchAlbum
import com.example.salty.presentation.search.SearchAdapter
import com.example.salty.presentation.search.SearchFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject



@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class SearchFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var testFragmentFactory: TestFragmentFactory
   // lateinit var searchAdapter: SearchAdapter

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun click_to_save() {
        launchFragmentInHiltsContainer<SearchFragment>(
            fragmentFactory = testFragmentFactory,
        ) {
          //  searchAdapter = SearchAdapter()
            searchAdapter.submitList(listOf(SearchAlbum("lol", "lol", "lol")))
        }

        onView(withId(R.id.searchAlbumRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, clickRecyclerViewFabAction(R.id.productSave)
            )
        )

        onView(withText("toast occurred")).inRoot(ToastMatcher()).check(matches(isDisplayed()))
    }
}

/**

@Test
fun test_recycler_is_visible_when_data_is_loaded() {
launchFragmentInHiltsContainer<ProductListFragment>(
fragmentFactory = testFragmentFactory
) {
productListAdapter.submitList(getListOfProduct())
}
onView(withId(R.id.productListRecyclerView)).check(matches(isDisplayed()))

}

@Test
fun click_on_recyclerViewholder_to_navigate_to_detail_screen() {
val product = getListOfProduct()
val navController = Mockito.mock(NavController::class.java)
launchFragmentInHiltsContainer<ProductListFragment>(
fragmentFactory = testFragmentFactory
){
productListAdapter.submitList(product)
androidx.navigation.Navigation.setViewNavController(requireView(), navController)
}
onView(withId(R.id.productListRecyclerView)).perform(
RecyclerViewActions.actionOnItemAtPosition<ProductListAdapter.ProductListViewHolder>(
0, click()
)
)
verify(navController).navigate(
ProductListFragmentDirections.actionProductListFragmentToProductDetailFragment(product[0])
)
}

@Test
fun click_visit_button_to_go_to_historyscreen() {
val navController = Mockito.mock(NavController::class.java)
launchFragmentInHiltsContainer<ProductListFragment>(
fragmentFactory = testFragmentFactory
) {
androidx.navigation.Navigation.setViewNavController(requireView(), navController)
}
onView(withId(R.id.visitButton)).perform(click())
verify(navController).navigate(
ProductListFragmentDirections.actionProductListFragmentToProductHistoryFragment()
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


}
 **/