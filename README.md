🧾 Bill Generation System (Spring Boot)
A complete backend system built using Spring Boot that handles product management, billing, payment processing, and customer notifications (SMS, Email, WhatsApp).

🚀 Features
📦 Product Management (Add / View Products)
🧾 Bill Generation with GST Calculation
👤 Customer Management
💳 Payment Confirmation System
📩 Notifications:
SMS (Twilio)
Email (SMTP)
WhatsApp Integration
📉 Stock Management (Auto deduction)
⚠️ Stock validation (Out of stock handling)

🏗️ Project Architecture
This project follows a clean layered architecture:
Controller → Service → Repository → Database
    ↓
DTO / Model

Controller → Handles API requests
Service → Business logic
Repository → Database operations
Model → Entity classes
DTO → Request & Response handling

🛠️ Tech Stack
☕ Java (Spring Boot)
🗄️ Spring Data JPA (Hibernate)
🌐 REST APIs
🛢️ MySQL Database
📩 Twilio (SMS)
📧 Java Mail Sender
💬 WhatsApp API Integration

⚙️ API Endpoints
🔹 1. Add Product
POST /admin/add-product
{
  "name": "Mobile",
  "price": 15000,
  "stock": 50,
  "threshold": 10
}


🔹 2. Get All Products
GET /admin/products

🔹 3. Generate Bill
POST /billing/generate
{
  "customerName": "Sujit",
  "mobileNo": "+919876XXXXXX",
  "email": "Send_mail@gmail.com",
  "productId": 1,
  "productCount": 2
}


🔹 4. Confirm Payment
POST /payment/confirm
{
  "billId": 1,
  "paymentOption": 1
}

💳 Payment Options:
1 = UPI  
2 = CARD  
3 = NET BANKING  
4 = CASH  
5 = WALLET


🔄 Application Flow
1️⃣ Add Product
2️⃣ Generate Bill
3️⃣ Stock Validation
4️⃣ Bill Saved in Database
5️⃣ Notifications Sent
6️⃣ Payment Confirmation
7️⃣ Status Updated

🧠 Business Logic Highlights
✅ Automatic GST Calculation (18%)
✅ Stock Deduction on Purchase
✅ Prevents Purchase if Stock is Insufficient
✅ Multi-channel Notification System
✅ Payment Status Handling (PENDING → SUCCESS)

⚠️ Error Handling
❌ Product Not Found
❌ Insufficient Stock
❌ Invalid Payment
⚠️ Notification failure does NOT affect billing

🔧 Setup Instructions
1️⃣ Clone Repository
git clone https://github.com/sujitmangukiya/Bill-Generation-System.git


2️⃣ Configure Database
Update application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password


3️⃣ Twilio Setup (SMS)
twilio.account.sid=YOUR_SID
twilio.auth.token=YOUR_TOKEN
twilio.phone.number=YOUR_NUMBER


4️⃣ Email Setup
spring.mail.username=your_email
spring.mail.password=app_password
spring.mail.host=smtp.gmail.com
spring.mail.port=587


5️⃣ Run Application
Run ProductApplication.java


📌 Future Enhancements
🔐 JWT Authentication
📊 Dashboard (Frontend Integration)
📱 Android App Integration
📈 Sales Analytics
🧾 PDF Bill Generation

👨‍💻 Author
Sujit Mangukiya
🚀 Backend Developer 

⭐ Support
If you like this project, give it a ⭐ on GitHub and share it!
 

