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
            // 5 Donors Added Below
            Donor(
                id = "6",
                name = "Sarah Connor",
                email = "sarah.connor@example.com",
                phoneNumber = "2222222222",
                profilePicture = "https://images.unsplash.com/photo-1521119989659-a83eee488004?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "O",
                Rh = "-",
                location = "San Francisco",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "7",
                name = "James Brown",
                email = "james.brown@example.com",
                phoneNumber = "1111111111",
                profilePicture = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "B",
                Rh = "+",
                location = "Seattle",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "8",
                name = "Olivia Martin",
                email = "olivia.martin@example.com",
                phoneNumber = "9999999999",
                profilePicture = "https://images.unsplash.com/photo-1511367461989-f85a21fda167?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "AB",
                Rh = "-",
                location = "Denver",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "9",
                name = "William Anderson",
                email = "william.anderson@example.com",
                phoneNumber = "8888888888",
                profilePicture = "https://images.unsplash.com/photo-1544723795-3fb6469f5b39?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "A",
                Rh = "+",
                location = "Boston",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "10",
                name = "Sophia White",
                email = "sophia.white@example.com",
                phoneNumber = "7777777777",
                profilePicture = "https://images.unsplash.com/photo-1560807707-8cc77767d783?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
                bloodGroup = "O",
                Rh = "+",
                location = "Miami",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
             Donor(
                    id = "11",
            name = "Omar khaldi",
            email = "omar.khaldi@example.com",
            phoneNumber = "7777777777",
            profilePicture = "https://www.utopix.com/wp-content/uploads/2024/04/MDhlZTc4ZGEtZWJiNS00NTJjLThhNjQtZjBkMmMxYzhlOGRi_54a01d6e-6ad0-4c97-86cc-e89d53ae9c06_profilhomme-768x534.jpg",
            bloodGroup = "O",
            Rh = "-",
            location = "London",
            lastDonationDate = Date(),
            createdAt = Date(),
            updatedAt = Date()
        ),
            Donor(
                id = "12",
                name = "sami omrane",
                email = "sami.omrane@example.com",
                phoneNumber = "7777777777",
                profilePicture = "https://www.utopix.com/fr/blog/wp-content/uploads/2024/04/ZDYzNzI0NzgtMmY0MC00MzdkLTg4ZjAtM2FkZWIzYzNmZmUz_a428f80b-176b-4aa8-951b-834d91be8b5b_profilhomme2-scaled.jpg",
                bloodGroup = "A",
                Rh = "-",
                location = "Milano",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),

            Donor(
                id = "13",
                name = "saif Ben Ali",
                email = "saif.benali@example.com",
                phoneNumber = "7777777777",
                profilePicture = "https://www.utopix.com/fr/blog/wp-content/uploads/2024/04/OWNjOTI3MjYtOGQyYy00MzU2LTlhZTUtZGQyZTBjZDdhMzA0_811db412-6829-4e04-bfe6-a8c04d3d424b_profilhomme3-scaled.jpg",
                bloodGroup = "B",
                Rh = "-",
                location = "Milano",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "13",
                name = "Abdelmouiz telhig",
                email = "abdelmouiz.telhig@example.com",
                phoneNumber = "7777777777",
                profilePicture = "https://www.utopix.com/fr/blog/wp-content/uploads/2024/04/Yzc3Y2NjZGItYzdkMS00MmE4LWIyZmQtYTBkMzEyMjI2YmI1_e07b5e13-b27c-4669-896c-8b44790692b3_profilhomme4-scaled.jpg",
                bloodGroup = "AB",
                Rh = "+",
                location = "Paris",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "14",
                name = "Oussama Belhadi",
                email = "oussama.belhadi@example.com",
                phoneNumber = "7777777777",
                profilePicture = "https://www.utopix.com/fr/blog/wp-content/uploads/2024/04/OGI0Mzc2YjItMTVlNS00MzBjLThmOWUtMmM0ZDIxZDhkYjA2_1cbeaae0-7898-4972-bec2-818e8b74357b_profilhomme6-scaled.jpg",
                bloodGroup = "A",
                Rh = "+",
                location = "Toulouse",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            ),
            Donor(
                id = "15",
                name = "Nebrass Mnedjlia",
                email = "nebrass.mnedjlia@example.com",
                phoneNumber = "7777777777",
                profilePicture = "https://www.utopix.com/fr/blog/wp-content/uploads/2024/04/ZGUzNmViOGQtMjE4MS00MzdjLTk1OWMtNjUzMmFlOWUxN2Zh_414c3606-acc3-415b-8227-86ea6d932268_profilhomme8-scaled.jpg",
                bloodGroup = "B",
                Rh = "+",
                location = "Canada",
                lastDonationDate = Date(),
                createdAt = Date(),
                updatedAt = Date()
            )

        )
    }
}
