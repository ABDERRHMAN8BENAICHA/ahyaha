package com.example.ahyaha.repository

import com.example.ahyaha.model.Donor
import java.util.Date

object DonorRepository {

    fun getAllDonors(): List<Donor> {
        return listOf(
            Donor(
                id = "1",
                name = "John Doe",
                email = "john.doe@example.com",
                phoneNumber = "1234567890",
                profilePicture = "https://randomuser.me/api/portraits/men/1.jpg",
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
                profilePicture = "https://randomuser.me/api/portraits/women/2.jpg",
                bloodGroup = "B",
                Rh = "-",
                location = "Los Angeles",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "3",
                name = "Walter White",
                email = "walter.white@example.com",
                phoneNumber = "5555555555",
                profilePicture = "https://randomuser.me/api/portraits/men/3.jpg",
                bloodGroup = "O",
                Rh = "+",
                location = "Albuquerque",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "4",
                name = "Trevor Turner",
                email = "trevor.turner@example.com",
                phoneNumber = "4444444444",
                profilePicture = "https://randomuser.me/api/portraits/men/4.jpg",
                bloodGroup = "AB",
                Rh = "+",
                location = "Chicago",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "5",
                name = "Alexander Brown",
                email = "alex.brown@example.com",
                phoneNumber = "3333333333",
                profilePicture = "https://randomuser.me/api/portraits/men/5.jpg",
                bloodGroup = "A",
                Rh = "-",
                location = "Houston",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "6",
                name = "Emily Davis",
                email = "emily.davis@example.com",
                phoneNumber = "2222222222",
                profilePicture = "https://randomuser.me/api/portraits/women/6.jpg",
                bloodGroup = "B",
                Rh = "+",
                location = "Phoenix",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "7",
                name = "Michael Johnson",
                email = "michael.johnson@example.com",
                phoneNumber = "1111111111",
                profilePicture = "https://randomuser.me/api/portraits/men/7.jpg",
                bloodGroup = "O",
                Rh = "-",
                location = "San Francisco",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "8",
                name = "jack daniel ",
                email = "emily.davis@example.com",
                phoneNumber = "2222222222",
                profilePicture = "https://www.utopix.com/fr/blog/wp-content/uploads/2024/04/OGI0Mzc2YjItMTVlNS00MzBjLThmOWUtMmM0ZDIxZDhkYjA2_1cbeaae0-7898-4972-bec2-818e8b74357b_profilhomme6-scaled.jpg",
                bloodGroup = "B",
                Rh = "+",
                location = "Phoenix",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
        )
    }
}
