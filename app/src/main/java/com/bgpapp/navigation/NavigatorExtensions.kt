package com.bgpapp.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import java.net.URI

fun Navigator.observeNavigation(lifecycleOwner: LifecycleOwner) {
    navigationCommands.observe(lifecycleOwner, Observer { event ->
        event.getContentIfNotHandled()?.let { command ->
            lifecycleOwner.navigate(command)
        }
    })
}

fun LifecycleOwner.navigate(command: NavigationCommand) {
    when (this) {
        is AppCompatActivity -> navigate(command)
        is Fragment -> navigate(command)
    }
}

fun NavController.navigate(command: NavigationCommand) {
    when (command) {
        is NavigationCommand.To -> navigate(command.directions)
        is NavigationCommand.Back -> navigateUp()
        is NavigationCommand.BackTo -> popBackStack(command.destinationId, false)
        is NavigationCommand.ToRoot -> popBackStack(graph.startDestination, false)
        else -> throw CommandNotSupportedException(command)
    }
}

fun AppCompatActivity.navigate(command: NavigationCommand) {
    when (command) {
        is NavigationCommand.ToFlow<*> -> {
            val intent = Intent(this, command.destination)
            if (command.clearStack) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            startActivity(intent)
        }
        is NavigationCommand.FinishFlow -> finish()
        is NavigationCommand.ToUri -> navigateToUri(this, command.uri)
        else -> throw CommandNotSupportedException(command)
    }
}

fun Fragment.navigate(command: NavigationCommand) {
    when (command) {
        is NavigationCommand.To -> findNavController().navigate(command)
        is NavigationCommand.ToUri -> navigateToUri(context!!, command.uri)
        is NavigationCommand.BackTo -> findNavController().navigate(command)
        is NavigationCommand.Back, NavigationCommand.ToRoot -> findNavController().navigate(command)
        is NavigationCommand.ToFlow<*>, NavigationCommand.FinishFlow -> activity?.navigate(command)
    }
}

private fun navigateToUri(context: Context, uri: URI) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri.toASCIIString()))
        context.startActivity(intent)
    } catch (ex: ActivityNotFoundException) {
        Log.e("Navigation exception", ex.message)
    }
}