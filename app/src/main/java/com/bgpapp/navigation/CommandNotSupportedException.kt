package com.bgpapp.navigation

class CommandNotSupportedException(command: NavigationCommand) :
        Exception("Navigation command \"${command.javaClass.simpleName}\" is not supported in this context")