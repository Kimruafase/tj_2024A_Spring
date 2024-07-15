package example.day08.todo;

public class ToDoDto {
    private int tNo;
    private String tContent;
    private int tState;

    public ToDoDto(){}

    public ToDoDto(int tNo, String tContent, int tState) {
        this.tNo = tNo;
        this.tContent = tContent;
        this.tState = 0;
    }

    public int gettNo() {
        return tNo;
    }

    public void settNo(int tNo) {
        this.tNo = tNo;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }

    public int gettState() {
        return tState;
    }

    public void settState(int tState) {
        this.tState = tState;
    }

    @Override
    public String toString() {
        return "ToDoDto{" +
                "tNo=" + tNo +
                ", tContent='" + tContent + '\'' +
                ", tState=" + tState +
                '}';
    }
}
