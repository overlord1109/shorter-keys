# shorter-keys

## Pre-requisites

* Java JDK version 8 or higher (check by running java -version)
* IntelliJ IDEA version 2021.3 or higher (can be installed from [here](https://www.jetbrains.com/idea/). Download Community Edition if you do not have license)


## Steps to run:

You can run this project both in production mode as well as in development mode.

To run in production mode:

1. Download the plugin zip from the first release in the repo (Release v1.0)
2. Open Intellij IDEA and follow instructions listed under 'Install plugin from disk' on [this](https://www.jetbrains.com/help/idea/managing-plugins.html) page to install the ShorterKeys plugin.
3. Restart IDE and the plugin will now be installed. You can open a Java project of your choice (or even this project) to test the plugin.

To run in development mode:

1. Clone this repository.
2. Open the project using Intellij IDEA.
3. Set up a 'Gradle run configuration' which runs the following task ':runIde'.
4. Run the above configuration to run in dev mode. You can also debug the above run configuration to step through the code.
