package Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterSkinConsultationManagerTest {

    @Test
    void testAddANewDoctor() {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        assertEquals("Doctor added successfully", (manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12345", "Cosmetic-Dermatology", "Available")));
    }

    @Test
    void testAddMoreThanTenDoctors() {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12345", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12346", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12347", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12348", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12349", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12310", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12311", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12312", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12313", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12314", "Cosmetic-Dermatology", "Available");
        assertEquals("Doctor Array is Full", (manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12315", "Cosmetic-Dermatology", "Available")));
    }

    @Test
    void testDeleteADoctor() {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12345", "Cosmetic-Dermatology", "Available");
        assertEquals("Doctor John Mike deleted successfully.", (manager.deleteADoctor("DOC12345")));
    }

    @Test
    void testDeleteANotAvailableDoctor() {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12345", "Cosmetic-Dermatology", "Not Available");
        assertEquals("Doctor not found by Medical Licence Number: DOC00000", (manager.deleteADoctor("DOC00000")));
    }

    @Test
    void saveDoctorsToFile() {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12345", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12346", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12347", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12348", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12349", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12310", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12311", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12312", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12313", "Cosmetic-Dermatology", "Available");
        manager.addANewDoctor("John", "Mike", "12-May-2000", "0702010059", "DOC12314", "Cosmetic-Dermatology", "Available");
        assertEquals("Doctors saved to file successfully", (manager.saveDoctorsToFile()));
    }

    @Test
    void loadDoctorsFromFile() {
    }

    @Test
    void addNewPatient() {
    }

    @org.junit.jupiter.api.Test
    void deleteAPatient() {
    }

    @Test
    void printAllPatients() {
    }

    @Test
    void savePatientsToFile() {
    }

    @Test
    void loadPatientsFromFile() {
    }

    @Test
    void addNewConsultation() {
    }

    @Test
    void deleteAConsultation() {
    }

    @Test
    void printAllConsultations() {
    }

    @Test
    void saveConsultationsToFile() {
    }

    @Test
    void loadConsultationsFromFile() {
    }

    @Test
    void strToDate() {
    }
}