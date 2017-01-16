package ua.nure.tatarinov.SummaryTask4.db.dto;

public class StatusDTO {
    private int idStatus;
    private String nameStatus;

    public StatusDTO(){

    }

    public StatusDTO(int idStatus, String nameStatus) {
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }

    @Override
    public String toString() {
        return "StatusDTO{" +
                "idStatus=" + idStatus +
                ", nameStatus='" + nameStatus + '\'' +
                '}';
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }


    public class Костя{
        private String имя;
        private String фамилия;
    }
}
