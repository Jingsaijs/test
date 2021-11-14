package com.leolee.reservation.Dao;

import com.leolee.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT * FROM reservation WHERE date = :reserveDate AND reservation_status = :reservationStatus AND department_id = :departmentId", nativeQuery = true)
    List<Reservation> findReservationsByDateAndReservationStatusAndDepartmentId(@Param("reserveDate") Date reserveDate,
                                                                                @Param("reservationStatus") boolean reservationStatus,
                                                                                @Param("departmentId") int departmentId);

    @Query(value = "SELECT * FROM reservation WHERE date BETWEEN :beginDate AND :endDate " +
            "AND phone = :phone " +
            "AND reservation_status = :reservationStatus", nativeQuery = true)
    List<Reservation> findReservationsByDateAndPhoneAndReservationStatus(@Param("beginDate") Date beginDate,
                                                                         @Param("endDate") Date endDate,
                                                                         @Param("phone") String phone,
                                                                         @Param("reservationStatus") boolean reservationStatus);

    @Transactional
    @Modifying
    @Query(value = "UPDATE reservation SET reservation_status = :reservationStatus WHERE id = :id", nativeQuery = true)
    void updateReservationById(@Param("reservationStatus") boolean reservationStatus, @Param("id") int id);
}
