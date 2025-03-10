# Task 3: Understanding Dependency Injection and Implementing Add Donor Feature

## Dependency Injection with Dagger Hilt

The Ahyaha app now uses Dagger Hilt for dependency injection across all layers of the application. Here's how it's structured:

### 1. Data Layer
- Repository implementations are provided via Hilt modules
- Data sources and their dependencies are injected into repositories
- Example: `DonorRepository` and `BloodTypeRepository` are injected with their dependencies

### 2. Domain Layer
- Use cases are injected with their required repositories
- Examples:
  - `GetDonorsUseCase` is injected with `DonorRepository`
  - `AddDonorUseCase` is injected with `DonorRepository`
  - `GetBloodTypesUseCase` is injected with `BloodTypeRepository`

### 3. Presentation Layer
- ViewModels are annotated with `@HiltViewModel`
- Use cases are injected into ViewModels
- Example: `DonorViewModel` is injected with `GetDonorsUseCase`

## Your Task: Implement Add Donor Feature

### 1. Create Add Donor View
Create a new composable function `AddDonorView` in the presentation layer with the following requirements:

- Input fields for:
  - Donor name
  - Email
  - Phone number
  - Blood group selection (dropdown)
  - Rh factor selection (+ or -)
  - Location
  - Profile picture URL
- Validation for all fields
- Submit button
- Success/Error feedback

### 2. Enhance AddDonorViewModel

Create an `AddDonorViewModel` to include:

```kotlin
// State for form fields
data class AddDonorState(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val bloodGroup: String = "",
    val rh: String = "",
    val location: String = "",
    val profilePicture: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

// Events that can be triggered
sealed class AddDonorEvent {
    data class NameChanged(val name: String) : AddDonorEvent()
    data class EmailChanged(val email: String) : AddDonorEvent()
    data class PhoneNumberChanged(val phoneNumber: String) : AddDonorEvent()
    data class BloodGroupChanged(val bloodGroup: String) : AddDonorEvent()
    data class RhChanged(val rh: String) : AddDonorEvent()
    data class LocationChanged(val location: String) : AddDonorEvent()
    data class ProfilePictureChanged(val url: String) : AddDonorEvent()
    object Submit : AddDonorEvent()
}
```

Required functionality:
1. State management for form fields
2. Input validation
3. Error handling
4. Integration with `AddDonorUseCase`
5. Success/failure feedback

### Implementation Steps

1. **AddDonorViewModel Implementation**
   - Add state management using `StateFlow`
   - Implement event handling
   - Add validation logic
   - Integrate with `AddDonorUseCase`

2. **AddDonorView Implementation**
   - Create UI components using Jetpack Compose
   - Connect UI events to ViewModel
   - Show loading state
   - Display error messages
   - Show success confirmation

3. **Navigation Integration**
   - Add navigation to Add Donor screen
   - Handle back navigation
   - Pass results back to previous screen

### Success Criteria

Your implementation should:
1. Successfully add new donors to the repository
2. Validate all input fields
3. Show appropriate loading states
4. Handle and display errors
5. Provide success feedback
6. Follow MVVM architecture patterns
7. Properly utilize dependency injection

## Tips
- Use `viewModelScope` for coroutines in ViewModel
- Implement proper error handling
- Follow Material Design guidelines for the UI
- Use proper state management
- Make use of Hilt's dependency injection

## Bonus Challenges
1. Add image upload functionality
2. Implement form state persistence
3. Add unit tests for ViewModel
4. Add UI tests for the Add Donor screen



Good luck!
