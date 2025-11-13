
🎬 CINEMA TICKET BOOKING SYSTEM (JDBC MINI PROJECT)
======================================================

📘 PROJECT OVERVIEW
-------------------
The Cinema Ticket Booking System is a console-based Java application built using JDBC

It allows administrators to manage movies and shows.
Customers can view seat availability, book tickets, and cancel bookings.

The project demonstrates end-to-end database interaction, covering:
- 🔗 Database connectivity with JDBC
- ✍️ CRUD operations
- 💾 Transactions (commit, rollback)
- ⚙️ Batch updates
- 🧩 Parameterized queries (PreparedStatement)
- 🆔 Generated keys handling (RETURN_GENERATED_KEYS)

------------------------------------------------------

🧩 PROJECT MODULES
-------------------
This project is divided into 3 core modules:
1️⃣ Movies & Shows Management
2️⃣ Seat Booking
3️⃣ Payment & Cancellation

------------------------------------------------------

🎞️ MODULE 1: MOVIES & SHOWS MANAGEMENT
---------------------------------------
🎯 Objective:
Allow the admin to add movies and schedule shows in available auditoriums.

🧱 Tables:
- movies(id, title, language, duration_min, certification, status, created_at)
- auditoriums(id, name, seat_rows, seat_cols)
- shows(id, movie_id, auditorium_id, show_time, end_time, status)

✅ Use Case 1: Add Movie
Admin adds a new movie to the catalog.
Steps:
1. Admin enters movie details (title, language, duration, certification).
2. System checks if movie already exists.
3. Inserts record into 'movies' table if not present.
🧠 JDBC Concepts: PreparedStatement, duplicate check, INSERT, SELECT

✅ Use Case 2: Create Show
Admin schedules a show for a movie.
Steps:
1. Select movie and auditorium.
2. Enter show date/time.
3. Check for conflicts and insert show if available.
🧠 JDBC Concepts: Time validation, PreparedStatement, transaction management

------------------------------------------------------

🪑 MODULE 2: SEAT BOOKING
--------------------------
🎯 Objective:
Allow customers to view seat availability and make bookings.

🧱 Tables:
- seats(id, auditorium_id, row_label, seat_no, seat_type)
- show_seats(id, show_id, seat_id, status, price)
- bookings(id, user_name, user_phone, show_id, total_amount, status, booked_at)
- booking_items(id, booking_id, show_seat_id, price)

✅ Use Case 1: View Seat Availability
Customer views available seats for a show.
Steps:
1. Enter show_id.
2. Fetch seat status (AVAILABLE/BOOKED/HELD) from 'show_seats'.
3. Display seat map on console.
🧠 JDBC Concepts: SELECT, JOIN, ResultSet mapping

✅ Use Case 2: Book Tickets (Atomic Transaction)
Customer books multiple seats at once.
Steps:
1. Select show_id and seats.
2. Check seat availability.
3. Start transaction → lock seats → update to BOOKED → create booking.
4. Commit on success, rollback on failure.
🧠 JDBC Concepts: Transactions, locking, batch updates, GeneratedKeys

------------------------------------------------------

💳 MODULE 3: PAYMENT & CANCELLATION
-----------------------------------
🎯 Objective:
Simulate payment confirmation and allow cancellation before show time.

🧱 Tables:
- payments(id, booking_id, amount, method, status, txn_ref, paid_at)

✅ Use Case 1: Payment Confirmation
Simulate a payment after booking.
Steps:
1. Choose payment method (Cash/Card/UPI).
2. Create payment record with status='SUCCESS'.
3. Update booking status to CONFIRMED.
🧠 JDBC Concepts: UPDATE, transaction consistency

✅ Use Case 2: Cancel Booking
Cancel confirmed bookings before show time.
Steps:
1. Enter booking ID.
2. Validate timing (≥2 hours before show).
3. Start transaction → set booking CANCELLED → free seats → mark REFUND_PENDING.
🧠 JDBC Concepts: Conditional queries, UPDATE, rollback, date comparison

------------------------------------------------------

🎓 LEARNING OUTCOMES
---------------------
By completing this project, you will learn:
✅ Connecting Java with MySQL using JDBC
✅ Performing CRUD operations
✅ Handling transactions & rollbacks
✅ Using PreparedStatements
✅ Mapping ResultSets to Java objects
✅ Simulating real-world workflows

------------------------------------------------------

📁 PROJECT STRUCTURE
---------------------
src/
 ├── dao/
 │    ├── MovieDAO.java
 │    ├── ShowDAO.java
 │    ├── SeatDAO.java
 │    ├── BookingDAO.java
 │    └── PaymentDAO.java
 ├── service/
 │    ├── MovieService.java
 │    ├── BookingService.java
 │    └── PaymentService.java
 ├── model/
 │    ├── Movie.java
 │    ├── Show.java
 │    ├── Seat.java
 │    ├── Booking.java
 │    └── Payment.java
 ├── util/
 │    └── DBConnection.java
 └── Main.java

------------------------------------------------------

==== CINEMA TICKET BOOKING ====

🖥️ START CONSOLE MENU
-----------------------
Select User Type
1️⃣ Admin
2️⃣ Customer
3️⃣ Exit


🖥️ SAMPLE ADMIN CONSOLE MENU
-----------------------
1️⃣ Add Movie
2️⃣ Create Show
3️⃣ Exit


-----------------------
🖥️ SAMPLE CUSTOMER CONSOLE MENU
3️⃣ View Seats
4️⃣ Book Tickets
5️⃣ Confirm Payment
6️⃣ Cancel Booking
7️⃣ Exit

------------------------------------------------------
