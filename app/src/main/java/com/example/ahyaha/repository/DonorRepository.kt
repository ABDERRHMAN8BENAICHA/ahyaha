package com.example.ahyaha.repository

import com.example.ahyaha.model.Donor
import java.util.Date

object DonorRepository {

    fun getAllDonors(): List<Donor> {
        // Simulating fetching data from a data source
        return listOf(
            Donor(
                id = "1",
                name = "Ahmed Ali",
                email = "ahmed.ali@example.com",
                phoneNumber = "1234567890",
                profilePicture = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "A",
                Rh = "+",
                location = "Cairo",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "2",
                name = "Fatima Zahra",
                email = "fatima.zahra@example.com",
                phoneNumber = "0987654321",
                profilePicture = "https://images.unsplash.com/photo-1532074205216-d0e1f4b87368?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "B",
                Rh = "-",
                location = "Riyadh",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "3",
                name = "Omar Khaled",
                email = "omar.khaled@example.com",
                phoneNumber = "5555555555",
                profilePicture = "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "O",
                Rh = "+",
                location = "Dubai",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "4",
                name = "Laila Hassan",
                email = "laila.hassan@example.com",
                phoneNumber = "4444444444",
                profilePicture = "https://images.unsplash.com/photo-1491349174775-aaafddd81942?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "AB",
                Rh = "+",
                location = "Beirut",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "5",
                name = "Youssef Nasser",
                email = "youssef.nasser@example.com",
                phoneNumber = "3333333333",
                profilePicture = "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "A",
                Rh = "-",
                location = "Amman",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "6",
                name = "Hana Mohamed",
                email = "hana.mohamed@example.com",
                phoneNumber = "2222222222",
                profilePicture = "https://images.unsplash.com/photo-1554151228-14d9def656e4?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "O",
                Rh = "+",
                location = "Casablanca",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            )
        )
    }
}