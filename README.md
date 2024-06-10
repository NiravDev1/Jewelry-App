# 💍 Online Jewelry Shopping App

Welcome to the Online Jewelry Shopping App! This project comprises two mobile applications: one for customers and sellers, and another for administrators. The apps are designed to provide a seamless shopping experience, effective management of products and orders, and an intuitive interface for both users and admins.

## 📱 Project Overview

This project aims to digitize the shopping experience for jewelry, enabling customers to browse and purchase items, sellers to manage their inventory, and admins to oversee the entire operation. The system is built using Android with Firebase as the backend for real-time data management.

- **Customer/Seller App**: Allows customers to browse, add to cart, and purchase jewelry. Sellers can list new products, manage inventory, and view sales data.
- **Admin App**: Provides functionalities for managing users, approving product listings, handling orders, and overseeing the marketplace.

## 🛠️ Features

### Customer/Seller App
- **Customer Features**:
  - Browse jewelry collections.
  - Add items to the cart and make purchases.
  - View order history and track current orders.
  - Leave reviews and ratings for products.

- **Seller Features**:
  - List new products with detailed descriptions and images.
  - Manage existing product listings and update inventory.
  - View and process orders from customers.
  - Access sales reports and analytics.

### Admin App
- **Admin Features**:
  - Manage user accounts and roles.
  - Approve or reject new product listings.
  - Monitor and manage all orders and transactions.
  - Generate reports on sales, user activity, and product performance.

## 🔧 Technologies Used

- **Frontend**: Java (Android)
- **Backend**: Firebase (Realtime Database, Firestore, Authentication)
- **Tools**: Android Studio, Firebase Console

## 🚀 Getting Started

### Prerequisites
- Android Studio installed on your system.
- Firebase account set up with Firestore and Realtime Database enabled.
- Basic knowledge of Kotlin and Firebase.

### Installation Steps

1. **Clone the Repository**:
    ```bash
    git clone [https://github.com/NiravDev1/Jewelry-App.git]
    ```

2. **Open in Android Studio**:
    - Open Android Studio and select `Open an existing project`.
    - Navigate to the cloned repository and open the project.

3. **Set Up Firebase**:
    - Go to the Firebase Console and create a new project.
    - Add two Android apps within your Firebase project, one for the Customer/Seller app and one for the Admin app.
    - Download the `google-services.json` files for both apps and place them in their respective app modules (`app` and `admin`).

4. **Sync the Project**:
    - Ensure the project is synced with Firebase services by going to `Tools` > `Firebase` in Android Studio and connecting your app.

5. **Build and Run**:
    - Build the project and run the apps on your preferred emulator or Android device.

### Firebase Setup

1. **Firestore Database**:
    - Create collections for `Users`, `Products`, `Orders`, etc.
    - Set appropriate rules and permissions for data access.

2. **Realtime Database**:
    - Utilize for real-time updates, such as order status changes and chat functionalities.

3. **Firebase Authentication**:
    - Set up authentication methods such as email/password for secure login.

## 📝 Usage

- **Customer/Seller App**:
    - Register or log in as a customer or seller.
    - Explore the app functionalities like browsing products, adding items to the cart, and managing listings.

- **Admin App**:
    - Log in with admin credentials.
    - Manage users, approve products, and oversee orders.

## 🤝 Contributing

Contributions are welcome! Please follow the guidelines below:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Create a new Pull Request.

## 🧑‍💻 Contact

Feel free to reach out for any queries or suggestions!

- **Email**: [niravpanchal6268@gmail.com]l̥

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

Happy Coding! 😊
