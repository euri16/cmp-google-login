# Compose Multiplatform Firebase Google Sign In

Welcome to the Compose Multiplatform Firebase Google Sign In repository! This repository showcases a Compose multiplatform project that initializes Firebase and implements Google Sign In on both Android and iOS platforms. 

This repository serves as a demonstration of how to modularize a Compose multiplatform project, making it easier to manage and maintain.

## Usage

To use this project, follow these steps:

1. Clone the repository to your local machine.
2. Open the project in your preferred IDE.
3. Replace the content of the Firebase Google Service files in Android and iOS.
4. Use your Client ID and Reversed Client ID in the `Info.plist` file
5. Run the project on either Android or iOS emulator/device.

## Project Structure:

* `androidMain`: Contains Android-specific code for your app.
* `iosApp`: Contains the native iOS project.
* `composeApp`: Contains common code for both platforms using Compose Multiplatform. This directory includes:
    * `commonMain`: Shared codebase for Android and iOS.
    * `iosMain`: Platform-specific code for iOS using Compose for iOS.
* `utils`: Utility modules used throughout the project.
* `signin`: Modules related to Google Sign In functionality.

This project demonstrates how to modularize a Compose multiplatform project for better maintainability and code reusability.

## Blog Post

To follow a step-by-step guide on how to implement Firebase Google Sign In in a Compose multiplatform project, check out these two articles:

1. [Part 1: Compose Multiplatform: Getting Started & Initializing Firebase](https://euryperez.dev/compose-multiplatform-getting-started-initializing-firebase-5abd99a1ae91)
2. [Part 2: Compose Multiplatform: Login With Google with 100% Kotlin Code](https://euryperez.dev/compose-multiplatform-login-with-google-cc3217a99349)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributors

- [Eury Pérez](http://linkedin.com/in/euryperez/) - Google Developer Expert for Android

Feel free to contribute to this project by opening issues or submitting pull requests!
