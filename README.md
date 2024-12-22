# Kotlin Timers ⏱️📅

A simple Android application that demonstrates the use of **Timer**, **AlarmManager**, and **WorkManager** to manage and schedule timing tasks in Android. This app showcases key Android components for tracking time, scheduling alarms, and managing background tasks.

---

## Features ✨

- **Timer Counter**: Track time upwards, starting from zero, with the option to stop and restart.
- **Stopwatch**: Count down from the selected number of minutes to zero, providing a simple way to track time in reverse.
- **AlarmManager**: Set alarms to trigger at specific times or intervals.
- **WorkManager**: Schedule background tasks with constraints and retries.
- **API Requests**: Example of network calls with scheduling.

---

## How to Use 🛠️

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/kotlin-timers
    ```

2. **Set up the project**:
    - Open the project in **Android Studio**.
    - Ensure that you have the necessary SDK versions installed.

3. **Run the app**:
    - Build and run the project in Android Studio.

---

## Technologies Used 🛠️

### Libraries & Tools
- **[WorkManager](https://developer.android.com/reference/androidx/work/WorkManager)**: For scheduling background tasks with flexible constraints and retries.
- **[Timer](https://developer.android.com/reference/java/util/Timer)**: For scheduling tasks that need to execute after a certain delay or periodically.
- **[AlarmManager](https://developer.android.com/reference/android/app/AlarmManager)**: For scheduling alarms to trigger at specific times.
- **[Retrofit](https://square.github.io/retrofit/)**: For making HTTP requests.
- **[Gson](https://github.com/google/gson)**: For JSON serialization and deserialization.
- **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)**: For observing data changes in a lifecycle-aware way.
