import extensions.applyDefault

plugins {
    id(Plugins.SPOTLESS)
}

allprojects {
    repositories.applyDefault()
}
