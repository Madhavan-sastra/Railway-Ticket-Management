import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingDAO {
    public void bookTicket(Booking booking) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO Bookings (user_id, train_id, booking_date, status, payment_status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getTrainId());
            pstmt.setString(3, booking.getBookingDate());
            pstmt.setString(4, booking.getStatus());
            pstmt.setString(5, booking.getPaymentStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
