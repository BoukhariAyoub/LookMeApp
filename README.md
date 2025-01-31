# Look Me App

## Overview
Look Me App is a modern mobile application designed to showcase fashion styles through curated themes. The app follows the **Clean Architecture** approach, ensuring scalability, maintainability, and separation of concerns.

## Architecture
The app is structured using **MVVM (Model-View-ViewModel) architecture** and **Clean Architecture** . This ensures a clear separation between UI, business logic, and data handling.

![App Architecture](https://github.com/user-attachments/assets/4e13673b-5bc0-4aef-8b57-7514a8366a17)

### Layers!



### 1. UI Layer
- Contains **ViewModels** responsible for UI logic.
- Uses **UI Models** for structuring data.
- Screens include:
  - **Today's Theme Screen** showcasing a specific fashion style.
  - **Feed Screen** displaying a curated list of fashion items.
  
### 2. Business Logic Layer (Domain Layer)
- Implements **Use Cases** such as:
  - `GetFeedUseCase`
  - `RefreshFeedUseCase`
- Defines **Entities** representing core business objects.
- Interfaces with the **Repository Layer** for data access.

### 3. Data Layer
- Uses **Repository Pattern** for data handling.
- Supports both **local and remote data sources**.
- Local data stored using **Room Database**.
- Remote data fetched via **Mock Repository**(for now).
- Implements `TaskRepositoryImpl` for managing data.

## Tech Stack
- **Kotlin** for Android Development
- **Jetpack Compose** for UI
- **ViewModel + LiveData/StateFlow** for state management
- **Room Database** for local persistence
- **Retrofit** for API calls
- **Dependency Injection** (Dagger/Hilt)
- **Coroutines & Flow** for asynchronous operations
