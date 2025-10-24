package tn.esprit.gamerapp.tn.esprit.gamerapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object ForgotPassword : Screen("forgot_password")
    object OTPValidation : Screen("otp_validation/{code}") {
        fun createRoute(code: String) = "otp_validation/$code"
    }
    object ResetPassword : Screen("reset_password")
}