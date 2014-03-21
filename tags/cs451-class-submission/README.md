# Apologies

A clone of the [board game “Sorry!”](https://en.wikipedia.org/wiki/Sorry!_%28game%29) for a class project.

Created by five students at Drexel University in the class CS 451 Software Engineering during Winter quarter 2014.

## Running the code

We have only tried running the code in the free IDE [IntelliJ IDEA](http://www.jetbrains.com/idea/). Open the “trunk” folder as an IDEA project. Depending on what version of Java your OS has, you may need to change the project configuration. If the version of Java specified by the project is not available, IDEA should prompt you to choose a different version. Or you can do it yourself by going to File > Project Structure and changing the Project SDK in Project Settings.

To run the code for the first time, open the MainMenu class and choose the menu item Run > Run…, then choose MainMenu from the pop-up menu. The code should build and run from the IDE. Then just click New Game, select some players, type their names and who goes first, and you’re ready to play.

Images are loaded from the src/main/resources folder. Teh getResourcePath method in ApologiesGameWindow handles getting the path of a resource file. It should work on any OS, but if you don’t see any images, then either that method is broken, or your build method did not copy the resources folder.

The project builds using Gradle. As far as I know, the included “gradlew” wrapper script will download and install gradle if it isn’t installed, so all you need to do is “./gradlew build” to build the project. IDEA should do this for you using its Gradle plugin.

We have also exported the IDEA project as an Eclipse project, so you can try Eclipse if you want. We haven’t tested it in Eclipse.