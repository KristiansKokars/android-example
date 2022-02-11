package com.testdevlab.androidexample.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun FragmentActivity.getNavController(id: Int) =
    (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController

fun Fragment.openFragment(fragmentId: Int, navHostId: Int) =
    activity?.openFragment(fragmentId, navHostId)

fun FragmentActivity.openFragment(fragmentId: Int, navHostId: Int) =
    getNavController(navHostId).run {
        if (currentBackStackEntry?.destination?.id == fragmentId) return@run
        if (!popBackStack(fragmentId, false)) {
            /**
             * If navigating back fails since a fragment not found in back stack - open
             * the fragment instead
             */
            navigate(fragmentId)
        }
    }

fun Fragment.launchUI(
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend CoroutineScope.() -> Unit
) = viewLifecycleOwner.lifecycleScope.launch {
    repeatOnLifecycle(lifecycleState, block)
}
