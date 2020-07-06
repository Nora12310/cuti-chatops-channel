package common

object Resource {
    const val INFO = "/info"
    const val BUILD = "/build"
    const val REGISTER = "/register"
    const val UNREGISTER = "/unregister"
    const val SENDER_ID = "sender_id"


    const val BUILD_MESSAGE = "Hi team! This is Cuti, the chatOps bot created by Android team. I will contact CI/CD process to build your APK now!"
    const val INVALID_URL_MESSAGE = "Your git url invalid. please check again!"
    const val ADD_URL_MESSAGE = "Your git repository has been monitored! If your git repository have any build action. I will notify to you."
}