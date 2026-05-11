





public class PainStatistic
{
    private String dateTime;
    private String bodyPart;
    private String painType;
    private int painScale;
    private String comment;

    public PainStatistic(String dateTime, String bodyPart, String painType, int painScale, String comment)
    {
        this.dateTime = dateTime;
        this.bodyPart = bodyPart;
        this.painType = painType;
        this.painScale = painScale;
        this.comment = comment;
    }

    // Gettery
    public String getDateTime() { return dateTime; }
    public String getBodyPart() { return bodyPart; }
    public String getPainType() { return painType; }
    public int getPainScale() { return painScale; }
    public String getComment() { return comment; }

    @Override
    public String toString() {
        return "Data: " + dateTime +
                ", Część ciała: " + bodyPart +
                ", Rodzaj bólu: " + painType +
                ", Skala: " + painScale +
                ", Komentarz: " + comment;
    }
}
