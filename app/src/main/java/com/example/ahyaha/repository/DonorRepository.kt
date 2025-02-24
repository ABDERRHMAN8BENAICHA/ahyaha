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
            Donor(
                id = "6",
                name = "Radja Guemoula",
                email = "radja.guemola@example.com",
                phoneNumber = "0770283626",
                profilePicture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSReNdLuLhSIw4gLoTh0BvlE-Dec__UOUxLqw&s",
                bloodGroup = "O",
                Rh = "+",
                location = "El Oued",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "7",
                name = "Bachir Toumi",
                email = "bachir.toumi@example.com",
                phoneNumber = "0664700764",
                profilePicture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSfA9pLExDXakwYb0Vau9BDQ5dbfuWKmJ5qMg&s",
                bloodGroup = "B",
                Rh = "+",
                location = "Los Angeles",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "8",
                name = "Sana Almasa",
                email = "sana.almasa@example.com",
                phoneNumber = "0645897235",
                profilePicture = "https://i.pinimg.com/originals/3d/b2/d7/3db2d703b4c32c6f5826a3b3db84bf91.jpg",
                bloodGroup = "O",
                Rh = "-",
                location = "China",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "9",
                name = "Alya Eljaohara",
                email = "alya.eljaohara@example.com",
                phoneNumber = "1484458404",
                profilePicture = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRB193OoZe9vatci21qd2QpauHZEr9VxPtI8d6YC0J0LWcWZfRQO2KVnXO36gWINOlG24&usqp=CAU",
                bloodGroup = "AB",
                Rh = "+",
                location = "Alger",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "10",
                name = "Walid Gusd",
                email = "walid.gusd@example.com",
                phoneNumber = "3333333333",
                profilePicture = "https://www.shutterstock.com/image-photo/portrait-handsome-caucasian-man-formal-260nw-2142820441.jpg",
                bloodGroup = "A",
                Rh = "-",
                location = "Phoenix",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            )
        )
    }
}
