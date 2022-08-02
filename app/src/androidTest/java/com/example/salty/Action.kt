package com.example.salty

import android.os.IBinder
import android.view.View
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.test.espresso.Root
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.any
import org.hamcrest.TypeSafeMatcher


fun clickRecyclerViewFabAction(@IdRes id: Int) = object : ViewAction {

    override fun getConstraints(): Matcher<View> = any(View::class.java)

    override fun getDescription(): String = "Click on a fab inside recycler view"

    override fun perform(uiController: UiController?, view: View?) = click().perform(uiController, view?.findViewById(id))

}

class ToastMatcher : TypeSafeMatcher<Root?>() {

    override fun describeTo(description: Description?) {
        description?.appendText("is toast")
    }

    override fun matchesSafely(item: Root?): Boolean {
        val type: Int? = item?.windowLayoutParams?.get()?.type
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            val windowToken: IBinder = item.decorView.windowToken
            val appToken: IBinder = item.decorView.applicationWindowToken
            if (windowToken === appToken) {
                return true
            }
        }
        return false
    }
}