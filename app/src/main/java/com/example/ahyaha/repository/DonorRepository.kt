package com.example.ahyaha.repository

import com.example.ahyaha.model.Donor
import java.util.Date

object DonorRepository {
    
    fun getAllDonors(): List<Donor> {
        // Simulating fetching data from a data source
        return listOf(

            Donor(
                id = "1",
                name = "John Doe",
                email = "john.doe@example.com",
                phoneNumber = "1234567890",
                profilePicture = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "A",
                Rh = "+",
                location = "New York",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "2",
                name = "Jane Smith",
                email = "jane.smith@example.com",
                phoneNumber = "0987654321",
                profilePicture = "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "B",
                Rh = "-",
                location = "Los Angeles",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "3",
                name = "Michael ",
                email = "michael.johnson@example.com",
                phoneNumber = "5555555555",
                profilePicture = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "O",
                Rh = "+",
                location = "Chicago",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "4",
                name = "Emily Davis",
                email = "emily.davis@example.com",
                phoneNumber = "4444444444",
                profilePicture = "https://images.unsplash.com/photo-1491349174775-aaafddd81942?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "AB",
                Rh = "+",
                location = "Houston",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "5",
                name = "David Wilson",
                email = "david.wilson@example.com",
                phoneNumber = "3333333333",
                profilePicture = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "A",
                Rh = "-",
                location = "Phoenix",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "6",
                name = "okba ",
                email = "michael.johnson@example.com",
                phoneNumber = "5555555555",
                profilePicture = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "O",
                Rh = "+",
                location = "Chicago",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),

        )
    }
}

