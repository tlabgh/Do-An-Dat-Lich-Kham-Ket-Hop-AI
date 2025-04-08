from sqlalchemy import text
from sqlalchemy.orm import Session

import schemas
import crud

from typing import Optional, Dict, Any, List


# Appointment CRUD operations
def get_appointment(db: Session, appointment_id: int):
    query = text("SELECT * FROM Appointments WHERE appointment_id = :appointment_id")
    result = db.execute(query, {"appointment_id": appointment_id}).first()
    return result


def get_appointments(db: Session, skip: int = 0, limit: int = 100) -> List[dict]:
    query = text("SELECT * FROM Appointments LIMIT :limit OFFSET :skip")
    result = db.execute(query, {"skip": skip, "limit": limit}).fetchall()

    appointments = [
        {
            "appointment_id": row[0],
            "patient_id": row[1],
            "doctor_id": row[2],
            "department_id": row[3],
            "appointment_date": row[4],
            "shift": row[5],
            "description": row[6],
            "status": row[7],
            "created_at": row[8]
        } for row in result
    ]
    return appointments


def get_appointments_by_patient(db: Session, patient_id: int, status: str, skip: int = 0, limit: int = 100) -> List[Dict[str, Any]]:
    query = text("""
        SELECT a.appointment_id, a.patient_id, pu.full_name AS patient_name, pu.phone AS patient_phone, 
               pu.gender AS patient_gender, pu.date_of_birth AS patient_date_of_birth, 
               a.doctor_id, du.full_name AS doctor_name, du.avatar_url AS doctor_avatar_url, 
               a.department_id, dep.name AS department_name, a.appointment_date, a.shift, 
               a.description, a.status, a.created_at
        FROM Appointments a
        JOIN Patients p ON a.patient_id = p.patient_id
        JOIN Users pu ON p.patient_id = pu.user_id
        JOIN Doctors d ON a.doctor_id = d.doctor_id
        JOIN Users du ON d.doctor_id = du.user_id
        JOIN Departments dep ON a.department_id = dep.department_id
        WHERE a.patient_id = :patient_id AND a.status = :status
        LIMIT :limit OFFSET :skip
    """)
    result = db.execute(query, {"patient_id": patient_id, "status": status, "skip": skip, "limit": limit}).fetchall()

    appointments = [
        {
            "appointment_id": row[0],
            "patient_id": row[1],
            "patient_name": row[2],
            "patient_phone": row[3],
            "patient_gender": row[4],
            "patient_date_of_birth": row[5],
            "doctor_id": row[6],
            "doctor_name": row[7],
            "doctor_avatar_url": row[8],
            "department_id": row[9],
            "department_name": row[10],
            "appointment_date": row[11],
            "shift": row[12],
            "description": row[13],
            "status": row[14],
            "created_at": row[15]
        } for row in result
    ]
    return appointments

def get_appointments_by_doctor(db: Session, doctor_id: int, status: str, skip: int = 0, limit: int = 100) -> List[Dict[str, Any]]:
    query = text("""
        SELECT a.appointment_id, a.patient_id, pu.full_name AS patient_name, a.doctor_id, du.full_name AS doctor_name,
               a.department_id, dep.name AS department_name, a.appointment_date, a.shift, a.description, a.status, a.created_at
        FROM Appointments a
        JOIN Patients p ON a.patient_id = p.patient_id
        JOIN Users pu ON p.patient_id = pu.user_id
        JOIN Doctors d ON a.doctor_id = d.doctor_id
        JOIN Users du ON d.doctor_id = du.user_id
        JOIN Departments dep ON a.department_id = dep.department_id
        WHERE a.doctor_id = :doctor_id AND a.status = :status
        LIMIT :limit OFFSET :skip
    """)
    result = db.execute(query, {"doctor_id": doctor_id, "status": status, "skip": skip, "limit": limit}).fetchall()

    appointments = [
        {
            "appointment_id": row[0],
            "patient_id": row[1],
            "patient_name": row[2],
            "doctor_id": row[3],
            "doctor_name": row[4],
            "department_id": row[5],
            "department_name": row[6],
            "appointment_date": row[7],
            "shift": row[8],
            "description": row[9],
            "status": row[10],
            "created_at": row[11]
        } for row in result
    ]
    return appointments

def count_appointments(db: Session, doctor_id: int, appointment_date: str, shift: str) -> int:
    query = text("""
        SELECT COUNT(*)
        FROM Appointments
        WHERE doctor_id = :doctor_id
        AND appointment_date = :appointment_date
        AND shift = :shift
        AND status = :status
    """)
    result = db.execute(query, {
        "doctor_id": doctor_id,
        "appointment_date": appointment_date,
        "shift": shift,
        "status": "Scheduled"
    }).scalar()
    return result

def create_appointment(db: Session, appointment: schemas.AppointmentCreate):
    query = text("""
        INSERT INTO Appointments (patient_id, doctor_id, department_id, appointment_date, shift, description)
        VALUES (:patient_id, :doctor_id, :department_id, :appointment_date, :shift, :description)
    """)

    db.execute(
        query,
        {
            "patient_id": appointment.patient_id,
            "doctor_id": appointment.doctor_id,
            "department_id": appointment.department_id,
            "appointment_date": appointment.appointment_date,
            "shift": appointment.shift,
            "description": appointment.description
        }
    )
    db.commit()

    # Get the last inserted ID
    result = db.execute(text("SELECT LAST_INSERT_ID()")).scalar()

    return get_appointment(db, result)


def update_appointment(db: Session, appointment_id: int, appointment_data: Dict[str, Any]):
    # First check if appointment exists
    appointment = get_appointment(db, appointment_id)
    if not appointment:
        return None

    # Prepare update parts
    update_parts = []
    params = {"appointment_id": appointment_id}

    valid_fields = ["appointment_date", "shift", "description", "status"]

    for key, value in appointment_data.items():
        if key in valid_fields:
            update_parts.append(f"{key} = :{key}")
            params[key] = value

    if not update_parts:
        return appointment

    # Build and execute update query
    query = text(f"""
        UPDATE Appointments
        SET {', '.join(update_parts)}
        WHERE appointment_id = :appointment_id
    """)

    db.execute(query, params)
    db.commit()

    return get_appointment(db, appointment_id)


def delete_appointment(db: Session, appointment_id: int):
    # First get the appointment to return it
    appointment = get_appointment(db, appointment_id)
    if not appointment:
        return None

    # Delete the appointment
    query = text("DELETE FROM Appointments WHERE appointment_id = :appointment_id")
    db.execute(query, {"appointment_id": appointment_id})
    db.commit()

    return appointment