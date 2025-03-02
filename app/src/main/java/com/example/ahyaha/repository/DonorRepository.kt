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
                name = "Michael Johnson",
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
                    // Additional Donors
            Donor(
            id = "6",
            name = "Olivia Brown",
            email = "olivia.brown@example.com",
            phoneNumber = "2222222222",
            profilePicture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRI8E7_C324i4TFCcXDEseZx_yAMUB8BK_9fw&s",
            bloodGroup = "B",
            Rh = "+",
            location = "San Francisco",
            lastDonationDate = Date(),
            createdAt = Date(),
            updatedAt = Date()
        ),
        Donor(
            id = "7",
            name = "Liam Martinez",
            email = "liam.martinez@example.com",
            phoneNumber = "1111111111",
            profilePicture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwPg6zOHcxJLapvqR5npReMtG0h1RrgjbIFw&",
            bloodGroup = "O",
            Rh = "-",
            location = "Seattle",
            lastDonationDate = Date(),
            createdAt = Date(),
            updatedAt = Date()
        ),
        Donor(
            id = "8",
            name = "Sophia Anderson",
            email = "sophia.anderson@example.com",
            phoneNumber = "6666666666",
            profilePicture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOOKbvG6s1k9bAks8fJvrVP-OiivlKJXV_nw&s",
            bloodGroup = "AB",
            Rh = "-",
            location = "Miami",
            lastDonationDate = Date(),
            createdAt = Date(),
            updatedAt = Date()
        ),
        Donor (
            id = "9",
            name = "Noah Thomas",
            email = "noah.thomas@example.com",
            phoneNumber = "7777777777",
            profilePicture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTFoUSQVD9BAzHAwvFRR9FM8XXQm9rDJo0w6g&s",
            bloodGroup = "A",
            Rh = "+",
            location = "Austin",
            lastDonationDate = Date(),
            createdAt = Date(),
            updatedAt = Date()
        ),
        Donor(
            id = "10",
            name = "Emma Harris",
            email = "emma.harris@example.com",
            phoneNumber = "8888888888",
            profilePicture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTX47KlDhgMUyCCOs69PlIOEgxyJp9SZeVtQA&s",
            bloodGroup = "B",
            Rh = "-",
            location = "Denver",
            lastDonationDate = Date(),
            createdAt = Date(),
            updatedAt = Date()
        ),
       )
    }
}

