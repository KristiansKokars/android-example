package com.testdevlab.androidexample.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment

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
