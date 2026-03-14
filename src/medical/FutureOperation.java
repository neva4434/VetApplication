package medical;

/*
kısırlaştırma sonrası dişilerde dikiş alımı
sezaryan
yatalak bırakmışsındır x gün sonra almaya gel
parvo hastası köpek(direkt yatışa alınıyor)
 */

import java.util.Date;

// Gelecek randevuları tutan basit sınıf
public class FutureOperation {
    private String operationType; // Örn: Sterilization
    private Date plannedDate;
    private String status; // Scheduled, Cancelled

    public FutureOperation(String operationType, Date plannedDate) {
        this.operationType = operationType;
        this.plannedDate = plannedDate;
        this.status = "Scheduled";
    }

    @Override
    public String toString() {
        return "Upcoming: " + operationType + " | Date: " + plannedDate;
    }
}