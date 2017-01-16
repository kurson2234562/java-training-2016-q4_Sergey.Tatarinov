package ua.nure.tatarinov.SummaryTask4.db.dto;

public class ThemeDTO {

    private int idTheme;
    private String nameTheme;

    public ThemeDTO(){

    }

    public ThemeDTO(int idTheme, String nameTheme) {
        this.idTheme = idTheme;
        this.nameTheme = nameTheme;
    }

    @Override
    public String toString() {
        return "ThemeDTO{" +
                "idTheme=" + idTheme +
                ", nameTheme='" + nameTheme + '\'' +
                '}';
    }

    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }

    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }
}
