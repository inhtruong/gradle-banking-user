package model;

public class Transfer {
    private int id;
    private int idSender;
    private int idReceive;
    private float amountReceive;
    private int feesPercent;
    private float feesAmount;
    private String nameSender;
    private String nameReceive;

    public Transfer() {

    }

    public Transfer(int id, int idSender, int idReceive, int feesPercent, float feesAmount) {
        this.id = id;
        this.idSender = idSender;
        this.idReceive = idReceive;
        this.feesPercent = feesPercent;
        this.feesAmount = feesAmount;
    }

    public Transfer(int idSender, int idReceive, float amountReceive, int feesPercent, float feesAmount) {
        this.idSender = idSender;
        this.idReceive = idReceive;
        this.amountReceive = amountReceive;
        this.feesPercent = feesPercent;
        this.feesAmount = feesAmount;
    }

    public Transfer(int id, int idSender, int idReceive, float amountReceive, int feesPercent, float feesAmount, String nameSender, String nameReceive) {
        this.id = id;
        this.idSender = idSender;
        this.idReceive = idReceive;
        this.amountReceive = amountReceive;
        this.feesPercent = feesPercent;
        this.feesAmount = feesAmount;
        this.nameSender = nameSender;
        this.nameReceive = nameReceive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdReceive() {
        return idReceive;
    }

    public void setIdReceive(int idReceive) {
        this.idReceive = idReceive;
    }

    public float getAmountReceive() {
        return amountReceive;
    }

    public void setAmountReceive(float amountReceive) {
        this.amountReceive = amountReceive;
    }

    public int getFeesPercent() {
        return feesPercent;
    }

    public void setFeesPercent(int feesPercent) {
        this.feesPercent = feesPercent;
    }

    public float getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(float feesAmount) {
        this.feesAmount = feesAmount;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }

    public String getNameReceive() {
        return nameReceive;
    }

    public void setNameReceive(String nameReceive) {
        this.nameReceive = nameReceive;
    }
}
