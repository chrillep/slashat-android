Slashapp för Android

Experimentbranch för Android Studio.

Förnärvarande bara för 3.0 Honeycomb och senare i väntan på maven/gradle-kompatibel release av Actionbarsherlock eller actionbar/fragmentcompat från Google.

Efter checkout och pull så kan man öppna projektet i Android studio genom att köra import project och välja gradle och gradle wrapper.

Om Android Studio inte skapar local.properties själv så ska den innehålla:

    # This file is automatically generated by Android Studio.
    # Do not modify this file -- YOUR CHANGES WILL BE ERASED!
    #
    # This file must *NOT* be checked into Version Control Systems,
    # as it contains information specific to your local configuration.
    
    # Location of the SDK. This is only used by Gradle.
    # For customization when using a Version Control System, please read the
    # header note.
    sdk.dir=/Applications/Android Studio.app/sdk

där sdk.dir pekar till sdk-diret i Android Studio (exemplet ovan från OSX). 



Vid exceptions som "MainActity Superclass not found" eller andra liknande problem där nya dependecies ej hittas så kör man kommandot:
./gradlew assemble --info

ifrån rootfoldern. (gradlew.bat om man kör windows)
